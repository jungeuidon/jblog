package kr.co.itcen.jblog3.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog3.repository.CategoryDao;
import kr.co.itcen.jblog3.vo.CategoryVo;
import kr.co.itcen.jblog3.vo.UserVo;


@Service
public class CategoryService {

	@Autowired
	CategoryDao categoryDao;
	
	//회원가입시 임시 카테고리 생성
	public void joinFirst(UserVo vo) {
		categoryDao.joinFirst(vo);
	}
	
	//카테고리 정보
		public Map<String,Object> categoryInfo(String userId) {
			
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("categoryList", categoryDao.getCategory(userId));
			
			return map;
		}
		
	//카테고리 생성
	public void categoryInsert(CategoryVo categoryVo) {
			
		categoryDao.insert(categoryVo);
	}

	public void delCategory(String userId, int no) {
		
		categoryDao.delete(userId, no);
	}

}
