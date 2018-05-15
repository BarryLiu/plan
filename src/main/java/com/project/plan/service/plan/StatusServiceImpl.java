package com.project.plan.service.plan;

import com.project.plan.dao.plan.IProjectTacheDao;
import com.project.plan.dao.plan.IStatusDao;
import com.project.plan.dao.support.IBaseDao;
import com.project.plan.entity.plan.ProjectTache;
import com.project.plan.entity.plan.Status;
import com.project.plan.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Barry on 2018/5/15.
 */
@Service
public class StatusServiceImpl  extends BaseServiceImpl<Status,Integer> {
    @Autowired
    private IStatusDao statusDao;
    @Override
    public IBaseDao<Status, Integer> getBaseDao() {
        return this.statusDao;
    }

    public void saveOrUpdate(Status status) {
        if(status.getId() != null){
            Date sysDate = new Date();
            Status dbProjectTache = super.find(status.getId());
            dbProjectTache.setName(status.getName());

            dbProjectTache.setStatus(status.getStatus());
            dbProjectTache.setUpdateComment(status.getUpdateComment());
            dbProjectTache.setUpdateTime(sysDate);

            super.update(dbProjectTache);
        }else{
            status.setCreateTime(new Date());
            status.setUpdateTime(new Date());
            statusDao.save(status);
        }
    }
}
