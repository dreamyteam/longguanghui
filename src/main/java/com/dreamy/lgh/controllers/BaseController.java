package com.dreamy.lgh.controllers;

import com.dreamy.beans.UserSession;
import com.dreamy.domain.admin.AdminUser;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.WebUtils;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * Created by wangyongxing on 16/4/1.
 */
public class BaseController extends RootController<UserSession> {

    /**
     * 页面跳转
     *
     * @param url 跳转链接
     * @return
     */
    public String redirect(String url) {
        return "redirect:" + url;
    }

    /**
     * 链接跳转
     *
     * @param url     默认链接
     * @param service 目标服务链接
     * @return
     */
    public String redirect(String url, String service) {
        if (StringUtils.isNotEmpty(service)) {
            return "redirect:" + WebUtils.decodeUrl(service);
        } else {
            return "redirect:" + url;
        }
    }

    /**
     * 构建登录用户基本信息
     *
     * @param admin
     * @return
     */
    public UserSession  buildUserSession(AdminUser admin) {
        if (admin != null) {
            UserSession userSession = new UserSession();
            userSession.setUserId(admin.getId());
            userSession.setUsername(admin.getTelphone());

            return userSession;
        }
        return getGuestSession();
    }

    /**
     * 设置错误提示
     *
     * @param mmap    页面对象
     * @param message 错误信息
     */
    public void setErrorMessage(ModelMap mmap, String message) {
        mmap.addAttribute("error", message);
    }

    @Override
    public UserSession getGuestSession() {
        return UserSession.GUEST;
    }

    /**
     * 接口请求数据返回
     *
     * @param response
     * @param result
     * @param type
     * @param callback
     * @return
     */
    public void interfaceReturn(HttpServletResponse response, String result, String type, String callback) {
        String resultView = "";
        String contentType = WebUtils.getContentTypeText();
        String jsonResult = result;
        if (StringUtils.isNotEmpty(callback)) {
            StringBuilder jsonp = new StringBuilder();
            jsonp.append(callback).append("(").append(jsonResult).append(");");
            contentType = WebUtils.getContentTypeJavascript();
            resultView = jsonp.toString();
        } else {
            contentType = WebUtils.getContentTypeJson();
            resultView = jsonResult;
        }
        write(response, resultView, contentType);
    }

    /**
     * 接口请求数据返回
     *
     * @param response
     * @param result
     * @param callback
     */
    public void interfaceReturn(HttpServletResponse response, String result, String callback) {
        interfaceReturn(response, result, null, callback);
    }

    /**
     * web请求返回数据返回
     *
     * @param response
     * @param data
     * @param contentType 返回数据类型
     */
    public void write(HttpServletResponse response, String data, String contentType) {
        try {
            if (StringUtils.isNotEmpty(contentType)) {
                HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
                wrapper.setContentType(contentType);
            }
            response.getWriter().print(StringUtils.nullToEmpty(data));
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
