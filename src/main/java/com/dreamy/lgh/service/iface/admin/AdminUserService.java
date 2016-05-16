package com.dreamy.lgh.service.iface.admin;

import com.dreamy.beans.Page;
import com.dreamy.lgh.domain.admin.AdminUser;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/8.
 */
public interface AdminUserService {

    public AdminUser getByUsername(String userName);

    public  AdminUser getById(Integer userId);

    public List<AdminUser> getList(AdminUser adminUser, Page page);

    public void save(AdminUser adminUser, int roleId);

    public void update(AdminUser adminUser, int roleId);

    public int updatePassword(AdminUser adminUser);

}
