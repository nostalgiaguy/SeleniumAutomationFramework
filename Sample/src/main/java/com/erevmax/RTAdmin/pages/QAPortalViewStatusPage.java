package com.erevmax.RTAdmin.pages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.erevmax.mysample.Sample.WebPage;

import static com.erevmax.baseTest.TestBase.browser;
import static com.erevmax.utils.DataBase.closeConnection;
import static com.erevmax.utils.DataBase.executeQuery;
import static com.erevmax.utils.DataBase.getRateTigerDataBaseConnection;

public class QAPortalViewStatusPage extends WebPage {

	//private final Logger log= Logger.getLogger(this.getClass().getName());
	public String today_date=todayDateForDashboardDB();
	public By othersMenu;
	public By mrViewStatus;
	public By statusCount;
	public By AssignedTo;
	public By HotelName;
	public By select_AssignedTo;
	public By select_HotelName;
	public By message;
	public By refresh;
	public By statusByName;
	public By saveQANotes;
	public String hotel_code;
	public String query_totalStatusCount=" select (select COUNT(1) from dbo.RT_MR_SCHEDULE_RECURRENCE "
			+" where  Report_Base_Date"
			+" IN (SELECT Report_Base_Date FROM dbo.RT_MR_SCHEDULES "
			+" where Report_Base_Date='"+today_date+"')) as viewStatusCount";

	public String common_xpath="//*[@id='origTableDiv']/table/tbody/tr[1]/td/table/tbody/tr[";
	public String hotelName_xpath="]/td[4]";
	public String reportDays_xpath="]/td[5]";
	public String numOfAdult_xpath="]/td[6]";
	public String assignedTo_xpath="]/td[7]";
	public String clientSource_xpath="]/td[8]";
	public String reportRateType_xpath="]/td[9]";
	public String currency_xpath="]/td[11]";
	public String fetchStatus_xpath="]/td[14]";
	public String QANote_xpath="]/td[18]";
	
	public String query_Method_1=" SELECT  MS.MR_Schedule_ID, MS.Hotel_Code, HM.Hotel_Name, MS.Exec_Date_Time_GMT,"
			+" MS.Status, CS.Client_Noadults,MSR.Recurrence, MSR.Report_Days, CS.Client_Curr,"
			+" CS.Client_Source, CS.Report_Rate_Type, QA.Admin_Login,QA.QA_Notes"
			+" FROM dbo.RT_MR_SCHEDULES MS WITH(NOLOCK) left outer join RT_MR_QA_ASSIGN QA "
			+" on MS.Hotel_Code=QA.Hotel_Code inner join  RT_HOTEL_MAST HM "
			+" on MS.Hotel_Code = HM.Hotel_Code inner join  RT_CLIENT_SUBS CS "
			+" on MS.Hotel_Code = CS.Hotel_Code  INNER JOIN RT_MR_SCHEDULE_RECURRENCE MSR"
			+" on CS.Hotel_Code = MSR.Hotel_Code and MS.Report_Base_Date = MSR.Report_Base_Date "
			+" where MS.Report_Base_Date ='"+today_date+"'";
	
	public String query_Method_2="SELECT CONCAT(First_Name,' ',Last_Name) , Admin_Login from dbo.RT_ADMIN_MAST";

	public QAPortalViewStatusPage(){	
		this.AssignedTo=By.id("userName_search");
		this.select_AssignedTo=By.id("selectuser_search");
		this.select_HotelName=By.id("selecthotel_search");
		this.HotelName=By.id("hotelName_search");
		this.refresh=By.linkText("Refresh");
		this.message=By.id("message");
		this.saveQANotes=By.id("saveQANotes");
		this.othersMenu=By.id("ot");
		this.mrViewStatus=By.id("ot10");
		this.statusByName=By.xpath("//tr[starts-with(@id,'viewstatusTR_')]");
		this.statusCount=By.xpath("//a[starts-with(@id,'0') or starts-with(@id,'1')or starts-with(@id,'2')or starts-with(@id,'3')or starts-with(@id,'4')or starts-with(@id,'5')or starts-with(@id,'6')or starts-with(@id,'7')or starts-with(@id,'8')or starts-with(@id,'9')]");
	}

