package com.erevmax.selenium.RTAdmin;

import com.erevmax.utils.Verify;

import static com.erevmax.selenium.PageObjects.ConfigDetailActionPage.cdp;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.erevmax.baseTest.TestBase;

public class Test_TC04_SubscriptionDetail extends TestBase {

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
	@DataProvider(name="hotelData") 
	public static Object[][] subscriptionData() {
		return new Object[][] { { "Abode", "12345","Yes"},{"No Group", "54321","No"}};
	}

	@Test(priority = 10,dataProvider="subscriptionData")
	public void SubscriptionDetailsSave1(String groupcode,String clientFilterCode,String Historical)
			throws InterruptedException {
		log.info("Test 2 Start");
		String parentwindow=null;
		browser.elementWait(4000);
		browser.click(cdp.editSubscriptionDetailBtn);	
		browser.elementWait(2000);
		parentwindow = browser.switchToNewlyOpenedWindow();
		cdp.populateSubscriptionDetails(groupcode, clientFilterCode, Historical);
		browser.elementWait(2000);
		browser.click(cdp.SaveSubscriptionDetailBtn);
		browser.elementWait(2000);
		browser.switchToOriginalWindow(parentwindow);
		browser.elementWait(2000);
		
		Verify.verifyEquals(browser.getText(cdp.groupCodeDisplay),groupcode);
		Verify.verifyEquals(browser.getText(cdp.clientFilterCodeDisplay),clientFilterCode);
		Verify.verifyEquals(browser.getText(cdp.clientReportDateDisplay),cdp.todayDateForDashboardDB());
		Verify.verifyEquals(browser.getText(cdp.historicalDisplay),Historical);
		log.info("Test 2 completed");
	}
	
}
