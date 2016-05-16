package com.dreamy.lgh.mapper.admin;

import com.dreamy.lgh.domain.admin.AdminUser;
import com.dreamy.lgh.domain.admin.AdminUserConditions;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminUserMapper {
    int countByExample(AdminUserConditions example);

    int deleteByExample(AdminUserConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    List<AdminUser> selectByExample(AdminUserConditions example);

    AdminUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminUser record, @Param("example") AdminUserConditions example);

    int updateByExample(@Param("record") AdminUser record, @Param("example") AdminUserConditions example);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
}