package org.java.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.java.pojo.ItemCat;
import org.java.pojo.ItemCatCriteria;

public interface ItemCatMapper {
    int countByExample(ItemCatCriteria example);

    int deleteByExample(ItemCatCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemCat record);

    int insertSelective(ItemCat record);

    List<ItemCat> selectByExample(ItemCatCriteria example);

    ItemCat selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemCat record, @Param("example") ItemCatCriteria example);

    int updateByExample(@Param("record") ItemCat record, @Param("example") ItemCatCriteria example);

    int updateByPrimaryKeySelective(ItemCat record);

    int updateByPrimaryKey(ItemCat record);
}