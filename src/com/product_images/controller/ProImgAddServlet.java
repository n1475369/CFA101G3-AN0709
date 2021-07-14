package com.product_images.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.member.model.MemVO;
import com.product_images.model.ProImgDAO;
import com.product_images.model.ProImgDAOimpl;
import com.product_images.model.ProImgVO;
import com.sun.mail.handlers.text_html;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/product/ProImgAddServlet")
public class ProImgAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//設置頭
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//取值
		Integer proi_pro_id = Integer.parseInt(request.getParameter("selectID"));
		System.out.println(proi_pro_id);
		
		byte[] buf = null;
		Part part = request.getPart("file1");
		InputStream in = part.getInputStream();
		//調用dao
		ProImgVO proImgVO = new ProImgVO();
		ProImgDAO dao = new ProImgDAOimpl();
		
		//匯入圖片
		if(in.available() !=0) {
			buf =new byte[in.available()];
			in.read(buf);
			in.close();
			
			proImgVO.setProi_pro_id(proi_pro_id);
			proImgVO.setProi_images(buf);
			dao.insertProImg(proImgVO);
		}
		
		try {
			response.getWriter().print("1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().print("0");
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
