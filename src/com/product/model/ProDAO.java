package com.product.model;

import java.util.List;

public interface ProDAO {
	
	//查詢商品
	List<ProVO> getAll();
	//商品上架
	void insert(ProVO proVO);
	//商品下架
	void delete(Integer pro_id);
	//尋找單筆商品PK
	ProVO findByPK(Integer pro_id);
	//修改商品
	void update(ProVO proVO);
	//根據會員店家ID尋找商品
	List findBySeller(Integer pro_smem_id);

	//商品瀏覽頁面
	
	
}
