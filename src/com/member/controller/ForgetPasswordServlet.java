package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemService;
import com.member.model.MemVO;

@WebServlet("/member/forgetPasswordServlet")
public class ForgetPasswordServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String email = request.getParameter("email");
		MemService service = new MemService();
		MemVO user = service.findByUsername(email);
		if(user != null) {
			SendEmailPassword sep = new SendEmailPassword(email,user.getMem_password());
			sep.start();
			sep = null;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
