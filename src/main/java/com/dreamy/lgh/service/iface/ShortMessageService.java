package com.dreamy.lgh.service.iface;

import com.dreamy.lgh.domain.user.Members;
import com.dreamy.lgh.domain.user.User;


/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/10
 * Time: 上午11:36
 */
public interface ShortMessageService {
    /**
     * @param content
     * @return
     */
    void send(String mobile, String content);


    /**
     * 发送验证码
     *
     * @param mobile
     * @param checkCode
     */
    void sendCheckCode(String mobile, String checkCode);


    /**
     * 申请通过后通知用户
     *
     * @param user
     */
    void passNote(Members member);

    /**
     * 有用户提交申请后通知管理员
     * @param user
     */
    void applyNote(User user);

    /**
     *
     * @param user
     */
    void modifyMobileCheckCode(String mobile,String checkCode);

}
