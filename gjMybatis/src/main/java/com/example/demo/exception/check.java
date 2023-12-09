package com.example.demo.exception;

public class check extends Exception {
	private String errormsg;
	public check(String msg) {
		this.errormsg=msg;
	}

}
