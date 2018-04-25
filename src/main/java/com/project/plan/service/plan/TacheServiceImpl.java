package com.project.plan.service.plan;

import com.project.plan.common.Constats;
import com.project.plan.common.utils.DateUtil;
import com.project.plan.common.utils.TextUtil;
import com.project.plan.dao.IUserDao;
import com.project.plan.dao.plan.IOpenateDao;
import com.project.plan.dao.plan.ITacheDao;
import com.project.plan.dao.support.IBaseDao;
import com.project.plan.entity.User;
import com.project.plan.entity.plan.Module;
import com.project.plan.entity.plan.Openate;
import com.project.plan.entity.plan.Tache;
import com.project.plan.service.support.impl.BaseServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Barry on 2018/4/20.
 */
@Service
public class TacheServiceImpl extends BaseServiceImpl<Tache,Integer> {
    @Autowired
    private ITacheDao tacheDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IOpenateDao openateDao;
    @Override
    public IBaseDao<Tache, Integer> getBaseDao() {
        return this.tacheDao;
    }


    public void saveOrUpdate(Tache tache) {
        if(tache.getId() != null){
            Date sysDate = new Date();

            Tache dbTache = super.find(tache.getId());

//            Module m = new Module();//环节修改模块,不让修改
//            m.setId(tache.getModule().getId());
//            dbTache.setModule(m);

            dbTache.setPlanBeginTime(tache.getPlanBeginTime());
            dbTache.setPlanEndTime(tache.getPlanEndTime());
            dbTache.setRealBeginTime(tache.getRealBeginTime());
            dbTache.setRealEndTime(tache.getRealEndTime());
            dbTache.setStatus(tache.getStatus());
            dbTache.setArchiveTime(tache.getArchiveTime());

            dbTache.setUpdateComment(tache.getUpdateComment());
            dbTache.setUpdateTime(sysDate);

            User u = new User();//修改负责人
            u.setId(tache.getUser().getId());
            dbTache.setUser(u);

            if( !dbTache.getStatus().equals(tache.getStatus()) &&Tache.STAT_SUCCESS == dbTache.getStatus() ){
                dbTache.setArchiveTime(sysDate);
            }

            super.update(dbTache);
        }else{
            tache.setCreateTime(new Date());
            tache.setUpdateTime(new Date());
            save(tache);
        }
    }


    /**
     * 每个模块都有11个环节,添加模块为其添加11个环节记录
     * @param module
     */
    public void saveTache(Module module) {
        List<Tache> tacheList = new ArrayList<>();

        Date date = new Date();
        for(int i=0 ; i<Tache.TACHE_INDEX_NAMES.length;i++ ){
            Tache t = new Tache();
            t.setTacheIndex(i+1);
            t.setName(Tache.TACHE_INDEX_NAMES[i]);
            t.setStatus(Tache.STAT_NEW);
            t.setModule(module);

            t.setPlanBeginTime(module.getStartTime());//环节的计划开始时间和计划结束时间先默认功能模块的计划时间,后续给其修改
            t.setPlanEndTime(module.getWishTime());

            t.setCreateTime(date);

            tacheList.add(t);
        }
        tacheDao.save(tacheList);
    }


    @Transactional
    public void editTache(Tache tache, HttpServletRequest request) {

        Date sysDate = new Date();
        if(tache.getId()!=null){//是修改添加修改了什么的记录
            Tache dbTache = tacheDao.findOne(tache.getId());
            //环节中的修改需要记录谁什么时候编辑了什么做了哪些改动,并将第一个修改的人做为这个环节的责任人
            Openate log =new Openate();
            StringBuffer requestUrl = request.getRequestURL();
            String remoteAddr = request.getRemoteAddr();
            log.setIp(remoteAddr);
            log.setUrl(requestUrl.toString());
            log.setCreateTime(sysDate);
            log.setStatus(Openate.STATUS_WARN);
            log.setDuration(0L);

            String createComment = compateUpdateComment(dbTache,tache);
            log.setCreateComment(createComment);
//                Tache t = new Tache();
//                t.setId(tache.getId());
//                log.setTache(m);
            log.setTacheId(tache.getId());

            User loginUser = (User) SecurityUtils.getSubject().getSession()
                    .getAttribute(Constats.CURRENTUSER);
            log.setUserId(loginUser.getId());
            openateDao.save(log);
        }
        saveOrUpdate(tache);
    }


