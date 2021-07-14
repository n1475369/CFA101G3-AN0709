package com.member.controller;


import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.member.model.MemService;
import com.member.model.MemVO;

//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/member/headshotBuyServlet")
public class HeadshotBuyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8"); 
    	HttpSession session = request.getSession();
		MemVO user = (MemVO)session.getAttribute("user");
		MemService service = new MemService();
		if(user!=null) {
		//判斷是否為請求資料
		String headshot = request.getParameter("headshot");
		if("headshot".equals(headshot)) {
			byte[] mem_headshot = user.getMem_headshot();
			if(mem_headshot!=null) {
				response.getOutputStream().write(mem_headshot);
			}
			return;
		}
		
		//修改個人頭像
		Part part = request.getPart("blob");
		InputStream in = part.getInputStream();
		byte[] buf = new byte[in.available()];
		in.read(buf);
		in.close();
		user.setMem_headshot(buf);
		service.updateBuyHeadshot(user);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
