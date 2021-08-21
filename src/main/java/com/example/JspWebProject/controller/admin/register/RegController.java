package com.example.JspWebProject.controller.admin.register;

import com.example.JspWebProject.entity.Notice;
import com.example.JspWebProject.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Optional;

@MultipartConfig(
        //일정 이상 용량의 데이터를 전송 받았을 때 메모리가 아닌 ssd에 저장되게 하는 경로
        //절대 경로를 사용해야 한다.
        //위에서 정의한 데이터 용량 기준 - 바이트가 단위이다.
        fileSizeThreshold = 1024*1024,
        //최대 파일 용량 - 5mb (파일 하나)
        maxFileSize = 1024*1024*50,
        //최대 전송 용량 (모든 파일)
        maxRequestSize = 1024*1024*50*5
)
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
        Part filePart = req.getPart("file");

        String realPath = req.getServletContext().getRealPath("/upload");
        String fileName = filePart.getSubmittedFileName();

        String filePath = realPath+File.separator+fileName;

        System.out.println(realPath);
        System.out.println(fileName);
        System.out.println(filePath);

        InputStream fis = filePart.getInputStream();
        FileOutputStream fos = new FileOutputStream(filePath);

        byte[] buf = new byte[1024];
        int size = 0;
        while((size=fis.read(buf)) != -1){
            fos.write(buf,0,size);
        }
        fos.close();
        fis.close();

        //공지글 생성 후 등록
        Notice notice = new Notice(1, title.get(), "admin", 1, new Date(), content.get());

        NoticeService service = new NoticeService();
        service.insertNotice(notice);

        String path = req.getServletContext().getRealPath("/upload");
        System.out.println(path);

        //redirect
        resp.sendRedirect("list");
    }
}
