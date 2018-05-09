package com.project.plan.dao.plan;

import com.project.plan.dao.support.IBaseDao;
import com.project.plan.entity.plan.Tache;
import com.project.plan.entity.plan.TacheUser;
import org.springframework.stereotype.Repository;

/**
 * Created by Barry on 2018/5/9.
 */
@Repository
public interface ITacheUserDao extends IBaseDao<TacheUser, Integer> {

}
