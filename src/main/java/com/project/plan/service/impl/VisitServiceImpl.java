package com.project.plan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.plan.dao.IVisitDao;
import com.project.plan.dao.support.IBaseDao;
import com.project.plan.entity.Visit;
import com.project.plan.service.support.impl.BaseServiceImpl;

/**
 * <p>
 * 访问记录表
 * </p>
 */
@Service
public class VisitServiceImpl extends BaseServiceImpl<Visit, Integer>  {

	@Autowired
	private IVisitDao visitDao;
	
	@Override
	public IBaseDao<Visit, Integer> getBaseDao() {
		return this.visitDao;
	}


}
