package com.lywgames.myjdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.lywgames.util.JDBCUtil;

/**
 * 实现java的DataSource接口
 */
public class MyDataSource implements DataSource {
	private List<Connection> list = new ArrayList<Connection>();
	
	/**
	 * 对象一创建就创建10个数据库连接对象, 放到list对象
	 */
	public MyDataSource() {
		for (int i = 0; i < 10; i++) {
			Connection conn = JDBCUtil.getConn();
			list.add(conn);
		}
	}
	
	// 该连接池对外公布的获取连接的方法
	@Override
	public Connection getConnection() throws SQLException {
		// 发现连接池的连接数量为0, 自动扩容
		if(list.size() == 0) {
			for (int i = 0; i < 10; i++) {
				Connection conn = JDBCUtil.getConn();
				list.add(conn);
			}
		}
		
		return list.remove(0);
	}
	
	/**
	 * 归还数据库连接对象
	 * @param conn
	 */
	public void backConnection(Connection conn) {
		System.out.println("归还连接前, 连接数:" + list.size());
		list.add(conn);
		System.out.println("归还连接后, 连接数:" + list.size());
	}
	
	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return null;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException {}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		return null;
	}

}
