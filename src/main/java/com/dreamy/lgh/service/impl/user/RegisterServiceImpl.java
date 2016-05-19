package com.dreamy.lgh.service.impl.user;

import com.dreamy.lgh.beans.params.RegisterParams;
import com.dreamy.lgh.enums.ErrorCodeEnums;
import com.dreamy.lgh.service.iface.VerificationCodeService;
import com.dreamy.lgh.service.iface.user.RegisterService;
import com.dreamy.utils.HashUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午12:23
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private VerificationCodeService verificationCodeService;

    @Override
    public ErrorCodeEnums checkRegisterParam(RegisterParams param) {
        ErrorCodeEnums errorCodeEnums = ErrorCodeEnums.success;
        String errorMsg = "";

        //空值判断
        if (StringUtils.isEmpty(param.getMobile())) {
            errorMsg = ("手机号码不能为空！");
        } else if (StringUtils.isEmpty(param.getCheckCode())) {
            errorMsg = ("验证码不能为空！");
        }

        if (StringUtils.isEmpty(errorMsg)) {
            Pattern pattern = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[6-8])|(18[0-9]))\\d{8}$");
            Matcher matcher = pattern.matcher(param.getMobile());

            if (!matcher.matches()) {
                errorMsg = ("手机号码格式不正确！");
            } else {
                String verificationCode = verificationCodeService.getCodeFromCache(param.getMobile());
                if (!param.getCheckCode().equals(verificationCode)) {
                    errorMsg = ("验证码错误");
                }
            }
        }

        if (StringUtils.isNotEmpty(errorMsg)) {
            errorCodeEnums = ErrorCodeEnums.register_failed;
            errorCodeEnums.setErrorMsg(errorMsg);
        }

        return errorCodeEnums;
    }

    @Override
    public String createUserKey(RegisterParams param) {
        Date date = new Date();
        return HashUtils.md5(param.getMobile() + date.toString());
    }

    @Override
    public ErrorCodeEnums checkAddMemberParam(RegisterParams params) {
        ErrorCodeEnums errorCodeEnums = ErrorCodeEnums.success;
        String errorMsg = "";

        if (StringUtils.isEmpty(params.getUserName())) {
            errorMsg = "用户名不能为空";
        } else if (params.getSex() == null) {
            errorMsg = "性别不能为空";
        } else if (StringUtils.isEmpty(params.getMobile())) {
            errorMsg = "手机号码不能为空";
        } else if (StringUtils.isEmpty(params.getBirthday())) {
            errorMsg = "地址不能为空";
        }

        if (StringUtils.isNotEmpty(errorMsg)) {
            errorCodeEnums = ErrorCodeEnums.add_member_failed;
        }

        return errorCodeEnums;
    }
}
