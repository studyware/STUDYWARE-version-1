package com.example.demo.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {
	
	private String username;
	private String testname;
	private int marks;
	private String email;
	private static final String admin_email="";
	private String mobile;
	private int total;

}
