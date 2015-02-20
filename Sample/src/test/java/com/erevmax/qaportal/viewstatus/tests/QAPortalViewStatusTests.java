package com.erevmax.qaportal.viewstatus.tests;

import java.util.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.apache.log4j.Logger;
import com.erevmax.RTAdmin.pages.AssignmentPage;
import com.erevmax.RTAdmin.pages.QAPortalViewStatusPage;
import com.erevmax.baseTest.TestBase;
import com.erevmax.utils.Verify;

public class QAPortalViewStatusTests extends TestBase{

	QAPortalViewStatusPage st=new QAPortalViewStatusPage("23557");
	AssignmentPage ap=new AssignmentPage();
	private final Logger log= Logger.getLogger(this.getClass().getName());
	 


	@Test(description="navigating to Managed Reports QA portal view status page")
	public void viewStatusNavigationTest(){
		log.info("Test 1:start");
		browser.click(st.othersMenu);
		log.info("Current url is :"+browser.getCurrentUrl());
		//browser.getLog().info("Current url is :" +browser.getCurrentUrl());
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
		String msg="No Reports found.";
		boolean flag = false;
		if(browser.getText(st.message).equalsIgnoreCase(msg)){
			flag =false;
			System.out.println(msg+" for "+st.today_date);
			System.out.println("Test 2 incomplete.");
		}
		else{
		flag=true;
		int actual_viewStatusCount=browser.getListSize(st.statusCount);
		int DB_viewStatusCount=st.checkDataBaseForInteger(st.query_totalStatusCount,"viewStatusCount");
		System.out.println("actual_viewStatusCount: "+actual_viewStatusCount);
		System.out.println("DB_viewStatusCount: "+DB_viewStatusCount);
		Verify.assertEquals(actual_viewStatusCount, DB_viewStatusCount);
		System.out.println("Test 2 completed.");

		st.DB_Method_1(st.query_Method_1);
		st.DB_Method_2(st.query_Method_2);
		}
		Verify.assertTrue(flag);
	}
	
	@Test(description="validating the data with database for today date by adding and comparing in list",
	dependsOnMethods={"viewStatusNavigationTest","totalViewStatusCountTest"},
	dataProvider="dataField")
	public void hotelNameTests(String lctr,List<String> DB_list, boolean flag){
		if(flag){
			browser.click(st.refresh);
		}
		System.out.println("Test 3:");
		List<String> actual_UIList= st.getUIDataList(lctr);
		System.out.println(actual_UIList);
		System.out.println(DB_list);
		boolean actual=st.compareUIandDBdata(actual_UIList, DB_list);
		Verify.assertEquals(actual, true);
		System.out.println("Test 3 completed");
	}
	@DataProvider	
	public Object[][] dataField(){
		Object [][] data= new Object[6][3];
		data[0][0]=st.hotelName_xpath;
		data[0][1]=st.getHotel_NameList;
		data[0][2]=true;

		data[1][0]=st.reportDays_xpath;
		data[1][1]=st.getReport_DaysList;
		data[1][2]=false;

		data[2][0]=st.numOfAdult_xpath;
		data[2][1]=st.getClient_NoadultsList;
		data[2][2]=false;

		data[3][0]=st.clientSource_xpath;
		data[3][1]=st.getClient_SourceList;
		data[3][2]=false;

		data[4][0]=st.reportRateType_xpath;
		data[4][1]=st.getReport_Rate_TypeList;
		data[4][2]=false;

		data[5][0]=st.currency_xpath;
		data[5][1]=st.getClient_CurrList;
		data[5][2]=false;

		return data;
	}

