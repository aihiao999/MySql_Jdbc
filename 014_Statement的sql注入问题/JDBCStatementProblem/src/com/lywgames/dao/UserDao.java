package com.lywgames.dao;

public interface UserDao {
	/**
	 * 用户登录方法
	 * @param username
	 * @param password
	 */
	public void login(String username, String password);
}
