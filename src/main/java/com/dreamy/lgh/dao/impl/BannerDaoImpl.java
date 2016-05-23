package com.dreamy.lgh.dao.impl;

import com.dreamy.lgh.dao.BaseDaoImpl;
import com.dreamy.lgh.dao.iface.BannerDao;
import com.dreamy.lgh.domain.banner.Banner;
import com.dreamy.lgh.domain.banner.BannerConditions;
import com.dreamy.lgh.mapper.banner.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/23
 * Time: 下午2:33
 */
@Repository
public class BannerDaoImpl extends BaseDaoImpl<Banner, Integer, BannerConditions> implements BannerDao {

    @Autowired
    private BannerMapper mapper;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setBaseMapper(mapper);
    }
}
