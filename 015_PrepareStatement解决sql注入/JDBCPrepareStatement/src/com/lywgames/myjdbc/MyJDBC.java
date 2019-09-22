package com.lywgames.myjdbc;

import com.lywgames.dao.UserDao;
import com.lywgames.dao.impl.UserDaoImpl;

public class MyJDBC {
	
	public static void main(String[] args) {
		UserDao userDao = new UserDaoImpl();
		userDao.findAll();
	}
	
}
