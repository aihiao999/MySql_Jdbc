package com.lywgames.myjdbc;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * 数据库时间类型
 */
public class SqlTime {
	public static void main(String[] args) {
		Date date = new Date(System.currentTimeMillis()); // 表示年月日
		Time time = new Time(System.currentTimeMillis()); // 表示时分秒
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); // 表示年月日时分秒
		
		System.out.println(date);
		System.out.println(time);
		System.out.println(timestamp);
	}
}
