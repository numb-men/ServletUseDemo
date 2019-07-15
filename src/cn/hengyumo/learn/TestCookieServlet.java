package cn.hengyumo.learn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@WebServlet(name = "TestCookieServlet", urlPatterns = "/testCookie")
public class TestCookieServlet extends HttpServlet {

    private int times = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
//        name = URLEncoder.encode(name, "utf-8");
        // 中文b编码、解码，URL.decode(name, "utf-8")

        Cookie timesCookie = new Cookie("times", "" + times);
        // 设置路径
        timesCookie.setPath("/");
        // 设置域名
        timesCookie.setDomain("localhost");
        // 过期时间，单位秒
        timesCookie.setMaxAge(100);
        // 设置注释
        timesCookie.setComment("这个cookie记录访问的次数");
        // 不允许前端修改
        timesCookie.setHttpOnly(true);
        // 设置安全性，是否只允许https
        timesCookie.setSecure(false);

        response.addCookie(timesCookie);

        // 删除cookie
        // cookie.setMaxAge(0);

        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies();
        out.println("<ul>");
        for (Cookie cookie : cookies) {
            out.println("<li>" + cookie.getName() + "***" + cookie.getValue() + "***" + cookie.getMaxAge()
                    + "***" + cookie.getPath() + "</li>");
            if (cookie.getName().equals("name")) {
                name = cookie.getValue();
            }
        }
        out.println("</ul>");

        out.println("<h1>" + name + "访问次数" + times + "</h1>");

        times ++;
    }
}
