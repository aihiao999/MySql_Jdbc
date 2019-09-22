package com.lywgames.dao;

public interface UserDao {
	/**
	 * 用户登录方法
	 * @param username
	 * @param password
	 */
	public void login(String username, String password);
	/**
	 * 注册新用户
	 * @param username
	 * @param password
	 * @param registertime
	 */
	public void register(String username, String password, long registertime);
	/**
	 * 修改密码
	 * @param username
	 * @param password
	 */
	public void modifyPassword(String username, String password);
	/**
	 * 删除用户
	 * @param username
	 */
	public void deleteUser(String username);
	/**
	 * 查找所有用户
	 */
	public void findAll();
}
