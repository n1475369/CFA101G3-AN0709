package com.shop_order.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop_order_item.model.SpoiVO;

public class SpoService {
	
	SpoDAOImpl dao = new SpoDAOImpl();
	//新增多筆商品訂單
	public void insertMultiple(HashMap<Integer, List<SpoiVO>> smemMap,String name,String phone,Integer paytype,HashMap<Integer,Integer> postageMap ,String address,Integer bmem_id) {
		
		dao.insertMultiple(smemMap, name, phone, paytype, postageMap, address, bmem_id);
	}
}
