package com.erevmax.selenium.RTAdmin;


import org.apache.log4j.Logger;

import static com.erevmax.selenium.PageObjects.ConfigDetailActionPage.cdp;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.erevmax.baseTest.TestBase;
import com.erevmax.utils.Verify;

public class Test_TC09_Preferences_DailyRateAlert extends TestBase{

	public String rateAlert1_YN = "Yes";
	public String backModule1_YN = "Yes";
	public String multReady1_YN = "Yes";
	public String dailyRep1_YN = "Yes";
	public String alertChValType1_AmtPct = "Amount";
	public String alertChValue1 = "15.00";
	public String dailyMaxDays1 = "10";
	public String alertMaxDays1 = "10";
	public String changeAmount1 = "45";

	private final Logger log= Logger.getLogger(this.getClass().getName());

	@Test(priority = 0)
	public void navigateConfigDetailActionPage(){
		log.info("Test 1 Start");
		browser.click(xpath_repository.CONFIG_TAB);
		log.info("Current url is :"+browser.getCurrentUrl());
		browser.autoSelect(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX, xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX_AJAXLISTOPTION, hotelCode);
		browser.click(xpath_repository.CLIENTHOTEL_SEARCH_CONTINUE_BUTTON);
		log.info("Next Current url is :" +browser.getCurrentUrl());
		int status =browser.sendHttpGetRequest(browser.getCurrentUrl());
		Verify.verifyEquals(status,200);
		log.info("Test 1 completed");
	}

	 @DataProvider(name="dailyRateAlert") 
	  public static Object[][] dailyRateAlertData() {
	        return new Object[][] { { "Yes", "Yes","Yes","Yes","Amount", "15.00","10","10","45"},
	        		{ "No", "No","No","No","Percentage", "22.00","90","10","65"}};
	 }

