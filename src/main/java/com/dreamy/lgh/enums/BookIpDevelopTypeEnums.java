package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/11
 * Time: 下午4:10
 */
public enum BookIpDevelopTypeEnums {
    file(1, "电影"),
    play(2, "电视剧"),
    mobile_game(3, "手游"),
    game(4, "游戏"),
    around(5, "周边"),
    other(6, "其他");

    private Integer type;
    private String description;

    BookIpDevelopTypeEnums(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

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
}
