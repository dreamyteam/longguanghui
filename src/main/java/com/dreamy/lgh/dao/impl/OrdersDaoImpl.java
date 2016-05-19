package com.dreamy.lgh.dao.impl;

import com.dreamy.lgh.dao.BaseDaoImpl;
import com.dreamy.lgh.dao.iface.OrdersDao;
import com.dreamy.lgh.domain.user.Orders;
import com.dreamy.lgh.domain.user.OrdersConditions;
import com.dreamy.lgh.mapper.user.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/19
 * Time: 下午4:50
 */
@Repository
public class OrdersDaoImpl extends BaseDaoImpl<Orders, Integer, OrdersConditions> implements OrdersDao {

    @Autowired
    private OrdersMapper mapper;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setBaseMapper(mapper);
    }
}
