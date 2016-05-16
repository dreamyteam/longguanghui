package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/28
 * Time: 下午4:18
 */
public enum IndexSourceEnums {
    baidu(1, "百度", "baidu", 0.5),
    s360(2, "360", "360", 0.35),
    weibo(3, "微博", "weibo", 0.15);

    private Integer type;

    private String description;

    private String name;

    private Double percent;

    IndexSourceEnums(Integer type, String description, String name, Double percent) {
        this.type = type;
        this.description = description;
        this.name = name;
        this.percent = percent;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
