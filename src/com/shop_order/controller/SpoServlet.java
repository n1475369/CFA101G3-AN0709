package com.shop_order.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemVO;
import com.product.model.ProVO;
import com.shop_order.model.SpoDAOImpl;
import com.shop_order.model.SpoService;
import com.shop_order.model.SpoVO;
import com.shop_order_item.model.SpoiVO;


@WebServlet("/shop_order/spoServlet")
public class SpoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String action = request.getParameter("action");
		
		//將購物車map轉換成賣家對應商品明細map
		if("getCart".equals(action)) {
			HttpSession session = request.getSession();
			HashMap<Integer,SpoiVO> map = (HashMap)session.getAttribute("cart");
			if (map != null) {
				HashSet<Integer> set = new HashSet<Integer>();//儲存賣家ID的set集合
				HashMap<Integer, List<SpoiVO>> smemMap = new HashMap<Integer,List<SpoiVO>>();//賣家的map(內存商品明細)
				Collection<SpoiVO> values = map.values();
				for (SpoiVO spoiVO : values) {
					Integer pro_smem_id = spoiVO.getProvo().getPro_smem_id();//取得賣家ID
					set.add(pro_smem_id);
				}
				for(Integer pro_smem_id : set ) {
					smemMap.put(pro_smem_id, new ArrayList<SpoiVO>());//賣家的map(內存商品明細)
				}
				for (SpoiVO spoiVO : values) {
					Integer pro_smem_id = spoiVO.getProvo().getPro_smem_id();//取得賣家ID
					smemMap.get(pro_smem_id).add(spoiVO);
				}
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getWriter(), smemMap);
			}
		}
		
		//送出結帳訂單
		if("insert".equals(action)) {
			HttpSession session = request.getSession();
			MemVO memVO = (MemVO)session.getAttribute("user");
			HashMap<Integer,SpoiVO> map = (HashMap)session.getAttribute("cart");
			if(memVO == null) {
				return;//驗證是否為登入狀態
			}
			if(map == null) {
				return;//驗證購物車是否有商品
			}
			Map<String, String[]> parameterMap = request.getParameterMap();
			Set<String> keySet = parameterMap.keySet();
			for (String key : keySet) {
				String parameter = request.getParameter(key);
				if(parameter == null ||parameter.trim().isEmpty()) {
					return;//驗證訂單資訊是否有空值或空字串
				}
			}
			
			//將購物車map轉換成賣家對應商品明細map
			HashSet<Integer> set = new HashSet<Integer>();//儲存賣家ID的set集合
			HashMap<Integer, List<SpoiVO>> smemMap = new HashMap<Integer,List<SpoiVO>>();//賣家的map(內存商品明細)
			Collection<SpoiVO> values = map.values();
			for (SpoiVO spoiVO : values) {
				Integer pro_smem_id = spoiVO.getProvo().getPro_smem_id();//取得賣家ID
				set.add(pro_smem_id);
			}
			for(Integer pro_smem_id : set ) {
				smemMap.put(pro_smem_id, new ArrayList<SpoiVO>());//賣家的map(內存商品明細)
			}
			for (SpoiVO spoiVO : values) {
				Integer pro_smem_id = spoiVO.getProvo().getPro_smem_id();//取得賣家ID
				smemMap.get(pro_smem_id).add(spoiVO);
			}
			
			try {
			String name = request.getParameter("name").trim();
			String phone = request.getParameter("phone").trim();
			Integer paytype = Integer.parseInt(request.getParameter("paytype").trim());
			String postage = request.getParameter("postage").trim();
			String address = request.getParameter("address").trim();
			Integer bmem_id = memVO.getMem_id();//買家會員ID
			
			//將運費JSON轉換成map
			ObjectMapper mapper = new ObjectMapper();
			HashMap<Integer,Integer> postageMap = mapper.readValue(postage, new TypeReference<HashMap<Integer,Integer>>(){});
			
			//新增多筆訂單
			SpoService spoService = new SpoService();
			spoService.insertMultiple(smemMap, name, phone, paytype, postageMap, address, bmem_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
