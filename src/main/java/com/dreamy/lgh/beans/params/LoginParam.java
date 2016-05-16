package com.dreamy.lgh.beans.params;

/**
 * Created by wangyongxing on 16/4/8.
 */
public class LoginParam {

    private String userName;
    private String password;
    private String ip;
    private String sessionId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /***
     * 工厂方法
     * @param userName
     * @param password
     * @param ip
     * @param sessionId
     * @return
     */
    public static LoginParam getNewInstance(String userName, String password, String ip, String sessionId) {
        LoginParam loginParam = new LoginParam();
        loginParam.setUserName(userName);
        loginParam.setPassword(password);
        loginParam.setIp(ip);
        loginParam.setSessionId(sessionId);
        return loginParam;
    }
}
