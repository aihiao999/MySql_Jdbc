package com.lywgames.dao;

public interface BlobDao {
	public void createTable();
	
	public void insert(String filePath);
	
	public void findAll();
	
	public void deleteTable();
}
