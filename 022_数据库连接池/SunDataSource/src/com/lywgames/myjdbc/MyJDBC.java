package com.lywgames.myjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.lywgames.util.JDBCUtil;

public class MyJDBC {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		// 创建自己的连接池对象
		MyDataSource ds = new MyDataSource();
		
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement("insert into user values(null,?,?,?)");
			ps.setString(1, "lvbu");
			ps.setString(2, "123456");
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(ps);
		}
		
		// 归还连接
		ds.backConnection(conn);
	}
}
