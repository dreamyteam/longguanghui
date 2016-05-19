package com.dreamy.lgh.service.impl.wx;

import com.dreamy.lgh.beans.WxUser;
import com.dreamy.lgh.domain.user.Members;
import com.dreamy.lgh.service.cache.RedisClientService;
import com.dreamy.lgh.service.iface.order.OrderService;
import com.dreamy.lgh.service.iface.wx.WxService;
import com.dreamy.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/19
 * Time: 下午1:33
 */
@Service
public class WxServiceImpl implements WxService {
    private final static String WX_APP_ID = "wx07d89ad0c6b2d206";
    private final static String WX_APP_SECRET = "926bb90270acf93aabc8b390fd5300b6";
    private final static String WX_MC_ID = "1320298201";
    private final static String PAY_KEY = "jkdir003jk03e0fi3h2jkdjd93kekci9";
    private final static String NOTIFY_URL = "http://dev.xingyifengji.com/pay/wx/trade/notify";
    private final static String VIP_FEE = "1";

    @Autowired
    private RedisClientService redisClientService;

    @Autowired
    private OrderService orderService;

    @Override
    public Map<String, String> getWxTokenInfo(String code) {
        String token = HttpUtils.getHtmlGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WX_APP_ID + "&secret=" + WX_APP_SECRET + "&code=" + code + "&grant_type=authorization_code");

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

    @Override
    public String getPayConfig(HttpServletRequest request, Members members) {
        Map<String, String> configMap = new HashMap<>();
        Date date = new Date();
        configMap.put("appId", WX_APP_ID);
        configMap.put("timeStamp", "" + date.getTime());
        configMap.put("nonceStr", StringUtils.random(32));
        configMap.put("signType", "MD5");
        configMap.put("package", "prepay_id=" + getPrePayId(configMap, members, HttpUtils.getIpAddr(request)));
        configMap.put("paySign", createPaySign(configMap));
        configMap.put("signature", createJsApiTicket(configMap, getTicketForJsApi(), request));

        return JsonUtils.toString(configMap);
    }

    public String getPrePayId(Map<String, String> configMap, Members members, String ip) {
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

        Map<String, String> params = new HashMap<>();
        params.put("appid", WX_APP_ID);
        params.put("mch_id", WX_MC_ID);
        params.put("nonce_str", configMap.get("nonceStr"));
        params.put("body", "龙光汇VIP会员(一年)");
        params.put("out_trade_no", orderService.createOrderId(members.getUserId()));
        params.put("spbill_create_ip", ip);
        params.put("notify_url", NOTIFY_URL);
        params.put("total_fee", VIP_FEE);
        params.put("trade_type", "JSAPI");
        params.put("openid", members.getWxId());

        String string1 = createSortStringByMap(params);
        String stringSignTemp = string1 + "&key=" + PAY_KEY;
        params.put("sign", HashUtils.md5(stringSignTemp).toUpperCase());

        String res = HttpUtils.post(url, mapToXml(params));
        if (StringUtils.isNotEmpty(res)) {
            String[] resStrs = res.split("prepay_id");
            if (resStrs.length == 3) {
                return resStrs[1].substring(10, resStrs[1].length() - 5);
            }
        }

        return null;
    }

    public String createPaySign(Map<String, String> configMap) {
        String stringB = createSortStringByMap(configMap);
        String stringSignTemp = stringB + "&key=" + PAY_KEY;

        return HashUtils.md5(stringSignTemp).toUpperCase();
    }

    public String createSortStringByMap(Map<String, String> configMap) {
        Collection<String> keyset = configMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        String stringA = "";
        for (String key : list) {
            stringA += key + "=" + configMap.get(key) + "&";
        }

        return stringA.substring(0, stringA.length() - 1);
    }

    private String mapToXml(Map<String, String> map) {
        String xml = "<xml>";
        for (String key : map.keySet()) {
            if (isNumeric(map.get(key))) {
                xml += "<" + key + ">" + map.get(key) + "</" + key + ">";
            } else {
                xml += "<" + key + "><![CDATA[" + map.get(key) + "]]></" + key + ">";
            }
        }
        xml += "</xml>";

        return xml;
    }

    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String createJsApiTicket(Map<String, String> configMap, String ticket, HttpServletRequest request) {
        Map<String, String> ticketMap = new HashMap<>();

        String currentFullUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
        ticketMap.put("noncestr", configMap.get("nonceStr"));
        ticketMap.put("timestamp", configMap.get("timeStamp"));
        ticketMap.put("jsapi_ticket", ticket);
        ticketMap.put("url", currentFullUrl);

        Collection<String> keyset = ticketMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);

        String stringA = "";
        for (String key : list) {
            stringA += key + "=" + ticketMap.get(key) + "&";
        }

        String stringB = stringA.substring(0, stringA.length() - 1);
        return HashUtils.sha1(stringB.getBytes());
    }

    public String getTicketForJsApi() {
        String cacheKey = "token_for_jsapi";
        String ticket = redisClientService.get(cacheKey);
        if (StringUtils.isEmpty(ticket)) {
            Map<String, String> params = new HashMap<>();
            String tokenInfo = HttpUtils.getHtmlGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WX_APP_ID + "&secret=" + WX_APP_SECRET);
            Map<String, String> map = JsonUtils.toMap(tokenInfo);
            String jsapiInfo = HttpUtils.post("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + map.get("access_token") + "&type=jsapi", params);
            Map<String, String> jsapiInfoMap = JsonUtils.toMap(jsapiInfo);

            ticket = jsapiInfoMap.get("ticket");
            redisClientService.set(cacheKey, ticket);
            redisClientService.expire(cacheKey, 6000);
        }
        return ticket;
    }

}
