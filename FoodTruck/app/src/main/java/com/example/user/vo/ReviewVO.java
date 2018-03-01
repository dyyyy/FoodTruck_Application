package com.example.user.vo;

import java.io.Serializable;

public class ReviewVO implements Serializable{
	
	private int revNo;				// 댓글 번호
	private String ftruckNo;		// 푸드트럭 번호
	private String revContent;		// 댓글 내용
	private String revDtm;			// 댓글 등록일
	private String revDelYn;		// 삭제 여부
	private double grade;			// 평점
	private String memId;			// 회원 아이디
	private String ordNo;			// 주문 번호
	private String ftruckName;		// 푸드트럭 이름
	private String licenseNo;		// 사업자 번호
	
	// getter & setter
	
	public int getRevNo() {
		return revNo;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getFtruckName() {
		return ftruckName;
	}
	public void setFtruckName(String ftruckName) {
		this.ftruckName = ftruckName;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public void setRevNo(int revNo) {
		this.revNo = revNo;
	}
	public String getFtruckNo() {
		return ftruckNo;
	}
	public void setFtruckNo(String ftruckNo) {
		this.ftruckNo = ftruckNo;
	}
	public String getRevContent() {
		return revContent;
	}
	public void setRevContent(String revContent) {
		this.revContent = revContent;
	}
	public String getRevDtm() {
		return revDtm;
	}
	public void setRevDtm(String revDtm) {
		this.revDtm = revDtm;
	}
	public String getRevDelYn() {
		return revDelYn;
	}
	public void setRevDelYn(String revDelYn) {
		this.revDelYn = revDelYn;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	
		
}
