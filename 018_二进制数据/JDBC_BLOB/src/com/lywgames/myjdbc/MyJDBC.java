package com.lywgames.myjdbc;

import com.lywgames.dao.BlobDao;
import com.lywgames.dao.impl.BlobDaoImpl;

public class MyJDBC {
	public static void main(String[] args) {
		BlobDao blobDao = new BlobDaoImpl();
		blobDao.createTable();
		blobDao.insert("Resources/1.jpg");
		blobDao.findAll();
		blobDao.deleteTable();
	}
}
