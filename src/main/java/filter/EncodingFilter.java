package filter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

/**
 * created by Xu on 2023/11/19 22:41.
 */
@WebFilter(filterName = "CharsetFilter", urlPatterns = "/*")
public class EncodingFilter extends HttpFilter {

    private static final Logger logger = LogManager.getLogger(EncodingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("deal charset....");
        request.setCharacterEncoding("utf-8"); //将编码改为utf-8
        response.setContentType("text/html;charset=utf-8");
        chain.doFilter(request, response);
    }

}
