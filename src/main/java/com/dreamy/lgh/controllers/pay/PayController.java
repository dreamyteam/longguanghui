package com.dreamy.lgh.controllers.pay;

import com.dreamy.lgh.beans.UserSession;
import com.dreamy.lgh.beans.WxUser;
import com.dreamy.lgh.controllers.LghController;
import com.dreamy.lgh.domain.user.Members;
import com.dreamy.lgh.domain.user.Orders;
import com.dreamy.lgh.domain.user.User;
import com.dreamy.lgh.enums.MemberEnums;
import com.dreamy.lgh.service.iface.member.MemberService;
import com.dreamy.lgh.service.iface.order.OrderService;
import com.dreamy.lgh.service.iface.user.UserService;
import com.dreamy.lgh.service.iface.wx.WxService;
import com.dreamy.utils.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
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
    private final static Logger log = LoggerFactory.getLogger(PayController.class);


    @Autowired
    private UserService userService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private WxService wxService;

    @Autowired
    private OrderService orderService;


    @Override
    public boolean checkLogin() {
        return false;
    }


    @RequestMapping("/wx")
    public String wx(ModelMap modelMap, HttpServletRequest request,
                     @RequestParam(value = "type", defaultValue = "0") Integer type) {
        modelMap.put("type", type);
        String action = request.getParameter("action");

        UserSession userSession = userSessionContainer.get(getUserSessionId(request));
        if (userSession != null && userSession.getUserId() > 0) {
            Integer userId = userSession.getUserId();

            User user = userService.getUserById(userId);
            Members members = memberService.getByUserId(userId);
            modelMap.put("user", user);
            modelMap.put("member", members);

            if (action != null) {
                if (type == 1) {
                    if (members.getType() == 0) {
                        modelMap.put("config", wxService.getPayConfig(request, members));
                    }
                }

                return "/pay/wxpay";
            }

            if (members.getType() == 0) {
                return redirect("/pay/choose?type=" + type);
            } else {
                return redirect("/user/center");
            }
        } else {
            String code = request.getParameter("code");
            if (StringUtils.isNotEmpty(code)) {
                Map<String, String> tokenMap = wxService.getWxTokenInfo(code);
                if (!tokenMap.containsKey("errcode")) {
                    String accessToken = tokenMap.get("access_token");
                    String openId = tokenMap.get("openid");

                    WxUser wxUser = wxService.getWxUserInfoByAccessTokenAndOpenId(accessToken, openId);
                    if (wxUser != null) {
                        Members currentMember = memberService.getByOpenId(openId);
                        User user;
                        if (currentMember == null) {
                            user = userService.saveByWx(wxUser);
                            wxUser.setType(type);
                            memberService.saveByWx(wxUser, user.getId());
                        } else {
                            user = userService.getUserById(currentMember.getUserId());
                        }

                        userSession = getUserSession(request);
                        userSession.setUserId(user.getId());
                        userSession.setUsername(user.getUserName());
                        userSessionContainer.set(getUserSessionId(request), userSession);

                        if (currentMember != null) {
                            if (currentMember.getType() == 0) {
                                return redirect("/pay/choose?type=" + type);
                            } else {
                                return redirect("/user/center");
                            }
                        }
                    }
                }
            }

            return redirect("/pay/choose?type=" + type);
        }
    }

    @RequestMapping("/wx/trade/notify")
    public void recieveNotify(HttpServletResponse response, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        try {
            InputStream inputStream = request.getInputStream();
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            List<Element> elementList = root.elements();
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
            }
            inputStream.close();
        } catch (Exception e) {
            log.error("wx notify error:" + JsonUtils.toString(map), e);
        }

        if (CollectionUtils.isNotEmpty(map)) {
            if (map.containsKey("result_code") && map.containsKey("return_code")) {
                if (map.get("result_code").equals("SUCCESS") && map.get("return_code").equals("SUCCESS")) {
                    wxService.handleNotifyDataFroWx(map);
                } else {
                    log.error("wx  notify error and the map is: " + JsonUtils.toString(map));
                }
            }
        }

        interfaceReturn(response, "SUCCESS", "");
    }

    @RequestMapping("/choose")
    public String chooseMember(ModelMap modelMap, @RequestParam(value = "type", defaultValue = "1") String type) {

        modelMap.put("type", type);
        return "/pay/choose";
    }

    @RequestMapping("/choose/detail")
    public String memberDetail(ModelMap modelMap, @RequestParam(value = "type", defaultValue = "1") Integer type) {
        return "/pay/detail";
    }

    @RequestMapping("/result")
    public String result(ModelMap modelMap, HttpServletRequest request, @RequestParam(value = "prepayOrderId", required = false) String orderId,
                         @RequestParam(value = "orderStatus", defaultValue = "0") String status) {

        String message;
        Integer type = 0;
        String memberTypeStr = "";

        UserSession userSession = userSessionContainer.get(getUserSessionId(request));
        if (userSession != null && userSession.getUserId() >= 0) {
            Members members = memberService.getByUserId(userSession.getUserId());
            type = members.getType();
            if (type.equals(1)) {
                Orders orders = orderService.getByOrderIdAndWxId(members.getWxOrderId(), members.getWxId());
                if (orders != null) {
                    modelMap.put("fee", Long.parseLong("" + orders.getTotalFee()) / 100);
                    message = "支付成功";
                } else {
                    message = "支付失败";
                }
            } else {
                message = "申请成功";
            }

            MemberEnums[] enums = MemberEnums.values();
            for (MemberEnums memberEnums : enums) {
                if (members.getType().equals(memberEnums.getType())) {
                    memberTypeStr = memberEnums.getDescription();
                }
            }

            modelMap.put("memberType", memberTypeStr);
            modelMap.put("time", TimeUtils.toString(
                    "yyyy-MM-dd HH:mm:ss", members.getStartedAt()));
        } else {
            message = "操作失败，请返回微信从新进入";
        }

        modelMap.put("type", type);
        modelMap.put("message", message);
        return "/pay/result";
    }
}
