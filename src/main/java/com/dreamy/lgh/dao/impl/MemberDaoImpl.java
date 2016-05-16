package com.dreamy.lgh.dao.impl;

import com.dreamy.lgh.dao.BaseDaoImpl;
import com.dreamy.lgh.dao.iface.MemberDao;
import com.dreamy.lgh.domain.user.Members;
import com.dreamy.lgh.domain.user.MembersConditions;
import com.dreamy.lgh.mapper.user.MembersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/16
 * Time: 下午7:49
 */
@Repository
public class MemberDaoImpl extends BaseDaoImpl<Members, Integer, MembersConditions> implements MemberDao {

    @Autowired
    private MembersMapper mapper;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setBaseMapper(mapper);
    }
}
