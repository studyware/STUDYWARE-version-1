package com.example.demo.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
    private JavaMailSender sender;
	
	
	
	
	
	@Override
	public String sendMail(String testname,String mobile,String username,String email,int marks) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        StringBuilder body  = new StringBuilder(); 
        
        body.append("Greetings "+username+" :");
        body.append("\n \n STUDY WARE is happy to welcome you. \n\n ");
        body.append("The test results for the test :"+testname+" is "+marks);
        body.append(".\n We are happy to Inform you that");
        body.append("Classes for 10th,11th,12th for all subject intergated with coaching for Neet and CA Foundation,so you are not needed to spend your valuable time on  attending seperate classes"
        		+ " for special coachings" + 
        		 
        		"Classes are taken by interactive engineers and professional accountants to make a class in conceptual and experimental way\r\n" + 
        		"" + 
        		"Classes are also taken for engineering subjects  and accountancy \r\n" + 
        		"");
        body.append("\n \nFor Further Details Please contact us in the below address: \n");
        body.append("\nContact us:\n Sabarish :"+ "88389 20026 "+"97899 04992\r\n" );
        body.append("\n\n\n Thanks & Regards \n STUDY WARE\n36/1 Sachidhanandhamstreet,\r\n" + 
        		"Kosapet,\r\n" + 
        		"Purasaiwalkam,\r\n" + 
        		"Chennai-600 012 \r \n");
        body.append("Your Details : \r \n"+"Name :"+username+"\r \n"+"Mobile : "+mobile);
        
        try {
            helper.setTo(email);
            helper.setBcc("mrsciencetest@gmail.com");
            helper.setCc("mrsciencetest@gmail.com");
            helper.setText(body.toString());
            helper.setSubject(testname+"-Result");
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error while sending mail ..";
        }
        sender.send(message);
        return "Mail Sent Success!";
    }
	

}
