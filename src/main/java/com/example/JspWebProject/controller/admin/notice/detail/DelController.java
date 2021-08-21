package com.example.JspWebProject.controller.admin.notice.detail;

import com.example.JspWebProject.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/board/notice/detail/del")
public class DelController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_ = req.getParameter("id");
        int id = Integer.parseInt(id_);

        NoticeService service = new NoticeService();
        service.deleteNotice(id);

        resp.sendRedirect("../list");
    }
}
