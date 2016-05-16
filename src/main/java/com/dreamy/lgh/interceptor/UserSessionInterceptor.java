package com.dreamy.lgh.interceptor;

import com.dreamy.beans.CanonicalSession;
import com.dreamy.beans.UserSessionContainer;
import com.dreamy.handlers.CSRFTokenManager;
import com.dreamy.handlers.CookieHandler;
import com.dreamy.lgh.controllers.RootController;
import com.dreamy.utils.StringUtils;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @param <S>
 * @author jared
 * @Description:用户登录拦截器
 * @date Nov 7, 2014 5:44:59 PM
 */
@SuppressWarnings("unchecked")
public class UserSessionInterceptor<S extends CanonicalSession> extends HandlerInterceptorAdapter {
    private NamedThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<Long>("StopWatch-StartTime");


    private CookieHandler cookieHandler;

    private UserSessionContainer<S> userSessionContainer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler == null) {
            return false;
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            handler = handlerMethod.getBean();
        }

        long beginTime = System.currentTimeMillis();//1、开始时间
        startTimeThreadLocal.set(beginTime);

        if (handler instanceof RootController) {
            RootController<S> controller = (RootController<S>) handler;
            if (controller.enableUserSession() && controller.getUserSessionId(request) == null) {
                String id = cookieHandler.getCookieValue(request);
                if (StringUtils.isNotEmpty(id)) {
                    S userSession = userSessionContainer.get(id);
                    if (userSession != null) {
                        request.setAttribute(RootController.REQUEST_ATTRIBUTE_USERSESSION, userSession);
                    }
                } else {
                    id = UUID.randomUUID().toString();
                    cookieHandler.addCookie(response, id, 30 * 24 * 3600);
                }
                request.setAttribute(CSRFTokenManager.CSRF_PARAM_NAME, CSRFTokenManager.getTokenForSession(request.getSession()));
                request.setAttribute(RootController.REQUEST_ATTRIBUTE_USERSESSION_ID, id);
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (handler instanceof RootController) {
            RootController<S> controller = (RootController<S>) handler;
            if (controller.enableUserSession()) {
                String id = controller.getUserSessionId(request);
                if (StringUtils.isNotEmpty(id)) {
                    S userSession = (S) request.getAttribute(RootController.REQUEST_ATTRIBUTE_USERSESSION);
                    if (userSession != null) {
                        userSessionContainer.set(id, userSession);// keep alive
                    }
                }
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long endTime = System.currentTimeMillis();//2、结束时间
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
        long consumeTime = endTime - beginTime;//3、消耗的时间
        if (consumeTime > 500) {//此处认为处理时间超过500毫秒的请求为慢请求
            //TODO 记录到日志文件
            System.out.println(
                    String.format("%s consume %d millis", request.getRequestURI(), consumeTime));
        }

    }

    public CookieHandler getCookieHandler() {
        return cookieHandler;
    }

    public void setCookieHandler(CookieHandler cookieHandler) {

        this.cookieHandler = cookieHandler;
    }

    public UserSessionContainer<S> getUserSessionContainer() {
        return userSessionContainer;
    }

    public void setUserSessionContainer(UserSessionContainer<S> userSessionContainer) {
        this.userSessionContainer = userSessionContainer;
    }
}
