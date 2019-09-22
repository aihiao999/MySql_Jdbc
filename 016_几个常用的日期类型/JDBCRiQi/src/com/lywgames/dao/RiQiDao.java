package com.lywgames.dao;

import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;

public interface RiQiDao {
	/**
	 * 创建表
	 */
	public void createTable();
	/**
	 * 插入数据
	 * @param date
	 * @param time
	 * @param ts
	 */
	public void insert(Date date, Time time, Timestamp ts);
	/**
	 * 查找所有数据
	 */
	public void findAll();
	/**
	 * 删除表
	 */
	public void dropTable();
}
