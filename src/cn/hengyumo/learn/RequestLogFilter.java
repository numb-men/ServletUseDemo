package cn.hengyumo.learn;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "RequestLogFilter")
public class RequestLogFilter implements Filter {

    private String name = null;
    /**
     * 销毁，执行一次
     */
    public void destroy() {
    }

    /**
     * 执行过滤，在每次请求都指向
     *
     * @param chain 过滤器链
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");

        PrintWriter printWriter = resp.getWriter();

        printWriter.println("<h1 color='blue'>这是一个过滤器，获取到了web.xml的参数：" + name + "</h1>");

        // 执行剩余的过滤器链
        chain.doFilter(req, resp);
    }

    /**
     * 初始化，只执行一次
     * @param config web.xml中的配置
     */
    public void init(FilterConfig config) throws ServletException {
        name = config.getInitParameter("name");
    }

}
