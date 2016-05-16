package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/29
 * Time: 下午12:10
 */
public enum BookIndexTypeEnums {
    hot(1,"热度指数"),
    develop(2,"开发指数"),
    propagate(3,"传播指数"),
    reputation(4,"口碑指数"),
    composite(5,"综合指数");
    private Integer type;

    private String Description;

    BookIndexTypeEnums(Integer type, String description) {
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
