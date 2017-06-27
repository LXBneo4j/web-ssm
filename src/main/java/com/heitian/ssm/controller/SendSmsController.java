package com.heitian.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.heitian.ssm.model.Sms;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.utils.sms.IPhoneSMS;
import com.heitian.ssm.utils.sms.JhSmsUtils;
import com.heitian.ssm.utils.sms.PhoneSMS;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lxb on 2017/5/18.
 */
@Controller
public class SendSmsController {
    private Logger log=Logger.getLogger(SendSmsController.class);
    private Map<String,Sms> smsMap=new HashMap<String,Sms>();
    private IPhoneSMS sms=new PhoneSMS();

    /**
     * 发送短信接口
     * @param request
     * @return
     */
    @RequestMapping("/sendSms")
    @ResponseBody
    public String sendSms(HttpServletRequest request){
       //System.out.println(request.getParameter("phone")+"验证码");
       String phone=request.getParameter("phone");
        Integer flag=valPhoneSms(request);
        if(flag==0){
            Date date=new Date();
//            Integer number=(int)((Math.random()*9+1)*100000);
            //短信平台出问题
            Integer number=123456;

            log.info(phone+": "+date+"发送验证码"+number);
            Sms sms=new Sms();
            sms.setPhone(phone);
            sms.setDate(date);
            sms.setNumber(number);
            //调用发送验证码服务
//            sms.sendSmsMesage(phone);
            HttpSession session=request.getSession();
            smsMap.put(phone,sms);
            session.setAttribute("sms",smsMap);
        }
        BaseInfo info=new BaseInfo();
        info.setSuccess();
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
        String json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
        return json;
    }

    /**
     *5分钟有效
     */
    public Integer valPhoneSms(HttpServletRequest request){
        String phone=request.getParameter("phone");
        HttpSession session=request.getSession();
        Map<String,Sms> map=(Map<String,Sms>)session.getAttribute("sms");
        if(map!=null){
            for(String str:map.keySet()){
                if(phone.equals(str)){
                    Long c =  Math.abs( new Date().getTime() - map.get(str).getDate().getTime()) / (1000 * 60);
                    if(c<=5L){
                        return map.get(str).getNumber();
                    }
                }
            }
        }
        return 0;
    }

}
