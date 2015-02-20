package com.erevmax.selenium.RTAdmin;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.erevmax.baseTest.TestBase;
import com.erevmax.utils.CustomTestListener;
import com.erevmax.utils.Verify;
//@Listeners(CustomTestListener.class)
public class Test_TC17_RTCorpConfigView extends TestBase {
	private final Logger log= Logger.getLogger(this.getClass().getName());
	/*static String baseUrl;
	static String userid;
	static String pwd;
	static WebDriver driver;
	static String hotelCode;
	static String hotelName;
	static String city;
	static String country;
	static String hotelAddress;
	static String address;*/

	@Test(priority = 0)
	public void navigateConfigDetailActionPage(){
		log.info("Test 1 Start");
		browser.click(xpath_repository.CONFIG_TAB);
		log.info("Current url is :"+browser.getCurrentUrl());
		browser.autoSelect(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX, xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX_AJAXLISTOPTION, hotelCode);
		if(!browser.getText(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX).equals(hotelCode)){
			browser.autoSelect(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX, xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX_AJAXLISTOPTION, hotelCode);
		}
		browser.click(xpath_repository.CLIENTHOTEL_SEARCH_CONTINUE_BUTTON);
		log.info("Next Current url is :" +browser.getCurrentUrl());
		int status =browser.sendHttpGetRequest(browser.getCurrentUrl());
		Verify.verifyEquals(status,200);
		log.info("Test 1 completed");
	}
	/*
	@BeforeClass
	@Parameters("browser")
	public static void openRTAdmin(@Optional("FF") String browser) throws BiffException, IOException, InterruptedException {

		Sheet s;        
		FileInputStream fi = new FileInputStream(xpath_repository.DATAXLS_PATH);
		Workbook w = Workbook.getWorkbook(fi);
		s = w.getSheet(0);
		baseUrl = s.getCell(1, 0).getContents();
		userid = s.getCell(1, 1).getContents();
		pwd = s.getCell(1, 2).getContents();
		hotelCode = s.getCell(2, 3).getContents();
		hotelName = s.getCell(2, 4).getContents();
		city = s.getCell(1, 5).getContents();
		country = s.getCell(1, 6).getContents();
		hotelAddress = s.getCell(1, 7).getContents();
		address = s.getCell(1, 8).getContents();

		if(browser.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("FF")){
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.get(baseUrl);
		
		WebElement username = browser.findWebElement(By.xpath(xpath_repository.LOGINPAGE_USERNAME_TXTBOX));
		username.sendKeys(userid);
		WebElement password = browser.findWebElement(By.xpath(xpath_repository.LOGINPAGE_PASSWORD_TXTBOX));
		password.sendKeys(pwd);
		browser.findWebElement(By.xpath(xpath_repository.LOGINPAGE_LOGIN_BUTTON)).click(); 

		//Click on the Config tab
		browser.findWebElement(By.xpath(xpath_repository.CONFIG_TAB)).click(); 
		WebDriverWait wait1 = new WebDriverWait(driver, 50);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX)));
		Thread.sleep(5000);
		
		browser.findWebElement(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX)).sendKeys(hotelCode.substring(0, 1));
		browser.findWebElement(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX)).sendKeys(hotelCode.substring(1, 2));
		browser.findWebElement(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX)).sendKeys(hotelCode.substring(2, 3));
		browser.findWebElement(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX)).sendKeys(hotelCode.substring(3, 4));
		browser.findWebElement(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX)).sendKeys(hotelCode.substring(4, 5));
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX_AJAXLISTOPTION)));
		Verify.verifyEquals(hotelCode,browser.findWebElement(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX_AJAXLISTOPTION)).getText());         
		browser.findWebElement(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX_AJAXLISTOPTION)).click();
		//Click on Continue button
		browser.findWebElement(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_CONTINUE_BUTTON)).click();
		Thread.sleep(15000);
	}

	@BeforeMethod(alwaysRun = true)
	public void openConfigtab() throws BiffException, IOException, InterruptedException {
//		WebElement username = browser.findWebElement(By.xpath(xpath_repository.LOGINPAGE_USERNAME_TXTBOX));
//		username.sendKeys(userid);
//		WebElement password = browser.findWebElement(By.xpath(xpath_repository.LOGINPAGE_PASSWORD_TXTBOX));
//		password.sendKeys(pwd);
//		browser.findWebElement(By.xpath(xpath_repository.LOGINPAGE_LOGIN_BUTTON)).click(); 
//
//		//Click on the Config tab
//		browser.findWebElement(By.xpath(xpath_repository.CONFIG_TAB)).click(); 
//		TimeUnit.SECONDS.sleep(6);
//		
//		browser.findWebElement(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX)).sendKeys(hotelCode.substring(0, 1));
//		browser.findWebElement(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX)).sendKeys(hotelCode.substring(1, 2));
//		browser.findWebElement(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX)).sendKeys(hotelCode.substring(2, 3));
//		browser.findWebElement(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX)).sendKeys(hotelCode.substring(3, 4));
//		browser.findWebElement(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX)).sendKeys(hotelCode.substring(4, 5));
//		
//		WebDriverWait wait = new WebDriverWait(driver, 50);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX_AJAXLISTOPTION)));
//		Verify.verifyEquals(hotelCode,browser.findWebElement(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX_AJAXLISTOPTION)).getText());         
//		browser.findWebElement(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX_AJAXLISTOPTION)).click();
//		//Click on Continue button
//		browser.findWebElement(By.xpath(xpath_repository.CLIENTHOTEL_SEARCH_CONTINUE_BUTTON)).click();
//		TimeUnit.SECONDS.sleep(8);

	}
*/
	@Test(priority = 10)
	public void corpconfigviewtest() throws InterruptedException {
		Thread.sleep(2000);
		//System.out.println("jfomi csf");
		Verify.verifyEquals(hotelName+"  |  Hotel Code: "+hotelCode,browser.findWebElement(By.xpath(".//*[@id='contentcolumn']/div/table[1]/tbody/tr/td[1]/strong")).getText());  
		//System.out.println("jfomi csf");
		Verify.verifyEquals("CM All-in-one (RTAIO)",browser.findWebElement(By.xpath(".//*[@id='DIV_357286_0']/td/table/tbody/tr[2]/td[1]")).getText());
		Verify.verifyEquals(true,browser.findWebElement(By.xpath(".//*[@id='radioComp' and @value='S']")).isEnabled());
		Verify.verifyEquals(false,browser.findWebElement(By.xpath(".//*[@id='radioComp' and @value='C']")).isEnabled());
		Verify.verifyEquals(false,browser.findWebElement(By.xpath(".//*[@id='radioShopperType' and @value='C']")).isEnabled());
		Verify.verifyEquals(false,browser.findWebElement(By.xpath(".//*[@id='radioShopperType' and @value='W']")).isEnabled());
		Verify.verifyEquals(true,browser.findWebElement(By.xpath(".//*[@id='radioHotelSite' and @value='S']")).isEnabled());
		Verify.verifyEquals(false,browser.findWebElement(By.xpath(".//*[@id='radioHotelSite' and @value='C']")).isEnabled()); 
		//System.out.println("jfomi csf");
		Select select = new Select(browser.findWebElement(By.xpath(".//*[@id='selectRegion']")));
		Verify.verifyEquals("global",select.getFirstSelectedOption().getText());
		//System.out.println("jfomi csf");
		String parentWindowHandle =null;
		try{
			browser.findWebElement(By.xpath(".//*[@id='shopper']/table[1]/tbody/tr/td/table/tbody/tr/td[5]/a")).click();

			parentWindowHandle = browser.switchToNewlyOpenedWindow();
			TimeUnit.SECONDS.sleep(1);

			Verify.verifyEquals("Region:",browser.findWebElement(By.xpath(".//*[@id='box']/table/tbody/tr[1]/td[1]")).getText());
			Verify.verifyEquals("Time zone:",browser.findWebElement(By.xpath(".//*[@id='box']/table/tbody/tr[2]/td[1]")).getText());
			Verify.verifyEquals("Currency code:",browser.findWebElement(By.xpath(".//*[@id='box']/table/tbody/tr[3]/td[1]")).getText());   
			Verify.verifyEquals(true,browser.findWebElement(By.xpath(".//*[@id='box']/table/tbody/tr[4]/td/table/tbody/tr/td[2]/a/img")).isEnabled());
			Verify.verifyEquals(true,browser.findWebElement(By.xpath(".//*[@id='box']/table/tbody/tr[4]/td/table/tbody/tr/td[4]/a/img")).isEnabled());
			browser.findWebElement(By.xpath(".//*[@id='box']/table/tbody/tr[4]/td/table/tbody/tr/td[4]/a/img")).click();
		}
		finally{
			browser.switchToOriginalWindow(parentWindowHandle);  
		}
		Verify.verifyEquals("RTCorp",browser.findWebElement(By.xpath(".//*[@id='DIV_357281_0']/td/table[2]/tbody/tr[2]/td/div/table[1]/tbody/tr/td[1]")).getText());
		Verify.verifyEquals("Report Type",browser.findWebElement(By.xpath(".//*[@id='DIV_357281_0']/td/table[2]/tbody/tr[2]/td/div/table[2]/tbody/tr[1]/td/table/tbody/tr/td[1]")).getText());
		Verify.verifyEquals("Rate Difference",browser.findWebElement(By.xpath(".//*[@id='DIV_357281_0']/td/table[2]/tbody/tr[2]/td/div/table[2]/tbody/tr[1]/td/table/tbody/tr/td[3]")).getText());
		Verify.verifyEquals("Alert Colour",browser.findWebElement(By.xpath(".//*[@id='DIV_357281_0']/td/table[2]/tbody/tr[2]/td/div/table[2]/tbody/tr[1]/td/table/tbody/tr/td[5]")).getText());
		Verify.verifyEquals("Alert Mail After",browser.findWebElement(By.xpath(".//*[@id='DIV_357281_0']/td/table[2]/tbody/tr[2]/td/div/table[2]/tbody/tr[1]/td/table/tbody/tr/td[7]")).getText());
		Verify.verifyEquals("Edit",browser.findWebElement(By.xpath(".//*[@id='DIV_357281_0']/td/table[2]/tbody/tr[2]/td/div/table[1]/tbody/tr/td[2]/a/span")).getText());
		Verify.verifyEquals(true,browser.findWebElement(By.xpath(".//*[@id='DIV_357281_0']/td/table[2]/tbody/tr[2]/td/div/table[1]/tbody/tr/td[2]/a/span")).isEnabled());

	}
/*
	@Test(priority = 20)
	public void MyHotelsTest() throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@id='DIV_357288_0']/td/div/table[1]/tbody/tr/td[2]/table/tbody/tr[1]/td[2]/a")));

		String parentWindowHandle = driver.getWindowHandle(); 

		browser.findWebElement(By.xpath("//tr[@id='DIV_357288_0']/td/div/table[1]/tbody/tr/td[2]/table/tbody/tr[1]/td[2]/a")).click();	 
		TimeUnit.SECONDS.sleep(6);

		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		String dragid1 = "dojoUnique1";   
		String myhotel1 = browser.findWebElement(By.id(dragid1)).getText();

		WebElement dragElement1 = browser.findWebElement(By.id(dragid1));
		WebElement dropElement1 = browser.findWebElement(By.xpath(".//div[@id='selected_my_hotels']"));

		Actions builder1 = new Actions(driver);
		Action drag1 = builder1.clickAndHold(dragElement1).build();
		drag1.perform();

		Action move1 = builder1.moveByOffset(355, -20).build();
		move1.perform();
		TimeUnit.SECONDS.sleep(2);
		Actions release1 = builder1.clickAndHold(dropElement1).release();
		release1.perform();

		//Click on Save
		browser.findWebElement(By.xpath("//tr[@id='DIV_357288_0']/td/div/table[3]/tbody/tr/td[2]/a")).click();
		TimeUnit.SECONDS.sleep(10);
		driver.switchTo().window(parentWindowHandle);

		WebDriverWait wait1 = new WebDriverWait(driver, 50);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@id='DIV_357288_0']/td/div/table[1]/tbody/tr/td[2]/table/tbody/tr[1]/td[1]/strong")));

		List<String> selected_myhotels_list = new ArrayList<String>();

		for(int i = 2; i < 50; i = i+1) { 

			try {
				String path = "//div[@id='selected_my_hotels']/div["+i+"]" ;    	     
				WebElement Element1 = browser.findWebElement(By.xpath(path));
				selected_myhotels_list.add(new String(Element1.getText()));

			} 
			catch (NoSuchElementException ex) { 
				break;
			}
		}

		Verify.verifyEquals(true,selected_myhotels_list.contains(myhotel1));

		TimeUnit.SECONDS.sleep(1);

		browser.findWebElement(By.xpath("//tr[@id='DIV_357288_0']/td/div/table[1]/tbody/tr/td[2]/table/tbody/tr[1]/td[2]/a")).click();	 
		TimeUnit.SECONDS.sleep(6);

		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		for(int i = 2; i < 50; i = i+1) { 
			try {

				WebElement dragElement = browser.findWebElement(By.xpath("//div[@id='selected_my_hotels']/div["+i+"]"));
				WebElement dropElement = browser.findWebElement(By.id("dojoUnique1"));

				if(myhotel1.equals(dragElement.getText())){

					Actions builder2 = new Actions(driver);
					Action drag2 = builder2.clickAndHold(dragElement).build();
					drag2.perform();

					Action move2 = builder1.moveByOffset(-355, 20).build();
					move2.perform();
					TimeUnit.SECONDS.sleep(2);
					Actions release2 = builder2.clickAndHold(dropElement).release();
					release2.perform();
				}
			} 
			catch (NoSuchElementException ex) { 
				break;
			}
		}

		//Click on Save
		browser.findWebElement(By.xpath("//tr[@id='DIV_357288_0']/td/div/table[3]/tbody/tr/td[2]/a")).click();
		TimeUnit.SECONDS.sleep(10);
		driver.switchTo().window(parentWindowHandle);
		WebDriverWait wait2 = new WebDriverWait(driver, 50);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@id='DIV_357288_0']/td/div/table[1]/tbody/tr/td[2]/table/tbody/tr[1]/td[1]/strong")));

		List<String> selected_myhotels_list1 = new ArrayList<String>();

		for(int i = 2; i < 50; i = i+1) { 

			try {
				String path = "//div[@id='selected_my_hotels']/div["+i+"]" ;    	     
				WebElement Element2 = browser.findWebElement(By.xpath(path));
				selected_myhotels_list1.add(new String(Element2.getText()));

			} 
			catch (NoSuchElementException ex) { 
				break;
			}
		}

		Verify.verifyEquals(false,selected_myhotels_list1.contains(myhotel1));	 

	}


	@Test(priority = 30)
	public void FTPSettingsTest() throws InterruptedException {
		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='DIV_357288_0']/td/div/table[1]/tbody/tr/td[2]/table/tbody/tr[1]/td[2]/a")));

		String parentWindowHandle = driver.getWindowHandle(); 
		try{ 
			browser.findWebElement(By.xpath(".//*[@id='DIV_357281_0']/td/table[2]/tbody/tr[1]/td/div/table[1]/tbody/tr/td[2]/a/span")).click();	 
			TimeUnit.SECONDS.sleep(6);

			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}

			browser.findWebElement(By.xpath(".//*[@id='isFTPReportDeliveryEnabledNo']")).click();
			Verify.verifyEquals("true",browser.findWebElement(By.xpath(".//*[@id='FTPURL']")).getAttribute("readonly"));
			Verify.verifyEquals("true",browser.findWebElement(By.xpath(".//*[@id='FTPPort']")).getAttribute("readonly"));
			Verify.verifyEquals("true",browser.findWebElement(By.xpath(".//*[@id='FTPPath']")).getAttribute("readonly"));
			Verify.verifyEquals("true",browser.findWebElement(By.xpath(".//*[@id='FTPUsername']")).getAttribute("readonly"));
			Verify.verifyEquals("true",browser.findWebElement(By.xpath(".//*[@id='FTPPassword']")).getAttribute("readonly"));

			browser.findWebElement(By.xpath(".//*[@id='isFTPReportDeliveryEnabledYes']")).click();
			browser.findWebElement(By.xpath(".//*[@id='FTPURL']")).clear();
			browser.findWebElement(By.xpath(".//*[@id='FTPURL']")).sendKeys("11.222.333.44");
			browser.findWebElement(By.xpath(".//*[@id='FTPPort']")).clear();
			browser.findWebElement(By.xpath(".//*[@id='FTPPort']")).sendKeys("1234");

			browser.findWebElement(By.xpath(".//*[@id='PopulateGenaralPreference']/table[3]/tbody/tr/td[3]/table/tbody/tr/td[1]/a")).click();
			TimeUnit.SECONDS.sleep(3);

			driver.switchTo().window(parentWindowHandle);

			Verify.verifyEquals("11.222.333.44:1234",browser.findWebElement(By.xpath(".//*[@id='DIV_357281_0']/td/table[2]/tbody/tr[1]/td/div/table[2]/tbody/tr[3]/td[2]/table/tbody/tr/td")).getText());

			browser.findWebElement(By.xpath(".//*[@id='DIV_357281_0']/td/table[2]/tbody/tr[1]/td/div/table[1]/tbody/tr/td[2]/a/span")).click();	 
			Thread.sleep(6000);

			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			browser.findWebElement(By.xpath(".//*[@id='isFTPReportDeliveryEnabledYes']")).click();
			browser.findWebElement(By.xpath(".//*[@id='FTPURL']")).clear();
			browser.findWebElement(By.xpath(".//*[@id='FTPURL']")).sendKeys("176.74.173.39");
			browser.findWebElement(By.xpath(".//*[@id='FTPPort']")).clear();
			browser.findWebElement(By.xpath(".//*[@id='FTPPort']")).sendKeys("9797");

			browser.findWebElement(By.xpath(".//*[@id='PopulateGenaralPreference']/table[3]/tbody/tr/td[3]/table/tbody/tr/td[1]/a")).click();
			TimeUnit.SECONDS.sleep(3);
		}
		finally{
			driver.switchTo().window(parentWindowHandle);
		}

	}

	@Test(priority = 40)
	public void FTPSettingsValidationMsgTest() throws InterruptedException {
		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='DIV_357288_0']/td/div/table[1]/tbody/tr/td[2]/table/tbody/tr[1]/td[2]/a")));

		String parentWindowHandle = driver.getWindowHandle(); 
		try{ 
			browser.findWebElement(By.xpath(".//*[@id='DIV_357281_0']/td/table[2]/tbody/tr[1]/td/div/table[1]/tbody/tr/td[2]/a/span")).click();	 
			TimeUnit.SECONDS.sleep(6);

			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}

			browser.findWebElement(By.xpath(".//*[@id='isFTPReportDeliveryEnabledYes']")).click();
			browser.findWebElement(By.xpath(".//*[@id='FTPURL']")).clear();
			browser.findWebElement(By.xpath(".//*[@id='PopulateGenaralPreference']/table[3]/tbody/tr/td[3]/table/tbody/tr/td[1]/a")).click();
			TimeUnit.SECONDS.sleep(1);
			Verify.verifyEquals("Please enter FTP URL.",browser.findWebElement(By.xpath(".//*[@id='validationMessage']")).getText());

			browser.findWebElement(By.xpath(".//*[@id='FTPURL']")).sendKeys("176.74.173.39");
			browser.findWebElement(By.xpath(".//*[@id='FTPPort']")).clear();
			browser.findWebElement(By.xpath(".//*[@id='PopulateGenaralPreference']/table[3]/tbody/tr/td[3]/table/tbody/tr/td[1]/a")).click();
			TimeUnit.SECONDS.sleep(1);
			Verify.verifyEquals("Please enter FTP Port.",browser.findWebElement(By.xpath(".//*[@id='validationMessage']")).getText());

			browser.findWebElement(By.xpath(".//*[@id='FTPPort']")).sendKeys("9797");
			browser.findWebElement(By.xpath(".//*[@id='FTPUsername']")).clear();
			browser.findWebElement(By.xpath(".//*[@id='PopulateGenaralPreference']/table[3]/tbody/tr/td[3]/table/tbody/tr/td[1]/a")).click();
			TimeUnit.SECONDS.sleep(1);
			Verify.verifyEquals("Please enter FTP Username.",browser.findWebElement(By.xpath(".//*[@id='validationMessage']")).getText());

			browser.findWebElement(By.xpath(".//*[@id='FTPUsername']")).sendKeys("testuser");
			browser.findWebElement(By.xpath(".//*[@id='FTPPassword']")).clear();
			browser.findWebElement(By.xpath(".//*[@id='PopulateGenaralPreference']/table[3]/tbody/tr/td[3]/table/tbody/tr/td[1]/a")).click();
			TimeUnit.SECONDS.sleep(1);
			Verify.verifyEquals("Please enter FTP Password.",browser.findWebElement(By.xpath(".//*[@id='validationMessage']")).getText());

			browser.findWebElement(By.xpath(".//*[@id='FTPPassword']")).sendKeys("welcome1");
			browser.findWebElement(By.xpath(".//*[@id='FTPPath']")).clear();
			browser.findWebElement(By.xpath(".//*[@id='PopulateGenaralPreference']/table[3]/tbody/tr/td[3]/table/tbody/tr/td[1]/a")).click();

			WebDriverWait wait1 = new WebDriverWait(driver, 50);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='EditGenaralPreference']/table[3]/tbody/tr/td[1]")));

			//		Thread.sleep(10);
			Verify.verifyEquals("General Preference updated successfully! Please wait...",browser.findWebElement(By.xpath(".//*[@id='EditGenaralPreference']/table[3]/tbody/tr/td[1]")).getText());
			TimeUnit.SECONDS.sleep(5);
			driver.switchTo().window(parentWindowHandle);

			browser.findWebElement(By.xpath(".//*[@id='DIV_357281_0']/td/table[2]/tbody/tr[1]/td/div/table[1]/tbody/tr/td[2]/a/span")).click();	 
			TimeUnit.SECONDS.sleep(6);

			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}

			browser.findWebElement(By.xpath(".//*[@id='FTPPath']")).sendKeys("RTAdmin-2.2.2");
			browser.findWebElement(By.xpath(".//*[@id='PopulateGenaralPreference']/table[3]/tbody/tr/td[3]/table/tbody/tr/td[1]/a")).click();
			//		Thread.sleep(10);
			WebDriverWait wait2 = new WebDriverWait(driver, 50);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='EditGenaralPreference']/table[3]/tbody/tr/td[1]")));
			Verify.verifyEquals("General Preference updated successfully! Please wait...",browser.findWebElement(By.xpath(".//*[@id='EditGenaralPreference']/table[3]/tbody/tr/td[1]")).getText());
		}
		finally{
			driver.switchTo().window(parentWindowHandle);
		}				
	}
	
	@Test(priority = 50)
	public void RegionTest() throws InterruptedException {
		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='DIV_357288_0']/td/div/table[1]/tbody/tr/td[2]/table/tbody/tr[1]/td[2]/a")));

		Select select = new Select(browser.findWebElement(By.xpath(".//*[@id='selectRegion']")));
		select.selectByVisibleText("APAC");
		TimeUnit.SECONDS.sleep(8);
		Verify.verifyEquals("Click on 'Edit' link to add new websites...",browser.findWebElement(By.xpath(".//*[@id='blank_selected_comp_hotels']")).getText());
		Verify.verifyEquals("Click on 'Edit' link to add new websites...",browser.findWebElement(By.xpath(".//*[@id='blank_travelsites_shopperTS']")).getText());
		Verify.verifyEquals("Click on 'Edit' link to add new websites...",browser.findWebElement(By.xpath(".//*[@id='blank_selected_my_hotels']")).getText());

		Verify.verifyEquals("Links_dis",browser.findWebElement(By.xpath(".//*[@id='DIV_357291_0']/td/table[3]/tbody/tr[1]/td[2]/a")).getAttribute("class"));
		Verify.verifyEquals("No",browser.findWebElement(By.xpath(".//*[@id='DIV_357281_0']/td/table[2]/tbody/tr[1]/td/div/table[2]/tbody/tr[2]/td[14]")).getText());

	}
/*
	@AfterMethod(alwaysRun = true)
	public void logoutRTAdmin() throws InterruptedException{
//		browser.findWebElement(By.xpath("html/body/div[1]/div[1]/div[2]/div/a[1]")).click();
//		TimeUnit.SECONDS.sleep(1);
	}

	@AfterClass
	public static void closeFirefox(){
		driver.quit();

		baseUrl=null;
		userid=null;
		pwd=null;
		driver=null;
		hotelCode=null;
		hotelName=null;
		city=null;
		country=null;
		hotelAddress=null;
	}
*/

}
