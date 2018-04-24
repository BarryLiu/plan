package com.project.plan.dao.plan;

import com.project.plan.dao.support.IBaseDao;
import com.project.plan.entity.plan.Module;
import com.project.plan.entity.plan.Openate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Barry on 2018/4/20.
 */
@Repository
public interface IOpenateDao extends IBaseDao<Openate, Integer> {

    //查询某个环节下面的所有记录
    @Query(value = " from Openate where tacheId = :tacheId ")
    List<Openate> findByTacheId(@Param("tacheId") Integer tacheId);
}
