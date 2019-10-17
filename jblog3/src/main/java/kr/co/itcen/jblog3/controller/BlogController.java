package kr.co.itcen.jblog3.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog3.security.AuthUser;
import kr.co.itcen.jblog3.service.BlogService;
import kr.co.itcen.jblog3.service.CategoryService;
import kr.co.itcen.jblog3.service.FileuploadService;
import kr.co.itcen.jblog3.service.PostService;
import kr.co.itcen.jblog3.vo.BlogVo;
import kr.co.itcen.jblog3.vo.CategoryVo;
import kr.co.itcen.jblog3.vo.PostVo;
import kr.co.itcen.jblog3.vo.UserVo;

@Controller
@RequestMapping("/{userId:(?!assets).*}")
public class BlogController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	FileuploadService fileuploadService;

	
	// 블로그 메인
		@RequestMapping(value = {"","/{pathNo1}","/{pathNo1}/{pathNo2}" })
		public String blogMain(@PathVariable(value = "userId") String userId,
							@PathVariable(value = "pathNo1") Optional<Long> pathNo1,
							@PathVariable(value = "pathNo2") Optional<Long> pathNo2,
							Model model) {
			
			Long categoryNo = 0L;
			Long postNo = 0L;
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			if(pathNo2.isPresent()) { // 글번호
				model.addAttribute("postno", "postno");
				System.out.println("PATHNO2 : " + pathNo2); //PATHNO2 : Optional[1]
				postNo = pathNo2.get(); 					
				System.out.println("postNo : " + postNo);   //postNo : 1
				categoryNo = pathNo1.get();
				map.putAll(postService.getPost(categoryNo, postNo, userId)); // jblog3/mantopuma/categoryno/postno
				model.addAttribute("categorynpostno", "categorynpostno");
			}else if (pathNo1.isPresent()) { // 카테고리번호
				categoryNo = pathNo1.get();
				map.putAll(postService.categoryPost(userId, categoryNo));   // jblog3/mantopuma/categoryno
				model.addAttribute("categoryNo", "categoryNo");
			}else {
				map.putAll(postService.getAllPost(userId, categoryNo));			 // jblog3/mantopuma
				map.put("defaultd", "default");
			}
			
			map.putAll(blogService.getBlogInfo(userId)); //카테고리 목록 , 블로그로고 이미지
			
			if(map.get("blogVo")==null) {
				return "error/404";
			}

			model.addAllAttributes(map);
			
			return "blog/blog-main";
		}
	
	
	//블로그 manage로 이동
	@RequestMapping("/manage")
	public String manage(@ModelAttribute BlogVo blogVo,
						@AuthUser UserVo authUser,
						@PathVariable(value="userId") String userId,
						Model model) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map = blogService.getBlogInfo(userId);
		
		model.addAllAttributes(map);
		
		
		
		return "blog/blog-admin-basic";
	}
	
	//블로그 업데이트
	@RequestMapping(value="/manage", method=RequestMethod.POST)
	public String manage(@AuthUser UserVo authUser,
						 @PathVariable(value="userId") String userId,
						 @ModelAttribute BlogVo blogVo,
						 @RequestParam(value="logoFile") MultipartFile multipartFile,
						 Model model) {
		
		
		
		
		if(!multipartFile.isEmpty()) {
			String url = fileuploadService.restore(multipartFile);
			blogVo.setLogo(url);
		}else {
			blogVo.setLogo("default.jpg");
		}
		blogService.updateBlog(userId, blogVo);
		
		
		return "redirect:/" + userId;
	}
	
	//카테고리폼으로 이동
	@RequestMapping(value="/category", method=RequestMethod.GET)
	public String category(@AuthUser UserVo authUser,
						   @PathVariable(value="userId") String userId,
						   @ModelAttribute CategoryVo categoryVo,
//						   @RequestParam("d") String userId,
						   Model model) {
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(blogService.getBlogInfo(userId)); //카테고리 목록 , 블로그로고 이미지
		map.putAll(categoryService.categoryInfo(userId));
		model.addAllAttributes(map);
		
		return "blog/blog-admin-category";
	}
	
	
	
	
	
	
	//글쓰기폼으로 이동
	@RequestMapping("/write")
	public String write(@AuthUser UserVo authUser,
						@PathVariable(value="userId") String userId,
						@ModelAttribute PostVo postVo,
						Model model) {
		
		Map<String, Object> map = new HashMap<String, Object>();	
		map.putAll(categoryService.categoryInfo(userId));
		model.addAllAttributes(map);
		
		return "blog/blog-admin-write";
	}
	
	//글쓰기 
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String blogWrite(@AuthUser UserVo authUser,
						@PathVariable(value="userId") String userId,
						@ModelAttribute @Valid PostVo postVo,
						Model model) {
		System.out.println("쓰기컨트롤러");
		postService.insertPost(postVo);
		
		return "redirect:/" + userId;
	}
	
	
}
