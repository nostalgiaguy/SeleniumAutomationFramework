package com.erevmax.configuration.tests;


import static com.erevmax.utils.DataBase.*;
import static com.erevmax.mysample.Sample.DatabaseMethods.*;

import com.erevmax.assignment.tests.*;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.openqa.selenium.By;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.erevmax.RTAdmin.pages.MRConfigurationPage;
import com.erevmax.RTAdmin.pages.LoginPage;
import com.erevmax.baseTest.TestBase;
import com.erevmax.exception.MyException;
import com.erevmax.utils.Verify;

public class ManageReportTests extends TestBase {

	LoginPage lp= new LoginPage();
	
	String hotel_Code="00343";//"00120";
	String invalid_hotelCode="98567";
	MRConfigurationPage cp=new MRConfigurationPage(hotel_Code);
		
	@Test
	public void checkMREnabled()  {
		System.out.println("1");
		browser.click(cp.config);
		browser.autoSelect(cp.hotelCode,cp.selectHotelCode,hotel_Code);
		browser.click(cp.proceed);
		browser.elementWait(5000);

		Verify.assertTrue(browser.isDisplayed(cp.module_ManageReport));
		System.out.println("MR feature is enabled for hotel code" +hotel_Code);
		System.out.println("1");
	}




	@Test(dependsOnMethods="checkMREnabled")
	public void dailyReportTest()
	{
		System.out.println("2");
		String moduleName=browser.getText(cp.module_ManageReport);
		   System.out.println(moduleName);
		    browser.elementWait(6000);
			browser.click(cp.MRSettingEditButton);
			String parent=browser.switchToNewlyOpenedWindow();
			cp.manageReportSubscriptionSetting(cp.dailyReportSubscribed, 0);
			cp.manageReportSubscriptionSetting(cp.weeklyReportSubscribed, 1);
			cp.manageReportSubscriptionSetting(cp.monthlyReportSubscribed, 1);

			cp.channelSelectionSetting(cp.hotelCkBx, "yes");
			cp.channelSelectionSetting(cp.channelCkBx, "yes");
			cp.manageReportPreferenceSetting(cp.activateDaily, "yes");

			browser.enterInput(cp.maxDailyReport,"4");


			int daily_week_days=cp.checkDaysSelected(4);
		
			browser.enterInput(cp.noOfDailyReport,"30");
			browser.click(cp.save);
			browser.elementWait(6000);
			browser.switchToOriginalWindow(parent);
			String manageReportStatus=browser.getText(cp.manageReportStatus);
			Verify.assertEquals(manageReportStatus, "D");
			System.out.println("changes to daily");

			Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Daily_Subs"), "1");
			Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Daily_Active"), "1");
			Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Daily_frequency"), "4");
			Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Daily_Report_Days"), "30");
			Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Daily_Week_Days"), ""+daily_week_days+"");
			System.out.println("2");
	}


	@Test(dependsOnMethods="checkMREnabled")
	public void weeklyReportTest()
	{
		System.out.println("3");
		String moduleName=browser.getText(cp.module_ManageReport);
		   System.out.println(moduleName);
		   browser.elementWait(6000);

			browser.click(cp.MRSettingEditButton);
			String current=browser.switchToNewlyOpenedWindow();
			cp.manageReportSubscriptionSetting(cp.dailyReportSubscribed, 1);
			cp.manageReportSubscriptionSetting(cp.weeklyReportSubscribed, 0);
			cp.manageReportSubscriptionSetting(cp.monthlyReportSubscribed, 1);

			cp.channelSelectionSetting(cp.hotelCkBx, "yes");
			cp.channelSelectionSetting(cp.channelCkBx, "yes");
			cp.manageReportPreferenceSetting(cp.activateWeekly, "yes");
			browser.click(cp.expandWeekly);
			browser.enterInput(cp.noOfWeeklyReport,"90");
			cp.selectWeekDayWeeklyName("Sunday");

			browser.click(cp.save);
			 browser.elementWait(6000);
			browser.switchToOriginalWindow(current);

			String manageReportStatus=browser.getText(cp.manageReportStatus);
			Verify.assertEquals(manageReportStatus, "W");
			System.out.println("changes to weekly");

			Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Weekly_Subs"), "1");
			Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Weekly_Active"), "1");
			Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Weekly_Report_Days"), "90");
			Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Weekly_Week_Days"), "1");
			
			System.out.println("3");
			
			//for weekly_week_days:
			// for monday=64;tuesday=32;wednesday=16;thursday=8;friday=4,saturday=2,sunday=1
			 
	}




	

