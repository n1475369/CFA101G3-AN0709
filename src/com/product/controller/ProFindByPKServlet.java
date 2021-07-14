package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemVO;
import com.product.model.ProService;
import com.product.model.ProVO;

/**
 * Servlet implementation class ProFindByPKServlet
 */
@WebServlet("/product/ProFindByPKServlet")
public class ProFindByPKServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//設置頭
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		//取得參數
		Integer pro_id = Integer.parseInt(request.getParameter("pro_id").trim());
		System.out.println(pro_id);
		
		//從DB找到資料
		ProService proSvc = new ProService();
		ProVO proVO = proSvc.getOnePro(pro_id);
		System.out.println(proVO);
		//開始輸出
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper(); //JSON
		String data = mapper.writeValueAsString(proVO); //JSON
		out.println(data);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
