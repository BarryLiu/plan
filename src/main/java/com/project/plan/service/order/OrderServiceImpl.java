package com.project.plan.service.order;

import com.project.plan.dao.order.IOrderDao;
import com.project.plan.dao.support.IBaseDao;
import com.project.plan.entity.order.Order;
import com.project.plan.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order,Integer> {
    @Autowired
    private IOrderDao orderDao;


    @Override
    public IBaseDao<Order, Integer> getBaseDao() {
        return this.orderDao;
    }

    public void saveOrUpdate(Order order) {
        if(order.getId() != null){
            Order dbModule = super.find(order.getId());

            dbModule.setStatus(order.getStatus());
            dbModule.setExpressCompany(order.getExpressCompany());
            dbModule.setExpressNumber(order.getExpressNumber());

            dbModule.setUpdateTime(new Date());
            super.update(dbModule);
        }else{
        	if(order.getCreateTime()!=null) {
                order.setUpdateTime(order.getCreateTime());	
        	}else {
        		order.setCreateTime(new Date());
                order.setUpdateTime(new Date());
        	}
            
            save(order);
        }
    }

    /**
     * 删除订单
     * @param id
     */
    @Transactional
    public void deleteModule(Integer id) {
        super.delete(id);
        System.out.println("delete successful !!! ");
    }

}
