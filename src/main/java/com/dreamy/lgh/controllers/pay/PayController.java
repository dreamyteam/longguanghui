package com.dreamy.lgh.controllers.pay;

import com.dreamy.lgh.beans.InterfaceBean;
import com.dreamy.lgh.beans.UserSession;
import com.dreamy.lgh.beans.WxUser;
import com.dreamy.lgh.controllers.LghController;
import com.dreamy.lgh.domain.user.Members;
import com.dreamy.lgh.domain.user.User;
import com.dreamy.lgh.service.cache.RedisClientService;
import com.dreamy.lgh.service.iface.member.MemberService;
import com.dreamy.lgh.service.iface.user.UserService;
import com.dreamy.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/17
 * Time: 下午2:43
 */
@Controller
@RequestMapping("/pay")
public class PayController extends LghController {
    private final static String WX_APP_ID = "wx07d89ad0c6b2d206";
    private final static String WX_APP_SECRET = "926bb90270acf93aabc8b390fd5300b6";
    private final static String WX_MC_ID = "1320298201";
    private final static String PAY_KEY = "jkdir003jk03e0fi3h2jkdjd93kekci9";

    @Autowired
    private RedisClientService redisClientService;

    @Autowired
    private UserService userService;

    @Autowired
    private MemberService memberService;

    @Override
    public boolean checkLogin() {
        return false;
    }


    @RequestMapping("/wx")
    public String wx(ModelMap modelMap, HttpServletRequest request,
                     @RequestParam(value = "type", defaultValue = "1") String type) {

        String code = request.getParameter("code");
        if (StringUtils.isNotEmpty(code)) {
            String token = HttpUtils.getHtmlGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WX_APP_ID + "&secret=" + WX_APP_SECRET + "&code=" + code + "&grant_type=authorization_code");

            Map<String, String> map = JsonUtils.toMap(token);
            if (!map.containsKey("errcode")) {
                String accessToken = map.get("access_token");
                String openId = map.get("openid");
                String userJson = HttpUtils.getHtmlGet("https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN");
                Map<String, String> userInfoMap = JsonUtils.toMap(userJson);

                if (!userInfoMap.containsKey("errcode")) {
                    modelMap.put("user", userInfoMap);

                    try {
                        WxUser wxUser = (WxUser) ObjectUtils.convertMapToObject(WxUser.class, userInfoMap);
                        Members currentMember = memberService.getByOpenId(openId);
                        User user = null;
                        if (currentMember == null) {
                            user = userService.saveByWx(wxUser);
                            if (user != null) {
                                memberService.saveByWx(wxUser, user.getId());
                            }
                        } else {
                            user = userService.getUserById(currentMember.getUserId());
                        }

                        if (user != null) {
                            UserSession userSession = getUserSession(request);
                            userSession.setUserId(user.getId());
                            userSession.setUsername(user.getUserName());
                            userSessionContainer.set(request.getRequestedSessionId(), userSession);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    modelMap.put("config", getPayConfig(request, openId));
                }
            }
        }

        modelMap.put("type", type);
        return "/pay/wxpay";
    }

    @RequestMapping("/wx/trade/notify")
    public void recieveNotify(HttpServletResponse response) {
        InterfaceBean bean = new InterfaceBean().success();
        interfaceReturn(response, JsonUtils.toString(bean), "");
    }

    @RequestMapping("/choose")
    public String chooseMember() {

        return "/pay/choose";
    }

    @RequestMapping("/choose/detail")
    public String memberDetail(ModelMap modelMap, @RequestParam(value = "type", defaultValue = "1") Integer type) {

        modelMap.put("type", type);
        return "/pay/detail";
    }

    @RequestMapping("/result")
    public String result(@RequestParam(value = "prepayOrderId") String orderId, @RequestParam(value = "orderStatus", defaultValue = "0") String status) {
        return "/pay/result";
    }

    public String getPayConfig(HttpServletRequest request, String openId) {
        Map<String, String> configMap = new HashMap<>();
        Date date = new Date();

        configMap.put("appId", WX_APP_ID);
        configMap.put("timeStamp", "" + date.getTime());
        configMap.put("nonceStr", "5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
        configMap.put("signType", "MD5");
        configMap.put("package", "prepay_id=" + getPrePayId(configMap, openId));

        configMap.put("paySign", createPaySign(configMap));

        Map<String, String> params = new HashMap<>();

        String tokenInfo = HttpUtils.getHtmlGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WX_APP_ID + "&secret=" + WX_APP_SECRET);
        Map<String, String> map = JsonUtils.toMap(tokenInfo);
        String jsapiInfo = HttpUtils.post("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + map.get("access_token") + "&type=jsapi", params);
        Map<String, String> jsapiInfoMap = JsonUtils.toMap(jsapiInfo);
        configMap.put("signature", createJsApiTicket(configMap, jsapiInfoMap.get("ticket"), request));
        return JsonUtils.toString(configMap);
    }

    public String createPaySign(Map<String, String> configMap) {
        String stringB = createSortStringByMap(configMap);
        String stringSignTemp = stringB + "&key=" + PAY_KEY;


        return HashUtils.md5(stringSignTemp).toUpperCase();
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

    public String getPrePayId(Map<String, String> configMap, String openId) {
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

        Map<String, String> params = new HashMap<>();
        params.put("appid", WX_APP_ID);
        params.put("mch_id", WX_MC_ID);
        params.put("nonce_str", configMap.get("nonceStr"));
        params.put("body", "支付产品测试");
        params.put("out_trade_no", configMap.get("timeStamp"));
        params.put("spbill_create_ip", "192.168.0.1");
        params.put("notify_url", "http://dev.xingyifengji.com/pay/wx/trade/notify");
        params.put("total_fee", "1");
        params.put("trade_type", "JSAPI");
        params.put("openid", openId);

        String string1 = createSortStringByMap(params);
        String stringSignTemp = string1 + "&key=" + PAY_KEY;

        params.put("sign", HashUtils.md5(stringSignTemp).toUpperCase());

        String xml = mapToXml(params);
        String res = HttpUtils.post(url, xml);
        if (StringUtils.isNotEmpty(res)) {
            String[] resStrs = res.split("prepay_id");
            if (resStrs.length == 3) {
                return resStrs[1].substring(10, resStrs[1].length() - 5);
            }
        }

        return null;
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

}
