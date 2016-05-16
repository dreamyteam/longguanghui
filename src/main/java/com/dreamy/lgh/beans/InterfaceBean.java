package com.dreamy.lgh.beans;

import com.dreamy.lgh.enums.ErrorCodeEnums;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Created by wangyongxing on 16/4/8.
 */
public class InterfaceBean implements Serializable {

    private static final long serialVersionUID = 4963349499102370560L;

    private int errorCode;

    private String errorMsg;

    private Object data = "";

    public InterfaceBean data(Object value) {
        setData(value);
        return this;
    }

    public InterfaceBean success() {
        setErrorCode(0);
        setErrorMsg("SUCCESS");
        return this;
    }

    public InterfaceBean failure(int code, String message) {
        setErrorCode(code);
        setErrorMsg(message);
        return this;
    }

    public InterfaceBean failure(ErrorCodeEnums errorCodeEnums) {
        setErrorCode(errorCodeEnums.getErrorCode());
        setErrorMsg(errorCodeEnums.getErrorMsg());
        return this;
    }

    @JsonProperty("error_code")
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("error_msg")
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

