package com.dreamy.lgh.beans.params;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/16
 * Time: 下午5:17
 */
public class MemberParams {
    private Integer memberType;
    private Integer memberTime;
    private Integer addTime;

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public Integer getMemberType() {
        return memberType;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    public Integer getMemberTime() {
        return memberTime;
    }

    public void setMemberTime(Integer memberTime) {
        this.memberTime = memberTime;
    }
}
