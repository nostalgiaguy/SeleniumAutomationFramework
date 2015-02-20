package com.erevmax.admin.tests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.erevmax.RTAdmin.pages.AdminPage;
import com.erevmax.baseTest.TestBase;
import com.erevmax.utils.Verify;

public class AdminTest extends TestBase{
	AdminPage ap= new AdminPage();

	@Test(description="navigating to Access Control page",priority=0)
	public void navigationToAssignmentsPage(){
		System.out.println("Test 1:");
		browser.click(ap.adminMenu);
		System.out.println("Current url is :" +browser.getCurrentUrl());
		//browser.elementWait(8);
		time(8);
		int status =browser.sendHttpGetRequest(browser.getCurrentUrl());
		//Verify.assertEquals(status,200);
		System.out.println("Test 1 completed.");
	}
	
	//@Test(priority=10)
	public void configRoleTest(){
		String parentWindowHandle=null;
		try{System.out.println("Test 2:");
		List<WebElement> a=new ArrayList<WebElement>();
		List<String> module=new ArrayList<String>();
		browser.elementWait(3);
		time(3);
		browser.click(ap.addNewUser);
		browser.elementWait(20);
		parentWindowHandle=browser.switchToNewlyOpenedWindow();
		browser.selectFromDropDownList(ap.role,"Config");
		time(10);
		 a=browser.findWebElements(By.xpath("//*[starts-with(@id,'DIV_')]/td/table/tbody/tr/td[3]"));
		 int ColumnIndex=1;
			for(WebElement colElement:a)
			{
				System.out.println(ColumnIndex+"  "+colElement.getText());
				module.add(colElement.getText().trim());
				ColumnIndex=ColumnIndex+1;
			}
			System.out.println(module+"  "+module.size());
			System.out.println(module.get(0));
			System.out.println(module.get(1));
			Verify.assertEquals(module.get(0),"Config");
			Verify.assertEquals(module.get(1),"Others");
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentWindowHandle);	
				System.out.println("Test 2 completed.");
			}
		}
		
		
		
	}
	//@Test(priority=20)
	public void configModuleTest(){
		String parentWindowHandle=null;
		try{
		System.out.println("Test 3:");
		List<WebElement> a=new ArrayList<WebElement>();
		List<String> module=new ArrayList<String>();
		browser.elementWait(3);
		time(3);
		browser.click(ap.addNewUser);
		browser.elementWait(20);
		 parentWindowHandle=browser.switchToNewlyOpenedWindow();
		browser.selectFromDropDownList(ap.role,"Config");
		time(10);
		/* a=browser.findWebElements(By.xpath("//*[starts-with(@id,'DIV_')]/td/table/tbody/tr/td[3]"));
		 int ColumnIndex=1;
			for(WebElement colElement:a)
			{
				module.add(colElement.getText());
				ColumnIndex=ColumnIndex+1;
			}
			*/
		browser.click(By.xpath("//*[@id='EC_52']"));
		a=browser.findWebElements(By.xpath("//*[starts-with(@id,'DIV_')]/td/table/tbody/tr"));
		System.out.println("size="+a.size());
		 int ColumnIndex=1;
			for(WebElement colElement:a)
			{
				System.out.println(ColumnIndex+"  "+colElement.getText());
				ColumnIndex=ColumnIndex+1;
			}
		}
		finally{

			if(browser.getDriver().getWindowHandles().size() == 2){
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentWindowHandle);	
			}
		System.out.println("Test 3 completed.");
		}
		}
		
		@Test(priority=30)
		public void configSubModuleTest(){
			String parentWindowHandle=null;
			try{System.out.println("Test 4:");
			List<WebElement> a=new ArrayList<WebElement>();
			List<String> module=new ArrayList<String>();
			browser.elementWait(3);
			time(3);
			browser.click(ap.addNewUser);
			browser.elementWait(20);
			parentWindowHandle=browser.switchToNewlyOpenedWindow();
			browser.selectFromDropDownList(ap.role,"Config");
			time(10);
			browser.click(By.xpath("//*[@id='EC_52']"));
			time(2);
			browser.deSelectCheckbox(By.xpath("//*[@id='freadmodules__52__55']"), 0);
			System.out.println("a1656");
			////*[@id='fwritemodules__52__55']
			browser.deSelectCheckbox(By.xpath("//*[@id='fwritemodules__52__55']"), 0);
			System.out.println("a14641");
			boolean r1=browser.isSelected(By.xpath("//*[@id='mreadmodules__52']"));
			System.out.println("a11561");
			boolean w1=browser.isSelected(By.xpath("//*[@id='mwritemodules__52']"));
			System.out.println("a121651");
			System.out.println("a1");
			Verify.assertEquals(r1, false);
			Verify.assertEquals(w1, false);
			System.out.println("a11");
			time(5);
			browser.checkCheckBoxField(By.xpath("//*[@id='freadmodules__52__55']"), 0);
			boolean r2=browser.isSelected(By.xpath("//*[@id='mreadmodules__52']"));
			boolean w2=browser.isSelected(By.xpath("//*[@id='mwritemodules__52']"));
			System.out.println("a12");
			Verify.assertEquals(r2, true);
			Verify.assertEquals(w2, false);
			System.out.println("a13");
			time(5);
			browser.checkCheckBoxField(By.xpath("//*[@id='freadmodules__52__55']"), 0);
			browser.checkCheckBoxField(By.xpath("//*[@id='fwritemodules__52__55']"), 0);
			boolean r3=browser.isSelected(By.xpath("//*[@id='mreadmodules__52']"));
			boolean w3=browser.isSelected(By.xpath("//*[@id='mwritemodules__52']"));
			System.out.println("a14");
			Verify.assertEquals(r3, true);
			Verify.assertEquals(w3, true);
			System.out.println("a15");
			
			}
			finally{

			if(browser.getDriver().getWindowHandles().size() == 2){
					browser.getDriver().close();
					browser.switchToOriginalWindow(parentWindowHandle);	
				}
			System.out.println("Test 4 completed.");
			}
		
		
	}
public void time(long milli){
	for(int i=1; i<milli;i++)
	  {
	   Calendar c = Calendar.getInstance();
	   try {
	    Thread.sleep(1000);
	    System.out.println(c.getTime());
	   } catch (InterruptedException e) {
	    e.printStackTrace();
	   }
	  }
}
	
}

