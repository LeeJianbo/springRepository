package com.springboot.springbootdemo.jdbcpool;

import com.springboot.springbootdemo.util.JDBCUtils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * description: 该类是web项目代码，需要在web环境下并由tomcat才能进行测试。
 * date: 2019-08-26 11:30
 * author: Lee
 */
public class TomcatServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 创建检索对象
            Context initCtx = new InitialContext();
            // 默认查找顶级java，名称串固定：java:comp/env
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            // 根据设置的名称查找连接池对象
            DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
            // 获得连接池中一个连接，接下来的代码和连接池无关
            Connection conn = ds.getConnection();
            String sql = "select * from user ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
            JDBCUtils.getInstance().close(conn, stmt, rs);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
