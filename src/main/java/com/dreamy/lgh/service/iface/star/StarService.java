package com.dreamy.lgh.service.iface.star;

import com.dreamy.beans.Page;
import com.dreamy.lgh.domain.star.Star;
import com.dreamy.lgh.enums.ErrorCodeEnums;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/21
 * Time: 下午1:17
 */
public interface StarService {

    /**
     * @param userId
     * @param page
     * @return
     */
    List<Integer> getFollowStarIdsByUserId(Integer userId, Page page);


    /**
     * @param starIds
     * @return
     */
    List<Star> getStarsByIds(List<Integer> starIds);

    /**
     * @param starId
     * @return
     */
    Star getById(Integer starId);


    /**
     * @param page
     * @return
     */
    List<Star> getStarsByPage(Page page);

    /**
     * @param starId
     * @return
     */
    Integer deleteStar(Integer starId);

    /**
     * @param starId
     * @return
     */
    Integer deleteById(Integer starId);

    /**
     * @param starId
     * @return
     */
    Integer deleteStarMapByStarId(Integer starId);


    /**
     * @param userId
     * @param starId
     * @return
     */
    ErrorCodeEnums addFollow(Integer userId, Integer starId);


    /**
     * @param userId
     * @param starId
     * @return
     */
    ErrorCodeEnums cancelFollow(Integer userId, Integer starId);

    /**
     * @param name
     * @return
     */
    Integer save(String name);

}
