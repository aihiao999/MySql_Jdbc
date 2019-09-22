package com.lywgames.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.lywgames.dao.UserDao;
import com.lywgames.util.JDBCUtil;

public class UserDaoImpl implements UserDao {
	public void login(String username, String password) {
		Connection conn = null; 
//		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			// 1.获取连接对象
			conn = JDBCUtil.getConn(); 
			/**
			 *	// 2.创建statement, 跟数据库打交道, 一定需要这个对象
			 *	st = conn.createStatement();
			 *	// 3.执行查询sql, 获取ResultSet结果集
			 *	rs = st.executeQuery("select * from user where username='"+ username  +"' and password='"+ password +"'");
			 **/
			// 2.PreparedStatement对象相比较以前的Statement对象, 预先处理给定的sql语句, 对其执行语法检查。 在sql语句里面使用?占位符来替代后续要传递进来的变量。
			// 后面不管传递什么进来, 都把它看成是字符串, 不会产生任何的关键字。
			ps = conn.prepareStatement("select * from user where username=?and password=?");
			// 给占位符赋值, 位置从1开始
			ps.setString(1, username);
			ps.setString(2, password);
			// 3.执行查询
			rs = ps.executeQuery();
			// 4.使用ResultSet结果集遍历
			if(rs.next()){
				System.out.println("登录成功");
			}else{
				System.out.println("登录失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5.释放资源
			JDBCUtil.release(conn, ps, rs);
		}
	}

	@Override
	public void register(String username, String password, long registertime) {
		Connection conn = null; 
		PreparedStatement ps = null;
		
		try {
			// 1.获取连接对象
			conn = JDBCUtil.getConn(); 
			// 2.PreparedStatement对象相比较以前的Statement对象, 预先处理给定的sql语句, 对其执行语法检查。 在sql语句里面使用?占位符来替代后续要传递进来的变量。
			// 后面不管传递什么进来, 都把它看成是字符串, 不会产生任何的关键字。
			ps = conn.prepareStatement("insert into user (username,password,registertime) values (?,?,?)");
			// 给占位符赋值, 位置从1开始
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setTimestamp(3, new Timestamp(registertime));
			// 3.执行更新
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.释放资源
			JDBCUtil.release(conn, ps);
		}
	}

	@Override
	public void modifyPassword(String username, String password) {
		Connection conn = null; 
		PreparedStatement ps = null;
		
		try {
			// 1.获取连接对象
			conn = JDBCUtil.getConn(); 
			// 2.PreparedStatement对象相比较以前的Statement对象, 预先处理给定的sql语句, 对其执行语法检查。 在sql语句里面使用?占位符来替代后续要传递进来的变量。
			// 后面不管传递什么进来, 都把它看成是字符串, 不会产生任何的关键字。
			ps = conn.prepareStatement("update user set password=? where username=?");
			// 给占位符赋值, 位置从1开始
			ps.setString(1, password);
			ps.setString(2, username);
			// 3.执行更新
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.释放资源
			JDBCUtil.release(conn, ps);
		}
	}

	@Override
	public void deleteUser(String username) {
		Connection conn = null; 
		PreparedStatement ps = null;
		
		try {
			// 1.获取连接对象
			conn = JDBCUtil.getConn(); 
			// 2.PreparedStatement对象相比较以前的Statement对象, 预先处理给定的sql语句, 对其执行语法检查。 在sql语句里面使用?占位符来替代后续要传递进来的变量。
			// 后面不管传递什么进来, 都把它看成是字符串, 不会产生任何的关键字。
			ps = conn.prepareStatement("delete from user where username=?");
			// 给占位符赋值, 位置从1开始
			ps.setString(1, username);
			// 3.执行更新
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.释放资源
			JDBCUtil.release(conn, ps);
		}
	}

	@Override
	public void findAll() {
		Connection conn = null; 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			// 1.获取连接对象
			conn = JDBCUtil.getConn(); 
			// 2.PreparedStatement对象相比较以前的Statement对象, 预先处理给定的sql语句, 对其执行语法检查。 在sql语句里面使用?占位符来替代后续要传递进来的变量。
			// 后面不管传递什么进来, 都把它看成是字符串, 不会产生任何的关键字。
			ps = conn.prepareStatement("select * from user");
			// 3.执行查询
			rs = ps.executeQuery();
			// 4.使用ResultSet结果集遍历
			while(rs.next()){
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getTimestamp(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5.释放资源
			JDBCUtil.release(conn, ps, rs);
		}
	}
}
