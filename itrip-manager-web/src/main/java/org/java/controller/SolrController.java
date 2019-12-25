package org.java.controller;

import org.java.common.pojo.ItripResult;
import org.java.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SolrController {
	@Autowired
	private SearchService searchService;
	@RequestMapping(value="/index/import",method=RequestMethod.POST)
	@ResponseBody
	public ItripResult solrImport(){
		return searchService.saveItemsToSolr();
	}
	
}
