package com.dreamy.lgh.service.impl.order;

import com.dreamy.beans.Page;
import com.dreamy.lgh.dao.iface.OrdersDao;
import com.dreamy.lgh.domain.user.Orders;
import com.dreamy.lgh.domain.user.OrdersConditions;
import com.dreamy.lgh.service.iface.order.OrderService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    public Integer updateByRecord(Orders orders) {
        return ordersDao.update(orders);
    }

    @Override
    public Orders getByOrderIdAndWxId(String orderId, String wxId) {
        OrdersConditions conditions = new OrdersConditions();
        conditions.createCriteria().andOrderIdEqualTo(orderId).andWxIdEqualTo(wxId);

        Page page = new Page();
        page.setPageSize(1);
        conditions.setPage(page);

        List<Orders> ordersList = ordersDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(ordersList)) {
            return ordersList.get(0);
        }

        return null;
    }

    @Override
    public Orders getByTransactionIdAndWxId(String transactionId, String wxId) {
        OrdersConditions conditions = new OrdersConditions();
        conditions.createCriteria().andTransactionIdEqualTo(transactionId).andWxIdEqualTo(wxId);

        Page page = new Page();
        page.setPageSize(1);
        conditions.setPage(page);

        List<Orders> ordersList = ordersDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(ordersList)) {
            return ordersList.get(0);
        }

        return null;
    }
}
