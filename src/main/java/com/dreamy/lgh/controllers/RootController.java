package com.dreamy.lgh.controllers;

import javax.servlet.http.HttpServletRequest;

/**
 * @param <S> 用户登录信息对象
*Created by wangyongxing on 16/4/1.
 */
@SuppressWarnings("unchecked")
public abstract class RootController<S extends Object> {

    public static final String REQUEST_ATTRIBUTE_USERSESSION = "thisUser";
    public static final String REQUEST_ATTRIBUTE_USERSESSION_ID = "DREAMY_USERSESSION_ID";

    /**
     * 是否启用用户登录信息
     *
     * @return
     */
    public boolean enableUserSession() {
        return true;
    }

    /**
     * 获取用户的sessionID
     *
     * @param request
     * @return
     */
    public String getUserSessionId(HttpServletRequest request) {
        return (String) request.getAttribute(REQUEST_ATTRIBUTE_USERSESSION_ID);
    }

    /**
     * 获取用户登录信息
     *
     * @param request
     * @return
     */
    public S getUserSession(HttpServletRequest request) {
        if (request != null) {
            S userSession = (S) request.getAttribute(REQUEST_ATTRIBUTE_USERSESSION);
            if (userSession != null) {
                return userSession;
            }
        }

        return getGuestSession();
    }

    /**
     * 清除用户登录信息
     *
     * @param request
     */
    public void clearUserSession(HttpServletRequest request) {
        request.removeAttribute(REQUEST_ATTRIBUTE_USERSESSION);
    }

    /**
     * 默认游客用户
     *
     * @return
     */
    public abstract S getGuestSession();

    /**
     * 是否要求登录
     *
     * @return
     */
    public boolean checkLogin() {
        return false;
    }
}

