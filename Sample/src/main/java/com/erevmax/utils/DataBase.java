package com.erevmax.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	public final static String CONNECTION_URL = "jdbc:sqlserver://";
	public final static String SERVER_NAME = "176.74.173.38";
	public final static String PORT_NUMBER = "1433";
	public final static String DATABASE_USERNAME = "rtcm";
	public final static String DATABASE_PASSWORD = "rtcmY7R4Dh";
	public final static String RATETIGER_DATABASE_NAME = "RATETIGER";
	private static final String selectMethod = "cursor";
	public static Connection con=null;
	public static PreparedStatement pst=null;
	public static ResultSet rs=null;
	public static Statement st=null;
	

	static {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static String rateTigerConnectionUrl() {
		return CONNECTION_URL + SERVER_NAME + ":" + PORT_NUMBER
				+ ";databaseName=" + RATETIGER_DATABASE_NAME + ";selectMethod="
				+ selectMethod + ";";

	}

	
	public static Connection getRateTigerDataBaseConnection() {
		 con = null;
		try {
			con = java.sql.DriverManager.getConnection(
					rateTigerConnectionUrl(), DATABASE_USERNAME,
					DATABASE_PASSWORD);
			if (con != null)
				System.out.println("RateTiger database Connection Successful!");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("RateTiger database connection is not successful");
		} 
		return con;
	}

	public static ResultSet executeQuery(String query, Connection con) {
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void closeConnection(Connection con, PreparedStatement pst,
			ResultSet rs) {
		try {
			if (con != null) {
				con.close();
				System.out.println("Closing the connection after test");
			}
			if (pst != null) {
				pst.close();
				System.out.println("Closing the preparedStatement after test");
			}
			if (rs != null) {
				rs.close();
				System.out.println("Closing the resultset after test");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public static void closeConnection(Connection con, Statement st,ResultSet rs) {
		try {
			if (con != null) {
				con.close();
				System.out.println("Closing the connection after test");
			}
			if (rs != null) {
				rs.close();
				System.out.println("Closing the resultset after test");
			}if (st != null) {
				st.close();
				System.out.println("Closing the Statement after test");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static void closeConnection(Connection con, Statement st,ResultSet rs,PreparedStatement pst) {
		try {
			if (con != null) {
				con.close();
				System.out.println("Closing the connection after test");

			}if (pst != null) {
				pst.close();
				System.out.println("Closing the preparedStatement after test");
			}
			if (rs != null) {
				rs.close();
				System.out.println("Closing the resultset after test");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	

}
