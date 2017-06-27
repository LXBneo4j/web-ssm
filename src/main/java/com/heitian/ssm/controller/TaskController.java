package com.heitian.ssm.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by lxb on 2017/5/6.
 */
@Controller
public class TaskController {
    private Logger log = Logger.getLogger(TaskController.class);

    @RequestMapping("/getTaskData")
    public  @ResponseBody
    Map<String, Object> getTaskData(HttpServletRequest request, Model model){
        String phone=request.getParameter("phone");



        return null;
    }

    @RequestMapping("/auditTaskData")
    public void auditTaskData(HttpServletRequest request, Model model){

    }
}
