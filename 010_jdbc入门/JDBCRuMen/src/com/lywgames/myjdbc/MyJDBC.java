package com.lywgames.myjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyJDBC {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 1.加载MySQL6及其以上版本驱动
		Class.forName("com.mysql.cj.jdbc.Driver");
		// 2.建立连接(连接对象内部其实包含了Socket对象, 是一个远程连接, 比较耗时。)
		// 参数一: 协议 + 访问的数据库; 参数二: 用户名; 参数三: 密码。
		// serverTimezone=Asia/Shanghai 时区亚洲上海
		String url = "jdbc:mysql://192.168.25.130:3306/studyjdbc?useSSL=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
		Connection connection = DriverManager.getConnection(url, "root", "lyw123456");
		// 3.创建statement, 跟数据库打交道, 一定需要这个对象
		// 这里导入Statement包一定要导入正确, 是java.sql.Statement
		Statement statement = connection.createStatement();
		// 4.执行查询sql, 获取ResultSet结果集
		// 这里导入ResultSet包一定要导入正确, 是java.sql.ResultSet
		ResultSet resultSet = statement.executeQuery("select * from user");
		// 5.使用ResultSet结果集遍历, 下标从1开始
		while(resultSet.next()) {
			System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getTimestamp(4));
		}
		// 6.释放资源
		resultSet.close();
		statement.close();
		connection.close();
	}
}
