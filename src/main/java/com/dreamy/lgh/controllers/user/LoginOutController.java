package com.dreamy.lgh.controllers.user;

import com.dreamy.lgh.controllers.LghController;
import com.dreamy.utils.ConstStrings;
import com.dreamy.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangyongxing on 16/4/15.
 */
@Controller
@RequestMapping(value = "/logout")
public class LoginOutController extends LghController {

    /**
     * 退出登录
     *
     * @param request
     * @param service
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(HttpServletRequest request,
                         @RequestParam(value = "service", required = false, defaultValue = ConstStrings.EMPTY) String service) {
        String sid = getUserSessionId(request);
        if (StringUtils.isNotEmpty(sid)) {
            clearUserSession(request);
            userSessionContainer.clear(sid);
        }
        return redirect("/", service);
    }

}
