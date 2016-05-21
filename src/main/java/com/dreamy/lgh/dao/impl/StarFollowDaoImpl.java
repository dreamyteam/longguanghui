package com.dreamy.lgh.dao.impl;

import com.dreamy.lgh.dao.BaseDaoImpl;
import com.dreamy.lgh.dao.iface.StarFollowDao;
import com.dreamy.lgh.domain.star.StarFollow;
import com.dreamy.lgh.domain.star.StarFollowConditions;
import com.dreamy.lgh.mapper.star.StarFollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/21
 * Time: 下午1:37
 */
@Repository
public class StarFollowDaoImpl extends BaseDaoImpl<StarFollow, Integer, StarFollowConditions> implements StarFollowDao {

    @Autowired
    private StarFollowMapper starFollowMapper;

    @Override
    public void setBaseMapper() {
        super.setBaseMapper(starFollowMapper);
    }
}
