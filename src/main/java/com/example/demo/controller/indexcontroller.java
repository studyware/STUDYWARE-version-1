package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Model.Test;
import com.example.demo.Repository.TestRepository;

@Controller
public class indexcontroller {

	 @Autowired
		private TestRepository testRepository;
	@RequestMapping("/")
	public String index(Model model) {
		List<Test> allTest = testRepository.findAll();
		 model.addAttribute("allTest",allTest);

			return "index";


		
	}
	
	

}
