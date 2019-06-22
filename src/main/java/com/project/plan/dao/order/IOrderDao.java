package com.project.plan.dao.order;

import org.springframework.stereotype.Repository;

import com.project.plan.dao.support.IBaseDao;
import com.project.plan.entity.order.Order;

@Repository
public interface IOrderDao extends IBaseDao<Order, Integer> {

}
