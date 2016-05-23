package com.dreamy.lgh.dao.impl;

import com.dreamy.lgh.dao.BaseDaoImpl;
import com.dreamy.lgh.dao.iface.SettingDao;
import com.dreamy.lgh.domain.sys.Setting;
import com.dreamy.lgh.domain.sys.SettingConditions;
import com.dreamy.lgh.mapper.sys.SettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/22
 * Time: 下午5:25
 */
@Repository
public class SettingDaoImpl extends BaseDaoImpl<Setting,Integer,SettingConditions> implements SettingDao  {

    @Autowired
    private SettingMapper mapper;

    @Autowired

    @Override
    public void setBaseMapper() {
        super.setBaseMapper(mapper);
    }
}
