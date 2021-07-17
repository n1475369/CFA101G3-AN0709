package com.shop_order.model;


import java.util.HashMap;
import java.util.List;
import com.shop_order_item.model.SpoiVO;

public class SpoService {
	
	SpoDAOImpl dao = new SpoDAOImpl();
	//新增多筆商品訂單(返回實付金額)
	public List<Integer> insertMultiple(HashMap<Integer, List<SpoiVO>> smemMap,String name,String phone,Integer paytype,HashMap<Integer,Integer> postageMap ,String address,Integer bmem_id) {
		return dao.insertMultiple(smemMap, name, phone, paytype, postageMap, address, bmem_id);
	}
	
	//更新多筆訂單付款狀態
	public void updateAllSpo_pay_status(List<Integer> spo_idList,Integer spo_pay_status) {
		dao.updateAllSpo_pay_status(spo_idList, spo_pay_status);
	}
}
