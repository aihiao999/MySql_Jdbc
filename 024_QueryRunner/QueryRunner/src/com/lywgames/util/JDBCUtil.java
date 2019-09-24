package com.lywgames.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 使用数据库连接池的获取的数据库连接, 我们不用做关闭操作, 数据库连接池自行管理
 */
public class JDBCUtil {
	private static DataSource dataSource = null;

	static {
		dataSource = new ComboPooledDataSource();
	}
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * 获取连接对象
	 */
	public static Connection getConn(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 释放资源
	 * @param st
	 * @param rs
	 */
	public static void release(Statement st, ResultSet rs){
		closeRs(rs);
		closeSt(st);
	}

	public static void release(Statement st){
		closeSt(st);
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
	
}
