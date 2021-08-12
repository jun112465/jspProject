package com.example.JspWebProject.service;

import com.example.JspWebProject.web.Notice;
import com.example.JspWebProject.web.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.DriverManager.*;

public class NoticeService {

    public List<Notice> getNoticeList(){
        return getNoticeList(null, null, 1);
    }
    public List<Notice> getNoticeList(int page){
        return getNoticeList(null, null, page);
    }
    public List<Notice> getNoticeList(String field, String query, int page) {

        List<Notice> noticeList = new ArrayList<>();

        int startRowNumber = (page-1)*10+1;
        int endRowNumber = startRowNumber+9;
        String sql = "select * from (select Notice.*, @rownum:=@rownum+1 as rownum from JdbcTutorial.Notice, (select @rownum:=0) as tmp order by date desc, id desc) as t1 where rownum between "+startRowNumber+" and "+endRowNumber;

        //db 세팅
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String server = "localhost"; // MySQL 서버 주소
        String database = "JdbcTutorial"; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "1q2w3e4r!"; // MySQL 서버 비밀번호
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            System.out.println("db connected");


            // 전체 글의 개수 && 현재 선택된 페이지 값 전달
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int views = rs.getInt("views");
                Date date = rs.getDate("date");
                String description = rs.getString("description");
                Notice notice = new Notice(id,title,author,views,date, description);

                System.out.println(notice.toString());
                noticeList.add(notice);
            }


            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return noticeList;
    }

    public int getNoticeCount(){

        return getNoticeCount(null, null);
    }
    public int getNoticeCount(String field, String query){

        String sql = "select count(*) as cnt from JdbcTutorial.Notice";
        int noticeCount = 1;
        //db 세팅
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String server = "localhost"; // MySQL 서버 주소
        String database = "JdbcTutorial"; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "1q2w3e4r!"; // MySQL 서버 비밀번호
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            System.out.println("db connected");
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            rs.next();
            noticeCount = rs.getInt("cnt");

            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return noticeCount;
    }

    public Notice getNotice(int id){
        Notice notice = null;
        String sql = "select * from JdbcTutorial.Notice where id = " + id;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String server = "localhost"; // MySQL 서버 주소
        String database = "JdbcTutorial"; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "1q2w3e4r!"; // MySQL 서버 비밀번호
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            System.out.println("db connected");
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            if(rs.next()){
                int nid = Integer.parseInt(rs.getString("id"));
                String title = rs.getString("title");
                String author = rs.getString("author");
                int views = Integer.parseInt(rs.getString("views"));
                Date date = rs.getDate("date");
                String description = rs.getString("description");

                notice = new Notice(nid, title, author, views, date, description);
            }
            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return notice;
    }
    public Notice getNextNotice(int id) {
        String sql = "select * from ( select @rownum:=@rownum+1 as rownum,Notice.* from JdbcTutorial.Notice, (select @rownum:=0) as tmp where date >= (select date from JdbcTutorial.Notice where id = ?)and id > ?) as tmp where rownum = 1";
        String.format(sql, id, id);
        Notice notice = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String server = "localhost"; // MySQL 서버 주소
        String database = "JdbcTutorial"; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "1q2w3e4r!"; // MySQL 서버 비밀번호
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            System.out.println("db connected");
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                int nid = Integer.parseInt(rs.getString("id"));
                String title = rs.getString("title");
                String author = rs.getString("author");
                int view = Integer.parseInt(rs.getString("view"));
                Date date = rs.getDate("date");
                String description = rs.getString("description");

                notice = new Notice(nid, title, author, view, date, description);
            }
            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return notice;
    }
    public Notice getPrevNotice(int id){
        return null;
    }
}