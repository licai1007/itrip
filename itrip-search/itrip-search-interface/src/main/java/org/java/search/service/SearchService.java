package org.java.search.service;

import org.java.common.pojo.ItripResult;
import org.java.common.pojo.SearchResult;

public interface SearchService {
	/**
	 * 添加所有的item到solr中
	 * @return
	 */
	ItripResult saveItemsToSolr();
	/**
	 * 在solr中分页查询
	 * @param queryString     查询条件
	 * @param pageNow         当前查询页码
	 * @param pageSize        每页显示的信息条数
	 * @return                查询结果
	 */
	SearchResult getSearchResult(String queryString,Integer pageNow,Integer pageSize) throws Exception;

}
