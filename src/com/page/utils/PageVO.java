package com.page.utils;

import java.util.ArrayList;
import java.util.List;

public class PageVO {
	private int rowsPerPage;  	//每次顯示幾筆
	private int rowNumber;      //總筆數
	private int pageNumber;     //總頁數
	private int whichPage;      //當前頁數
	private List pageDatas = new ArrayList(); //分頁查詢出來的結果list
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getWhichPage() {
		return whichPage;
	}
	public void setWhichPage(int whichPage) {
		this.whichPage = whichPage;
	}
	public List getPageDatas() {
		return pageDatas;
	}
	public void setPageDatas(List pageDatas) {
		this.pageDatas = pageDatas;
	}
	@Override
	public String toString() {
		return "pageVO [rowsPerPage=" + rowsPerPage + ", rowNumber=" + rowNumber + ", pageNumber=" + pageNumber
				+ ", whichPage=" + whichPage + ", getRowsPerPage()=" + getRowsPerPage() + ", getRowNumber()="
				+ getRowNumber() + ", getPageNumber()=" + getPageNumber() + ", getWhichPage()=" + getWhichPage()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
