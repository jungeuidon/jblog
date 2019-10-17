package kr.co.itcen.jblog3.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.itcen.jblog3.service.BlogService;
import kr.co.itcen.jblog3.service.CategoryService;
import kr.co.itcen.jblog3.service.UserService;
import kr.co.itcen.jblog3.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
//	@Qualifier("userService")   //객체가 다르기때문에 굳이 안써줘도된다.
	private UserService userService;
	
	@Autowired
//	@Qualifier("blogService")   //객체가 다르기때문에 굳이 안써줘도된다.	
	private BlogService blogService;
	
	@Autowired
//	@Qualifier("categoryService")   //객체가 다르기때문에 굳이 안써줘도된다.
	private CategoryService categoryService;
	
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo,
						BindingResult result,
						Model model) {
		;
		
		if(result.hasErrors()) {
			model.addAttribute(result.getModel());
			return "user/join";
		}
		
		userService.join(vo);
		blogService.join(vo);
		categoryService.joinFirst(vo);
		
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login")
	public String login() {
		return "user/login";
	}
	
	
	@RequestMapping("/logout")
	public String logout() {
		return "redirect:/main";
	}
}
