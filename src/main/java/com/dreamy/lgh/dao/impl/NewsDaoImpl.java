package com.dreamy.lgh.dao.impl;

import com.dreamy.lgh.dao.BaseDaoImpl;
import com.dreamy.lgh.dao.iface.NewsDao;
import com.dreamy.lgh.domain.news.News;
import com.dreamy.lgh.domain.news.NewsConditions;
import com.dreamy.lgh.mapper.BaseMapper;
import com.dreamy.lgh.mapper.news.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/23
 * Time: 下午3:56
 */
@Repository
public class NewsDaoImpl extends BaseDaoImpl<News,Integer,NewsConditions> implements NewsDao{

    @Autowired
    private NewsMapper mapper;

    @Autowired

    @Override
    public void setBaseMapper() {
        super.setBaseMapper(mapper);
    }
}
