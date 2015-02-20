package com.erevmax.selenium.PageObjects;

import static com.erevmax.baseTest.TestBase.browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.erevmax.mysample.Sample.WebPage;

public class ConfigDetailActionPage extends WebPage {
	
	public static ConfigDetailActionPage cdp=new ConfigDetailActionPage();
	
	public final By configTab=By.id("cg");
	public final By hotelcodeTxtBox=By.id("hotelCode");
	public final By hotelcodeAjaxOption=By.xpath("//select[@id='selectHotelCode']/option");
	public final By hotelnameTxtBox=By.id("hotelName");
	public final By hotelnameAjaxOption=By.xpath(".//*[@id='selectHotelName']/option");
	public final By cityTxtBox=By.id("city");
	public final By cityAjaxOption=By.xpath(".//*[@id='selectCity']/option[1]");
	public final By countryTxtBox=By.id("country");
	public final By countryAjaxOption=By.xpath(".//*[@id='selectCountry']/option[1]");
	public final By hotelAddressBox=By.id("hotelAddress");
	public final By continueButton=By.xpath("//form[@id='hotelSearchForm']/table/tbody/tr[8]/td/table/tbody/tr[1]/td[2]/a");
	public final By ResetButton=By.xpath("//form[@id='hotelSearchForm']/table/tbody/tr[8]/td/table/tbody/tr[1]/td[4]/a");
	public final By validationmsg=By.xpath("//form[@id='hotelSearchForm']/table/tbody/tr[8]/td/table/tbody/tr[2]/td");

	public final By addHotelChainLink = By.xpath("//form[@id='hotelSearchForm']/table/tbody/tr[3]/td[2]/a");
	public final By addHotelChainNameTxtBox = By.xpath("//div[@id='chainNameListing']/span/input[3]");
	public final By addHotelChainNameTxtBoxajaxlist = By.xpath("html/body/span/div[1]/b");
	public final By addHotelHotelNameTxtBox = By.xpath("//input[@id='hotelSearchForm_hotelName']");
	public final By addHotelHotelAddressTxtBox = By.xpath("//input[@id='hotelSearchForm_hotelAddress']");
	public final By addHotelCityTxtBox = By.xpath("//div[@id='newHotelCityListing']/span/input[3]");
	public final By addHotelCityTxtBoxajaxlist = By.xpath("html/body/span/div[1]");
	public final By addHotelStateTxtBox = By.xpath("//input[@id='hotelSearchForm_state']");
	public final By addHotelCountryTxtBox = By.xpath("//div[@id='newHotelCountryListing']/span/input[3]");
	public final By addHotelSortName = By.xpath("//input[@id='hotelSearchForm_hotelSName']");
	public final By addHotelsaveAndCloseButton = By.xpath("//a[contains(text(),'Save and Close')]");
	public final By addHotelsaveAndAddButton = By.xpath("//a[contains(text(),'Save and Add')]");
	public final By addHotelResetButton = By.xpath("//a[contains(text(),'Reset')]");

	public final By addCityLink = By.xpath("//form[@id='hotelSearchForm']/table/tbody/tr[4]/td[2]/a");
	public final By addCitySaveAndCloseBtn = By.xpath("//form[@id='addCityForm']/table[2]/tbody/tr[2]/td/table/tbody/tr[6]/td[2]/table/tbody/tr/td[2]/table/tbody/tr/td[1]/a");
	public final By addCitySaveAndAddBtn = By.xpath("//form[@id='addCityForm']/table[2]/tbody/tr[2]/td/table/tbody/tr[6]/td[2]/table/tbody/tr/td[2]/table/tbody/tr/td[2]/a");
	public final By addCityResetBtn = By.xpath("//form[@id='addCityForm']/table[2]/tbody/tr[2]/td/table/tbody/tr[6]/td[2]/table/tbody/tr/td[2]/table/tbody/tr/td[3]/a");
	public final By addCityNameTxtBox = By.id("cityName");
	public final By addCityCodeTxtBox = By.id("cityCode");
	public final By addCityAirportCodeTxtBox = By.id("cityAirportCode");
	public final By addCityVATPercentageTxtBox = By.id("cityVatPercent");
	public final By addCityCountryTxtBox = By.xpath("//div[@id='newHotelCountryListing']/span/input[3]");
	public final By addCityCountryAjaxList = By.xpath("html/body/span/div[1]/b");
	public final By addCityTaxPercentageTxtBox = By.id("cityTaxPercent");
	public final By addCityTaxAmountTxtBox = By.id("cityTaxAmount");
	public final By addCityPPersonPNIghtTxtBox = By.id("pPersonPNight");
	public final By addCityPNightPRoomTxtBox = By.id("pNightPRoom");

