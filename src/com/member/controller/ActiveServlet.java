package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemService;

import redis.clients.jedis.Jedis;

@WebServlet("/member/activeServlet")
public class ActiveServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String code = request.getParameter("code");
		String username = request.getParameter("username");
		Jedis jedis = new Jedis("34.81.27.105",6379);
		String checkCode = jedis.hget(username, "email");
		
		if(code.equals(checkCode)) {
			MemService service = new MemService();
			try {
				service.active(username);
				request.setAttribute("msg", "帳號啟用成功，請重新登入");
				request.setAttribute("status", true);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "帳號啟用失敗請重新申請驗證");
				request.setAttribute("status", false);
			}
			
		}else {
			request.setAttribute("msg", "帳號啟用失敗請重新申請驗證");
			request.setAttribute("status", false);
		}
		request.getRequestDispatcher("/front_end/member/active.jsp").forward(request, response);
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
