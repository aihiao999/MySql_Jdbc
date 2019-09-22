package com.lywgames.myjdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lywgames.util.JDBCUtil;

public class MyJDBC {
	
	public static void main(String[] args) throws SQLException {
		// 1.获取连接对象
		Connection conn = JDBCUtil.getConn(); 
		// 2.创建statement, 跟数据库打交道, 一定需要这个对象
		Statement st = conn.createStatement();
		// 3.执行查询sql, 获取ResultSet结果集
		ResultSet rs = st.executeQuery("select * from user");
		// 4.使用ResultSet结果集遍历, 下标从1开始
		while(rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getTimestamp(4));
		}
		// 5.释放资源
		JDBCUtil.release(conn, st, rs);
	}
	
}
