package com.dreamy.lgh.mapper.banner;

import com.dreamy.lgh.domain.banner.Banner;
import com.dreamy.lgh.domain.banner.BannerConditions;

import java.util.List;

import com.dreamy.lgh.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BannerMapper extends BaseMapper<Banner, Integer, BannerConditions> {
    int countByExample(BannerConditions example);

    int deleteByExample(BannerConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(Banner record);

    int insertSelective(Banner record);

    List<Banner> selectByExample(BannerConditions example);

    Banner selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Banner record, @Param("example") BannerConditions example);

    int updateByExample(@Param("record") Banner record, @Param("example") BannerConditions example);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);
}