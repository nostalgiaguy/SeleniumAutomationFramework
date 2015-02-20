package com.erevmax.selenium.RTAdmin;


import org.apache.log4j.Logger;

import static com.erevmax.selenium.PageObjects.ConfigDetailActionPage.cdp;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.erevmax.baseTest.TestBase;
import com.erevmax.utils.CustomTestListener;
import com.erevmax.utils.Verify;
@Listeners(CustomTestListener.class)
public class Test_TC12_Preferences_Reservation extends TestBase{

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
	@DataProvider(name="Reservation")
	public static Object[][] ReservationData(){
		return new Object[][] {{"Enabled","Yes","90","Yes"},{"Disabled","No","1","No"},{"3PI","No","30","Yes"}};
	}

	@Test(priority = 10,dataProvider="ReservationData")
	public void ReservationPrefSave1(String enCC,String migrateGRType,String retainCCDays,String displayCCInfo) throws InterruptedException {
		log.info("Test 2 Start");
		String parentwindow=null;
		try{
			browser.elementWait(3000);
		//browser.waitForElementClickable(cdp.editPreferencesReservationBtn);	
		browser.click(cdp.editPreferencesReservationBtn);
		browser.waitForNumberOfWindowsToEqual(2);
		parentwindow = browser.switchToNewlyOpenedWindow();
		browser.elementWait(3000);
		cdp.populateReservationPref(enCC, migrateGRType, retainCCDays, displayCCInfo);
		browser.elementWait(2000);
		browser.click(cdp.SavePreferencesReservationBtn);
		browser.elementWait(6000);
		browser.switchToOriginalWindow(parentwindow);
		
		Verify.verifyEquals(browser.getText(cdp.enCCView), enCC);
		Verify.verifyEquals(browser.getText(cdp.isGenericRoomTypeMigratedView), migrateGRType);
		Verify.verifyEquals(browser.getText(cdp.retainCCNoForView), retainCCDays+" days");
		Verify.verifyEquals(browser.getText(cdp.displayCCInfoView), displayCCInfo);
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
	
	@Test(priority = 40)
	public void CCValidationError() throws InterruptedException {
		log.info("Test 3 Start");
		String parentwindow=null;
		String alertTxt=null;
		try{
		
		browser.waitForElementClickable(cdp.editPreferencesReservationBtn);	
		browser.click(cdp.editPreferencesReservationBtn);
		browser.waitForNumberOfWindowsToEqual(2);
		parentwindow = browser.switchToNewlyOpenedWindow();
		browser.elementWait(3000);
	
		browser.enterInput(cdp.retainCCNoForDaysTxtBox,"91");
		browser.click(cdp.SavePreferencesReservationBtn);
		
		browser.waitForAlert();
		alertTxt = browser.checkTextAlertMessage();
		
		
		Verify.verifyEquals(alertTxt, "You cannot enter more than 90 days");
		log.info("Test 3 completed");
		}
		
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentwindow);	
			}
		}
		
	}

}
