package com.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接测试应用
 */
public class App {
    public static void main(String[] args) {
        System.out.println("开始测试数据库连接...");

        String url = "jdbc:mysql://localhost:3306/db_desktoppet_todo?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF8&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "123456";

        try {
            // 加载MySQL驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL驱动加载成功");

            // 尝试建立连接
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("数据库连接成功！");
            System.out.println("数据库URL: " + url);
            System.out.println("用户名: " + username);

            // 关闭连接
            connection.close();
            System.out.println("连接已关闭");

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL驱动未找到: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("数据库连接失败: " + e.getMessage());
            System.err.println("SQL状态: " + e.getSQLState());
            System.err.println("错误代码: " + e.getErrorCode());
        } catch (Exception e) {
            System.err.println("其他错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