	@Test(dependsOnMethods="checkMREnabled")
	public void monthlyReportTest()
	{
		System.out.println("4");
		String moduleName=browser.getText(cp.module_ManageReport);
		   System.out.println(moduleName);
		   browser.elementWait(6000);			
			browser.click(cp.MRSettingEditButton);
			String current=browser.switchToNewlyOpenedWindow();
			cp.manageReportSubscriptionSetting(cp.dailyReportSubscribed, 1);
			cp.manageReportSubscriptionSetting(cp.weeklyReportSubscribed, 1);
			cp.manageReportSubscriptionSetting(cp.monthlyReportSubscribed, 0);

			cp.channelSelectionSetting(cp.hotelCkBx, "yes");
			cp.channelSelectionSetting(cp.channelCkBx, "yes");
			cp.manageReportPreferenceSetting(cp.activateMonthly, "yes");

			browser.click(cp.expandMonthly);

			browser.enterInput(cp.noOfMonthlyReport,"150");
		    boolean choose=false;
		    if(choose){
			cp.selectMonthlySubscription("first");
			browser.enterInput(cp.enterMonthDay, "31");

			browser.click(cp.save);
			browser.checkTextAlertMessage();

		    }
		    else{
		    	cp.selectMonthlySubscription("second");
		    	cp.selectPosOfWeekMonthly("Third");
		    	cp.selectWeekDayMonthlyName("Friday");
		    }
		    browser.click(cp.save);
		    browser.elementWait(6000);
		    browser.switchToOriginalWindow(current);

			String manageReportStatus=browser.getText(cp.manageReportStatus);
			Verify.assertEquals(manageReportStatus, "M");
			System.out.println("changes to monthly");

			Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Monthly_Subs"), "1");
			Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Monthly_Active"), "1");
			Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Monthly_Report_Days"), "150");
			if(choose){
			Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Monthly_Week_Date"), "31");
			}
			else{
				Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Monthly_Week_Days"), "Third_Friday");
			}
			System.out.println("4");
	}

	

	@Test(dependsOnMethods="checkMREnabled")
	public void dailyWeeklyReportTest()
	{
		System.out.println("5");
		String moduleName=browser.getText(cp.module_ManageReport);
		   System.out.println(moduleName);
		   browser.elementWait(6000);
			browser.click(cp.MRSettingEditButton);
		String current=browser.switchToNewlyOpenedWindow();
		cp.manageReportSubscriptionSetting(cp.dailyReportSubscribed, 0);
		cp.manageReportSubscriptionSetting(cp.weeklyReportSubscribed, 0);
		cp.manageReportSubscriptionSetting(cp.monthlyReportSubscribed, 1);

		cp.channelSelectionSetting(cp.hotelCkBx, "yes");
		cp.channelSelectionSetting(cp.channelCkBx, "yes");
		cp.manageReportPreferenceSetting(cp.activateDaily, "yes");
		cp.manageReportPreferenceSetting(cp.activateWeekly, "yes");



		browser.enterInput(cp.maxDailyReport,"4");
	

		int daily_week_days=cp.checkDaysSelected(4);
		browser.enterInput(cp.noOfDailyReport,"30");

		browser.click(cp.expandWeekly);
		browser.enterInput(cp.noOfWeeklyReport,"90");
		cp.selectWeekDayWeeklyName("Sunday");

		browser.click(cp.save);
		 browser.elementWait(6000);

		browser.switchToOriginalWindow(current);

		String manageReportStatus=browser.getText(cp.manageReportStatus);
		Verify.assertEquals(manageReportStatus, "D / W");
		System.out.println("changes to daily and weekly");
		System.out.println("5");
	}

