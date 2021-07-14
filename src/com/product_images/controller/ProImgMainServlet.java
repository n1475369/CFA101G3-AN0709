package com.product_images.controller;

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
import com.product_images.model.ProImgDAO;
import com.product_images.model.ProImgDAOimpl;
import com.product_images.model.ProImgVO;


@WebServlet("/product/ProImgServlet")
public class ProImgMainServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json; charset=utf-8"); //回應為JSON
		ProImgDAO dao = new ProImgDAOimpl(); //new一個實作介面
		List<ProImgVO> list = dao.getall();
//		out.println(list);
		PrintWriter out = response.getWriter(); //servlet輸出
		ObjectMapper mapper = new ObjectMapper(); //JSON
		String data = mapper.writeValueAsString(list); //JSON
		out.println(data);//讓我看看!!
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
