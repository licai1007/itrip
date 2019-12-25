package org.java.redis.test;

import java.util.HashSet;
import java.util.Set;

import org.java.redis.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisTest {
	@Test
	public void testRedisCluster(){
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		HostAndPort node1 = new HostAndPort("192.168.63.135",7001);
		nodes.add(node1);
		HostAndPort node2 = new HostAndPort("192.168.63.135",7002);
		nodes.add(node2);
		HostAndPort node3 = new HostAndPort("192.168.63.135",7003);
		nodes.add(node3);
		HostAndPort node4 = new HostAndPort("192.168.63.135",7004);
		nodes.add(node4);
		HostAndPort node5 = new HostAndPort("192.168.63.135",7005);
		nodes.add(node5);
		HostAndPort node6 = new HostAndPort("192.168.63.135",7006);
		nodes.add(node6);
		//创建jedis集群
		JedisCluster jedisCluster = new JedisCluster(nodes);
		//往jedis集群中set字符串
		jedisCluster.set("hello5","hello5");
		//取出
		String str = jedisCluster.get("hello5");
		System.out.println(str);
	}
	@Test
	public void testRedisClusterSpring(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		JedisClient jedisClient = (JedisClient)context.getBean("jedisClient");
		jedisClient.set("hello6","hello6");
		String str = jedisClient.get("hello6");
		System.out.println(str);
	}
}
