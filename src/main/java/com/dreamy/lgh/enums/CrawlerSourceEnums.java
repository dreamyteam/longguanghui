package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/18
 * Time: 下午7:18
 */
public enum CrawlerSourceEnums {
    amazon(1, "亚马逊", "amazon", 0.1),
    jd(2, "京东", "jd", 0.16),
    dangdang(3, "当当", "dd", 0.24),
    douban(4, "豆瓣", "douban", 0.5);

    private Integer type;

    private String description;

    private String name;

    private Double percent;

    CrawlerSourceEnums(Integer type,String description,String name, Double percent) {
        this.type = type;
        this.percent = percent;
        this.name = name;
        this.description = description;
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
