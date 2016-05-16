package com.dreamy.lgh.controllers.user;

import com.dreamy.lgh.beans.InterfaceBean;
import com.dreamy.lgh.beans.params.LoginParam;
import com.dreamy.lgh.controllers.LghController;
import com.dreamy.lgh.service.iface.admin.AdminLoginService;
import com.dreamy.utils.ConstStrings;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/16
 * Time: 上午11:46
 */
@Controller
public class LoginController extends LghController {
    @Resource
    private AdminLoginService adminLoginService;

    @Override
    public boolean checkLogin() {
        return false;
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String loginPost(HttpServletRequest request, ModelMap model,
                            @RequestParam(value = "userName", defaultValue = ConstStrings.EMPTY) String userName,
                            @RequestParam(value = "password", defaultValue = ConstStrings.EMPTY) String password,
                            @RequestParam(value = "service", required = false, defaultValue = ConstStrings.EMPTY) String service) {
        if (StringUtils.isNotEmpty(userName)) {
            if (StringUtils.isNotEmpty(password)) {
                InterfaceBean bean = adminLoginService.doLogin(LoginParam.getNewInstance(userName, password, WebUtils.getRemoteAddress(request), getUserSessionId(request)));
                if (bean.getErrorCode() == 0) {
                    return redirect("/member/list", service);
                } else {
                    model.put("error", bean.getErrorMsg());
                }
            } else {
                model.put("error", "密码不能为空");
            }
        } else {
            model.put("error", "手机号不能为空");
        }

        model.put("userName", userName);
        model.put("password", password);
        model.put("service", service);

        return "/";
    }
}
