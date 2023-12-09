package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.impl.porderServiceImpl;
import com.example.demo.vo.member;
import com.example.demo.vo.porder;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/porder")

public class porderController {
	@Autowired
	porderServiceImpl psi;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletResponse response;
	
	@GetMapping("/porder")
	public ModelAndView gotoPorder() {
		return new ModelAndView("/porder/porder");
	}
	
	@PostMapping("/add")
	public ModelAndView add(int a, int b, int c) {
		member m = (member) session.getAttribute("M");
		porder p = new porder(m.getName(),a,b,c);
		session.setAttribute("P", p);
		return new ModelAndView("/porder/confirm");
	}
	
	@RequestMapping("/finish")
	public ModelAndView finish() {
		porder p =(porder)session.getAttribute("P");
		psi.addPorder(p);
		return new ModelAndView("/porder/finish");
	}
	
	@GetMapping("/logout")
	public void logout() {
		session.removeAttribute("M");
		session.removeAttribute("P");
		
		try {
			response.sendRedirect("/index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
