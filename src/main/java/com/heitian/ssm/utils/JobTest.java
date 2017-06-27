package com.heitian.ssm.utils;

import com.heitian.ssm.utils.job.MsgPushJob;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * Created by lxb on 2017/6/5.
 */
public class JobTest {
    public static void main(String[] args) {
//        QuartzUtils.addJob("lxb", MsgPushJob.class,new Date());
//        System.out.println("结束");
//        String ip=null;
//        try {
//            /**返回本地主机。*/
//            InetAddress addr = InetAddress.getLocalHost();
//            /**返回 IP 地址字符串（以文本表现形式）*/
//            ip = addr.getHostAddress();
//        } catch(Exception ex) {
//            ip = "";
//        }
//        System.out.println(ip);

        //快递100订单查询

        try {
            URL url= new URL("http://api.kuaidi100.com/api?id=AqaFWUkG3018&com=tiandihuayu&nu=289036534745&show=0&muti=1&order=desc");
            URLConnection con=url.openConnection();
            con.setAllowUserInteraction(false);
            InputStream urlStream = url.openStream();
            String type = con.guessContentTypeFromStream(urlStream);
            String charSet=null;
            if (type == null) {
                type = con.getContentType();
            }
            if (type == null || type.trim().length() == 0 || type.trim().indexOf("text/html") < 0){
                return ;
            }
            if(type.indexOf("charset=") > 0)
                charSet = type.substring(type.indexOf("charset=") + 8);

            byte b[] = new byte[10000];
            int numRead = urlStream.read(b);
            String content = new String(b, 0, numRead);
            while (numRead != -1) {
                numRead = urlStream.read(b);
                if (numRead != -1) {
                    //String newContent = new String(b, 0, numRead);
                    String newContent = new String(b, 0, numRead, charSet);
                    content += newContent;
                }
            }
            System.out.println("content:" + content);

            urlStream.close();
        } catch (Exception e){
            System.out.println("出错了："+e.getMessage());
        }

}
}








