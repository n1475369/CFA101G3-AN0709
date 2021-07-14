package com.product_images.model;

import java.io.Serializable;
import java.util.Arrays;

public class ProImgVO  implements Serializable{

	private Integer proi_id;
	private Integer proi_pro_id;
	private byte[] proi_images;
	
	
	public ProImgVO(Integer proi_id, Integer proi_pro_id, byte[] proi_images) {
		this.proi_id = proi_id;
		this.proi_pro_id = proi_pro_id;
		this.proi_images = proi_images;
	}
	public ProImgVO() {
		
	}
	public Integer getProi_id() {
		return proi_id;
	}
	public  Integer getProi_pro_id() {
		return proi_pro_id;
	}
	public byte[] getProi_images() {
		return proi_images;
	}
	public void setProi_id(Integer proi_id) {
		this.proi_id = proi_id;
	}
	public void setProi_pro_id(Integer proi_pro_id) {
		this.proi_pro_id = proi_pro_id;
	}
	public void setProi_images(byte[] proi_images) {
		this.proi_images = proi_images;
	}
	@Override
	public String toString() {
		return "ProImg [proi_id=" + proi_id + ", proi_pro_id=" + proi_pro_id + ", proi_images="
				+ Arrays.toString(proi_images) + "]";
	}
	
	
}
