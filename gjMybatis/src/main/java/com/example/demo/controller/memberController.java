package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.impl.memberServiceImpl;
import com.example.demo.vo.member;

import jakarta.servlet.http.HttpSession;


@RestController

public class memberController {
	@Autowired
	memberServiceImpl msi;
	
	@Autowired
	HttpSession session;
	
	@PostMapping("/login")
	public ModelAndView gotoLogin(String username,String password) {
		member m = msi.Login(username, password);
		ModelAndView mav= null;
		if(m!=null) {
			session.setAttribute("M", m);
			mav= new ModelAndView("/loginSuccess");
		}else {
			mav= new ModelAndView("/loginError");
		}
		return mav;
	}
	
	@GetMapping("/toAddMember")
	public ModelAndView toAddMember() {
		return new ModelAndView("/addMember");
	}
	
	@PostMapping("/add")
	public ModelAndView add(String name,String username,String password) {
		ModelAndView mav= null;
		if(msi.UsernameRepeat(username)) {
			mav= new ModelAndView("/addMemberError");
		}else {
			msi.addMember(new member(name,username,password));
			mav= new ModelAndView("/addMemberSuccess");
		}
		return mav;
	}
	
	@RequestMapping("/queryMemberAll")
	public List<member> queryMemberAll(){
		return msi.queryMemberAll();
	}
}
