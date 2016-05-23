package com.dreamy.lgh.mapper.news;

import com.dreamy.lgh.domain.news.News;
import com.dreamy.lgh.domain.news.NewsConditions;

import java.util.List;

import com.dreamy.lgh.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface NewsMapper extends BaseMapper<News, Integer, NewsConditions> {
    int countByExample(NewsConditions example);

    int deleteByExample(NewsConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    List<News> selectByExample(NewsConditions example);

    News selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") News record, @Param("example") NewsConditions example);

    int updateByExample(@Param("record") News record, @Param("example") NewsConditions example);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
}