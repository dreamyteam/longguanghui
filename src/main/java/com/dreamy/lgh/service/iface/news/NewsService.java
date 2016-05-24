package com.dreamy.lgh.service.iface.news;

import com.dreamy.beans.Page;
import com.dreamy.lgh.domain.news.News;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/23
 * Time: 下午3:58
 */
public interface NewsService {

    /**
     * @param news
     * @return
     */
    Integer save(News news);

    /**
     * @param page
     * @param order
     * @return
     */
    List<News> getByPageAndOrderAndType(Page page, String order,Integer type);

    /**
     * @param id
     * @return
     */
    News getById(Integer id);

    /**
     *
     * @param id
     * @return
     */
    Integer deleteById(Integer id);

    /**
     *
     * @param news
     * @return
     */
    Integer updateByRecord(News news);


}
