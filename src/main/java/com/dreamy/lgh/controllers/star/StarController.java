package com.dreamy.lgh.controllers.star;

import com.dreamy.lgh.beans.UserSession;
import com.dreamy.lgh.controllers.LghController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/20
 * Time: 下午5:37
 */
@Controller
@RequestMapping("/star")
public class StarController extends LghController {

    @RequestMapping("/list")
    public String list(ModelMap modelMap, HttpServletRequest request) {
        UserSession userSession = userSessionContainer.get(getUserSessionId(request));
        if (userSession != null) {
            Integer userId = userSession.getUserId();

        }

        return "list";
    }
}
