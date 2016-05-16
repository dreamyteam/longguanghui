package com.dreamy.lgh.service.iface;

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
}
