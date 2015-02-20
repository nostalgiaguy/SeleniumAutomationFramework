package com.erevmax.dashboard.tests;

import static com.erevmax.mysample.Sample.DatabaseMethods.*;

import java.text.DecimalFormat;

import org.openqa.selenium.By;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.erevmax.RTAdmin.pages.AssignmentPage;
import com.erevmax.RTAdmin.pages.DashboardPage;
import com.erevmax.baseTest.DateTest;
import com.erevmax.baseTest.TestBase;
import com.erevmax.utils.Verify;


public class DashboardTests extends TestBase {
	
	AssignmentPage ap= new AssignmentPage();
	DateTest date=new DateTest();
	
	DashboardPage dp=new DashboardPage();

	@Test (description="navigating to dashboard view page")
	public void dashboardNavigationTest(){
		//Navigating to menu page.
		browser.click(ap.othersMenu);
		System.out.println("Current url is :" +browser.getCurrentUrl());
		browser.elementWait(3000);
		browser.click(dp.dashboardView);
		browser.click(dp.dashboardView);
		System.out.println("Next Current url is :" +browser.getCurrentUrl());
		int status =browser.sendHttpGetRequest(browser.getCurrentUrl());
		Verify.assertEquals(status,200);
	}

	@Test(description="Validating the current date in dash board view page",
			dependsOnMethods="dashboardNavigationTest")
	public void currentDateTest(){
		
		String currentDashboardDate=browser.getText(dp.currentDate);
		String today_date=date.todayDate();
		System.out.println("currentDashboardDate:"+currentDashboardDate);
		System.out.println("today_date:"+today_date);
		Verify.assertEquals(currentDashboardDate, today_date);
	}


	@Test(description="Validating the total cities count displayed with database in dash board view page.",
			dependsOnMethods="dashboardNavigationTest")
	public void totalCityTest(){
		browser.findWebElement(dp.refresh).click();
		dp.expand();
		String expected=browser.getText(dp.cityCovered);
		int actual=dp.totalCity();
		System.out.println("actual num of city:"+actual);
		System.out.println("total city by dashboard:"+expected);
		Verify.assertEquals("Cities Covered ("+actual+")", expected);
		int totalCityDisplayed_DB=checkDataBaseForInteger(dp.query_totalCityCount, "totalCityCount");
		Verify.assertEquals(actual, totalCityDisplayed_DB);
		boolean flag=false;
		if(actual>0){
			flag=true;
		}
		Verify.assertTrue(flag);
	}

	@Test(description="Validating the overall report counts with cities reports count and database should be equal",
			dependsOnMethods={"dashboardNavigationTest","totalCityTest"})
	public void totalReportCountTest(){
		browser.findWebElement(dp.refresh).click();
		dp.collapse();
		int totalReportCount=dp.totalReportCount();
		int totalReportDisplayed=Integer.parseInt(browser.getText(dp.totalNumOfReport));
		System.out.println("total report count:"+totalReportCount+"  total report displayed:"+totalReportDisplayed);
		Verify.assertEquals(totalReportCount,totalReportDisplayed );
		int totalReportDisplayed_DB=checkDataBaseForInteger(dp.query_totalReportDisplayed, "totalReports");
		Verify.assertEquals(totalReportDisplayed, totalReportDisplayed_DB);
	}

	@Test(description="Validating the overall total volume data fetched should equals database volume of data fetched",
			dependsOnMethods={"dashboardNavigationTest","totalCityTest"})
	public void totalDataTest(){
		dp.expand();
		int expected_data=dp.totalDataVolumeDisplayed();
		System.out.println("displayed data volume:"+expected_data);
		int totalDataDisplayed_DB=checkDataBaseForInteger(dp.query_totalReportDisplayed, "totalData");
		System.out.println("displayed data volume in DB:"+totalDataDisplayed_DB);
		Verify.assertEquals(expected_data, totalDataDisplayed_DB);
	}
	
	@Test(description="Validating the overall report already sents with database reports already sent should be equal",
			dependsOnMethods={"dashboardNavigationTest","totalCityTest"})
	public void reportAlreadySentTest(){
		browser.findWebElement(dp.refresh).click();
		dp.expand();
		int actual_reportAlreadySentCount=dp.totalReportAlreadySentCount();
		int expected_reportAlreadySentDisplayed=dp.totalReportAlreadySentDisplay();
		System.out.println("actual_reportAlreadySentCount:"+actual_reportAlreadySentCount);
		System.out.println("expected_reportAlreadySentDisplayed:"+expected_reportAlreadySentDisplayed);
		Verify.assertEquals(actual_reportAlreadySentCount, expected_reportAlreadySentDisplayed);
		int reportAlreadySent_DB=checkDataBaseForInteger(dp.query_totalReportDisplayed, "reportsAlreadySent");
		Verify.assertEquals(expected_reportAlreadySentDisplayed, reportAlreadySent_DB);
		
	}
	
