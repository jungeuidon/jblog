package kr.co.itcen.jblog3.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog3.repository.BlogDao;
import kr.co.itcen.jblog3.repository.CategoryDao;
import kr.co.itcen.jblog3.repository.PostDao;
import kr.co.itcen.jblog3.vo.BlogVo;
import kr.co.itcen.jblog3.vo.PostVo;
import kr.co.itcen.jblog3.vo.UserVo;

@Service
public class BlogService {
	
	@Autowired
	BlogDao blogDao;

	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	PostDao postDao;
	
	//회원가입시 블로그생성
	public void join(UserVo vo) {
		blogDao.insert(vo);
	}

	//blog-main 에서 카테고리목록, 블로그 타이틀,로고 가져오기
	public Map<String, Object> getBlogInfo(String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryList", categoryDao.getCategory(userId)); // 카테고리목록
		map.put("blogVo", blogDao.getVo(userId)); //타이틀 , 로고 
		return map;
	}

	public void updateBlog(String userId, BlogVo blogVo) {
		blogDao.updateBlog(userId, blogVo);
	}


	
	
	

}
