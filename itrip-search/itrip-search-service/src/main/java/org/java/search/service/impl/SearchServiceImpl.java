package org.java.search.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.java.common.pojo.ItripResult;
import org.java.common.pojo.SearchItem;
import org.java.common.pojo.SearchResult;
import org.java.search.mapper.SearchItemMapper;
import org.java.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class SearchServiceImpl implements SearchService{
	@Autowired
	private SearchItemMapper searchItemMapper;
	@Autowired
	private SolrServer solrServer;
	@Value("${Solr_DEFAL_RESULT_COUNT}")
	private Integer Solr_DEFAL_RESULT_COUNT;
	@Override
	public ItripResult saveItemsToSolr() {
		try {
			//查询到所有的对象
			List<SearchItem> list = searchItemMapper.getSearchs();
			//遍历列表
			for (SearchItem searchItem : list) {
				SolrInputDocument docs = new SolrInputDocument();
				docs.setField("id",searchItem.getId());
				docs.setField("item_title",searchItem.getTitle());
				docs.setField("item_sell_point",searchItem.getSellPoint());
				docs.setField("item_price",String.valueOf(searchItem.getPrice()));
				docs.setField("item_image",searchItem.getImage());
				docs.setField("item_category_name",searchItem.getCategoryName());
				//添加到solr中
				solrServer.add(docs);
			}
			solrServer.commit();
			return ItripResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ItripResult.build(500,"添加到solr失败");
		}
	}
	@Override
	public SearchResult getSearchResult(String queryString,Integer pageNow,
			Integer pageSize) throws Exception{
		SearchResult result = new SearchResult();
		//创建SolrQuery对象
		SolrQuery query = new SolrQuery();
		query.setQuery(queryString);
		//分页
		if(pageNow == null || pageNow <1){
			pageNow = 1;
		}
		if(pageSize == null){
			pageSize = Solr_DEFAL_RESULT_COUNT;  //默认每页10条
		}
		query.setStart((pageNow-1)*pageSize);   //每页开始的行标
		query.setRows(pageSize);     //每页显示的信息条数
		query.set("df","item_keywords");  //设置搜索默认域
		//设置高亮
		query.setHighlight(true);
		//高亮显示的域
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<span style='color:red;'><strong>");
		query.setHighlightSimplePost("</strong></span>");
		//执行查询，得到response对象
		QueryResponse response = solrServer.query(query);
		//执行查询
		SolrDocumentList solrList = response.getResults();
		//获取到总的信息条数
		long count = solrList.getNumFound();
		result.setCount(count);
		//计算总的页数
		long totalPages = count/pageSize;
		if(count%pageSize != 0){
			totalPages++;
		}
		result.setTotalPages(totalPages);
		List<SearchItem> list = new ArrayList<SearchItem>();
		for (SolrDocument solrDocument : solrList) {
			SearchItem item = new SearchItem();
			//获取到高光集合
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<String> titles = highlighting.get(solrDocument.get("id")).get("item_title");
			//考虑到有可能查询的关键字在标题中没有，在卖点或者是分类名称中有
			String title = "";
			if(titles != null && titles.size() >0){
				title = titles.get(0);
			}else{
				title = (String) solrDocument.get("item_title");
			}
			item.setId(Long.valueOf(String.valueOf(solrDocument.get("id"))));
			item.setTitle(title);
			item.setSellPoint((String)solrDocument.get("item_sell_points"));
			item.setPrice(new BigDecimal(String.valueOf(solrDocument.get("item_price"))));
			String image = (String)solrDocument.get("item_image");
			//如果上传的图片有多张的时候，那么解析只需要一张
			if(StringUtils.isNotBlank(image)){
				//将图片切割成数组，然后只需要数组中的第一个
				image = image.split(",")[0];
			}
			item.setImage(image);
			item.setCategoryName((String)solrDocument.get("item_category_name"));
			//将item放入到list中
			list.add(item);
		}
		result.setItemList(list);
		return result;
	}

}
