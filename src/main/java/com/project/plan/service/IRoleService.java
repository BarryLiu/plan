package com.project.plan.service;

import com.project.plan.entity.Role;
import com.project.plan.service.support.IBaseService;

/**
 * <p>
 * 角色服务类
 * </p>
 *
 */
public interface IRoleService extends IBaseService<Role,Integer> {

	/**
	 * 添加或者修改角色
	 * @param role
	 */
	void saveOrUpdate(Role role);

	/**
	 * 给角色分配资源
	 * @param id 角色ID
	 * @param resourceIds 资源ids
	 */
	void grant(Integer id, String[] resourceIds);
	
}
