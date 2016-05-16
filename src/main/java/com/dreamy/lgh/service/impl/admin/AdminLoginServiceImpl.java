package com.dreamy.lgh.service.impl.admin;

import com.dreamy.lgh.beans.InterfaceBean;
import com.dreamy.lgh.beans.UserSession;
import com.dreamy.lgh.beans.UserSessionContainer;
import com.dreamy.lgh.beans.params.LoginParam;
import com.dreamy.lgh.domain.admin.AdminUser;
import com.dreamy.lgh.service.iface.admin.AdminLoginService;
import com.dreamy.lgh.service.iface.admin.AdminUserService;
import com.dreamy.utils.HashUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/20
 * Time: 上午11:24
 */
@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    @Qualifier("userSessionContainer")
    private UserSessionContainer<UserSession> userSessionContainer;

    @Override
    public InterfaceBean doLogin(LoginParam loginParam) {
        InterfaceBean bean = new InterfaceBean().success();
        if (loginParam == null) {
            return bean.failure(1, "系统错误!");
        }

        if (StringUtils.isNotEmpty(loginParam.getUserName())) {
            if (StringUtils.isEmpty(loginParam.getPassword())) {
                return bean.failure(1, "用户密码不能为空!");
            }

            AdminUser adminUser = adminUserService.getByUsername(loginParam.getUserName());
            if (adminUser == null) {
                return bean.failure(1, "用户不存在!");
            }

            if (HashUtils.md5(loginParam.getPassword()).equals(adminUser.getPassword())) {
                //增加登录信息
                addRedisSession(loginParam.getSessionId(), adminUser);
                bean.setData(adminUser);
            } else {
                bean.failure(1, "用户密码不正确!");
            }
        }

        return bean;
    }


    private void addRedisSession(String sessionId, AdminUser adminUser) {
        if (adminUser != null && StringUtils.isNotEmpty(sessionId)) {
            UserSession session = new UserSession();
            session.setUserId(adminUser.getId());
            session.setUsername(adminUser.getUserName());
            userSessionContainer.set(sessionId, session);
        }
    }
}
