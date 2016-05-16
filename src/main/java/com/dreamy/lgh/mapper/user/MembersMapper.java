package com.dreamy.lgh.mapper.user;

import com.dreamy.lgh.domain.user.Members;
import com.dreamy.lgh.domain.user.MembersConditions;
import java.util.List;

import com.dreamy.lgh.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface MembersMapper extends BaseMapper<Members,Integer,MembersConditions>{
    int countByExample(MembersConditions example);

    int deleteByExample(MembersConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(Members record);

    int insertSelective(Members record);

    List<Members> selectByExample(MembersConditions example);

    Members selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Members record, @Param("example") MembersConditions example);

    int updateByExample(@Param("record") Members record, @Param("example") MembersConditions example);

    int updateByPrimaryKeySelective(Members record);

    int updateByPrimaryKey(Members record);
}