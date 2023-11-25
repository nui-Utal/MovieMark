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
 * 业务层
 * created by Xu on 2023/11/11 16:11.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserService userService = new UserService();

    /**
     * 登录时的查询，仅得到
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
        User user = mapper.readValue(receive, User.class);
        boolean login = userService.login(user);
        if (!login) {
            Response.Json(resp, Result.fail("登陆失败"));
            return;
        }
        Cookie email = new Cookie("myfiview-email", user.getEmail());
        email.setMaxAge(60 * 60 * 240);
//        email.setPath("/");
//        // 禁止js访问cookie
        email.setHttpOnly(true);
        resp.addCookie(email);
        Response.Json(resp, Result.ok());
    }
    // cookie只能在https连接下传输
//        email.setSecure(true);
}
