package com.springboot.springbootdemo.jdbcpool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * description: 自定义连接池主函数
 * date: 2019-08-25 10:04
 * author: Lee
 */
public class customizePool {
    public static void main(String[] args) {
        try {
            MyDataSource myDataSource = new MyDataSource();
            System.out.println("创建连接：");
            Connection connection = myDataSource.getConnection();
            System.out.println("=========================");
            System.out.println("释放连接：");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
