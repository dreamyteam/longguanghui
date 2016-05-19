package com.dreamy.lgh.controllers.wx;

import com.dreamy.lgh.controllers.LghController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/19
 * Time: 下午1:15
 */
@Controller
@RequestMapping("/wx")
public class WxController extends LghController{

    @Override
    public boolean checkLogin() {
        return false;
    }

    @RequestMapping(value = {"","/"})
    public String index(){

        return "";
    }
}
