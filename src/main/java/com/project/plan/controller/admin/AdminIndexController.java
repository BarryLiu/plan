package com.project.plan.controller.admin;

import com.project.plan.controller.BaseController;
import com.project.plan.entity.plan.Module;
import com.project.plan.service.plan.ModuleServiceImpl;
import com.project.plan.service.plan.TacheServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class AdminIndexController extends BaseController {
	@Autowired
	private TacheServiceImpl tacheService;
	@Autowired
	private ModuleServiceImpl moduleService;


	/**后台index页面*/
	@RequestMapping(value ={"/admin/","/admin/index"})
	public String index(){
		
		return "admin/index";
	}
	
	@RequestMapping(value = {"/admin/welcome"})
	public String welcome(ModelMap map,Integer moduleId){
		return  redirect("/plan/module/index");
	}


}
