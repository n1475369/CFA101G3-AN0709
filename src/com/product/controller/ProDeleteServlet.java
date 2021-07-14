package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.model.ProDAO;
import com.product.model.ProDAOimpl;
import com.product.model.ProService;
import com.product.model.ProVO;


@WebServlet("/product/ProDeleteServlet")
public class ProDeleteServlet extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//設置頭
		response.setContentType("text/html;charset=utf-8");
		
		//取得參數
		Integer pro_id = Integer.parseInt(request.getParameter("pro_id").trim());
		System.out.println(pro_id);
		//開始刪除參數
		ProService proSvc = new ProService();
		proSvc.deletePro(pro_id);
		//輸出
		PrintWriter out = response.getWriter();
		String msg = "下架成功";
		Map hashMap = new HashMap();
		hashMap.put("msg",msg);
		// json by 黑馬
		ObjectMapper mapper = new ObjectMapper();
//		mapper.writeValue(out, msg); 我是縮減的
		String str = mapper.writeValueAsString(hashMap);
		out.print(str);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}
}

