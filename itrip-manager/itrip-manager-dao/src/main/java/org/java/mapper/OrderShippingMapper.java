package org.java.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.java.pojo.OrderShipping;
import org.java.pojo.OrderShippingCriteria;

public interface OrderShippingMapper {
    int countByExample(OrderShippingCriteria example);

    int deleteByExample(OrderShippingCriteria example);

    int deleteByPrimaryKey(String orderid);

    int insert(OrderShipping record);

    int insertSelective(OrderShipping record);

    List<OrderShipping> selectByExample(OrderShippingCriteria example);

    OrderShipping selectByPrimaryKey(String orderid);

    int updateByExampleSelective(@Param("record") OrderShipping record, @Param("example") OrderShippingCriteria example);

    int updateByExample(@Param("record") OrderShipping record, @Param("example") OrderShippingCriteria example);

    int updateByPrimaryKeySelective(OrderShipping record);

    int updateByPrimaryKey(OrderShipping record);
}