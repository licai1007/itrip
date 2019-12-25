package org.java.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.java.common.pojo.EasyUITreeNodeResult;
import org.java.common.pojo.ItripResult;
import org.java.content.service.ContentCategoryService;
import org.java.mapper.ContentCategoryMapper;
import org.java.pojo.ContentCategory;
import org.java.pojo.ContentCategoryCriteria;
import org.java.pojo.ContentCategoryCriteria.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{
	@Autowired
	private ContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EasyUITreeNodeResult> getContentCategoryList(int parentId) {
		ContentCategoryCriteria example = new ContentCategoryCriteria();
		Criteria criteria = example.createCriteria();
		//添加查询条件
		criteria.andParentidEqualTo(parentId);
		//执行查询
		List<ContentCategory> list = contentCategoryMapper.selectByExample(example);
		
		List<EasyUITreeNodeResult> result = new ArrayList<EasyUITreeNodeResult>();
		//遍历list集合
		for (ContentCategory cat : list) {
			EasyUITreeNodeResult node = new EasyUITreeNodeResult();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsparent()?"closed":"open");
			//添加到result集合中
			result.add(node);
		}
		return result;
	}
	@Override
	public ItripResult saveContentCategory(int parentId, String name) {
		ContentCategory cat = new ContentCategory();
		//属性补全
		cat.setParentid(parentId);
		cat.setName(name);
		//状态。可选值：1（正常），2（删除）
		cat.setStatus(1);
		cat.setSortorder(1);
		cat.setIsparent(false);//新增加的节点永远都是一个子节点
		//根据父节点的id获取到父节点对象
		ContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(parent.getIsparent()==false){
			//表示当前的节点是添加到一个原始是子节点的节点对象中，那么现在就不再是子节点，将变成一个父节点
			parent.setIsparent(true);
			parent.setUpdated(new Date());
			//更新到数据库中
			contentCategoryMapper.updateByPrimaryKey(parent);
		}
		cat.setCreated(new Date());
		cat.setUpdated(new Date());
		//提交到数据库(需要在mapper.xml中添加配置useGeneratedKeys="true" keyProperty="id")，在执行添加成功后，会将自动生成的id返回到要添加的对象中
		contentCategoryMapper.insert(cat);
		return ItripResult.ok(cat);
	}
	@Override
	public ItripResult updateContentCategory(int id, String name) {
		ContentCategory cat = new ContentCategory();
		cat.setId(id);
		cat.setName(name);
		cat.setUpdated(new Date());
		try {
			contentCategoryMapper.updateByPrimaryKeySelective(cat);
			return ItripResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ItripResult.build(500,"修改内容分类失败");
		}
	}
	@Override
	public ItripResult deleteContentCategory(int id) {
		try {
			//根据id查询对象
			ContentCategory cat = contentCategoryMapper.selectByPrimaryKey(id);
			if(cat.getIsparent()){
				//1.如果要删除的节点是父节点：删除的时候判断该要删除的节点对象是否有子节点，如果有子节点，那么需要删除子节点
				//查询得到所有的子节点
				ContentCategoryCriteria example = new ContentCategoryCriteria();
				Criteria criteria = example.createCriteria();
				criteria.andParentidEqualTo(id);//查询当前节点下的子节点
				List<ContentCategory> childNodes = contentCategoryMapper.selectByExample(example);
				//判断是否有子节点
				if(childNodes != null && childNodes.size()>0){
					//删除子节点
					for (ContentCategory contentCategory : childNodes) {
						contentCategoryMapper.deleteByPrimaryKey(contentCategory.getId());
					}
				}
			}else{
				//2.如果要删除的节点是子节点：该子节点的父节点有多个子节点，那么父节点不用做处理，如果该父节点只有一个子节点，那么该子节点删除后父节点变成子节点
				//获取到当前要删除节点的父节点
				ContentCategory parent = contentCategoryMapper.selectByPrimaryKey(cat.getParentid());
				ContentCategoryCriteria example = new ContentCategoryCriteria();
				Criteria criteria = example.createCriteria();
				criteria.andParentidEqualTo(parent.getId());
				List<ContentCategory> childNodes = contentCategoryMapper.selectByExample(example);
				if(childNodes != null && childNodes.size()==1){
					//只有一个子节点就是当前要删除的这个节点
					//将这个父节点的isparent修改为false
					parent.setIsparent(false);
					contentCategoryMapper.updateByPrimaryKey(parent);
				}
			}
			//删除当前要删除的节点对象
			contentCategoryMapper.deleteByPrimaryKey(id);
			return ItripResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ItripResult.build(500,"删除分类信息失败");
		}
	}

}
