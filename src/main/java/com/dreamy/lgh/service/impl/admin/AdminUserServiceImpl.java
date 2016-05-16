package com.dreamy.lgh.service.impl.admin;


import com.dreamy.beans.Page;
import com.dreamy.lgh.domain.admin.AdminUser;
import com.dreamy.lgh.service.iface.admin.AdminUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/8.
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {


    @Override
    public AdminUser getByUsername(String userName) {

        return null;
    }

    @Override
    public AdminUser getById(Integer userId) {
        return null;
    }

    @Override
    public List<AdminUser> getList(AdminUser adminUser, Page page) {
        return null;
    }

    @Override
    public void save(AdminUser adminUser, int roleId) {


    }

    @Override
    public void update(AdminUser adminUser, int roleId) {


    }

    @Override
    public int updatePassword(AdminUser adminUser) {
        return 0;
    }
}
