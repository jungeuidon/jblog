package kr.co.itcen.jblog3.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog3.repository.UserDao;
import kr.co.itcen.jblog3.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public void join(UserVo vo) {
		userDao.insert(vo);
	}

	public Boolean checkId(String id) {
		return userDao.checkId(id) != null;
	}

	public UserVo getUser(UserVo vo) {
		return userDao.getUser(vo);
	}
	

}
