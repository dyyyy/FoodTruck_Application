package com.example.user.vo;

import java.io.Serializable;

public class NoticeVO implements Serializable{
	
	private int noticeNo;			// 글번호
	private String memId;			// 회원 아이디
	private String noticeTitle;		// 제목
	private String noticeContent;	// 내용
	private String noticeReg;		// 등록일
	private int noticeCnt;			// 조회수
	
	// getter & setter
	
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public int getNoticeCnt() {
		return noticeCnt;
	}
	public void setNoticeCnt(int noticeCnt) {
		this.noticeCnt = noticeCnt;
	}
	public String getNoticeReg() {
		return noticeReg;
	}
	public void setNoticeReg(String noticeReg) {
		this.noticeReg = noticeReg;
	}
	
	
	
	
}
