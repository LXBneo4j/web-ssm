package com.heitian.ssm.utils.enums;

/**
 * Created by lxb on 2017/5/5.
 */
public enum ResultEnum {
    NO_LOGIN(1001,"未登录"),
    SYSTEM_EXCEPTION(1002,"系统异常"),
    DB_EXCEPTION(1003,"数据库异常"),
    LOGIN_FAIL(2001,"登录失败"),
    LOGIN_SUCCESS(2002,"登录成功"),
    SUCCESS(1000,"执行成功"),
    FAIL(9999,"执行失败")
    ;
    protected Integer code;

    protected String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

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
}
