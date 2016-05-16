package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/6
 * Time: 下午3:38
 */
public enum BookLevelEnums {
    one_class(1, "2%", 50.0),
    two_class(2, "8%", 12.5),
    three_class(3, "18%", 5.25),
    four_class(4, "32%", 3.125),
    five_class(5, "40%", 2.5),;
    private Integer level;
    private String description;
    private Double value;

    BookLevelEnums(Integer level, String description, Double value) {
        this.level = level;
        this.description = description;
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
