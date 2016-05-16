package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/29
 * Time: 下午3:02
 */
public enum BookIndexStatusEnums {
    waitting(0, "等待抓取"),
    starting(1, "抓取中"),
    finished(2, "抓取完成"),
    exception(3, "异常");

    private Integer status;

    private String description;

    BookIndexStatusEnums(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
