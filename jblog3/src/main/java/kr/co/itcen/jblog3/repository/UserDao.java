package kr.co.itcen.jblog3.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog3.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	SqlSession sqlSession;
	
	public Boolean insert(UserVo vo) {

		int cnt = sqlSession.insert("user.insert", vo);
		return cnt==1;
	}

	public Object checkId(String id) {
		UserVo result = sqlSession.selectOne("user.check", id);
		return result;
	}

	public UserVo getUser(UserVo vo) {
		UserVo result = sqlSession.selectOne("user.getUser", vo);
		return result;
	}

}
