package com.example.JspWebProject.controller;

import com.example.JspWebProject.service.NoticeService;
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

        NoticeService service = new NoticeService();
        List<Notice> list = service.getNoticeList();
        req.setAttribute("list", list);

        int noticeCount = service.getNoticeCount();
        int totalPage = (noticeCount+9)/10;
        req.setAttribute("totalPage", totalPage);
        req.getRequestDispatcher("/WEB-INF/views/notice/list.jsp").forward(req,resp);
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
