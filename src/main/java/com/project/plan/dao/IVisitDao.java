package com.project.plan.dao;

import org.springframework.stereotype.Repository;

import com.project.plan.dao.support.IBaseDao;
import com.project.plan.entity.Visit;

/**
 * 记录所有访问
 */
@Repository
public interface IVisitDao extends IBaseDao<Visit, Integer> {


}
