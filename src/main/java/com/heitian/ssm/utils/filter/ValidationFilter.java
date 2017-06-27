package com.heitian.ssm.utils.filter;

import com.heitian.ssm.model.TbActionDO;
import com.heitian.ssm.model.TbEmployeeDO;
import com.heitian.ssm.model.TbEmployeeRoleDO;
import com.heitian.ssm.service.ActionService;
import com.heitian.ssm.service.impl.ActionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by lxb on 2017/4/30.
 * 验证是否登录的过滤器，每访问一个接口都要验证是否登录，
 * 不需要验证的过滤器在web.xml中配置
 */
public class ValidationFilter implements Filter {

//    @Autowired
//    private ActionService actionService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 获取uri地址
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String uri = request.getRequestURI();
        String ctx=request.getContextPath();
        uri = uri.substring(ctx.length());
        System.out.println("==访问资源"+uri);
        //对于注册和登录页面，无需登录
        if(uri.equals("/")  || uri.equals("/login") || uri.equals("/register")) {
            filterChain.doFilter(request,servletResponse);
            return;
        }

        if(uri.contains(".gif")  ||uri.contains(".png")  || uri.contains(".jpg") || uri.contains("/js/")|| uri.contains(".css") || uri.contains("/jquery-easyui-1.5/")|| uri.contains(".ico")) {
            filterChain.doFilter(request,servletResponse);
            return;
        }
        //验证是否登录;每次要上传phone，验证
        TbEmployeeDO employeeDO=null;
        String phone=request.getParameter("phone");
        request.setAttribute("phone",phone);
        System.out.println("拦截号码："+phone);
        if(phone==null){
            return;
        }
        Boolean isLog=false;
        HttpSession session=request.getSession();
        Map<String,TbEmployeeDO> map=( Map<String,TbEmployeeDO>)session.getAttribute("user");

        if(map!=null){
            for(String ph:map.keySet()){
                if(phone.equals(ph)){
                    isLog=true;
                    employeeDO=map.get(ph);
                    break;
                }
            }
        }
        if(!isLog){
           return;
        }
        //System.out.println(employeeDO);
        TbEmployeeRoleDO employeeRoleDO=new TbEmployeeRoleDO();
        employeeRoleDO.setEid(employeeDO.getId());

        Map<String,List<TbActionDO>>  actionMap=(Map<String,List<TbActionDO>>)session.getAttribute("action");
        List<TbActionDO> list=actionMap.get(phone);
        //功能验证
        System.out.println(list);
        Boolean flag=false;
        if(list!=null ){
            for(int i=0;!flag && i<list.size();i++){
                TbActionDO actionDO=list.get(i);

                String url=null;
                if(actionDO.getUrl()!=null && actionDO.getUrl().contains("jsp/") ){
                    url="/"+actionDO.getUrl();
                }else{
                    url=actionDO.getUrl();
                }
//                System.out.println(uri);
//                System.out.println(url);
                if( uri.equals(url)){
                    flag=true;
                }
            }
        }
        if(flag){
            System.out.println("继续访问");
            filterChain.doFilter(request,servletResponse);
            return;
        }else{
            System.out.println("拦截返回");
            return;
        }
    }

    @Override
    public void destroy() {

    }



}
