package com.weddingPhoto.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weddingPhoto.model.WedService;
import com.weddingPhoto.model.WedVO;

/**
 * Servlet implementation class WorkPhotoServlet
 */
@WebServlet("/weddingPhoto/workPhotoServlet")
public class WorkPhotoServlet extends HttpServlet {

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getParameter("action");

		//以下為jsp用ajax可跳過
		if("jsp".equals(action)) {
			try {
				Integer wed_wor_id = Integer.parseInt(request.getParameter("wed_wor_id"));
				WedService wedService = new WedService();
				List<WedVO> list = wedService.findByForeignKey(wed_wor_id);
				request.setAttribute("list", list);
				RequestDispatcher rd = request.getRequestDispatcher("/Test.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		//以下為ajax用JSP可跳過
		if("ajax".equals(action)) {
			try {
				Integer wed_wor_id = Integer.parseInt(request.getParameter("wed_wor_id"));
				WedService wedService = new WedService();
				List<WedVO> list = wedService.findByForeignKey(wed_wor_id);
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(list);
				response.getWriter().print(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
