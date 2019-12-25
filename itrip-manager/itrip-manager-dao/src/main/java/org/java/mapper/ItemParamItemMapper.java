package org.java.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.java.pojo.ItemParamItem;
import org.java.pojo.ItemParamItemCriteria;

public interface ItemParamItemMapper {
    int countByExample(ItemParamItemCriteria example);

    int deleteByExample(ItemParamItemCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemParamItem record);

    int insertSelective(ItemParamItem record);

    List<ItemParamItem> selectByExample(ItemParamItemCriteria example);

    ItemParamItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemParamItem record, @Param("example") ItemParamItemCriteria example);

    int updateByExample(@Param("record") ItemParamItem record, @Param("example") ItemParamItemCriteria example);

    int updateByPrimaryKeySelective(ItemParamItem record);

    int updateByPrimaryKey(ItemParamItem record);
}