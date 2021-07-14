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
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemVO;
import com.product.model.ProDAO;
import com.product.model.ProDAOimpl;
import com.product.model.ProService;
import com.product.model.ProVO;


@WebServlet("/product/ProInsertServlet")
public class ProInsertServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//設置頭
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		//取得參數
		HttpSession session = request.getSession();
//		System.out.println(session);
		MemVO memVo = (MemVO)session.getAttribute("user");
//		System.out.println(memVo);
		Integer pro_smem_id = memVo.getMem_id();
		System.out.println(pro_smem_id);
		String pro_name = request.getParameter("pro_name").trim();
		Integer pro_price = Integer.parseInt(request.getParameter("pro_price").trim());
		String pro_content = request.getParameter("pro_content").trim();
		Integer pro_proc_id = Integer.parseInt(request.getParameter("pro_proc_id"));
		Integer pro_status =  Integer.parseInt(request.getParameter("pro_status"));
		// set它!!
		ProVO proVO = new ProVO();
//		proVO.setPro_id(pro_id);
		proVO.setPro_smem_id(pro_smem_id);
		proVO.setPro_name(pro_name);
		proVO.setPro_price(pro_price);
		proVO.setPro_content(pro_content);
		proVO.setPro_proc_id(pro_proc_id);
		proVO.setPro_status(pro_status);
		// 新增資料
		ProService proSvc = new ProService();
		proVO = proSvc.addPro(pro_smem_id,pro_name, pro_price, pro_content, pro_proc_id, pro_status);
		//輸出
		PrintWriter out = response.getWriter(); //servlet輸出
		ObjectMapper mapper = new ObjectMapper(); //JSON
		String msg = "上傳成功";
		Map hashMap = new HashMap();//把字串放進map
		hashMap.put("msg",msg);
		String data = mapper.writeValueAsString(hashMap); //JSON
		out.println(data);//讓我看看!!

	}
}
