package com.dreamy.lgh.service.impl.banner;

import com.dreamy.lgh.dao.iface.BannerDao;
import com.dreamy.lgh.domain.banner.Banner;
import com.dreamy.lgh.domain.banner.BannerConditions;
import com.dreamy.lgh.service.iface.banner.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/23
 * Time: 下午2:37
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    public Integer save(Banner banner) {
        return bannerDao.save(banner);
    }

    @Override
    public List<Banner> getAllByOrderAndType(String order,Integer type) {
        BannerConditions conditions = new BannerConditions();
        conditions.createCriteria().andStatusEqualTo(0).andTypeEqualTo(type);

        return bannerDao.selectByExample(conditions);
    }

    @Override
    public Banner getById(Integer id) {
        return bannerDao.selectById(id);
    }

    @Override
    public Integer updateByRecord(Banner banner) {
        return bannerDao.update(banner);
    }

    @Override
    public Integer deleteById(Integer id) {
        return bannerDao.deleteById(id);
    }
}
