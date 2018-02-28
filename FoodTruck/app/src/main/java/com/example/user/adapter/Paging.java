package com.example.user.adapter;

/**
 * Created by bit-user on 2018-02-28.
 */

public class Paging {
    public int w_size = 10; // 글 개수
    public int p_size = 10;  // 페이지 개수
    public int writing_Count = 0;

    public int cur_Page = 0;


    public Paging(int w_size, int p_size, int writing_Count, int cur_Page) {
        super();
        this.w_size = w_size;
        this.p_size = p_size;
        this.writing_Count = writing_Count;
        this.cur_Page = cur_Page;
    }

    public int getPage_Count() {
        return ((writing_Count - 1) / w_size) + 1;
    }

    public int getPage_Start() {
        return ((cur_Page - 1) / p_size) * p_size + 1;
    }

    public int getPage_End() {
        return Math.min(getPage_Start() + p_size - 1, getPage_Count());
    }

    public boolean isPre() {
        return getPage_Start() != 1;
    }

    public boolean isNext() {
        return getPage_End() < getPage_Count();
    }

    public int getWriting_Start() {
        return getWriting_End() - w_size + 1;
    }

    public int getWriting_End() {
        return cur_Page * w_size;
    }
    /*Paging pg = new Paging(한 화면에 보여질 글 수 , 페이지 분할 수 , 총 글의 갯수  , 현재 보고 있는 페이지 번호  );
      System.out.println("총 페이지 개수 : "+pg.getPage_Count());
  System.out.println("페이지 시작 숫자  : "+pg.getPage_Start());
  System.out.println("페이지 마지막 숫자  : "+pg.getPage_End());
  System.out.println("Pre 표시 여부  : "+pg.isPre());
  System.out.println("Next 표시 여부   : "+pg.isNext());
  System.out.println("글 범위 시작 번호   : "+pg.getWriting_Start());
  System.out.println("글 범위 끝 번호   : "+pg.getWriting_End());
*/
}