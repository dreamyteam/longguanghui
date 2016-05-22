package com.dreamy.lgh.controllers.star;

import com.dreamy.beans.Page;
import com.dreamy.lgh.beans.InterfaceBean;
import com.dreamy.lgh.beans.UserSession;
import com.dreamy.lgh.controllers.LghController;
import com.dreamy.lgh.domain.star.Star;
import com.dreamy.lgh.enums.ErrorCodeEnums;
import com.dreamy.lgh.service.iface.star.StarService;
import com.dreamy.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/20
 * Time: 下午5:37
 */
@Controller
@RequestMapping("/star")
public class StarController extends LghController {

    @Override
    public boolean checkLogin() {
        return false;
    }

    @Autowired
    private StarService starService;

    @RequestMapping("/list")
    public String list(ModelMap modelMap, HttpServletRequest request) {
//        UserSession userSession = userSessionContainer.get(getUserSessionId(request));
//        if (userSession != null) {
            Integer userId = 1;
//            Integer userId = userSession.getUserId();

            Page page = new Page();
            page.setPageSize(300);
            List<Star> starList = starService.getStarsByPage(page);
            List<Integer> followStarIds = starService.getFollowStarIdsByUserId(userId, page);

            modelMap.put("starList", starList);
            modelMap.put("followStarIds", followStarIds);
            return "/star/list";
//        } else {
//            return null;
//        }

    }

    @RequestMapping("/follow")
    @ResponseBody
    public void follow(HttpServletResponse response, HttpServletRequest request, @RequestParam(value = "starId") Integer starId) {
        InterfaceBean bean = new InterfaceBean().success();
        UserSession userSession = userSessionContainer.get(getUserSessionId(request));
        if (userSession != null) {
            Integer userId = userSession.getUserId();
            ErrorCodeEnums errorCodeEnums = starService.addFollow(userId, starId);
            if (errorCodeEnums.getErrorCode() > 0) {
                bean.failure(errorCodeEnums);
            }

        }

        interfaceReturn(response, JsonUtils.toString(bean), "");
    }

    @RequestMapping("/unfollow")
    @ResponseBody
    public void followCancel(HttpServletResponse response, HttpServletRequest request, @RequestParam(value = "starId") Integer starId) {
        InterfaceBean bean = new InterfaceBean().success();
        UserSession userSession = userSessionContainer.get(getUserSessionId(request));
        if (userSession != null) {
            Integer userId = userSession.getUserId();
            ErrorCodeEnums errorCodeEnums = starService.cancelFollow(userId, starId);
            if (errorCodeEnums.getErrorCode() > 0) {
                bean.failure(errorCodeEnums);
            }

        }

        interfaceReturn(response, JsonUtils.toString(bean), "");
    }
}
