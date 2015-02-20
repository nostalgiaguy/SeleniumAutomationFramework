package com.erevmax.qaportal.viewstatus.tests;

import java.util.*;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.erevmax.mysample.Sample.DatabaseMethods.*;

import com.erevmax.RTAdmin.pages.QAPortalViewStatusPage_old;
import com.erevmax.baseTest.TestBase;
import com.erevmax.utils.Verify;

public class QAPortalViewStatusTests_old extends TestBase{
	
	QAPortalViewStatusPage_old st=new QAPortalViewStatusPage_old("23557");
	
	@Test(description="navigating to Managed Reports QA portal view status page")
	public void viewStatusNavigationTest(){
		System.out.println("Test 1:");
		browser.click(st.othersMenu);
		System.out.println("Current url is :" +browser.getCurrentUrl());
		browser.click(st.mrViewStatus);
		browser.click(st.mrViewStatus);
		System.out.println("Next Current url is :" +browser.getCurrentUrl());
		int status =browser.sendHttpGetRequest(browser.getCurrentUrl());
		Verify.assertEquals(status,200);
		System.out.println("Test 1 completed.");
	}
	
	@Test(description="validating the total report count with database for today date",
			dependsOnMethods="viewStatusNavigationTest")

	public void totalViewStatusCountTest(){
		System.out.println("Test 2:");
		int actual_viewStatusCount=st.totalStatusCount();
		int DB_viewStatusCount=checkDataBaseForInteger(st.query_totalStatusCount,"viewStatusCount");
		System.out.println("actual_viewStatusCount: "+actual_viewStatusCount);
		System.out.println("DB_viewStatusCount: "+DB_viewStatusCount);
		Verify.assertEquals(actual_viewStatusCount, DB_viewStatusCount);
		System.out.println("Test 2 completed.");
	}

	
	@Test(description="validating the data with database for today date by adding and comparing in list",
			dependsOnMethods="viewStatusNavigationTest",
			dataProvider="dataField")
	public void hotelNameTests(String lctr,String colmnName){
		System.out.println("Test 3:");
		List<String> actual_UIList= st.getUIDataList(lctr);
		List<String> DB_List=checkDataBaseForStringList(st.query, colmnName);
		System.out.println(actual_UIList);
		System.out.println(DB_List);
		boolean actual=st.compareUIandDBdata(actual_UIList, DB_List);
		Verify.assertEquals(actual, true);
		System.out.println("Test 3 completed");
	}
	@DataProvider	
	public Object[][] dataField(){
		Object [][] data= new Object[6][2];
		data[0][0]=st.hotelName_xpath;
		data[0][1]="Hotel_Name";
		
		data[1][0]=st.reportDays_xpath;
		data[1][1]="Report_Days";
		
		data[2][0]=st.numOfAdult_xpath;
		data[2][1]="Client_Noadults";
		
		data[3][0]=st.clientSource_xpath;
		data[3][1]="Client_Source";
		
		data[4][0]=st.reportRateType_xpath;
		data[4][1]="Report_Rate_Type";
		
		data[5][0]=st.currency_xpath;
		data[5][1]="Client_Curr";
		
		return data;
		}

	@Test(description="validating the fetch status with database  by adding and comparing in list",
			dependsOnMethods="viewStatusNavigationTest")
	public void fetchStatusTests(){
		System.out.println("Test 4:");
		List<String> db_temp =  new ArrayList<String>();
		List<String> actual_fetchStatusList= st.getUIDataList(st.fetchStatus_xpath);
		List<String> DB_fetchStatusList=checkDataBaseForStringList(st.query, "Status");	
		System.out.println(actual_fetchStatusList);
		System.out.println(DB_fetchStatusList);
		for(int count=0;count<DB_fetchStatusList.size();count++){
			String status=DB_fetchStatusList.get(count).trim();
			if(status.equals("READY")){
				status="Not Started";
			}
			if(status.equals("PENDING")){
				status="Not Started";
			}
			if(status.equals("COMPLETED")){
				status="Completed";
			}
			db_temp.add(status.trim());
		}
		System.out.println(db_temp);
		boolean actual=st.compareUIandDBdata(actual_fetchStatusList, db_temp);
		Verify.assertEquals(actual, true);
		System.out.println("Test 4 completed");
	}
	
	@Test(description="validating the QA Note with database  by adding and comparing in list",
			dependsOnMethods="viewStatusNavigationTest")
	public void qANoteTests(){
		System.out.println("Test 5:");
		List<String> ui_temp = new ArrayList<String>();
		List<String> db_temp =  new ArrayList<String>();
		List<String> actual_QANoteList= st.getUIDataList(st.QANote_xpath);
		System.out.println(actual_QANoteList);
		for(int count=0;count<actual_QANoteList.size();count++){
			String temp=actual_QANoteList.get(count).replaceAll(" ", "");
			ui_temp.add(temp);
		}
		List<String> DB_QANoteList=checkDataBaseForStringList(st.query, "QA_Notes");
		System.out.println(DB_QANoteList);
		for(int count=0;count<DB_QANoteList.size();count++){
			String temp=DB_QANoteList.get(count).replaceAll(" ", "");
			db_temp.add(temp);
		}
		System.out.println(ui_temp);
		System.out.println(db_temp);
		boolean actual=st.compareUIandDBdata(ui_temp, db_temp);
		Verify.assertEquals(actual, true);
		System.out.println("Test 5 completed");
	}
	
	
	@Test(description="validating the assigned to names with database  by adding and comparing in list",
			dependsOnMethods="viewStatusNavigationTest")
	public void assignedToTest(){
		System.out.println("Test 6:");
		List<String> db_temp =  new ArrayList<String>();
		List<String> actual_assignedToList= st.getUIDataList(st.assignedTo_xpath);
		List<String> DB_assignedToList=checkDataBaseForStringList(st.query, "Admin_Login");	
		System.out.println(actual_assignedToList);
		System.out.println(DB_assignedToList);
		for(int count=0;count<DB_assignedToList.size();count++){
			String temp=checkDataBaseForString("select(SELECT CONCAT(First_Name,' ',Last_Name) from dbo.RT_ADMIN_MAST where Admin_Login='"+DB_assignedToList.get(count)+"') as QA_Name ","QA_Name");
			db_temp.add(temp);
		}
		System.out.println(actual_assignedToList);
		System.out.println(db_temp);
		boolean actual=st.compareUIandDBdata(actual_assignedToList, db_temp);
		Verify.assertEquals(actual, true);
		System.out.println("Test 6 completed");
	}
	
