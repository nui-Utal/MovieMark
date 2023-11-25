package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * created by Xu on 2023/11/11 22:52.
 */
public class DruidUtil {
    private static DruidDataSource druidDataSource;
    // 静态代码块初始化定义DruidDataSource对象
    static {
        try {
            // 读取druid.properties文件中配置的属性
            // 在resource下需要定位到项目源码目录 getClassLoader()
            InputStream is = DruidUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(is);
            // 使用属性文件初始化DruidDataSource对象
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从连接池对象中获取mysql连接
     * @return Connection
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = druidDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
