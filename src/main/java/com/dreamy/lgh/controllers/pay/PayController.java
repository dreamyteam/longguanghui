package com.dreamy.lgh.controllers.pay;

import com.dreamy.lgh.controllers.LghController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/17
 * Time: 下午2:43
 */
@Controller
@RequestMapping("/pay")
public class PayController extends LghController {
    @Override
    public boolean checkLogin() {
        return false;
    }


    @RequestMapping("/wx")
    public String wx() {
        return "/pay/wxpay";
    }

}
