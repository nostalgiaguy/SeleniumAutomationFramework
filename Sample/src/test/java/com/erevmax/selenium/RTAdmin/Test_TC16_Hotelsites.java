package com.erevmax.selenium.RTAdmin;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.erevmax.baseTest.TestBase;
import com.erevmax.utils.Verify;

public class Test_TC16_Hotelsites extends TestBase{
	
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

	@Test(priority = 10)	
	public void HotelSiteViewTest() throws InterruptedException {
		//		new OpenConfigTab(browser.getDriver(), hotelCode);
		log.info("Test 2 Start");
		String parentWindowHandle=null;
		browser.elementWait(5000);

		WebDriverWait wait = new WebDriverWait(browser.getDriver(), 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='DIV_357291_0']/td/table[1]/tbody/tr[1]/td/span/strong")));

		Verify.verifyEquals(" Hotelsites",browser.getText(By.xpath(".//*[@id='DIV_357291']/td/table/tbody/tr/td[2]")));
		Verify.verifyEquals("Save",browser.getText(By.xpath(".//*[@id='DIV_357291_0']/td/table[3]/tbody/tr[2]/td[2]/table/tbody/tr/td[1]/a")));
		Verify.verifyEquals("Reset",browser.getText(By.xpath(".//*[@id='DIV_357291_0']/td/table[3]/tbody/tr[2]/td[2]/table/tbody/tr/td[2]/a")));
		Verify.verifyEquals("Click on the hotelsite name to modify it.",browser.getText(By.xpath(".//*[@id='DIV_357291_0']/td/table[1]/tbody/tr[1]/td")));
		Verify.verifyEquals("Shopper",browser.getText(By.xpath(".//*[@id='DIV_357291_0']/td/table[1]/tbody/tr[2]/td/table/tbody/tr/td[1]/strong")));
		Verify.verifyEquals("Corp",browser.getText(By.xpath(".//*[@id='DIV_357291_0']/td/table[1]/tbody/tr[2]/td/table/tbody/tr/td[2]")));
		Verify.verifyEquals("Hotel Name",browser.getText(By.xpath(".//*[@id='DIV_357291_0']/td/table[2]/tbody/tr[1]/td/table/tbody/tr/td[1]/span")));
		Verify.verifyEquals("Hotel Code",browser.getText(By.xpath(".//*[@id='DIV_357291_0']/td/table[2]/tbody/tr[1]/td/table/tbody/tr/td[2]/span")));
		Verify.verifyEquals("Hotelsite Name",browser.getText(By.xpath(".//*[@id='DIV_357291_0']/td/table[2]/tbody/tr[1]/td/table/tbody/tr/td[3]")));
		Verify.verifyEquals(" Shopping Preferences",browser.getText(By.xpath(".//*[@id='DIV_357291_0']/td/table[3]/tbody/tr[1]/td[2]/a[1]")));

		Verify.verifyEquals("LaSuiteHotel",browser.getAttributeValue(By.xpath(".//*[@id='txtTSDName_6909300_43013_Shopper']"),"value"));
		Verify.verifyEquals("GreenHillsHotel",browser.getAttributeValue(By.xpath(".//*[@id='txtTSDName_6946800_41846_Shopper']"),"value"));
		Verify.verifyEquals("Txtbox-StarTwo",browser.getAttributeValue(By.xpath(".//*[@id='txtTSDName_6946800_41846_Shopper']"),"class"));

		browser.click(By.xpath(".//*[@id='DIV_357291_0']/td/table[3]/tbody/tr[1]/td[2]/a[1]"));
		browser.elementWait(4000);

		try{
			parentWindowHandle =browser.switchToNewlyOpenedWindow();
			Verify.verifyEquals("Shopping Preferences - WebL/SNG-XML, Client-side/Server-side, IP Anonymization",browser.getText(By.xpath("html/body/table[2]/tbody/tr[1]/td")));

			Verify.verifyEquals("Auto RTSuite Hotel",browser.getText(By.xpath(".//*[@id='demoTable']/tbody/tr[2]/td[1]")));
			Verify.verifyEquals("DEMO HOTEL TESTING",browser.getText(By.xpath(".//*[@id='demoTable']/tbody/tr[3]/td[1]")));

			Verify.verifyEquals("Hotel Name",browser.getText(By.xpath(".//*[@id='demoTable']/tbody/tr/td[1]")));
			Verify.verifyEquals("Hotelsite Name",browser.getText(By.xpath(".//*[@id='demoTable']/tbody/tr/td[2]")));
			Verify.verifyEquals("Shop by SNG-XML",browser.getText(By.xpath(".//*[@id='demoTable']/tbody/tr/td[3]")));
			Verify.verifyEquals("Server-side Shopping",browser.getText(By.xpath(".//*[@id='demoTable']/tbody/tr/td[4]")));
			Verify.verifyEquals("IPA",browser.getText(By.xpath(".//*[@id='demoTable']/tbody/tr/td[5]")));
			Verify.verifyEquals("Paid Channel",browser.getText(By.xpath(".//*[@id='demoTable']/tbody/tr/td[6]")));
			Verify.verifyEquals("Shop Limit",browser.getText(By.xpath(".//*[@id='demoTable']/tbody/tr/td[7]")));  


			Verify.verifyEquals(false,browser.isEnabled(By.xpath(".//*[@id='69093:6909300' and @name='shopEnabled']")));    
			Verify.verifyEquals(true,browser.isEnabled(By.xpath(".//*[@id='69468:6946800' and @name='shopEnabled']"))); 

			Verify.verifyEquals(true,browser.isEnabled(By.xpath(".//*[@id='69093:6909300' and @name='serverEnabled']")));
			Verify.verifyEquals(true,browser.isEnabled(By.xpath(".//*[@id='69093:6909300' and @name='ipAnonyEnabled']")));
			Verify.verifyEquals(true,browser.isEnabled(By.xpath(".//*[@id='69093:6909300' and @name='paidChannelEnabled']")));

			Verify.verifyEquals(true,browser.isEnabled(By.xpath(".//*[@id='69468:6946800' and @name='serverEnabled']")));
			Verify.verifyEquals(true,browser.isEnabled(By.xpath(".//*[@id='69468:6946800' and @name='ipAnonyEnabled']")));
			Verify.verifyEquals(true,browser.isEnabled(By.xpath(".//*[@id='69468:6946800' and @name='paidChannelEnabled']")));

			//Verify Shop Limit is disabled when paid channel is unselected
			if(browser.isSelected(By.xpath("//*[@id='69093:6909300' and @name='paidChannelEnabled']"))== true){
				browser.click(By.xpath("//*[@id='69093:6909300' and @name='paidChannelEnabled']"));

				Verify.verifyEquals("input_L_disabled",browser.getAttributeValue(By.xpath(".//*[@id='shopLimit_69093:6909300']"),"class"));

			}

			if(browser.isSelected(By.xpath("//*[@id='69468:6946800' and @name='paidChannelEnabled']"))== true){
				browser.click(By.xpath("//*[@id='69468:6946800' and @name='paidChannelEnabled']"));
				Verify.verifyEquals("input_L_disabled",browser.getAttributeValue(By.xpath(".//*[@id='shopLimit_69468:6946800']"),"class"));
			}	 

			//Verify that Shop Limit gets enabled when Paid channel checkbox is selected
			browser.click(By.xpath(".//*[@id='69093:6909300' and @name='paidChannelEnabled']"));
			Verify.verifyEquals("input_L",browser.getAttributeValue(By.xpath(".//*[@id='shopLimit_69093:6909300']"),"class"));

			//Verify that Server-side Shopping checkbox gets selected when IPA checkbox is selected
			browser.click(By.xpath(".//*[@id='69093:6909300' and @name='ipAnonyEnabled']"));
			Verify.verifyEquals(true,browser.isSelected(By.xpath(".//*[@id='69093:6909300' and @name='serverEnabled']")));
			log.info("Test 2 completed");
		}
		finally{
			browser.getDriver().close(); 	         
			browser.switchToOriginalWindow(parentWindowHandle);
		}
	}

