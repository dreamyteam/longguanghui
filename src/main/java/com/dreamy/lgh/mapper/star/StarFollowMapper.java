package com.dreamy.lgh.mapper.star;

import com.dreamy.lgh.domain.star.StarFollow;
import com.dreamy.lgh.domain.star.StarFollowConditions;
import java.util.List;

import com.dreamy.lgh.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface StarFollowMapper extends BaseMapper<StarFollow,Integer,StarFollowConditions>{
    int countByExample(StarFollowConditions example);

    int deleteByExample(StarFollowConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(StarFollow record);

    int insertSelective(StarFollow record);

    List<StarFollow> selectByExample(StarFollowConditions example);

    StarFollow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StarFollow record, @Param("example") StarFollowConditions example);

    int updateByExample(@Param("record") StarFollow record, @Param("example") StarFollowConditions example);

    int updateByPrimaryKeySelective(StarFollow record);

    int updateByPrimaryKey(StarFollow record);
}