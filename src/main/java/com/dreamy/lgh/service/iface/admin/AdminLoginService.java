package com.dreamy.lgh.service.iface.admin;


import com.dreamy.lgh.beans.InterfaceBean;
import com.dreamy.lgh.beans.params.LoginParam;

/**
 * Created by wangyongxing on 16/4/8.
 */
public interface AdminLoginService {

    /**
     * 用户登录
     *
     * @param loginParam
     * @return
     */
    public InterfaceBean doLogin(LoginParam loginParam);
}
