package com.dreamy.lgh.controllers.user;

import com.dreamy.lgh.beans.InterfaceBean;
import com.dreamy.lgh.beans.UserSession;
import com.dreamy.lgh.beans.params.RegisterParams;
import com.dreamy.lgh.controllers.LghController;
import com.dreamy.lgh.domain.user.Members;
import com.dreamy.lgh.domain.user.User;
import com.dreamy.lgh.enums.ErrorCodeEnums;
import com.dreamy.lgh.service.iface.ShortMessageService;
import com.dreamy.lgh.service.iface.VerificationCodeService;
import com.dreamy.lgh.service.iface.member.MemberService;
import com.dreamy.lgh.service.iface.user.UserService;
import com.dreamy.lgh.service.impl.user.RegisterServiceImpl;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.PasswordUtils;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.TimeUtils;
import com.dreamy.utils.asynchronous.AsynchronousService;
import com.dreamy.utils.asynchronous.ObjectCallable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午2:42
 */
@Controller
public class RegisterController extends LghController {

    @Autowired
    private RegisterServiceImpl registerService;

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private ShortMessageService shortMessageService;

    @Autowired
    private MemberService memberService;

    @Override
    public boolean checkLogin() {
        return false;
    }

    @RequestMapping(value = "/user/register/verificationCode")
    @ResponseBody
    public void getVerificationCode(RegisterParams param, HttpServletResponse response) {
        InterfaceBean bean = new InterfaceBean().success();
        String mobile = param.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            bean.failure(ErrorCodeEnums.get_verification_code_failed.getErrorCode(), "手机号码不能为空");
        } else {
            User user = userService.getUserByMobile(param.getMobile());
            if (user.getId() != null) {
                bean.failure(ErrorCodeEnums.get_verification_code_failed.getErrorCode(), "手机号码已经存在");
            } else {
                AsynchronousService.submit(new ObjectCallable(mobile) {
                    @Override
                    public Object run() throws Exception {
                        String code = verificationCodeService.createVerificationCode(4);
                        if (StringUtils.isNotEmpty(code)) {
                            verificationCodeService.saveCodeToCache(name, code);
                            shortMessageService.sendCheckCode(name, code);
                        }

                        return null;
                    }
                });
            }
        }

        interfaceReturn(response, JsonUtils.toString(bean), "");
    }


    @RequestMapping(value = "/user/register")
    @ResponseBody
    public void register(RegisterParams param, HttpServletRequest request, HttpServletResponse response) {
        InterfaceBean bean = new InterfaceBean().success();

        ErrorCodeEnums errorCodeEnums = ErrorCodeEnums.success;
        if (errorCodeEnums.getErrorCode() > 0) {
            bean.failure(errorCodeEnums);
        } else {
            User user = userService.getUserByMobile(param.getMobile());
            if (user.getId() == null) {
                UserSession userSession = userSessionContainer.get(getUserSessionId(request));
                Integer userId = userSession.getUserId();
                user = userService.getUserById(userId);

                user.phone(param.getMobile());
                user.userName(param.getUserName());
                user.address(param.getAddress());
                user.userKey(registerService.createUserKey(param));
                user.birthday(TimeUtils.getDateByStr(param.getBirthday(), "yyyy-MM-dd"));
                user.sex(param.getSex());

                userService.updateByRecord(user);

                Members members = memberService.getByUserId(userId);
                members.type(Integer.parseInt(param.getType()));
                memberService.updateByRecord(members);

                UserSession session = getUserSession(request);
                session.setUserId(user.getId());
                session.setUsername(user.getUserName());
                userSessionContainer.set(getUserSessionId(request), session);
            } else {
                errorCodeEnums = ErrorCodeEnums.register_failed;
                errorCodeEnums.setErrorMsg("用户已存在！");
                bean.failure(errorCodeEnums);
            }
        }

        interfaceReturn(response, JsonUtils.toString(bean), "");
    }
}
