package servlet.user;

import dto.Result;
import utils.Cache;
import utils.CacheFileDeleteTask;
import utils.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created by Xu on 2023/11/20 10:24.
 */
@WebServlet("/checkVerificationCode")
public class VerifCodeCheckServlet extends HttpServlet {

    /**
     * 检验邮箱与验证码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        String fileCode = Cache.readCache(email);
        if ("".equals(fileCode)) {
            Response.Json(resp, Result.fail("未找到发送给该邮箱的验证码"));
            return;
        }
        if (fileCode.equals(code)) {
            Response.Json(resp, Result.ok());
        }
        Response.Json(resp, Result.fail("验证码错误"));
    }
}
