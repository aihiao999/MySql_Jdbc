package com.lywgames.myjdbc;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.lywgames.bean.User;
import com.lywgames.util.JDBCUtil;

/**
 * QueryRunner的update方法, 可以进行插入、删除、更新操作
 * QueryRunner的query方法, 可以进行查询操作
 */
public class MyJDBC {
	public static void main(String[] args) {
		try {
			QueryRunner queryRunner = new QueryRunner();
			User user = queryRunner.query(JDBCUtil.getConn(), "select * from user where id = ?", new BeanHandler<User>(User.class), 1002);
			System.out.println(user.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void selectList() {
		try {
			QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
			List<User> list = queryRunner.query("select * from user", new BeanListHandler<User>(User.class));
			for (User user : list) {
				System.out.println(user.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void insert() {
		try {
			// 传入数据库连接池对象, QueryRunner不会创建连接
			QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
			queryRunner.update("insert into user values (null, ?, ?, ?)", "guanyu", "123456", new Timestamp(System.currentTimeMillis()));
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void update() {
		try {
			QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
			queryRunner.update("update user set username = ? where id = ?", "zhangfei", 1000);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void delete() {
		try {
			QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
			queryRunner.update("delete from user where id = ?", 1001);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	
	
}