	public QAPortalViewStatusPage(String hotel_code){
		this();
		this.hotel_code=hotel_code;
	}

	public String getHotelQuery(){
		String query=" SELECT  MS.MR_Schedule_ID, MS.Hotel_Code, HM.Hotel_Name, MS.Exec_Date_Time_GMT,"
				+" MS.Status, CS.Client_Noadults,MSR.Recurrence, MSR.Report_Days, CS.Client_Curr, "
				+" CS.Client_Source, CS.Report_Rate_Type, QA.Admin_Login,QA.QA_Notes"
				+" FROM dbo.RT_MR_SCHEDULES MS WITH(NOLOCK) left outer join RT_MR_QA_ASSIGN QA "
				+" on MS.Hotel_Code=QA.Hotel_Code inner join  RT_HOTEL_MAST HM "
				+" on MS.Hotel_Code = HM.Hotel_Code inner join  RT_CLIENT_SUBS CS"
				+" on MS.Hotel_Code = CS.Hotel_Code  INNER JOIN RT_MR_SCHEDULE_RECURRENCE MSR"
				+" on CS.Hotel_Code = MSR.Hotel_Code and MS.Report_Base_Date = MSR.Report_Base_Date"
				+" where MS.Report_Base_Date ='"+today_date+"' AND MS.Hotel_Code='"+hotel_code+"'";
		return query;
	}
	public int statusCountByValue(){
		List<WebElement> list = browser.findWebElements(statusByName);
		/*
		 * java.lang.NullPointerException
	at com.google.common.base.Preconditions.checkNotNull(Preconditions.java:192)
		 */
		//List<WebElement> list = findWebElements(statusByName);
		int count=0;
		int j=0;
		while(j<list.size()){
			if(list.get(j).getAttribute("style").equals("")){
				count++;
			}
			j++;	
		}
		return count;
	}


	public List<String> getUIDataList(String lctr){
		List<String> UIData_List=new ArrayList<String>();
		int size=browser.getListSize(statusCount);
		for(int count=1;count<=size;count++){
			String data=browser.findWebElement(By.xpath(common_xpath+(count+2)+lctr)).getText().trim();
			UIData_List.add(data);
		}
		return UIData_List;	
	}


	public boolean compareUIandDBdata(List<String> ui,List<String> db){
		if(ui.size()==db.size()){
			for(int count=0;count<ui.size();count++){
				if(ui.get(count).equals(db.get(count))){	
				}
				else{
					System.out.println("UI and DB data is not matching");
					return false;
				}
			}
			return true;
		}
		else{
			System.out.println("UI view status count="+ui.size());
			System.out.println("DB view status count="+db.size());
			return false;
		}
	}


	public List<String> getHotel_NameList=new ArrayList<String>();
	public List<String> getReport_DaysList=new ArrayList<String>();
	public List<String> getClient_NoadultsList=new ArrayList<String>();
	public List<String> getClient_SourceList=new ArrayList<String>();
	public List<String> getReport_Rate_TypeList=new ArrayList<String>();
	public List<String> getClient_CurrList=new ArrayList<String>();
	public List<String> getStatusList=new ArrayList<String>();
	public List<String> getQA_NotesList=new ArrayList<String>();
	public List<String> getAdmin_LoginList=new ArrayList<String>();