	@Test(dependsOnMethods="checkMREnabled")
	public void dailyMonthlyReportTest()
	{
		System.out.println("6");
		String moduleName=browser.getText(cp.module_ManageReport);
		   System.out.println(moduleName);
		   browser.elementWait(6000);

		browser.click(cp.MRSettingEditButton);
		String current=browser.switchToNewlyOpenedWindow();
		cp.manageReportSubscriptionSetting(cp.dailyReportSubscribed, 0);
		cp.manageReportSubscriptionSetting(cp.weeklyReportSubscribed, 1);
		cp.manageReportSubscriptionSetting(cp.monthlyReportSubscribed, 0);

		cp.channelSelectionSetting(cp.hotelCkBx, "yes");
		cp.channelSelectionSetting(cp.channelCkBx, "yes");
		cp.manageReportPreferenceSetting(cp.activateDaily, "yes");
		cp.manageReportPreferenceSetting(cp.activateMonthly, "yes");



		browser.enterInput(cp.maxDailyReport,"4");

	  


	  	int daily_week_days=cp.checkDaysSelected(4);
		browser.enterInput(cp.noOfDailyReport,"30");

		browser.click(cp.expandMonthly);
		browser.enterInput(cp.noOfMonthlyReport,"15");
	    boolean choose=false;
	    if(choose){
		cp.selectMonthlySubscription("first");
		browser.enterInput(cp.enterMonthDay, "31");

		browser.click(cp.save);
		browser.checkTextAlertMessage();

	    }
	    else{
	    	cp.selectMonthlySubscription("second");
	    	cp.selectPosOfWeekMonthly("Third");
	    	cp.selectWeekDayMonthlyName("Friday");
	    }
	    browser.click(cp.save);
	    browser.elementWait(6000);

		browser.switchToOriginalWindow(current);

		String manageReportStatus=browser.getText(cp.manageReportStatus);
		Verify.assertEquals(manageReportStatus, "D / M");
		System.out.println("changes to daily and monthly");
		System.out.println("6");
	}


	@Test(dependsOnMethods="checkMREnabled")
	public void weeklyMonthlyReportTest()
	{
		System.out.println("7");
		String moduleName=browser.getText(cp.module_ManageReport);
		   System.out.println(moduleName);
		   browser.elementWait(6000);

		browser.click(cp.MRSettingEditButton);
		String current=browser.switchToNewlyOpenedWindow();
		cp.manageReportSubscriptionSetting(cp.dailyReportSubscribed, 1);
		cp.manageReportSubscriptionSetting(cp.weeklyReportSubscribed, 0);
		cp.manageReportSubscriptionSetting(cp.monthlyReportSubscribed, 0);


		cp.channelSelectionSetting(cp.hotelCkBx, "yes");
		cp.channelSelectionSetting(cp.channelCkBx, "yes");
		cp.manageReportPreferenceSetting(cp.activateMonthly, "yes");
		cp.manageReportPreferenceSetting(cp.activateWeekly, "yes");


		browser.click(cp.expandWeekly);
		browser.enterInput(cp.noOfWeeklyReport,"90");
		cp.selectWeekDayWeeklyName("Sunday");

        browser.click(cp.expandMonthly);
		browser.enterInput(cp.noOfMonthlyReport,"15");
	    boolean choose=false;
	    if(choose){
		cp.selectMonthlySubscription("first");
		browser.enterInput(cp.enterMonthDay, "31");

		browser.click(cp.save);
		browser.checkTextAlertMessage();

	    }
	    else{
	    	cp.selectMonthlySubscription("second");
	    	cp.selectPosOfWeekMonthly("Third");
	    	cp.selectWeekDayMonthlyName("Friday");
	    }
	    browser.click(cp.save);
	    browser.elementWait(6000);

		browser.switchToOriginalWindow(current);

		String manageReportStatus=browser.getText(cp.manageReportStatus);
		Verify.assertEquals(manageReportStatus, "W / M");
		System.out.println("changes to monthly and weekly");
		System.out.println("7");
	}