	@Test(description="validating the recurrence of hotel with database  by adding and comparing in list",
			dependsOnMethods="viewStatusNavigationTest")
	public void recurrenceTest(){
		System.out.println("Test 7:");
		int recurrence=0;
		List<String> actual_RecurrenceList= st.getUIDataList(st.hotelName_xpath);
		List<String> DB_RecurrenceList=checkDataBaseForStringList(st.getHotelQuery(), "Recurrence");
		String hotel_name=checkDataBaseForString(st.getHotelQuery(),"Hotel_Name");
		System.out.println("Recurrence test for hotel name:="+hotel_name+" with hotel code:="+st.hotel_code);
		for(int count=0;count<actual_RecurrenceList.size();count++){
			if(actual_RecurrenceList.get(count).equals(hotel_name)){
			    recurrence++;
			}	
		}
		System.out.println(DB_RecurrenceList);
		Verify.assertEquals(DB_RecurrenceList.size(), recurrence);
		System.out.println("Test 7 completed");
	}

}

/*
 * *********************************************************************************************************
@Test(description="validating the Report days with database for today date by adding and comparing in list",
		dependsOnMethods="viewStatusNavigationTest")
public void reportDaysTests(){
	System.out.println("Test 4:");
	List<String> actual_reportDaysList= st.getUIDataList(st.reportDays_xpath);
	List<String> DB_reportDaysList=checkDataBaseForStringList(st.query, "Report_Days");	
	System.out.println(actual_reportDaysList);
	System.out.println(DB_reportDaysList);
	boolean actual=st.compareUIandDBdata(actual_reportDaysList, DB_reportDaysList);
	Verify.assertEquals(actual, true);
	System.out.println("Test 4 completed");
}

@Test(description="validating the number of adult with database for today date by adding and comparing in list",
		dependsOnMethods="viewStatusNavigationTest")
public void numOfAdultTests(){
	System.out.println("Test 5:");
	List<String> actual_numOfAdultList= st.getUIDataList(st.numOfAdult_xpath);
	List<String> DB_numOfAdultList=checkDataBaseForStringList(st.query, "Client_Noadults");	
	System.out.println(actual_numOfAdultList);
	System.out.println(DB_numOfAdultList);
	boolean actual=st.compareUIandDBdata(actual_numOfAdultList, DB_numOfAdultList);
	Verify.assertEquals(actual, true);
	System.out.println("Test 5 completed");
}

@Test(description="validating the client source type with database  by adding and comparing in list",
		dependsOnMethods="viewStatusNavigationTest")
public void clientSourceTests(){
	System.out.println("Test 6:");
	List<String> actual_clientSourceList= st.getUIDataList(st.clientSource_xpath);
	List<String> DB_clientSourceList=checkDataBaseForStringList(st.query, "Client_Source");	
	System.out.println(actual_clientSourceList);
	System.out.println(DB_clientSourceList);
	boolean actual=st.compareUIandDBdata(actual_clientSourceList, DB_clientSourceList);
	Verify.assertEquals(actual, true);
	System.out.println("Test 6 completed");
}

@Test(description="validating the Report Rate type with database  by adding and comparing in list",
		dependsOnMethods="viewStatusNavigationTest")
public void reportRateTypeTests(){
	System.out.println("Test 7:");
	List<String> actual_reportRateTypeList= st.getUIDataList(st.reportRateType_xpath);
	List<String> DB_reportRateTypeList=checkDataBaseForStringList(st.query, "Report_Rate_Type");	
	System.out.println(actual_reportRateTypeList);
	System.out.println(DB_reportRateTypeList);
	boolean actual=st.compareUIandDBdata(actual_reportRateTypeList, DB_reportRateTypeList);
	Verify.assertEquals(actual, true);
	System.out.println("Test 7 completed");
}

@Test(description="validating the client currency type with database  by adding and comparing in list",
		dependsOnMethods="viewStatusNavigationTest")
public void clientCurrencyTests(){
	System.out.println("Test 8:");
	List<String> actual_clientCurrencyTypeList= st.getUIDataList(st.currency_xpath);
	List<String> DB_clientCurrencyTypeList=checkDataBaseForStringList(st.query, "Client_Curr");	
	System.out.println(actual_clientCurrencyTypeList);
	System.out.println(DB_clientCurrencyTypeList);
	boolean actual=st.compareUIandDBdata(actual_clientCurrencyTypeList, DB_clientCurrencyTypeList);
	Verify.assertEquals(actual, true);
	System.out.println("Test 8 completed");
}
*/
