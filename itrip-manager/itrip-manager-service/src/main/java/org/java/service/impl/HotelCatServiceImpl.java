package org.java.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.java.common.pojo.EasyUITreeNodeResult;
import org.java.mapper.ItemCatMapper;
import org.java.pojo.ItemCat;
import org.java.pojo.ItemCatCriteria;
import org.java.pojo.ItemCatCriteria.Criteria;
import org.java.service.HotelCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelCatServiceImpl implements HotelCatService{
	//自动注入分类mapper
	@Autowired
	private ItemCatMapper itemCatMapper;
	@Override
	public List<EasyUITreeNodeResult> getTreeNodeByParentId(int parentId) {
		ItemCatCriteria example = new ItemCatCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andParentidEqualTo(parentId);//添加查询条件
		List<ItemCat> list = itemCatMapper.selectByExample(example);
		
		//将查询结果转换成EasyUITreeNodeResult
		List<EasyUITreeNodeResult> resultList = new ArrayList<EasyUITreeNodeResult>();
		for(ItemCat cat:list){
			EasyUITreeNodeResult result = new EasyUITreeNodeResult();
			result.setId(cat.getId());
			result.setText(cat.getName());
			result.setState(cat.getIsparent()?"closed":"open");
			//添加到集合中
			resultList.add(result);
		}
		return resultList;
	}
	@Override
	public ItemCat getItemCatById(int id) {
		return itemCatMapper.selectByPrimaryKey(id);
	}

}
