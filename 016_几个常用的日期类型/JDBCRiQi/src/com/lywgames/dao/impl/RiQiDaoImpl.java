package com.lywgames.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;

import com.lywgames.dao.RiQiDao;
import com.lywgames.util.JDBCUtil;

public class RiQiDaoImpl implements RiQiDao {
	@Override
	public void createTable() {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = JDBCUtil.getConn();
			
			String sql = "create table riqi(id int(11) not null auto_increment, regDate date default null, regTime time default null, reg timestamp default null, primary key(id))";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, ps);
		}
	}

	@Override
	public void insert(Date date, Time time, Timestamp ts) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = JDBCUtil.getConn();
			
			String sql = "insert into riqi values (null, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setDate(1, date);
			ps.setTime(2, time);
			ps.setTimestamp(3, ts);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, ps);
		}
	}

	@Override
	public void findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConn();
			
			String sql = "select * from riqi";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getDate(2) + " " + rs.getTime(3) + " " + rs.getTimestamp(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, ps);
		}
	}

	@Override
	public void dropTable() {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = JDBCUtil.getConn();
			
			String sql = "drop table riqi";
			ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, ps);
		}
	}
}
