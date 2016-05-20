package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/17
 * Time: 上午10:52
 */
public enum MemberStateEnums {
    new_add(0, "新建"),
    active(1, "有效"),
    out_of_date(2, "过期");

    private Integer status;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    MemberStateEnums(Integer status, String description) {
        this.status = status;
        this.description = description;
    }
}
