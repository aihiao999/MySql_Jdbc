package com.lywgames.myjdbc;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import com.lywgames.dao.RiQiDao;
import com.lywgames.dao.impl.RiQiDaoImpl;

public class MyJDBC {
	public static void main(String[] args) throws SQLException {
		RiQiDao userDao = new RiQiDaoImpl();
		userDao.createTable();
		userDao.insert(new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
		userDao.findAll();
		userDao.dropTable();
	}
}