	public final By addCountryLink = By.xpath("//form[@id='hotelSearchForm']/table/tbody/tr[5]/td[2]/a");
	public final By addCountrySaveAndCloseBtn = By.xpath("//form[@id='addCountryForm']/table[2]/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/table/tbody/tr/td[1]/a");
	public final By addCountrySaveAndAddBtn = By.xpath("//form[@id='addCountryForm']/table[2]/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/table/tbody/tr/td[2]/a");
	public final By addCountryResetBtn = By.xpath("//form[@id='addCountryForm']/table[2]/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/table/tbody/tr/td[3]/a");
	public final By addCountryNameTxtBox = By.id("countryName");
	public final By addCountryCodeATxtBox = By.id("countryCodeA");
	public final By addCountryCodeNTxtBox = By.id("countryCodeN");
	public final By addCountryCurrencyTxtBox = By.id("countryCurr");
	public final By SetupPortalViewStatusLink = By.id("cg3");
		
	public final By partnerMappingSaveButton=By.xpath("//form[@id='editPartnerMapForm']/table/tbody/tr[5]/td/table/tbody/tr/td[1]/a");

	public void populateAddCityForm() throws InterruptedException{
		browser.findWebElement(addCityNameTxtBox).sendKeys("test city name");
		browser.findWebElement(addCityCodeTxtBox).sendKeys("12345");
		browser.findWebElement(addCityAirportCodeTxtBox).sendKeys("987");
		browser.findWebElement(addCityVATPercentageTxtBox).sendKeys("12");
		browser.findWebElement(addCityCountryTxtBox).sendKeys("india");
		
		WebDriverWait wait8 = new WebDriverWait(browser.getDriver(), 50);
		wait8.until(ExpectedConditions.visibilityOfElementLocated(addCityCountryAjaxList));

		browser.findWebElement(addCityCountryAjaxList).click();
		
		browser.findWebElement(addCityTaxPercentageTxtBox).sendKeys("12");
		browser.findWebElement(addCityTaxAmountTxtBox).sendKeys("5000");
		browser.findWebElement(addCityPPersonPNIghtTxtBox).sendKeys("2");
		browser.findWebElement(addCityPNightPRoomTxtBox).sendKeys("25");
	}

