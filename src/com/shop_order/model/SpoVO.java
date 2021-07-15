package com.shop_order.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SpoVO implements Serializable{
	private Integer spo_id;
	private Timestamp spo_time;
	private Integer spo_payment;
	private Integer spo_postage;
	private Integer spo_bmem_id;
	private Integer spo_smem_id;
	private String spo_receiver_name;
	private String spo_receiver_phone;
	private String spo_receiver_address;
	private Integer spo_paytype;
	private Integer spo_status;
	private Integer spo_pay_status;
	private Integer spo_cargo_status;
	public Integer getSpo_id() {
		return spo_id;
	}
	public void setSpo_id(Integer spo_id) {
		this.spo_id = spo_id;
	}
	public Timestamp getSpo_time() {
		return spo_time;
	}
	public void setSpo_time(Timestamp spo_time) {
		this.spo_time = spo_time;
	}
	public Integer getSpo_payment() {
		return spo_payment;
	}
	public void setSpo_payment(Integer spo_payment) {
		this.spo_payment = spo_payment;
	}
	public Integer getSpo_postage() {
		return spo_postage;
	}
	public void setSpo_postage(Integer spo_postage) {
		this.spo_postage = spo_postage;
	}
	public Integer getSpo_bmem_id() {
		return spo_bmem_id;
	}
	public void setSpo_bmem_id(Integer spo_bmem_id) {
		this.spo_bmem_id = spo_bmem_id;
	}
	public Integer getSpo_smem_id() {
		return spo_smem_id;
	}
	public void setSpo_smem_id(Integer spo_smem_id) {
		this.spo_smem_id = spo_smem_id;
	}
	public String getSpo_receiver_name() {
		return spo_receiver_name;
	}
	public void setSpo_receiver_name(String spo_receiver_name) {
		this.spo_receiver_name = spo_receiver_name;
	}
	public String getSpo_receiver_phone() {
		return spo_receiver_phone;
	}
	public void setSpo_receiver_phone(String spo_receiver_phone) {
		this.spo_receiver_phone = spo_receiver_phone;
	}
	public String getSpo_receiver_address() {
		return spo_receiver_address;
	}
	public void setSpo_receiver_address(String spo_receiver_address) {
		this.spo_receiver_address = spo_receiver_address;
	}
	public Integer getSpo_paytype() {
		return spo_paytype;
	}
	public void setSpo_paytype(Integer spo_paytype) {
		this.spo_paytype = spo_paytype;
	}
	public Integer getSpo_status() {
		return spo_status;
	}
	public void setSpo_status(Integer spo_status) {
		this.spo_status = spo_status;
	}
	public Integer getSpo_pay_status() {
		return spo_pay_status;
	}
	public void setSpo_pay_status(Integer spo_pay_status) {
		this.spo_pay_status = spo_pay_status;
	}
	public Integer getSpo_cargo_status() {
		return spo_cargo_status;
	}
	public void setSpo_cargo_status(Integer spo_cargo_status) {
		this.spo_cargo_status = spo_cargo_status;
	}
}
