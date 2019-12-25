package org.java.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.java.pojo.ItemParam;
import org.java.pojo.ItemParamCriteria;

public interface ItemParamMapper {
    int countByExample(ItemParamCriteria example);

    int deleteByExample(ItemParamCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemParam record);

    int insertSelective(ItemParam record);

    List<ItemParam> selectByExample(ItemParamCriteria example);

    ItemParam selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemParam record, @Param("example") ItemParamCriteria example);

    int updateByExample(@Param("record") ItemParam record, @Param("example") ItemParamCriteria example);

    int updateByPrimaryKeySelective(ItemParam record);

    int updateByPrimaryKey(ItemParam record);
}