	@Test(description="Validating the overall report pending with database reports pending should be equal",
			dependsOnMethods={"dashboardNavigationTest","totalCityTest"})
	public void reportPendingTest(){
		browser.findWebElement(dp.refresh).click();
		dp.expand();
		int actual_reportPendingCount=dp.totalReportPendingCount();
		int expected_reportPendingDisplayed=dp.totalReportPendingDisplay();
		System.out.println("actual_reportPendingCount:"+actual_reportPendingCount);
		System.out.println("expected_reportPendingDisplayed:"+expected_reportPendingDisplayed);
		Verify.assertEquals(actual_reportPendingCount, expected_reportPendingDisplayed);
		int reportsPending_DB=checkDataBaseForInteger(dp.query_totalReportDisplayed, "reportsPending");
		Verify.assertEquals(expected_reportPendingDisplayed, reportsPending_DB);
	}
	
	@Test(description="Validating the overall report to be sent with database reports to be sent should be equal",
			dependsOnMethods={"dashboardNavigationTest","totalCityTest"})
	public void reportToBeSentTest(){
		browser.findWebElement(dp.refresh).click();
		dp.expand();
		int actual_reportToBeSentCount=dp.totalReportToBeSentCount();
		int expected_reportToBeSentDisplayed=dp.totalReportToBeSentDisplay();
		System.out.println("actual_reportToBeSentCount:"+actual_reportToBeSentCount);
		System.out.println("expected_reportToBeSentDisplayed:"+expected_reportToBeSentDisplayed);
		Verify.assertEquals(actual_reportToBeSentCount, expected_reportToBeSentDisplayed);
		int reportsToBeSent_DB=checkDataBaseForInteger(dp.query_totalReportDisplayed, "reportsToBeSent");
		Verify.assertEquals(expected_reportToBeSentDisplayed, reportsToBeSent_DB);
	}

 

	@Test(dataProvider="cityName",description="Validating the city report showing is equal to city name user input ",
			dependsOnMethods={"dashboardNavigationTest","totalCityTest"})
	public void reportPerCity(String cityName){
		browser.findWebElement(dp.refresh).click();
		int key=dp.getCityIndex(cityName);
		if(key>0){

			browser.autoSelect(dp.enterCityName,dp.selectCityName, cityName);
			String expected_cityName=dp.getCityName(key);
			System.out.println("selected_cityName:"+cityName);
			System.out.println("expected_cityName:"+expected_cityName);
			Verify.assertEquals(cityName, expected_cityName);

			String xpath="//*[@id='DIV_";
			String xpath1="/td/table/tbody/tr/td[3]/table/tbody/tr/td[1]/a";
			int report_already_sent=Integer.parseInt(browser.getText(By.xpath(xpath+key+"_0']"+dp.report_already_sent_path)));
			int report_pending=Integer.parseInt(browser.getText(By.xpath(xpath+key+"_0']"+dp.report_pending_path)));
			int report_to_be_sent=Integer.parseInt(browser.getText(By.xpath(xpath+key+"_0']"+dp.report_to_be_sent_path)));
			int total_report_count=Integer.parseInt(browser.getText(By.xpath(xpath+key+"']"+xpath1)));
			int expected_count=report_already_sent+report_pending+report_to_be_sent;
			Verify.assertEquals(total_report_count, expected_count);

			int totalReportCountPerCity_DB=checkDataBaseForInteger(dp.query1_totalReportCountPerCity+cityName+dp.query2_totalReportCountPerCity, "totalReportCountPerCity");
			Verify.assertEquals(expected_count, totalReportCountPerCity_DB);	

			double dataPerCityDisplayed=Integer.parseInt(browser.getText(By.xpath(xpath+key+"_0']"+dp.data_volume_path)).replaceAll(",", ""));
			double dataPerCity_DB=checkDataBaseForInteger(dp.query1_totalDataCountPerCity+cityName+dp.query2_totalDataCountPerCity, "totalDataPerCity");
			System.out.println(dataPerCityDisplayed+"  "+dataPerCity_DB);
			Verify.assertEquals(dataPerCityDisplayed, dataPerCity_DB);

			double dataAsRatesPerCity_DB=checkDataBaseForInteger(dp.query1_dataAsRatesPerCity+cityName+dp.query2_dataAsRatesPerCity,"dataAsRatesPerCity");
			double actual_dataAsRatesPerCityDisplayed=Double.parseDouble(browser.getText(By.xpath(xpath+key+"_0']"+dp.dataAsRatePath)).replaceAll("%", ""));
			double temp=(dataAsRatesPerCity_DB/dataPerCityDisplayed)*100;
			double expected=Double.parseDouble(new DecimalFormat("##.##").format(temp));
			System.out.println("actual_dataAsRatesPerCityDisplayed:"+actual_dataAsRatesPerCityDisplayed);
			System.out.println("expected_calculation"+expected);
			Verify.assertEquals(actual_dataAsRatesPerCityDisplayed, expected);

			double dataAsCEPerCity_DB=checkDataBaseForInteger(dp.query1_dataAsCEPerCity+cityName+dp.query2_dataAsCEPerCity,"dataAsCEPerCity");
			double actual_dataAsCEPerCityDisplayed=Double.parseDouble(browser.getText(By.xpath(xpath+key+"_0']"+dp.dataAsCEpath)).replaceAll("%", ""));
			double temp_1=(dataAsCEPerCity_DB/dataPerCityDisplayed)*100;
			double expected_1=Double.parseDouble(new DecimalFormat("##.##").format(temp_1));
			System.out.println("actual_dataAsCEPerCityDisplayed:"+actual_dataAsCEPerCityDisplayed);
			System.out.println("expected_calculation"+expected_1);
			Verify.assertEquals(actual_dataAsCEPerCityDisplayed, expected_1);


			double dataAsNFPerCity_DB=checkDataBaseForInteger(dp.query1_dataAsNFPerCity+cityName+dp.query2_dataAsNFPerCity,"dataAsNFPerCity");
			double actual_dataAsNFPerCityDisplayed=Double.parseDouble(browser.getText(By.xpath(xpath+key+"_0']"+dp.dataAsNFpath)).replaceAll("%", ""));
			double temp_2=(dataAsNFPerCity_DB/dataPerCityDisplayed)*100;
			double expected_2=Double.parseDouble(new DecimalFormat("##.##").format(temp_2));
			System.out.println("actual_dataAsNFPerCityDisplayed:"+actual_dataAsNFPerCityDisplayed);
			System.out.println("expected_calculation"+expected_2);
			Verify.assertEquals(actual_dataAsNFPerCityDisplayed, expected_2);
		}
		else{
			System.out.println("city is not present.");
		}

	}
	@DataProvider	
	public Object[][] cityName(){
		Object [][] data= new Object[3][1];
		data[0][0]=dp.getCityName(1);

		data[1][0]=dp.getCityName(2);

		data[2][0]=dp.getCityName(3);	
		return data;
	}
		
