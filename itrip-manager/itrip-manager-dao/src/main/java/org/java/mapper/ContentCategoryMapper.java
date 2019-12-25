package org.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.java.pojo.ContentCategory;
import org.java.pojo.ContentCategoryCriteria;

public interface ContentCategoryMapper {
    int countByExample(ContentCategoryCriteria example);

    int deleteByExample(ContentCategoryCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ContentCategory record);

    int insertSelective(ContentCategory record);

    List<ContentCategory> selectByExample(ContentCategoryCriteria example);

    ContentCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ContentCategory record, @Param("example") ContentCategoryCriteria example);

    int updateByExample(@Param("record") ContentCategory record, @Param("example") ContentCategoryCriteria example);

    int updateByPrimaryKeySelective(ContentCategory record);

    int updateByPrimaryKey(ContentCategory record);
}