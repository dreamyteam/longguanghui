package com.dreamy.lgh.enums;

/**
 * Created by wangyongxing on 16/4/29.
 */
public enum KeyWordEnums {

    baidu(1, "百度", 0.16),
    so(2, "360", 0.12),
    weibo(3, "微博", 0.52),
    weixin(4, "微信文章", 0.3);
    private Integer type;

    private String description;

    private double percent;

    KeyWordEnums(Integer type, String description, Double percent) {
        this.type = type;
        this.description = description;
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
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
