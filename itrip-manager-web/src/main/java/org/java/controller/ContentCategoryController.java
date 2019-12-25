package org.java.controller;

import java.util.List;

import org.java.common.pojo.EasyUITreeNodeResult;
import org.java.common.pojo.ItripResult;
import org.java.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;
	/**
	 * 内容分类信息展示
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNodeResult> categoryList(@RequestParam(name="id",defaultValue="0") int parentId){
		return contentCategoryService.getContentCategoryList(parentId);
	}
	/**
	 * 创建内容分类信息节点
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping("/content/category/create")
	@ResponseBody
	public ItripResult categoryCreate(@RequestParam(name="parentId") int parentId,
			@RequestParam(name="name") String name){
		return contentCategoryService.saveContentCategory(parentId,name);
	}
	/**
	 * 分类内容修改
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping("/content/category/update")
	@ResponseBody
	public ItripResult categoryUpdate(@RequestParam(name="id") int id,
			@RequestParam(name="name") String name){
		return contentCategoryService.updateContentCategory(id,name);
	}
	@RequestMapping("/content/category/delete/")
	@ResponseBody
	public ItripResult categoryDelete(@RequestParam(name="id") int id){
		return contentCategoryService.deleteContentCategory(id);
	}
	
}
