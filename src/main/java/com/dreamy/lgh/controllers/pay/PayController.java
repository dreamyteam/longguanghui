package com.dreamy.lgh.controllers.pay;

import com.dreamy.lgh.beans.InterfaceBean;
import com.dreamy.lgh.controllers.LghController;
import com.dreamy.lgh.service.cache.RedisClientService;
import com.dreamy.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.regex.Pattern;

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
    private final static String PAY_KEY = "";

    @Autowired
    private RedisClientService redisClientService;

    @Override
    public boolean checkLogin() {
        return false;
    }


    @RequestMapping("/wx")
    public String wx(ModelMap modelMap, HttpServletRequest request) {

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
                    modelMap.put("config", getPayConfig(request, openId));
                }
            }
        }

        return "/pay/wxpay";
    }

    @RequestMapping("/wx/trade/notify")
    public void recieveNotify(HttpServletResponse response) {
        InterfaceBean bean = new InterfaceBean().success();
        interfaceReturn(response, JsonUtils.toString(bean), "");

    }


    public String getPayConfig(HttpServletRequest request, String openId) {
        Map<String, String> configMap = new HashMap<>();
        Date date = new Date();

        configMap.put("appId", WX_APP_ID);
        configMap.put("timeStamp", "" + date.getTime());
        configMap.put("nonceStr", "5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
        configMap.put("signType", "MD5");
        configMap.put("package", "prepay_id=" + getPrePayId(configMap, openId));

//        configMap.put("paySign", createPaySign(configMap));
//
//        Map<String, String> params = new HashMap<>();
//
//        String tokenInfo = HttpUtils.getHtmlGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WX_APP_ID + "&secret=" + WX_APP_SECRET);
//        Map<String, String> map = JsonUtils.toMap(tokenInfo);
//        String jsapiInfo = HttpUtils.post("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + map.get("access_token") + "&type=jsapi", params);
//        Map<String, String> jsapiInfoMap = JsonUtils.toMap(jsapiInfo);
//        configMap.put("signature", createJsApiTicket(configMap, jsapiInfoMap.get("ticket"), request));
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
        params.put("out_trade_no", "ty81997382");
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
            Map<String, String> resMap = JsonUtils.toMap(res);
            return resMap.get("prepay_id");
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


    @RequestMapping("/test")
    public void test() {
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

        Map<String, String> params = new HashMap<>();
        params.put("appid", WX_APP_ID);
        params.put("mch_id", WX_MC_ID);
        params.put("nonce_str", "5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
        params.put("body", "1231231");
        params.put("out_trade_no", "81997382");
        params.put("notify_url", "http://dev.xingyifengji.com/pay/wx/trade/notify");
        params.put("total_fee", "1");
        params.put("trade_type", "JSAPI");
        params.put("spbill_create_ip", "127.0.0.1");
        params.put("openid", "ovS3gs4sIy7blJ5fKoS7RigOi8aY");

        String string1 = createSortStringByMap(params);
        String stringSignTemp = string1 + "&key=" + WX_APP_SECRET;

        params.put("sign", HashUtils.md5(stringSignTemp).toUpperCase());

        String xml = mapToXml(params);

        String res = HttpUtils.post(url, xml);
        if (StringUtils.isNotEmpty(res)) {
            Map<String, String> resMap = JsonUtils.toMap(res);
            System.err.println("ssss");
        }
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