	public void populateAddCountryForm(){
		browser.findWebElement(addCountryNameTxtBox).sendKeys("test country name");
		browser.findWebElement(addCountryCodeATxtBox).sendKeys("123");
		browser.findWebElement(addCountryCodeNTxtBox).sendKeys("456");
		browser.findWebElement(addCountryCurrencyTxtBox).sendKeys("INR"); 
	}

/*
 * ************************************************************************************************************
 */
	

public final By hotelNameCodeDisplay=By.xpath("//div[@id='contentcolumn']/div/table[1]/tbody/tr/td[1]/strong");
public final By hotelAddressDisplay=By.xpath("//div[@id='contentcolumn']/div/table[1]/tbody/tr/td[2]");
public final By editHotelAddressBtn=By.xpath("//div[@id='contentcolumn']/div/table[1]/tbody/tr/td[3]/a");
public final By hotelAddressTxtBox=By.id("EditHotelAddressAction_hotelAddress");
public final By stateTxtBox =By.id("EditHotelAddressAction_state");
public final By zipCodeTxtBox =By.id("EditHotelAddressAction_zip");
public final By TelNoTxtBox =By.id("EditHotelAddressAction_phoneNumber");
public final By roomCountTxtBox = By.id("EditHotelAddressAction_roomCount");
public final By SaveHotelAddressBtn =By.xpath("//form[@id='EditHotelAddressAction']/table[2]/tbody/tr[6]/td[2]/table/tbody/tr/td[2]/table/tbody/tr/td[1]/a");
public final By ResetHotelAddressBtn =By.xpath("//form[@id='EditHotelAddressAction']/table[2]/tbody/tr[6]/td[2]/table/tbody/tr/td[2]/table/tbody/tr/td[3]/a");
public final By errorMsgLabel = By.id("messageDiv");

public void populateHotelAddress(String hAddress, String  hCity, String hState, String hCountry, String ZCode, String rCount) throws InterruptedException{
	browser.enterInput(hotelAddressTxtBox,hAddress);
	browser.enterInput(cityTxtBox,hCity);
	browser.waitForElementVisible(cityAjaxOption);
	browser.click(cityAjaxOption);		
	browser.doubleClickOn(cityAjaxOption);
	browser.enterInput(stateTxtBox,hState);
	browser.enterInput(zipCodeTxtBox,ZCode);
	browser.enterInput(roomCountTxtBox,rCount);
}


/*
 * ************************************************************************************************************
 */
public final By groupCodeDisplay=By.xpath("//tr[@id='DIV_357285_2']/td/table/tbody/tr/td[2]");
public final By clientFilterCodeDisplay=By.xpath("//tr[@id='DIV_357285_2']/td/table/tbody/tr/td[5]");
public final By clientReportDateDisplay=By.xpath("//tr[@id='DIV_357285_2']/td/table/tbody/tr/td[8]");
public final By historicalDisplay=By.xpath("//tr[@id='DIV_357285_2']/td/table/tbody/tr/td[14]");
public final By editSubscriptionDetailBtn=By.xpath("//tr[@id='DIV_357285']/td/table/tbody/tr/td[3]/a/span");

public final By groupCodeList=By.id("select");
public final By clientFilterCodeTxtBox =By.id("clientFilterCode");
public final By clientRepDatefield =By.id("clientRepDate");
public final By calendar_todaysDate =By.xpath("//table[@id='outerTable']/tbody/tr[3]/td/a");
public final By clientHistoricalYesBtn =By.id("clientHistoricalY");
public final By clientHistoricalNoBtn =By.id("clientHistoricalN");

public final By SaveSubscriptionDetailBtn =By.xpath("//form[@id='PopulateSubsAction']/table[3]/tbody/tr/td[2]/table/tbody/tr/td[1]/a");
public final By ResetSubscriptionDetailBtn =By.xpath("//form[@id='PopulateSubsAction']/table[3]/tbody/tr/td[2]/table/tbody/tr/td[2]/a");
		
public void populateSubscriptionDetails(String gCode, String  clientFltrCode, String historical) throws InterruptedException{		
	WebElement datePicker = browser.findWebElement(By.id("gToday:normal:agenda.js"));
	
	browser.selectFromDropDownList(groupCodeList,gCode);		
	browser.enterInput(clientFilterCodeTxtBox,clientFltrCode);		
	browser.click(clientRepDatefield);
	browser.elementWait(2000);
//	driver.switchTo().frame(0);
//	driver.switchTo().frame("gToday:normal:agenda.js");
//	driver.switchTo().frame(driver.findElement(By.id("gToday:normal:agenda.js")));
	browser.getDriver().switchTo().frame(datePicker);
	
	browser.click(calendar_todaysDate);
	browser.getDriver().switchTo().defaultContent();
	
	if(historical.equals("Yes")){
		browser.click(clientHistoricalYesBtn);
	}
	else if (historical.equals("No")){
		browser.click(clientHistoricalNoBtn);
	}				
}

/*
 * ***********************************************************************************************************
 */


public final By clientSourceView=By.xpath("//tr[@id='DIV_357286_0']/td/table/tbody/tr[2]/td[2]");
public final By clientStatusView=By.xpath("//tr[@id='DIV_357286_0']/td/table/tbody/tr[2]/td[3]");
public final By clientStartDateView=By.xpath("//tr[@id='DIV_357286_0']/td/table/tbody/tr[2]/td[4]");
public final By clientDueDateView=By.xpath("//tr[@id='DIV_357286_0']/td/table/tbody/tr[2]/td[5]");
public final By gracePeriodView=By.xpath("//tr[@id='DIV_357286_0']/td/table/tbody/tr[2]/td[6]");
public final By timeZoneView=By.xpath("//tr[@id='DIV_357286_0']/td/table/tbody/tr[2]/td[7]");
public final By noWebsites 	=By.xpath("//tr[@id='DIV_357286_0']/td/table/tbody/tr[2]/td[8]");
public final By noHotels=By.xpath("//tr[@id='DIV_357286_0']/td/table/tbody/tr[2]/td[9]");

public final By clientSourceTxtBox = By.id("clientSource"); 
public final By clientStatusList = By.id("clientStatus");;
public final By clientStartDateField = By.id("clientStartDate");;
public final By clientDueDateField = By.id("clientDueDate");;
public final By noWebsitesTxtBox = By.id("noOfWebsites");;
public final By noHotelsTxtBox = By.id("noOfHotels");;
public final By gracePeriodTxtBox = By.id("gracePeriod");;
public final By timeZoneList = By.id("mapTimeZone");;

public final By editProductDetailBtn=By.xpath("//a[@id='RTAIO']/img");
public final By SaveProductDetailBtn =By.xpath("html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[5]/td[2]/table/tbody/tr/td[1]/a");
public final By ReseProductDetailtBtn =By.xpath("html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[5]/td[2]/table/tbody/tr/td[3]/a");

public void populateProductDetails(String clSource, String  clStatus, String noOfWebsites, String noOfHotels, String grace, String timeZone) throws InterruptedException{		
	
	browser.enterInput(clientSourceTxtBox,clSource);
	//selectFromDropList(clientStatusList,clStatus);
	browser.selectFromDropDownList(clientStatusList,clStatus);
	browser.enterInput(noWebsitesTxtBox,noOfWebsites);
	browser.enterInput(noHotelsTxtBox,noOfHotels);
	browser.enterInput(gracePeriodTxtBox,grace);
	//selectFromDropList(timeZoneList,timeZone);
	browser.selectFromDropDownList(timeZoneList,timeZone);
			
}

/*
 * ***********************************************************************************************************
 */


public final By selectExistingContactLink=By.linkText("  Select Existing Contacts  ");
public final By Contact1_FName=By.xpath("//td[@title='Contact Details']/table/tbody/tr[2]/td[1]");
public final By Contact1_LName=By.xpath("//td[@title='Contact Details']/table/tbody/tr[2]/td[2]");
public final By Contact1_Email=By.xpath("//td[@title='Contact Details']/table/tbody/tr[2]/td[3]");
public final By Contact1_pwd=By.xpath("//td[@title='Contact Details']/table/tbody/tr[2]/td[7]");
public final By Contact1_Notifs=By.xpath("//td[@title='Contact Details']/table/tbody/tr[2]/td[10]");

public final By existingContactSearchBtn=By.xpath("//a[contains(text(),'Search')]");
public final By FNameTxtBox=By.id("firstName");
public final By LNameTxtBox=By.id("lastName");
public final By EmailTxtBox=By.id("email");
public final By editMember1Link = By.xpath("//form[@id='contactDetailform']//tr[2]/td[11]//img[@alt='Edit Members']");
public final By memberFnameTxtBox = By.name("firstName");
public final By memberLnameTxtBox = By.name("lastName");
public final By memberEmailTxtBox = By.name("email");
public final By memberPasswordTxtBox = By.name("password");
public final By resFailureChkBox = By.id("notification_3");
public final By editMember_SaveBtn = By.xpath("//a[contains(text(),'Save ')]");
	
public void editMemberDetails(String m_fname,String m_lname,String m_email,String m_pwd, String Notif_Type){
	
	browser.enterInput(memberFnameTxtBox,m_fname);
	browser.enterInput(memberLnameTxtBox,m_lname);
	browser.enterInput(memberEmailTxtBox,m_email);
	browser.enterInput(memberPasswordTxtBox,m_pwd);
	boolean res_failure_checkbox_status = browser.isSelected(resFailureChkBox);
	if (!res_failure_checkbox_status && Notif_Type.equals("RESERVATION_FAILURE")){
		browser.click(resFailureChkBox);
	}

	else if (res_failure_checkbox_status && !Notif_Type.equals("RESERVATION_FAILURE")){
		browser.click(resFailureChkBox);
	}
	
}
/*
 * ****************************************************************************************************
 */

public final By LOSView = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[1]/td/div/table[2]/tbody/tr[1]/td[2]");
public final By occupancyView = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[1]/td/div/table[2]/tbody/tr[1]/td[5]");
public final By noDaysInReport = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[1]/td/div/table[2]/tbody/tr[1]/td[8]");
public final By currencyView = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[1]/td/div/table[2]/tbody/tr[1]/td[11]");
public final By rateTypeView = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[1]/td/div/table[2]/tbody/tr[1]/td[14]");
public final By ipaShopLimitView = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[1]/td/div/table[2]/tbody/tr[2]/td[2]");
public final By ppShopLimitView = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[1]/td/div/table[2]/tbody/tr[2]/td[5]");
public final By throttlingApplicableView = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[1]/td/div/table[2]/tbody/tr[2]/td[8]");

public final By editPreferencesShopperBtn = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[1]/td/div/table[1]/tbody/tr/td[2]/a/span");

public final By LOSTxtBox = By.id("textfield11");
public final By occupancyTxtBox = By.id("textfield12");
public final By noDaysInReportTxtBox = By.id("textfield15");
public final By currencyDropList = By.id("select4");
public final By rateTypeDropList = By.id("select5");
public final By ipaShopLimitTxtBox = By.id("ipAnonShopLimit");
public final By ppShopLimitTxtBox = By.id("priceShopLimit");
public final By ThrottlingApplyYesRadioBtn = By.id("disableChannelsYes");
public final By ThrottlingApplyNoRadioBtn = By.id("disableChannelsNo");

public final By SavePreferencesShopperBtn = By.xpath("//form[@id='PopulateGenaralPreference']/table[3]/tbody/tr/td[3]/table/tbody/tr/td[1]/a");
public final By ResetPreferencesShopperBtn = By.xpath("//form[@id='PopulateGenaralPreference']/table[3]/tbody/tr/td[3]/table/tbody/tr/td[2]/a");


public void populateShopperPref(String LOS, String Occupancy,String noDaysinReport,String Currency,String rateType, String IPAShopLimit, String PPShopLimit, String throttlingApplicable){
	browser.enterInput(LOSTxtBox,LOS);
	browser.enterInput(occupancyTxtBox,Occupancy);
	browser.enterInput(noDaysInReportTxtBox,noDaysinReport);
	browser.selectFromDropDownList(currencyDropList,Currency);
	browser.selectFromDropDownList(rateTypeDropList,rateType);
	browser.enterInput(ipaShopLimitTxtBox,IPAShopLimit);
	browser.enterInput(ppShopLimitTxtBox,PPShopLimit);
	
	if(throttlingApplicable.equalsIgnoreCase("Yes")){
		browser.click(ThrottlingApplyYesRadioBtn);
	}
	else if(throttlingApplicable.equalsIgnoreCase("No")){
		browser.click(ThrottlingApplyNoRadioBtn);
	}
	
}
/*
 * ********************************************************************************************************
 */

public final By alertChangeValueDisplay=By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]");
public final By dailyMaxDaysDisplay=By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[2]/td/div/table[2]/tbody/tr/td[5]");
public final By changeAmountDisplay = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[2]/td/div/table[2]/tbody/tr/td[9]");
public final By editDailyRateAlertBtn = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[2]/td/div/table[1]/tbody/tr/td[2]/a/span");

