package com.shop_order.model;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.shop_order_item.model.SpoiVO;

public interface SpoDAO {
	//新增商品訂單(需藉由別人的連線)(單筆)
	public void insert(SpoVO spoVO,List<SpoiVO> spoiVOList,Connection con);
	//新增多筆商品訂單
	public void insertMultiple(HashMap<Integer, List<SpoiVO>> smemMap,String name,String phone,Integer paytype,HashMap<Integer,Integer> postageMap,String address,Integer bmem_id);
}
