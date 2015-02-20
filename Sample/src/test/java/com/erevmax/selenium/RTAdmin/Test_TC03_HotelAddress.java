package com.erevmax.selenium.RTAdmin;

import java.io.IOException;

import com.erevmax.utils.Verify;

import org.apache.log4j.Logger;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.erevmax.baseTest.TestBase;

import static com.erevmax.selenium.PageObjects.ConfigDetailActionPage.cdp;

public class Test_TC03_HotelAddress extends TestBase{

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
	
	 @DataProvider(name="hotelData1") 
	  public static Object[][] hotelData() {
	        return new Object[][] { { "Time Square", "London","UK","United Kingdom","400051", "8"},
	        		{ "Salt Lake", "Kolkata","WB","India","700021","16" }};
	  }
	 
	@Test(priority = 10,dataProvider ="hotelData" )
	public void HotelAddressSave1(String hotelAddress,String hotelCity,String HotelState,String HotelCountry,String HotelzipCode,String roomCount) throws InterruptedException, IOException {
		log.info("Test 2 Start");
		String parentwindow=null;

		browser.elementWait(4000);
		browser.click(cdp.editHotelAddressBtn);
		browser.elementWait(2000);
		parentwindow = browser.switchToNewlyOpenedWindow();

		cdp.populateHotelAddress(hotelAddress, hotelCity, HotelState, HotelCountry, HotelzipCode,roomCount);
		browser.elementWait(2000);
		
		browser.click(cdp.SaveHotelAddressBtn);
		browser.elementWait(2000);
		browser.switchToOriginalWindow(parentwindow);
		browser.elementWait(2000);
		
		Verify.verifyEquals(browser.getText(cdp.hotelNameCodeDisplay), hotelName+"  |  Hotel Code: "+hotelCode);
		Verify.verifyEquals(browser.getText(cdp.hotelAddressDisplay),hotelAddress+", "+hotelCity+", "+HotelState+" , "+HotelCountry+" , "+HotelzipCode);
		log.info("Test 2 completed");
		
	}
	
	 @DataProvider(name="hotelData2") 
	  public static Object[][] missingHotelData() {
	        return new Object[][] { { "Salt Lake", "Kolkata","WB","India","700021","" }};        		
	  }
	 
	@Test(priority = 30,dataProvider="missingHotelData")
	public void HotelRoomCountMandatoryTest(String hotelAddress,String hotelCity,String HotelState,String HotelCountry,String HotelzipCode,String roomCount) throws InterruptedException {
		log.info("Test 3 Start");
		String parentwindow=null;
		try{
			browser.elementWait(4000);
			browser.click(cdp.editHotelAddressBtn);
			browser.elementWait(2000);
			parentwindow = browser.switchToNewlyOpenedWindow();

			cdp.populateHotelAddress(hotelAddress, hotelCity, HotelState, HotelCountry, HotelzipCode,roomCount);
			browser.elementWait(2000);
			
			browser.click(cdp.SaveHotelAddressBtn);
			browser.waitForElementVisible(cdp.errorMsgLabel);

			Verify.verifyEquals(browser.getText(cdp.errorMsgLabel), "Room Count is not valid!");
			log.info("Test 3 completed");
		}
		finally{
			browser.switchToOriginalWindow(parentwindow);
		}
	}
	
}
