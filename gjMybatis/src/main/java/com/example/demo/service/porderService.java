package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.mapper.porderMapper;
import com.example.demo.vo.porder;

public interface porderService {
	void addPorder(porder p);
	List<porder> findAll();

}
