package com.project.plan;

import com.project.plan.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanApplicationTests {

    private UserServiceImpl userService;

	@Test
	public void contextLoads() {
		System.out.println("xxxxxxxxxxxxxx load test ........");
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
