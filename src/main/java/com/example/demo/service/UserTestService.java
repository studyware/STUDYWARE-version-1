package com.example.demo.service;

import java.util.List;

import com.example.demo.Model.Questions;
import com.example.demo.Model.Test;
import com.example.demo.Model.User_platform_questions;

public interface UserTestService {
	
public Test userTest(Test test);
	
	public int evaluate(List<Questions>questions,List<User_platform_questions> user_platform_questions );

}
