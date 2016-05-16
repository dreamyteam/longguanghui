package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/4
 * Time: 下午4:12
 */
public enum SougouEnums {
    sohu(1, "搜狐"),
    tenxun(2, "腾讯"),
    sina(3, "新浪"),
    ifeng(4, "凤凰"),
    wangyi(5, "网易");
    private Integer type;

    private String Description;

    SougouEnums(Integer type, String description) {
        this.type = type;
        Description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
