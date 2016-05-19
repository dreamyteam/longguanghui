package com.dreamy.lgh.service.impl.order;

import com.dreamy.lgh.dao.iface.OrdersDao;
import com.dreamy.lgh.domain.user.Orders;
import com.dreamy.lgh.service.iface.order.OrderService;
import com.dreamy.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/19
 * Time: 下午4:54
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersDao ordersDao;

    @Override
    public Integer save(Orders orders, Integer userId) {
        orders.setOrderId(createOrderId(userId));
        return ordersDao.save(orders);
    }

    @Override
    public String createOrderId(Integer userId) {
        Date d = new Date();
        return TimeUtils.toString("yyyyMMddhhmmss", d) + "1000" + "0000" + "0000" + (100000 + userId);
    }
}