public final By rateAlertYesRadioBtn = By.id("Rate_Alert_SubscribedYes");
public final By rateAlertNoRadioBtn = By.id("Rate_Alert_SubscribedNo");
public final By backgroundModuleYesRadioBtn = By.id("BackgroundYes");
public final By backgroundModuleNoRadioBtn = By.id("BackgroundNo");
public final By multipleReadyYesRadioBtn = By.id("Multiple_ReadyYes");
public final By multipleReadyNoRadioBtn = By.id("Multiple_ReadyNo");
public final By dailyReportYesRadioBtn = By.id("Daily_Report_SubscribedYes");
public final By dailyReportNoRadioBtn = By.id("Daily_Report_SubscribedNo");
public final By alertChangeValueTypeAmountRadioBtn = By.id("Alert_Change_Value_TypeAmount");
public final By alertChangeValueTypePercentageRadioBtn = By.id("Alert_Change_Value_TypePercentage");
public final By alertChangeValueTxtBox = By.id("Alert_Change_Value");
public final By dailyMaxDaysList = By.id("Daily_Max_Days");
public final By alertMaxDaysList = By.id("Alert_Max_days");
public final By changeAmountTxtBox = By.id("textfield12");
public final By SaveDailyRateAlertBtn = By.xpath("html/body/table[2]/tbody/tr[3]/td/table/tbody/tr/td[1]/a");
public final By ResetDailyRateAlertBtn = By.xpath("html/body/table[2]/tbody/tr[3]/td/table/tbody/tr/td[2]/a");

