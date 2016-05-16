/**
 * LSLive User
 * v2.2
 */
LSUserMini = function () {
    this.MAX_UPLOAD = '2mb';
    this.OUT_TIME = 5;
};
LSUserMini.prototype = {
    //校验手机号
    isMPhone: function (phoneNumber) {
        var reg_MPnum = /^0{0,1}(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])[0-9]{8}$/;
        var isMPhone = true;
        if (phoneNumber == "" || !reg_MPnum.test(phoneNumber)) {
            isMPhone = false;
        }
        return isMPhone;
    },
    isNickname: function (nickname) {
        var regNickName = /^[\u4E00-\u9FA5]{1,5}$/;
        var isNickname = true;
        if (!regNickName.test(nickname)) {
            isNickname = false;
        }
        ;
        return isNickname;
    },
    inputTips: function () {
        $('.login-one input').focus(function () {
                var tips = $(this).data('tips');
                $(this).addClass('on');
                $(this).parent().find('p .infoArea').removeClass('error');
                $(this).parent().find('p .infoArea').html(tips);
                $(this).parent().find('p .infoArea').show();
            })
            .blur(function () {
                $(this).removeClass('on');
                $(this).parent().find('p .infoArea').removeClass('error');
                $(this).parent().find('p .infoArea').html('');
            });
    },
    /**
     * 刷新验证码
     * @param CaptchaImageID  验证码显示的图片ID
     */
    refreshCaptchaImage: function (CaptchaImageID) {
        var sSrc = $('#' + CaptchaImageID).attr('data-apiurl').split('?');
        var d = new Date();
        $('#' + CaptchaImageID).attr('src', sSrc[0] + '?t=' + d.getTime());
    },
    /**
     * 发送短信验证码
     * @param sendObj 发送按钮对象.如: '.sendCode'
     * @param telPhoneObj 手机号对象.如: '.telPhone'
     * @param tipsObj 提示信息对象.如:'.tips',若为null 则使用xbox.tips提示
     * @param sendTime 发送间隔
     * @returns {boolean}
     */
    sendSMSRcode: function (sendObj, telPhoneObj, tipsObj, sendTime) {
        var lsuser = new LSUserMini();
        var telphone = $(telPhoneObj).val() || '';
        var API_URL = $.trim($(sendObj).data('apiurl')) || null;
        var isRcode = $.trim($(sendObj).data('rcode')) || 0;
        var captchaCode = '';

        if (API_URL == '' || API_URL == undefined) {
            $.xbox.tips('验证码接口配置错误！');
            return false;
        }
        if ($(sendObj).hasClass('disabled')) {
            return false;
        }
        if (lsuser.isMPhone(telphone)) {
            if (isRcode==1) {
                $.xbox.showMask();
                $('#captchaInput').val('');
                $('#captchaImage').click();
                $('.RcodeBox').show();
                $('#wrongText').text('');

                $('#checkRcode,#captchaImage, #changeCaptchaImage,#cancelBtn').unbind('click');
                $('#checkRcode').html('确定').addClass("fl");
                $('#cancelBtn').html('取消').show();
                //刷新验证码
                $('#captchaImage, #changeCaptchaImage').on('click',function(){
                    lsuser.refreshCaptchaImage('captchaImage');
                });
                //关闭发送站内验证码
                $('#cancelBtn').on('click',function(){
                    $('#xbox_mask_div').remove();
                    $('.RcodeBox').hide();
                });
                //检查输入的验证码
                $('#checkRcode').on('click',function(){
                    captchaCode = $.trim($('#captchaInput').val()) || '';
                    if (captchaCode=='' ||captchaCode.length>6) {
                        $('#wrongText').text('验证码输入错误');
                    }else{
                        $('#wrongText').text('');
                        $(telPhoneObj).attr("disabled", true).addClass("disabled");
                        lsuser.sendSMS(telPhoneObj,sendObj,sendTime,tipsObj,API_URL,telphone,captchaCode);
                    }
                });
            }else{
                $(telPhoneObj).attr("disabled", true).addClass("disabled");
                lsuser.sendSMS(telPhoneObj,sendObj,sendTime,tipsObj,API_URL,telphone,captchaCode);
            }
        } else {
            if(tipsObj==null){
                $.xbox.tips('请输入正确的手机号');
            }else{
                $(tipsObj).addClass('error').text("请输入正确的手机号").show();
            }
            return false;
        }
    },
    /**
     * 发送短信
     * @param telPhoneObj  手机号码输入框
     * @param sendObj   发送的对象
     * @param sendTime  发送间隔时间
     * @param tipsObj   提示对象
     * @param API_URL   短信接口地址
     * @param telphone  发送的手机号
     * @param captchaCode   验证码
     */
    sendSMS: function (telPhoneObj, sendObj, sendTime, tipsObj, API_URL, telphone, captchaCode) {
        var t_dt = null;
        var sendWait = sendTime;
        function dtime() {
            if (sendWait == 0) {
                $(telPhoneObj).attr("disabled", false).removeClass("disabled");
                $(sendObj).attr("disabled", false).removeClass('disabled').text("发送验证码");
                if(tipsObj!=null){
                    $(tipsObj).text('').addClass('error').hide();
                }
                sendWait = sendTime;
            } else {
                $(telPhoneObj).attr("disabled", true).addClass("disabled");
                $(sendObj).attr("disabled", true).addClass('disabled').text("重新发送(" + sendWait + ")");
                sendWait--;
                t_dt = setTimeout(function () {
                    dtime()
                }, 1000);
            }
        }
        dtime();
        $.ajax({
            type: "POST",
            url: API_URL,
            data: {telphone: telphone,captchaCode: captchaCode},
            dataType: "json",
            success: function (data) {
                if (data.status == '1') {
                    if(tipsObj==null){
                        $.xbox.tips('短信发送成功，请注意查收！');
                    }else{
                        $(tipsObj).text('短信发送成功，请注意查收！').show();
                    }
                    $('#cancelBtn').click();
                } else {
                    $('#wrongText').text(data.failed);
                    $('#captchaImage').click();
                    clearTimeout(t_dt);
                    $(telPhoneObj).attr("disabled", false).removeClass('disabled');
                    $(sendObj).attr("disabled", false).removeClass('disabled').text("发送验证码");
                    return false;
                }
            }
        });
    },
    LoginEnd: function (data) {
        if(data.ftype=='reg'){
            $('.popup-box, .popup-bg').hide();
            $(".popup-common,.popup-bg").show();
            $(".popup-common .popup-text").html("注册成功");
            //$(".popup-common .popup-text").html("注册成功<br/><br/>奖品已到账，请登录蓝鲨APP到个人中心领取");
            $(".btn-true").html("下载APP领取").addClass('fl');
            $(".btn-false").html("关闭");
            $(".btn-true, .btn-false").show();
            $(".btn-true").on("click", function () {
                window.location.href = DOWN_URL;
            });
            $(".btn-false").on("click", function () {
                //$(".popup-common,.popup-bg").hide();
                window.location.href = data.url;
            });
            $('.popup-common .popup-close').on('click',function(){
                window.location.href = data.url;
            });
        }else{
            window.location.href = data.url;
        }
    }
    //end
}
/**
 * 注册验证
 */