	@Test(description="Validating the overall data as rates with database should be equal",
			dependsOnMethods={"dashboardNavigationTest","totalCityTest"})
	public void dataAsRatesTest(){

		double  totalDataVolume=dp.totalDataVolumeDisplayed();
		double dataAsRates_DB=checkDataBaseForInteger(dp.query_totalReportDisplayed, "dataAsRates");
		double actual_dataAsRatesDisplayed=dp.dataAsRates_CE_NF_displayed(dp.dataAsRatePath);
		double temp=(dataAsRates_DB/totalDataVolume)*100;
		double expected=Double.parseDouble(new DecimalFormat("##.##").format(temp));
		System.out.println("actual_dataAsRatesDisplayed:"+actual_dataAsRatesDisplayed);
		System.out.println("expected_calculation"+expected);
		Verify.assertEquals(actual_dataAsRatesDisplayed, expected);

	}

	@Test(description="Validating the overall data as CE with database should be equal",
			dependsOnMethods={"dashboardNavigationTest","totalCityTest"})
	public void dataAsCETest(){

		double  totalDataVolume=dp.totalDataVolumeDisplayed();
		double dataAsCE_DB=checkDataBaseForInteger(dp.query_totalReportDisplayed, "dataAsCE");
		double actual_dataAsCEDisplayed=dp.dataAsRates_CE_NF_displayed(dp.dataAsCEpath);
		double temp=(dataAsCE_DB/totalDataVolume)*100;
		double expected=Double.parseDouble(new DecimalFormat("##.##").format(temp));
		System.out.println("actual_dataAsCEDisplayed:"+actual_dataAsCEDisplayed);
		System.out.println("expected_calculation"+expected);
		Verify.assertEquals(actual_dataAsCEDisplayed, expected);
	}

	@Test(description="Validating the overall data as NF with database should be equal",
			dependsOnMethods={"dashboardNavigationTest","totalCityTest"})
	public void dataAsNFTest(){

		double  totalDataVolume=dp.totalDataVolumeDisplayed();
		double dataAsNF_DB=checkDataBaseForInteger(dp.query_totalReportDisplayed, "dataAsNF");
		double actual_dataAsNFDisplayed=dp.dataAsRates_CE_NF_displayed(dp.dataAsNFpath);
		double temp=(dataAsNF_DB/totalDataVolume)*100;
		double expected=Double.parseDouble(new DecimalFormat("##.##").format(temp));
		System.out.println("actual_dataAsNFDisplayed:"+actual_dataAsNFDisplayed);
		System.out.println("expected_calculation"+expected);
		Verify.assertEquals(actual_dataAsNFDisplayed, expected);
	}
	
}

/*
@Test(dependsOnMethods="dashboardNavigationTest")
public void goToCEPage(){
	browser.click(By.xpath("//*[@id='DIV_357286_0']/td/table/tbody/tr[2]/td[3]/table/tbody/tr[1]/td[2]/a"));
}
*/
