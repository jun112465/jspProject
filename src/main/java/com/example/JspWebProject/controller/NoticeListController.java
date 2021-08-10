package com.example.JspWebProject.controller;

import com.example.JspWebProject.web.Notice;
import com.example.JspWebProject.web.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


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
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            System.out.println("db connected");


            // 전체 글의 개수 && 현재 선택된 페이지 값 전달
            String sql = "select count(*) as cnt from Notice";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            rs.next();
            int noticeCnt = rs.getInt("cnt");
            int totalPage = noticeCnt/10 + noticeCnt%10;

            //페이지값 넘겨주기
            Optional<String> _page = Optional.ofNullable(req.getParameter("p"));
            int curPage = Integer.parseInt(_page.orElse("1"));
            Page page = new Page(curPage, totalPage);
            req.setAttribute("page", page);

            //현재 선택된 페이지에 맞게 글 10개씩 올려주기
            int startId = noticeCnt - (curPage-1)*10;
            sql = String.format("select * from Notice where %d-10<id && id<=%d order by id desc", startId, startId);
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            System.out.println(rs.toString());
            List<Notice> noticeList = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int views = rs.getInt("views");
                Date date = rs.getDate("date");
                String description = rs.getString("description");
                Notice notice = new Notice(id,title,author,views,date, description);
                noticeList.add(notice);
            }
            for(Notice n:noticeList){
                System.out.println(n.toString());
            }
            req.setAttribute("list", noticeList);




            req.getRequestDispatcher("/WEB-INF/views/notice/list.jsp").forward(req,resp);
            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean isNumeric(String s){
        try{
            Double.parseDouble(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

}
