package org.java.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.java.common.util.JsonUtils;
import org.java.mapper.ItemDescMapper;
import org.java.pojo.ItemDesc;
import org.java.redis.JedisClient;
import org.java.service.HotelDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class HotelDescServiceImpl implements HotelDescService{
	@Autowired
	private ItemDescMapper itemDescMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${ITEM}")
	private String ITEM;
	@Value("${EXPIRE_SECOND}")
	private int EXPIRE_SECOND;
	@Override
	public ItemDesc getDescByItemId(Long id) {
		//判断是否有，如果有直接从redis中取出
		String json = jedisClient.get(ITEM+id+":DESC");
		if(StringUtils.isNotBlank(json)){
			return JsonUtils.jsonToPojo(json,ItemDesc.class);
		}
		//执行查询
		ItemDesc desc = itemDescMapper.selectByPrimaryKey(id);
		//放到redis中
		jedisClient.set(ITEM+id+":DESC",JsonUtils.objectToJson(desc));
		jedisClient.expire(ITEM+id+":DESC",EXPIRE_SECOND);
		return desc;
	}

}
