package cn.kkl.mall.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.kkl.mall.pojo.SearchResult;
import cn.kkl.mall.search.service.SearchService;

/**
 * @author Admin
 * item search controller
 */
@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@Value("${SEARCH_RESULT_ROWS}")
	private int rows;
	
	@RequestMapping(value="/search")
	public String searchItemList(String keyword,
			@RequestParam(defaultValue="1")Integer page,
			Model model) {
		try {
			if (StringUtils.isNotBlank(keyword)) {
				keyword=new String(keyword.getBytes("iso-8859-1"), "utf-8");
			}
			SearchResult result = searchService.search(keyword, page, rows);
			model.addAttribute("query", keyword);
			model.addAttribute("totalPages", result.getTotalPages());
			model.addAttribute("page", page);
			model.addAttribute("recourdCount", result.getRecordCount());
			model.addAttribute("itemList", result.getItemList());
			return "search";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/exception";
		}
	}

}
