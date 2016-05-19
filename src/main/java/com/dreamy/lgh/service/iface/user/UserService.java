package com.dreamy.lgh.service.iface.user;


import com.dreamy.lgh.beans.WxUser;
import com.dreamy.lgh.domain.user.User;

import javax.servlet.http.Cookie;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午2:14
 */
public interface UserService {
    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    Integer save(User user);

    /**
     * @param wxUser
     * @return
     */
    User saveByWx(WxUser wxUser);

    /**
     * @param mobile
     * @return
     */
    User getUserByMobile(String mobile);

    /**
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    /**
     *
     * @param openId
     * @return
     */
    User getUserByOpenId(String openId);

    /**
     * @param user
     * @return
     */
    Integer updateByRecord(User user);

    /**
     * @param response
     * @param user
     */
    Cookie rememerPwd(User user);


}
