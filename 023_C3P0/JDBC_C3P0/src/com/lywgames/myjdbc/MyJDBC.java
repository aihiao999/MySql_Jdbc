package com.lywgames.myjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MyJDBC {
	public static void main(String[] args) {
		c3p0UseConfig();
	}
	
	public static void c3p0() {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			//1. 创建datasource
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			//2. 设置连接数据的信息
			dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql://192.168.25.130:3306/studyjdbc?useSSL=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
			dataSource.setUser("root");
			dataSource.setPassword("lyw123456");
			//3. 得到连接对象
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("insert into user values(null,?,?,?)");
			ps.setString(1, "machao");
			ps.setString(2, "123456");
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				ps = null;
			}
		}
	}
	
	public static void c3p0UseConfig() {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			//1. 创建datasource通过类加载器查找c3p0-config.xml
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			//2. 得到连接对象
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("insert into user values(null,?,?,?)");
			ps.setString(1, "pangjuan");
			ps.setString(2, "123456");
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				ps = null;
			}
		}
	}
}
