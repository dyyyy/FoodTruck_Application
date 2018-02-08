package com.example.user.vo;

import java.io.Serializable;

public class MInquiryVO implements Serializable {

    private int qaScNo;                //qna 번호
    private String memId;            //이메일 아이디
    private String qaScTitle;        //제목
    private String qaScContent;        //내용
    private String qaScReg;            //등록일자
    private String qaScCategory1;    //카테고리1
    private String qaScCategory2;    //카테고리2
    private String qaScTel;            //전화번호
    private String qaScStat;        //y&n flag
    private String licenseNo;        //사업자 번호

    //getter,setter

    public int getQaScNo() {
        return qaScNo;
    }

    public void setQaScNo(int qaScNo) {
        this.qaScNo = qaScNo;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
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


}
