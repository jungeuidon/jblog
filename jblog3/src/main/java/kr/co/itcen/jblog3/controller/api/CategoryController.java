package kr.co.itcen.jblog3.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.jblog3.service.CategoryService;
import kr.co.itcen.jblog3.service.UserService;
import kr.co.itcen.jblog3.vo.CategoryVo;

@Controller("CategoryAjaxController")
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService; 
	
	@ResponseBody
	@RequestMapping(value="/categoryInsert", method=RequestMethod.POST)
	public String categoryInsert(@RequestParam("userId") String userId,
								@ModelAttribute CategoryVo vo
								) {
		
		vo.setId(userId);
		System.out.println(vo);
		categoryService.categoryInsert(vo);
		
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("/categoryList")
	public Map<String, Object> categoryList(@RequestParam(value="userId") String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		System.out.println("userId" + userId);
		map = categoryService.categoryInfo(userId);
		
		System.out.println(map);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/categoryDel", method=RequestMethod.POST)
	public String categoryDelete(@RequestParam(value="userId") String userId,
								@RequestParam(value="no") int no) {
		
		System.out.println("userId " + userId + " no " + no);
		categoryService.delCategory(userId, no);
		return "success";
	}

}