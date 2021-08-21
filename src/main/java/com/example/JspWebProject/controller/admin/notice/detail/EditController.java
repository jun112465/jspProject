package com.example.JspWebProject.controller.admin.notice.detail;

import com.example.JspWebProject.entity.Notice;
import com.example.JspWebProject.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/admin/board/notice/detail/edit")
public class EditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_ = req.getParameter("id");
        int id = Integer.parseInt(id_);
        req.setAttribute("id", id);
        req.getRequestDispatcher("/WEB-INF/views/admin/board/notice/edit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_ = req.getParameter("id");
        int id = Integer.parseInt(id_);
        String title = req.getParameter("title");
        String content = req.getParameter("content");


        Notice notice = new Notice(id,title,"", 1, new Date(), content);
        NoticeService service = new NoticeService();
        service.updateNotice(notice);
        resp.sendRedirect("../list");
    }
}

