package com.lywgames.myjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.lywgames.util.JDBCUtil;

public class MyJDBC {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConn();
			// 1.设置非自动提交
			conn.setAutoCommit(false);
			// 2.批量插入1000条数据
			ps = conn.prepareStatement("insert into user values(null,?,?,?)");
			for(int i = 1; i < 1000; i++) {
				ps.setString(1, "myname_" + i);
				ps.setString(2, "123456");
				ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
				ps.addBatch();
			}
			// 3.删除数据
			ps.addBatch("delete from user where id=990");
			// 4.执行批量操作
			ps.executeBatch();
			
			// 5.提交事务
			conn.commit();
		} catch (SQLException e) {
			try {
				// 6.回滚事务
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps, rs);
		}
	
	}
}
