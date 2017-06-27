package com.heitian.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.heitian.ssm.model.TbActionDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.service.ActionService;
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
public class ActionController {
    private Logger log=Logger.getLogger(ActionController.class);
    @Autowired
    private ActionService actionService;

    @RequestMapping("/addAction")
    @ResponseBody
    public String addAction(HttpServletRequest request, TbActionDO actionDO1){
        BaseInfo info=actionService.addAction(actionDO1);
        String json=null;
        if(info!=null && info.getCode()== ResultEnum.SUCCESS.getCode()){
            JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
            json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
            System.out.println(json);
        }
        return json;
    }


    @RequestMapping("/deleteAction")
    @ResponseBody
    public String deleteAction(HttpServletRequest request, TbActionDO actionDO){
        BaseInfo info=actionService.deleteAction(actionDO);
        String json=null;
        if(info!=null && info.getCode()== ResultEnum.SUCCESS.getCode()){
            JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
            json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
            System.out.println(json);
        }
        return json;
    }

    @RequestMapping("/updateAction")
    @ResponseBody
    public String updateAction(HttpServletRequest request, TbActionDO actionDO){
        BaseInfo info=actionService.updateAction(actionDO);
        String json=null;
        if(info!=null && info.getCode()== ResultEnum.SUCCESS.getCode()){
            JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
            json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
            System.out.println(json);
        }
        return json;
    }

    @RequestMapping("/getActionData")
//    @ResponseBody
    public @ResponseBody
    Map<String, Object> getActionData(HttpServletRequest request, TbActionDO actionDO){
        String pageNo=request.getParameter("page");
        String pageSize=request.getParameter("rows");
        List<TbActionDO> list=actionService.queryAction(actionDO,Integer.valueOf(pageNo),Integer.valueOf(pageSize));

        Long total=actionService.count();
        request.setAttribute("phone",request.getParameter("phone"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", total);
        return map;
    }













}
