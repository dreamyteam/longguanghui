package com.dreamy.lgh.service.impl;

import com.dreamy.lgh.domain.user.Members;
import com.dreamy.lgh.domain.user.User;
import com.dreamy.lgh.enums.MemberEnums;
import com.dreamy.lgh.enums.SysSettingEnums;
import com.dreamy.lgh.service.iface.ShortMessageService;
import com.dreamy.lgh.service.iface.setting.SettingService;
import com.dreamy.lgh.service.iface.user.UserService;
import com.dreamy.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/10
 * Time: 上午11:36
 */
@Service
public class ShortMessageServiceImpl implements ShortMessageService {

    private static final String API_KEY = "93a1be01ead21e04bac79f8e75225f9f";
    private static final String API_SECRITE = "8d613058";
    private static final String REQUEST_URL = "https://sms.yunpian.com/v2/sms/single_send.json";


    @Autowired
    private UserService userService;

    @Autowired
    private SettingService settingService;

    @Override
    public void send(String mobile, String text) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("apikey", API_KEY);
        map.put("mobile", mobile);
        map.put("text", text);

        HttpUtils.post(REQUEST_URL, map);
    }

    @Override
    public void sendCheckCode(String mobile, String checkCode) {
        String content = "【龙光汇】尊敬的用户，您注册的验证码是 " + checkCode;
        send(mobile, content);
    }

    @Override
    public void passNote(Members members) {
        MemberEnums[] enums = MemberEnums.values();
        String memberTypeStr = "";
        for (MemberEnums memberEnums : enums) {
            if (members.getType().equals(memberEnums.getType())) {
                memberTypeStr = memberEnums.getDescription();
            }
        }

        String content = "【龙光汇】尊敬的用户，您申请的" + memberTypeStr + "已通过审核";
        User user = userService.getUserById(members.getUserId());
        if (user != null) {
            send(user.getPhone(), content);
        }
    }


    @Override
    public void applyNote(User user) {
        String phone = settingService.getValue(SysSettingEnums.apply_note_phone.getKey());
        String content = "【龙光汇】尊敬的管理员，有新用户(" + user.getPhone() + ")提交了会员申请，请及时处理";

        send(phone, content);
    }

    @Override
    public void modifyMobileCheckCode(String mobile, String checkCode) {
        String content = "【龙光汇】尊敬的用户，你正在修改手机号码，验证码是 " + checkCode;

        send(mobile, content);
    }
}
