package com.project.plan.controller.web;

import java.lang.invoke.MethodType;
import java.util.List;

import com.project.plan.controller.BaseController;
import com.project.plan.controller.plan.ModuleController;
import com.project.plan.entity.plan.Module;
import com.project.plan.entity.plan.Tache;
import com.project.plan.service.IUserService;
import com.project.plan.entity.User;

import com.project.plan.service.plan.ModuleServiceImpl;
import com.project.plan.service.plan.PlanServiceImpl;
import com.project.plan.service.plan.ProjectServiceImpl;
import com.project.plan.service.plan.TacheServiceImpl;
import com.project.plan.service.specification.SimpleSpecificationBuilder;
import com.project.plan.service.specification.SpecificationOperator;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Log4j
@Controller
public class IndexController extends BaseController {


	@Autowired
	private ModuleServiceImpl moduleService;
	@Autowired
	private ProjectServiceImpl projectService;
	@Autowired
	private TacheServiceImpl tacheService;

	/**前index页面*/
	@RequestMapping(value={"/","/index"})
	public String index(ModelMap map){
//		return redirect("/plan/module/index");

		SimpleSpecificationBuilder<Module> builder = new SimpleSpecificationBuilder<Module>();
		builder.add("status", SpecificationOperator.Operator.notEqual.name(), Module.STAT_DEFAULT);//新创建和已上线都不到首页显示
		builder.add("status", SpecificationOperator.Operator.notEqual.name(), Module.STAT_SUCCESS);


		Sort sort = new Sort(Sort.Direction.DESC, "id");
		PageRequest pageRequest = new PageRequest(0, 20, sort);//只显示最新20个功能

		Page<Module> page = moduleService.findAllWithProject(builder.generateSpecification(),pageRequest);
		for(Module m: page.getContent()){//查询一个模块下面的描述,具体哪些人哪些功能已经上线，哪些功能没有还做,放到createComments 里面,
			List<Tache> taches = tacheService.findAllByModuleIdWithUser(m.getId());

			String createComments = ModuleController.findModuleComments(taches,Tache.STAT_NEW);       //未归档了环节描述
			String updateComments = ModuleController.findModuleComments(taches,Tache.STAT_SUCCESS);   //已经归档了环节描述

			m.setCreateCommentStr(createComments);
			m.setUpdateCommentStr(updateComments);
		}

		map.put("page",page);
		return "index";
	}


	/**前index页面*/
	@RequestMapping(value={"/errorhaha"})
	public String errorhaha(){
		if(true){
			throw new NumberFormatException("hahaha");
		}
		return "sfafsf";
	}
	/**前index页面*/
	@RequestMapping(value={"/errorhaha2"},method = RequestMethod.POST)
	public String errorhaha2(){
		if(true){
			throw new NumberFormatException("hahaha");
		}
		return "sfafsf";
	}
	/**前index页面*/
	@RequestMapping(value={"/errorhaha4"},method = RequestMethod.GET)
	@ResponseBody
	public String errorhaha4(){
		if(true){
			throw new NumberFormatException("hahaha222rest");
		}
		return "sfafsf";
	}
	/**前index页面*/
	@RequestMapping(value={"/errorhaha5"},method = RequestMethod.GET)
	@ResponseBody
	public String errorhaha5(){
		boolean flag = true;
		System.out.println(flag);
		if(flag){
			throw new NumberFormatException("hahaha222rest");
		}
		return "sfafsf";
	}

	/**前index页面*/
	@RequiresPermissions("plan:module:edit")
	@RequestMapping(value={"/errorhaha3"},method = RequestMethod.GET)
	public String errorhaha3(){
		if(true){
			throw new NumberFormatException("hahaha");
		}
		return "sfafsf";
	}
}
