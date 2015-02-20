package com.erevmax.selenium.RTAdmin;


import org.apache.log4j.Logger;

import static com.erevmax.selenium.PageObjects.ConfigDetailActionPage.cdp;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.erevmax.baseTest.TestBase;
import com.erevmax.utils.Verify;

public class Test_TC11_Preferences_RTConnect extends TestBase{

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
	@DataProvider(name="RTConnect")
	public static Object[][] RTConnectData(){
		return new Object[][] {{"Yes","Yes","Yes","Yes","Yes","Enabled","Yes","2","1",true},
				{"No","No","No","No","No","Disabled","No","1","0",false}};
	}

	@Test(priority = 10,dataProvider="RTConnectData")
	public void RTConnectPrefSave1(String allowOverBking,String enableResPMSStorage, String overrideAMLogic,String deliverAllRes,String storeDatainPMSDBatChannelLevel,String enableDeltaProcessing,String resyncwithPMSDB,String applyMultiLeg,String PMSCRSdataStorageLogic,boolean flag) throws InterruptedException {
		log.info("Test 2 Start");
		String parentwindow=null;
		try{

			browser.waitForElementClickable(cdp.editPreferencesRTConnectBtn);	
			browser.click(cdp.editPreferencesRTConnectBtn);
			browser.waitForNumberOfWindowsToEqual(2);
			parentwindow = browser.switchToNewlyOpenedWindow();
			browser.elementWait(3000);

			cdp.populateRTConnectPref(storeDatainPMSDBatChannelLevel,allowOverBking, applyMultiLeg, overrideAMLogic, deliverAllRes, enableDeltaProcessing, resyncwithPMSDB, applyMultiLeg, PMSCRSdataStorageLogic);

			browser.elementWait(2000);
			browser.click(cdp.SavePreferencesRTConnectBtn);
			browser.elementWait(3000);
			browser.switchToOriginalWindow(parentwindow);
			

			Verify.verifyEquals(browser.getText(cdp.allowOverbookingView), allowOverBking);
			Verify.verifyEquals(browser.getText(cdp.overrideAMLogicView), overrideAMLogic);
			Verify.verifyEquals(browser.getText(cdp.PMSCRSdataStorageLogicView), PMSCRSdataStorageLogic);
			Verify.verifyEquals(browser.getText(cdp.applyMultilegView), applyMultiLeg);
			Verify.verifyEquals(browser.getText(cdp.deltaProcessingView), enableDeltaProcessing);
			if(flag){
				Verify.verifyEquals(browser.getText(cdp.resyncWithPMSDBView), resyncwithPMSDB);
			}
			Verify.verifyEquals(browser.getText(cdp.storeDatainPMSDBatChannelLevelView), storeDatainPMSDBatChannelLevel);
			log.info("Test 2 completed");
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				//parentwindow = browser.switchToNewlyOpenedWindow();
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentwindow);	
			}
		}

	}
	//Verify the Contextual help link is working
	@Test(priority = 30)
	public void ContextualHelp () throws InterruptedException {
		log.info("Test 3 Start");
		String parentwindow=null;
		String parentwindow1=null;
		try{

			browser.waitForElementClickable(cdp.editPreferencesRTConnectBtn);
			//browser.elementWait(2000);
			//parentwindow = getParentWindowHandle();
			browser.click(cdp.editPreferencesRTConnectBtn);
			browser.waitForNumberOfWindowsToEqual(2);
			parentwindow=browser.switchToNewlyOpenedWindow();
			//switchToPopupWindow();
			browser.elementWait(2000);
			//parentwindow1 = getParentWindowHandle();
			browser.click(cdp.helpIcon);
			browser.waitForNumberOfWindowsToEqual(3);
			parentwindow1=browser.switchToNewlyOpenedWindow();

			Verify.verifyEquals(browser.getText(cdp.contlHelpHeader),"RTConnect Preferences Contextual Help");
			browser.elementWait(1000);
			browser.getDriver().close();
			//switchToParentWindow(parentwindow1);
			browser.switchToOriginalWindow(parentwindow1);
			browser.elementWait(1000);
			log.info("Test 3 completed");

		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				//parentwindow = browser.switchToNewlyOpenedWindow();
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentwindow);
			}
		}

	}

	//Verify Store data in PMSDBatChannelLevel radio btn is enabled only when PMS/CRS Data Storage Logic is 1.
	@Test(priority = 40)
	public void PMSDBatChannelLevelRadioBtnStateTest () throws InterruptedException {
		log.info("Test 4 Start");
		String parentwindow=null;
		try{

			browser.waitForElementClickable(cdp.editPreferencesRTConnectBtn);	
			browser.click(cdp.editPreferencesRTConnectBtn);
			browser.waitForNumberOfWindowsToEqual(2);
			parentwindow = browser.switchToNewlyOpenedWindow();
			browser.elementWait(3000);

			for(int i=0; i<4;i++){
				browser.selectFromDropDownList(cdp.PMSCRSdataStorageLogicList,String.valueOf(i));
				browser.elementWait(1000);
				if(i==0){
					Verify.verifyEquals(browser.isEnabled(cdp.storeDatainPMSDBatChannelLevelYesRadio), false);
					Verify.verifyEquals(browser.isEnabled(cdp.storeDatainPMSDBatChannelLevelNoRadio), false);
				}

				else if(i==1){
					Verify.verifyEquals(browser.isEnabled(cdp.storeDatainPMSDBatChannelLevelYesRadio), true);
					Verify.verifyEquals(browser.isEnabled(cdp.storeDatainPMSDBatChannelLevelNoRadio), true);
				}

				else if(i==2){
					Verify.verifyEquals(browser.isEnabled(cdp.storeDatainPMSDBatChannelLevelYesRadio), false);
					Verify.verifyEquals(browser.isEnabled(cdp.storeDatainPMSDBatChannelLevelNoRadio), false);
				}

				else if(i==3){
					Verify.verifyEquals(browser.isEnabled(cdp.storeDatainPMSDBatChannelLevelYesRadio), false);
					Verify.verifyEquals(browser.isEnabled(cdp.storeDatainPMSDBatChannelLevelNoRadio), false);
				}

			}
			log.info("Test 4 completed");
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				//parentwindow = browser.switchToNewlyOpenedWindow();
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentwindow);	
			}
		}

	}	

}
