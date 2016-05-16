package com.dreamy.lgh.beans;

import java.io.Serializable;

/**
 * Created by wangyongxing on 16/4/8.
 */
public interface CanonicalSession extends Serializable {

    /**
     * 用户登录状态
     *
     * @return
     */
    public boolean isLogin();
}
