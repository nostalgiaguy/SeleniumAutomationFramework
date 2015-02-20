package com.erevmax.selenium.RTAdmin;
//tvhthythyjnty
import java.util.ArrayList;

import static com.erevmax.selenium.PageObjects.ConfigDetailActionPage.cdp;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.erevmax.utils.*;

import org.testng.annotations.Test;

import com.erevmax.baseTest.TestBase;

public class Test_TC07_CompetitorHotels extends TestBase{
	
	private final Logger log= Logger.getLogger(this.getClass().getName());
	//WebDriver driver=browser.getDriver(); 
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
	
	@Test(priority=10,enabled=true)
	public void yest() throws InterruptedException{
		System.out.println("Test 2 Start");
		int size=browser.getListSize(By.xpath("//*[@id='selected_comp_hotels']/div"));
		System.out.println(size);
		System.out.println("Test 2 Start");
		String hotel1 = null;
		String dragid3 = "dojoUnique3"; 
		String parentwindow=null;
		browser.elementWait(4000);
		browser.click(cdp.editCompetitorHotelBtn);
		browser.elementWait(20000);///////////////////////////////
		parentwindow = browser.switchToNewlyOpenedWindow();
		//try{
			hotel1 = browser.findWebElement(By.xpath("//*[@id='dojoUnique3']")).getText();
		System.out.println("i m here");
		browser.elementWait(10000);
		WebElement From =browser.findWebElement(By.xpath("//*[@id='dojoUnique3']"));
		 
		WebElement To = browser.findWebElement(By.xpath("//*[@id='dojoUnique1251']"));
		
		//WebElement element = driver.findElement(By.name("source"));
		//WebElement target = driver.findElement(By.name("target"));

		//(new Actions(browser.getDriver())).dragAndDrop(From, To).perform();
		
		//driver = webdriver.Firefox()
				//e = driver.find_element_by_xpath("//someXpath")
				Point location = To.getLocation();
				//Size size1 = From.size1;
				System.out.println(location);
		/*		//System.out.println(size1);
		Actions builder = new Actions(browser.getDriver());
		 
		Action dragAndDrop = builder.clickAndHold(From)
		 
		.moveToElement(To)
		 
		.release(To)
		 
		.build();
		 
		dragAndDrop.perform();*/
		   System.out.println("sdv");
		   Actions builder = new Actions(browser.getDriver());
		   System.out.println("sd grev");// Configure the Action  
           Action dragAndDrop = builder.clickAndHold(From).moveToElement(To).release(To).build();  
           System.out.println("sdvd s");// Get the action 
           Thread.sleep(1000L);
           dragAndDrop.perform();
           System.out.println("sxwedv");// Execute the Action  
		 
        browser.elementWait(5000);
		browser.click(cdp.SaveCompetitorHotelBtn);
		browser.elementWait(15000);
		browser.switchToOriginalWindow(parentwindow);
		//browser.elementWait(2000);

		browser.waitForElementVisible(cdp.compHotelShoppingTab);
		browser.click(cdp.compHotelShoppingTab);
	


		List<String> selected_comphotels_list = new ArrayList<String>();
		////*[@id='selected_comp_hotels']
		 size=browser.getListSize(By.xpath("//*[@id='selected_comp_hotels']/div"));
		System.out.println(size);
		for(int i = 2; i <= size; i = i+1) { 

			try {
				String path = "//div[@id='selected_comp_hotels']/div["+i+"]" ;    	     
				WebElement Element1 = browser.findWebElement(By.xpath(path));
				System.out.println(Element1.getText());
				selected_comphotels_list.add(new String(Element1.getText()));

			} 
			catch (NoSuchElementException ex) { 
				break;
			}
		}
		Verify.assertEquals(selected_comphotels_list.contains(hotel1),true);
	}

