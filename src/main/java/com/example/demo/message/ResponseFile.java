package com.example.demo.message;

public class ResponseFile {
	
	 private String name;
	  private String url;
	  private String type;
	  private long size;
	  private int grade;
	  private String subject;

	  public ResponseFile(String name, String url, String type, long size,int grade,String subject) {
	    this.name = name;
	    this.url = url;
	    this.type = type;
	    this.size = size;
	    this.grade=grade;
	    this.subject=subject;
	  }

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
