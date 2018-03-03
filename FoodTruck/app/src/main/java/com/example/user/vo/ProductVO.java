
package com.example.user.vo;

import java.io.Serializable;

public class ProductVO implements Serializable{
	
	private String prodNo;			// 상품 번호
	private String prodName;		// 상품 이름
	private String prodContent;		// 상품 내용
	private String prodImg;			// 상품 이미지
	private int prodPrice;			// 상품 가격 
	private String ftruckNo;		// 푸드트럭 번호
	private String licenseNo;		// 사업자 번호
	private String prodOrgin; 		// 원산지
	
	// getter & setter
	
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdContent() {
		return prodContent;
	}
	public void setProdContent(String prodContent) {
		this.prodContent = prodContent;
	}
	public String getProdImg() {
		return prodImg;
	}
	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getProdOrgin() {
		return prodOrgin;
	}
	public void setProdOrgin(String prodOrgin) {
		this.prodOrgin = prodOrgin;
	}
	public String getFtruckNo() {
		return ftruckNo;
	}
	public void setFtruckNo(String ftruckNo) {
		this.ftruckNo = ftruckNo;
	}
	
}

