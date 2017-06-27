package com.heitian.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.heitian.ssm.model.TbDepartmentDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.service.DepartmentService;
import com.heitian.ssm.utils.enums.ResultEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxb on 2017/4/30.
 * 部门相关操作控制层
 */
@Controller
public class DepartmentController {

    private Logger log=Logger.getLogger(ActionController.class);
    @Autowired
    private DepartmentService departmentService;
    @RequestMapping("/addDepartment")
    @ResponseBody
    public String addDepartment(HttpServletRequest request, TbDepartmentDO departmentDO1){
        BaseInfo info=departmentService.addDepartment(departmentDO1);
        String json=null;
        if(info!=null && info.getCode()== ResultEnum.SUCCESS.getCode()){
            JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
            json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
            System.out.println(json);
        }
        return json;
    }


    @RequestMapping("/deleteDepartment")
    @ResponseBody
    public String deleteDepartment(HttpServletRequest request, TbDepartmentDO departmentDO){
        BaseInfo info=departmentService.deleteDepartment(departmentDO);
        String json=null;
        if(info!=null && info.getCode()== ResultEnum.SUCCESS.getCode()){
            JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
            json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
            System.out.println(json);
        }
        return json;
    }

    @RequestMapping("/updateDepartment")
    @ResponseBody
    public String updateDepartment(HttpServletRequest request, TbDepartmentDO departmentDO){
        BaseInfo info=departmentService.updateDepartment(departmentDO);
        String json=null;
        if(info!=null && info.getCode()== ResultEnum.SUCCESS.getCode()){
            JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
            json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
            System.out.println(json);
        }
        return json;
    }

    @RequestMapping("/getDepartmentData")
//    @ResponseBody
    public  @ResponseBody
    Map<String, Object> getDepartmentData(HttpServletRequest request, TbDepartmentDO departmentDO){
        String pageNo=request.getParameter("page");
        String pageSize=request.getParameter("rows");
        BaseInfo<List<TbDepartmentDO>> info=departmentService.queryDepartment(departmentDO,Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        Long total=departmentService.count();
        request.setAttribute("phone",request.getParameter("phone"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", info.getT());
        map.put("total", total);
        return map;
    }

}
