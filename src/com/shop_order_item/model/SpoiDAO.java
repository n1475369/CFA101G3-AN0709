package com.shop_order_item.model;

import java.sql.Connection;

public interface SpoiDAO {
	//新增商品訂單明細(產生商品訂單時用)
	public void insertByShopOrder(SpoiVO spoiVO,Connection con);
}
