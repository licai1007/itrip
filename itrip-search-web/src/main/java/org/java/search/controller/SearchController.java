package org.java.search.controller;

import org.java.common.pojo.SearchResult;
import org.java.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	@RequestMapping("/search")
	public String search(@RequestParam(name="q") String queryString,
			@RequestParam(name="page",defaultValue="1") Integer pageNow,
			@RequestParam(name="pageSize",defaultValue="12") Integer pageSize
			,Model model) throws Exception{
		//根据条件查询
		SearchResult result = searchService.getSearchResult(queryString,pageNow,pageSize);
		model.addAttribute("query",queryString);
		model.addAttribute("page",pageNow);
		model.addAttribute("totalPages",result.getTotalPages());
		model.addAttribute("itemList",result.getItemList());
		return "search";
	}
	/**
	 * 进入到异常页面中
	 */
	@RequestMapping("/error")
	public String error(){
		return "error/exception";
	}
}
