package org.java.activemq.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class ActivemqQueueTest {
	@Test
	public void testQueueProducer() throws Exception{
		//1.创建ConnectionFactory对象
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.63.138:61616");
		//2.创建Connection对象
		Connection connection = connectionFactory.createConnection();
		//3.开启连接
		connection.start();
		//4.创建Session对象（参数1表示是否开启事务，参数2表示应答机制，一般都是用自动应答机制）
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		//5.Session对象来创建destination对象(queue,topic)
		//名字叫：test-queue
		Queue queue = session.createQueue("test-queue");
		//6.通过Session对象创建Producer对象
		MessageProducer producer = session.createProducer(queue);
		//7.创建message对象
		TextMessage message = session.createTextMessage("hello");
		//8.通过producer来发送信息
		producer.send(message);
		//9.关闭资源
		producer.close();
		session.close();
		connection.close();
	}
	@Test
	public void testQueueConsumer() throws Exception{
		//1.创建ConnectionFactory对象
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.63.138:61616");
		//2.创建Connection对象
		Connection connection = connectionFactory.createConnection();
		//3.开启连接
		connection.start();
		//4.创建Session对象（参数1表示是否开启事务，参数2表示应答机制，一般都是用自动应答机制）
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		//5.Session对象来创建destination对象(queue,topic)
		//名字叫：test-queue
		Queue queue = session.createQueue("test-queue");
		//6.创建消费者
		MessageConsumer consumer = session.createConsumer(queue);
		//7.接收消息
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				//因为消息的发送用的就是textMessage
				TextMessage textMessage = (TextMessage)message;
				String text = null;
				try {
					//获取到消息的内容
					text = textMessage.getText();
					//打印消息
					System.out.println(text);
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("queue的消费............");
		//等待输入
		System.in.read();
		//关闭资源
		consumer.close();
		session.close();
		connection.close();
	}
	@Test
	public void testQueueProducerSpring() throws Exception{
		//读取spring的配置文件
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		JmsTemplate jmsTemplate = (JmsTemplate)app.getBean("jmsTemplate");
		Queue queue = (Queue)app.getBean("test-queue");
		jmsTemplate.send(queue,new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage("spring send queue");
				return message;
			}
		});
	}
	@Test
	public void testQueueConsumerSpring() throws Exception{
		//读取spring的配置文件
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		//等待
		System.in.read();
	}
}