	public  void DB_Method_1(String query){
		Connection con= getRateTigerDataBaseConnection();
		ResultSet rs=executeQuery(query,con);	
		try {
			while(rs.next()){

				String Hotel_Name=rs.getString("Hotel_Name").trim();		
				getHotel_NameList.add(Hotel_Name);	
				String Report_Days=rs.getString("Report_Days").trim();		
				getReport_DaysList.add(Report_Days);	
				String Client_Noadults=rs.getString("Client_Noadults").trim();		
				getClient_NoadultsList.add(Client_Noadults);		
				String Client_Source=rs.getString("Client_Source").trim();			
				getClient_SourceList.add(Client_Source);		
				String Report_Rate_Type=rs.getString("Report_Rate_Type").trim();	 	
				getReport_Rate_TypeList.add(Report_Rate_Type);	
				String Client_Curr=rs.getString("Client_Curr").trim();		
				getClient_CurrList.add(Client_Curr);		 
				String Status=rs.getString("Status").trim();		
				getStatusList.add(Status);		
				String QA_Notes=rs.getString("QA_Notes").trim();	 		
				getQA_NotesList.add(QA_Notes);
				String Admin_Login=rs.getString("Admin_Login").trim();	 		
				getAdmin_LoginList.add(Admin_Login);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception occured.");
		}
		closeConnection(con,null,rs,null);
	}
	
	public List<String> getFullNameList =  new ArrayList<String>();
	public List<String> getAdminLoginList =  new ArrayList<String>();	
	
	public  void DB_Method_2(String query){
		Connection con= getRateTigerDataBaseConnection();
		ResultSet rs=executeQuery(query,con);
		try {
			while(rs.next()){
				 String full_Name=rs.getString(1);
				 getFullNameList.add(full_Name);
				 String adminLogin_Name=rs.getString(2);
				 getAdminLoginList.add(adminLogin_Name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception occured.");
		}
		closeConnection(con,null,rs,null);
		//System.out.println(actual);
	}

}
/*
 * **************************************************************************************************************

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


public List<String> hotelNameList(){
	List<String> hotel_List=new ArrayList<String>();
	int size=totalStatusCount();
	for(int count=1;count<=size;count++){
		String hotelName=browser.findWebElement(By.xpath(common_xpath+(count+2)+hotelName_xpath)).getText().trim();
		hotel_List.add(hotelName);
	}
	return hotel_List;
}

public List<String> fetchStatusList(){
	List<String> fetchStatus_List=new ArrayList<String>();
	int size=totalStatusCount();
	for(int count=1;count<=size;count++){
		String status=browser.findWebElement(By.xpath(common_xpath+(count+2)+fetchStatus_xpath)).getText().trim();
		fetchStatus_List.add(status.trim());
	}
	return fetchStatus_List;
}

public List<String> reportDaysList(){
	List<String> reportDays_List=new ArrayList<String>();
	int size=totalStatusCount();
	for(int count=1;count<=size;count++){
		String reportDays=browser.findWebElement(By.xpath(common_xpath+(count+2)+reportDays_xpath)).getText().trim();
		reportDays_List.add(reportDays);
	}
	return reportDays_List;	
}

public List<String> QANoteList(){
	List<String> QANote_List=new ArrayList<String>();
	int size=totalStatusCount();
	for(int count=1;count<=size;count++){
		String QANote=browser.findWebElement(By.xpath(common_xpath+(count+2)+QANote_xpath)).getText().trim();
		QANote_List.add(QANote);
	}
	return QANote_List;	
}

public List<String> numOfAdultList(){
	List<String> numOfAdult_List=new ArrayList<String>();
	int size=totalStatusCount();
	for(int count=1;count<=size;count++){
		String numOfAdult=browser.findWebElement(By.xpath(common_xpath+(count+2)+numOfAdult_xpath)).getText().trim();
		numOfAdult_List.add(numOfAdult);
	}
	return numOfAdult_List;	
}

public List<String> clientSourceList(){
	List<String> clientSource_List=new ArrayList<String>();
	int size=totalStatusCount();
	for(int count=1;count<=size;count++){
		String clientSource=browser.findWebElement(By.xpath(common_xpath+(count+2)+clientSource_xpath)).getText().trim();
		clientSource_List.add(clientSource);
	}
	return clientSource_List;	
}

public List<String> reportRateTypeList(){
	List<String> reportRateType_List=new ArrayList<String>();
	int size=totalStatusCount();
	for(int count=1;count<=size;count++){
		String reportRateType=browser.findWebElement(By.xpath(common_xpath+(count+2)+reportRateType_xpath)).getText().trim();
		reportRateType_List.add(reportRateType);
	}
	return reportRateType_List;	
}

public List<String> clientCurrencyList(){
	List<String> currency_List=new ArrayList<String>();
	int size=totalStatusCount();
	for(int count=1;count<=size;count++){
		String currency=browser.findWebElement(By.xpath(common_xpath+(count+2)+currency_xpath)).getText().trim();
		currency_List.add(currency);
	}
	return currency_List;	
}

 */

