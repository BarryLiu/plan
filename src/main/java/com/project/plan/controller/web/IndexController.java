package com.project.plan.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.plan.controller.BaseController;

@Controller
public class IndexController extends BaseController {


    /**
     * 前index页面
     */
    @RequestMapping(value = {"/", "/index"})
    public String index(ModelMap map) {
	
        return "index";
    }

    @RequestMapping(value = {"/chenggong"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String chenggong() {
        return "chenggong";
    }
    @RequestMapping(value = {"/index2"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index2() {
        return "index2";
    }
}
