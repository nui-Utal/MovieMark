package servlet.user;

import dto.Result;
import service.UserService;
import utils.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created by Xu on 2023/11/19 10:59.
 */
@WebServlet("/isRegistered")
public class IsRegisterServlet extends HttpServlet {

    private UserService userService = new UserService();

    /**
     * 验证邮箱是否被注册过
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String userRegistered = userService.isUserRegistered(email);
        if (!"".equals(userRegistered)) {
            Response.Json(resp, Result.fail("该邮箱已被注册"));
            return;
        }
        Response.Json(resp, Result.ok());
    }
}
