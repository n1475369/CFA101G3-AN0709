package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemService;
import com.member.model.MemVO;


@WebServlet("/member/registerSellerServlet")
public class RegisterSellerServlet extends HttpServlet {
    
	//註冊賣家帳號
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		MemService service = new MemService();
		MemVO member = new MemVO();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		Integer role = Integer.parseInt(request.getParameter("role"));
		String shopname = request.getParameter("shopname");
		String city = request.getParameter("city");
		String cityarea = request.getParameter("cityarea");
		String street = request.getParameter("street");
		
		member.setMem_username(username);
		member.setMem_password(password);
		member.setMem_name(name);
		member.setMem_role(role);
		member.setMem_phone(phone);
		member.setMem_shop_name(name);
		member.setMem_city(city);
		member.setMem_cityarea(cityarea);
		member.setMem_street(street);
		int count = service.register(member);
		if(count == 1) {
			SendEmail se = new SendEmail(username);
			se.start();
			se = null;
			RequestDispatcher rd = request.getRequestDispatcher("/member/loginServlet");
			rd.forward(request, response);
		}else {
			response.getWriter().print("0");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
