package com.dreamy.lgh.controllers;

import com.dreamy.beans.UserSession;
import com.dreamy.beans.UserSessionContainer;
import com.dreamy.domain.admin.AdminUser;
import com.dreamy.handlers.CookieHandler;
import com.dreamy.service.iface.admin.AdminUserService;
import com.dreamy.utils.CookieUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangyongxing on 16/4/8.
 */

public class LghBaseController extends BaseController {

    @Autowired
    protected AdminUserService adminUserService;

    @Autowired
    protected UserSessionContainer<UserSession> userSessionContainer;

    @Autowired
    @Qualifier("sessionCookieHandler")
    protected CookieHandler sessionCookieHandler;

    /**
     * 管理员帐号登录
     *
     * @param request
     * @param response
     * @param remember 是否记住密码
     * @param adminUser    管理员相关信息
     * @return
     */
    public boolean doAdminLogin(HttpServletRequest request, HttpServletResponse response, boolean remember, AdminUser adminUser) {
        UserSession userSession = buildUserSession(adminUser);
        if (userSession != null) {
            String sid = getUserSessionId(request);
            if (StringUtils.isNotEmpty(sid)) {
                if (remember) {
                    CookieUtils.add(response, sessionCookieHandler.getCookieName(), sid,
                            sessionCookieHandler.getCookieDomain(), sessionCookieHandler.getCookiePath(), true,
                            30 * 24 * 3600);
                    userSessionContainer.persistentSet(sid, userSession);
                } else {
                    userSessionContainer.set(sid, userSession);
                }

                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 返回404页面
     *
     * @return
     */
    public String redirect404() {
        return redirect("/404");
    }


    /**
     * 获取管理员
     * @param request
     * @return
     */
    public AdminUser getUser(HttpServletRequest request) {
        AdminUser user = null;
        UserSession userSession = getUserSession(request);
        AdminUser adminUser = adminUserService.getById(userSession.getUserId());
        return adminUser;
    }
}
