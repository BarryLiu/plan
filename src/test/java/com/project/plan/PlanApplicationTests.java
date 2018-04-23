package com.project.plan;

import com.project.plan.common.utils.LDAPControl;
import com.project.plan.common.utils.TUser;
import com.project.plan.entity.User;
import com.project.plan.service.impl.UserServiceImpl;
import org.assertj.core.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanApplicationTests {

	@Autowired
    private UserServiceImpl userService;

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
			User sysUser = new User();
			sysUser.setUserName(ldUser.getName());
			sysUser.setNickName(ldUser.getDisplayName());
			sysUser.setPassword("3931MUEQD1939MQMLM4AISPVNE");//设置密码是 "111111"
			sysUser.setSex(1);//不分男女
//			sysUser.setBirthday();ldUser.getEnterDate()
			sysUser.setEmail(ldUser.getEmail());
//			sysUser.setAddress(ldUser.get);
//			sysUser.setTelephone();
			sysUser.setDeleteStatus(ldUser.getStat());
			sysUser.setLocked(0);//未锁定
			sysUser.setDescription(ldUser.getDepart());
			sysUser.setCreateTime(new Date());
			userList.add(sysUser);
		}
		System.out.print("----插入用户-----"+userService);
		userService.save(userList);

	}

//	@Test
//	public void test(){
//
//		userMapper.insert("barry2", "123456", "12345678910");
//		User u = userMapper.findUserByPhone("12345678910");
//		Assert.assertEquals("barry2", u.getName());
//	}
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
