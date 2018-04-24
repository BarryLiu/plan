package com.project.plan;

import com.project.plan.common.Constats;
import com.project.plan.common.utils.LDAPControl;
import com.project.plan.common.utils.MD5Utils;
import com.project.plan.common.utils.TUser;
import com.project.plan.entity.User;
import com.project.plan.entity.plan.Module;
import com.project.plan.service.impl.UserServiceImpl;
import com.project.plan.service.plan.ModuleServiceImpl;
import com.project.plan.service.specification.SimpleSpecificationBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanApplicationTests {

	@Autowired
    private UserServiceImpl userService;

	@Autowired
	private ModuleServiceImpl moduleService;
	@Test
	public void contextLoads() {
		System.out.println("xxxxxxxxxxxxxx load test ........");
	}


	//测试同步公司ldap域下的用户插入到系统  tb_user 表中
	@Test
	public void syncLdapUsers(){
		System.out.println("getAllusers:");
		List<TUser> users = LDAPControl.getInstance().getAllLadpUser();

		List<User> userList = new ArrayList<>();
		for(int i=0;i<users.size();i++){
			TUser ldUser = users.get(i);
			System.out.println(ldUser);
			User sysUser = TUser.parseLdapUserToUser(ldUser);
			userList.add(sysUser);
		}
		System.out.print("----插入用户-----"+userService);
		userService.save(userList);

	}

	@Test
	public void testSelect(){
		PageRequest pageRequest =new PageRequest(0,10);
		SimpleSpecificationBuilder<Module> builder = new SimpleSpecificationBuilder<Module>();

		Page<Module> page =  moduleService.findAllWithProject(builder.generateSpecification(),pageRequest);

		System.out.println("-------------------------");
		for (Module m: page.getContent()) {
			System.out.println(m);
		}


	}
//
//
//	@Test
//	@Transactional
//	public void test2(){
//		System.out.println("-----------test2--------------");
//		userMapper.insert("张三2", "123456", "18600000000");
//		int a = 1/0;
//		userMapper.insert("李四2", "123456", "13500000000");
//		User u = userMapper.findUserByPhone("12345678910");
//		System.out.println("-----------test2 end --------------");
//		Assert.assertEquals("winterchen", u.getName());
//	}
//	@Test
//	public  void test3(){
//		User user = userService.getById(1008);
//		System.out.println(user);
//	}
}