public final By ValidationMsglabel = By.xpath("//td[@id='message']/center");

	
public void populateDailyRateAlertPref(String rateAlert,String bgroundMod, String multReady, String DailyRep, String AlertChValType, String AlertChValue, String DailyMaxDays, String AlertMaxDays, String ChangeAmt) throws InterruptedException{
	if(rateAlert.equals("Yes")){
		browser.click(rateAlertYesRadioBtn);
	}
	else{
		browser.click(rateAlertNoRadioBtn);
	}
	
	if(bgroundMod.equals("Yes")){
		browser.click(backgroundModuleYesRadioBtn);
	}
	else{
		browser.click(backgroundModuleNoRadioBtn);
	}
	
	if(multReady.equals("Yes")){
		browser.click(multipleReadyYesRadioBtn);
	}
	else{
		browser.click(multipleReadyNoRadioBtn);
	}
	
	if(DailyRep.equals("Yes")){
		browser.click(dailyReportYesRadioBtn);
	}
	else{
		browser.click(dailyReportNoRadioBtn);
	}
	
	if(AlertChValType.equals("Amount")){
		browser.click(alertChangeValueTypeAmountRadioBtn);
	}
	else{
		browser.click(alertChangeValueTypePercentageRadioBtn);
	}
	
	browser.enterInput(alertChangeValueTxtBox,AlertChValue);		
	browser.selectFromDropDownList(dailyMaxDaysList,DailyMaxDays);
	browser.selectFromDropDownList(alertMaxDaysList,AlertMaxDays);
	browser.enterInput(changeAmountTxtBox,ChangeAmt);
			
}

