package com.weddingPhoto.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weddingPhoto.model.WedService;
import com.weddingPhoto.model.WedVO;


@WebServlet("/weddingPhoto/wedPhotoServlet")
public class WedPhotoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Integer wed_id = null;
		try {
			wed_id = Integer.parseInt(request.getParameter("wed_id"));
			WedService wedService = new WedService();
			WedVO wedVO = wedService.findByPrimaryKey(wed_id);
			if(wedVO != null) {
				byte[] wed_images = wedVO.getWed_images();
				response.getOutputStream().write(wed_images);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
