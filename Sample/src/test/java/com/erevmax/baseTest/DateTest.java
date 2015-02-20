package com.erevmax.baseTest;
import static java.text.DateFormat.*;
import static java.util.Locale.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateTest {

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
}

