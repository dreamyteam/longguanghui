package com.dreamy.lgh.dao.impl;

import com.dreamy.lgh.dao.BaseDaoImpl;
import com.dreamy.lgh.dao.iface.StarDao;
import com.dreamy.lgh.domain.star.Star;
import com.dreamy.lgh.domain.star.StarConditions;
import com.dreamy.lgh.mapper.star.StarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/21
 * Time: 下午1:18
 */
@Repository
public class StarDaoImpl extends BaseDaoImpl<Star, Integer, StarConditions> implements StarDao {

    @Autowired
    private StarMapper starMapper;

    @Override
    public void setBaseMapper() {
        super.setBaseMapper(starMapper);
    }
}
