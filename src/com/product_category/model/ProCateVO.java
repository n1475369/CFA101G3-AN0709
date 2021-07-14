package com.product_category.model;

import java.io.Serializable;

public class ProCateVO implements Serializable{

	//週邊分類JavaBean
	private Integer proc_id;
	private String proc_name;
	
	
	
	public ProCateVO(Integer proc_id, String proc_name) {
		this.proc_id = proc_id;
		this.proc_name = proc_name;
	}
	
	public Integer getProc_id() {
		return proc_id;
	}
	public String getProc_name() {
		return proc_name;
	}
	public void setProc_id(Integer proc_id) {
		this.proc_id = proc_id;
	}
	public void setProc_name(String proc_name) {
		this.proc_name = proc_name;
	}

	@Override
	public String toString() {
		return "ProCateVO [proc_id=" + proc_id + ", proc_name=" + proc_name + "]";
	}
	
}
