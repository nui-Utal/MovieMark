package utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by Xu on 2023/11/13 10:40.
 */
public class Request {

    /**
     * 读取请求体中的json数据
     * @param request
     * @return
     * @throws IOException
     */
    public static String getJson(HttpServletRequest request) throws IOException {
        String contentType = request.getContentType();
        if (!(contentType != null && contentType.startsWith("application/json"))) {
            // 请求数据格式必须是json
            return "";
        }
        BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            sb.append(inputStr);
        }
        return sb.toString();
    }
}
