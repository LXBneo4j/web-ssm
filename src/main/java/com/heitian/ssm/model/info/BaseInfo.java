package com.heitian.ssm.model.info;

import com.heitian.ssm.utils.enums.ResultEnum;

/**
 * Created by lxb on 2017/5/5.
 */
public class BaseInfo<T> {

    protected  T t;

    protected  Integer code;

    protected String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public void setSuccess(){
        this.code= ResultEnum.SUCCESS.getCode();
        this.message=ResultEnum.SUCCESS.getMessage();
    }
    public void setFail(){
        this.code= ResultEnum.FAIL.getCode();
        this.message=ResultEnum.FAIL.getMessage();
    }

    public void setDbException(){
        this.code= ResultEnum.DB_EXCEPTION.getCode();
        this.message=ResultEnum.DB_EXCEPTION.getMessage();
    }

}
