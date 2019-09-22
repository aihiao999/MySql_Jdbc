package com.lywgames.dao;

import java.io.Reader;

public interface TextDao {

	public void createTable();
	
	public void insert(Reader reader);
	
	public void findAll();
	
	public void deleteTable();
}
