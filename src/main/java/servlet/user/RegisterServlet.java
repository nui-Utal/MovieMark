package servlet.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Result;
import entity.User;
import service.UserService;
import utils.Request;
import utils.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created by Xu on 2023/11/11 20:49.
 */
@WebServlet("/user/register")
public class RegisterServlet extends HttpServlet {

    UserService userService = new UserService();

    /**
     * 实现用户注册
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = Request.getJson(req);
        if ("".equals(json)) {
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);
        boolean register = userService.userRegister(user);
        if(register) {
            Response.Json(resp, Result.ok());
        } else {
            Response.Json(resp, Result.fail("注册失败，请稍后再试"));
        }
        // 设置cookie，默认保持登录
        Cookie email = new Cookie("myfiview-email", user.getEmail());
        email.setMaxAge(60 * 60 * 240);
        email.setPath("/");
        // 禁止js访问cookie
        email.setHttpOnly(true);
        resp.addCookie(email);
    }
}