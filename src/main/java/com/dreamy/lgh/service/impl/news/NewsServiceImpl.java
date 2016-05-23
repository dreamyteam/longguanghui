package com.dreamy.lgh.service.impl.news;

import com.dreamy.beans.Page;
import com.dreamy.lgh.dao.iface.NewsDao;
import com.dreamy.lgh.domain.news.News;
import com.dreamy.lgh.domain.news.NewsConditions;
import com.dreamy.lgh.service.iface.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/23
 * Time: 下午3:59
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;


    @Override
    public Integer save(News news) {
        return newsDao.save(news);
    }

    @Override
    public List<News> getByPageAndOrder(Page page, String order) {
        NewsConditions conditions = new NewsConditions();
        conditions.setPage(page);
        conditions.setOrderByClause(order);
        return newsDao.selectByExample(conditions);
    }

    @Override
    public News getById(Integer id) {
        return newsDao.selectById(id);
    }

    @Override
    public Integer deleteById(Integer id) {
        return newsDao.deleteById(id);
    }

    @Override
    public Integer updateByRecord(News news) {
        return newsDao.update(news);
    }
}
