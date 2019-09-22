package com.lywgames.dao.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lywgames.dao.TextDao;
import com.lywgames.util.JDBCUtil;

public class TextDaoImpl implements TextDao {
	@Override
	public void createTable() {
		Connection conn = null; 
		PreparedStatement ps = null;
		
		try {
			conn = JDBCUtil.getConn(); 
			ps = conn.prepareStatement("create table dawenbwen(id int(11) not null auto_increment, introduce text, primary key(id))");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps);
		}
	}

	@Override
	public void insert(Reader reader) {
		Connection conn = null; 
		PreparedStatement ps = null;
		
		try {
			conn = JDBCUtil.getConn(); 
			ps = conn.prepareStatement("insert into dawenbwen values(null, ?)");
			ps.setClob(1, reader);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
			ps = conn.prepareStatement("select * from dawenbwen");
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt(1);
				
				String tempText = null;
				StringBuffer sb = new StringBuffer();
				Clob clob = rs.getClob(2);
				Reader reader = clob.getCharacterStream();
				BufferedReader br = new BufferedReader(reader);
				while((tempText = br.readLine()) != null) {
					sb.append(tempText);
				}
				
				System.out.println(id + " " + sb.toString());
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps, rs);
		}
	}

	@Override
	public void deleteTable() {
		Connection conn = null; 
		PreparedStatement ps = null;
		
		try {
			conn = JDBCUtil.getConn(); 
			ps = conn.prepareStatement("drop table dawenbwen");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps);
		}
	}
}
