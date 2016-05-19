package com.dreamy.lgh.service.impl.wx;

import com.dreamy.lgh.beans.WxUser;
import com.dreamy.lgh.service.iface.wx.WxService;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/19
 * Time: 下午1:33
 */
@Service
public class WxServiceImpl implements WxService {

    @Override
    public Map<String, String> getWxTokenInfo(String code, String appId, String appSecret) {
        String token = HttpUtils.getHtmlGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code");

        return JsonUtils.toMap(token);
    }

    @Override
    public WxUser getWxUserInfoByAccessTokenAndOpenId(String accessToken, String openId) {
        String userJson = HttpUtils.getHtmlGet("https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN");
        Map<String, String> userInfoMap = JsonUtils.toMap(userJson);
        if (!userInfoMap.containsKey("errcode")) {
            try {
                return (WxUser) ObjectUtils.convertMapToObject(WxUser.class, userInfoMap);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