	@Test(dependsOnMethods="checkMREnabled")
	public void dailyWeeklyMonthlyEnabledReportTest()
	{
		System.out.println("8");
		String moduleName=browser.getText(cp.module_ManageReport);
		System.out.println(moduleName);
		 browser.elementWait(6000);

		browser.click(cp.MRSettingEditButton);
		String current=browser.switchToNewlyOpenedWindow();
		cp.manageReportSubscriptionSetting(cp.dailyReportSubscribed, 0);
		cp.manageReportSubscriptionSetting(cp.weeklyReportSubscribed, 0);
		cp.manageReportSubscriptionSetting(cp.monthlyReportSubscribed, 0);

		cp.channelSelectionSetting(cp.hotelCkBx, "yes");
		cp.channelSelectionSetting(cp.channelCkBx, "yes");
		cp.manageReportPreferenceSetting(cp.activateDaily, "yes");
		cp.manageReportPreferenceSetting(cp.activateWeekly, "yes");
		cp.manageReportPreferenceSetting(cp.activateMonthly, "yes");



		browser.enterInput(cp.maxDailyReport,"4");


		int daily_week_days=cp.checkDaysSelected(4);
		browser.enterInput(cp.noOfDailyReport,"30");

		browser.click(cp.expandWeekly);
		browser.enterInput(cp.noOfWeeklyReport,"90");
		cp.selectWeekDayWeeklyName("Sunday");

		browser.click(cp.expandMonthly);
		browser.enterInput(cp.noOfMonthlyReport,"120");
		boolean choose=false;
		if(choose){
			cp.selectMonthlySubscription("first");
			browser.enterInput(cp.enterMonthDay, "31");

			browser.click(cp.save);
			browser.checkTextAlertMessage();

		}
		else{
			cp.selectMonthlySubscription("second");
			cp.selectPosOfWeekMonthly("Third");
			cp.selectWeekDayMonthlyName("Monday");
		}
		browser.click(cp.save);
		 browser.elementWait(6000);

		browser.switchToOriginalWindow(current);

		String manageReportStatus=browser.getText(cp.manageReportStatus);
		Verify.assertEquals(manageReportStatus, "D / W / M");
		System.out.println("changes to daily,monthly and weekly");

		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Daily_Subs"), "1");
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Daily_Active"), "1");
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Daily_frequency"), "4");
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Daily_Report_Days"), "30");
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Daily_Week_Days"), ""+daily_week_days+"");

		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Weekly_Subs"), "1");
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Weekly_Active"), "1");
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Weekly_Report_Days"), "90");
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Weekly_Week_Days"), "1");

		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Monthly_Subs"), "1");
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Monthly_Active"), "1");
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Monthly_Report_Days"), "120");
		if(choose){
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Monthly_Week_Date"), "31");
		}
		else{
			Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Monthly_Week_Days"), "Third_Monday");
		}
		System.out.println("8");
	}

	@Test(dependsOnMethods="checkMREnabled")
	public void dailyWeeklyMonthlyDisabledReportTest()
	{
		System.out.println("9");
		String moduleName=browser.getText(cp.module_ManageReport);
		System.out.println(moduleName);
		 browser.elementWait(6000);

		browser.click(cp.MRSettingEditButton);
		String current=browser.switchToNewlyOpenedWindow();
		cp.manageReportSubscriptionSetting(cp.dailyReportSubscribed, 1);
		cp.manageReportSubscriptionSetting(cp.weeklyReportSubscribed, 1);
		cp.manageReportSubscriptionSetting(cp.monthlyReportSubscribed, 1);

		cp.channelSelectionSetting(cp.hotelCkBx, "yes");
		cp.channelSelectionSetting(cp.channelCkBx, "yes");

		browser.click(cp.save);
		 browser.elementWait(6000);

		browser.switchToOriginalWindow(current);

		String manageReportStatus=browser.getText(cp.manageReportStatus);
		Verify.assertEquals(manageReportStatus.trim(), "");
		System.out.println("facility is disabled");
		boolean choose=true;
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Daily_Subs"), "0");
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Daily_Active"), "0");
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Daily_frequency"), null);
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Daily_Report_Days"), null);
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Daily_Week_Days"), "0");

		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Weekly_Subs"), "0");
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Weekly_Active"), "0");
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Weekly_Report_Days"), null);
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Weekly_Week_Days"), "0");

		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Monthly_Subs"), "0");
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_Monthly_Active"), "0");
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Monthly_Report_Days"), null);
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Is_HotelSite_Selected"), "1");
		if(choose){
		Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Monthly_Week_Date"), null);
		}
		else{
			Verify.assertEquals(checkDataBaseForString(cp.getQuery(),"Monthly_Week_Days"), "Third_Monday");
		}
		System.out.println("9");
	}



