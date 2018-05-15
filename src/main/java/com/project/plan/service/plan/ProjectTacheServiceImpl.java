package com.project.plan.service.plan;

import com.project.plan.dao.plan.IProjectDao;
import com.project.plan.dao.plan.IProjectTacheDao;
import com.project.plan.dao.support.IBaseDao;
import com.project.plan.entity.User;
import com.project.plan.entity.plan.Project;
import com.project.plan.entity.plan.ProjectTache;
import com.project.plan.entity.plan.Tache;
import com.project.plan.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Barry on 2018/5/15.
 */
@Service
public class ProjectTacheServiceImpl extends BaseServiceImpl<ProjectTache,Integer> {
    @Autowired
    private IProjectTacheDao projectTacheDao;
    @Override
    public IBaseDao<ProjectTache, Integer> getBaseDao() {
        return this.projectTacheDao;
    }

    public void saveOrUpdate(ProjectTache projectTache) {
        if(projectTache.getId() != null){
            Date sysDate = new Date();

            ProjectTache dbProjectTache = super.find(projectTache.getId());

            dbProjectTache.setName(projectTache.getName());
            dbProjectTache.setSimpleName(projectTache.getSimpleName());
            dbProjectTache.setHaveStatus(projectTache.getHaveStatus());
            dbProjectTache.setSortIndex(projectTache.getSortIndex());

            dbProjectTache.setStatus(projectTache.getStatus());
            dbProjectTache.setUpdateComment(projectTache.getUpdateComment());
            dbProjectTache.setUpdateTime(sysDate);

            super.update(dbProjectTache);
        }else{
            projectTache.setCreateTime(new Date());
            projectTache.setUpdateTime(new Date());
            projectTacheDao.save(projectTache);
        }
    }


}
