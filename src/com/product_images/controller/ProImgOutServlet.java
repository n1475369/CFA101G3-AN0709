package com.product_images.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProService;
import com.product_images.model.ProImgService;
import com.product_images.model.ProImgVO;


@WebServlet("/product/ProImgOutServlet")
public class ProImgOutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Integer proi_id = null;
		
		try {
			proi_id = Integer.parseInt(request.getParameter("proi_id"));
			ProImgService service = new ProImgService();
			ProImgVO imgVO = service.findByPrimaryKey(proi_id);
			if(imgVO != null) {
				byte[] proi_images = imgVO.getProi_images();
				response.getOutputStream().write(proi_images);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