	@Test(description="validating the fetch status with database  by adding and comparing in list",
	dependsOnMethods={"viewStatusNavigationTest","totalViewStatusCountTest"})
	public void fetchStatusTests(){
		browser.click(st.refresh);
		System.out.println("Test 4:");
		List<String> db_temp =  new ArrayList<String>();
		List<String> actual_fetchStatusList= st.getUIDataList(st.fetchStatus_xpath);
		List<String> DB_fetchStatusList=st.getStatusList;
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
	dependsOnMethods={"viewStatusNavigationTest","totalViewStatusCountTest"})
	public void qANoteTests(){
		System.out.println("Test 5:");
		browser.click(st.refresh);
		List<String> ui_temp = new ArrayList<String>();
		List<String> db_temp =  new ArrayList<String>();
		List<String> actual_QANoteList= st.getUIDataList(st.QANote_xpath);
		System.out.println(actual_QANoteList);
		for(int count=0;count<actual_QANoteList.size();count++){
			String temp=actual_QANoteList.get(count).replaceAll(" ", "");
			ui_temp.add(temp);
		}
		List<String> DB_QANoteList=st.getQA_NotesList;
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

	@Test(description="validating the recurrence of hotel with database ",
	dependsOnMethods={"viewStatusNavigationTest","totalViewStatusCountTest"})
	public void recurrenceTest(){
		System.out.println("Test 6:");
		browser.click(st.refresh);
		int recurrence=0;
		List<String> actual_RecurrenceList= st.getUIDataList(st.hotelName_xpath);
		List<String> DB_RecurrenceList=st.checkDataBaseForStringList(st.getHotelQuery(), "Recurrence");
		String hotel_name=st.checkDataBaseForString(st.getHotelQuery(),"Hotel_Name");
		System.out.println("Recurrence test for hotel name:="+hotel_name+" with hotel code:="+st.hotel_code);
		for(int count=0;count<actual_RecurrenceList.size();count++){
			if(actual_RecurrenceList.get(count).equals(hotel_name)){
				recurrence++;
			}	
		}
		System.out.println(DB_RecurrenceList);
		Verify.assertEquals(DB_RecurrenceList.size(), recurrence);
		System.out.println("Test 6 completed");
	}

	@Test(description="validating the assigned to names with database ",
	dependsOnMethods={"viewStatusNavigationTest","totalViewStatusCountTest"})
	public void assignedToTest(){
		System.out.println("Test 7:");
		browser.click(st.refresh);
		List<String> db_temp =  new ArrayList<String>();
		List<String> actual_assignedToList= st.getUIDataList(st.assignedTo_xpath);
		List<String> DB_adminLoginList=st.getAdmin_LoginList;
		System.out.println(actual_assignedToList);
		System.out.println(DB_adminLoginList);

		for(int count=0;count<DB_adminLoginList.size();count++){
			String temp=DB_adminLoginList.get(count);
			int num=(st.getAdminLoginList).indexOf(temp);
			db_temp.add((st.getFullNameList).get(num));
		}	
		System.out.println(actual_assignedToList);
		System.out.println(db_temp);
		boolean actual=st.compareUIandDBdata(actual_assignedToList, db_temp);
		Verify.assertEquals(actual, true);
		System.out.println("Test 7 completed");
	}

	@Test(description="validating total status report count with database for perticular assigned name",
	dependsOnMethods={"viewStatusNavigationTest","totalViewStatusCountTest"},
	dataProvider="NameField")
	public void totalStatusCountPerAssigne(String qaName){
		System.out.println("Test 8:");
		browser.click(st.refresh);
		browser.elementWait(4000);
		browser.autoSelect(st.AssignedTo,st.select_AssignedTo,qaName);
		String msg=browser.getText(st.message).trim();
		if(msg.equalsIgnoreCase("No Reports found.")){
			System.out.println(msg+" for "+qaName);
			System.out.println("Test 8 completed");
		}
		else{
			int index=(st.getFullNameList).indexOf(qaName);
			String login_name=(st.getAdminLoginList).get(index);
			int actual_viewStatusCount=st.statusCountByValue();
			int DB_viewStatusCount=Collections.frequency(st.getAdmin_LoginList, login_name);
			System.out.println("actual_viewStatusCount: "+actual_viewStatusCount+" for: "+qaName);
			System.out.println("DB_viewStatusCount: "+DB_viewStatusCount+" for: "+qaName);
			Verify.assertEquals(actual_viewStatusCount, DB_viewStatusCount);
			System.out.println("Test 8 completed");
		}	
	}
	@DataProvider	
	public Object[][] NameField(){
		Object [][] data= new Object[3][1];
		data[0][0]="shubham pathak";
		data[1][0]="Debashis Das"; 
		data[2][0]="Aditya Mohanty";
		return data;
	}

	@Test(description="validating total status report count with database for perticular hotel name ",
	dependsOnMethods={"viewStatusNavigationTest","totalViewStatusCountTest"},
	dataProvider="hotelNameField")
	public void totalStatusCountPerHotel(String hotelName){
		System.out.println("Test 9:");
		browser.click(st.refresh);
		browser.elementWait(4000);
		browser.autoSelect(st.HotelName,st.select_HotelName,hotelName);
		String msg="No Reports found.";
		if(browser.getText(st.message).equalsIgnoreCase(msg)){
			System.out.println(msg+" for "+hotelName);
			System.out.println("Test 9 completed");
		}
		else{
			int actual_viewStatusCount= st.statusCountByValue();
			int DB_viewStatusCount=Collections.frequency(st.getHotel_NameList, hotelName);
			System.out.println("actual_viewStatusCount: "+actual_viewStatusCount);
			System.out.println("DB_viewStatusCount: "+DB_viewStatusCount);
			Verify.assertEquals(actual_viewStatusCount, DB_viewStatusCount);
			System.out.println("Test 9 completed");			
		}			
	}

	@DataProvider	
	public Object[][] hotelNameField(){
		Object [][] data= new Object[1][1];
		data[0][0]="Test Support Hotel";			
		return data;
	}

	@Test(description="validating total status report count with database for perticular assigned name ",
	dependsOnMethods={"viewStatusNavigationTest","totalViewStatusCountTest"},
	dataProvider="editNoteField")
	public void editNoteFieldTest(String hotelName,String editNote,String expected_update){
		System.out.println("Test 10:");
		browser.click(st.refresh);
		browser.elementWait(4000);
		browser.autoSelect(st.HotelName,st.select_HotelName,hotelName);
		String msg="No Reports found.";
		try{
			if(browser.getText(st.message).equalsIgnoreCase(msg)){
				System.out.println(msg+" for this "+hotelName);
				System.out.println("Test 10 completed");
			}
			else{
				int indexWebElement=0;
				List<WebElement> editButton=browser.findWebElements(st.statusCount);
				int count=0;
				while(count<editButton.size()){
					List<String>hotel=browser.getListText(By.xpath("//tr[starts-with(@id,'viewstatusTR_')]/td[4]/div"));
					if(hotel.get(count).equalsIgnoreCase(hotelName) ){
						indexWebElement =count;
						break;
					}
					count++;
				}
				editButton.get(indexWebElement).click();
				By textAreaLocator=By.xpath("//textarea[contains(@id,'T_QAnotes_') and contains(@id,'_"+indexWebElement+"_' )]");
				browser.enterInput(textAreaLocator, editNote);
				int hotelNameIndex=st.getHotel_NameList.indexOf(hotelName);
				String adminLoginName=st.getAdmin_LoginList.get(hotelNameIndex);
				int adminLoginNameIndex=st.getAdminLoginList.indexOf(adminLoginName);
				String adminFullName=st.getFullNameList.get(adminLoginNameIndex);			
				browser.click(st.saveQANotes);
				browser.elementWait(2000);
				String actual_update=browser.getText(st.message).trim();
				Verify.assertEquals(actual_update, expected_update);
				browser.click(ap.assignmentsView);
				browser.elementWait(3000);
				browser.autoSelect(ap.assignedTo,ap.selectAssign,adminFullName);
				browser.click(ap.searchButton);
				browser.elementWait(3000);
				int recordCount=browser.getListSize(ap.tableElement);
				int page=1;
				for(int start=0;start<recordCount;start++){
					String text=browser.getText(By.xpath("//*[@id='assignmentBody']/tr["+(start+1)+"]/td[5]/div"));
					if(text.equals(hotelName)){
						String actual=browser.getText(By.xpath("//*[@id='note2_"+start+"']")).trim();
						Verify.assertEquals(actual, editNote);
						System.out.println("Test 10 completed.");
						break;
					}
					if(start/50==page && start!=0){
						browser.click(ap.next);
						page++;
					}
				}
			}
		}
		finally{
			browser.click(st.mrViewStatus);
		}
		}

		@DataProvider	
		public Object[][] editNoteField(){
			Object [][] data= new Object[1][3];
			data[0][0]="Test Support Hotel";
			data[0][1]="Daily,Weekly,Monthly Reports.";
			data[0][2]="Note updated successfully";

			return data;
		}
}
