package com.dreamy.lgh.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by wangyongxing on 16/4/8.
 */
public class CSRFTokenManager {

    private static final String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = "duotin_session_csrf_token";

    public static final String CSRF_PARAM_NAME = "CSRFToken";

    public static String getTokenForSession(HttpSession session) {

        String token = null;

        synchronized (session) {
            token = (String) session.getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
            if (null == token) {
                token = UUID.randomUUID().toString();
                session.setAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME, token);
            }
        }

        return token;
    }

    public static String getTokenFromRequest(HttpServletRequest request) {
        return request.getParameter(CSRF_PARAM_NAME);
    }
}