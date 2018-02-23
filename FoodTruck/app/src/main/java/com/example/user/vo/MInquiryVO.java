package com.example.user.vo;

import java.io.Serializable;

public class MInquiryVO implements Serializable{
	
	private String memId;			//이메일 아이디
	// 판매자 
	private int qaScNo;				//qna 번호
	private String qaScTitle;		//제목
	private String qaScContent;		//내용
	private String qaScReg;			//등록일자
	private String qaScCategory1;	//카테고리1
	private String qaScCategory2;	//카테고리2
	private String qaScTel;			//판매자 전화번호
	private String qaScStat;		//y&n flag
	private String licenseNo;		//사업자 번호
	
	// 구매자 
	private int qaSelNo;			//qna 번호
	private String ordNo;			//주문번호
	private String qaSelTel;		//사용자 전화번호
	private String qaSelTitle;		//제목
	private String qaSelContent;	//내용
	private String qaSelReg;		//등록일자
	private String qaSelCategory1;	//카테고리1
	private String qaSelCategory2;	//카테고리2
	private String qaSelStat;		//y&n flag
	
	
	//getter,setter
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getQaScNo() {
		return qaScNo;
	}
	public void setQaScNo(int qaScNo) {
		this.qaScNo = qaScNo;
	}
	public String getQaScTitle() {
		return qaScTitle;
	}
	public void setQaScTitle(String qaScTitle) {
		this.qaScTitle = qaScTitle;
	}
	public String getQaScContent() {
		return qaScContent;
	}
	public void setQaScContent(String qaScContent) {
		this.qaScContent = qaScContent;
	}
	public String getQaScReg() {
		return qaScReg;
	}
	public void setQaScReg(String qaScReg) {
		this.qaScReg = qaScReg;
	}
	public String getQaScCategory1() {
		return qaScCategory1;
	}
	public void setQaScCategory1(String qaScCategory1) {
		this.qaScCategory1 = qaScCategory1;
	}
	public String getQaScCategory2() {
		return qaScCategory2;
	}
	public void setQaScCategory2(String qaScCategory2) {
		this.qaScCategory2 = qaScCategory2;
	}
	public String getQaScTel() {
		return qaScTel;
	}
	public void setQaScTel(String qaScTel) {
		this.qaScTel = qaScTel;
	}
	public String getQaScStat() {
		return qaScStat;
	}
	public void setQaScStat(String qaScStat) {
		this.qaScStat = qaScStat;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public int getQaSelNo() {
		return qaSelNo;
	}
	public void setQaSelNo(int qaSelNo) {
		this.qaSelNo = qaSelNo;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getQaSelTel() {
		return qaSelTel;
	}
	public void setQaSelTel(String qaSelTel) {
		this.qaSelTel = qaSelTel;
	}
	public String getQaSelTitle() {
		return qaSelTitle;
	}
	public void setQaSelTitle(String qaSelTitle) {
		this.qaSelTitle = qaSelTitle;
	}
	public String getQaSelContent() {
		return qaSelContent;
	}
	public void setQaSelContent(String qaSelContent) {
		this.qaSelContent = qaSelContent;
	}
	public String getQaSelReg() {
		return qaSelReg;
	}
	public void setQaSelReg(String qaSelReg) {
		this.qaSelReg = qaSelReg;
	}
	public String getQaSelCategory1() {
		return qaSelCategory1;
	}
	public void setQaSelCategory1(String qaSelCategory1) {
		this.qaSelCategory1 = qaSelCategory1;
	}
	public String getQaSelCategory2() {
		return qaSelCategory2;
	}
	public void setQaSelCategory2(String qaSelCategory2) {
		this.qaSelCategory2 = qaSelCategory2;
	}
	public String getQaSelStat() {
		return qaSelStat;
	}
	public void setQaSelStat(String qaSelStat) {
		this.qaSelStat = qaSelStat;
	}
}
