package com.dreamy.lgh.domain.user;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/16
 * Time: 下午9:05
 */
public class UserWithMember {
    private User user;
    private Members members;
    private String  memberStateStr;
    private String levelStr;

    public String getLevelStr() {
        return levelStr;
    }

    public void setLevelStr(String levelStr) {
        this.levelStr = levelStr;
    }


    public String getMemberStateStr() {
        return memberStateStr;
    }

    public void setMemberStateStr(String memberStateStr) {
        this.memberStateStr = memberStateStr;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Members getMembers() {
        return members;
    }

    public void setMembers(Members members) {
        this.members = members;
    }
}
