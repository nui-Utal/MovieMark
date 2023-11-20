package servlet.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import utils.*;
import dto.Result;
import dto.UserDto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;

/**
 * created by Xu on 2023/11/16 13:56.
 */

@WebServlet("/VerifyCode")
public class VeriCodeSendServlet extends HttpServlet {

    /**
     * 检查邮箱与验证码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contentType = req.getContentType();
        if (!(contentType != null && contentType.startsWith("application/json"))) {
            // 请求数据格式必须是json
            return;
        }
        String receive = Request.getJson(req);
        // 数据不能为空
        if (receive == null || "".equals(receive)) {
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        UserDto user = mapper.readValue(receive, UserDto.class);

        String code = Generator.generateVerificationCode();
        String filePath = Cache.writeCache(user.getEmail(), code);
        CacheFileDeleteTask.startTask(filePath);

        Response.Json(resp, Result.ok());
    }
}
