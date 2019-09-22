package com.lywgames.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lywgames.dao.UserDao;
import com.lywgames.util.JDBCUtil;

public class UserDaoImpl implements UserDao {
	public void login(String username, String password) {
		Connection conn = null; 
		Statement st = null;
		ResultSet rs = null;
		
		try {
			// 1.获取连接对象
			conn = JDBCUtil.getConn(); 
			// 2.创建statement, 跟数据库打交道, 一定需要这个对象
			st = conn.createStatement();
			// 3.执行查询sql, 获取ResultSet结果集
			String sql = "select * from user where username='"+ username  +"' and password='"+ password +"'";
			System.out.println(sql);
			rs = st.executeQuery(sql);
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
			JDBCUtil.release(conn, st, rs);
		}
	}
}
