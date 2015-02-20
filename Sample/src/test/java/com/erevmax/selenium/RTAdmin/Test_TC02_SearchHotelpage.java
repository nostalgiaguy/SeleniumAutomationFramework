package com.erevmax.selenium.RTAdmin;

import static com.erevmax.selenium.PageObjects.ConfigDetailActionPage.cdp;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.erevmax.utils.Verify;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.erevmax.baseTest.TestBase;




public class Test_TC02_SearchHotelpage extends TestBase{ 

	String AddHotelChainName = "hyatt Hotels";
	String AddHotelHotelName = "testhotelname";
	String AddHotelHotelAddress = "testhoteladdress";
	String AddHotelCityName = "Kolkata";
	String AddHotelSortName = "tst";
	private final Logger log= Logger.getLogger(this.getClass().getName());

	@BeforeMethod(alwaysRun = true)
	public void setup() throws  IOException, InterruptedException {
		try{
			browser.click(cdp.configTab);	
			browser.findWebElement(cdp.hotelcodeTxtBox).clear();
			browser.findWebElement(cdp.hotelnameTxtBox).clear();
			browser.findWebElement(cdp.cityTxtBox).clear();
			browser.findWebElement(cdp.countryTxtBox).clear();
			browser.findWebElement(cdp.countryTxtBox).sendKeys(Keys.TAB);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			browser.elementWait(2000);
		}
	}


	@Test(priority = 20)
	public void countryajaxtest() throws InterruptedException, IOException {
		try{
			log.info("Test 1 Start.");
			browser.autoSelectFromAjaxList(cdp.countryTxtBox, cdp.countryAjaxOption,country);
			browser.elementWait(2000);
			Verify.verifyEquals(country,browser.getAttributeValue(cdp.countryTxtBox, "value"));
			
			log.info("Verified that ajax is enabled for the Country textbox");
			log.info("Test 1 completed.");
		}
		finally{
			File srcFile = ((TakesScreenshot)browser.getDriver()).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(srcFile, new File("F:\\Selenium STG1 screenshots\\TC02_SearchHotelPage\\countryajaxtest.jpg"));
		}

	}

	@Test(priority = 30)
	public void cityajaxtest() throws InterruptedException, IOException {

		try{
			log.info("Test 2 Start.");
			//cdp.populateCityNameAjaxfield(city);
			browser.enterInput(cdp.cityTxtBox,city);
			browser.waitForElementVisible(cdp.cityAjaxOption);
			browser.click(cdp.cityAjaxOption);		
			browser.doubleClickOn(cdp.cityAjaxOption);
			browser.elementWait(2000);
			Verify.verifyEquals(browser.getAttributeValue(cdp.cityTxtBox,"value"),city);
			Verify.verifyEquals(browser.getAttributeValue(cdp.countryTxtBox,"value"),country);
			log.info("Verified that ajax is enabled for the City textbox");
			log.info("Verified that Country is populated automatically once city is selected");
			log.info("Test 2 completed.");
		}
		finally{
			File srcFile = ((TakesScreenshot)browser.getDriver()).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(srcFile, new File("F:\\Selenium STG1 screenshots\\TC02_SearchHotelPage\\cityajaxtest.jpg"));
		}
	}

	@Test(priority = 40)
	public void hotelnameajaxtest() throws InterruptedException, IOException {
		
		try{
			log.info("Test 3 Start.");
			browser.autoSelectFromAjaxList(cdp.hotelnameTxtBox, cdp.hotelnameAjaxOption, hotelName);
			browser.elementWait(2000);
			Verify.verifyEquals(hotelCode+" ",browser.getAttributeValue(cdp.hotelcodeTxtBox,"value"));
			Verify.verifyEquals(hotelName,browser.getAttributeValue(cdp.hotelnameTxtBox,"value"));
			Verify.verifyEquals("London",browser.getAttributeValue(cdp.cityTxtBox,"value"));
			Verify.verifyEquals("United Kingdom",browser.getAttributeValue(cdp.countryTxtBox,"value"));    	

			log.info("Verified that ajax is enabled for the Hotel Name textbox\n");
			log.info("Verified that HotelCode,City and Country is populated automatically once HotelName is selected");
			log.info("Test 3 completed.");
		}
		finally{
			File srcFile = ((TakesScreenshot)browser.getDriver()).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(srcFile, new File("F:\\Selenium STG1 screenshots\\TC02_SearchHotelPage\\hotelnameajaxtest.jpg"));
		}
	}

