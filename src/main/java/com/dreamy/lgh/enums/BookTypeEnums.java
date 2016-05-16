package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/4
 * Time: 下午4:12
 */
public enum BookTypeEnums {
    chuban(1, "出版文学"),
    net(2, "网络文学");
    private Integer type;

    private String Description;

    BookTypeEnums(Integer type, String description) {
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
