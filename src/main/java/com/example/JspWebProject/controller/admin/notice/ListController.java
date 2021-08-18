package com.example.JspWebProject.controller.admin.notice;

import com.example.JspWebProject.entity.NoticeView;
import com.example.JspWebProject.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //검색 필드
        Optional<String> _field = Optional.ofNullable(req.getParameter("f"));
        String field = _field.orElse("");
        //검색 내용
        Optional<String> _query = Optional.ofNullable(req.getParameter("q"));
        String query = _query.orElse("");
        //목록중에서 선택된 페이지
        Optional<String> _page = Optional.ofNullable(req.getParameter("p"));
        int page = Integer.parseInt(_page.orElse("1"));
        req.setAttribute("curPage", page);

        //notice 서비스 클래스 불러오기
        NoticeService service = new NoticeService();

        ////jsp에 넘겨주기 위한 데이터 준비하기
        //서비스 객체 자체
        //버튼 리스너를 위해 넘겨주는 것
        req.setAttribute("nService", service);

        //공지글 목록
        List<NoticeView> list = service.getNoticeList(field, query, page);
        req.setAttribute("list", list);
        //전체 페이지 수
        int noticeCount = service.getNoticeCount(field, query);
        int totalPage = (noticeCount+9)/10;
        req.setAttribute("totalPage", totalPage);

        //연결할 jsp 파일에 req,resp 넘겨주기
        req.getRequestDispatcher("/WEB-INF/views/admin/board/notice/list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NoticeService service = new NoticeService();
        String[] openIds = req.getParameterValues("openId");
        String[] dltIds = req.getParameterValues("dltId");
        String cmd = req.getParameter("sendBtn");
        System.out.println(cmd);
        switch(cmd){
            case "open":
                if(openIds == null) break;
                for(String openId : openIds)
                    System.out.println(openId);
                break;
            case "delete":
                if(dltIds == null) break;
                service.removeNoticeAll(dltIds);
                break;
        }

        resp.sendRedirect("list");
    }
}
