package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/16
 * Time: 下午3:30
 */
public enum MemberEnums {
    vip(1, "VIP会员"),
    diamond(2, "钻石会员"),
    queue(3, "皇冠会员"),
    top(4, "顶级会员");
    private Integer type;
    private String description;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    MemberEnums(Integer type, String description) {

        this.type = type;
        this.description = description;
    }
}
