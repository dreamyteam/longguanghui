package com.dreamy.lgh.service.iface.wx;

import com.dreamy.lgh.beans.WxUser;
import com.dreamy.lgh.domain.user.Members;

import javax.servlet.http.HttpServletRequest;
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
     * @return
     */
    Map<String, String> getWxTokenInfo(String code);

    /**
     *
     * @param accessToken
     * @param openId
     * @return
     */
    WxUser getWxUserInfoByAccessTokenAndOpenId(String accessToken,String openId);

    /**
     *
     * @param request
     * @param members
     * @return
     */
   String getPayConfig(HttpServletRequest request,Members members);

    /**
     *
     * @param map
     */
    void  handleNotifyDataFroWx( Map<String, String> map);
}
