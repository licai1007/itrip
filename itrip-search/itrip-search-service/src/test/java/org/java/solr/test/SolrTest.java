package org.java.solr.test;


import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrTest {
	//添加对象到solr索引库中
	@Test
	public void testSave() throws Exception{
		//创建solr服务（查看继承关系:ctrl + t）
		SolrServer solrServer = new HttpSolrServer("http://192.168.63.136:8080/solr/collection1");
		//创建document
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id","test009");
		doc.setField("item_title","测试标题9");
		doc.setField("item_sell_point","测试卖点9");
		doc.setField("item_price",998);
		//添加到solr中
		solrServer.add(doc);
		//提交事务
		solrServer.commit();
	}
	//修改solr索引库中的对象
	@Test
	public void testUpdate() throws Exception{
		//创建solr服务（查看继承关系:ctrl + t）
		SolrServer solrServer = new HttpSolrServer("http://192.168.63.136:8080/solr/collection1");
		//创建document
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id","test001");
		doc.setField("item_title","新加坡旅游");
		doc.setField("item_sell_point","满100减99测试");
		doc.setField("item_price",1);
		//添加到solr中
		solrServer.add(doc);
		//提交事务
		solrServer.commit();
	}
	//根据id删除
	@Test
	public void testDeleteById() throws Exception{
		SolrServer solrServer = new HttpSolrServer("http://192.168.63.136:8080/solr/collection1");
		solrServer.deleteById("change.me");
		solrServer.commit();
	}
	//根据其它条件删除
	@Test
	public void testDeleteByQuery() throws Exception{
		SolrServer solrServer = new HttpSolrServer("http://192.168.63.136:8080/solr/collection1");
		solrServer.deleteByQuery("item_title:测试");
		solrServer.commit();
	}
	//查询
	@Test
	public void testQuery() throws Exception{
		SolrServer solrServer = new HttpSolrServer("http://192.168.63.136:8080/solr/collection1");
		//创建SolrQuery对象
		SolrQuery query = new SolrQuery();
		//query.set("q","*:*");
		query.setQuery("测试");
		//分页
		query.setStart(0);//每页开始的行标
		query.setRows(9);//每页显示的信息条数
		//设置搜索默认域（可以不用设置，如果不设置，默认是item_keywords）
		query.set("df","item_keywords");
		//设置高亮
		query.setHighlight(true);
		//高亮显示的域
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<div>");
		query.setHighlightSimplePost("</div>");
		//执行查询，得到response对象
		QueryResponse response = solrServer.query(query);
		//取到查询的结果
		SolrDocumentList results = response.getResults();
		//获取到查询总的结果条数
		long numFound = results.getNumFound();
		System.out.println("查询到的总信息条数："+numFound);
		//遍历查询到的结果
		for (SolrDocument solrDocument : results) {
			//获取到id
			System.out.println("id:"+solrDocument.get("id"));
			//取高亮显示
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			//这个判断就是为了解决查询的关键字不在title中，在卖点或者是分类中
			String title = "";
			if(list != null && list.size() > 0){
				title = list.get(0);
			}else{
				title = (String)solrDocument.get("item_title");
			}
			System.out.println("item_title:"+title);
			System.out.println("item_sell_point:"+solrDocument.get("item_sell_point"));
			System.out.println("item_price:"+solrDocument.get("item_price"));
			System.out.println("===================");
		}
		
	}

}
