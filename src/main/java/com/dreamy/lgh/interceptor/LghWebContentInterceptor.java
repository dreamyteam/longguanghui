package com.dreamy.lgh.interceptor;

import com.dreamy.lgh.beans.UserSession;
import com.dreamy.lgh.controllers.LghBaseController;
import com.dreamy.utils.HttpUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Author: wangyongxing(wangyongxing@duotin.com)
 * Date: 15-6-30 上午10:14
 *
 * @Description:
 */


public class LghWebContentInterceptor extends WebContentInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg) throws Exception {
        if (super.preHandle(request, response, arg)) {
            if (arg != null && arg instanceof LghBaseController) {
                LghBaseController controller = (LghBaseController) arg;
                if (controller.enableUserSession() && controller.checkLogin()) {
                    UserSession userSession = controller.getUserSession(request);
                    if (userSession == null || !userSession.isLogin()) {
                        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx07d89ad0c6b2d206&redirect_uri=http://wx.longguanghui.net/pay/wx/&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect?service="
                                + HttpUtils.encodeUrl(HttpUtils.getFullUrl(request)));
                        return Boolean.FALSE;
                    }
                }

                return Boolean.TRUE;
            }
        }
        return Boolean.TRUE;
    }
}