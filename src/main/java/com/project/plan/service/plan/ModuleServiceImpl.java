package com.project.plan.service.plan;

import com.project.plan.dao.plan.IModuleDao;
import com.project.plan.dao.plan.IProjectDao;
import com.project.plan.dao.support.IBaseDao;
import com.project.plan.entity.plan.Module;
import com.project.plan.entity.plan.Project;
import com.project.plan.entity.plan.Tache;
import com.project.plan.service.support.impl.BaseServiceImpl;
import org.aspectj.weaver.tools.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Barry on 2018/4/20.
 */
@Service
public class ModuleServiceImpl extends BaseServiceImpl<Module,Integer> {
    @Autowired
    private IModuleDao moduleDao;

    @Autowired
    private IProjectDao projectDao;

    @Autowired
    private  TacheServiceImpl tacheService;


    @Override
    public IBaseDao<Module, Integer> getBaseDao() {
        return this.moduleDao;
    }

    public void saveOrUpdate(Module module) {
        if(module.getId() != null){
            Module dbModule = super.find(module.getId());

            Project p = new Project();//如果项目有修改,修改项目
            p.setId(module.getProject().getId());
            dbModule.setProject(p);

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

            tacheService.saveTache(module);
        }

    }

    /** */
    public Page<Module> findAllWithProject(Specification<Module> moduleSpecification, PageRequest pageRequest) {
        Page<Module>  modules =   super.findAll(moduleSpecification,pageRequest);
//        for (Module m: modules) {
////            Project p = projectDao.findOne(m.getProjectId());
////            m.setProject(p);
//            Project p = m.getProject();
//        }
        return modules;
//        return moduleDao.selectWithProject();
        //return super.findAll(moduleSpecification,pageRequest);
    }
}
