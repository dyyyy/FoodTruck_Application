package com.example.user.vo;

import java.io.Serializable;

public class FoodTruckVO extends ReviewVO implements Serializable{

	private String ftruckNo; 		// 푸드트럭 번호
	private String ftruckName;	 	// 푸드트럭 이름
	private String ftruckTel; 		// 푸드트럭 전화번호
	private String ftruckAddr; 		// 푸드트럭 주소1
	private String ftruckAddr2; 	// 푸드트럭 주소2
	private String ftruckIntro; 	// 푸드트럭 소개
	private String ftruckReg; 		// 푸드트럭 등록일
	private String ftruckDlvYn; 	// 푸드트럭 배달여부(Y/N)
	private String ftruckRsvYn; 	// 푸드트럭 예약여부(Y/N)
	private String ftruckState; 	// 푸드트럭 운영여부(Y/N)
	private double ftruckGrade; 	// 푸드트럭 평점
	private String ftruckImg; 		// 푸드트럭 이미지
	private String latitude; 		// 위도
	private String longitude; 		// 경도
	private int category; 			// 카테고리 번호
	private String licenseNo; 		// 사업자 번호
	private int count;				// 리뷰 총 갯수
	private int total;				// 리뷰 평점 총 합
	private String ftruckCondtion;	// 푸드트럭상태(여유 / 혼잡)
	private String ftruckEvent;		// 푸드트럭 이벤트
	private int eventNo;			// 이벤트 번호
	private String eventReg2;		// 이벤트 시간

	// getter & setter
	public String getFtruckNo() {
		return ftruckNo;
	}
	public void setFtruckNo(String ftruckNo) {
		this.ftruckNo = ftruckNo;
	}
	public String getFtruckName() {
		return ftruckName;
	}
	public void setFtruckName(String ftruckName) {
		this.ftruckName = ftruckName;
	}
	public String getFtruckTel() {
		return ftruckTel;
	}
	public void setFtruckTel(String ftruckTel) {
		this.ftruckTel = ftruckTel;
	}
	public String getFtruckAddr() {
		return ftruckAddr;
	}
	public void setFtruckAddr(String ftruckAddr) {
		this.ftruckAddr = ftruckAddr;
	}
	public String getFtruckAddr2() {
		return ftruckAddr2;
	}
	public void setFtruckAddr2(String ftruckAddr2) {
		this.ftruckAddr2 = ftruckAddr2;
	}
	public String getFtruckIntro() {
		return ftruckIntro;
	}
	public void setFtruckIntro(String ftruckIntro) {
		this.ftruckIntro = ftruckIntro;
	}
	public String getFtruckReg() {
		return ftruckReg;
	}
	public void setFtruckReg(String ftruckReg) {
		this.ftruckReg = ftruckReg;
	}
	public String getFtruckDlvYn() {
		return ftruckDlvYn;
	}
	public void setFtruckDlvYn(String ftruckDlvYn) {
		this.ftruckDlvYn = ftruckDlvYn;
	}
	public String getFtruckRsvYn() {
		return ftruckRsvYn;
	}
	public void setFtruckRsvYn(String ftruckRsvYn) {
		this.ftruckRsvYn = ftruckRsvYn;
	}
	public String getFtruckState() {
		return ftruckState;
	}
	public void setFtruckState(String ftruckState) {
		this.ftruckState = ftruckState;
	}
	public double getFtruckGrade() {
		return ftruckGrade;
	}
	public void setFtruckGrade(double ftruckGrade) {
		this.ftruckGrade = ftruckGrade;
	}
	public String getFtruckImg() {
		return ftruckImg;
	}
	public void setFtruckImg(String ftruckImg) {
		this.ftruckImg = ftruckImg;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getFtruckCondtion() {
		return ftruckCondtion;
	}
	public void setFtruckCondtion(String ftruckCondtion) {
		this.ftruckCondtion = ftruckCondtion;
	}
	public String getFtruckEvent() {
		return ftruckEvent;
	}
	public void setFtruckEvent(String ftruckEvent) {
		this.ftruckEvent = ftruckEvent;
	}
	public int getEventNo() {
		return eventNo;
	}
	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}
	public String getEventReg2() {
		return eventReg2;
	}
	public void setEventReg2(String eventReg2) {
		this.eventReg2 = eventReg2;
	}

}