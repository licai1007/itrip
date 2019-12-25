package org.java.solr.test;


import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SolrCloudTest {
	@Test
	public void saveTest() throws Exception{
		//创建SolrCloud服务
		CloudSolrServer cloudSolrServer = new CloudSolrServer("192.168.63.136:2181,192.168.63.136:2182,192.168.63.136:2183");
		//需要设置DefaultCollection属性
		cloudSolrServer.setDefaultCollection("collection2");
		//创建文档对象
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id","test009");
		doc.setField("item_title","测试标题9");
		doc.setField("item_sell_point","测试卖点9");
		doc.setField("item_price",998);
		//添加
		cloudSolrServer.add(doc);
		cloudSolrServer.commit();
	}
	@Test
	public void deleteTest() throws SolrServerException, IOException{
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-solr.xml");
		CloudSolrServer css = (CloudSolrServer)app.getBean("cloudSolrServer");
		css.deleteById("520");
		css.commit();
	}
	
}
