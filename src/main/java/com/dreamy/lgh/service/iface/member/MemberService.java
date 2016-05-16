package com.dreamy.lgh.service.iface.member;

import com.dreamy.lgh.domain.user.Members;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/16
 * Time: 下午7:44
 */
public interface MemberService {
    /**
     *
     * @param members
     * @return
     */
    Integer save(Members members);

    /**
     *
     * @param userId
     * @return
     */
    Members getByUserId(Integer userId);
}
