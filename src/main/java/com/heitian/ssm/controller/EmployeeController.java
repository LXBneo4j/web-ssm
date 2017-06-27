package com.heitian.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.heitian.ssm.model.Sms;
import com.heitian.ssm.model.TbActionDO;
import com.heitian.ssm.model.TbEmployeeDO;
import com.heitian.ssm.model.TbLoginDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.model.info.LoginResultInfo;
import com.heitian.ssm.model.info.UserDataResultInfo;
import com.heitian.ssm.service.EmployeeService;
import com.heitian.ssm.service.LoginService;
import com.heitian.ssm.utils.enums.ResultEnum;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lxb on 2016/7/15.
 * 用户相关操作
 */

@Controller
public class EmployeeController {

    Map<String,TbEmployeeDO> userMap=new HashMap<>();
    Map<String,List<TbActionDO>> actionMap=new HashMap<>();
    private Logger log = Logger.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private LoginService loginService;

    /**
     * 默认返回资源
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        System.out.println("路径："+request.getRequestURI());
        //request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        //转发==>response.sendRedirect("http://www.baidu.com");
        return "login";
    }

    /**
     * 登录操作
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String userLogin(HttpServletRequest request, Model model) {
        //获取参数值
        String phone=request.getParameter("username");
        String password=request.getParameter("password");
        if(phone==null || password==null){
            return "login";
        }

        //访问底层
        LoginResultInfo info=null;
        try{
            info=employeeService.userLogin(phone,password);

        }catch (Exception e){
            log.info(e.getMessage());
            return "login";
        }
        if(info!=null && info.getCode()== ResultEnum.LOGIN_SUCCESS.getCode()){
            HttpSession session=request.getSession();
            userMap.put(info.getUser().getPhone(),info.getUser());
            actionMap.put(info.getUser().getPhone(),info.getLists());
            session.setAttribute("user",userMap);
            session.setAttribute("action",actionMap);
            request.setAttribute("user",info.getUser());
            request.setAttribute("phone",info.getUser().getPhone());
            request.setAttribute("lists",info.getLists());
            //登录后到达主界面
            return "main";
        }
       return "login";
    }

    /**
     * 通过id查询用户信息
     * 用户基础信息+所有角色+所有功能信息
     *  ajax 请求
     * @param request
     *
     */
    @RequestMapping("/getUserData")
    //@ResponseBody
    public @ResponseBody
    Map<String, Object> getUserData(HttpServletRequest request, HttpServletResponse response){
        String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        HttpSession session=request.getSession();
        String phone=request.getParameter("phone");
        System.out.println("电话"+phone);
        Map<String,TbEmployeeDO> map1=(Map<String,TbEmployeeDO>)session.getAttribute("user");
        UserDataResultInfo info=null;
        Long id=map1.get(phone).getId();
        info=employeeService.getUserData(id);
        List<UserDataResultInfo> list=new ArrayList<>();
        list.add(info);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", 1);
        //System.out.println(list);
        return map;
    }

    @RequestMapping("/updateSelf")
    @ResponseBody
    public String updateSelf(HttpServletRequest request,TbEmployeeDO employeeDO){
        BaseInfo info=null;
        try{
            info=employeeService.updateSelf(employeeDO);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        String json=null;
        if(info!=null && info.getCode()==ResultEnum.SUCCESS.getCode()){
            JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
            json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
           // System.out.println(json);
        }
        return json;
    }

    @RequestMapping("/updateSelfPassword")
    @ResponseBody
    public String updateSelfPassword(HttpServletRequest request){
        BaseInfo info=new BaseInfo();
        String pass1=request.getParameter("pass1");
        String pass2=request.getParameter("pass2");
        String scode=request.getParameter("scode");

        String phone=request.getParameter("phone");
        //System.out.println(pass1+"-----"+pass2);
        if(!pass1.equals(pass2)){
            return fail(info);
        }

        if(!num(scode)){
            return fail(info);
        }

        Boolean flag=false;
        HttpSession session=request.getSession();
        Map<String,Sms> map=(Map<String,Sms>)session.getAttribute("sms");
        for(String str:map.keySet()){
            if(phone.equals(str) && map.get(str).getNumber()==Integer.valueOf(scode)){
                flag=true;
                break;
            }
        }
        if(!flag){
            return fail(info);
        }
        TbLoginDO loginDO=new TbLoginDO();
        loginDO.setPassword(pass1);
        loginDO.setPhone(phone);
        info=loginService.updatePassword(loginDO);
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
        String json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(json);
        return json;
    }

    public Boolean num(String str){
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher((CharSequence)str);
        Boolean result=matcher.matches();
        return result;
    }

    public String fail(BaseInfo info){
        info.setFail();
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
        String json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(json);
        return json;
    }

    @RequestMapping("/getAllUserData")
    public @ResponseBody Map<String, Object> getAllUserData(HttpServletRequest request){
        Integer pageNo=Integer.valueOf(request.getParameter("page"));
        Integer pageSize=Integer.valueOf(request.getParameter("rows"));
        List<TbEmployeeDO> list=employeeService.getAllUserData((pageNo-1)*pageSize,pageSize);
        Long total=employeeService.count();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", total);
        return map;

    }


    @RequestMapping("/updateEmp")
    @ResponseBody
    public String updateEmp(HttpServletRequest request,TbEmployeeDO employeeDO){
        BaseInfo info=employeeService.updateUser(employeeDO);
        log.info("shuju"+employeeDO.toString());

        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
        String json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(json);
        return json;
    }

    @RequestMapping("/addEmp")
    @ResponseBody
    public String addEmp(HttpServletRequest request,TbEmployeeDO employeeDO1){
        BaseInfo info=employeeService.addEmp(employeeDO1);
        log.info("shuju"+employeeDO1.toString());
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
        String json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(json);
        return json;
    }

    @RequestMapping("/deleteEmp")
    @ResponseBody
    public String deleteEmp(HttpServletRequest request,TbEmployeeDO employeeDO){
        BaseInfo info=employeeService.deleteEmp(employeeDO);
        log.info("shuju"+employeeDO.toString());


        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
        String json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
        //System.out.println(json);
        return json;
    }


    public static void main(String[] args){
        String a="kkkklooooooo";
        System.out.println(a.substring(0,2));
    }
}
