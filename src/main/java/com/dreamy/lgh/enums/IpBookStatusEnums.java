package com.dreamy.lgh.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/21
 * Time: 下午7:21
 */
public enum IpBookStatusEnums {
    waitting(1, "等待抓取"),
    starting(2, "抓取中"),
    finished(3, "抓取完成"),
    exception(4, "异常");

    private Integer status;

    private String description;

    IpBookStatusEnums(Integer status, String description) {
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
