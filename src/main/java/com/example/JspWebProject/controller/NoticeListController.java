package com.example.JspWebProject.controller;

import com.example.JspWebProject.entity.NoticeView;
import com.example.JspWebProject.service.NoticeService;
import com.example.JspWebProject.entity.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<String> _field = Optional.ofNullable(req.getParameter("f"));
        String field = _field.orElse("");
        Optional<String> _query = Optional.ofNullable(req.getParameter("q"));
        String query = _query.orElse("");
        Optional<String> _page = Optional.ofNullable(req.getParameter("p"));
        int page = Integer.parseInt(_page.orElse("1"));
        req.setAttribute("curPage", page);

        NoticeService service = new NoticeService();
        List<NoticeView> list = service.getNoticeList(field, query, page);
        req.setAttribute("list", list);

        int noticeCount = service.getNoticeCount(field, query);
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
