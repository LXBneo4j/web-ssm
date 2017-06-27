package com.heitian.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.heitian.ssm.model.TbDepartmentDO;
import com.heitian.ssm.model.TbRoleDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.service.RoleService;
import com.heitian.ssm.utils.enums.ResultEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxb on 2017/5/6.
 */
@Controller
public class RoleController {
    private Logger log = Logger.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping("/addRole")
    @ResponseBody
    public String addRole(HttpServletRequest request, TbRoleDO roleDO1){
        BaseInfo info=roleService.addRole(roleDO1);
        String json=null;
        if(info!=null && info.getCode()== ResultEnum.SUCCESS.getCode()){
            JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
            json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
            System.out.println(json);
        }
        return json;
    }


    @RequestMapping("/deleteRole")
    @ResponseBody
    public String deleteRole(HttpServletRequest request, TbRoleDO roleDO){
        BaseInfo info=roleService.deleteRole(roleDO);
        String json=null;
        if(info!=null && info.getCode()== ResultEnum.SUCCESS.getCode()){
            JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
            json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
            System.out.println(json);
        }
        return json;
    }

    @RequestMapping("/updateRole")
    @ResponseBody
    public String updateRole(HttpServletRequest request, TbRoleDO roleDO){
        BaseInfo info=roleService.updateRole(roleDO);
        String json=null;
        if(info!=null && info.getCode()== ResultEnum.SUCCESS.getCode()){
            JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
            json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
            System.out.println(json);
        }
        return json;
    }

    @RequestMapping("/getRoleData")
    public @ResponseBody Map<String, Object> getRoleData(HttpServletRequest request, TbRoleDO roleDO){
        String pageNo=request.getParameter("page");
        String pageSize=request.getParameter("rows");
        BaseInfo<List<TbRoleDO>> info=roleService.queryRole(roleDO,Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        Long total=roleService.count();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", info.getT());
        map.put("total", total);
        return map;
    }
}
