package com.dreamy.lgh.beans;

/**
 * Created by wangyongxing on 16/4/8.
 */
public interface UserSessionContainer<S extends CanonicalSession> {

    /**
     * 通过sessionId获取用户登录信息
     *
     * @param id 用户sessionId
     * @return 用户登录对象
     */
    public S get(String id);

    /**
     * 设置用户登录信息
     *
     * @param id          用户sessionId
     * @param userSession 用户登录对象
     */
    public void set(String id, S userSession);

    /**
     * 清除用户登录信息
     *
     * @param id 用户sessionId
     */
    public void clear(String id);

    /**
     * 设置用户登录信息
     *
     * @param id          用户sessionId
     * @param userSession 用户登录对象
     */
    public void persistentSet(String id, S userSession);
}
