package com.erevmax.selenium.RTAdmin;

import static com.erevmax.selenium.PageObjects.ConfigDetailActionPage.cdp;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.erevmax.baseTest.TestBase;
import com.erevmax.utils.Verify;


public class Test_TC08_Preferences_Shopper extends TestBase{
	
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

	 @DataProvider(name="shopper") 
	  public static Object[][] shopperData() {
	        return new Object[][] { { "6", "3","30","INR","O", "600","3000","No"},
	        		{ "2", "1","45","USD","G", "500","4000","Yes"}};
	 }

	 @Test(priority = 10,dataProvider="shopperData")
	 public void ShopperPrefSave1(String LOS,
			 String Occupancy,
			 String noDaysinReport,
			 String Currency,
			 String rateType,
			 String IPAShopLimit,
			 String PPShopLimit,
			 String throttlingApplicable) throws InterruptedException {
		 
		 log.info("Test 2 Start");
		 String parentwindow=null;
		 try{

			browser.waitForElementClickable(cdp.editPreferencesShopperBtn);
			browser.click(cdp.editPreferencesShopperBtn);
			browser.waitForNumberOfWindowsToEqual(2);
			parentwindow = browser.switchToNewlyOpenedWindow();
			browser.elementWait(3000);
			cdp.populateShopperPref(LOS, Occupancy, noDaysinReport, Currency, rateType, IPAShopLimit, PPShopLimit, throttlingApplicable);
			browser.elementWait(2000);
			browser.click(cdp.SavePreferencesShopperBtn);
			browser.elementWait(2000);
			if(browser.isAlertPresent()){
				browser.checkTextAlertMessage();
			}
			browser.waitForNumberOfWindowsToEqual(1);

			browser.switchToOriginalWindow(parentwindow);
			browser.elementWait(2000);

			Verify.verifyEquals(browser.getText(cdp.LOSView), LOS);
			Verify.verifyEquals(browser.getText(cdp.occupancyView), Occupancy);
			Verify.verifyEquals(browser.getText(cdp.noDaysInReport), noDaysinReport);
			Verify.verifyEquals(browser.getText(cdp.currencyView), Currency);
			Verify.verifyEquals(browser.getText(cdp.rateTypeView), rateType);
			Verify.verifyEquals(browser.getText(cdp.ipaShopLimitView), IPAShopLimit);
			Verify.verifyEquals(browser.getText(cdp.ppShopLimitView), PPShopLimit);
			Verify.verifyEquals(browser.getText(cdp.throttlingApplicableView), throttlingApplicable);
			log.info("Test 2 completed.");
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				parentwindow = browser.switchToNewlyOpenedWindow();
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentwindow);		
			}
		}

	}

}
