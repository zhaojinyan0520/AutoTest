package com.youceedu.inter.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;

import com.youceedu.inter.model.Autolog;

public class DbcpUtil {
	/**
	 * 初始化变量
	 */
	private static BasicDataSource dataSource = null;
	private static String sqlBatch = "";
	/**
	 * 创建静态代码块
	 */
	static{
		try {
			
			if (dataSource==null) {
				dataSource = new BasicDataSource();
				//设置连接池连接
				dataSource.setDriverClassName("com.mysql.jdbc.Driver");
				dataSource.setUrl("jdbc:mysql://192.168.228.128:3306/interface?xxx");
				dataSource.setUsername("root");
				dataSource.setPassword("123456");
				
				//设置连接池大小
				dataSource.setInitialSize(30);	
				dataSource.setMaxActive(30);
				dataSource.setMaxIdle(30);
				dataSource.setMinIdle(30);

				//设置是否检查
				dataSource.setTestOnBorrow(false);
				dataSource.setTestOnReturn(false);
				dataSource.setLoginTimeout(2000);
				
				dataSource.setPoolPreparedStatements(true);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	/**
	 * 建立connection连接
	 */
	public static synchronized Connection getconn() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return conn;
		
	}
	
	/**
	 * 通过jdbc进行增删改
	 */
	public static int jdbcUpdate(String sql,Autolog autoLog){
		int result = 0;
		try {
			
			Connection conn = getconn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, autoLog.getId());
			ps.setString(2, autoLog.getReqType());
			result = ps.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
		
	}
	
	/**
	 * 通过jdbc批量进行增删改
	 */
	
	public static int[] jdbcUpdateBatch(String sql,List<Autolog> list){
		int[] result = null;
		try {
			
			Connection conn = getconn();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			for (Autolog autolog : list) {
				ps.setString(1, autolog.getTestCase());
				ps.setString(2, autolog.getReqType());
				ps.setString(3, autolog.getReqUrl());
				ps.setString(4, autolog.getReqData());
				ps.setString(5, autolog.getExpResult());
				ps.setString(6, autolog.getActResult());
				ps.setInt(7, autolog.getResult());
				ps.setString(8, autolog.getExecTime());
				list.add(autolog);
			}
			result = ps.executeBatch();
			
		} catch (Exception e) {
			
		}
		
		return result;
		
	}
	
	/**
	 * 通过jdbc进行查询
	 */
	public static List<Object> jdbcQuery(Autolog autolog){
		List<Object> list=new ArrayList<Object>();
		try {
			Connection conn = getconn();
			PreparedStatement ps = conn.prepareStatement(sqlBatch);
			ps.setInt(1, autolog.getId());
			ps.setString(2, autolog.getReqType());
			ResultSet rs = ps.executeQuery(sqlBatch);
			list = handlerValue(rs,Autolog.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
		
	}
	
	public static List<Object> handlerValue(ResultSet rs,Class<?> cls){
		Object object= null;
		List<Object> list = new ArrayList<Object>();
		try {
			object = cls.newInstance();
			ResultSetMetaData rsmd = rs.getMetaData();
			int ColumnCount = rsmd.getColumnCount();
			
			for (int columnIndex = 0; columnIndex <= ColumnCount; columnIndex++) {
				String columnName = rsmd.getColumnName(columnIndex);
				Field field = Autolog.class.getDeclaredField(columnName);
				
				field.setAccessible(true);
				field.set(object, rs.getObject(columnName));
				
			}
			list.add(object);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
		
	}
	
}
