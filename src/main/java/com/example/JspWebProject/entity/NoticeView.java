package com.example.JspWebProject.entity;

import java.util.Date;

public class NoticeView extends Notice{

    private int cmtCnt;
    public NoticeView(int id, String title, String author, int views, Date date, int cmtCnt) {
        super(id, title, author, views, date, "");
        this.cmtCnt = cmtCnt;
    }

    public int getCmtCnt() {
        return cmtCnt;
    }

    public void setCmtCnt(int cmtCnt) {
        this.cmtCnt = cmtCnt;
    }
}
