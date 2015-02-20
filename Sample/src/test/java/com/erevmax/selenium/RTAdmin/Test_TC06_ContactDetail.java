package com.erevmax.selenium.RTAdmin;


import com.erevmax.utils.Verify;

import static com.erevmax.selenium.PageObjects.ConfigDetailActionPage.cdp;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.erevmax.baseTest.TestBase;

public class Test_TC06_ContactDetail extends TestBase{

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
	
	 @DataProvider(name="contactDetail") 
	  public static Object[][] contactDetailData() {
	        return new Object[][] { { "Anis_test_fname", "Anis_test_lname","anisa.test@erevmax.com","anis_pwd_test","RESERVATION_FAILURE"},
	        		{ "Anis", "Ansari","anisa@erevmax.com","anis_test","NO_RESERVATION_FAILURE" }};
	  }
	 
	@Test(priority = 10,dataProvider="contactDetailData")
	public void SaveContactandMemberDetail1_Test(String mem_fname,String mem_lname,String mem_email,String mem_pwd,String Notif_Type) 
			throws InterruptedException{

		log.info("Test 2 Start");
		String parentwindow=null;

		browser.elementWait(4000);
		browser.click(cdp.editMember1Link);
		browser.elementWait(2000);
		parentwindow = browser.switchToNewlyOpenedWindow();
		cdp.editMemberDetails(mem_fname, mem_lname, mem_email,mem_pwd,Notif_Type);
		browser.elementWait(2000);
		browser.click(cdp.editMember_SaveBtn);
		browser.waitForAlert();
		if(browser.isAlertPresent()==true){
			Verify.verifyEquals("Configuration has been saved successfully.", browser.checkTextAlertMessage());
		}
		browser.switchToOriginalWindow(parentwindow);
		browser.elementWait(2000);		
		
		Verify.verifyEquals(browser.getText(cdp.Contact1_FName),mem_fname);
		Verify.verifyEquals(browser.getText(cdp.Contact1_LName),mem_lname);
		Verify.verifyEquals(browser.getText(cdp.Contact1_Email),mem_email);
		Verify.verifyEquals(browser.getText(cdp.Contact1_pwd),mem_pwd);

		String Notification_Detail = browser.getText(cdp.Contact1_Notifs);		
		Verify.verifyEquals(true, Notification_Detail.toLowerCase().contains(Notif_Type.toLowerCase()));
		log.info("Test 2 completed");								
	}
	
}
		
		
	


