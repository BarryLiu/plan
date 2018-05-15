package com.project.plan.controller.plan;

import com.project.plan.common.JsonResult;
import com.project.plan.common.utils.TextUtil;
import com.project.plan.controller.BaseController;
import com.project.plan.entity.plan.*;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Barry on 2018/5/15.
 */
@Controller
@RequestMapping("/plan/projectTache")
public class ProjectTacheController extends BaseController {
    @Autowired
    private ProjectTacheServiceImpl projectTacheService;
    @Autowired
    private StatusServiceImpl statusService;

    @ApiIgnore
    @RequestMapping(value = { "","/", "/index" })
    public String index(ModelMap map) {
        return "plan/projectTache/index";
    }

    @ApiOperation(value="分页获取项目环节列表", notes="可以更具传入的searchText根据项目环节名模糊搜索")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Page<ProjectTache> list() {
        SimpleSpecificationBuilder<ProjectTache> builder = new SimpleSpecificationBuilder<ProjectTache>();
        String searchText = request.getParameter("searchText");
        if(StringUtils.isNotBlank(searchText)){
            builder.add("name", SpecificationOperator.Operator.likeAll.name(), searchText);
        }

        Page<ProjectTache> page = projectTacheService.findAll(builder.generateSpecification(),getPageRequest());
//        for(ProjectTache m: page.getContent()){//查询一个功能环节下面的状态,没做好，不到前端展示
//            String haveStatus = m.getHaveStatus();
//            if(TextUtil.isNullOrEmpty(haveStatus)){
//                continue;
//            }
//            String[] haveStatusList =m.getHaveStatus()==null?new String[0]:m.getHaveStatus().split(",");
//            List<Integer> haveStatusListIds = new ArrayList<>();
//            for (int i=0;i<haveStatusList.length;i++) {
//                String statusId = haveStatusList[i];
//                if(TextUtil.isNullOrEmpty(statusId)){
//                    continue;
//                }
//                haveStatusListIds.add(Integer.valueOf(statusId));
//            }
//            List<Status> status = statusService.findList(haveStatusListIds);
//            StringBuffer sb = new StringBuffer();
//            for (Status s:status) {
//                sb.append(s.getName()+",");
//            }
//
//        }
        return page;
    }

    @ApiOperation(value="跳到添加项目环节页面", notes="项目环节增加和修改需要拿到项目列表")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @RequiresPermissions("plan:projectTache:add")
    public String add(ModelMap map) {
        Sort sort = new Sort(Sort.Direction.ASC, "sortIndex");
        List<ProjectTache> projectTacheList = projectTacheService.findList(sort);

        SimpleSpecificationBuilder<Status> builder = new SimpleSpecificationBuilder<Status>();
        builder.add("status",SpecificationOperator.Operator.eq.name(),Status.STAT_VIEW);
        List<Status> statusList = statusService.findList(builder.generateSpecification());

        map.put("projectTacheList",projectTacheList);
        map.put("statusList",statusList);
        map.put("haveStatusList",new Integer[0]);//所拥有的状态,避免空指针
        return "plan/projectTache/form";
    }

    @ApiOperation(value="跳到修改项目环节页面", notes="项目环节增加和修改需要拿到项目列表")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer",paramType = "path")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        ProjectTache projectTache = projectTacheService.find(id);

        Sort sort = new Sort(Sort.Direction.ASC, "sortIndex");
        List<ProjectTache> projectTacheList = projectTacheService.findList(sort);

        SimpleSpecificationBuilder<Status> builder = new SimpleSpecificationBuilder<Status>();
        builder.add("status",SpecificationOperator.Operator.eq.name(),Status.STAT_VIEW);
        List<Status> statusList = statusService.findList(builder.generateSpecification());

        String[] haveStatusList =projectTache.getHaveStatus()==null?new String[0]:projectTache.getHaveStatus().split(",");
        List<Integer> haveStatusListIds = new ArrayList<>();
        for (int i=0;i<haveStatusList.length;i++) {
            String statusId = haveStatusList[i];
            if(TextUtil.isNullOrEmpty(statusId)){
                continue;
            }
            haveStatusListIds.add(Integer.valueOf(statusId));
        }


        map.put("projectTacheList",projectTacheList);
        map.put("statusList",statusList);
        map.put("haveStatusList",haveStatusListIds);//

        map.put("projectTache", projectTache);
        return "plan/projectTache/form";
    }

    //    @RequiresPermissions("plan:projectTache:edit")
    @ApiOperation(value="修改或添加项目环节", notes="有id就是修改,没有id添加")
    @RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(ProjectTache projectTache, ModelMap map){
        try {
            if(projectTache.getSortIndex()==null||projectTache.getSortIndex()==0){//如果不知道是第几个就默认是第一个
                projectTache.setSortIndex(1);
            }
            //修改能不能
            projectTacheService.saveOrUpdate(projectTache);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    //    @RequiresPermissions("plan:projectTache:delete")
    @ApiOperation(value="删除项目环节", notes="根据id删除项目环节,同时删除其下面的环节")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer",paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id,ModelMap map) {
        try {
            //判断业务能不能删除
            projectTacheService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
}
