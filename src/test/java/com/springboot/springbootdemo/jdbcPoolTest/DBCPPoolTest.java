package com.springboot.springbootdemo.jdbcPoolTest;

import com.springboot.springbootdemo.util.JDBCUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * description: DBCPPoolTest
 * date: 2019-08-25 10:22
 * author: Lee
 */
public class DBCPPoolTest {
    /**
     * DBCP连接池使用默认配置test
     * @throws SQLException
     */
    @Test
    public void dbcpPoolTest() throws SQLException {
        // 使用BasicDataSource 创建连接池
        BasicDataSource basicDataSource = new BasicDataSource();
        // 创建连接池 一次性创建多个连接池
        // 连接池 创建连接 ---至少需要四个参数
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://10.0.202.75:3306/lijb?useUnicode=true&characterEncoding=utf8&useSSL=false");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("troy.abc123");

        // 从连接池中获取连接
        Connection conn = basicDataSource.getConnection();
        String sql = "select * from user";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
        basicDataSource.close();
    }

    /**
     * DBCP使用自定义配置文件test
     * @throws Exception
     */
    @Test
    public void dbcpPoolTest2() throws Exception {
        // 读取dbcp.properties ---- Properties
        Properties properties = new Properties();
        properties.load(new FileInputStream(this.getClass().getResource("/dbcp.properties").getFile()));
        DataSource basicDataSource = BasicDataSourceFactory
                .createDataSource(properties);
        // 从连接池中获取连接
        Connection conn = basicDataSource.getConnection();
        String sql = "select * from user";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
        JDBCUtils.getInstance().close(conn, stmt, rs);
    }
}
