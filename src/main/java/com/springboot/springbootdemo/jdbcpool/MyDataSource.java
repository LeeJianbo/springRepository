package com.springboot.springbootdemo.jdbcpool;

import com.springboot.springbootdemo.util.JDBCUtils;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * description: 自定义数据源连接池
 * date: 2019-08-25 9:36
 * author: Lee
 */
public class MyDataSource implements DataSource {
    // 用于保存连接对象
    // Connection对象应保证将自己返回到连接池的集合对象中，而不要把Connection还给数据库
    private LinkedList<Connection> dataSources = new LinkedList<>();

    // 构造函数
    public MyDataSource() {
        // 一次性创建10个连接
        for (int i = 0; i < 10; i++) {
            try {
                Connection conn = JDBCUtils.getInstance().getCon();
                // 将连接加入连接池中
                dataSources.add(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        // 取出连接池中一个连接
        final Connection conn = dataSources.removeFirst(); // 删除第一个连接返回
        System.out.println("取出一个连接剩余 " + dataSources.size() + "个连接！");
        // 将目标Connection对象进行增强
        Connection connProxy = (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(),
                conn.getClass().getInterfaces(), new InvocationHandler() {
            // 执行代理对象任何方法 都将执行 invoke
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("close")) {
                    // 需要加强的方法
                    // 不将连接真正关闭，将连接放回连接池
                    releaseConnection(conn);
                    return null;
                } else {
                    // 不需要加强的方法
                    return method.invoke(conn, args); // 调用真实对象方法
                }
            }
        });
        return connProxy;
    }

    // 将连接放回连接池
    public void releaseConnection(Connection conn) {
        dataSources.add(conn);
        System.out.println("将连接 放回到连接池中 数量:" + dataSources.size());
        System.out.println("实际上是调用的releaseConnection方法");
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
