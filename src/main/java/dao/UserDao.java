package dao;

import entity.User;
import utils.DruidUtil;

import java.sql.*;

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
    public boolean register(User user) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement stmt;
        String sql = "INSERT INTO user(email, pwd, salt, name, last_time) values(?, ?, ?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPwd());
            stmt.setString(3, user.getSalt());
            stmt.setString(4, user.getName());
            stmt.setDate(5, (Date) user.getLastTime());
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



}
