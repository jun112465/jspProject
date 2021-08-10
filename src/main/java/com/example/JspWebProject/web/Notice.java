package com.example.JspWebProject.web;

import java.util.Date;

public class Notice {
    private int id;
    private String title;
    private String author;
    private int views;
    private Date date;
    private String description;

    public Notice(int id, String title, String author, int views, Date date, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.views = views;
        this.date = date;
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
