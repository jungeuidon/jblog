package kr.co.itcen.jblog3.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog3.vo.CategoryVo;
import kr.co.itcen.jblog3.vo.UserVo;

@Repository
public class CategoryDao {
	
	@Autowired
	SqlSession sqlSession;

	//회원가입시 카테고리 기본생성
	public Boolean joinFirst(UserVo vo) {
		
		int cnt = sqlSession.insert("category.joinFirst", vo);
		
		return cnt==1;
	}
	
	//카테고리 목록
	public List<CategoryVo> getCategory(String userId) {
		List<CategoryVo> list = sqlSession.selectList("category.getList", userId);
		
		return list;
	}
	
	//새 카테고리 만들기
	public Boolean insert(CategoryVo categoryVo) {

		int cnt = sqlSession.insert("category.insert", categoryVo);
		
		return cnt ==1;
	}

	public Boolean delete(String userId, int no) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("no", no);
		int cnt = sqlSession.delete("category.delete", map);
		return cnt ==1;
	}

}