	@Test(priority = 50)
	public void AddNewHoteltest() throws InterruptedException, IOException {
		
		String parentWindowHandle=null;
		try{
			log.info("Test 4 Start.");
			browser.findWebElement(cdp.addHotelChainLink).click();
			browser.elementWait(1000);

			 parentWindowHandle=browser.switchToNewlyOpenedWindow();
			browser.elementWait(4000);
			
			WebDriverWait wait4 = new WebDriverWait(browser.getDriver(), 50);
			wait4.until(ExpectedConditions.visibilityOfElementLocated(cdp.addHotelCityTxtBox));
			
			//Validate the buttons Save and Close,Save and Add and Reset are enabled.
			Verify.assertTrue(browser.isEnabled(cdp.addHotelsaveAndCloseButton));
			Verify.assertTrue(browser.isEnabled(cdp.addHotelsaveAndAddButton));
			Verify.assertTrue(browser.isEnabled(cdp.addHotelResetButton));

			browser.autoSelectFromAjaxList(cdp.addHotelChainNameTxtBox,cdp.addHotelChainNameTxtBoxajaxlist, AddHotelChainName);
			browser.enterInput(cdp.addHotelHotelNameTxtBox, AddHotelHotelName);	
			browser.enterInput(cdp.addHotelHotelAddressTxtBox, AddHotelHotelAddress);
			browser.autoSelectFromAjaxList(cdp.addHotelCityTxtBox, cdp.addHotelCityTxtBoxajaxlist, city);
			browser.enterInput(cdp.addHotelSortName, AddHotelSortName);

			//Click on the Reset button
			browser.findWebElement(cdp.addHotelResetButton).click();      
			WebDriverWait wait6 = new WebDriverWait(browser.getDriver(), 50);
			wait6.until(ExpectedConditions.visibilityOfElementLocated(cdp.addHotelCityTxtBox));

			Verify.verifyEquals(browser.getAttributeValue(cdp.addHotelChainNameTxtBox,"value"),"");
			Verify.verifyEquals(browser.getAttributeValue(cdp.addHotelHotelNameTxtBox,"value"),"");
			Verify.verifyEquals(browser.getAttributeValue(cdp.addHotelHotelAddressTxtBox,"value"),"");
			Verify.verifyEquals(browser.getAttributeValue(cdp.addHotelCityTxtBox,"value"),"");
			Verify.verifyEquals(browser.getAttributeValue(cdp.addHotelCountryTxtBox,"value"),"");
			Verify.verifyEquals(browser.getAttributeValue(cdp.addHotelSortName,"value"),"");

			log.info("Verified the labels and ajax fields of Add New Hotel window");
			log.info("Verified the Reset button function in Add New Hotel window");
			log.info("Test 4 completed.");
		}	      
		finally{
			File srcFile = ((TakesScreenshot)browser.getDriver()).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(srcFile, new File("F:\\Selenium STG1 screenshots\\TC02_SearchHotelPage\\Add New Hotel.jpg"));

		    browser.getDriver().close();        
		    browser.switchToOriginalWindow(parentWindowHandle);
		}         

	}


	@Test(priority = 60)
	public void AddNewCitytest() throws InterruptedException, IOException {

		String parentWindowHandle = null;

		
		try{
			log.info("Test 5 Start.");
			browser.click(cdp.addCityLink);
			browser.elementWait(1000);	
			
			 parentWindowHandle=browser.switchToNewlyOpenedWindow();
			browser.elementWait(8000);

			Verify.assertTrue(browser.isEnabled(cdp.addCitySaveAndCloseBtn));
			Verify.assertTrue(browser.isEnabled(cdp.addCitySaveAndAddBtn));
			Verify.assertTrue(browser.isEnabled(cdp.addCityResetBtn));

			cdp.populateAddCityForm();
			browser.findWebElement(cdp.addCityResetBtn).click();
			browser.elementWait(3000);

			//Verify all the text boxes are blanked out
			Verify.verifyEquals(browser.getAttributeValue(cdp.addCityNameTxtBox,"value"),"");
			Verify.verifyEquals(browser.getAttributeValue(cdp.addCityCodeTxtBox,"value"),"");
			Verify.verifyEquals(browser.getAttributeValue(cdp.addCityAirportCodeTxtBox,"value"),"");
			Verify.verifyEquals(browser.getAttributeValue(cdp.addCityVATPercentageTxtBox,"value"),"");
			Verify.verifyEquals(browser.getAttributeValue(cdp.addCityCountryTxtBox,"value"),"");
			Verify.verifyEquals(browser.getAttributeValue(cdp.addCityTaxPercentageTxtBox,"value"),"");
			Verify.verifyEquals(browser.getAttributeValue(cdp.addCityTaxAmountTxtBox,"value"),"");
			Verify.verifyEquals(browser.getAttributeValue(cdp.addCityPPersonPNIghtTxtBox,"value"),"");
			Verify.verifyEquals(browser.getAttributeValue(cdp.addCityPNightPRoomTxtBox,"value"),"");
			
			log.info("Verified the Reset button function in Add City window");
			log.info("Test 5 completed.");
		}
		finally{
			File srcFile = ((TakesScreenshot)browser.getDriver()).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(srcFile, new File("F:\\Selenium STG1 screenshots\\TC02_SearchHotelPage\\AddNewCitytest.jpg"));
			
		    browser.getDriver().close(); 
		    browser.switchToOriginalWindow(parentWindowHandle);
		}

	}

