package com.project.plan.controller.plan;

import com.project.plan.common.Constats;
import com.project.plan.common.JsonResult;
import com.project.plan.common.utils.DateUtil;
import com.project.plan.common.utils.TextUtil;
import com.project.plan.controller.BaseController;
import com.project.plan.entity.User;
import com.project.plan.entity.plan.Module;
import com.project.plan.entity.plan.Openate;
import com.project.plan.entity.plan.Tache;
import com.project.plan.service.impl.UserServiceImpl;
import com.project.plan.service.plan.OpenateServiceImpl;
import com.project.plan.service.plan.PlanServiceImpl;
import com.project.plan.service.plan.TacheServiceImpl;
import com.project.plan.service.specification.SimpleSpecificationBuilder;
import com.project.plan.service.specification.SpecificationOperator;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by Barry on 2018/4/20.
 */
@Controller
@RequestMapping("/plan/tache")
public class TacheController extends BaseController {
    @Autowired
    private PlanServiceImpl planService;
    @Autowired
    private TacheServiceImpl tacheService;
    @Autowired
    private OpenateServiceImpl openateService;
    @Autowired
    private UserServiceImpl userService;


    @RequestMapping("/index")
    public String index() {
        return "plan/tache/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Page<Tache> list() {
        SimpleSpecificationBuilder<Tache> builder = new SimpleSpecificationBuilder<Tache>();
        String searchText = request.getParameter("searchText");
        if(StringUtils.isNotBlank(searchText)){
            builder.add("name", SpecificationOperator.Operator.likeAll.name(), searchText);
        }
        Page<Tache> page = tacheService.findAll(builder.generateSpecification(),getPageRequest());
        return page;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        List<User> userList = userService.findAll();
        map.put("userList", userList);
        return "plan/tache/form";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        Tache tache = tacheService.find(id);
        map.put("tache", tache);
        List<User> userList = userService.getBaseDao().findAll();
        map.put("userList", userList);

        return "plan/tache/form";
    }

    @RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(Tache tache, ModelMap map,Integer moduleId){
        try {
            Date sysDate = new Date();
            if(tache.getId()!=null){//是修改添加修改了什么的记录
                Tache dbTache = tacheService.find(tache.getId());
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
                Module m = new Module();
                m.setId(moduleId);
                log.setModule(m);

                User loginUser = (User) SecurityUtils.getSubject().getSession()
                        .getAttribute(Constats.CURRENTUSER);
                log.setUserId(loginUser.getId());
                openateService.save(log);

//                tache.getUserId()

            }
            tacheService.saveOrUpdate(tache);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id,ModelMap map) {
        try {
            tacheService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
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
