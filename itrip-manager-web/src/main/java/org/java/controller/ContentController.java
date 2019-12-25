package org.java.controller;

import org.java.common.pojo.EasyUIDataGrideResult;
import org.java.common.pojo.ItripResult;
import org.java.content.service.ContentService;
import org.java.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	/**
	 * 根据分类id查询内容列表
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGrideResult getContentsByCatId(@RequestParam(name="categoryid") int categoryid,
			@RequestParam(name="page") int page,@RequestParam(name="rows") int rows){
		return contentService.getContents(categoryid,page,rows);
	}
	/**
	 * 删除分类内容
	 * @param ids
	 * @return
	 */
	@RequestMapping("/content/delete")
	@ResponseBody
	public ItripResult deleteContentById(@RequestParam(name="ids") int[] ids){
		return contentService.deleteContents(ids);
	}
	/**
	 * 添加内容
	 * @param content
	 * @return
	 */
	@RequestMapping("/content/save")
	@ResponseBody
	public ItripResult contentSave(Content content){
		return contentService.saveContent(content);
	}
	@RequestMapping(value="/rest/content/edit",method=RequestMethod.POST)
	@ResponseBody
	public ItripResult edit(Content content){
		return contentService.updateContent(content);
	}
}
