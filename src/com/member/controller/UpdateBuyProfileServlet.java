package com.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemService;
import com.member.model.MemVO;


@WebServlet("/member/updateBuyProfileServlet")
public class UpdateBuyProfileServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		MemVO user = (MemVO)session.getAttribute("user");
		if(user!= null) {
			String username = user.getMem_username();
			String name = request.getParameter("name-form");
			String phone = request.getParameter("phone-form");
			String city = request.getParameter("city");
			String cityarea = request.getParameter("cityarea");
			String street = request.getParameter("street-form");
			String reg = "^09[0-9]{8}$";
			if(name.trim().isEmpty()||phone.trim().isEmpty()||!phone.matches(reg)) {
				response.getWriter().print("0");
				return;
			}
			user.setMem_name(name);
			user.setMem_phone(phone);
			user.setMem_city(city);
			user.setMem_cityarea(cityarea);
			user.setMem_street(street);
			MemService service = new MemService();
			boolean updateBuyProfile = service.updateBuyProfile(user);
			if(updateBuyProfile) {
				response.getWriter().print("1");
			}else {
				response.getWriter().print("0");
			}
		}else {
			response.getWriter().print("0");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
