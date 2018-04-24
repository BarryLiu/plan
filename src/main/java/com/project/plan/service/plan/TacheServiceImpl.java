package com.project.plan.service.plan;

import com.project.plan.dao.plan.ITacheDao;
import com.project.plan.dao.support.IBaseDao;
import com.project.plan.entity.plan.Module;
import com.project.plan.entity.plan.Tache;
import com.project.plan.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
