package com.project.plan.controller.order;

import com.project.plan.common.JsonResult;
import com.project.plan.controller.BaseController;
import com.project.plan.entity.order.Order;
import com.project.plan.entity.plan.Project;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            builder.add("productName", SpecificationOperator.Operator.likeAll.name(), searchText);
        }
        Page<Order> page = orderService.findAll(builder.generateSpecification(),getPageRequest());
        return page;
    }

    @ApiOperation(value="跳到添加项目页面", notes="增加和修改是一个页面")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
    	
        return "order/add";
    }
   
    @ApiOperation(value="跳到修改订单页面", notes="增加和修改是一个页面")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer",paramType = "path")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        Order order = orderService.find(id);
        map.put("order", order);

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
    
    @ApiOperation(value="前台界面提交表单", notes="前台界面提交表单")
    @RequestMapping(value = "/addOrder", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> addOrder(Integer num,String suk,String userName,
    		String bdprovince,String bdcity,String bdarea,String address,String phone) {
    	boolean res = true;
    	StringBuffer msg = new StringBuffer();
    	
    	Integer price = num;
    	Integer payType=0;
    	String productName= suk;
    	Integer orderStatus=0;
    	
    	//String userName= userName;
//    	String bdprovince = (String) map.get("bdprovince");
//    	String bdcity = (String) map.get("bdcity");
//    	String bdarea = (String) map.get("bdarea");
//    	String address = (String) map.get("address");
//    	String phone = (String) map.get("phone");
    	
    	String realAddr = bdprovince +"-"+bdcity+"-"+bdarea+" "+address;
    	if(productName==null||"".equals(productName.trim())) {
    		res = false;
    		msg.append("请选择套餐");
    	}else if(price == null || price==0) {
    		res = false;
    		msg.append("价格不能为空");
    	}else if((bdprovince==null||"".equals(bdprovince.trim()))||(bdcity==null||"".equals(bdcity.trim()))||(bdarea==null||"".equals(bdarea.trim()))) {
    		res = false;
    		msg.append("请选择收获地区");
		}else if(address==null||"".equals(address.trim())) {
			res = false;
    		msg.append("请填入详细地址");
		}else if(phone==null||"".equals(phone.trim())) {
			res = false;
			msg.append("请填入手机号");
		}
        try {
        	if(res) {
        		Order order = new Order(productName,userName , phone, realAddr, price, orderStatus, payType, null, null, null);
        		orderService.saveOrUpdate(order);
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Object> result = new HashMap<>();
        result.put("res", res);
        result.put("msg", msg.toString());
        return result;
    }
    
    @ApiOperation(value="前台界面提交表单", notes="前台界面提交表单")
    @RequestMapping(value = "/addForm", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> addForm(String userName,String phone,String address1,String address2,
    		String productName) {
    	boolean res = true;
    	StringBuffer msg = new StringBuffer();
    	
    	Integer price = 1;
    	Integer payType=0;
    	Integer orderStatus=0;
    	
    	//String userName= userName;
//    	String bdprovince = (String) map.get("bdprovince");
//    	String bdcity = (String) map.get("bdcity");
//    	String bdarea = (String) map.get("bdarea");
//    	String address = (String) map.get("address");
//    	String phone = (String) map.get("phone");
    	
    	
    	String realAddr = address1 +"-"+address2;
    	if(productName==null||"".equals(productName.trim())) {
    		res = false;
    		msg.append("请选择套餐");
    	}else if(price == null || price==0) {
    		res = false;
    		msg.append("价格不能为空");
    	}else if(address1==null||"".equals(address1.trim())) {
    		res = false;
    		msg.append("请选择收获地区");
		}else if(address2==null||"".equals(address2.trim())) {
			res = false;
    		msg.append("请填入详细地址");
		}else if(phone==null||"".equals(phone.trim())) {
			res = false;
			msg.append("请填入手机号");
		}
        try {
        	if(res) {
        		Order order = new Order(productName,userName , phone, realAddr, price, orderStatus, payType, null, null, null);
        		orderService.saveOrUpdate(order);
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Object> result = new HashMap<>();
        result.put("res", res);
        result.put("msg", msg.toString());
        return result;
    }
}
