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
		response.setHeader("Cache-Control","no-store");//清快取
		response.setHeader("Pragma","no-cache");//清快取
		response.setDateHeader("Expires",0);//清快取
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
			List<Integer> spo_idList = spoService.insertMultiple(smemMap, name, phone, paytype, postageMap, address, bmem_id);
			session.setAttribute("spo_idList", spo_idList);//將訂單ID List存到session
			
			//計算付款金額後轉交jsp
			Integer payment = 0;//給定付款金額初值
			Set<Integer> keySet2 = smemMap.keySet();
			 for (Integer smem_id : keySet2) {
				 	List<SpoiVO> list = smemMap.get(smem_id);//獲取訂單明細VO
				 	for(SpoiVO spoiVO : list) {
				 		payment += spoiVO.getSpoi_totalprice();//訂單明細金額加總
				 	}
				 	payment += postageMap.get(smem_id);//實付金額加上運費
			 }
			session.removeAttribute("cart");//清空購物車商品
			request.setAttribute("payment", payment);
			
			//付款方式為信用卡forword至對應的jsp
			if(paytype==0) {
				request.getRequestDispatcher("/front_end/product/CreditCard.jsp").forward(request, response);
			}else if(paytype==1) {
				request.getRequestDispatcher("/front_end/product/WebATM.jsp").forward(request, response);
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//信用卡付款確認
		if("creditcard".equals(action)) {
			request.removeAttribute("payment");
			String cardnumber = request.getParameter("cardnumber");
			String expirationdate = request.getParameter("expirationdate");
			String securitycode = request.getParameter("securitycode");
			HttpSession session = request.getSession();
			List<Integer> spo_idList = (List<Integer>)session.getAttribute("spo_idList");//取得訂單ID List
			session.removeAttribute("spo_idList");
			SpoService spoService = new SpoService();
			if("4321 4321 4321 4321".equals(cardnumber) && "10/25".equals(expirationdate) && "333".equals(securitycode)) {
				spoService.updateAllSpo_pay_status(spo_idList, 3);//更新為付款成功
				request.getRequestDispatcher("/front_end/product/Success.jsp").forward(request, response);
			}else {
				spoService.updateAllSpo_pay_status(spo_idList, 1);//更新為付款失敗
				request.getRequestDispatcher("/front_end/product/Error.jsp").forward(request, response);
			}
		}
		
		//WebATM付款確認
		if("webATM".equals(action)) {
			Integer payment = Integer.parseInt(request.getParameter("payment").trim());
			Integer pay = Integer.parseInt(request.getParameter("pay").trim());
			request.removeAttribute("payment");
			HttpSession session = request.getSession();
			List<Integer> spo_idList = (List<Integer>)session.getAttribute("spo_idList");//取得訂單ID List
			session.removeAttribute("spo_idList");
			SpoService spoService = new SpoService();
			if(pay.equals(payment)) {
				spoService.updateAllSpo_pay_status(spo_idList, 3);//更新為付款成功
				request.getRequestDispatcher("/front_end/product/Success.jsp").forward(request, response);
			}else {
				spoService.updateAllSpo_pay_status(spo_idList, 1);//更新為付款失敗
				request.getRequestDispatcher("/front_end/product/Error.jsp").forward(request, response);
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
