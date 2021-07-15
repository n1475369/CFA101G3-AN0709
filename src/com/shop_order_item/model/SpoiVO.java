package com.shop_order_item.model;

import java.io.Serializable;

import com.product.model.ProVO;

public class SpoiVO implements Serializable{
	private Integer spo_id;
	private ProVO provo;//商品VO(含pro_id方便購物車展示)
	private Integer spoi_quantity;//數量
	private Integer spoi_totalprice;//小計金額(自動計算)
	public Integer getSpo_id() {
		return spo_id;
	}
	public void setSpo_id(Integer spo_id) {
		this.spo_id = spo_id;
	}
	public ProVO getProvo() {
		return provo;
	}
	public void setProvo(ProVO provo) {
		this.provo = provo;
	}
	public Integer getSpoi_quantity() {
		return spoi_quantity;
	}
	public void setSpoi_quantity(Integer spoi_quantity) {
		this.spoi_quantity = spoi_quantity;
	}
	public Integer getSpoi_totalprice() {
		spoi_totalprice = provo.getPro_price() * spoi_quantity;
		return spoi_totalprice;
	}
	public void setSpoi_totalprice(Integer spoi_totalprice) {
		this.spoi_totalprice = spoi_totalprice;
	}
	@Override
	public String toString() {
		return "SpoiVO [spo_id=" + spo_id + ", provo=" + provo + ", spoi_quantity=" + spoi_quantity
				+ ", spoi_totalprice=" + spoi_totalprice + "]";
	}
	}