/*
 * ****************************************************************************************************
 */

public final By alertDayView = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[4]/td/div/table[2]/tbody/tr/td[2]");
public final By alertTypeView = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[4]/td/div/table[2]/tbody/tr/td[5]");
public final By alertStatusView = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[4]/td/div/table[2]/tbody/tr/td[8]");

public final By editPreferencesRTReviewBtn = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[5]/td/div/table[1]/tbody/tr/td[2]/a/span");

public final By alertDayListBox = By.id("Review_Alert_Day");
public final By alertTypeListBox = By.id("Review_Alert_Type");
public final By alertStatusRadioBtn_Subscribed = By.id("Review_Alert_StatusSubscribed");
public final By alertStatusNoRadioBtn_Unsubscribed = By.id("Review_Alert_StatusUnsubscribed");


public final By SavePreferencesRTReviewBtn = By.xpath("//form[@id='PopulateRTReviewClientPref']/table[3]/tbody/tr/td[2]/table/tbody/tr/td[1]/a");
public final By ResetPreferencesRTReviewBtn = By.xpath("//form[@id='PopulateRTReviewClientPref']/table[3]/tbody/tr/td[2]/table/tbody/tr/td[2]/a");



public void populateRTReviewPref(String alertDay, String alertType,String alertStatus){

	browser.selectFromDropDownList(alertDayListBox,alertDay);
	browser.selectFromDropDownList(alertTypeListBox,alertType);
			
	if(alertStatus.equals("Subscribed")){
		browser.click(alertStatusRadioBtn_Subscribed);
	}
	else if(alertStatus.equals("Unsubscribed")){
		browser.click(alertStatusNoRadioBtn_Unsubscribed);
	}
	
}
/*
 * *********************************************************************************************************
 */
