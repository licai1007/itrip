package org.java.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.java.pojo.Content;
import org.java.pojo.ContentCriteria;

public interface ContentMapper {
    int countByExample(ContentCriteria example);

    int deleteByExample(ContentCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Content record);

    int insertSelective(Content record);

    List<Content> selectByExample(ContentCriteria example);

    Content selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Content record, @Param("example") ContentCriteria example);

    int updateByExample(@Param("record") Content record, @Param("example") ContentCriteria example);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);
}