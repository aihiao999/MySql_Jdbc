package com.lywgames.myjdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.lywgames.dao.TextDao;
import com.lywgames.dao.impl.TextDaoImpl;

public class MyJDBC {
	public static void main(String[] args) throws FileNotFoundException {
		TextDao textDao = new TextDaoImpl();
		textDao.createTable();
		textDao.insert(new FileReader(new File("Resources/cuihua.txt")));
		textDao.findAll();
		textDao.deleteTable();
	}
}
