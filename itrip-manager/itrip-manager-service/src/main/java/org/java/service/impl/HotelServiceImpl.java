package org.java.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.java.common.pojo.EasyUIDataGrideResult;
import org.java.common.pojo.ItripResult;
import org.java.common.util.IDUtils;
import org.java.common.util.JsonUtils;
import org.java.mapper.ItemDescMapper;
import org.java.mapper.ItemMapper;
import org.java.pojo.Item;
import org.java.pojo.ItemCriteria;
import org.java.pojo.ItemDesc;
import org.java.redis.JedisClient;
import org.java.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class HotelServiceImpl implements HotelService{
	//注入mapper
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	@Autowired
	private JmsTemplate jmsTemplate;
	@Resource(name="itemAddTopic")
	private Destination itemAddTopic;
	@Resource(name="itemDeleteQueue")
	private Destination itemDeleteQueue;
	@Autowired
	private JedisClient jedisClient;
	@Value("${ITEM}")
	private String ITEM;
	@Value("${EXPIRE_SECOND}")
	private int EXPIRE_SECOND;
	@Override
	public Item getById(Long id) {
		//1.判断redis中是否有该对象，如果有直接从redis中取出
		String json = jedisClient.get(ITEM+id+":BASE");
		if(StringUtils.isNotBlank(json)){
			//将json格式的字符串转换成对象返回
			return JsonUtils.jsonToPojo(json,Item.class);
		}
		//2.如果没有，那么从数据库查询
		Item item = itemMapper.selectByPrimaryKey(id);
		//3.将对象放入到redis中
		jedisClient.set(ITEM+id+":BASE",JsonUtils.objectToJson(item));
		jedisClient.expire(ITEM+id+":BASE",EXPIRE_SECOND);
		return item;
	}
	@Override
	public EasyUIDataGrideResult getHotelList(Integer pageNow, Integer pageSize) {
		//开始分页
		PageHelper.startPage(pageNow, pageSize);
		//执行查询
		ItemCriteria example = new ItemCriteria();
		List<Item> list = itemMapper.selectByExample(example);
		//转化成pageInfo对象
		PageInfo<Item> pageInfo = new PageInfo<Item>(list);
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		result.setTotal(pageInfo.getTotal());//总的信息条数
		result.setRows(pageInfo.getList());//一页信息
		return result;
	}
	@Override
	public ItripResult saveHotel(Item item) {
		try {
			//补全
			//生成id
			final long id = IDUtils.genItemId();
			item.setId(id);
			//商品状态，1-正常，2-下架，3-删除
			item.setStatus(1);
			item.setCreated(new Date());
			item.setUpdated(new Date());
			itemMapper.insert(item);
			//保存详情
			ItemDesc itemDesc = new ItemDesc();
			itemDesc.setItemid(id);
			itemDesc.setItemdesc(item.getDesc());
			itemDesc.setCreated(new Date());
			itemDesc.setUpdated(new Date());
			itemDescMapper.insert(itemDesc);
			//---------------------将添加完成后的商品id发布到activemq中---------------------
			jmsTemplate.send(itemAddTopic,new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					//将添加的商品的id放入到activemq中（在search中可以通过id就能查询到对象，然后同步到solr中）
					TextMessage message = session.createTextMessage(id+"");
					return message;
				}
			});
			//如果添加没有出现异常
			return ItripResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ItripResult.build(500,"添加失败");
		}
	}
	@Override
	public ItripResult deleteHotel(long[] ids) {
		try {
			for (final long id: ids) {
				Item item = itemMapper.selectByPrimaryKey(id);
				if(item.getStatus().intValue()==1){
					jmsTemplate.send(itemDeleteQueue,new MessageCreator() {
						@Override
						public Message createMessage(Session session) throws JMSException {
							TextMessage message = session.createTextMessage(id + "");
							return message;
						}
					});
				}
				itemMapper.deleteByPrimaryKey(id);
			}
			return ItripResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ItripResult.build(500,"删除失败");
		}
	}
	@Override
	public ItripResult xiaJia(long[] ids) {
		try {
			for (final long id: ids) {
				Item item = new Item();
				item.setId(id);
				item.setStatus(2);
				item.setUpdated(new Date());
				itemMapper.updateByPrimaryKeySelective(item);
				//------------------------下架后将商品id发布到activemq中------------------------
				jmsTemplate.send(itemDeleteQueue,new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						TextMessage message = session.createTextMessage(id+"");
						return message;
					}
				});
			}
			return ItripResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ItripResult.build(500,"下架失败");
		}

	}
	@Override
	public ItripResult shangJia(long[] ids) {
		try {
			for (final long id: ids) {
				Item item = new Item();
				item.setId(id);
				item.setStatus(1);
				item.setUpdated(new Date());
				itemMapper.updateByPrimaryKeySelective(item);
				jmsTemplate.send(itemAddTopic,new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						TextMessage message = session.createTextMessage(id + "");
						return message;
					}
				});
			}
			return ItripResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ItripResult.build(500,"上架失败");
		}
	}
	@Override
	public ItripResult update(Item item) {
		try {
			final Long id = item.getId();
			//补全
			item.setUpdated(new Date());
			itemMapper.updateByPrimaryKeySelective(item);
			ItemDesc itemDesc = new ItemDesc();
			itemDesc.setItemid(id);
			itemDesc.setUpdated(new Date());
			itemDesc.setItemdesc(item.getDesc());
			itemDescMapper.updateByPrimaryKeySelective(itemDesc);
			//在redis中作修改
			jedisClient.set(ITEM+id+":BASE",JsonUtils.objectToJson(item));
			jedisClient.expire(ITEM+id+":BASE",EXPIRE_SECOND);
			jedisClient.set(ITEM+id+":DESC",JsonUtils.objectToJson(itemDesc));
			jedisClient.expire(ITEM+id+":DESC",EXPIRE_SECOND);
			//查询商品的状态
			Item im = itemMapper.selectByPrimaryKey(id);
			if(im.getStatus().intValue()==1){
				jmsTemplate.send(itemAddTopic,new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						TextMessage message = session.createTextMessage(id+"");
						return message;
					}
				});
			}
			return ItripResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ItripResult.build(500,"修改失败");
		}
		
	}	
}
