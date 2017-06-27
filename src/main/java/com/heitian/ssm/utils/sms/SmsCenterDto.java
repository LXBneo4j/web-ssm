package com.heitian.ssm.utils.sms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lxb on 2017/2/6.
 */
public class SmsCenterDto {
    private Map<String,Object>  tplValue;
    public SmsCenterDto(String title, Object value) {
        Map mc=new HashMap();
        mc.put(title,value);
        setTplValue(mc);
    }


    public Map<String, Object> getTplValue() {
        return tplValue;
    }

    public void setTplValue(Map<String, Object> tplValue) {
        this.tplValue = tplValue;
    }

    @Override
    public String toString() {
        return "SmsCenterDto{" +
                "tplValue=" + tplValue +
                '}';
    }
}
