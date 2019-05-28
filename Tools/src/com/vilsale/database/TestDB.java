package com.vilsale.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDB{
	// JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://192.168.0.57:3306/villagesale?rewriteBatchedStatements=true";
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "***";
    static final String PASS = "***";
    
    private static Connection con = null;
    private static Statement st = null;
    private static PreparedStatement pst = null;
    
	/**
	 * 创建数据库连接
	 * @return
	 */
	public static Statement getStatement() {
		 // 注册 JDBC 驱动
        try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL,USER,PASS);
		    st = con.createStatement();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return st;
	}
	
	/**
	 * 创建数据库连接
	 * @return
	 */
	public static PreparedStatement getPrepareStatement(String sql) {
		 // 注册 JDBC 驱动
        try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL,USER,PASS);
			pst = con.prepareStatement(sql);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return pst;
	}
	
	/**
	 * 关闭数据库连接
	 */
	public static void close() {
		// 关闭资源
        try{
            if(st!=null) st.close();
            if(pst!=null) pst.close();
        }catch(SQLException se2){
        }// 什么都不做
        try{
            if(con!=null) con.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
	}
}
