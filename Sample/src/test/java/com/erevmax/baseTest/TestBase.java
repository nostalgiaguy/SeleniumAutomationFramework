package com.erevmax.baseTest;

import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.erevmax.RTAdmin.pages.LoginPage;
import com.erevmax.mysample.Sample.HelperMethod;
import com.erevmax.mysample.Sample.SeleniumImplementation;
import com.erevmax.mysample.Sample.SeleniumImplementation.BrowserType;

public class TestBase {

	protected String hotelName="Auto RTSuite Hotel";
	protected String hotelCode="43013";
	protected String city="Kolkata";
	protected String country="India";
	protected String hotelAddress="Salt Lake";
	protected String address="Salt Lake";
	//protected String bangalore_URL="http://176.74.173.43:5159/RTAdmin/";
	//protected String kolkata_URL="http://176.74.173.39:9797/RTAdmin-2.2.4/";
	public TestBase(){

	}

	//public static HelperMethod browser= new HelperMethod();
	public static SeleniumImplementation browser= new SeleniumImplementation();
	LoginPage lp= new LoginPage();
	public static TestBase base= new TestBase();
	
	@BeforeTest
	//@Test
	public void launchBrowser(){
		try{
			browser.openBrowserType(BrowserType.valueOf("FIREFOX"));
			browser.openUrl(browser.bangalore_URL);
			//browser.openUrl(browser.kolkata_URL);

			lp.doLogin("adminuser", "adminuser");
			lp.clickLoginButton();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Problem in connecting with firefox");
		}
	}
	
	/*
	@AfterTest
	public void closeBrowser(){
		browser.closeBrowser();
	}
	*/
}

/*
 * *************************************************************************************************************
 */
/*
@Test
	public void test1(){
		/*System.out.println("Current url is :" +browser.getCurrentUrl());
		//int a=browser.sendGetRequest("http://176.74.173.83:5159/");
		//System.out.println(a);
		lp.doLogin("adminuser", "adminuser");
		lp.clickLoginButton();
		System.out.println("Next Current url is :" +browser.getCurrentUrl());
		boolean c=browser.isResourceLoaded(browser.getCurrentUrl());
		System.out.println(c);
	boolean b=false;
	int i=-1;
	if(i>0){
		b=true;
	}
	Assert.assertTrue(b);
	}

@Test(dependsOnMethods="test1")
public void test12(){
	/*System.out.println("Current url is :" +browser.getCurrentUrl());
	//int a=browser.sendGetRequest("http://176.74.173.83:5159/");
	//System.out.println(a);
	lp.doLogin("adminuser", "adminuser");
	lp.clickLoginButton();
	System.out.println("Next Current url is :" +browser.getCurrentUrl());
	boolean c=browser.isResourceLoaded(browser.getCurrentUrl());
	System.out.println(c);
	System.out.println("i m here");
}

/*
	@Test
	public void successLogin(){
		String a="romy";
		String b="rom7y";
		String c="romy";
		String d="romy";

		try{
			Assert.assertEquals(a, b);
			System.out.println("dfnxksjnfh");
		}
		catch(AssertionError e){

			System.out.println("error");
			return;
		}
		System.out.println("vdklfjl");
	}
	}


 
*************************************************************************************************************
*/


