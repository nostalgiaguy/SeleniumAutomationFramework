package com.erevmax.selenium.RTAdmin;

import org.openqa.selenium.By;

public class xpath_repository {
	/*
//	public static final String DATAXLS_PATH = "C:\\Documents and Settings\\anisa\\workspace\\RTAdminProject\\Excel File\\Data.xls";
//	public static final String DATAXLS_PATH = "user.dir\\RTAdmin\\TestData\\Data.xls";
	public static final String DATAXLS_PATH = System.getProperty("user.dir").concat("/TestData/Data.xls");
//	System.getProperty("user.dir").concat("/resources/controlcentre.properties");
	public static final String LOGINPAGE_USERNAME_TXTBOX = "//input[@id='LoginAction_userName']";
	public static final String LOGINPAGE_PASSWORD_TXTBOX = "//input[@id='LoginAction_password']";
	public static final String LOGINPAGE_LOGIN_BUTTON = "//input[@id='LoginAction_Login']";
	public static final String CONFIG_TAB = "//a[@id='cg']";
	public static final String CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX = "//input[@id='hotelCode']";
	public static final String CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX_AJAXLISTOPTION = "//select[@id='selectHotelCode']/option";
	public static final String CLIENTHOTEL_SEARCH_CONTINUE_BUTTON = "//form[@id='hotelSearchForm']/table/tbody/tr[8]/td/table/tbody/tr[1]/td[2]/a";
	*/
	public static final By CONFIG_TAB = By.xpath("//a[@id='cg']");
	public static final By CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX = By.xpath("//input[@id='hotelCode']");
	public static final By CLIENTHOTEL_SEARCH_HOTELCODE_TXTBOX_AJAXLISTOPTION =By.xpath("//select[@id='selectHotelCode']/option");
	public static final By CLIENTHOTEL_SEARCH_CONTINUE_BUTTON = By.xpath("//form[@id='hotelSearchForm']/table/tbody/tr[8]/td/table/tbody/tr[1]/td[2]/a");


}
