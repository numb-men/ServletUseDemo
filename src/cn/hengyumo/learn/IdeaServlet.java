package cn.hengyumo.learn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 使用注解无需在web.xml中对servlet进行配置，强烈推荐
@WebServlet(name = "IdeaServlet", urlPatterns = "/idea")
public class IdeaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        // 获取PrintWriter输出对象
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<h1> 测试 注解方式</h1>");
    }
}