	@Test(dependsOnMethods="checkMREnabled")

 public void dailyAlertTest(){
		System.out.println("10");
	 String moduleName=browser.getText(cp.module_ManageReport);
	 System.out.println(moduleName);
	 browser.elementWait(2000);
	 browser.click(cp.MRSettingEditButton);
	 String current=browser.switchToNewlyOpenedWindow();
	 cp.manageReportSubscriptionSetting(cp.dailyReportSubscribed, 0);

		cp.manageReportSubscriptionSetting(cp.weeklyReportSubscribed, 1);
		cp.manageReportSubscriptionSetting(cp.monthlyReportSubscribed, 1);

		cp.channelSelectionSetting(cp.hotelCkBx, "yes");
		cp.channelSelectionSetting(cp.channelCkBx, "yes");
		cp.manageReportPreferenceSetting(cp.activateDaily, "no");
		cp.manageReportPreferenceSetting(cp.activateWeekly, "no");
		cp.manageReportPreferenceSetting(cp.activateMonthly, "no");

	 browser.click(cp.save);
	browser.elementWait(5000);
	browser.switchToOriginalWindow(current);
	String manageReportStatus=browser.getText(cp.manageReportStatus);
	Verify.assertEquals(manageReportStatus, "D");
	browser.elementWait(3000);
	browser.click(cp.MRSettingEditButton);
	current=browser.switchToNewlyOpenedWindow();
	cp.manageReportSubscriptionSetting(cp.dailyReportSubscribed, 0);
	cp.manageReportPreferenceSetting(cp.activateDaily, "yes");
	browser.click(cp.save);
	Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please enter all required fields for daily subscription");
	browser.enterInput(cp.maxDailyReport,"9");
	browser.enterInput(cp.maxDailyReport,"4");

	browser.click(cp.save);
	Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please select the no of days on which reports needs to send out.");
	browser.enterInput(cp.noOfDailyReport,"7");

	browser.click(cp.save);
	Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please enter a value ranged in between 15-360");
	Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please select the no of days on which reports needs to send out.");
	browser.enterInput(cp.noOfDailyReport,"362");

	browser.elementWait(2000);
	browser.enterInput(cp.noOfDailyReport,"259");

	
	browser.elementWait(3000);
	browser.click(cp.save);


	Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please enter a value which is a multiple of 15.");
	Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please select the no of days on which reports needs to send out.");
	browser.enterInput(cp.noOfDailyReport,"180");
	browser.elementWait(2000);

	browser.click(cp.save);
	Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please select the no of days on which reports needs to send out.");
	cp.checkDaysSelected(9);

	browser.click(cp.save);
	browser.elementWait(6000);
	browser.switchToOriginalWindow(current);
	 manageReportStatus=browser.getText(cp.manageReportStatus);
	Verify.assertEquals(manageReportStatus, "D");
	System.out.println("10");
}
	
	
 @Test(dependsOnMethods="checkMREnabled")

 public void weeklyAlertTest(){
	 System.out.println("11");
	String moduleName=browser.getText(cp.module_ManageReport);
	 System.out.println(moduleName);
	 browser.elementWait(2000);
	 browser.click(cp.MRSettingEditButton);
	 String current=browser.switchToNewlyOpenedWindow();
	 cp.manageReportSubscriptionSetting(cp.dailyReportSubscribed, 1);

		cp.manageReportSubscriptionSetting(cp.weeklyReportSubscribed, 0);
		cp.manageReportSubscriptionSetting(cp.monthlyReportSubscribed, 1);

		cp.channelSelectionSetting(cp.hotelCkBx, "yes");
		cp.channelSelectionSetting(cp.channelCkBx, "yes");
		cp.manageReportPreferenceSetting(cp.activateDaily, "no");
		cp.manageReportPreferenceSetting(cp.activateWeekly, "no");
		cp.manageReportPreferenceSetting(cp.activateMonthly, "no");

	 browser.click(cp.save);
	browser.elementWait(6000);
	browser.switchToOriginalWindow(current);
	String manageReportStatus=browser.getText(cp.manageReportStatus);
	Verify.assertEquals(manageReportStatus, "W");
	browser.elementWait(3000);
	browser.click(cp.MRSettingEditButton);
	current=browser.switchToNewlyOpenedWindow();
	cp.manageReportSubscriptionSetting(cp.weeklyReportSubscribed, 0);
	cp.manageReportPreferenceSetting(cp.activateWeekly, "yes");
	browser.click(cp.save);
	Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please enter all required fields for Weekly subscription");

	browser.click(cp.expandWeekly);
	browser.enterInput(cp.noOfWeeklyReport,"7");
	browser.click(cp.save);
	Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please enter a value ranged in between 15-360");
	Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please enter all required fields for Weekly subscription");
	browser.elementWait(3000);

	browser.enterInput(cp.noOfWeeklyReport,"23");
	browser.click(cp.save);
	Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please enter a value which is a multiple of 15.");
	Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please enter all required fields for Weekly subscription");
	browser.elementWait(3000);

	browser.enterInput(cp.noOfWeeklyReport,"60");
	browser.click(cp.save);
	browser.elementWait(6000);
	browser.switchToOriginalWindow(current);
	 manageReportStatus=browser.getText(cp.manageReportStatus);
	Verify.assertEquals(manageReportStatus, "W");
	System.out.println("11");
 }
	 
