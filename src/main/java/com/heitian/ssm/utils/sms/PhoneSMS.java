package com.heitian.ssm.utils.sms;

//import com.bcloud.msg.http.HttpSender;

//import com.google.common.base.Preconditions;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lxb on 2017/2/6.
 */
public class PhoneSMS implements IPhoneSMS {
    private static String TEST_SMS_CONTENT = "您的验证码是CODE,请及时输入";

    //protected SmsCenterService smsCenterService;
    public void sendSmsMesage(String phoneNum){
        String randCode=getSendRandCode();
        System.out.println(randCode);
        String msgCount = TEST_SMS_CONTENT.replace("CODE", randCode);
        System.out.println(msgCount);
        SmsCenterDto scd=new SmsCenterDto("code",randCode);
//        Map<String,String> map= sendSms(phoneNum, randCode, msgCount, SmsTempEnum.CQ_SMS_CONTENT,scd);
        Map<String,String> map= sendSms(phoneNum, randCode, msgCount, SmsTempEnum.SMS_GETRANDCODE,scd);
//        if (map.get("result").equals("0")) {
//            throw new RuntimeException(map.get("failureMessage"));
//        }
    }

    /**
     *
     * @param phoneNum
     * @param code
     * @param message
     *
     * @return
     */
    public Map<String, String> sendSms(String phoneNum, String code, String message, SmsTempEnum smsTempEnum, SmsCenterDto smsCenterDto) {
        Map<String, String> result = new HashMap<String, String>();

//        String resp = smsCenterService.sendMessage(message, phoneNum, null,smsTempEnum,smsCenterDto);
//        String sendType = smsCenterService.sendMessageType(resp);
        String resp=sendMessage(message,phoneNum,null,smsTempEnum,smsCenterDto);
        String sendType = sendMessageType(resp);
        // 发送成功
        //System.out.println(resp+"  "+sendType);
        System.out.println("=========>"+sendType);
        return result;
    }

    public String sendMessage(String message, String phoneNum, String time, SmsTempEnum smsTempEnum, SmsCenterDto smsCenterDto) {
        return jhSendMessage(message, phoneNum, time, smsTempEnum,smsCenterDto);
        //return ycSendMessage(message, phoneNum, time, smsTempEnum);
        //return clSendMessage(message, phoneNum, time);
    }

    public String sendMessageType(String str) {
        return jhSendMessageType(str);
        //return ycSendMessageType(str);
        //return clSendMessageType(str);
    }

    /**
     * 聚合短信服务平台
     * @param message
     * @param phoneNum
     * @param time
     * @param smsTempEnum
     * @param smsCenterDto
     * @return
     */
    public String jhSendMessage(String message, String phoneNum, String time, SmsTempEnum smsTempEnum, SmsCenterDto smsCenterDto) {
        String result =null;
        String url ="http://v.juhe.cn/sms/send";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("mobile",phoneNum);//接收短信的手机号码
        params.put("tpl_id",smsTempEnum.getJhTempId());//短信模板ID，请参考个人中心短信模板设置
        //params.put("tpl_value",smsCenterDto.toString());//变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
        //params.put("tpl_value",smsCenterDto.getTplValue().get("code"));
        params.put("key","faff1c477989a7ce80d86612e5c35db4");//应用APPKEY(应用详细页查询)
        params.put("dtype","json");//返回数据的格式,xml或json，默认json
        //System.out.println(smsCenterDto.toString());
        try {
            result = JhSmsUtils.net(url, params, "GET");
            System.out.println(phoneNum+"  MESSAGE:-=-=-=-=-=-===");
        } catch (Exception e) {
            System.out.println("错====了");
        }
        return result;

    }
    public String jhSendMessageType(String jsonStr) {
        //System.out.println("发送字符串："+jsonStr);
        try {
            net.sf.json.JSONObject object = net.sf.json.JSONObject.fromObject(jsonStr);
            if(object.getInt("error_code")==0){//成功
                return "1";
            }else{
                throw new RuntimeException(object.toString()+object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            System.out.println("JH发短信失败,响应报文:"+jsonStr+e.getMessage());
        }
        return "-1";
    }

    /**
     * 云测短信服务平台（成功）
     * @param message
     * @param phoneNum
     * @param time
     * @param smsTempEnum
     * @return
     */

    public String ycSendMessage(String message, String phoneNum, String time, SmsTempEnum smsTempEnum) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("op", "Sms.send");
        jsonObj.put("apiKey", "f693ea19b1964d94f1d6896448170691");
        jsonObj.put("ts", System.currentTimeMillis());
        jsonObj.put("phone", phoneNum);
        jsonObj.put("templateId", smsTempEnum.getYcTempId());
        jsonObj.put("content", message);
        jsonObj.put("taskId", System.currentTimeMillis()+"");//不超过64位长度的唯一字符串，通过和recvRPT获取的结果里的teskid关联，确定发送的信息是否收到。
        jsonObj.put("extNum", "");
        jsonObj.put("sig", MessageUtil.getSig(jsonObj, "1B7994373D25C5DB"));

        String result = MessageUtil.transmessage("http://api.sms.testin.cn/sms", jsonObj.toString());
        return result;
    }


    public String ycSendMessageType(String jsonStr) {

        try {
            JSONObject jsonReq = new JSONObject(jsonStr);

            //	System.out.println(jsonReq.get("code").toString());

            if (jsonReq.get("code").toString().equals("1000")) {
                return "1";
            }else {
                throw new RuntimeException(jsonReq.toString());
            }
        } catch (Exception e) {
            //logger.error("YC发短信失败,响应报文:"+jsonStr+e.getMessage());
        }
        return "-1";
    }

    /**
     * 创蓝短信服务平台
     * @param message
     * @param phoneNum
     * @param time
     * @return
     */

    public String clSendMessage(String message, String phoneNum, String time) {

        String returnString =null;
        try {
            Preconditions.checkNotNull(message,"短信内容不能为空");
            Preconditions.checkNotNull(phoneNum,"手机号不能为空");
            //returnString = HttpSender.batchSend("http://222.73.117.156:80/msg/HttpBatchSendSM", "jinwowo", "Tch123456", phoneNum, message, true, null, null);
        } catch (Exception e) {
            // TODO 处理异常
            returnString= e.toString();
        }
        System.out.println(returnString);
        return returnString;
    }


    public String clSendMessageType(String XMLStr) {
        try {
            Preconditions.checkNotNull(XMLStr,"短信响应报文为空");
            String[] respArr=XMLStr.split(",");
            if(respArr[1].startsWith("0")){//请求成功
                return "1";
            }else {
                //logger.info("发短信响应报文:"+XMLStr);
                return XMLStr;
            }
        } catch (Exception e) {
            return "-1";
        }
    }



    /**
     * 获取发送的验证码，如果离上次发送XX分钟以内，验证码不变
     *
     * @return
     */
    private String getSendRandCode() {
        String code = RandomStringUtils.randomNumeric(6);
        return code;
    }
}
