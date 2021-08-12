package com.example.JspWebProject.controller;

import com.example.JspWebProject.service.NoticeService;
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

        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println(id);
        NoticeService service = new NoticeService();
        Notice notice = service.getNotice(id);
        //forward - req,resp 넘겨서 새로운 jsp 페이지로 넘어가기
        req.setAttribute("n", notice);
        req.getRequestDispatcher("/WEB-INF/views/notice/detail.jsp").forward(req,resp);

    }
}
