package com.dreamy.lgh.dao.iface;

import com.dreamy.lgh.dao.BaseDao;
import com.dreamy.lgh.domain.news.News;
import com.dreamy.lgh.domain.news.NewsConditions;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/23
 * Time: 下午3:55
 */
public interface NewsDao extends BaseDao<News, Integer, NewsConditions> {
}
