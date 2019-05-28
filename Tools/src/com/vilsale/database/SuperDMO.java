package com.vilsale.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SuperDMO {
	
	private ResultSet query_result = null;
	protected List<Object> params = null;
	
	private String SQLStrign = null;

	public void execute(String sqlString) throws Exception {
		System.out.println("-------------替换？前SQL:----------");
		System.out.println(sqlString);
		System.out.println("-------------替换？后SQL:----------");
		System.out.println(sqlString = insertString(sqlString, params));
		Statement st = TestDB.getStatement();
		query_result = st.executeQuery(sqlString);
	}
	
	/**
	 * 获得查询结果集
	 * 
	 * @return 查询结果集
	 */
	public ResultSet getQueryResult() {
		return query_result;
	}
	
	public void setParams(List<Object> params) {
		this.params = params;
	}
	
	/**
	 * 添加多个变量，用法：setParams(userid,storid)
	 * @param args
	 */
	public void setParams(Object... args){
		List<Object> list = new ArrayList<Object>();
		for(int i = 0; i< args.length; i++){
			list.add(args[i]);
		}
		setParams(list);
	}
	
	
	/**
	 * 关闭数据库连接
	 */
	public void close() {
		TestDB.close();
		try {
			if (query_result != null) {
				query_result.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static String insertString(String result_sql,List<Object> list) throws Exception {
		if (!result_sql.contains("?")) {
			return result_sql;
		}
		if (list == null) {
			throw new Exception("插入的值为空，并且SQL需要插入值");
		}
		StringBuffer stringBuffer = new StringBuffer(result_sql);
		int num = list.size();
		for(int i = stringBuffer.length(); i>0; i--) {
			int index = stringBuffer.lastIndexOf("?", i-1);
			if (index>=0) {
				if (num == 0) {
					throw new Exception("插入的值的数量不足");
				}
				stringBuffer.replace(index, index+1, list.get(--num).toString());
			}
		}
		if (num != 0) {
			throw new Exception("插入的值的数量比插入的位置多");
		}
		return stringBuffer.toString();
	}
}
