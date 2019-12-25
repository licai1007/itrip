package org.java.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.java.pojo.OrderItem;
import org.java.pojo.OrderItemCriteria;

public interface OrderItemMapper {
    int countByExample(OrderItemCriteria example);

    int deleteByExample(OrderItemCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    List<OrderItem> selectByExample(OrderItemCriteria example);

    OrderItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OrderItem record, @Param("example") OrderItemCriteria example);

    int updateByExample(@Param("record") OrderItem record, @Param("example") OrderItemCriteria example);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}