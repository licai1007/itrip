package org.java.search.mapper;

import java.util.List;

import org.java.common.pojo.SearchItem;

public interface SearchItemMapper {
	/**
	 * 查询到要导入到solr中的对象列表
	 * @return
	 */
	List<SearchItem> getSearchs();
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	SearchItem getSearchById(Long id);
}
