package com.project.plan.service.plan;

import com.project.plan.dao.plan.IModuleDao;
import com.project.plan.dao.support.IBaseDao;
import com.project.plan.entity.plan.Module;
import com.project.plan.entity.plan.Project;
import com.project.plan.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Barry on 2018/4/20.
 */
@Service
public class ModuleServiceImpl extends BaseServiceImpl<Module,Integer> {
    @Autowired
    private IModuleDao moduleDao;

    @Override
    public IBaseDao<Module, Integer> getBaseDao() {
        return this.moduleDao;
    }

    public void saveOrUpdate(Module module) {
        if(module.getId() != null){
            Module dbModule = super.find(module.getId());
            dbModule.setName(module.getName());
            dbModule.setStartTime(module.getStartTime());
            dbModule.setWishTime(module.getWishTime());
            dbModule.setStatus(module.getStatus());
            dbModule.setUpdateTime(new Date());
            super.update(dbModule);
        }else{
            module.setCreateTime(new Date());
            module.setUpdateTime(new Date());
            save(module);
        }

    }
}
