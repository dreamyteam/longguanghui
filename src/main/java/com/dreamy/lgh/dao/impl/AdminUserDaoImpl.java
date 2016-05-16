package com.dreamy.lgh.dao.impl;

import com.dreamy.lgh.dao.BaseDaoImpl;
import com.dreamy.lgh.dao.iface.AdminUserDao;
import com.dreamy.lgh.domain.admin.AdminUser;
import com.dreamy.lgh.domain.admin.AdminUserConditions;
import com.dreamy.lgh.mapper.admin.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/8.
 */
@Repository("adminUserDao")
public class AdminUserDaoImpl extends BaseDaoImpl<AdminUser,Integer,AdminUserConditions> implements AdminUserDao {
    @Resource
    private AdminUserMapper adminUserMapper;
    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(adminUserMapper);
    }
}
