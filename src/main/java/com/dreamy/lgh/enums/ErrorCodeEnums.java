package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/7
 * Time: 下午4:12
 */
public enum ErrorCodeEnums {
    success(0, "success"),

    //注册、登陆相关
    register_failed(20001, "注册失败"),
    login_failed(2002, "登录失败"),
    get_verification_code_failed(2003,"获取验证码失败"),


    //图片上传
    image_upload_failed(9001, "图片上传失败"),

    //投票
    vote_failed(10001, "投票失败");


    private Integer errorCode;

    private String errorMsg;

    ErrorCodeEnums(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
