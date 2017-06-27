package com.heitian.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.heitian.ssm.model.TbDepartmentDO;
import com.heitian.ssm.model.TbNoticeDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.model.info.UserDataResultInfo;
import com.heitian.ssm.service.EmployeeService;
import com.heitian.ssm.service.NoticeService;
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
 * 公告
 */
@Controller
public class NoticeController {
    private Logger log = Logger.getLogger(NoticeController.class);
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/getNoticeData")
//    @ResponseBody
    public @ResponseBody
    Map<String, Object> geNoticeData(HttpServletRequest request, Model model, TbNoticeDO noticeDO){
        String pageNo=request.getParameter("page");
        String pageSize=request.getParameter("rows");
        HttpSession session=request.getSession();
        //Long id=(Long)session.getAttribute("id");
        String phone=request.getParameter("phone");
        BaseInfo<List<TbNoticeDO>> info=noticeService.queryNotice(phone,noticeDO,Integer.valueOf(pageNo),Integer.valueOf(pageSize));

        for(TbNoticeDO notice:info.getT()){
            UserDataResultInfo info1=employeeService.getUserData(notice.getCreator());
            notice.setRemark(info1.getName());
        }
        request.setAttribute("phone",phone);
        Long total=noticeService.count();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", info.getT());
        map.put("total", total);
        return map;
    }


    @RequestMapping("/addNotice")
    @ResponseBody
    public String addNotice(HttpServletRequest request, TbNoticeDO noticeDO){
        HttpSession session=request.getSession();
        Long id=(Long)session.getAttribute("id");
        noticeDO.setCreator(id);
        noticeDO.setUpdater(id);
        log.info("公告："+noticeDO);
        BaseInfo info=noticeService.addNotice(noticeDO);
        String json=null;
        if(info!=null && info.getCode()== ResultEnum.SUCCESS.getCode()){
            JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
            json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
            System.out.println(json);
        }
        return json;
    }
}
