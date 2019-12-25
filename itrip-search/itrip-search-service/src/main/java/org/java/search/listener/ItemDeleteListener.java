package org.java.search.listener;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemDeleteListener implements MessageListener{
	@Autowired
	private CloudSolrServer cloudSolrServer;
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage)message;
		try {
			String text = textMessage.getText();
			//删除索引库中的对象
			cloudSolrServer.deleteById(text);
			cloudSolrServer.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
