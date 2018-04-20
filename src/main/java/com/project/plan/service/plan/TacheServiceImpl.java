package com.project.plan.service.plan;

import com.project.plan.dao.plan.ITacheDao;
import com.project.plan.dao.support.IBaseDao;
import com.project.plan.entity.plan.Module;
import com.project.plan.entity.plan.Tache;
import com.project.plan.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
