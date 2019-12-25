package org.java.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.java.pojo.Order;
import org.java.pojo.OrderCriteria;

public interface OrderMapper {
    int countByExample(OrderCriteria example);

    int deleteByExample(OrderCriteria example);

    int deleteByPrimaryKey(String orderid);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderCriteria example);

    Order selectByPrimaryKey(String orderid);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderCriteria example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderCriteria example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}