package service;

import dao.UserDao;

/**
 * 业务层
 * created by Xu on 2023/11/13 10:39.
 */
public class UserService {
    private UserDao userDao = new UserDao();

    public String isUserRegistered(String email) {
        if(!userDao.isEmailNotExists(email)) {
            return "该账号已被注册，请登录";
        }
        return "";
    }
}
