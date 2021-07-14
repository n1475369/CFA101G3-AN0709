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
import com.product.model.ProService;
import com.product_images.model.ProImgDAO;
import com.product_images.model.ProImgDAOimpl;
import com.product_images.model.ProImgService;
import com.product_images.model.ProImgVO;


@WebServlet("/product/ProImgOutServlet2")
public class ProImgOutServlet2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
			Integer proi_pro_id = Integer.parseInt(request.getParameter("proi_pro_id"));
			ProImgDAO dao = new ProImgDAOimpl();
			List<ProImgVO> list = dao.findByFKlist(proi_pro_id);
			PrintWriter out = response.getWriter();
			ObjectMapper Mapper = new ObjectMapper();
			String data = Mapper.writeValueAsString(list);
			out.println(data);
			System.out.println(data);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
