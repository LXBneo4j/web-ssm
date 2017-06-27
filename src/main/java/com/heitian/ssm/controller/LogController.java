package com.heitian.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.heitian.ssm.model.TbDepartmentDO;
import com.heitian.ssm.model.TbLogDO;
import com.heitian.ssm.model.TbNoticeDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.service.LogService;
import com.heitian.ssm.utils.enums.ResultEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxb on 2017/5/6.
 */
@Controller
public class LogController {
    private Logger log = Logger.getLogger(LogController.class);
    @Autowired
    private LogService logService;
    @RequestMapping("/getLogData")

    public @ResponseBody
    Map<String, Object> getLogData(HttpServletRequest request, Model model, TbLogDO logDO){
        String pageNo=request.getParameter("page");
        String pageSize=request.getParameter("rows");
        String phone=request.getParameter("phone");
        HttpSession session=request.getSession();
        Long id=(Long) session.getAttribute("id");
        logDO.setCreator(id);
        BaseInfo<List<TbLogDO>> info=logService.queryLog(phone,logDO,Integer.valueOf(pageNo),Integer.valueOf(pageSize));
        for(TbLogDO log:info.getT()){
            if(log.getState()==0){
                log.setRemark("未通过");
            }
            if(log.getState()==0){
                log.setRemark("通过");
            }
        }
        Long total=logService.count();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", info.getT());
        map.put("total", total);
        return map;
    }

    @RequestMapping("/updateLog")
    @ResponseBody
    public String updateLog(HttpServletRequest request, TbLogDO logDO){
        BaseInfo info=logService.updateLog(logDO);
        String json=null;
        if(info!=null && info.getCode()== ResultEnum.SUCCESS.getCode()){
            JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
            json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
            System.out.println(json);
        }
        return json;
    }

    @RequestMapping("/addLog")
    @ResponseBody
    public String addLog(HttpServletRequest request, TbLogDO logDO){
        String phone=request.getParameter("phone");
        BaseInfo info=logService.addLog(phone,logDO);
        request.setAttribute("phone",phone);
        String json=null;
        if(info!=null && info.getCode()== ResultEnum.SUCCESS.getCode()){
            JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
            json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
            System.out.println(json);
        }
        return json;
    }
}