	@Test(dependsOnMethods="checkMREnabled")

	public void monthlyAlertTest(){
		System.out.println("12");
		String moduleName=browser.getText(cp.module_ManageReport);
		System.out.println(moduleName);
		browser.elementWait(2000);
		browser.click(cp.MRSettingEditButton);
		String current=browser.switchToNewlyOpenedWindow();
		cp.manageReportSubscriptionSetting(cp.dailyReportSubscribed, 1);

		cp.manageReportSubscriptionSetting(cp.weeklyReportSubscribed, 1);
		cp.manageReportSubscriptionSetting(cp.monthlyReportSubscribed, 0);

		cp.channelSelectionSetting(cp.hotelCkBx, "yes");
		cp.channelSelectionSetting(cp.channelCkBx, "yes");
		cp.manageReportPreferenceSetting(cp.activateDaily, "no");
		cp.manageReportPreferenceSetting(cp.activateWeekly, "no");
		cp.manageReportPreferenceSetting(cp.activateMonthly, "no");

		browser.click(cp.save);
		browser.elementWait(6000);
		browser.switchToOriginalWindow(current);
		String manageReportStatus=browser.getText(cp.manageReportStatus);
		Verify.assertEquals(manageReportStatus, "M");
		browser.elementWait(3000);
		browser.click(cp.MRSettingEditButton);
		current=browser.switchToNewlyOpenedWindow();
		cp.manageReportSubscriptionSetting(cp.monthlyReportSubscribed, 0);
		cp.manageReportPreferenceSetting(cp.activateMonthly, "yes");
		browser.click(cp.save);
		Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please enter all required fields for Monthly subscription");

		browser.click(cp.expandMonthly);

		browser.enterInput(cp.noOfMonthlyReport,"7");
		browser.click(cp.save);
		Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please enter a value ranged in between 15-360");
		Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please enter all required fields for Monthly subscription");
		browser.elementWait(3000);

		browser.enterInput(cp.noOfMonthlyReport,"23");
		browser.click(cp.save);
		Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please enter a value which is a multiple of 15.");
		Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please enter all required fields for Monthly subscription");
		browser.elementWait(3000);

		browser.enterInput(cp.noOfMonthlyReport,"60");

		cp.selectMonthlySubscription("first");
		browser.enterInput(cp.enterMonthDay, "11");
		browser.click(cp.save);
		browser.elementWait(6000);
		browser.switchToOriginalWindow(current);
		manageReportStatus=browser.getText(cp.manageReportStatus);
		Verify.assertEquals(manageReportStatus, "M");
		System.out.println("12");
	}

	
	@Test(dependsOnMethods="checkMREnabled")
	public void AlertTestForChannel(){
		System.out.println("13");
		String moduleName=browser.getText(cp.module_ManageReport);
		System.out.println(moduleName);
		 browser.elementWait(6000);

		browser.click(cp.MRSettingEditButton);
		String current=browser.switchToNewlyOpenedWindow();
		cp.manageReportSubscriptionSetting(cp.dailyReportSubscribed, 1);
		cp.manageReportSubscriptionSetting(cp.weeklyReportSubscribed, 1);
		cp.manageReportSubscriptionSetting(cp.monthlyReportSubscribed, 1);

		cp.channelSelectionSetting(cp.hotelCkBx, "no");
		cp.channelSelectionSetting(cp.channelCkBx, "no");
		
		browser.click(cp.save);
		Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please select a hotel first.");

		browser.elementWait(2000);
	
		cp.channelSelectionSetting(cp.hotelCkBx, "yes");
		browser.elementWait(5000);
		cp.channelSelectionSetting(cp.channelCkBx, "no");
	
		browser.click(cp.save);
		Verify.assertEquals(browser.checkTextAlertMessage().trim(), "Please select at least one shoppable source.");
		browser.elementWait(2000);
		cp.channelSelectionSetting(cp.channelCkBx, "yes");
		browser.click(cp.save);
		browser.switchToOriginalWindow(current);
		System.out.println("13");

	}



