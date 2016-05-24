package com.dreamy.lgh.controllers.setting;

import com.dreamy.lgh.controllers.LghController;
import com.dreamy.lgh.domain.sys.Setting;
import com.dreamy.lgh.enums.SysSettingEnums;
import com.dreamy.lgh.service.iface.ShortMessageService;
import com.dreamy.lgh.service.iface.VerificationCodeService;
import com.dreamy.lgh.service.iface.setting.SettingService;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.asynchronous.AsynchronousService;
import com.dreamy.utils.asynchronous.ObjectCallable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/22
 * Time: 下午5:14
 */
@Controller
@RequestMapping("/setting")
public class SettingController extends LghController {

    @Autowired
    private SettingService settingService;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private ShortMessageService shortMessageService;

    @RequestMapping("/service/phone")
    public String notePhone(ModelMap modelMap,
                            @RequestParam(value = "phone", defaultValue = "") String phone,
                            @RequestParam(value = "checkCode", defaultValue = "") String checkCode) {
        if (StringUtils.isNotEmpty(phone)) {
            Setting setting = new Setting().sysKey(SysSettingEnums.apply_note_phone.getKey()).value(phone);
            String checkCodeFromCache = verificationCodeService.getCodeFromCache(phone);
            modelMap.put("checkCode", checkCode);

            if (StringUtils.isNotEmpty(checkCode) && checkCodeFromCache.equals(checkCode)) {
                settingService.save(setting);
            }
        }

        modelMap.put("phone", phone);
        modelMap.put("checkCode", checkCode);
        return "/setting/phone";
    }

    @RequestMapping("/service/phone/code")
    public String getCheckCode(String phone) {
        AsynchronousService.submit(new ObjectCallable(phone) {
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
        return redirect("/setting/service/phone?checkCode=1&phone=" + phone);
    }

}
