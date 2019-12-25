package org.java.content.service;

import java.util.List;

import org.java.common.pojo.EasyUITreeNodeResult;
import org.java.common.pojo.ItripResult;

public interface ContentCategoryService {
	/**
	 * 查询获取到内容分类EasyUI的视图对象
	 * @return
	 */
	List<EasyUITreeNodeResult> getContentCategoryList(int parentId);
	/**
	 * 添加内容分类对象
	 * @param parentId
	 * @param name
	 * @return
	 */
	ItripResult saveContentCategory(int parentId,String name);
	/**
	 * 修改内容分类信息
	 * @param id
	 * @param name
	 * @return
	 */
	ItripResult updateContentCategory(int id,String name);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	ItripResult deleteContentCategory(int id);
}
