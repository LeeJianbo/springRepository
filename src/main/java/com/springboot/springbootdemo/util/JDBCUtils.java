package com.springboot.springbootdemo.util;

import java.sql.*;
import java.util.Properties;

/**
 * description: JDBCUtils
 * date: 2019-08-26 9:52
 * author: Lee
 */
public class JDBCUtils {

    private static Properties properties = new Properties();

    static {
        // 加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        try {
//            properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream("connection.properties"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private static JDBCUtils jdbcUtils;

    private JDBCUtils() {
        super();
    }

    public static JDBCUtils getInstance() {
        if (jdbcUtils == null) {
            jdbcUtils = new JDBCUtils();
        }
        return jdbcUtils;
    }

    /**
     * 获取连接
     *
     * @return
     * @throws SQLException
     */
    public Connection getCon() throws SQLException {
        // 申明 连接 参数
        String url = "jdbc:mysql://10.0.202.75:3306/lijb?useUnicode=true&characterEncoding=utf8&useSSL=false";
        String user = "root";
        String password = "troy.abc123";
//        String mysql_url = properties.getProperty("mysql_url");
//        String mysql_user = properties.getProperty("mysql_user");
//        String mysql_password = properties.getProperty("mysql_password");
        Connection con = null;
        con = DriverManager.getConnection(url, user, password);
        return con;
    }

    public void close(Connection con, Statement st, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(con, st);
    }

    public void close(Connection con, Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

