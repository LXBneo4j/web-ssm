package com.heitian.ssm.utils;


import org.apache.log4j.Logger;

/**
 * Created by lxb on 2017/5/11.
 */
public class PageValidate {
    static Logger log=Logger.getLogger(PageValidate.class);
    public static Boolean validate(Integer no,Integer size){
        log.info("==分页检查;页码："+no+"--大小："+size+"==");
        if(no>=1 && size>=1){
            return true;
        }
        log.info("分页参数有错");
        return false;
    }
}