	//@Test(priority = 10,enabled=true)
	public void CompetitorHotelMapUnmapTest() throws InterruptedException {

		System.out.println("Test 2 Start");
		String hotel1 = null;
		String dragid1 = "dojoUnique1"; 
		String parentwindow=null;
		browser.elementWait(4000);
		browser.click(cdp.editCompetitorHotelBtn);
		browser.elementWait(10000);///////////////////////////////
		parentwindow = browser.switchToNewlyOpenedWindow();
		//try{
			hotel1 = browser.findWebElement(By.xpath("//*[@id='dojoUnique2']")).getText();

			WebElement dragElement1 = browser.findWebElement(By.xpath("//*[@id='dojoUnique2']"));
			WebElement dropElement1 = browser.findWebElement(By.xpath("//*[@id='dojoUnique1250']"));

			System.out.println(dragElement1.getText());
			System.out.println(dropElement1.getText());
			
			System.out.println(dragElement1.getLocation());
			System.out.println(dropElement1.getLocation());
			
			browser.elementWait(10000);
			
			Actions builder1 = new Actions(browser.getDriver());
			Action drag1 = builder1.clickAndHold(dragElement1).build();
			drag1.perform();
			//Action move1 = builder1.moveByOffset(385, 203).build();
			Action move1 = builder1.moveToElement(dropElement1, 385, 203).build();
			move1.perform();
			builder1.release(dropElement1).build().perform();
			
			
			browser.elementWait(3000);
			browser.click(cdp.SaveCompetitorHotelBtn);
			browser.elementWait(15000);
			browser.switchToOriginalWindow(parentwindow);
			//browser.elementWait(2000);

			browser.waitForElementVisible(cdp.compHotelShoppingTab);
			browser.click(cdp.compHotelShoppingTab);
		
	

			List<String> selected_comphotels_list = new ArrayList<String>();
			////*[@id='selected_comp_hotels']
			int size=browser.getListSize(By.xpath("//*[@id='selected_comp_hotels']/div"));
			System.out.println(size);
			for(int i = 2; i <= size; i = i+1) { 

				try {
					String path = "//div[@id='selected_comp_hotels']/div["+i+"]" ;    	     
					WebElement Element1 = browser.findWebElement(By.xpath(path));
					System.out.println(Element1.getText());
					selected_comphotels_list.add(new String(Element1.getText()));

				} 
				catch (NoSuchElementException ex) { 
					break;
				}
			}
			Verify.assertEquals(selected_comphotels_list.contains(hotel1),true);
		}
/*
		finally{
			//browser.switchToOriginalWindow(parentwindow);
		}
		browser.elementWait(1000);

		browser.click(cdp.editBtn);	 
		browser.waitForNumberOfWindowsToEqual(2);
		parentwindow = browser.switchToNewlyOpenedWindow();
		try{
			
			for(int i = 2; i < 50; i = i+1) { 

				try {

					WebElement dragElement = browser.findWebElement(By.xpath("//div[@id='selected_comp_hotels']/div["+i+"]"));
					WebElement dropElement = browser.findWebElement(By.id("dojoUnique1"));

					if(hotel1.equals(dragElement.getText())){

						Actions builder2 = new Actions(browser.getDriver());
						Action drag2 = builder2.clickAndHold(dragElement).build();
						drag2.perform();

						Action move2 = builder2.moveByOffset(-355, 20).build();
						move2.perform();
						Thread.sleep(2000);
						Actions release2 = builder2.clickAndHold(dropElement).release();
						release2.perform();
					}

				} 
				catch (NoSuchElementException ex) { 
					break;
				}
			}

			browser.click(cdp.SaveCompetitorHotelBtn);
			browser.waitForNumberOfWindowsToEqual(1);
			browser.switchToOriginalWindow(parentwindow);

			//browser.elementWait(2000);
			browser.waitForElementVisible(cdp.compHotelShoppingTab);
			browser.click(cdp.compHotelShoppingTab);

			List<String> selected_comphotels_list1 = new ArrayList<String>();

			for(int i = 2; i < 50; i = i+1) { 

				try {
					String path = "//div[@id='selected_comp_hotels']/div["+i+"]" ;    	     
					WebElement Element2 = browser.findWebElement(By.xpath(path));
					selected_comphotels_list1.add(new String(Element2.getText()));

				} 
				catch (NoSuchElementException ex) { 
					break;
				}
			}

			Verify.assertEquals(false,selected_comphotels_list1.contains(hotel1));	
			System.out.println("Test 2 completed");
		}
		finally{
			//browser.switchToOriginalWindow(parentwindow);
		}

	}

/*
	@Test(priority = 10)
	public void CompetitorHotelResetTest() throws InterruptedException {

		System.out.println("Test 3 Start.");
		String parentWindowHandle=null;
		browser.elementWait(4000);
		//browser.waitForElementClickable(cdp.editCompetitorHotelBtn);
		
		browser.click(cdp.editCompetitorHotelBtn);
		browser.waitForNumberOfWindowsToEqual(2);
		parentWindowHandle =  browser.switchToNewlyOpenedWindow();

		String dragid1 = "dojoUnique1";   
		String hotel1 =browser.findWebElement(By.id(dragid1)).getText();

		WebElement dragElement1 = browser.findWebElement(By.id(dragid1));
		WebElement dropElement1 = browser.findWebElement(By.xpath("//div[@id='selected_comp_hotels']"));

		Actions builder1 = new Actions(browser.getDriver());
		Action drag1 = builder1.clickAndHold(dragElement1).build();
		drag1.perform();

		Action move1 = builder1.moveByOffset(355, -20).build();
		move1.perform();
		browser.elementWait(2000);
		Actions release1 = builder1.clickAndHold(dropElement1).release();
		release1.perform();

		browser.click(cdp.ResetBtn);
		browser.elementWait(10000);
		browser.getDriver().close();
		browser.switchToOriginalWindow(parentWindowHandle);
		browser.waitForElementVisible(cdp.compHotelShoppingTab);
		browser.click(cdp.compHotelShoppingTab);

		List<String> selected_comphotels_list1 = new ArrayList<String>();

		for(int i = 2; i < 50; i = i+1) { 

			try {
				String path = "//div[@id='selected_comp_hotels']/div["+i+"]" ;    	     
				WebElement Element = browser.findWebElement(By.xpath(path));
				selected_comphotels_list1.add(new String(Element.getText()));

			} 
			catch (NoSuchElementException ex) { 
				break;
			}
		}

		Verify.assertEquals(false,selected_comphotels_list1.contains(hotel1));	 
	}
*/
}
	


