package com.weddingPhoto.model;

import java.io.Serializable;

public class WedVO implements Serializable{
	private Integer wed_id;
	private Integer wed_wor_id;
	private byte[] wed_images;
	public Integer getWed_id() {
		return wed_id;
	}
	public void setWed_id(Integer wed_id) {
		this.wed_id = wed_id;
	}
	public Integer getWed_wor_id() {
		return wed_wor_id;
	}
	public void setWed_wor_id(Integer wed_wor_id) {
		this.wed_wor_id = wed_wor_id;
	}
	public byte[] getWed_images() {
		return wed_images;
	}
	public void setWed_images(byte[] wed_images) {
		this.wed_images = wed_images;
	}
}
