package com.example.demo.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name= "notes")
public class Notes {
	
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO) 
	private Integer id;

	  private String name;

	  private String type;
	 
	  private int grade;
	  
	  private String subject;
	  
	
	  
	  
	  @Lob
	  private byte[] data;
	
	  

	  public Notes(String name, String type, byte[] data) {
	    this.name = name;
	    this.type = type;
	    this.data = data;
	   
	  } 
	  public Notes(String name, String type, byte[] data,int grade,String subject) {
		    this.name = name;
		    this.type = type;
		    this.data = data;
		    this.grade=grade;
		    this.subject=subject;
		   
		  } 
	  
	

}
