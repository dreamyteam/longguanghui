package com.dreamy.lgh.service.iface.member;

import com.dreamy.beans.Page;
import com.dreamy.lgh.beans.WxUser;
import com.dreamy.lgh.domain.user.Members;
import com.dreamy.lgh.domain.user.UserWithMember;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/16
 * Time: 下午7:44
 */
public interface MemberService {
    /**
     * @param members
     * @return
     */
    Integer save(Members members);

    /**
     *
     * @param wxUser
     * @return
     */
    Members saveByWx(WxUser wxUser,Integer userId);

    /**
     * @param userId
     * @return
     */
    Members getByUserId(Integer userId);

    /**
     * @param userId
     * @return
     */
    Members getByOpenId(String openId);

    /**
     * @param page
     * @param order
     * @return
     */
    List<Members> getByPageAndOrder(Page page, String order);

    /**
     * @param page
     * @param userName
     * @return
     */
    List<UserWithMember> getByPageAndUserNameAndPhone(Page page, String userName, String phone);

    /**
     * @param userIds
     * @return
     */
    List<Members> getMemberByUserIds(List<Integer> userIds);

    /**
     * @param membersList
     * @return
     */
    Map<Integer, Members> getUserIdAndMemberMapByMemberList(List<Members> membersList);
}
