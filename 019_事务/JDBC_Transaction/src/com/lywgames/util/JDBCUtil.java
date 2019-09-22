package com.lywgames.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	private static String driverClass = null;
	private static String url = null;
	private static String name = null;
	private static String password= null;
	
	static {
		try {
			// 1.创建一个属性配置对象
			Properties properties = new Properties();
			// 2.使用类加载器, 去读取src底下的资源文件。对应文件位于src目录底下
			InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			// 3.导入输入流。
			properties.load(is);
			// 4.读取属性
			driverClass = properties.getProperty("driverClass");
			url = properties.getProperty("url");
			name = properties.getProperty("name");
			password = properties.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取连接对象
	 */
	public static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, name, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 释放资源
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void release(Connection conn, Statement st, ResultSet rs){
		closeRs(rs);
		closeSt(st);
		closeConn(conn);
	}

	public static void release(Connection conn, Statement st){
		closeSt(st);
		closeConn(conn);
	}
	
	public static void closeRs(ResultSet rs){
		try {
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			rs = null;
		}
	}
	
	public static void closeSt(Statement st){
		try {
			if(st != null){
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			st = null;
		}
	}
	
	public static void closeConn(Connection conn){
		try {
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn = null;
		}
	}
	
}
