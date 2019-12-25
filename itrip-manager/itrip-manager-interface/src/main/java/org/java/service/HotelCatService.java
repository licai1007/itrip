package org.java.service;

import java.util.List;

import org.java.common.pojo.EasyUITreeNodeResult;
import org.java.pojo.ItemCat;

public interface HotelCatService {
	/**
	 * 根据父节点的id查询该父节点下的子节点
	 * @param parentId
	 * @return
	 */
	List<EasyUITreeNodeResult> getTreeNodeByParentId(int parentId);
	/**
	 * 根据id获取类目对象
	 * @param id
	 * @return
	 */
	ItemCat getItemCatById(int id);
}
