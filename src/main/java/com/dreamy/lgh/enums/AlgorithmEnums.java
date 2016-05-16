package com.dreamy.lgh.enums;

/**
 * Created by wangyongxing on 16/4/19.
 */
public enum AlgorithmEnums {

    hx(1, "用户画像"),
    zhzs(2, "综合指数"),
    rdzs(3, "热度指数"),
    qlzs(4, "潜力指数"),
    cbzs(5, "传播指数"),
    kbzs(6, "口碑指数");

    private Integer type;

    private String description;

    AlgorithmEnums(Integer type, String description) {
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
