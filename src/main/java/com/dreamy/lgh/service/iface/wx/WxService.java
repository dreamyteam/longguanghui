package com.dreamy.lgh.service.iface.wx;

import com.dreamy.lgh.beans.WxUser;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/19
 * Time: 下午1:32
 */
public interface WxService {

    /**
     *
     * @param code
     * @param appId
     * @param appSecret
     * @return
     */
    Map<String, String> getWxTokenInfo(String code,String appId,String appSecret);

    /**
     *
     * @param accessToken
     * @param openId
     * @return
     */
    WxUser getWxUserInfoByAccessTokenAndOpenId(String accessToken,String openId);


}
