package com.project.plan.controller.order;

import com.project.plan.common.JsonResult;
import com.project.plan.controller.BaseController;
import com.project.plan.entity.order.Order;
import com.project.plan.service.order.OrderServiceImpl;
import com.project.plan.service.specification.SimpleSpecificationBuilder;
import com.project.plan.service.specification.SpecificationOperator;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController  extends BaseController {

    @Autowired
    private OrderServiceImpl orderService;

    @ApiIgnore
    @RequestMapping(value = { "","/", "/index" })
    public String index() {
        return "order/index";
    }

    @ApiOperation(value="获取订单列表", notes="获取列表订单")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Page<Order> list() {
        SimpleSpecificationBuilder<Order> builder = new SimpleSpecificationBuilder<Order>();
        String searchText = request.getParameter("searchText");
        if(StringUtils.isNotBlank(searchText)){
            builder.add("name", SpecificationOperator.Operator.likeAll.name(), searchText);
        }
        Page<Order> page = orderService.findAll(builder.generateSpecification(),getPageRequest());
        return page;
    }


    @ApiOperation(value="跳到修改订单页面", notes="增加和修改是一个页面")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer",paramType = "path")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        Order project = orderService.find(id);
        map.put("project", project);

        return "order/form";
    }

    @ApiOperation(value="修改或添加项目", notes="user的属性,有id就是修改,没有id添加")
    @RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(Order order, ModelMap map){
        try {
            orderService.saveOrUpdate(order);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @ApiOperation(value="删除订单", notes="根据id删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id,ModelMap map) {
        try {
            orderService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
}
