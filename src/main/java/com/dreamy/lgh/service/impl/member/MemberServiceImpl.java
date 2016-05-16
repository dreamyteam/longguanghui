package com.dreamy.lgh.service.impl.member;

import com.dreamy.lgh.dao.iface.MemberDao;
import com.dreamy.lgh.domain.user.Members;
import com.dreamy.lgh.service.iface.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/16
 * Time: 下午7:44
 */
@Service
public class MemberServiceImpl  implements MemberService{

    @Autowired
    private MemberDao memberDao;

    @Override
    public Integer save(Members members) {
        return memberDao.save(members);
    }

    @Override
    public Members getByUserId(Integer userId) {
        return null;
    }
}
