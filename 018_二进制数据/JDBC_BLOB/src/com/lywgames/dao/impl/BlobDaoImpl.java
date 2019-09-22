package com.lywgames.dao.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lywgames.dao.BlobDao;
import com.lywgames.util.JDBCUtil;

public class BlobDaoImpl implements BlobDao {
	@Override
	public void createTable() {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = JDBCUtil.getConn();
			ps = conn.prepareStatement("create table photos(id int(11) not null auto_increment, photo blob, primary key(id))");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps);
		}
	}

	@Override
	public void insert(String filePath) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = JDBCUtil.getConn();
			ps = conn.prepareStatement("insert into photos values(null, ?)");
			ps.setBlob(1, new FileInputStream(filePath));
			ps.executeUpdate();
		} catch (SQLException | IOException e) {
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
			ps = conn.prepareStatement("select photo from photos");
			rs = ps.executeQuery();
			int length = -1;
			byte[] buffer = new byte[1024 * 10];
			while(rs.next()) {
				Blob blob = rs.getBlob(1);
				InputStream is = blob.getBinaryStream();
				FileOutputStream fos = new FileOutputStream("Resources/" + System.currentTimeMillis() + ".png");
				while((length = is.read(buffer, 0, buffer.length)) != -1) {
					fos.write(buffer, 0, length);
				}
				fos.flush();
				fos.close();
				is.close();
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
			ps = conn.prepareStatement("drop table photos");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps);
		}
	}
}