LSUserMini.register = function () {
    _this = this;
    _this.sendTime = 60;
    _this.OUT_TIME = 1;
};
LSUserMini.register.prototype = {
    init: function () {
        var lsuser = new LSUserMini;
        this.op();
    },
    op: function () {
        var _this = this;
        var lsuser = new LSUserMini;
        lsuser.inputTips();
        $('#gendercol .checkbox').on('click', function () {
            $('#gendercol .checkbox').removeClass('checked');
            $(this).addClass('checked');
            $('#gender').val($(this).data('value'));
        });
        //发送验证码
        $("#SendCode").on("click", function () {
        	lsuser.refreshCaptchaImage('captchaImage');
            lsuser.sendSMSRcode('#SendCode', '#miniTel', null, _this.sendTime);
        });
        //提交按钮
        $('#regsubmitMini').on('click', function () {
            var API_URL = $('#frmUserRegMini').attr('action');
            var theBtn = $(this);
            if (API_URL == '' || API_URL == undefined) {
                $.xbox.tips('API_URL配置错误！');
                return false;
            }
            ;
            if (theBtn.hasClass('disabled')) {
                return false;
            }
            ;
            $('#miniTel1').val($.trim($('#miniTel').val()));
            if (_this.check()) {
                theBtn.addClass('disabled');
                /** 验证通过 */
                    //md5 加密密码
                $('#pwdrehide').val($.md5($.trim($('#miniRePassword').val())));
                $.ajax({
                    type: "POST",
                    url: API_URL,
                    data: $('#frmUserRegMini').serialize(),
                    dataType: "json",
                    success: function (data) {
                        if (data.status == '1') {
                            //$.xbox.tips("恭喜你，注册成功！");
                            //登录后操作
                            lsuser.LoginEnd(data);
                        } else {
                            $.xbox.tips(data.failed);
                            theBtn.removeClass('disabled');
                            return false;
                        }
                    }
                });
            } else {
                theBtn.removeClass('disabled');
                return false;
            }
        });
    },
    //提交检测
    check: function () {
        var _this = this;
        var isPass = true;
        var lsusermini = new LSUserMini;
        var telphone = $.trim($('#miniTel').val());
        var nickname = $.trim($('#regNickname').val());
        var checkTel = lsusermini.isMPhone(telphone);
        var password = $.trim($("#miniPassword").val());
        var password2 = $.trim($("#miniRePassword").val());
        if ($.trim($("#rcode").val()) == "") {
            $(".iRcodeMini").addClass('error').text('请输入手机验证码').show();
            isPass = false;
        } else {
            $(".iRcodeMini").text('').hide();
        }
        if (telphone == '') {
            $('.miniiMPTEL').addClass('error').text("请输入您的手机号").show();
            isPass = false;
        } else if (!checkTel) {
            $('.miniiMPTEL').addClass('error').text("请输入正确的手机号").show();
            isPass = false;
        } else {
            $('.miniiMPTEL').removeClass('error').text("").hide();
        }
        if (password.length < 6 || password.length > 16) {
            $(".iPasswordM").addClass('error').text('输入密码有误，请输入6～16位的密码').show();
            isPass = false;
        } else {
            $(".iPasswordM").text('').hide();
        }
        if (password2 != password) {
            $(".iPasswordM2").addClass('error').text("输入密码不一致").show();
            isPass = false;
        } else {
            $(".iPasswordM2").text('').hide();
        }
        return isPass;
    }
}
/**
 * 登录验证
 */
