package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/22
 * Time: 下午5:30
 */
public enum SysSettingEnums {
    apply_note_phone("apply_note_phone", "会员提交申请后的通知手机");

    private String key;


    private String description;

    SysSettingEnums(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
