package com.project.plan.controller.web;

import java.util.List;

import com.project.plan.controller.BaseController;
import com.project.plan.service.IUserService;
import com.project.plan.entity.User;

import com.project.plan.service.plan.PlanServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {


	private Logger logger = LoggerFactory.getLogger(getClass());


	/**前index页面*/
	@RequestMapping(value={"/","/index"})
	public String index(){

		return redirect("/plan/module/index");
		//return "index";
	}
}
