package com.example.user.vo;

import java.io.Serializable;

public class EventVO implements Serializable{
	
	private int eventNo;			// 글번호
	private String memId;			// 회원 아이디
	private String eventTitle;		// 제목
	private String eventContent;	// 내용
	private String eventReg;		// 등록일
	private int eventCnt;			// 조회수
	
	// getter & setter
	
	public int getEventNo() {
		return eventNo;
	}
	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventContent() {
		return eventContent;
	}
	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}
	public String getEventReg() {
		return eventReg;
	}
	public void setEventReg(String eventReg) {
		this.eventReg = eventReg;
	}
	public int getEventCnt() {
		return eventCnt;
	}
	public void setEventCnt(int eventCnt) {
		this.eventCnt = eventCnt;
	}
	
}
