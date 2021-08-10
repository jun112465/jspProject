package com.example.JspWebProject.controller;

import com.example.JspWebProject.web.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int noticeId = Integer.parseInt(req.getParameter("id"));
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String server = "localhost"; // MySQL 서버 주소
        String database = "JdbcTutorial"; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "1q2w3e4r!"; // MySQL 서버 비밀번호


        try {
            //드라이버 로딩
            Class.forName("com.mysql.jdbc.Driver");
            //jdbc 연결
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            System.out.println("정상적으로 연결되었습니다.");

            String sql = "Select * from JdbcTutorial.Notice where id=?";
            st = con.prepareStatement(sql);
            st.setInt(1,noticeId);
            rs = st.executeQuery();
            rs.next();
            //model setting
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            Date date = rs.getDate("date");
            int views = rs.getInt("views");
            String description = rs.getString("description");
            Notice notice = new Notice(id,title,author,views,date,description);
            //req 저장소에 담기
            req.setAttribute("n", notice);
            //forward - req,resp 넘겨서 새로운 jsp 페이지로 넘어가기
            req.getRequestDispatcher("/WEB-INF/views/notice/detail.jsp").forward(req,resp);

            //연결종료
            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
