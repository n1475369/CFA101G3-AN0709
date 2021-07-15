package com.member.model;

import java.util.List;

public interface MemDAO {
	//獲取所有買家會員
	public List<MemVO> getAllByBuyMember();
	//驗證帳密是否存在
	public MemVO findByUsernameAndPassword(String username,String password);
	//驗證帳號是否存在
	public MemVO findByUsername(String username);
	//註冊會員
	public int insert(MemVO member);
	//更新會員信箱驗證狀態
	public void updateEmailStatus(String username);
	//更新買家會員個人資料
	public void updateBuyProfile(MemVO member);
	//更新買家會員個人頭像
	public void updateBuyHeadshot(MemVO member);
	//更新會員密碼
	public void updatePassword(MemVO member);
	//分頁顯示返回多重查詢總筆數
	public int getBuyMemberRowNumber(String find_username,String find_name,String find_status);
	//分頁顯示返回買家會員list集合 start:從哪頁開始查詢 rowsPerPage:每次查幾筆
	public List<MemVO> findBuyMemberByPagination(int start,int rowsPerPage,String find_username,String find_name,String find_status);
	//獲取個人會員資料
	public MemVO getOne(Integer mem_id);
	//管理員修改買家會員資料
	public void updateBuyMember(MemVO memVO);
}
