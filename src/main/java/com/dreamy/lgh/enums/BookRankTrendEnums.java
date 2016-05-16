package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/6
 * Time: 下午4:45
 */
public enum BookRankTrendEnums {
    keep(0, "持平"),
    up(1, "上升"),
    down(2, "下降");

    private Integer type;
    private String description;

    BookRankTrendEnums(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {

        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
