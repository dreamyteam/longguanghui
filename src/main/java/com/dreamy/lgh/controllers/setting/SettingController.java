package com.dreamy.lgh.controllers.setting;

import com.dreamy.lgh.controllers.LghController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/22
 * Time: 下午5:14
 */
@Controller
@RequestMapping("/setting")
public class SettingController extends LghController{

    @RequestMapping("/service/phone")
    public String notePhone(String phone){

        return "";
    }

}
