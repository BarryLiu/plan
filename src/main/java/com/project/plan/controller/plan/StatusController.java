package com.project.plan.controller.plan;

import com.project.plan.common.JsonResult;
import com.project.plan.controller.BaseController;
import com.project.plan.entity.plan.Status;
import com.project.plan.service.plan.*;
import com.project.plan.service.specification.SimpleSpecificationBuilder;
import com.project.plan.service.specification.SpecificationOperator;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Created by Barry on 2018/5/15.
 */
@Controller
@RequestMapping("/plan/status")
public class StatusController extends BaseController {
    @Autowired
    private StatusServiceImpl statusService;
    @Autowired
    private ProjectServiceImpl projectService;
    @Autowired
    private TacheServiceImpl tacheService;

    @ApiIgnore
    @RequestMapping(value = { "","/", "/index" })
    public String index(ModelMap map) {
        return "plan/status/index";
    }

    @ApiOperation(value="分页获取项目状态列表", notes="可以更具传入的searchText根据项目状态名模糊搜索")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Page<Status> list() {
        SimpleSpecificationBuilder<Status> builder = new SimpleSpecificationBuilder<Status>();
        String searchText = request.getParameter("searchText");
        if(StringUtils.isNotBlank(searchText)){
            builder.add("name", SpecificationOperator.Operator.likeAll.name(), searchText);
        }

        Page<Status> page = statusService.findAll(builder.generateSpecification(),getPageRequest());
        return page;
    }

    @ApiOperation(value="跳到添加项目状态页面", notes="项目状态增加和修改需要拿到项目列表")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @RequiresPermissions("plan:status:add")
    public String add(ModelMap map) {
        return "plan/status/form";
    }

    @ApiOperation(value="跳到修改项目状态页面", notes="项目状态增加和修改需要拿到项目列表")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer",paramType = "path")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        Status status = statusService.find(id);

        map.put("status", status);
        return "plan/status/form";
    }

    //    @RequiresPermissions("plan:status:edit")
    @ApiOperation(value="修改或添加项目状态", notes="有id就是修改,没有id添加")
    @RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(Status status, ModelMap map){
        try {
            //修改能不能
            statusService.saveOrUpdate(status);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    //    @RequiresPermissions("plan:status:delete")
    @ApiOperation(value="删除项目状态", notes="根据id删除项目状态,同时删除其下面的环节")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer",paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id,ModelMap map) {
        try {
            //判断业务能不能删除
            statusService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
}
