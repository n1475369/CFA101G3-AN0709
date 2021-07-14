package com.proJOINpc.model;

import java.io.Serializable;

public class ProJoinPcVO implements Serializable{

	private String pro_name;
	private Integer pro_price;
	private String pro_content;
	private String proc_name;
	
	
	
	public ProJoinPcVO(String pro_name, Integer pro_price, String pro_content, String proc_name) {
		this.pro_name = pro_name;
		this.pro_price = pro_price;
		this.pro_content = pro_content;
		this.proc_name = proc_name;
	}
	public ProJoinPcVO() {
		
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
	public String getProc_name() {
		return proc_name;
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
	public void setProc_name(String proc_name) {
		this.proc_name = proc_name;
	}
	@Override
	public String toString() {
		return "ProJoinPcVO [pro_name=" + pro_name + ", pro_price=" + pro_price + ", pro_content=" + pro_content
				+ ", proc_name=" + proc_name + "]";
	}
	
	
	
}