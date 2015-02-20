package com.erevmax.RTAdmin.pages;

import org.openqa.selenium.By;
import static com.erevmax.baseTest.TestBase.browser;
public class LoginPage {
	
	
	public By username;
	public By password;
	public By clickLogin;
	
	
	public LoginPage(){
		this.username=By.id("LoginAction_userName");
		this.password=By.id("LoginAction_password");
		this.clickLogin=By.id("LoginAction_Login");
		
		
	}
	
	
	public void doLogin(String uName, String passwd){
		browser.enterInput(username,uName);
		browser.enterInput(password,passwd);	
	}
	
	
	public void clickLoginButton(){
		browser.click(clickLogin);
	}

}
