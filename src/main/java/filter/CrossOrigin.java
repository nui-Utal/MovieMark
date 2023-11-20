package filter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created by Xu on 2023/11/16 15:38.
 */
//@WebFilter("/*")
@WebFilter(filterName = "CrossOrigin", urlPatterns = "/*")
public class CrossOrigin extends HttpFilter {
    private static final Logger logger = LogManager.getLogger(CrossOrigin.class);


    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
//        System.out.println("something...");
        logger.info("deal cors....");
        // 允许所有来源的请求
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许常见的请求方法
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        // 允许请求头中的Content-Type字段
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // 继续处理请求
        chain.doFilter(request, response);
    }
}