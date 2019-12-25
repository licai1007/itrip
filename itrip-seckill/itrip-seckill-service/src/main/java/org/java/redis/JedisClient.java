package org.java.redis;
public interface JedisClient {
	//添加一个字符串到redis中
	String set(String key, String value);
	//根据key获取到一个字符串对象
	String get(String key);
	//查询key是否在redis中存在
	Boolean exists(String key);
	//设置存在时间
	Long expire(String key, int seconds);
	//查看存在的时间
	Long ttl(String key);
	//自增
	Long incr(String key);
	//hash
	Long hset(String key, String field, String value);
	//取出hash
	String hget(String key, String field);
	//删除
	Long hdel(String key, String... field);
}