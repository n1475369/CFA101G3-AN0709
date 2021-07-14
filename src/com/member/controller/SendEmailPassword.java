package com.member.controller;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import redis.clients.jedis.Jedis;

public class SendEmailPassword extends Thread{
	public String email;//收件人信箱
	public String memberPassword;//密碼
	
	public SendEmailPassword(String email,String memberPassword) {
		this.email = email;
		this.memberPassword = memberPassword;
	}
	
	@Override
	public void run() {
	      // Recipient's email ID needs to be mentioned.
	      String to = email;//change accordingly

	      // Sender's email ID needs to be mentioned
	      String from = "aka9988test@gmail.com";//change accordingly
	      final String username = "aka9988test@gmail.com";//change accordingly
	      final String password = "aazz7788";//change accordingly
	      
	      
	      
	      
	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");
	      props.setProperty("mail.debug", "true");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field(信箱標題)
	         message.setSubject("嫁給幸福-密碼通知信件");

	         // Now set the actual message

	         //信件內容以HTML格式傳出去
	         message.setContent("<h1>建議您登入後立即變更您的密碼</h1><br><p>您的密碼：</p>"+memberPassword,"text/html; charset=UTF-8" );
	         
	         
	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	}
}
