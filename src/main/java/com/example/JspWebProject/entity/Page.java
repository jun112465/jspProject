package com.example.JspWebProject.entity;

public class Page {
    private int start;
    private int end;
    private int current;
    private int totalPage;


    public Page(int current, int totalPage){
        this.current = current;
        this.totalPage = totalPage;

        this.start = current/10*10+1;
        if(totalPage > this.start+9)
            this.end = this.start + 9;
        else
            this.end = totalPage;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void nextPage(int current){

    }

    public void prevPage(int current){

    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
