package com.erevmax.selenium.RTAdmin;

import com.erevmax.utils.Verify;

import static com.erevmax.selenium.PageObjects.ConfigDetailActionPage.cdp;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.erevmax.baseTest.TestBase;


public class Test_TC10_Preferences_RTReview extends TestBase{

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
	@DataProvider(name="RTReview")
	public static Object[][] RTReviewData(){
		return new Object[][] {{"Saturday","Weekly","Unsubscribed"},{"Monday","Daily","Subscribed"}};
	}
	@Test(priority = 10,dataProvider="RTReviewData")
	public void RTReviewPrefSave1(String alertDay,String alertType,String alertStatus) throws InterruptedException {
		log.info("Test 2 Start");
		String parentwindow=null;

		browser.elementWait(4000);
		browser.click(cdp.editPreferencesRTReviewBtn);
		browser.elementWait(2000);
		parentwindow = browser.switchToNewlyOpenedWindow();
		cdp.populateRTReviewPref(alertDay, alertType, alertStatus);
		browser.elementWait(2000);
		browser.click(cdp.SavePreferencesRTReviewBtn);
		browser.switchToOriginalWindow(parentwindow);
		browser.elementWait(2000);		
		
		Verify.verifyEquals(browser.getText(cdp.alertDayView), alertDay);
		Verify.verifyEquals(browser.getText(cdp.alertTypeView), alertType);
		Verify.verifyEquals(browser.getText(cdp.alertStatusView), alertStatus);
		log.info("Test 2 completed");
	}
	
}
