package com.example.demo.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import com.example.demo.DTO.UserTestCreationDto;
import com.example.demo.Model.Questions;
import com.example.demo.Model.Result;
import com.example.demo.Model.Test;
import com.example.demo.Model.User_platform_questions;
import com.example.demo.Repository.QuestionsRepository;
import com.example.demo.Repository.TestRepository;
import com.example.demo.service.MailService;
import com.example.demo.service.UserTestService;
@Controller
@SessionAttributes("test")
public class StudentTestController {
	
	@Autowired
	UserTestService userTestService;
	
	@Autowired
	MailService mailService;
	
	
	UserTestCreationDto userTestCreationDto = new UserTestCreationDto();

	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private TestRepository testRepository;
	
	@Autowired
	private QuestionsRepository questionsRepository;
	
	
	
	
	
	
	
	
	

	@GetMapping("/id/{id}")
	public String getById(@PathVariable("id") int id, Model model) {

		//String getTestUrl = "http://localhost:8081/id/" + id;

	//	Test response = restTemplate.getForObject(getTestUrl, Test.class);

		
		Test response = testRepository.findById(id).orElse(null);
		model.addAttribute("test", response);
		
		userTestCreationDto.addTestDetails(response.getTestname(),response.getGrade(),response.getTotal_marks(),response.getTime());
		
		
		  List<User_platform_questions> user_platform_questions = new ArrayList<>();
		  
		  for(Questions quest : response.getQuestions()) {
		  
		  User_platform_questions user_questions = new User_platform_questions();
		  
		  
		  user_questions.setQuestion(quest.getQuestion());
		  user_questions.setOption_1(quest.getOption_1());
		  user_questions.setOption_2(quest.getOption_2());
		  user_questions.setOption_3(quest.getOption_3());
		  user_questions.setOption_4(quest.getOption_4());
		  user_questions.setSubject(quest.getSubject());
		  user_questions.setExplanation(quest.getExplanation());
		  user_questions.setId(quest.getId());
		  user_questions.setUser_answer(new String());
		  quest.setAnswer(quest.getAnswer());
		  
		  
		  user_platform_questions.add(user_questions);
		  
		  }
		  
		  userTestCreationDto.addQuestions(user_platform_questions);
		
		
		model.addAttribute("userTest", userTestCreationDto);

		return "testSeries";

	}

	@PostMapping("/evaluate")
	public String evaluate(Model model,@ModelAttribute("userTest") UserTestCreationDto userTestCreationDto,@SessionAttribute("test") Test test) {

		Result result = new Result();
		int marks = userTestService.evaluate(test.getQuestions(), userTestCreationDto.getQuestions());
		
		result.setUsername(userTestCreationDto.getUsername());
		result.setEmail(userTestCreationDto.getEmail());
		result.setMarks(marks);
		result.setMobile(userTestCreationDto.getMobile());
		result.setTotal(test.getTotal_marks());
		result.setTestname(test.getTestname());
		
		model.addAttribute("result", result);
		
		model.addAttribute("questions", test.getQuestions());
		
		String mail = mailService.sendMail(result.getTestname(),result.getMobile(),result.getUsername(),result.getEmail(),result.getMarks());
		
		return "result";

	}
	


}
