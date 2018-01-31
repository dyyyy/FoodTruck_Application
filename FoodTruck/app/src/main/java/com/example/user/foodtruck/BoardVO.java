package com.example.user.foodtruck;

/**
 * Created by bit-user on 2018-01-24.
 */

public class BoardVO {
    private int boardNo;
    private String memId;
    private String boardTitle;
    private String boardContent;
    private String boardReg;
    private int boardCnt;


    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public String getBoardReg() {
        return boardReg;
    }

    public void setBoardReg(String boardReg) {
        this.boardReg = boardReg;
    }

    public int getBoardCnt() {
        return boardCnt;
    }

    public void setBoardCnt(int boardCnt) {
        this.boardCnt = boardCnt;
    }
}
