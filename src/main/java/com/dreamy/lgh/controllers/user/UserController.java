package com.dreamy.lgh.controllers.user;

import com.dreamy.lgh.beans.InterfaceBean;
import com.dreamy.lgh.beans.UserSession;
import com.dreamy.lgh.beans.params.RegisterParams;
import com.dreamy.lgh.controllers.LghController;
import com.dreamy.lgh.domain.user.Members;
import com.dreamy.lgh.domain.user.User;
import com.dreamy.lgh.domain.user.UserWithMember;
import com.dreamy.lgh.enums.ErrorCodeEnums;
import com.dreamy.lgh.enums.MemberEnums;
import com.dreamy.lgh.service.iface.VerificationCodeService;
import com.dreamy.lgh.service.iface.member.MemberService;
import com.dreamy.lgh.service.iface.user.UserService;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

    @Autowired
    private MemberService memberService;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Override
    public boolean checkLogin() {
        return false;
    }

    @RequestMapping("/center")
    public String userCenter(ModelMap modelMap, HttpServletRequest request) {
        Integer activeDays = 0;
        UserSession userSession = getUserSession(request);
        if (userSession != null && userSession.getUserId() != 0) {
            Integer userId = userSession.getUserId();
            User user = userService.getUserById(userId);
            Members members = memberService.getByUserId(userId);

            UserWithMember userWithMember = new UserWithMember();
            userWithMember.setUser(user);
            userWithMember.setMembers(members);

            MemberEnums[] enums = MemberEnums.values();
            for (MemberEnums memberEnums : enums) {
                if (members.getType().equals(memberEnums.getType())) {
                    userWithMember.setLevelStr(memberEnums.getDescription());
                }
            }

            long activeTime = TimeUtils.diff(members.getStartedAt(), members.getEndedAt());

            if (activeTime > 0) {
                activeDays = (int) (activeTime / (24 * 60 * 60 * 1000));
            }
            modelMap.put("userWithMember", userWithMember);
            modelMap.put("activeDays", activeDays);
            return "/user/center";
        }

        return null;
    }

    @RequestMapping("/modify")
    public String modify(ModelMap modelMap, HttpServletRequest request) {
        UserSession userSession = userSessionContainer.get(getUserSessionId(request));
        if (userSession != null && userSession.getUserId() > 0) {
            User user = userService.getUserById(userSession.getUserId());

            modelMap.put("user", user);
            return "/user/modify";
        }
        return null;
    }

    @RequestMapping("/update")
    @ResponseBody
    public void update(RegisterParams params, HttpServletResponse response, HttpServletRequest request) {

        InterfaceBean bean = new InterfaceBean().success();

        UserSession userSession = userSessionContainer.get(getUserSessionId(request));
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
                    if (verificationCodeService.getCodeFromCache(mobile) != params.getCheckCode()) {
                        bean.failure(ErrorCodeEnums.update_profile_failed.getErrorCode(), "验证码错误");
                    } else {
                        user.phone(mobile);
                    }
                }

                if (StringUtils.isNotEmpty(address)) {
                    user.address(address);
                }
                if (sex != null) {
                    user.sex(sex);
                }

                if (bean.getErrorCode() == 0) {
                    userService.updateByRecord(user);
                }
            }
        }

        interfaceReturn(response, JsonUtils.toString(bean), "");
    }
}
