package org.java.activemq.test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


public class QueueListener implements MessageListener{
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage)message;
		//获取到message中的内容
		String text = null;
		try {
			text = textMessage.getText();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		System.out.println(text);
	}

}
