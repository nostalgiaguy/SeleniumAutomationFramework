package com.erevmax.assignment.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.erevmax.utils.CustomTestListener;
import com.erevmax.utils.Verify;

@Listeners(CustomTestListener.class)
@Test(enabled=true)
public class AnnotationDemo {
	
	@BeforeClass(description="Before class test")
	public void beforeClassTest(){
		System.out.println("Before class test");
		//Assert.assertTrue(false);
	}
	@BeforeSuite(description="Before suite test")
	 public void suiteTest(){
		System.out.println("Suite test: Load test suite url");
	}
	@Parameters({ "url" })
	@BeforeTest(description="Before Test  test")
	public void launchApp(@Optional("http://rediffmail.com") String url){
		System.out.println("Before test"+url);
	}
	
	@Test(groups="P1",description="Validate the login page loaded")
	public void TC01_login(){
			System.out.println("Validate the session");
			System.out.println("i m here,i m enjoying");
			System.out.println("i m enjoying=");
			System.out.println("i m not here");
	}
	
	
	@Test(groups="P1",description="Check the user should allow to login successfully with valid username and valid password")
	public void TC02_dologin(){
		System.out.println("user should allow to login successfully with valid username and valid password");
	}
	
	
	@Test(groups="P2",description="Check the Validation messages for all valid and invlaid test data")
	public void TC03_loginValidationMessage(){
		Assert.assertTrue(true);
		System.out.println("Validation messages for all valid and invlaid test data");
		String newChainName="ABCD";
				String newChainCode="99";
		System.out.println(newChainName+" | "+newChainCode);
	}

	@AfterClass(description="after class test")
	public void afterclassTest(){
		System.out.println("After class test");
	}
	
@AfterTest(description="after test test")
public void closeBrowser(){
	System.out.println("Close the browser-after test");
}

@AfterSuite(description="after suite test")
public void aftersuiteTest(){
	System.out.println("after suite-test");
}
}

/*
	@Test
	public void a(){
		System.out.println("run a");
		
	}
	@Test
	public void b(){
		System.out.println("run b");
	}
	@Test(dependsOnMethods="a")
	public void c(){
		
		System.out.println("run c");
	}
	
	
}
*/

