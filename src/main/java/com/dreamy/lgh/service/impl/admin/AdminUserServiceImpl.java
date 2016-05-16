package com.dreamy.lgh.service.impl.admin;


import com.dreamy.beans.Page;
import com.dreamy.lgh.dao.iface.AdminUserDao;
import com.dreamy.lgh.domain.admin.AdminUser;
import com.dreamy.lgh.domain.admin.AdminUserConditions;
import com.dreamy.lgh.service.iface.admin.AdminUserService;
import com.dreamy.utils.BeanUtils;
import com.dreamy.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/8.
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {


    @Resource
    private AdminUserDao adminUserDao;

    @Override
    public AdminUser getByUsername(String userName) {
        AdminUserConditions adminUserConditions = new AdminUserConditions();
        adminUserConditions.createCriteria().andUserNameEqualTo(userName);
        List<AdminUser> list = adminUserDao.selectByExample(adminUserConditions);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public AdminUser getById(Integer userId) {
        return adminUserDao.selectById(userId);
    }

    @Override
    public List<AdminUser> getList(AdminUser adminUser, Page page) {
        Map<String, Object> params = BeanUtils.toQueryMap(adminUser);
        AdminUserConditions adminUserConditions = new AdminUserConditions();
        adminUserConditions.createCriteria().addByMap(params);
        if (page != null) {
            page.setTotalNum(adminUserDao.countByExample(adminUserConditions));
            adminUserConditions.setPage(page);
        }
        return adminUserDao.selectByExample(adminUserConditions);
    }

    @Override
    public void save(AdminUser adminUser, int roleId) {
//        adminUserDao.save(adminUser);
//        UserRole userRole = new UserRole().roleId(roleId).adminId(adminUser.getId());
//        userRoleService.save(userRole);

    }

    @Override
    public void update(AdminUser adminUser, int roleId) {
//        adminUserDao.update(adminUser);
//        UserRole userRole = new UserRole().adminId(adminUser.getId()).roleId(roleId);
//        userRoleService.updateRoleId(userRole);


    }

    @Override
    public int updatePassword(AdminUser adminUser) {
        return adminUserDao.update(adminUser);
    }
}
