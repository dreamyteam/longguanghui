package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午8:19
 */
public enum QualificationEnums {
    zhuan_ke(1, "专科"),
    ben_ke(2, "本科"),
    shuo_shi(3, "硕士"),
    bo_shi(4, "博士"),
    extra(5, "其他");
    private Integer type;
    private String description;

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

    QualificationEnums(Integer type, String description) {
        this.type = type;
        this.description = description;
    }
}
