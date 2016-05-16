package com.dreamy.lgh.enums;

/**
 * Created by wangyongxing on 16/4/25.
 */
public enum  CrawlerSourceNetBookEnums {
    qidian(1, "起点文学","qidian"),
    qdmm(2, "起点女生","qdmm"),
    zongheng(3, "纵横文学","zongheng"),
    tieba(4, "百度贴吧","tieba");

    private Integer type;

    private String description;

    private String name;

    CrawlerSourceNetBookEnums(Integer type, String description,String name) {
        this.type = type;
        this.description = description;
        this.name = name;
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
