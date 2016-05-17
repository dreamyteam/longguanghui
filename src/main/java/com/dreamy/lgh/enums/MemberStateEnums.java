package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/17
 * Time: 上午10:52
 */
public enum MemberStateEnums {
    active("有效"),
    out_of_date("过期");
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    MemberStateEnums(String description) {
        this.description = description;
    }
}
