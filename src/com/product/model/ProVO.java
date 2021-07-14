package com.product.model;

import java.io.Serializable;

public class ProVO implements Serializable{

//週邊商品JavaBean
	private Integer pro_id;
	private String pro_name;
	private Integer pro_price;
	private String pro_content;
	private Integer pro_smem_id;
	private Integer pro_proc_id;
	private Integer pro_status;
	
	public ProVO(){ };
	
	public ProVO( Integer pro_id, String pro_name, Integer pro_price, String pro_content, Integer pro_smem_id,Integer pro_proc_id, Integer pro_status) {
		this.pro_id=pro_id;
		this.pro_name=pro_name;
		this.pro_price=pro_price;
		this.pro_content=pro_content;
		this.pro_smem_id=pro_smem_id;
		this.pro_proc_id=pro_proc_id;
		this.pro_status=pro_status;
	}
	
	public Integer getPro_id() {
		return pro_id;
	}
	public String getPro_name() {
		return pro_name;
	}
	public Integer getPro_price() {
		return pro_price;
	}
	public String getPro_content() {
		return pro_content;
	}
	public Integer getPro_smem_id() {
		return pro_smem_id;
	}
	public Integer getPro_proc_id() {
		return pro_proc_id;
	}
	public Integer getPro_status() {
		return pro_status;
	}
	public void setPro_id(Integer pro_id) {
		this.pro_id = pro_id;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public void setPro_price(Integer pro_price) {
		this.pro_price = pro_price;
	}
	public void setPro_content(String pro_content) {
		this.pro_content = pro_content;
	}
	public void setPro_smem_id(Integer pro_smem_id) {
		this.pro_smem_id = pro_smem_id;
	}
	public void setPro_proc_id(Integer pro_proc_id) {
		this.pro_proc_id = pro_proc_id;
	}
	public void setPro_status(Integer pro_status) {
		this.pro_status = pro_status;
	}

	@Override
	public String toString() {
		return "ProVO [pro_id=" + pro_id + ", pro_name=" + pro_name + ", pro_price=" + pro_price + ", pro_content="
				+ pro_content + ", pro_smem_id=" + pro_smem_id + ", pro_proc_id=" + pro_proc_id + ", pro_status="
				+ pro_status + "]";
	}
	
}
