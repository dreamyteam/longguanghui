package com.dreamy.lgh.service.iface.order;

import com.dreamy.lgh.domain.user.Orders;
import com.dreamy.lgh.domain.user.User;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/19
 * Time: 下午4:53
 */
public interface OrderService {
    /**
     * @param orders
     * @return
     */
    Integer save(Orders orders,User user);

    /**
     *
     * @param user
     * @return
     */
    public String createOrderId(Integer userId);
}
