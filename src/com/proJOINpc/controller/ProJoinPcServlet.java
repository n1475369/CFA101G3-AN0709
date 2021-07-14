package com.proJOINpc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proJOINpc.model.ProJoinPcDAOimpl;
import com.proJOINpc.model.ProJoinPcVO;
import com.sun.mail.handlers.text_html;


@WebServlet("/product/ProJoinPcServlet")
public class ProJoinPcServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//響應頭
		response.setContentType("text/html; charset=utf-8");
		//調用dao
		ProJoinPcDAOimpl dao = new ProJoinPcDAOimpl();
		List<ProJoinPcVO> list = dao.getAll();
		//輸出
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String result =mapper.writeValueAsString(list);
		out.print(result);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
