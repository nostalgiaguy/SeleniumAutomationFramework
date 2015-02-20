package com.erevmax.selenium.RTAdmin;

import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import com.erevmax.baseTest.TestBase;
import com.erevmax.utils.Verify;

public class Test_TC01_Dashboard extends TestBase {

	
	public final By welcomeTxt=By.xpath("//*[@id='maincontainer']/table/tbody/tr/td");
	public final By userDisplay=By.xpath("//html/body/div[1]/div[1]/div[1]/b");
	public final By copyrightStmt=By.xpath("//html/body/div[3]");
	private final Logger log= Logger.getLogger(this.getClass().getName());
	
	//When I tried to read an Excel file in Java it throws "biff exception".
	
	@Test
	public void Dashboardtest() {
		log.info("Test 1 Start.");
		String actualwelcometext = "";
		String expectedwelcometext = "Welcome to RTAdmin";
		String actualuserdisplay = "";
		String expecteduserdisplay = "Admin: adminuser";

		actualwelcometext = browser.getText(welcomeTxt);
		actualuserdisplay = browser.getText(userDisplay);


		Verify.verifyEquals(actualwelcometext, expectedwelcometext );
		Verify.verifyEquals(actualuserdisplay, expecteduserdisplay );        

		Verify.verifyEquals( browser.getText(copyrightStmt), "© eRevMax Inc. All Rights Reserved.");

		log.info("Verified the display of user role,userid and the welcome message in Dashboard");
		log.info("Verified the copyright statement text in Dashboard");
		Reporter.log("Testing Reporter log");
		log.info("Test 1 completed.");
	}


}
