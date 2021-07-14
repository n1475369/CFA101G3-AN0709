package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.model.ProDAO;
import com.product.model.ProDAOimpl;
import com.product.model.ProVO;

@WebServlet("/product/ProReadServlet")
public class ProReadServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8"); //回應為UTF-8
		ProDAO dao = new ProDAOimpl(); //new一個實作介面
		List<ProVO> list = dao.getAll(); //小吳流調用
//		out.println(list);
		PrintWriter out = response.getWriter(); //servlet輸出
		ObjectMapper mapper = new ObjectMapper(); //JSON
		String data = mapper.writeValueAsString(list); //JSON
		out.println(data);//讓我看看!!
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
