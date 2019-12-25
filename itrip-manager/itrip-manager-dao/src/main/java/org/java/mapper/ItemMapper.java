package org.java.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.java.pojo.Item;
import org.java.pojo.ItemCriteria;

public interface ItemMapper {
    int countByExample(ItemCriteria example);

    int deleteByExample(ItemCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Item record);

    int insertSelective(Item record);

    List<Item> selectByExample(ItemCriteria example);

    Item selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Item record, @Param("example") ItemCriteria example);

    int updateByExample(@Param("record") Item record, @Param("example") ItemCriteria example);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
}