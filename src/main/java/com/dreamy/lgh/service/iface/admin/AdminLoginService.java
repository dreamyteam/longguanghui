package com.dreamy.lgh.service.iface.admin;


import com.dreamy.lgh.beans.InterfaceBean;
import com.dreamy.lgh.beans.params.LoginParams;

/**
 * Created by wangyongxing on 16/4/8.
 */
public interface AdminLoginService {

    /**
     * 用户登录
     *
     * @param loginParams
     * @return
     */
    public InterfaceBean doLogin(LoginParams loginParams);
}
