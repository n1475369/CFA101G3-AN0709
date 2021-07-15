package com.shop_order_item.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.model.ProService;
import com.product.model.ProVO;
import com.product_images.model.ProImgService;
import com.product_images.model.ProImgVO;
import com.shop_order_item.model.SpoiVO;


@WebServlet("/shop_order_item/spoiServlet")
public class SpoiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getParameter("action");
		//加入購物車
		if("addCart".equals(action)) {
			HttpSession session = request.getSession();
			HashMap<Integer,SpoiVO> map = (HashMap)session.getAttribute("cart");
			if(map == null) {
				map = new HashMap<Integer,SpoiVO>();
			}
			Integer pro_id = Integer.parseInt(request.getParameter("pro_id"));
			ProService proService = new ProService();
			ProVO proVO = proService.getOnePro(pro_id);
			SpoiVO spoiVO = map.get(pro_id);
			if(spoiVO != null) {
				if(spoiVO.getSpoi_quantity() <= 20) {
					spoiVO.setSpoi_quantity(spoiVO.getSpoi_quantity()+1);
				}
			}else {
				spoiVO = new SpoiVO();
				spoiVO.setProvo(proVO);
				spoiVO.setSpoi_quantity(1);
			}
			map.put(pro_id, spoiVO);
			session.setAttribute("cart", map);
			ObjectMapper mapper = new ObjectMapper();
			String mapJSON = mapper.writeValueAsString(map);
			response.getWriter().print(mapJSON);
		}
		
		//請求購物車資料
		if("getCart".equals(action)) {
			HttpSession session = request.getSession();
			HashMap<Integer,SpoiVO> map = (HashMap)session.getAttribute("cart");
			if(map != null) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getWriter(), map);
			}
			
		}
		
		//更新購物車資料
		if("updateCart".equals(action)) {
			HttpSession session = request.getSession();
			HashMap<Integer,SpoiVO> map = (HashMap)session.getAttribute("cart");
			if(map != null) {
				Integer pro_id = Integer.parseInt(request.getParameter("pro_id"));
				Integer spoi_quantity = Integer.parseInt(request.getParameter("spoi_quantity"));
				if(spoi_quantity > 20) {
					return;//數量大於20不動作
				}
				SpoiVO spoiVO = map.get(pro_id);
				if(spoiVO != null) {
					spoiVO.setSpoi_quantity(spoi_quantity);
				}
				map.put(pro_id, spoiVO);
				session.setAttribute("cart", map);
				ObjectMapper mapper = new ObjectMapper();
				String mapJSON = mapper.writeValueAsString(map);
				response.getWriter().print(mapJSON);
			}
		}
		
		//刪除購物車內商品
		if("delete".equals(action)) {
			HttpSession session = request.getSession();
			HashMap<Integer,SpoiVO> map = (HashMap)session.getAttribute("cart");
			if(map != null) {
				Integer pro_id = Integer.parseInt(request.getParameter("pro_id"));
				map.remove(pro_id);
				session.setAttribute("cart", map);
				ObjectMapper mapper = new ObjectMapper();
				String mapJSON = mapper.writeValueAsString(map);
				response.getWriter().print(mapJSON);
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