    /**
     * 计算 将 tache修改成 dbTache修改了哪些内容,并将修改内容记录下来
     * @param dbTache
     * @param tache
     * @return
     */
    private String compateUpdateComment(Tache dbTache, Tache tache) {
        StringBuffer sb = new StringBuffer();
        if(dbTache==null||tache==null){
            throw new RuntimeException("修改的环节或要修改的环节为空!!!");
        }

        if(dbTache.getUser()==null&&tache.getUser()==null){//没有选择负责人,默认修改人

        }else if(dbTache.getUser()==null
                &&tache.getUser()!=null&&tache.getUser().getId()!=null){//添加负责人
            User u = userDao.findOne(tache.getUser().getId());
            sb.append(" 添加责任人为 “"+u.getUserName()+"”");
        }else if(dbTache.getUser() != null&&dbTache.getUser().getId()!=null
                &&tache.getUser() == null){//修改负责人为空
            sb.append(" 将责任人由“"+dbTache.getUser().getUserName()+"” 改为空");
        }else if(dbTache.getUser().getId()!=null&&tache.getUser().getId()!=null&&
                !dbTache.getUser().getId().equals(tache.getUser().getId())){
            User u = userDao.findOne(tache.getUser().getId());
            sb.append(" 将责任人由“"+dbTache.getUser().getUserName()+"” 改为 “"+u.getUserName()+"”");
        }else{//没有修改不记录
            System.out.println("dbTache: "+dbTache+"  ---> "+tache);
        }

        if(dbTache.getStatus().intValue()!=tache.getStatus().intValue()){//修改环节状态
            String dbStatus = Tache.TACHE_STATUS(dbTache.getStatus());
            String status = Tache.TACHE_STATUS(tache.getStatus());
            sb.append(" 将修改描述由“"+dbStatus+"”改成“"+status+"”");
        }

        if(TextUtil.isNullOrEmpty(dbTache.getUpdateComment())
                &&!TextUtil.isNullOrEmpty(tache.getUpdateComment())){
            //原来没有修改描述,新加修改描述
            sb.append(" 添加修改描述为:“"+tache.getUpdateComment()+"”");
        }else if(!TextUtil.isNullOrEmpty(dbTache.getUpdateComment())
                &&TextUtil.isNullOrEmpty(tache.getUpdateComment())){
            sb.append(" 删除为:“"+tache.getUpdateComment()+"”的修改描述");
        }else if(!TextUtil.isNullOrEmpty(dbTache.getUpdateComment())
                &&!TextUtil.isNullOrEmpty(tache.getUpdateComment())
                &&!dbTache.getUpdateComment().trim().equalsIgnoreCase(tache.getUpdateComment())){//修改备注
            sb.append(" 将备注从 “"+dbTache.getUpdateComment()+"”修改为“"+tache.getUpdateComment()+"”");
        }else{}//还有一种空修改成空,不处理

        String appenStr = " - ";
        String dbPlanTimeStr =  DateUtil.format(dbTache.getPlanBeginTime(),DateUtil.DEF_DATE_PATTERN,"")+appenStr+ DateUtil.format(dbTache.getPlanEndTime(),DateUtil.DEF_DATE_PATTERN,"");
        String planTimeStr =  DateUtil.format(tache.getPlanBeginTime(),DateUtil.DEF_DATE_PATTERN,"")+appenStr+ DateUtil.format(tache.getPlanEndTime(),DateUtil.DEF_DATE_PATTERN,"");
        if(appenStr.equals(dbPlanTimeStr)&&!appenStr.equals(planTimeStr)){//添加
            sb.append(" 添加计划时间为:“"+planTimeStr+"”！");
        }else if(!appenStr.equals(dbPlanTimeStr)&&appenStr.equals(planTimeStr)){//删除
            sb.append(" 删除为“"+dbPlanTimeStr+"”的计划时间！");
        }else if(!dbPlanTimeStr.equalsIgnoreCase(planTimeStr)){
            sb.append(" 将计划时间从 “"+dbPlanTimeStr+"”修改为“"+planTimeStr+"”");
        }else{}//还有一种空修改成空,不处理

        String dbRealTimeStr =  DateUtil.format(dbTache.getRealBeginTime(),DateUtil.DEF_DATE_PATTERN,"")+appenStr+ DateUtil.format(dbTache.getRealEndTime(),DateUtil.DEF_DATE_PATTERN,"");
        String realTimeStr =  DateUtil.format(tache.getRealBeginTime(),DateUtil.DEF_DATE_PATTERN,"")+appenStr+ DateUtil.format(tache.getRealEndTime(),DateUtil.DEF_DATE_PATTERN,"");
        if(appenStr.equals(dbRealTimeStr)&&!appenStr.equals(realTimeStr)){//添加
            sb.append(" 添加实际时间为:“"+planTimeStr+"”！");
        }else if(!appenStr.equals(dbRealTimeStr)&&appenStr.equals(realTimeStr)){//删除
            sb.append(" 删除为“"+dbRealTimeStr+"”的实际时间！");
        }else if(!dbRealTimeStr.equalsIgnoreCase(realTimeStr)){
            sb.append(" 将实际时间从 “"+dbRealTimeStr+"”修改为“"+realTimeStr+"”");
        }else{}//还有一种空修改成空,不处理
        return sb.toString();
    }
}
