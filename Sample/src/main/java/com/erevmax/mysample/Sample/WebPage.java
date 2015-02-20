package com.erevmax.mysample.Sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.erevmax.utils.DataBase;
import static com.erevmax.utils.DataBase.closeConnection;
import static com.erevmax.utils.DataBase.executeQuery;
//import static com.erevmax.utils.DataBase.getRateTigerDataBaseConnection;
import static java.util.Locale.*;
import static java.text.DateFormat.*;




public  class WebPage extends SeleniumImplementation {	
	//Connection con= getRateTigerDataBaseConnection();
	
	static Date today = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");


	public String getStartDate(){ 	
		String startDate = formatter.format(today);
		return startDate;
	}

	public String getAssignmentDate(){
		DateFormat assignmentDate = DateFormat.getDateInstance(MEDIUM, UK);
		return ("Assignment Details for "+assignmentDate.format(today));
	}

	public String todayDateForDashboardDB(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
		//EEE MMM dd hh:mm:ss yyyy   results in    Sun Sep 06 08:32:51 2009
	}
	
	public String todayDate(){
		DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
		//EEE MMM dd hh:mm:ss yyyy   results in    Sun Sep 06 08:32:51 2009
	}
	 
    public  String yesterdayDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }
    
    public  String tomorrowDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +1);
        return dateFormat.format(cal.getTime());
    }
    
    public  String yearGap() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +365);
        return dateFormat.format(cal.getTime());
    }
    
    public  String monthGap() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +30);
        return dateFormat.format(cal.getTime());
    }
    
   /* public String getDateInTheFormOfRTAdmin()
    {
     Date date = new Date();
     DateFormat df = new SimpleDateFormat("dd MMM yyyy");
     return (df.format(date));
    }*/
    
    public  String checkDataBaseForString(String query,String field){
		Connection con= DataBase.getRateTigerDataBaseConnection();
		
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
		System.out.println(actual);
		return actual;
	}
    
    
    public   List<String> checkDataBaseForStringList(String query,String field){
		Connection con= DataBase.getRateTigerDataBaseConnection();
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
    
    public  int checkDataBaseForInteger(String query,String field){
		Connection con= DataBase.getRateTigerDataBaseConnection();
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
		System.out.println(actual);
		return actual;
	}
    


	

}
