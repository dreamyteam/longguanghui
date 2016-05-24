package com.dreamy.lgh.controllers.member;

import com.dreamy.beans.Page;
import com.dreamy.lgh.beans.InterfaceBean;
import com.dreamy.lgh.beans.params.MemberParams;
import com.dreamy.lgh.beans.params.RegisterParams;
import com.dreamy.lgh.controllers.LghController;
import com.dreamy.lgh.domain.user.Members;
import com.dreamy.lgh.domain.user.User;
import com.dreamy.lgh.domain.user.UserWithMember;
import com.dreamy.lgh.enums.ErrorCodeEnums;
import com.dreamy.lgh.enums.MemberEnums;
import com.dreamy.lgh.enums.MemberStateEnums;
import com.dreamy.lgh.service.iface.ShortMessageService;
import com.dreamy.lgh.service.iface.VerificationCodeService;
import com.dreamy.lgh.service.iface.member.MemberService;
import com.dreamy.lgh.service.iface.user.RegisterService;
import com.dreamy.lgh.service.iface.user.UserService;
import com.dreamy.utils.*;
import com.dreamy.utils.asynchronous.AsynchronousService;
import com.dreamy.utils.asynchronous.ObjectCallable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/16
 * Time: 下午2:42
 */
@Controller
@RequestMapping("/member")
public class MemberController extends LghController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private ShortMessageService shortMessageService;


    @RequestMapping("/list")
    public String list(HttpServletResponse response, Page page, ModelMap map,
                       @RequestParam(value = "userName", required = false) String userName,
                       @RequestParam(value = "mobile", required = false) String phone) {
        List<UserWithMember> userWithMemberList = memberService.getByPageAndUserNameAndPhone(page, userName, phone);

        map.put("userWithMemberList", userWithMemberList);
        map.put("page", page);
        return "/member/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap map) {

        map.put("memberTypes", MemberEnums.values());
        return "/member/add";
    }

    @RequestMapping("/doAdd")
    public String doAdd(HttpServletResponse response, RegisterParams registerParams, MemberParams memberParams) {

        InterfaceBean bean = new InterfaceBean().success();
        ErrorCodeEnums errorCodeEnums = registerService.checkAddMemberParam(registerParams);
        if (errorCodeEnums.getErrorCode() > 0) {
            bean.failure(errorCodeEnums);
        } else {
            User user = userService.getUserByMobile(registerParams.getMobile());
            if (user.getId() == null) {
                user.phone(registerParams.getMobile());
                user.userName(registerParams.getUserName());
                user.userKey(registerService.createUserKey(registerParams));
                user.sex(registerParams.getSex());
                user.birthday(TimeUtils.getDateByStr(registerParams.getBirthday(), "yyyy-MM-dd"));
                user.address(registerParams.getAddress());

                Integer res = userService.save(user);
                if (res > 0) {
                    Date currentDate = new Date();
                    Integer userId = user.getId();
                    Members members = new Members();
                    members.userId(userId).type(memberParams.getMemberType()).startedAt(currentDate);

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(currentDate);
                    calendar.add(Calendar.YEAR, memberParams.getMemberTime());

                    currentDate = calendar.getTime();
                    members.endedAt(currentDate);
                    memberService.save(members);

                    AsynchronousService.submit(new ObjectCallable(registerParams.getMobile()) {
                        @Override
                        public Object run() throws Exception {
                            String code = verificationCodeService.createVerificationCode(4);
                            if (StringUtils.isNotEmpty(code)) {
                                verificationCodeService.saveCodeToCache(name, code);
                                shortMessageService.send(name, "【龙光汇】您的验证码是" + code);
                            }

                            return null;
                        }
                    });
                    return redirect("/member/list");
                }
            }
        }

        return redirect("/member/add?error=" + HttpUtils.encodeUrl(JsonUtils.toString(bean)));
    }

    @RequestMapping("/apply")
    public String apply(HttpServletResponse response, Page page, ModelMap map,
                        @RequestParam(value = "userName", required = false) String userName,
                        @RequestParam(value = "mobile", required = false) String phone) {
        List<UserWithMember> userWithMemberList = memberService.getApplyListByPageAndUserNameAndPhone(page, userName, phone);

        map.put("userWithMemberList", userWithMemberList);
        map.put("page", page);

        return "/member/applies";
    }

    @RequestMapping("/apply/pass")
    public String pass(HttpServletResponse response, @RequestParam(value = "userId") Integer userId) {
        final Members members = memberService.getByUserId(userId);
        if (members != null) {
            members.status(MemberStateEnums.active.getStatus());
            memberService.updateByRecord(members);

            User user = userService.getUserById(userId);

            if (StringUtils.isNotEmpty(user.getPhone())) {
                shortMessageService.passNote(members);
            }
            return redirect("/member/apply");
        }

        return null;
    }

    @RequestMapping("/detail")
    public String detail(ModelMap modelMap, @RequestParam(value = "userId") Integer userId) {
        Members members = memberService.getByUserId(userId);
        User user = userService.getUserById(userId);


        long activeTime = TimeUtils.diff(new Date(), members.getEndedAt());
        Integer activeDays = 0;
        if (activeTime > 0) {
            activeDays = (int) (activeTime / (24 * 60 * 60 * 1000));
        }

        Integer a = TimeUtils.getCalendar(members.getStartedAt()).getWeekYear();
        Integer b = TimeUtils.getCalendar(members.getEndedAt()).getWeekYear();

        modelMap.put("activeDays", activeDays);
        modelMap.put("user", user);
        modelMap.put("member", members);
        modelMap.put("memberTypes", MemberEnums.values());
        modelMap.put("year", b - a);

        return "/member/detail";
    }

    @RequestMapping("/update")
    public String update(RegisterParams params, MemberParams memberParams, @RequestParam(value = "userId") Integer userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            Members members = memberService.getByUserId(userId);
            if (members != null) {
                user.userName(params.getUserName())
                        .sex(params.getSex())
                        .phone(params.getMobile())
                        .birthday(TimeUtils.getDateByStr(params.getBirthday(), "yyyy-MM-dd"))
                        .address(params.getAddress());
                userService.updateByRecord(user);

                if (memberParams.getAddTime() > 0) {

                    Calendar calendar = Calendar.getInstance();
                    if (memberParams.getMemberType().equals(members.getType())) {
                        calendar.setTime(members.getEndedAt());
                    } else {
                        Date currentDate = new Date();
                        members.startedAt(currentDate);
                        calendar.setTime(currentDate);
                    }

                    members.type(memberParams.getMemberType());
                    calendar.add(Calendar.YEAR, memberParams.getAddTime());
                    Date endDate = calendar.getTime();
                    members.endedAt(endDate);

                    memberService.updateByRecord(members);
                }

                return redirect("/member/list");
            }
        }

        return null;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void delete(HttpServletResponse response, @RequestParam(value = "userId") Integer userId) {
        InterfaceBean bean = new InterfaceBean().success();
        Members members = memberService.getByUserId(userId);
        if (members != null) {
            members.status(MemberStateEnums.deleted.getStatus());
            memberService.updateByRecord(members);
        } else {
            bean.failure(ErrorCodeEnums.update_profile_failed.getErrorCode(), "删除失败");
        }

        interfaceReturn(response, JsonUtils.toString(bean), "");
    }
}
