package servlet.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.UserService;
import utils.*;
import dto.Result;
import dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 发送邮箱验证码
 * created by Xu on 2023/11/16 13:56.
 */

@WebServlet("/VerifyCode")
public class VeriCodeSendServlet extends HttpServlet {

    private UserService userService = new UserService();

    /**
     * 检查邮箱与验证码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String receive = Request.getJson(req);
        // 数据不能为空
        if ("".equals(receive)) {
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        UserDto user = mapper.readValue(receive, UserDto.class);

        String userRegistered = userService.isUserRegistered(user.getEmail());
        if (!"".equals(userRegistered)) {
            Response.Json(resp, Result.fail("该邮箱已被注册"));
            return;
        }
        String code = Generator.generateVerificationCode();
        EMailUtil.send(user.getEmail(), code);
        String filePath = Cache.writeCache(user.getEmail(), code);
        CacheFileDeleteTask.startTask(filePath);
        Response.Json(resp, Result.ok());
    }
}