LSUserMini.login = function () {
    this.OUT_TIME = 1;
};
LSUserMini.login.prototype = {
    init: function () {
        this.op();
    },
    op: function () {
        var _this = this;
        var lsuser = new LSUserMini;
        $('.login-list input').focus(function () {
                var tips = $(this).data('tips');
                $(this).addClass('on');
                $(this).parent().find('p .infoArea').removeClass('error');
                $(this).parent().find('p .infoArea').html(tips);
                $(this).parent().find('p .infoArea').show();
            })
            .blur(function () {
                $(this).removeClass('on');
                $(this).parent().find('p .infoArea').removeClass('error');
                $(this).parent().find('p .infoArea').html('');
            });
        $('#frmSubmitMini').on('click', function () {
            var API_URL = $.trim($('#frmUserLoginMini').attr('action'));
            var theBtn = $(this);
            if (API_URL == '' || API_URL == undefined) {
                $.xbox.tips('API_URL配置错误！');
                return false;
            }
            ;
            if (theBtn.hasClass('disabled')) {
                return false;
            }
            ;
            if (_this.check()) {
                theBtn.addClass('disabled');
                /** 验证通过 */
                    //md5 加密密码
                $('#pwdhidelogin').val($.md5($.trim($('#loginMinpassword').val())));
                $.ajax({
                    type: "POST",
                    url: API_URL,
                    data: $('#frmUserLoginMini').serialize(),
                    dataType: "json",
                    success: function (data) {
                        if (data.status == '1') {
                            $.xbox.tips('恭喜您，登录成功！');
                            //登录后操作
                            lsuser.LoginEnd(data);
                        } else {
                            $.xbox.tips(data.failed);
                            theBtn.removeClass('disabled');
                            return false;
                        }
                    }
                });
            } else {
                theBtn.removeClass('disabled');
                return false;
            }
        });
    },
    check: function () {
        var _this = this;
        var isPass = true;
        var lsuser = new LSUserMini;
        var telphone = $.trim($('#loginMinTel').val());
        var password = $.trim($('#loginMinpassword').val());
        var checkTel = lsuser.isMPhone(telphone);
        if (telphone == '') {
            $('.loginiMPTEL').addClass('error').text("请输入您的手机号").show();
            isPass = false;
        } else if (!checkTel) {
            $('.loginiMPTEL').addClass('error').text("请输入正确的手机号").show();
            isPass = false;
        } else {
            $('.loginiMPTEL').removeClass('error').text("").hide();
        }
        if (password == '') {
            $('.loginiPassword').addClass('error').text("请输入您的密码").show();
            isPass = false;
        } else if (password.length < 6 || password.length > 16) {
            $('.loginiPassword').addClass('error').text("请输入正确密码（6-16位）").show();
            isPass = false;
        } else {
            $('.loginiPassword').removeClass('error').text("").hide();
        }
        return isPass;
    }
}
function loginShow() {
    $(".popup-slide span:last-child").addClass("cur").siblings().removeClass("cur");
    $(".popup-box,.popup-bg,.popup-login").show();
    $(".popup-reg").hide();
    $(".popup-box").css("marginTop", -($(".popup-box").height() / 2));
}
function regShow() {
    $(".popup-slide span:first-child").addClass("cur").siblings().removeClass("cur");
    $(".popup-box,.popup-bg,.popup-reg").show();
    $(".popup-login").hide();
    $(".popup-box").css("marginTop", -($(".popup-box").height() / 2));
}
function closeLoginRegBox() {
    $(".popup-box,.popup-bg,.popup-common").hide();
}
$(".popup-box").css("marginTop", -($(".popup-box").height() / 2));
$(".popup-slide span").on("click", function () {
    var flag = $(this).data("slide");
    $(".popup-slide ." + flag).removeClass("cur");
    $(this).addClass("cur").siblings().removeClass("cur");
    if (flag == "login") {
        $(".popup-login").show();
        $(".popup-reg").hide();
        $(".popup-box").css("marginTop", -($(".popup-box").height() / 2));
    } else {
        $(".popup-login").hide();
        $(".popup-reg").show();
        $(".popup-box").css("marginTop", -($(".popup-box").height() / 2));
    }
})
$(".popup-close").on("click", function () {
    closeLoginRegBox();
})
//调用
$(function () {
    new LSUserMini.login().init();
    new LSUserMini.register().init();
    //调用如下 登录框.loginShow() 注册框.regShow()
    $("#login,.popup-reg .login-tips a").on("click", function () {
        loginShow();
    })
    $("#reg,.popup-login .login-tips a").on("click", function () {
        regShow();
    })
});