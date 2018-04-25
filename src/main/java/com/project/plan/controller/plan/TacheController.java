package com.project.plan.controller.plan;

import com.project.plan.common.Constats;
import com.project.plan.common.JsonResult;
import com.project.plan.common.utils.DateUtil;
import com.project.plan.common.utils.HttpUtil;
import com.project.plan.common.utils.TextUtil;
import com.project.plan.controller.BaseController;
import com.project.plan.entity.User;
import com.project.plan.entity.plan.Module;
import com.project.plan.entity.plan.Openate;
import com.project.plan.entity.plan.Tache;
import com.project.plan.service.impl.UserServiceImpl;
import com.project.plan.service.plan.ModuleServiceImpl;
import com.project.plan.service.plan.OpenateServiceImpl;
import com.project.plan.service.plan.PlanServiceImpl;
import com.project.plan.service.plan.TacheServiceImpl;
import com.project.plan.service.specification.SimpleSpecificationBuilder;
import com.project.plan.service.specification.SpecificationOperator;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Barry on 2018/4/20.
 */
@Controller
@RequestMapping("/plan/tache")
public class TacheController extends BaseController {
    @Autowired
    private PlanServiceImpl planService;
    @Autowired
    private TacheServiceImpl tacheService;
    @Autowired
    private OpenateServiceImpl openateService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ModuleServiceImpl moduleService;




    @RequestMapping("/index")
    public String index() {
        return "plan/tache/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Page<Tache> list() {
        SimpleSpecificationBuilder<Tache> builder = new SimpleSpecificationBuilder<Tache>();
        String searchText = request.getParameter("searchText");
        if(StringUtils.isNotBlank(searchText)){
            builder.add("name", SpecificationOperator.Operator.likeAll.name(), searchText);
        }
        Page<Tache> page = tacheService.findAll(builder.generateSpecification(),getPageRequest());

        return page;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        List<User> userList = userService.findAll();
        map.put("userList", userList);
        return "plan/tache/form";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        Tache tache = tacheService.find(id);
        map.put("tache", tache);
        List<User> userList = userService.getBaseDao().findAll();
        map.put("userList", userList);

        return "plan/tache/form";
    }

    @RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(Tache tache, ModelMap map,Integer moduleId){
        try {
            tacheService.editTache(tache,request);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id,ModelMap map) {
        try {
            tacheService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    /** 某环节环节备注记录列表 */
    @RequestMapping("/recordlist")
    public String recordlist(ModelMap map ,Integer tacheId,@RequestParam(value="type",defaultValue="0")Integer type) {
        Tache tache = tacheService.getBaseDao().findOne(tacheId);
        List<Openate> openateList = openateService.findByTacheId(tacheId);

        map.put("tache",tache);
        map.put("openateList",openateList);
        map.put("type",type);//客户端请求过来的路径类型, 0默认是点击记录按钮进来的(给他显示添加记录),1是来查看记录的,不给他添加记录
        return "plan/tache/openateRecord";
    }
    /** 添加环节备注记录 */
    @RequestMapping(value= {"/openateEdit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult openateEdit(Openate openate, ModelMap map,Integer moduleId){
        try {
            Date sysDate = new Date();
            User loginUser = (User) SecurityUtils.getSubject().getSession()
                    .getAttribute(Constats.CURRENTUSER);
            openate.setUserId(loginUser.getId());
            openate.setCreateTime(sysDate);
            openate.setDuration(0L);
            openate.setIp(HttpUtil.getClientIP(request));
            openate.setUrl(request.getRequestURL().toString());
            openate.setStatus(Openate.STATUS_WARN);

            openateService.save(openate);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }


}
