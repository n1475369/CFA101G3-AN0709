package com.member.model;

import java.util.List;

public class MemService {
	private MemDAO dao = new MemDAOImpl();
	//登入驗證方法
	public MemVO login(String username,String password) {
		return dao.findByUsernameAndPassword(username, password);
	}
	
	//驗證帳號是否存在
	public MemVO findByUsername(String username) {
		return dao.findByUsername(username);
	}
	
	//註冊會員帳號
	public int register(MemVO member) {
		return dao.insert(member);
	}
	
	//啟用信箱驗證
	public void active(String username) {
		dao.updateEmailStatus(username);
	}
	
	//更新買家會員個人資料
	public boolean updateBuyProfile(MemVO member) {
		if(member != null) {
			dao.updateBuyProfile(member);
			return true;
		}else {
			return false;
		}
	}
	
	//更新買家會員個人頭像
	public boolean updateBuyHeadshot(MemVO member) {
		if(member != null) {
			dao.updateBuyHeadshot(member);
			return true;
		}else {
			return false;
		}
	}
	
	//更新會員密碼
	public void updatePassword(String username,String passowrd) {
		MemVO memVO = new MemVO();
		memVO.setMem_username(username);
		memVO.setMem_password(passowrd);
		dao.updatePassword(memVO);
	}
	
	//獲取所有買家會員
	public List<MemVO> getAllByBuyMember(){
		return dao.getAllByBuyMember();
	}
	
	//分頁顯示返回買家會員list集合 start:從哪頁開始查詢 rowsPerPage:每次查幾筆 whichPage:當前頁面
	public List<MemVO> findBuyMemberByPagination(int whichPage, int rowsPerPage,String find_username,String find_name,String find_status) {
		int start = (whichPage - 1) * rowsPerPage;//起始頁面=(當前頁面-1)*每次查幾筆
		List<MemVO> list = dao.findBuyMemberByPagination(start, rowsPerPage,find_username,find_name,find_status);
		return list;
	}
	
	//分頁顯示返回多重查詢總筆數
	public int getBuyMemberRowNumber(String find_username,String find_name,String find_status) {
		return dao.getBuyMemberRowNumber(find_username, find_name, find_status);
	}
	
	//獲取個人會員資料
	public MemVO getOne(Integer mem_id) {
		return dao.getOne(mem_id);
	}
	
	//管理員修改買家會員資料
	public void updateBuyMember(Integer mem_id,String mem_username,String mem_name,String mem_phone,Integer mem_status) {
		MemVO memVO = new MemVO();
		memVO.setMem_id(mem_id);
		memVO.setMem_username(mem_username);
		memVO.setMem_name(mem_name);
		memVO.setMem_phone(mem_phone);
		memVO.setMem_status(mem_status);
		dao.updateBuyMember(memVO);
	}
}