public final By allowOverbookingView = By.xpath("//div[@id='client-preference']//td[contains(text(),'Allow Overbooking:')]/following-sibling::td[1]");
public final By overrideAMLogicView = By.xpath("//div[@id='client-preference']//td[contains(text(),'Override AM Logic:')]/following-sibling::td[1]");
public final By PMSCRSdataStorageLogicView = By.xpath("//div[@id='client-preference']//td[contains(text(),'PMS/CRS Data Storage Logic:')]/following-sibling::td[1]");
public final By applyMultilegView = By.xpath("//div[@id='client-preference']//td[contains(text(),'Apply Multileg:')]/following-sibling::td[1]");
public final By deltaProcessingView = By.xpath("//div[@id='client-preference']//td[contains(text(),'Delta Processing:')]/following-sibling::td[1]");
public final By resyncWithPMSDBView = By.xpath("//div[@id='client-preference']//td[contains(text(),'Resync with PMS DB:')]/following-sibling::td[1]");
public final By storeDatainPMSDBatChannelLevelView = By.xpath("//div[@id='client-preference']//td[contains(text(),'Store data in PMS DB at Channel Level:')]/following-sibling::td[1]");
public final By editPreferencesRTConnectBtn = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[4]/td/div/table[1]/tbody/tr/td[2]/a/span");

public final By allowOverBkingYesRadio = By.id("Allow_OverbookingYes");
public final By allowOverBkingNoRadio = By.id("Allow_OverbookingNo");
public final By enableResPMSStorageYesRadio = By.id("enResrvPMStoreYes");
public final By enableResPMSStorageNoRadio = By.id("enResrvPMStoreNo");
public final By overrideAMLogicYesRadio = By.id("Is_AM_Logic_OverridenYes");
public final By overrideAMLogicNoRadio = By.id("Is_AM_Logic_OverridenNo");
public final By deliverAllResYesRadio = By.id("DeliverAllResrvYes");
public final By deliverAllResNoRadio = By.id("DeliverAllResrvNo");
public final By storeDatainPMSDBatChannelLevelYesRadio = By.id("travelSiteInfoYes");
public final By storeDatainPMSDBatChannelLevelNoRadio = By.id("travelSiteInfoNo");
public final By enableDeltaProcessingYesRadio = By.id("enDeltaProcessingYes");
public final By enableDeltaProcessingNoRadio = By.id("enDeltaProcessingNo");
public final By resyncwithPMSDBYesRadio = By.id("resyncPmsDbYes");
public final By resyncwithPMSDBNoRadio = By.id("resyncPmsDbNo");
public final By applyMultiLegList = By.id("applyMultileg");
public final By PMSCRSdataStorageLogicList = By.id("Data_Storage_Logic");


public final By SavePreferencesRTConnectBtn = By.xpath("//form[@id='PopulateRTCTClientPref']/table[2]/tbody/tr[6]/td[3]/table/tbody/tr/td[2]/a");
public final By ResetPreferencesRTConnectBtn = By.xpath("//form[@id='PopulateRTCTClientPref']/table[2]/tbody/tr[6]/td[3]/table/tbody/tr/td[3]/a");

public final By helpIcon = By.id("help");
public final By contlHelpHeader = By.xpath("html/body/table/tbody/tr[1]/td");

public void populateRTConnectPref(String storeDatainPMSDB_YN,String allowOverbooking_YN, String enResrvPMStore_YN,String overrideAMLogic_YN, String DeliverAllResrv_YN, String enDeltaProcessing_YN,String resyncPmsDb_YN,String applyMultilegvalue,String dataStorageLogicvalue){
	
	browser.selectFromDropDownList(applyMultiLegList,applyMultilegvalue);
	browser.selectFromDropDownList(PMSCRSdataStorageLogicList,dataStorageLogicvalue);
	
	
	System.out.println("1 In storeDatainPMSDBatChannelLevel");
	if(storeDatainPMSDB_YN.equals("Yes")){
		browser.click(storeDatainPMSDBatChannelLevelYesRadio);
	}
	else if(storeDatainPMSDB_YN.equals("No")){
		browser.click(storeDatainPMSDBatChannelLevelNoRadio);
	}
	
	System.out.println("2 In allowOverBking");
	if(allowOverbooking_YN.equals("Yes")){
		browser.click(allowOverBkingYesRadio);
	}
	else if(allowOverbooking_YN.equals("No")){
		browser.click(allowOverBkingNoRadio);
	}
	
	System.out.println("3 In enableResPMSStorage");
	if(enResrvPMStore_YN.equals("Yes")){
		browser.click(enableResPMSStorageYesRadio);
	}
	else if(enResrvPMStore_YN.equals("No")){
		browser.click(enableResPMSStorageNoRadio);
	}
	
	System.out.println("4 In overrideAMLogic");
	if(overrideAMLogic_YN.equals("Yes")){
		browser.click(overrideAMLogicYesRadio);
	}
	else if(overrideAMLogic_YN.equals("No")){
		browser.click(overrideAMLogicNoRadio);
	}
	
	System.out.println("5 In deliverAllRes");
	if(DeliverAllResrv_YN.equals("Yes")){
		browser.click(deliverAllResYesRadio);
	}
	else if(DeliverAllResrv_YN.equals("No")){
		browser.click(deliverAllResNoRadio);
	}
	
	
	System.out.println("6 In enableDeltaProcessing");
	if(enDeltaProcessing_YN.equals( "Enabled")){
		browser.click(enableDeltaProcessingYesRadio);
	}
	else if(enDeltaProcessing_YN.equals("Disabled")){
		browser.click(enableDeltaProcessingNoRadio);
	}
	
	System.out.println("7 In resyncwithPMSDB");
	if(resyncPmsDb_YN.equals("Yes")){
		browser.click(resyncwithPMSDBYesRadio);
	}
	else if(resyncPmsDb_YN .equals("No")){
		browser.click(resyncwithPMSDBNoRadio);
	}	
}
/*
 * ****************************************************************************************************
 */