	 @Test(priority = 10,dataProvider="dailyRateAlertData")
	 public void DailyRateAlertPrefSave1(String rateAlert_YN,
			 String backModule_YN,
			 String multReady_YN,
			 String dailyRep_YN,
			 String alertChValType_AmtPct,
			 String alertChValue,
			 String dailyMaxDays,
			 String alertMaxDays,
			 String changeAmount) throws InterruptedException {
		log.info("Test 2 Start");
		String parentwindow=null;
		try{
			browser.waitForElementClickable(cdp.editDailyRateAlertBtn);
			browser.click(cdp.editDailyRateAlertBtn);
			browser.waitForNumberOfWindowsToEqual(2);
			parentwindow = browser.switchToNewlyOpenedWindow();
			browser.elementWait(3000);
			
			cdp.populateDailyRateAlertPref(rateAlert_YN, backModule_YN, multReady_YN, dailyRep_YN, alertChValType_AmtPct, alertChValue, dailyMaxDays, alertMaxDays, changeAmount);
			
			browser.elementWait(2000);
			browser.click(cdp.SaveDailyRateAlertBtn);
			browser.waitForNumberOfWindowsToEqual(1);
			browser.switchToOriginalWindow(parentwindow);
			browser.elementWait(2000);

			Verify.verifyEquals(browser.getText(cdp.alertChangeValueDisplay), alertChValue);
			Verify.verifyEquals(browser.getText(cdp.dailyMaxDaysDisplay), dailyMaxDays);
			Verify.verifyEquals(browser.getText(cdp.changeAmountDisplay), changeAmount);
			log.info("Test 2 completed");
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				parentwindow = browser.switchToNewlyOpenedWindow();
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentwindow);
			}
		}

	}

	//Verify that 'Change Amount:' text box only allows upto 2 digits
	@Test(priority = 30)
	public void DailyRateAlertPrefVal1() throws InterruptedException {
		log.info("Test 3 Start");
		String parentwindow=null;
		browser.waitForElementClickable(cdp.editDailyRateAlertBtn);
		browser.click(cdp.editDailyRateAlertBtn);
		browser.waitForNumberOfWindowsToEqual(2);
		parentwindow = browser.switchToNewlyOpenedWindow();
		browser.elementWait(3000);
		
		cdp.populateDailyRateAlertPref(rateAlert1_YN, backModule1_YN, multReady1_YN, dailyRep1_YN, alertChValType1_AmtPct, alertChValue1, dailyMaxDays1, alertMaxDays1, "2500");
		
		browser.elementWait(2000);
		browser.click(cdp.SaveDailyRateAlertBtn);
		browser.waitForNumberOfWindowsToEqual(1);
		browser.switchToOriginalWindow(parentwindow);
		browser.elementWait(2000);
		
		Verify.verifyEquals(browser.getText(cdp.changeAmountDisplay), "25");
		log.info("Test 3 completed");

	}

	//Verify that 'Change Amount:' field is mandatory
	@Test(priority = 40)
	public void DailyRateAlertPrefVal2() throws InterruptedException {
		log.info("Test 4 Start");
		String parentwindow=null;
		try{
			browser.waitForElementClickable(cdp.editDailyRateAlertBtn);
			browser.click(cdp.editDailyRateAlertBtn);
			browser.waitForNumberOfWindowsToEqual(2);
			parentwindow = browser.switchToNewlyOpenedWindow();
			browser.elementWait(3000);
			
			cdp.populateDailyRateAlertPref(rateAlert1_YN, backModule1_YN, multReady1_YN, dailyRep1_YN, alertChValType1_AmtPct, alertChValue1, dailyMaxDays1, alertMaxDays1, "");
			
			browser.elementWait(2000);
			browser.click(cdp.SaveDailyRateAlertBtn);
			browser.elementWait(2000);

			Verify.verifyEquals(browser.getText(cdp.ValidationMsglabel), "Please select the fields!");
			log.info("Test 4 completed");
		}
		finally{
			browser.getDriver().close();
			browser.switchToOriginalWindow(parentwindow);
		}
	}

	//Verify that 'Change Amount:' field is does NOT allow special character
	@Test(priority = 50)
	public void DailyRateAlertPrefVal3() throws InterruptedException {
		log.info("Test 5 Start");
		String parentwindow=null;
		try{
			browser.waitForElementClickable(cdp.editDailyRateAlertBtn);
			browser.click(cdp.editDailyRateAlertBtn);
			browser.waitForNumberOfWindowsToEqual(2);
			parentwindow = browser.switchToNewlyOpenedWindow();
			browser.elementWait(3000);
			
			cdp.populateDailyRateAlertPref(rateAlert1_YN, backModule1_YN, multReady1_YN, dailyRep1_YN, alertChValType1_AmtPct, alertChValue1, dailyMaxDays1, alertMaxDays1, "$%");
			
			browser.elementWait(2000);
			browser.click(cdp.SaveDailyRateAlertBtn);
			browser.elementWait(2000);

			Verify.verifyEquals(browser.getText(cdp.ValidationMsglabel), "Daily / Rate Alert Preferences can not be updated! Try again!");
			log.info("Test 5 completed");
		}
		finally{
			browser.getDriver().close();
			browser.switchToOriginalWindow(parentwindow);
		}
	}

	//Verify that 'Change Amount:' field is does NOT allow alphabet numeric characters
	@Test(priority = 60)
	public void DailyRateAlertPrefVal4() throws InterruptedException {
		log.info("Test 6 Start");
		String parentwindow=null;
		try{
			browser.waitForElementClickable(cdp.editDailyRateAlertBtn);
			browser.click(cdp.editDailyRateAlertBtn);
			browser.waitForNumberOfWindowsToEqual(2);
			parentwindow = browser.switchToNewlyOpenedWindow();
			browser.elementWait(3000);
			
			cdp.populateDailyRateAlertPref(rateAlert1_YN, backModule1_YN, multReady1_YN, dailyRep1_YN, alertChValType1_AmtPct, alertChValue1, dailyMaxDays1, alertMaxDays1, "x1");
		
			browser.elementWait(2000);
			browser.click(cdp.SaveDailyRateAlertBtn);
			browser.elementWait(2000);

			Verify.verifyEquals(browser.getText(cdp.ValidationMsglabel), "Daily / Rate Alert Preferences can not be updated! Try again!");
			log.info("Test 6 completed");
		}
		finally{
			browser.getDriver().close();
			browser.switchToOriginalWindow(parentwindow);
		}
	}
}
