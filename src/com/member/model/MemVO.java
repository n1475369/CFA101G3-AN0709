package com.member.model;

import java.io.Serializable;

//會員JavaBean
public class MemVO implements Serializable{
	private Integer mem_id;
	private String mem_username;
	private String mem_password;
	private String mem_name;
	private Integer mem_role;
	private String mem_phone;
	private String mem_city;
	private String mem_cityarea;
	private String mem_street;
	private Integer mem_status;
	private String mem_shop_name;
	private String mem_shop_content;
	private byte[] mem_shop_logo;
	private byte[] mem_shop_banner;
	private Integer mem_shop_status;
	private byte[] mem_headshot;
	private Integer mem_review_count;
	private Integer mem_review_score;
	
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_username() {
		return mem_username;
	}
	public void setMem_username(String mem_username) {
		this.mem_username = mem_username;
	}
	public String getMem_password() {
		return mem_password;
	}
	public void setMem_password(String mem_password) {
		this.mem_password = mem_password;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public Integer getMem_role() {
		return mem_role;
	}
	public void setMem_role(Integer mem_role) {
		this.mem_role = mem_role;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public String getMem_city() {
		return mem_city;
	}
	public void setMem_city(String mem_city) {
		this.mem_city = mem_city;
	}
	public String getMem_cityarea() {
		return mem_cityarea;
	}
	public void setMem_cityarea(String mem_cityarea) {
		this.mem_cityarea = mem_cityarea;
	}
	public String getMem_street() {
		return mem_street;
	}
	public void setMem_street(String mem_street) {
		this.mem_street = mem_street;
	}
	public Integer getMem_status() {
		return mem_status;
	}
	public void setMem_status(Integer mem_status) {
		this.mem_status = mem_status;
	}
	public String getMem_shop_name() {
		return mem_shop_name;
	}
	public void setMem_shop_name(String mem_shop_name) {
		this.mem_shop_name = mem_shop_name;
	}
	public String getMem_shop_content() {
		return mem_shop_content;
	}
	public void setMem_shop_content(String mem_shop_content) {
		this.mem_shop_content = mem_shop_content;
	}
	public byte[] getMem_shop_logo() {
		return mem_shop_logo;
	}
	public void setMem_shop_logo(byte[] mem_shop_logo) {
		this.mem_shop_logo = mem_shop_logo;
	}
	public byte[] getMem_shop_banner() {
		return mem_shop_banner;
	}
	public void setMem_shop_banner(byte[] mem_shop_banner) {
		this.mem_shop_banner = mem_shop_banner;
	}
	public Integer getMem_shop_status() {
		return mem_shop_status;
	}
	public void setMem_shop_status(Integer mem_shop_status) {
		this.mem_shop_status = mem_shop_status;
	}

	public byte[] getMem_headshot() {
		return mem_headshot;
	}
	public void setMem_headshot(byte[] mem_headshot) {
		this.mem_headshot = mem_headshot;
	}
	public Integer getMem_review_count() {
		return mem_review_count;
	}
	public void setMem_review_count(Integer mem_review_count) {
		this.mem_review_count = mem_review_count;
	}
	public Integer getMem_review_score() {
		return mem_review_score;
	}
	public void setMem_review_score(Integer mem_review_score) {
		this.mem_review_score = mem_review_score;
	}
	
}
