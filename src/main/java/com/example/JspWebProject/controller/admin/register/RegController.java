package com.example.JspWebProject.controller.admin.register;

import com.example.JspWebProject.entity.Notice;
import com.example.JspWebProject.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;


@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/admin/board/notice/reg.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> title = Optional.ofNullable(req.getParameter("title"));
        Optional<String> content = Optional.ofNullable(req.getParameter("content"));
        //공지글 생성 후 등록
        Notice notice = new Notice(1, title.get(), "admin", 1, new Date(), content.get());

        NoticeService service = new NoticeService();
        service.insertNotice(notice);

        //redirect
        resp.sendRedirect("list");
    }
}