	@Test(priority = 20)	
	public void ShoppingPrefSaveTest() throws InterruptedException {
		//		new OpenConfigTab(browser.getDriver(), hotelCode);
		log.info("Test 3 Start");
		browser.elementWait(5000);

		WebDriverWait wait = new WebDriverWait(browser.getDriver(), 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='DIV_357291_0']/td/table[1]/tbody/tr[1]/td/span/strong")));

		String parentWindowHandle =null;
		browser.click(By.xpath(".//*[@id='DIV_357291_0']/td/table[3]/tbody/tr[1]/td[2]/a[1]"));
		browser.elementWait(4000);


		parentWindowHandle=browser.switchToNewlyOpenedWindow();

		try{
			if(browser.isSelected(By.xpath(".//*[@id='69093:6909300' and @name='ipAnonyEnabled']"))== true){
				browser.click(By.xpath(".//*[@id='69093:6909300' and @name='ipAnonyEnabled']"));
			} 

			if(browser.isSelected(By.xpath(".//*[@id='69468:6946800' and @name='ipAnonyEnabled']"))==true){
				browser.click(By.xpath(".//*[@id='69468:6946800' and @name='ipAnonyEnabled']")); 
			}

			browser.click(By.xpath("html/body/table[2]/tbody/tr[2]/td/table/tbody/tr[4]/td/table/tbody/tr/td[1]/a"));
			browser.elementWait(2000);



			if(browser.isSelected(By.xpath(".//*[@id='69093:6909300' and @name='serverEnabled']")) == false){
				browser.click(By.xpath(".//*[@id='69093:6909300' and @name='serverEnabled']")); 
			}

			if(browser.isSelected(By.xpath(".//*[@id='69093:6909300' and @name='ipAnonyEnabled']"))== false){
				browser.click(By.xpath(".//*[@id='69093:6909300' and @name='ipAnonyEnabled']"));
			}

			if(browser.isSelected(By.xpath(".//*[@id='69093:6909300' and @name='paidChannelEnabled']"))== false){
				browser.click(By.xpath(".//*[@id='69093:6909300' and @name='paidChannelEnabled']"));
			}

			if(browser.isSelected(By.xpath(".//*[@id='69468:6946800' and @name='serverEnabled']"))==false){
				browser.click(By.xpath(".//*[@id='69468:6946800' and @name='serverEnabled']")); 
			}

			if(browser.isSelected(By.xpath(".//*[@id='69468:6946800' and @name='ipAnonyEnabled']"))==false){
				browser.click(By.xpath(".//*[@id='69468:6946800' and @name='ipAnonyEnabled']")); 
			}

			if(browser.isSelected(By.xpath(".//*[@id='69468:6946800' and @name='paidChannelEnabled']"))==false){
				browser.click(By.xpath(".//*[@id='69468:6946800' and @name='paidChannelEnabled']"));
			}


		
			browser.enterInput(By.xpath(".//*[@id='shopLimit_69093:6909300']"),"50");
			browser.enterInput(By.xpath(".//*[@id='shopLimit_69468:6946800']"), "100");
	
			browser.click(By.xpath("html/body/table[2]/tbody/tr[2]/td/table/tbody/tr[4]/td/table/tbody/tr/td[1]/a"));

			browser.elementWait(2000);

			String alertText=browser.checkTextAlertMessage();
			Verify.verifyEquals("IP anonymization is a paid service. Please ensure that the enabling of IP anonymization for this client-channel combination is approved from management. Do you want to proceed?", alertText);

			browser.elementWait(1000);
			browser.getDriver().close();
			browser.elementWait(1000);
			browser.switchToOriginalWindow(parentWindowHandle);

			browser.click(By.xpath(".//*[@id='DIV_357291_0']/td/table[3]/tbody/tr[1]/td[2]/a[1]"));
			browser.elementWait(4000);

			browser.switchToNewlyOpenedWindow();
			Verify.verifyEquals(true,browser.isSelected(By.xpath(".//*[@id='69093:6909300' and @name='serverEnabled']")));
			Verify.verifyEquals(true,browser.isSelected(By.xpath(".//*[@id='69093:6909300' and @name='ipAnonyEnabled']")));
			Verify.verifyEquals(true,browser.isSelected(By.xpath(".//*[@id='69093:6909300' and @name='paidChannelEnabled']")));

			Verify.verifyEquals(true,browser.isSelected(By.xpath(".//*[@id='69468:6946800' and @name='serverEnabled']")));
			Verify.verifyEquals(true,browser.isSelected(By.xpath(".//*[@id='69468:6946800' and @name='ipAnonyEnabled']")));
			Verify.verifyEquals(true,browser.isSelected(By.xpath(".//*[@id='69468:6946800' and @name='paidChannelEnabled']")));

			Verify.verifyEquals("50",browser.getAttributeValue(By.xpath(".//*[@id='shopLimit_69093:6909300']"),"value"));
			Verify.verifyEquals("100",browser.getAttributeValue(By.xpath(".//*[@id='shopLimit_69468:6946800']"),"value"));

			//Restore checkbox selections as to initial state
			browser.click(By.xpath(".//*[@id='69093:6909300' and @name='paidChannelEnabled']"));	 	  
			browser.click(By.xpath(".//*[@id='69093:6909300' and @name='ipAnonyEnabled']"));
			browser.click(By.xpath(".//*[@id='69093:6909300' and @name='serverEnabled']"));

			browser.click(By.xpath(".//*[@id='69468:6946800' and @name='paidChannelEnabled']"));
			browser.click(By.xpath(".//*[@id='69468:6946800' and @name='ipAnonyEnabled']"));
			browser.click(By.xpath(".//*[@id='69468:6946800' and @name='serverEnabled']")); 

			browser.click(By.xpath("html/body/table[2]/tbody/tr[2]/td/table/tbody/tr[4]/td/table/tbody/tr/td[1]/a"));	 
			browser.elementWait(2000);
			log.info("Test 3 completed");
		}
		finally{
			browser.getDriver().close();	         
			browser.switchToOriginalWindow(parentWindowHandle);
		}
	}	
}
