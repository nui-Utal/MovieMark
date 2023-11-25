package dao;

import entity.User;
import utils.DruidUtil;
import utils.Generator;

import java.sql.*;
import java.util.LinkedList;

/**
 * created by Xu on 2023/11/12 21:03.
 * 处理user表
 */
public class UserDao {
    /**
     * 插入数据
     * @param user
     * @return
     */
    public boolean createUser(User user) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement stmt;
        String sql = "INSERT INTO user(email, pwd, salt, name, register_time, role_id, status) values(?, ?, ?, ?, ?, 4, 1)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPwd());
            stmt.setString(3, user.getSalt());
            stmt.setString(4, user.getName());
            stmt.setString(5, Generator.getCurrentTime());
            int affectRows = stmt.executeUpdate();
            return affectRows == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断邮箱是否存在。
     * @param email
     * @return true
     */
    public boolean isEmailNotExists(String email) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement stmt;
        String exist = "";
        String sql = "select email from user where email = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                exist = rs.getString(1);  // 获取查询结果
            }
            return "".equals(exist);    //
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 得到数据库存储的用户信息
     * @param email
     * @return
     */
    public User getUserByEmail(String email) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        User user = new User();
        String sql = "select * from user where email = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user.setUid(rs.getInt("uid"));
                user.setEmail(rs.getString("email"));
                user.setPwd(rs.getString("pwd"));
                user.setAvatar(rs.getString("avatar"));
                user.setSalt(rs.getString("salt"));
                user.setName(rs.getString("name"));
                user.setRegisterTime(rs.getString("register_time"));
                user.setRoleId(rs.getString("role_id"));
                user.setStatus(rs.getInt("status"));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 查询user表得到用户角色id，通过角色id得到相应的权限
     * @param email
     * @return
     */
    public LinkedList getUserPermission(String email) {
        LinkedList list = new LinkedList();
        PreparedStatement stmt;
        ResultSet rs;
        Connection connection = DruidUtil.getConnection();
        String sql = "SELECT rp.pid FROM user u JOIN role_permission rp ON u.role_id = rp.rid WHERE u.email = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            while(rs.next()) {
                list.add(rs.getInt("pid"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