	@Test(priority = 70)
	public void AddNewCountrytest() throws InterruptedException, IOException {

		String parentWindowHandle =null; 

		//Click on AddCountry link
		try{
			log.info("Test 6 Start.");
			browser.click(cdp.addCountryLink);
			browser.elementWait(1000);	
			
			 parentWindowHandle=browser.switchToNewlyOpenedWindow();
			browser.elementWait(5000);

			//Validate the buttons Save and Reset are enabled.
			Verify.assertTrue(browser.isEnabled(cdp.addCountrySaveAndCloseBtn));
			Verify.assertTrue(browser.isEnabled(cdp.addCountrySaveAndAddBtn));
			Verify.assertTrue(browser.isEnabled(cdp.addCountryResetBtn));
			
			cdp.populateAddCountryForm();     
			browser.click(cdp.addCountryResetBtn);
			browser.elementWait(2000);

			//Verify all the text boxes are blanked out
			Verify.verifyEquals(browser.getAttributeValue(cdp.addCountryNameTxtBox,"value"),"");
			Verify.verifyEquals(browser.getAttributeValue(cdp.addCountryCodeATxtBox,"value"),"");
			Verify.verifyEquals(browser.getAttributeValue(cdp.addCountryCodeNTxtBox,"value"),"");
			Verify.verifyEquals(browser.getAttributeValue(cdp.addCountryCurrencyTxtBox,"value"),"");

			log.info("Verified the Reset button function in Add Country window");
			log.info("Test 6 completed.");
		}
		finally{
			File srcFile = ((TakesScreenshot)browser.getDriver()).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(srcFile, new File("F:\\Selenium STG1 screenshots\\TC02_SearchHotelPage\\AddNewCountrytest.jpg"));
			
		    browser.getDriver().close(); 
		    browser.switchToOriginalWindow(parentWindowHandle);
		}
	}

	@Test(priority = 80)
	public void requiredFieldMsgtest() throws InterruptedException, IOException {
		try{
			log.info("Test 7 Start.");
			browser.autoSelectFromAjaxList(cdp.cityTxtBox, cdp.cityAjaxOption, city);
			browser.click(cdp.continueButton);
			browser.elementWait(2000);

			Verify.verifyEquals(browser.getText(cdp.validationmsg),"Enter hotel code or hotel name in order to proceed");
			log.info("Verified the Required fields validation and error message in Search a Client Hotel page");
			log.info("Test 7 completed.");
		}
		finally{
			File srcFile = ((TakesScreenshot)browser.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File("F:\\Selenium STG1 screenshots\\TC02_SearchHotelPage\\requiredfieldmsgtest.jpg"));
		}
	}

	@Test(priority = 90)
	public void continue_clienthotelconfig_test() throws InterruptedException, IOException {
		try{
			log.info("Test 8 Start.");
			browser.autoSelectFromAjaxList(cdp.hotelcodeTxtBox,cdp.hotelcodeAjaxOption, hotelCode);
			browser.click(cdp.continueButton);
			//cdp.clickContinueButton();

			browser.elementWait(4000);
			Verify.verifyEquals(browser.getDriver().getTitle(),"RTAdmin - Client Hotel Configuration");	

			log.info("Verified the Continue button function in Search Client Hotel page");
			log.info("Verified the title of the browser in Client Hotel Configuration page");
			log.info("Test 8 completed.");
		}
		finally{
			File srcFile = ((TakesScreenshot)browser.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File("F:\\Selenium STG1 screenshots\\TC02_SearchHotelPage\\continue_clienthotelconfig_test.jpg"));
		}
	}

	@AfterMethod(alwaysRun = true)
	public void teardown()throws InterruptedException{

		browser.elementWait(2000);
	}

}
