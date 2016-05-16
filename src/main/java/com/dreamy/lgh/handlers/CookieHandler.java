package com.dreamy.lgh.handlers;

import com.dreamy.utils.CookieUtils;
import com.dreamy.utils.ObjectUtils;
import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangyongxing on 16/4/1.
 */
public class CookieHandler extends CookieGenerator {

    private boolean httpOnly = false;

    /**
     * 通过request请求头获取cookie
     *
     * @param request
     * @return
     */
    public String getCookieValue(HttpServletRequest request) {
        return CookieUtils.getValue(request, getCookieName());
    }

    @Override
    public void addCookie(HttpServletResponse response, String value) {
        addCookie(response, value, ObjectUtils.nullToDefault(this.getCookieMaxAge(), -1));
    }

    public void addCookie(HttpServletResponse response, String value, int time) {
        CookieUtils.add(response, getCookieName(), value, getCookieDomain(), getCookiePath(), httpOnly, time);
    }

    public boolean isHttpOnly() {
        return httpOnly;
    }

    public void setHttpOnly(boolean httpOnly) {
        this.httpOnly = httpOnly;
    }
}
