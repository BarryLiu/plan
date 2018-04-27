package com.project.plan.controller.plan;

import com.project.plan.common.JsonResult;
import com.project.plan.controller.BaseController;
import com.project.plan.entity.plan.Module;
import com.project.plan.entity.plan.Project;
import com.project.plan.service.plan.ModuleServiceImpl;
import com.project.plan.service.plan.PlanServiceImpl;
import com.project.plan.service.plan.ProjectServiceImpl;
import com.project.plan.service.specification.SimpleSpecificationBuilder;
import com.project.plan.service.specification.SpecificationOperator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Barry on 2018/4/20.
 */
@Controller
@RequestMapping("/plan/module")
public class ModuleController extends BaseController {
    @Autowired
    private PlanServiceImpl planService;
    @Autowired
    private ModuleServiceImpl moduleService;
    @Autowired
    private ProjectServiceImpl projectService;

    @RequestMapping("/index")
    public String index() {
        return "plan/module/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Page<Module> list() {
        SimpleSpecificationBuilder<Module> builder = new SimpleSpecificationBuilder<Module>();
        String searchText = request.getParameter("searchText");
        if(StringUtils.isNotBlank(searchText)){
            builder.add("name", SpecificationOperator.Operator.likeAll.name(), searchText);
        }
//        builder.generateSpecification()
//      builder

        Page<Module> page = moduleService.findAllWithProject(builder.generateSpecification(),getPageRequest());
        return page;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        List<Project> projectList = projectService.findAll();
        map.put("projectList",projectList);
        return "plan/module/form";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        Module module = moduleService.find(id);
        List<Project> projectList = projectService.findAll();
        map.put("module", module);
        map.put("projectList",projectList);
        List<Module> list = moduleService.findAll();
        map.put("list", list);
        return "plan/module/form";
    }

//    @RequiresPermissions("plan:module:edit")
    @RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(Module module, ModelMap map){
        try {
            moduleService.saveOrUpdate(module);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

//    @RequiresPermissions("plan:module:delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id,ModelMap map) {
        try {
            moduleService.deleteModule(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

}
