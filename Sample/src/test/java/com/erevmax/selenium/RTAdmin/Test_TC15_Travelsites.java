package com.erevmax.selenium.RTAdmin;
//TGFCTVTDV
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.erevmax.baseTest.TestBase;
import static com.erevmax.selenium.PageObjects.ConfigDetailActionPage.cdp;
import com.erevmax.utils.Verify;

public class Test_TC15_Travelsites extends TestBase{
	
//	public static String allocSite1 = "";
	
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
	public void Shopping_MappingUnmappingTravelSitesTest() throws InterruptedException {

		System.out.println("Test 2 Start");
		String site1 =null;
		String parentwindow = null;
		String dragid1 = "dojoUnique1";   
/*
		
		browser.waitForElementVisible(cdp.travelSitesShoppingTab);
		System.out.println("Test 2 Startcrtetre");
		browser.click(cdp.travelSitesShoppingTab);
		System.out.println("Test 2 Startcsefrwxw");
		browser.waitForElementClickable(cdp.shopper_editBtn);
		System.out.println("5ryretgcefge");
		//parentwindow = getParentWindowHandle();		
		browser.click(cdp.shopper_editBtn);

		System.out.println("Test 2 Startxrfweftrete5");
		browser.waitForNumberOfWindowsToEqual(2);
		browser.switchToNewlyOpenedWindow();
		try{
			site1 = browser.findWebElement(By.id(dragid1)).getText();

			WebElement dragElement1 =browser.findWebElement(By.id(dragid1));
			WebElement dropElement1 =browser.findWebElement(By.xpath("//div[@id='travelsites_shopperTS']/div[2]"));

			Actions builder1 = new Actions(browser.getDriver());
			Action drag1 = builder1.clickAndHold(dragElement1).build();
			drag1.perform();

			Action move1 = builder1.moveByOffset(355, -20).build();
			move1.perform();
			Thread.sleep(2000);
			Actions release1 = builder1.clickAndHold(dropElement1).release();
			release1.perform();

			browser.click(cdp.SaveBtn);
			browser.waitForNumberOfWindowsToEqual(1);
			browser.switchToOriginalWindow(parentwindow);

			browser.waitForElementVisible(cdp.travelSitesShoppingTab);
			browser.click(cdp.travelSitesShoppingTab);

			List<String> selected_shopsites_list = new ArrayList<String>();

			for(int i = 2; i < 50; i = i+1) { 

				try {
					String path = "//div[@id='travelsites_shopperTS']/div["+i+"]" ;    	     
					WebElement Element1 = browser.findWebElement(By.xpath(path));
					selected_shopsites_list.add(new String(Element1.getText()));

				} 
				catch (NoSuchElementException ex) { 
					break;
				}
			}

			Verify.assertEquals(true,selected_shopsites_list.contains(site1));
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentwindow);		
			}
		}
		Thread.sleep(1000);

		browser.click(cdp.shopper_editBtn);	 
		browser.waitForNumberOfWindowsToEqual(2);
		browser.switchToNewlyOpenedWindow();
		try{
			for(int i = 2; i < 50; i = i+1) { 

				try {

					WebElement dragElement = browser.findWebElement(By.xpath("//div[@id='travelsites_shopperTS']/div["+i+"]"));
					WebElement dropElement = browser.findWebElement(By.id("dojoUnique1"));

					if(site1.equals(dragElement.getText())){

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

			browser.click(cdp.SaveBtn);
			browser.waitForNumberOfWindowsToEqual(1);
			browser.switchToOriginalWindow(parentwindow);	

			browser.waitForElementVisible(cdp.travelSitesShoppingTab);
			browser.click(cdp.travelSitesShoppingTab);

			List<String> selected_travelsites_list1 = new ArrayList<String>();	 
			for(int i = 2; i < 50; i = i+1) { 

				try {
					String path = "//div[@id='travelsites_shopperTS']/div["+i+"]" ;    	     
					WebElement Element2 = browser.findWebElement(By.xpath(path));
					selected_travelsites_list1.add(new String(Element2.getText()));

				} 
				catch (NoSuchElementException ex) { 
					break;
				}
			}	 
			Verify.assertEquals(false,selected_travelsites_list1.contains(site1));
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentwindow);	
			}
		}
	}

	@Test(priority = 20)	
	public void Allocation_MappingUnmappingTravelSitesTest() throws InterruptedException {

		String site2 = "";
		String parentwindow = "";
		String dragid1 = "dojoUnique1";   

		browser.getDriver().navigate().refresh();
	
		browser.waitForElementVisible(cdp.travelSitesAllocatorTab);
		browser.click(cdp.travelSitesAllocatorTab);
		Thread.sleep(2000);
			
		browser.waitForElementClickable(cdp.allocator_editBtn);		
		browser.click(cdp.allocator_editBtn);	

		browser.waitForNumberOfWindowsToEqual(2);  
		browser.switchToNewlyOpenedWindow();
		
		try{
			site2 = browser.findWebElement(By.id(dragid1)).getText();

			WebElement dragElement1 = browser.findWebElement(By.id(dragid1));
			WebElement dropElement1 = browser.findWebElement(By.xpath("//div[@id='travelsites_allocatorTS']"));

			Actions builder1 = new Actions(browser.getDriver());
			Action drag1 = builder1.clickAndHold(dragElement1).build();
			drag1.perform();

			Action move1 = builder1.moveByOffset(355, -20).build();
			move1.perform();
			Thread.sleep(2000);
			Actions release1 = builder1.clickAndHold(dropElement1).release();
			release1.perform();

			browser.click(cdp.SaveBtn);
			browser.waitForNumberOfWindowsToEqual(1);
			browser.switchToOriginalWindow(parentwindow);	
//			browser.getDriver().navigate().refresh();
			Thread.sleep(10000);
			browser.waitForElementClickable(cdp.travelSitesAllocatorTab);
			browser.click(cdp.travelSitesAllocatorTab);

			List<String> selected_AllocSites_list = new ArrayList<String>();

			for(int i = 2; i < 50; i = i+1) { 

				try {
					String path = "//div[@id='travelsites_allocatorTS']/div["+i+"]" ;    	     
					WebElement Element1 = browser.findWebElement(By.xpath(path));
					selected_AllocSites_list.add(new String(Element1.getText()));

				} 
				catch (NoSuchElementException ex) { 
					break;
				}
			}

			Verify.assertEquals(selected_AllocSites_list.contains(site2),true);
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentwindow);	
			}
		}
		Thread.sleep(1000);

		browser.click(cdp.allocator_editBtn);	 
		browser.waitForNumberOfWindowsToEqual(2);
		browser.switchToNewlyOpenedWindow();
		try{
			for(int i = 2; i < 50; i = i+1) { 

				try {

					WebElement dragElement = browser.findWebElement(By.xpath("//div[@id='travelsites_allocatorTS']/div["+i+"]"));
					WebElement dropElement = browser.findWebElement(By.id("dojoUnique1"));

					if(site2.equals(dragElement.getText())){

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

			//Click on Save
			browser.click(cdp.SaveBtn);
			Thread.sleep(2000);
			if(browser.isAlertPresent()){
				browser.checkTextAlertMessage();
			}
			browser.waitForNumberOfWindowsToEqual(1);
			browser.switchToOriginalWindow(parentwindow);	
			Thread.sleep(10000);
			browser.waitForElementClickable(cdp.travelSitesAllocatorTab);
			browser.click(cdp.travelSitesAllocatorTab);

			List<String> selected_travelsites_list1 = new ArrayList<String>();	 
			for(int i = 2; i < 50; i = i+1) { 

				try {
					String path = "//div[@id='travelsites_allocatorTS']/div["+i+"]" ;    	     
					WebElement Element2 = browser.findWebElement(By.xpath(path));
					selected_travelsites_list1.add(new String(Element2.getText()));

				} 
				catch (NoSuchElementException ex) { 
					break;
				}
			}	 
			Verify.assertEquals(selected_travelsites_list1.contains(site2),false);
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentwindow);		
			}
		}
	}
*/
	}
}
