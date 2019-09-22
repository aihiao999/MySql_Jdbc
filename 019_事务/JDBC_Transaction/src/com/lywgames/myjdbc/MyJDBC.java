package com.lywgames.myjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lywgames.util.JDBCUtil;

public class MyJDBC {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConn();
			// 1.关闭事务自动提交
			conn.setAutoCommit(false);
			
			// 2.扣除用户1的钱
			ps = conn.prepareStatement("update account set money = money - ? where id = ?");
			ps.setInt(1, 100);
			ps.setInt(2, 1);
			ps.executeUpdate();
			
			// 3.抛一个运行时异常
			new RuntimeException();
			
			JDBCUtil.closeSt(ps);
			// 4.给用户2加钱
			ps = conn.prepareStatement("update account set money = money + ? where id = ?");
			ps.setInt(1, 100);
			ps.setInt(2, 2);
			ps.executeUpdate();
			
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
