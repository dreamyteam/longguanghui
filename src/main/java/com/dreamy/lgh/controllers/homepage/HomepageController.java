package com.dreamy.lgh.controllers.homepage;

import com.dreamy.lgh.controllers.LghController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/13
 * Time: 下午6:40
 */
@Controller
@RequestMapping(value = {"", "/"})
public class HomepageController extends LghController {
    @RequestMapping("")
    public String index() {
        return "/homepage/login";
    }
}