public final By enCCView = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[6]/td/div/table[2]/tbody/tr/td[2]");
public final By retainCCNoForView = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[6]/td/div/table[2]/tbody/tr/td[5]");
public final By isGenericRoomTypeMigratedView = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[6]/td/div/table[2]/tbody/tr/td[8]");
public final By displayCCInfoView = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[6]/td/div/table[2]/tbody/tr/td[11]");

public final By editPreferencesReservationBtn = By.xpath("//tr[@id='DIV_357281_0']/td/table[2]/tbody/tr[6]/td/div/table[1]/tbody/tr/td[2]/a/span");

public final By enCCYesRadio = By.id("enControlCenterSuiteYes");
public final By enCCNoRadio = By.id("enControlCenterSuiteNo");
public final By en3PIRadio = By.id("enControlCenterSuite3PI");
public final By migrateGRTypeYesRadio = By.id("isGRMigratedYes");
public final By migrateGRTypeNoRadio = By.id("isGRMigratedNo");
public final By retainCCNoForDaysTxtBox = By.id("showCCardDays");
public final By displayCCInfoYesRadio = By.id("Display_CreditCard_InfoYes");
public final By displayCCInfoNoRadio = By.id("Display_CreditCard_InfoNo");

public final By SavePreferencesReservationBtn = By.xpath("//form[@id='PopulateRTSuiteClientPref']/table[3]/tbody/tr/td[2]/table/tbody/tr/td[1]/a");
public final By ResetPreferencesReservationBtn = By.xpath("//form[@id='PopulateRTSuiteClientPref']/table[3]/tbody/tr/td[2]/table/tbody/tr/td[2]/a");


public void populateReservationPref(String enableCC_YN,String migrateGRType_YN,String retainCCNoForDays,String displayCCInfo_YN){

	if(enableCC_YN.equals("Enabled")){
		browser.click(enCCYesRadio);
	}
	else if(enableCC_YN.equals("Disabled")){
		browser.click(enCCNoRadio);
	}
	else if(enableCC_YN.equals("3PI")){
		browser.click(en3PIRadio);
	}
			
	
	if(migrateGRType_YN.equals("Yes")){
		browser.click(migrateGRTypeYesRadio);
	}
	else if(migrateGRType_YN.equals("No")){
		browser.click(migrateGRTypeNoRadio);
	}
	
	browser.enterInput(retainCCNoForDaysTxtBox,retainCCNoForDays);
	
	if(displayCCInfo_YN.equals("Yes")){
		browser.click(displayCCInfoYesRadio);
	}
	else if(displayCCInfo_YN.equals("No")){
		browser.click(displayCCInfoNoRadio);
	}
							
}
/*
 * **************************************************************************************************
 */

public final By editCompetitorHotelBtn = By.xpath("//form[@id='competitorHotelsform1']//a[contains(text(),'Edit')]");
public final By SaveCompetitorHotelBtn = By.xpath("//a[contains(text(),'Save')]");
public final By ResetCompetitorHotelBtn = By.xpath("//a[contains(text(),'Reset')]");

public final By compHotelShoppingTab = By.xpath("//ul[@id='competitor-menu']/li/a");


}
