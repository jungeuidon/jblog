package kr.co.itcen.jblog3.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.jblog3.dao.JSONResult;
import kr.co.itcen.jblog3.service.UserService;

@Controller("userCheckController")
@RequestMapping("/api/user")
public class CheckController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkid")
	public JSONResult checkEmail(@RequestParam(value="id", required=true, defaultValue="") String id) {
		Boolean exist = userService.checkId(id);
		return JSONResult.success(exist);
	}

}