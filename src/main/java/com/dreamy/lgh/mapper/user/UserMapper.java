package com.dreamy.lgh.mapper.user;

import com.dreamy.lgh.domain.user.User;
import com.dreamy.lgh.domain.user.UserConditions;
import java.util.List;

import com.dreamy.lgh.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User,Integer,UserConditions> {
    int countByExample(UserConditions example);

    int deleteByExample(UserConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserConditions example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserConditions example);

    int updateByExample(@Param("record") User record, @Param("example") UserConditions example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}