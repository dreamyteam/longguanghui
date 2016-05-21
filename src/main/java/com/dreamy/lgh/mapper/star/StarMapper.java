package com.dreamy.lgh.mapper.star;

import com.dreamy.lgh.domain.star.Star;
import com.dreamy.lgh.domain.star.StarConditions;
import java.util.List;

import com.dreamy.lgh.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface StarMapper extends BaseMapper<Star,Integer,StarConditions>{
    int countByExample(StarConditions example);

    int deleteByExample(StarConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(Star record);

    int insertSelective(Star record);

    List<Star> selectByExample(StarConditions example);

    Star selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Star record, @Param("example") StarConditions example);

    int updateByExample(@Param("record") Star record, @Param("example") StarConditions example);

    int updateByPrimaryKeySelective(Star record);

    int updateByPrimaryKey(Star record);
}