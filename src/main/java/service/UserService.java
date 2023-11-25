package service;

import dao.UserDao;
import entity.User;
import utils.Generator;

import java.util.LinkedList;

/**
 *
 * created by Xu on 2023/11/13 10:39.
 */
public class UserService {
    private UserDao userDao = new UserDao();

    /**
     * 账号注册前的检验，邮箱是否被注册
     * @param email
     * @return
     */
    public String isUserRegistered(String email) {
        if(!userDao.isEmailNotExists(email)) {
            return "该账号已被注册，请登录";
        }
        return "";
    }

    /**
     * 注册账号，完善基本信息
     * @param user
     * @return
     */
    public boolean userRegister(User user) {
        user.setSalt(Generator.generateSalt(8));
        user.setPwd(Generator.HashOfPwdWithSalt(user.getPwd() + user.getSalt()));
        return userDao.createUser(user);
    }

    /**
     * 判断用户是否匹配数据库中已有用户，进行登录。
     * @param inputUser
     * @return
     */
    public boolean login(User inputUser) {
        User userDB = userDao.getUserByEmail(inputUser.getEmail());
        if(userDB == null) {
            return false;
        }
        String hashed = Generator.HashOfPwdWithSalt(inputUser.getPwd() + userDB.getSalt());
        if(userDB.getPwd().equals(hashed)) {
            // 密码正确
            userDB.setPwd(hashed);
            return true;
        }
        return false;
    }

    /**
     * 调用数据层接口得到用户权限
     * @param email
     * @return
     */
    public LinkedList UserPermission(String email) {
        return userDao.getUserPermission(email);
    }
}
