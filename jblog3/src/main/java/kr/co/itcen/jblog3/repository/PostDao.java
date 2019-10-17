package kr.co.itcen.jblog3.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog3.vo.PostVo;
import kr.co.itcen.jblog3.vo.UserVo;

@Repository
public class PostDao {

	@Autowired
	SqlSession sqlSession;
	
	public Boolean insert(UserVo vo) {
		
		int cnt = sqlSession.insert("blog.insert", vo);
		
		return cnt ==1;
	}

	public Boolean insertPost(PostVo postVo) {
		System.out.println("포스트 쓰기");

		int cnt = sqlSession.insert("post.insertPost", postVo);
		
		return cnt ==1;
	}


	// /jblog3/카테고리번호/글번호 의 글 상세정보
	public PostVo getPost(Long categoryNo, Long postNo) {
		
		Map<String, Object> map =new HashMap<String, Object>();
		
		map.put("categoryNo", categoryNo);
		map.put("postNo", postNo);
		PostVo vo =sqlSession.selectOne("post.getPost", map);
		System.out.println(vo);
		return vo;
	}

	// /jblog3/카테고리번호 의 글목록
	public List<PostVo> getPostList(Long categoryNo) {
		System.out.println("getPostList" + categoryNo);
		List<PostVo> list = sqlSession.selectList("post.caNoPost", categoryNo);
		System.out.println("categoryPost : " + list);
		return list;
	}
	// 전체 글목록
	public List<PostVo> allPostList(String userId) {
		List<PostVo> list = sqlSession.selectList("post.allPostList", userId);
		return list;
	}
	//전체 혹은 카테고리 별 첫번째(나중에쓴)글 
	public PostVo defaultPost(String userId, Long categoryNo) {
		
		System.out.println(userId + "  : "  + categoryNo);
		
		Map<String, Object> map =new HashMap<String, Object>();
		
		if(categoryNo.longValue()!=0) {
			map.put("categoryNo", categoryNo);
		}
		
		map.put("userId", userId);
		
		PostVo defaultPost = sqlSession.selectOne("post.defaultPost", map);
		System.out.println(defaultPost);
		return defaultPost;
	}

}
