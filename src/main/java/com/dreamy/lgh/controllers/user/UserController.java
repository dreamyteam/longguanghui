package com.dreamy.lgh.controllers.user;

import com.dreamy.lgh.beans.InterfaceBean;
import com.dreamy.lgh.beans.UserSession;
import com.dreamy.lgh.beans.params.RegisterParams;
import com.dreamy.lgh.controllers.LghController;
import com.dreamy.lgh.domain.user.User;
import com.dreamy.lgh.enums.ErrorCodeEnums;
import com.dreamy.lgh.service.iface.user.UserService;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/26
 * Time: 下午7:37
 */
@RequestMapping("user")
@Controller
public class UserController extends LghController {

    @Autowired
    private UserService userService;

    @RequestMapping("/update")
    @ResponseBody
    public void update(RegisterParams params, HttpServletResponse response, HttpServletRequest request) {

        InterfaceBean bean = new InterfaceBean().success();
        
        UserSession userSession = getUserSession(request);
        if (userSession != null && userSession.getUserId() > 0) {
            Integer userId = userSession.getUserId();
            User user = userService.getUserById(userId);
            if (user == null) {
                bean.failure(ErrorCodeEnums.update_profile_failed.getErrorCode(), "用户不存在");
            } else {
                String userName = params.getUserName();
                String mobile = params.getMobile();
                String birthday = params.getBirthday();
                String address = params.getAddress();
                Integer sex = params.getSex();
                if (StringUtils.isNotEmpty(userName)) {
                    user.userName(userName);
                }
                if (StringUtils.isNotEmpty(birthday)) {
                    user.birthday(TimeUtils.getDateByStr(birthday, "yyyy-MM-dd"));
                }

                if (StringUtils.isNotEmpty(mobile)) {
                    user.phone(mobile);
                }

                if (StringUtils.isNotEmpty(address)) {
                    user.address(address);
                }
                if (sex != null) {
                    user.sex(sex);
                }

                userService.updateByRecord(user);
            }
        }

        interfaceReturn(response, JsonUtils.toString(bean), "");
    }
}