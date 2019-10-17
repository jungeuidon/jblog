package kr.co.itcen.jblog3.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog3.repository.CategoryDao;
import kr.co.itcen.jblog3.repository.PostDao;
import kr.co.itcen.jblog3.vo.PostVo;
import kr.co.itcen.jblog3.vo.UserVo;

@Service
public class PostService {
	
	@Autowired
	PostDao postDao;

	@Autowired
	CategoryDao categoryDao;
	
	
	//글쓰기
	public void insertPost(PostVo postVo) {
		postDao.insertPost(postVo);
	}

	// 카테고리별 글목록에서 상세글 가져오기, 카테고리 목록, 카테고리별 글목록 가져오기  
	public Map<String, Object> getPost(Long categoryNo, Long postNo, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("postInfo", postDao.getPost(categoryNo, postNo)); // 카테고리별 글목록에서 상세글 가져오기 - PostVo
		map.put("postList", postDao.getPostList(categoryNo));  // 카테고리별 글목록 가져오기  - List<PostVo>
		return map;
	}

	public  Map<String, Object> categoryPost(String userId, Long categoryNo) {
		System.out.println("PostService  :  " + "userId" + userId +" categoryNo: " + categoryNo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("defaultPost", postDao.defaultPost(userId, categoryNo));
		map.put("postList", postDao.getPostList(categoryNo));
		return map;
	}

	public Map<String, Object> getAllPost(String userId, Long categoryNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("defaultPost", postDao.defaultPost(userId, categoryNo)); // 기본글
		map.put("allPostList", postDao.allPostList(userId)); //전체리스트
		return map;
	}

	
	

}
