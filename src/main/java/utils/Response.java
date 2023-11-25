package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * created by Xu on 2023/11/19 11:31.
 */
public class Response {
    /**
     * 向浏览器响应json类型的数据
     * @param response
     * @param value 将对象转为json
     * @throws IOException
     */
    public static void Json(HttpServletResponse response, Object value) throws IOException {
        // 响应使用json，且允许中文
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper sendMapper = new ObjectMapper();
        // 类要有相关的public的getter、setter
        String send = sendMapper.writeValueAsString(value);
        PrintWriter out = response.getWriter();
        out.write(send);
        out.flush();
        out.close();
    }
}
