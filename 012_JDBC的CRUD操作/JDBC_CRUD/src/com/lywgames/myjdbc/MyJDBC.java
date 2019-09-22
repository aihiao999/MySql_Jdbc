package com.lywgames.myjdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lywgames.util.JDBCUtil;

/**
 * CRUD操作(create添加数据; read读取数据;  update修改数据; delete删除数据)
 */
public class MyJDBC {
	
	public static void main(String[] args) throws SQLException {
		select();
		delete();
		System.out.println("-------------------------------");
		select();
	}
	
	public static void select() throws SQLException {
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
	
	public static void insert() throws SQLException {
		// 1.获取连接对象
		Connection conn = JDBCUtil.getConn(); 
		// 2.创建statement, 跟数据库打交道, 一定需要这个对象
		Statement st = conn.createStatement();
		// 3.执行查询sql
		st.executeUpdate("insert into user (username,password,registertime) values ('小翠',666,now())");
		// 4.释放资源
		JDBCUtil.release(conn, st);
	}
	
	public static void update() throws SQLException {
		// 1.获取连接对象
		Connection conn = JDBCUtil.getConn(); 
		// 2.创建statement, 跟数据库打交道, 一定需要这个对象
		Statement st = conn.createStatement();
		// 3.执行查询sql
		st.executeUpdate("update user set username = " + "'无名'" + ",password = " + "'wu3456'" + " where id = " + 1);
		// 4.释放资源
		JDBCUtil.release(conn, st);
	}
	
	public static void delete() throws SQLException {
		// 1.获取连接对象
		Connection conn = JDBCUtil.getConn(); 
		// 2.创建statement, 跟数据库打交道, 一定需要这个对象
		Statement st = conn.createStatement();
		// 3.执行查询sql
		st.executeUpdate("delete from user where id = " + 2);
		// 4.释放资源
		JDBCUtil.release(conn, st);
	}
}
