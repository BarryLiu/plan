package com.project.plan.dao.order;

import com.project.plan.dao.support.IBaseDao;
import com.project.plan.entity.order.Order;
import com.project.plan.entity.plan.Module;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDao extends IBaseDao<Order, Integer> {

}
