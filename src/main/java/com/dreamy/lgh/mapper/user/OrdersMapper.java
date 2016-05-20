package com.dreamy.lgh.mapper.user;

import com.dreamy.lgh.domain.user.Orders;
import com.dreamy.lgh.domain.user.OrdersConditions;
import java.util.List;

import com.dreamy.lgh.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface OrdersMapper extends BaseMapper<Orders,Integer,OrdersConditions> {

    int countByExample(OrdersConditions example);

    int deleteByExample(OrdersConditions example);

    int deleteByPrimaryKey(Integer id);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersConditions example);

    Orders selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersConditions example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersConditions example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
}