package com.member.model;

public interface MemDAO {
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
	
}
