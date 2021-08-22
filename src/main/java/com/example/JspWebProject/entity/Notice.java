package com.example.JspWebProject.entity;

import java.util.Date;

public class Notice {
    private int id;
    private String title;
    private String author;
    private int views;
    private Date date;
    private String description;
    private String files;

    public String[] getFileList() {
        return fileList;
    }

    public String[] fileList;

    public Notice(){

    }
    public Notice(int id, String title, String author, int views, Date date, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.views = views;
        this.date = date;
        this.description = description;
    }
    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
        if(this.files != null)
            setFileList();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getViews() {
        return views;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {return description;}

    public void setFileList() {
        int i=0;
        this.fileList = new String[2];
        for(String tmp : files.split(",")){
            this.fileList[i] = tmp;
            i++;
        }
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", views=" + views +
                ", date=" + date +
                '}';
    }
}
