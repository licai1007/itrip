package org.java.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.java.pojo.ItemDesc;
import org.java.pojo.ItemDescCriteria;

public interface ItemDescMapper {
    int countByExample(ItemDescCriteria example);

    int deleteByExample(ItemDescCriteria example);

    int deleteByPrimaryKey(Long itemid);

    int insert(ItemDesc record);

    int insertSelective(ItemDesc record);

    List<ItemDesc> selectByExample(ItemDescCriteria example);

    ItemDesc selectByPrimaryKey(Long itemid);

    int updateByExampleSelective(@Param("record") ItemDesc record, @Param("example") ItemDescCriteria example);

    int updateByExample(@Param("record") ItemDesc record, @Param("example") ItemDescCriteria example);

    int updateByPrimaryKeySelective(ItemDesc record);

    int updateByPrimaryKey(ItemDesc record);
}