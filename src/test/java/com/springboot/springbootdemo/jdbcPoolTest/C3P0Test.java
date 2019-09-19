package com.springboot.springbootdemo.jdbcPoolTest;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.springboot.springbootdemo.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * description: C3P0Test
 * date: 2019-08-26 10:45
 * author: Lee
 */
public class C3P0Test {
    /**
     * C3P0test
     * @throws Exception
     */
    @Test
    public void c3p0Test1() throws Exception{
        // 使用c3p0配置文件
        // 自动加载src/c3p0-config.xml，不需要dbcp手动加载dbcpconfig.properties配置文件！
        // ComboPooledDataSource dataSource = new ComboPooledDataSource(); // 使用默认配置
        ComboPooledDataSource dataSource = new ComboPooledDataSource("testConfig"); // 使用自定义配置
        Connection conn = dataSource.getConnection();
        String sql = "select * from user";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
        JDBCUtils.getInstance().close(conn,stmt,rs);
    }
}
