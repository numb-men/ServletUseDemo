package cn.hengyumo.learn;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

public class TestServlet1 extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init servlet");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy servlet");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("service servlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("post 获取到参数 name = " + request.getParameter("name"));
        printWriter.println("<h1>test1 post 测试post</h1>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        if (request.getParameter("name") == null) {
            response.sendError(403, "name 参数必须要有");
            return;
        }

        if (request.getParameter("name").equals("123")) {
            // 路由的重定向
            response.sendRedirect("/");
        }

        // 获取PrintWriter输出对象
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<h1>test1 get 测试get</h1>");

        printWriter.println("pathInfo: " + request.getPathInfo());
        printWriter.println("<br />");
        printWriter.println("contextPath: " + request.getContextPath());
        printWriter.println("<br />");
        printWriter.println("queryString: " + request.getQueryString());
        printWriter.println("<br />");
        printWriter.println("method: " + request.getMethod());
        printWriter.println("<br />");
        printWriter.println("remoteAddr: " + request.getRemoteAddr());
        printWriter.println("<br />");
        printWriter.println("servletPath: " + request.getServletPath());
        printWriter.println("<br />");
        printWriter.println("requestUrl: " + request.getRequestURL());
        printWriter.println("<br />");
        printWriter.println("requestUrI: " + request.getRequestURI());
        printWriter.println("<br />");

        printWriter.println("headers:");
        printWriter.println("<br />");
        Enumeration headers = request.getHeaderNames();
        printWriter.println("<table border='1'>");
        printWriter.println("<tr bgcolor='#c9c9c9'><th>名称</th><th>值</th></tr>");
        while(headers.hasMoreElements()){
            String key = headers.nextElement().toString();
            String value = request.getHeader(key);
            printWriter.println("<tr>"
                    + "<td>" + key + "</td>"
                    + "<td>" + value + "</td>"
                    + "</tr>");
        }
        printWriter.println("</table>");

        Cookie cookie = new Cookie("name", request.getParameter("name"));
        response.addCookie(cookie);

        response.addHeader("my-header", "hhhhhhh");

        // 设置刷新自动加载时间为 5 秒
        response.setIntHeader("Refresh", 5);

        //使用默认时区和语言环境获得一个日历
        Calendar cale = Calendar.getInstance();
        //将Calendar类型转换成Date类型
        Date tasktime=cale.getTime();
        //设置日期输出的格式
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式化输出
        String nowTime = df.format(tasktime);

        printWriter.println("<h1> 当前时间是：" + nowTime + "</h1>");

        response.setStatus(200);

        /*
        request.getParameterValues(String name) 是获得如 checkbox 类（名字相同，但值有多个）的数据，接收数组变量 ，如 checkobx 类型。

        request.getParameter(String name) 是获得相应名的数据，如果有重复的名，则返回第一个的值，接收一般变量 ，如 text 类型。
         */

        printWriter.println("get 获取到参数 name = " + request.getParameter("name"));
        // 路由前不加/，即是war下相对路由
        printWriter.println("<form action='test1' method='POST'>");
        printWriter.println("姓名：<input type='text' name='name' />");
        printWriter.println("<input type='submit' value='确定' />");
        printWriter.println("</form>");
    }
}
