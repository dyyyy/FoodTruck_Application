package com.example.user.vo;

import java.io.Serializable;

public class MemberVO extends LicenseVO implements Serializable{
	
	private String memberId;		// 회원 아이디
	private String memberName;		// 회원 이름
	private String memberPw;		// 회원 비밀번호
	private String memberTel;		// 회원 전화번호
	private String memberAuth;		// 회원 구분 (1 = 윤영자  , 2 = 판매자 , 3 = 일반회원 )
	 
	
	// getter & setter
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}
	public String getMemberAuth() {
		return memberAuth;
	}
	public void setMemberAuth(String memberAuth) {
		this.memberAuth = memberAuth;
	}
	
	

}
