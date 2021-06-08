package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.DTO.QuestionCreationDto;
import com.example.demo.Model.Questions;
import com.example.demo.Model.Residue;
import com.example.demo.Model.Test;
import com.example.demo.Model.searchTest;
import com.example.demo.Repository.QuestionsRepository;
import com.example.demo.Repository.TestRepository;

@Controller
@SessionAttributes("test")
public class TestController {

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private QuestionsRepository questionsRepository;

	@RequestMapping("/admin")
	public String admin(Model model) {

		Test test = new Test();
		model.addAttribute("test", test);

		return "admin";
	}

	@RequestMapping("/preface")
	public String quiz(@ModelAttribute("test") Test test, Model model) {

		System.out.println(test.getTestname());

		model.addAttribute("test", test);

		QuestionCreationDto questionForm = new QuestionCreationDto();

		for (int i = 0; i < test.getTotal_questions(); i++) {

			Questions question = new Questions();
			questionForm.addQuestion(question);

		}

		model.addAttribute("questions", questionForm);

		return "quiz";
	}

	@RequestMapping("/insertquiz")
	public String insertTest(@ModelAttribute("questions") QuestionCreationDto questionForm, Model model,
			@SessionAttribute("test") Test test) {

		System.out.println(test.getTestname());

		test.setQuestions(questionForm.getQuestions());

		testRepository.save(test);

		return "redirect:/admin";

	}

	@RequestMapping("/allTest")
	public String testForm(Model model) {
		model.addAttribute("searchTest", new searchTest());
		return "selectTest";
	}

	@RequestMapping("/searchTest")
	public String selectTest(@ModelAttribute("searchTest") searchTest SearchTest, Model model) {

		List<Test> allTest = testRepository.findAll();
		List<Test> filterTest = new ArrayList<>();

		String general = "General";

		
		  for(Test test:allTest)
			  {
			  
			  	if(SearchTest.getSubject().equalsIgnoreCase(general))
			  	{
			  		if(test.getGrade()==SearchTest.getStd())
			  		{
			  			 filterTest.add(test);
						 	
			  		}
			  	}
			  
				  
			  	else if(test.getGrade()==SearchTest.getStd()&&(test.getSubject_name().equalsIgnoreCase(SearchTest.getSubject()))) 
				  {
					  filterTest.add(test);
					  System.out.println(test.getSubject_name());
				  
				  }
				  
			  }
				  
		 

		/*
		 * for (Test test : allTest) {
		 * 
		 * if (test.getGrade() == SearchTest.getStd()) { if
		 * (test.getSubject_name().equalsIgnoreCase(SearchTest.getSubject())) {
		 * 
		 * filterTest.add(test); System.out.println(test.getSubject_name());
		 * 
		 * } else if (test.getSubject_name().equalsIgnoreCase(general)) {
		 * filterTest.add(test); System.out.println(test.getSubject_name());
		 * 
		 * }
		 * 
		 * } }
		 */
		model.addAttribute("allTest", filterTest);

		return "Test";
	}

	@RequestMapping("/tests")
	public String filteredtest(Model model) {
		List<Test> allTest = testRepository.findAll();
		model.addAttribute("allTest", allTest);
		return "selectTest";
	}

	@GetMapping("/delteTest")
	public String getDeletetest(Model model) {

		Residue testname = new Residue();
		model.addAttribute("name", testname);

		return "delete_test";
	}

	@PostMapping("/deleteTest")
	@Transactional
	public String deleteTest(@ModelAttribute("name") Residue testname) {

		testRepository.deleteByTestname(testname.getId());

		return "redirect:/admin";
	}

}
