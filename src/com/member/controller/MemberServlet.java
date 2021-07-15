package com.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemService;
import com.member.model.MemVO;
import com.page.utils.PageVO;


@WebServlet("/member/memberServlet")
public class MemberServlet extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
    	String action = request.getParameter("action");
    	//買家會員分頁顯示
    	if("buyMember".equals(action)) {
    		String whichPageStr = request.getParameter("whichPage");
    		String rowsPerPageStr = request.getParameter("rowsPerPage");
    		String find_username = request.getParameter("find_username");
    		String find_name = request.getParameter("find_name");
    		String find_status = request.getParameter("find_status");
    		if(whichPageStr == null || whichPageStr.isEmpty()) {
    			whichPageStr = "1";
    		}
    		if(rowsPerPageStr == null || rowsPerPageStr.isEmpty()) {
    			rowsPerPageStr = "5";
    		}
    		int whichPage = Integer.parseInt(whichPageStr);//當前頁數
    		int rowsPerPage = Integer.parseInt(rowsPerPageStr);//每次顯示幾筆
    	    MemService memService = new MemService();
    	    int rowNumber = memService.getBuyMemberRowNumber(find_username,find_name,find_status);//總筆數
    	    int pageNumber = rowNumber % rowsPerPage != 0 ? rowNumber/rowsPerPage + 1 : rowNumber/rowsPerPage;//總頁數

    	    //判斷當前頁面是否超過邊界
    	    if(whichPage <= 0) {
    	    	whichPage = 1;
    	    }else if(whichPage >= pageNumber) {
    	    	whichPage = pageNumber;
    	    }
    	    
    	    Map<String, String[]> condition = request.getParameterMap();//請求參數的查詢條件
    	    request.setAttribute("condition", condition);//轉發回去給jsp作查詢後資料不清除
    	    
    	    List<MemVO> list = memService.findBuyMemberByPagination(whichPage, rowsPerPage,find_username,find_name,find_status);//分頁查詢顯示結果集list
    	    PageVO pageVO = new PageVO();
    	    pageVO.setWhichPage(whichPage);
    	    pageVO.setRowsPerPage(rowsPerPage);
    	    pageVO.setRowNumber(rowNumber);
    	    pageVO.setPageNumber(pageNumber);
    	    pageVO.setPageDatas(list);
    	    request.setAttribute("pageVO", pageVO);
    	    request.getRequestDispatcher("/back_end/member/buyMember.jsp").forward(request, response);
    	}
    	//取得買家會員個人資料(分頁顯示)
    	if("buyMemberGetOne".equals(action)) {
    		String mem_idStr = request.getParameter("mem_id");
    		if(mem_idStr != null) {
    			int mem_id = Integer.parseInt(mem_idStr);
    			MemService memService = new MemService();
    			MemVO memVO = memService.getOne(mem_id);
    			ObjectMapper mapper = new ObjectMapper();
    			String json = mapper.writeValueAsString(memVO);
    			response.getWriter().print(json);
    		}
    	}
    	//更新買家會員個人資料
    	if("buyMemberUpdate".equals(action)) {
    		String mem_idStr = request.getParameter("mem_id");
    		String mem_usernameStr = request.getParameter("mem_username");
    		String mem_nameStr = request.getParameter("mem_name");
    		String mem_phoneStr = request.getParameter("mem_phone");
    		String mem_statusStr = request.getParameter("mem_status");
    		if(mem_idStr==null||mem_usernameStr==null||mem_nameStr==null||mem_phoneStr==null||mem_statusStr==null||mem_nameStr.isEmpty()||mem_phoneStr.isEmpty()) {
    			response.setStatus(401);
    			response.getWriter().print("內容不得為空");
    			return;
    		}
    		Integer mem_id = Integer.parseInt(mem_idStr);
    		String mem_username = mem_usernameStr.trim();
    		String mem_name = mem_nameStr.trim();
    		String mem_phone = mem_phoneStr.trim();
    		Integer mem_status = Integer.parseInt(mem_statusStr);
    		MemService memService = new MemService();
    		try {
				memService.updateBuyMember(mem_id, mem_username, mem_name, mem_phone, mem_status);
			} catch (Exception e) {
				response.setStatus(401);
    			response.getWriter().print(e.getMessage());
			}
    	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
