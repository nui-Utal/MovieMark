package servlet.user;

import dto.Result;
import service.UserService;
import utils.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 使用cookie进行免密登录
 * created by Xu on 2023/11/23 15:55.
 */
@WebServlet("/loginWithoutPwd")
public class CookieServlet extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = null;
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            resp.sendRedirect("");
        }
        for(Cookie c : cookies) {
            if("myfiview-email".equals(c.getName())) {
                email = c.getValue();
                // 刷新Cookie过期时间
                c.setMaxAge(60 * 60 * 24);
                Response.Json(resp, Result.ok(userService.UserPermission(email)));
            }
        }
        if(email == null || email.isEmpty()) {
            // 重定位到登录页
            resp.sendRedirect("");
        }
    }
}
