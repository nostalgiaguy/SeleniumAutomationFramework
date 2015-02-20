package com.erevmax.selenium.RTAdmin;

import static com.erevmax.selenium.PageObjects.ConfigDetailActionPage.cdp;

import com.erevmax.utils.Verify;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.erevmax.baseTest.TestBase;



public class Test_TC05_ProductDetail extends TestBase{

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
	
	 @DataProvider(name="productDetail") 
	  public static Object[][] productDetailData() {
	        return new Object[][] { { "TA", "Current Account","15","5","7", "GMT+09:30"},
	        		{ "RT", "Demo","12","20","14","GMT+04:30" }};
	  }
	
	@Test(priority = 10,dataProvider="productDetailData")
	public void ProductDetailsSave1(String clientSource,String clientStatus,String noOfWebsites,String noOfHotels,String gracePeriod,String timeZone) 
			throws InterruptedException {
		log.info("Test 2 Start");
		String parentwindow=null;
		
		browser.elementWait(2000);
		browser.click(cdp.editProductDetailBtn);
		browser.elementWait(2000);
		parentwindow = browser.switchToNewlyOpenedWindow();
		
		
		cdp.populateProductDetails(clientSource, clientStatus, noOfWebsites, noOfHotels,gracePeriod,timeZone);
		browser.elementWait(2000);
		browser.click(cdp.SaveProductDetailBtn);
		browser.switchToOriginalWindow(parentwindow);
		
		browser.elementWait(2000);
		
		browser.waitForElementVisible(cdp.clientSourceView);
		Verify.verifyEquals(browser.getText(cdp.clientSourceView),clientSource);
		Verify.verifyEquals(browser.getText(cdp.clientStatusView),clientStatus);
		Verify.verifyEquals(browser.getText(cdp.noWebsites),noOfWebsites);
		Verify.verifyEquals(browser.getText(cdp.noHotels),noOfHotels);
		Verify.verifyEquals(browser.getText(cdp.gracePeriodView),gracePeriod);
		Verify.verifyEquals(browser.getText(cdp.timeZoneView),timeZone);
		log.info("Test 2 completed");
	}
}
