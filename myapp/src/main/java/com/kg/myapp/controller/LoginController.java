package com.kg.myapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.kg.myapp.service.IUsersService;
import com.kg.myapp.vo.UsersVO;

@Controller
public class LoginController {
	
	@Autowired
	IUsersService userService;
	
	@Autowired 
	BCryptPasswordEncoder bpe;
	
	@PostMapping(value="/login.do")
	public String login(String id, String pw, Model model, HttpSession session) {
		String dbpw= userService.getPassword(id);
		if(dbpw != null & bpe.matches(pw, dbpw)) {
			UsersVO users = userService.getUsers(id);
			session.setAttribute("users", users);
			session.setAttribute("userid", id);
			return "redirect:/emp";
		} else {
			model.addAttribute("message","아이디 또는 비밀번호가 잘못되었습니다.");
			return "/login";
		}
	}
	
	@PostMapping(value="/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
}













