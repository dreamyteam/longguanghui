package com.dreamy.lgh.service.iface.banner;

import com.dreamy.lgh.domain.banner.Banner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/23
 * Time: 下午2:37
 */
public interface BannerService {
    /**
     * @param banner
     * @return
     */
    Integer save(Banner banner);


    /**
     * @param order
     * @return
     */
    List<Banner> getAllByOrderAndType(String order,Integer type);

    /**
     *
     * @param id
     * @return
     */
    Banner getById(Integer id);

    /**
     *
     * @param banner
     * @return
     */
    Integer updateByRecord(Banner banner);

    /**
     *
     * @param id
     * @return
     */
    Integer deleteById(Integer id);
}
