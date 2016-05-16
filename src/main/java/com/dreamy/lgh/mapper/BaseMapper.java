package com.dreamy.lgh.mapper;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/6.
 */
public interface BaseMapper<BaseDomain, ID extends Serializable,T> {

    int countByExample(T example);

    int deleteByExample(T example);

    int deleteByPrimaryKey(ID id);

    int insert(BaseDomain record);

    int insertSelective(BaseDomain record);

    List<BaseDomain> selectByExample(T example);

    BaseDomain selectByPrimaryKey(ID id);

    int updateByExampleSelective(@Param("record") BaseDomain record, @Param("example") T example);

    int updateByExample(@Param("record") BaseDomain record, @Param("example") T example);

    int updateByPrimaryKeySelective(BaseDomain record);

    int updateByPrimaryKey(BaseDomain record);


}
