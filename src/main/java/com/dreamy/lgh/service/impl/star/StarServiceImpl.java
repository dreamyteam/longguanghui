package com.dreamy.lgh.service.impl.star;

import com.dreamy.beans.Page;
import com.dreamy.lgh.dao.iface.StarDao;
import com.dreamy.lgh.dao.iface.StarFollowDao;
import com.dreamy.lgh.domain.star.Star;
import com.dreamy.lgh.domain.star.StarConditions;
import com.dreamy.lgh.domain.star.StarFollow;
import com.dreamy.lgh.domain.star.StarFollowConditions;
import com.dreamy.lgh.enums.ErrorCodeEnums;
import com.dreamy.lgh.service.iface.star.StarService;
import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/21
 * Time: 下午1:17
 */
@Service
public class StarServiceImpl implements StarService {

    @Autowired
    private StarDao starDao;

    @Autowired
    private StarFollowDao starFollowDao;


    @Override
    public Integer save(String name) {
        Star star = new Star();

        star.name(name);
        return starDao.save(star);
    }

    @Override
    public List<Integer> getFollowStarIdsByUserId(Integer userId, Page page) {

        StarFollowConditions starFollowConditions = new StarFollowConditions();
        starFollowConditions.createCriteria().andUserIdEqualTo(userId);
        starFollowConditions.setPage(page);

        List<StarFollow> starFollowList = starFollowDao.selectByExample(starFollowConditions);
        if (CollectionUtils.isNotEmpty(starFollowList)) {
            List<Integer> starIdList = new LinkedList<>();
            for (StarFollow starFollow : starFollowList) {
                starIdList.add(starFollow.getStarId());
            }

            return starIdList;
        }

        return null;
    }

    @Override
    public List<Star> getStarsByIds(List<Integer> starIds) {

        StarConditions starConditions = new StarConditions();
        starConditions.createCriteria().andIdIn(starIds);

        return starDao.selectByExample(starConditions);
    }

    @Override
    public Star getById(Integer starId) {
        return starDao.selectById(starId);
    }

    @Override
    public List<Star> getStarsByPage(Page page) {
        StarConditions starConditions = new StarConditions();
        starConditions.setPage(page);
        return starDao.selectByExample(starConditions);
    }

    @Override
    public ErrorCodeEnums addFollow(Integer userId, Integer starId) {
        ErrorCodeEnums errorCodeEnums = ErrorCodeEnums.add_follow_failed;

        Page page = new Page();
        page.setPageSize(100);
        List<Integer> followedStarIdList = getFollowStarIdsByUserId(userId, page);
        if (CollectionUtils.isNotEmpty(followedStarIdList)) {
            if (!followedStarIdList.contains(starId)) {
                StarFollow starFollow = new StarFollow();
                starFollow.userId(userId);
                starFollow.starId(starId);

                starFollowDao.save(starFollow);
                errorCodeEnums = ErrorCodeEnums.success;
            }
        }

        return errorCodeEnums;
    }

    @Override
    public ErrorCodeEnums cancelFollow(Integer userId, Integer starId) {
        ErrorCodeEnums errorCodeEnums = ErrorCodeEnums.cancel_follow_failed;

        Page page = new Page();
        page.setPageSize(100);
        List<Integer> followedStarIdList = getFollowStarIdsByUserId(userId, page);
        if (CollectionUtils.isNotEmpty(followedStarIdList)) {
            if (followedStarIdList.contains(starId)) {
                StarFollowConditions conditions = new StarFollowConditions();
                conditions.createCriteria().andUserIdEqualTo(userId).andStarIdEqualTo(starId);

                starFollowDao.deleteByExample(conditions);
                errorCodeEnums = ErrorCodeEnums.success;
            }
        }

        return errorCodeEnums;
    }

    @Override
    public Integer deleteById(Integer starId) {
        return starDao.deleteById(starId);
    }

    @Override
    public Integer deleteStarMapByStarId(Integer starId) {
        StarFollowConditions conditions = new StarFollowConditions();
        conditions.createCriteria().andStarIdEqualTo(starId);
        return starFollowDao.deleteByExample(conditions);
    }

    @Override
    public Integer deleteStar(Integer starId) {
        deleteById(starId);
        return deleteStarMapByStarId(starId);
    }
}
