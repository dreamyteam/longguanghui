package com.dreamy.lgh.controllers.member;

import com.dreamy.lgh.controllers.LghController;
import com.dreamy.lgh.enums.MemberEnums;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/16
 * Time: 下午2:42
 */
@Controller
@RequestMapping("/member")
public class MemberController extends LghController {

    @RequestMapping("/list")
    public String list() {

        return "/member/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap map) {

        map.put("memberTypes", MemberEnums.values());
        return "/member/add";
    }
}
