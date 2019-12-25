package org.java.search.listener;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.java.common.pojo.SearchItem;
import org.java.search.mapper.SearchItemMapper;
import org.springframework.beans.factory.annotation.Autowired;


public class ItemAddListener implements MessageListener{
	@Autowired
	private SearchItemMapper searchItemMapper;
	@Autowired
	private SolrServer solrServer;
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage)message;
		//获取到message中的内容
		String text = null;
		try {
			text = textMessage.getText();
			Thread.sleep(60000);
			//将text转换成id
			long id = Long.parseLong(text);
			//根据id查询对象（这里是有可能查询不到对象：因为事务的问题）
			SearchItem searchItem = searchItemMapper.getSearchById(id);
			//将对象同步到索引库中
			SolrInputDocument doc = new SolrInputDocument();
			doc.setField("id",id);
			doc.setField("item_title",searchItem.getTitle());
			doc.setField("item_sell_point",searchItem.getSellPoint());
			doc.setField("item_price",String.valueOf(searchItem.getPrice()));
			doc.setField("item_image",searchItem.getImage());
			doc.setField("item_category_name",searchItem.getCategoryName());
			solrServer.add(doc);
			solrServer.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