	@Test(dependsOnMethods="checkMREnabled")

	public void channelSelectionTest() {
		System.out.println("14");
		String moduleName=browser.getText(cp.module_ManageReport);
		System.out.println(moduleName);
		browser.elementWait(6000);

		browser.click(cp.MRSettingEditButton); 
		String current=browser.switchToNewlyOpenedWindow();
		cp.manageReportSubscriptionSetting(cp.dailyReportSubscribed, 1);
		cp.manageReportSubscriptionSetting(cp.weeklyReportSubscribed, 1);
		cp.manageReportSubscriptionSetting(cp.monthlyReportSubscribed, 1);

		browser.click(cp.expandChannelSelection);
		cp.selectNumberOfHotels(40);
		cp.selectNumberOfChannels(20);
		browser.click(cp.save);
		String actual=checkDataBaseForString(cp.getQuery(),"Is_HotelSite_Selected");
		Verify.assertEquals(actual, "1");
		browser.switchToOriginalWindow(current);
		System.out.println("14");

	}


}

/*
 * /*****************************************************************************************************************
 
Connection con= getRateTigerDataBaseConnection();
String getQuery()="select * from rt_mr_settings where hotel_code='00343'";
ResultSet rs=executeQuery(getQuery(),con);
try {
	while(rs.next()){
   Verify.assertEquals(rs.getBoolean("is_daily_Active"), "1");
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

 
@Test
public void validate(){
	Connection con= getRateTigerDataBaseConnection();
	String getQuery()="select * from rt_mr_settings where hotel_code='00343'";
	ResultSet rs=executeQuery(getQuery(),con);
	try {
		while(rs.next()){
	    Verify.assertTrue(rs.getBoolean("is_daily_Active"));
	    Verify.assertTrue(rs.getBoolean("is_Daily_Subs"));
		Verify.assertEquals(rs.getInt("Daily_Frequency"),4);
	     System.out.println(rs.getInt("Daily_Report_Days"));
		Verify.assertEquals(rs.getInt("Daily_Report_Days"), 0);
		Verify.assertEquals(rs.getInt("daily_week_days"), 0);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}




@Test(dataProvider="TestCaseData2")
public void TestCase2(String expected){
	browser.click(cp.config);
	browser.click(cp.proceed);
	String actual=browser.getText(cp.hotelCodeAlert);
	Verify.assertEquals(actual, expected);
	System.out.println("enter hotel code");
}

@DataProvider	
public Object[][] TestCaseData2(){
	Object [][] data= new Object[1][1];
	data[0][0]="Enter hotel code or hotel name in order to proceed";

	return data;
}

@Test(dataProvider="TestCaseData3")
public void TestCase3(String expected){
	browser.click(cp.config);
	browser.enterInput(cp.hotelCode, invalid_hotelCode);
	browser.click(cp.proceed);
	browser.elementWait(4000);

	String actual=browser.getText(cp.hotelCodeAlert);
	Verify.assertEquals(actual, expected);
	System.out.println("enter valid hotel code");
}

@DataProvider	
public Object[][] TestCaseData3(){
	Object [][] data= new Object[1][1];
	data[0][0]="Invalid hotel code.";

	return data;
}
 */
//@Test(dependsOnMethods={"successLogin",""})