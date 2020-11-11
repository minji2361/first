package com.kg.myapp.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kg.myapp.service.IUsersService;
import com.kg.myapp.vo.UsersVO;

@Controller
@RequestMapping(value="/users")
public class UsersController {
	
	@Autowired
	IUsersService userService;
	
	@GetMapping(value="/signin")
	public String signin() {
		return "/users/signin";
	}
	
	@GetMapping(value="/insert")
	public void insertUsers(Model model) {
		// UsersVO users = new UsersVO(); -> validate 할 때 필요
	}
	
	@PostMapping(value="/insert")
//	public String insertUsers(@ModelAttribute("users") @Valid UsersVO users, BindingResult result) {
//		
//	}
	public String insertUsers(@RequestParam MultipartFile file, UsersVO users, 
			RedirectAttributes redirectAttributes) {
		if(file!=null && !file.isEmpty()) {
			try {
				users.setPicture(file.getBytes());
				users.setPictureSize(file.getSize());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		userService.insertUsers(users);
		redirectAttributes.addFlashAttribute("message", "회원 가입 완료");
		return "redirect:/login";
	}
	// 입력이 끝나면 redirect -> model을 쓸 수 없다
	
//	@GetMapping(value="/login")
//	public String getPassword(String userid, Model model) { 
//		userService.getPassword(userid);
//		return 
//		
//	}

	@PostMapping(value="/check")
	@ResponseBody
	public String idCheck(String userid) {
		return userService.idCheck(userid)==0 ? "true" : null;
	}
}










