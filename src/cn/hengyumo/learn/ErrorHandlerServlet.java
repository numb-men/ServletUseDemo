package cn.hengyumo.learn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorHandlerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            // 错误信息的获取
            Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
            System.out.println(throwable);

            Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
            System.out.println(statusCode);

            String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
            System.out.println(servletName);

            String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
            System.out.println(requestUri);

            if (servletName == null){
                servletName = "Unknown";
            }
            if (requestUri == null){
                requestUri = "Unknown";
            }

            // 设置响应内容类型
            response.setContentType("text/html;charset=utf-8");

            PrintWriter out = response.getWriter();

            out.println("<h2>错误信息</h2>");
            out.println("Servlet Name : " + servletName + "</br></br>");
            out.println("status code : " + statusCode + "</br></br>");
            out.println("请求 URI: " + requestUri + "<br><br>");

            if (throwable != null) {
                out.println("异常信息: " + throwable.getMessage( ));
                out.println("异常类型 : " + throwable.getClass( ).getName( ) + "</br></br>");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
