package com.erevmax.RTAdmin.pages;

import static com.erevmax.utils.DataBase.closeConnection;
import static com.erevmax.utils.DataBase.executeQuery;
import static com.erevmax.utils.DataBase.getRateTigerDataBaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.erevmax.mysample.Sample.WebPage;

public class ReportRecurrenceQueryDetails extends WebPage {
	
	public String hotel_code;
	//public String RT_MR_SCHEDULE_query="SELECT * FROM dbo.RT_MR_SCHEDULES where Report_Base_Date='"+todayDate()+"' AND hotel_code ='"+hotel_code+"'";
	
	
	public ReportRecurrenceQueryDetails() {}

	public ReportRecurrenceQueryDetails(String hotel_code){
		this();
		this.hotel_code=hotel_code;
	}
	
	public String get_RT_MR_SCHEDULE_query(){
		String query="SELECT * FROM dbo.RT_MR_SCHEDULES where Report_Base_Date='"+todayDate()+"' AND hotel_code ='"+hotel_code+"'";
		return query;
	}
	
	public String get_RT_MR_SCHEDULE_RECURRENCE_query(){
		String query="SELECT * FROM dbo.RT_MR_SCHEDULE_RECURRENCE where Report_Base_Date='"+todayDate()+"' AND hotel_code ='"+hotel_code+"'";
		return query;
	}
	
	public String get_RT_MR_SHOPPING_COMBINATION_query(){
		String query="SELECT * FROM dbo.RT_MR_SHOPPING_COMBINATION where Report_Base_Date='"+todayDate()+"' AND hotel_code ='"+hotel_code+"'";
		return query;
	}
	
	public String get_RT_MR_SHOPPING_COMBINATION_SLICE_query(){
		String query="SELECT * FROM dbo.RT_MR_SHOPPING_COMBINATION_SLICE where Report_Base_Date='2014-12-16'  AND hotel_code ='00045' AND "
				      +"combination_id IN(SELECT combination_id FROM dbo.RT_MR_SHOPPING_COMBINATION where Report_Base_Date='2014-12-16'  AND hotel_code ='00045' AND schedule_id IN ('14107'))";
		return query;
	}

	

	public List<String> get_MR_SCHEDULE_ID=new ArrayList<String>();
	public List<String> get_HOTEL_CODE=new ArrayList<String>();
	public List<String> get_REPORT_BASE_DATE=new ArrayList<String>();
	public List<String> get_REPORT_DAYS=new ArrayList<String>();
	public List<String> get_STATUS=new ArrayList<String>();
	public List<String> get_AUTO_CE_NF_FETCH_STATUS=new ArrayList<String>();
	public List<String> get_TRAVEL_SITE_DCODE=new ArrayList<String>();
	public List<String> get_SHOP_QUEUE=new ArrayList<String>();
	
	public  void get_RT_MR_SCHEDULE_tableDetail(String query){
		Connection con= getRateTigerDataBaseConnection();
		ResultSet rs=executeQuery(query,con);	
		try {
			while(rs.next()){

				String MR_SCHEDULE_ID=rs.getString("MR_SCHEDULE_ID").trim();		
				get_MR_SCHEDULE_ID.add(MR_SCHEDULE_ID);	
				String HOTEL_CODE=rs.getString("HOTEL_CODE").trim();		
				get_HOTEL_CODE.add(HOTEL_CODE);	
				String REPORT_BASE_DATE=rs.getString("REPORT_BASE_DATE").trim();		
				get_REPORT_BASE_DATE.add(REPORT_BASE_DATE);		
				String REPORT_DAYS=rs.getString("REPORT_DAYS").trim();			
				get_REPORT_DAYS.add(REPORT_DAYS);		
				String STATUS=rs.getString("STATUS").trim();	 	
				get_STATUS.add(STATUS);	
				String AUTO_CE_NF_FETCH_STATUS=rs.getString("AUTO_CE_NF_FETCH_STATUS").trim();		
				get_AUTO_CE_NF_FETCH_STATUS.add(AUTO_CE_NF_FETCH_STATUS);		 	
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception occured.");
		}
		closeConnection(con,null,rs,null);
	}
	
	public  void get_RT_MR_SCHEDULE_RECURRENCE_tableDetail(String query){
		Connection con= getRateTigerDataBaseConnection();
		ResultSet rs=executeQuery(query,con);	
		try {
			while(rs.next()){	
				String HOTEL_CODE=rs.getString("HOTEL_CODE").trim();		
				get_HOTEL_CODE.add(HOTEL_CODE);	
				String REPORT_BASE_DATE=rs.getString("REPORT_BASE_DATE").trim();		
				get_REPORT_BASE_DATE.add(REPORT_BASE_DATE);		
				String REPORT_DAYS=rs.getString("REPORT_DAYS").trim();			
				get_REPORT_DAYS.add(REPORT_DAYS);		
				String STATUS=rs.getString("STATUS").trim();	 	
				get_STATUS.add(STATUS);			 	
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception occured.");
		}
		closeConnection(con,null,rs,null);
	}
	
	public  void get_RT_MR_SHOPPING_COMBINATION_tableDetail(String query){
		Connection con= getRateTigerDataBaseConnection();
		ResultSet rs=executeQuery(query,con);	
		try {
			while(rs.next()){	
				String SCHEDULE_ID=rs.getString("SCHEDULE_ID").trim();		
				get_MR_SCHEDULE_ID.add(SCHEDULE_ID);
				String HOTEL_CODE=rs.getString("HOTEL_CODE").trim();		
				get_HOTEL_CODE.add(HOTEL_CODE);	
				String REPORT_BASE_DATE=rs.getString("REPORT_BASE_DATE").trim();		
				get_REPORT_BASE_DATE.add(REPORT_BASE_DATE);		
				String REPORT_DAYS=rs.getString("REPORT_DAYS").trim();			
				get_REPORT_DAYS.add(REPORT_DAYS);		
				String STATUS=rs.getString("STATUS").trim();	 	
				get_STATUS.add(STATUS);
				String TRAVEL_SITE_DCODE=rs.getString("TRAVEL_SITE_DCODE").trim();	 	
				get_TRAVEL_SITE_DCODE.add(TRAVEL_SITE_DCODE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception occured.");
		}
		closeConnection(con,null,rs,null);
	}
	
	public void get_RT_MR_SHOPPING_COMBINATION_SLICE_tableDetail(String query){
		Connection con= getRateTigerDataBaseConnection();
		ResultSet rs=executeQuery(query,con);	
		try {
			while(rs.next()){	
				String HOTEL_CODE=rs.getString("HOTEL_CODE").trim();		
				get_HOTEL_CODE.add(HOTEL_CODE);	
				String REPORT_BASE_DATE=rs.getString("REPORT_BASE_DATE").trim();		
				get_REPORT_BASE_DATE.add(REPORT_BASE_DATE);			
				String STATUS=rs.getString("STATUS").trim();	 	
				get_STATUS.add(STATUS);
				String SHOP_QUEUE=rs.getString("SHOP_QUEUE").trim();	 	
				get_SHOP_QUEUE.add(SHOP_QUEUE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception occured.");
		}
		closeConnection(con,null,rs,null);
	}
	
}
