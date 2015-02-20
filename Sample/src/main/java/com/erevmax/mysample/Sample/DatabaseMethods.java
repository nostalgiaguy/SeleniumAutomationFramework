package com.erevmax.mysample.Sample;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.erevmax.utils.DataBase.*;


public class DatabaseMethods {
		
	public static int checkDataBaseForInteger(String query,String field){
		Connection con= getRateTigerDataBaseConnection();
		ResultSet rs=executeQuery(query,con);
		int actual=0;
		try {
			while(rs.next()){			
				 actual=rs.getInt(field);				
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("sql exception occured.");
		}			
		closeConnection(con,null,rs,null);	
		return actual;
}
	
	public  static List<Integer> checkDataBaseForIntegerList(String query,String field){
		Connection con= getRateTigerDataBaseConnection();
		List<Integer> list=new ArrayList<Integer>();
		ResultSet rs=executeQuery(query,con);
		int actual=0;		
		try {
			while(rs.next()){
				 actual=rs.getInt(field);
				 list.add(actual);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception occured.");
		}
		closeConnection(con,null,rs,null);
		//System.out.println(list);
		return list;
	}
	public  static String checkDataBaseForString(String query,String field){
		Connection con= getRateTigerDataBaseConnection();
		//System.out.println(query);
		ResultSet rs=executeQuery(query,con);
		String actual=null;		
		try {
			while(rs.next()){
				 actual=rs.getString(field);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception occured.");
		}
		closeConnection(con,null,rs,null);
		//System.out.println(actual);
		return actual;
	}
	
	public  static List<String> checkDataBaseForStringList(String query,String field){
		Connection con= getRateTigerDataBaseConnection();
		List<String> list=new ArrayList<String>();
		ResultSet rs=executeQuery(query,con);
		String actual=null;		
		try {
			while(rs.next()){
				 actual=rs.getString(field).trim();	 
				 list.add(actual);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception occured.");
		}
		closeConnection(con,null,rs,null);
		//System.out.println(list);
		return list;
	}

}


/*
@Test
	public String checkDB(){
		Connection con= getRateTigerDataBaseConnection();
		String query="select * from rt_mr_settings where hotel_code='00343'";
		ResultSet rs=executeQuery(query,con);
		java.sql.ResultSetMetaData md=rs.getMetaData();			
		System.out.println(md.getColumnName(1)+"  "+md.getColumnName(2)+"  "+md.getColumnName(3)+"  "+md.getColumnName(4)+"  "+md.getColumnName(5)+"  "+md.getColumnName(6)+"  "+md.getColumnName(7));
		try {
			while(rs.next()){
		System.out.println(rs.getInt("Is_Daily_Active"));
				System.out.println(rs.getInt("Hotel_Code"));					
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("sql exception occured.");
		}			
		closeConnection(con,null,rs,null);			
}
*/