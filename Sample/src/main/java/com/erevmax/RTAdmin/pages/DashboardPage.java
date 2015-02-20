package com.erevmax.RTAdmin.pages;
import static com.erevmax.baseTest.TestBase.browser;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.erevmax.baseTest.DateTest;
import com.erevmax.mysample.Sample.WebPage;




public class DashboardPage extends WebPage {
	
	DateTest date=new DateTest();
	public String today_date=date.todayDateForDashboardDB();
	
	public By refresh;
	public By enterCityName;
	public By selectCityName;
	public By currentDate;
	public By totalNumOfReport;
	public By dashboardView; 
	public By expandCity;
	public By cityCovered;
	public By reportPerCity;
	public By fullReport;
	public String total_path="//tr[starts-with(@id, 'DIV_')]";
	
	
	public String dataAsRatePath="/td/table/tbody/tr[2]/td[1]/table/tbody/tr[2]/td[2]";
	public String dataAsCEpath="/td/table/tbody/tr[2]/td[3]/table/tbody/tr[1]/td[2]/a";
	public String dataAsNFpath="/td/table/tbody/tr[2]/td[3]/table/tbody/tr[2]/td[2]/a";
	public String data_volume_path="/td/table/tbody/tr[2]/td[1]/table/tbody/tr[1]/td[2]";
	                              
	public String report_already_sent_path="/td/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td[2]/table/tbody/tr/td[1]/a";
	public String report_pending_path="/td/table/tbody/tr[1]/td[1]/table/tbody/tr[2]/td[2]/table/tbody/tr/td[1]/a";
	public String report_to_be_sent_path="/td/table/tbody/tr[1]/td[1]/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/a";
	public String query1_totalDataCountPerCity="select(select COUNT(*) from RT_RATE_MAST_NEW WITH(NOLOCK) where Rate_Base_Date='"+today_date+"'  AND hotel_code IN (SELECT RT_HOTEL_MAST.Hotel_Code FROM dbo.RT_HOTEL_MAST where Hotel_City='";
	public String query2_totalDataCountPerCity="')) as totalDataPerCity";
	
	public String query1_dataAsRatesPerCity="select(select count(1) from RT_RATE_MAST_NEW WITH(NOLOCK) where Rate_Base_Date='"+today_date+"'and (Rate_Onsite not in (-1.00, 0.0) or Rate_Net not in (-1.00, 0.0) or Rate_Gross not in (-1.00, 0.0))AND hotel_code IN (SELECT RT_HOTEL_MAST.Hotel_Code FROM dbo.RT_HOTEL_MAST where Hotel_City='";
	public String query2_dataAsRatesPerCity="')) as dataAsRatesPerCity";
			 
	public String query1_dataAsCEPerCity="SELECT(select count(1) from RT_RATE_MAST_NEW WITH(NOLOCK) where Rate_Base_Date='"+today_date+"' and (Rate_Onsite in (-1.00) or Rate_Net in (-1.00) or Rate_Gross in (-1.00)) AND hotel_code IN (SELECT RT_HOTEL_MAST.Hotel_Code FROM dbo.RT_HOTEL_MAST where Hotel_City='";
	public String query2_dataAsCEPerCity="')) as dataAsCEPerCity";
	
	public String query1_dataAsNFPerCity="select(select count(1) from RT_RATE_MAST_NEW WITH(NOLOCK) where Rate_Base_Date='"+today_date+"' and (Rate_Onsite in (0.0) or Rate_Net in (0.0) or Rate_Gross in (0.0)) AND hotel_code IN (SELECT RT_HOTEL_MAST.Hotel_Code FROM dbo.RT_HOTEL_MAST where Hotel_City='";
	public String query2_dataAsNFPerCity="')) as dataAsNFPerCity";
	
	public String query1_totalReportCountPerCity="select(SELECT COUNT(*) FROM dbo.RT_MR_SCHEDULE_RECURRENCE where Report_Base_Date ='"+today_date+"' AND hotel_code IN (SELECT Hotel_Code from dbo.RT_MR_SCHEDULE_RECURRENCE where Hotel_Code IN (SELECT RT_HOTEL_MAST.Hotel_Code FROM dbo.RT_HOTEL_MAST where Hotel_City='";
	public String query2_totalReportCountPerCity="'))) as totalReportCountPerCity";
	public String query_totalReportDisplayed="select (select count(1) from RT_MR_SCHEDULE_RECURRENCE WITH(NOLOCK) where Report_Base_Date='"+today_date+"') as totalReports, (select count(1) from RT_MR_SCHEDULE_RECURRENCE WITH(NOLOCK) where Report_Base_Date='"+today_date+"' and Status='COMPLETED' and Report_Send_DateTime is not null) as reportsAlreadySent, (select count(1) from RT_MR_SCHEDULE_RECURRENCE WITH(NOLOCK) where Report_Base_Date='"+today_date+"' and Status!='COMPLETED') as reportsPending, (select count(1) from RT_MR_SCHEDULE_RECURRENCE WITH(NOLOCK) where Report_Base_Date='"+today_date+"' and Status='COMPLETED' and Report_Send_DateTime is null) as reportsToBeSent, (select count(1) from RT_RATE_MAST_NEW WITH(NOLOCK) where Rate_Base_Date='"+today_date+"') as totalData, (select count(1) from RT_RATE_MAST_NEW WITH(NOLOCK) where Rate_Base_Date='"+today_date+"' and (Rate_Onsite not in (-1.00, 0.0) or Rate_Net not in (-1.00, 0.0) or Rate_Gross not in (-1.00, 0.0))) as dataAsRates, (select count(1) from RT_RATE_MAST_NEW WITH(NOLOCK) where Rate_Base_Date='"+today_date+"' and (Rate_Onsite in (-1.00) or Rate_Net in (-1.00) or Rate_Gross in (-1.00))) as dataAsCE,(select count(1) from RT_RATE_MAST_NEW WITH(NOLOCK) where Rate_Base_Date='"+today_date+"' and (Rate_Onsite in (0.0) or Rate_Net in (0.0) or Rate_Gross in (0.0))) as dataAsNF";
	public String query_totalCityCount="select(SELECT  count(DISTINCT Hotel_City) FROM dbo.RT_HOTEL_MAST WHERE Hotel_Code IN (SELECT Hotel_Code FROM dbo.RT_MR_SCHEDULE_RECURRENCE where Report_Base_Date ='"+today_date+"'))as totalCityCount";
	
	
	public DashboardPage(){
		this.refresh=By.linkText("Refresh");
		this.enterCityName=By.id("citiesCoveredText");
		this.selectCityName=By.id("selectCitiesCovered");
		this.currentDate=By.id("currentDateLable");
		this.dashboardView=By.id("ot8");
		this.expandCity=By.xpath("//img[starts-with(@id, 'EC_')]");
		this.cityCovered=By.xpath("//*[@id='xsnazzy']/div/table[2]/tbody/tr[3]/td/table[2]/tbody/tr[1]/td[1]/strong");
		this.fullReport=By.xpath("//div[starts-with(@id, 'City_DIV_')]");
		this.reportPerCity=By.xpath("//div[starts-with(@id, 'City_DIV_')]//td//tr//a");
		this.totalNumOfReport=By.xpath("//tr[starts-with(@id, 'DIV_')]//td//tr//a");
	}
	
	public void expand(){
		List<WebElement> list = browser.findWebElements(expandCity);
		int j=0;
		while(j<list.size()){
			if(list.get(j).getAttribute("src").equals(browser.bangalore_URL+"Icon-Minus.png")){
			}else {
				((WebElement) list.get(j)).click();
			}
		j++;	
		}
	}
	
	public void collapse(){
		List<WebElement> list = browser.findWebElements(expandCity);
		int j=0;
		while(j<list.size()){
			if(list.get(j).getAttribute("src").equals(browser.bangalore_URL+"Icon-Plus.png")){
			}else {
				((WebElement) list.get(j)).click();
			}
		j++;	
		}
	}
	public int totalCity(){
		int size=browser.getListSize(expandCity);
		
		List<WebElement> full_report = browser.findWebElements(fullReport);
		int k=0;
		while(k<full_report.size()){
			//System.out.print(((WebElement)full_report.get(k)).getText());
			k++;
			//System.out.println();
		}
		return size;
	}
	
	public int totalReportCount(){
		
		int z1=0;
		List<WebElement> city = browser.findWebElements(reportPerCity);
		for(int z=0;z<city.size();z++){
			String text=(((WebElement)city.get(z)).getText());
			if(text.equals("") ||  text.equals(null))
				continue;
			int count=Integer.parseInt(text);
			 z1=z1+count;
		}
		return z1;
	}
	
	public int totalDataVolumeDisplayed(){
		String value=browser.getText(By.xpath(total_path+data_volume_path));
		String regex = "(?<=[\\d])(,)(?=[\\d])";
		Pattern p = Pattern.compile(regex);
		String str = value;
		Matcher m = p.matcher(str);
		str = m.replaceAll("");
		int totalData=Integer.parseInt(str);
		return totalData;
	}

public double dataAsRates_CE_NF_displayed(String pathToData){
	String value=browser.getText(By.xpath(total_path+pathToData));
	String temp = value.replaceAll("%", "");
	double count=Double.parseDouble(temp);
	return count;
}


	
	public int totalReportAlreadySentCount(){
		
		List<WebElement> city = browser.findWebElements(expandCity);
		int count=0;
		int j=0;
		while(j<city.size()){
			String text=browser.getText(By.xpath("//*[@id='DIV_"+(j+1)+"_0']"+report_already_sent_path));
			int num=Integer.parseInt(text);
			count=count+num;			
			j++;
		}
		return count;
	}
	
	public int totalReportAlreadySentDisplay(){
		String text=browser.getText(By.xpath(total_path+report_already_sent_path));
		int num=Integer.parseInt(text);
		return num;
	}
	
	public int totalReportPendingCount(){
	
		List<WebElement> city = browser.findWebElements(expandCity);
		int count=0;
		int j=0;
		while(j<city.size()){
			String text=browser.getText(By.xpath("//*[@id='DIV_"+(j+1)+"_0']"+report_pending_path));
			int num=Integer.parseInt(text);
			count=count+num;
			j++;
		}
		return count;
	}
	public int totalReportPendingDisplay(){
		String text=browser.getText(By.xpath(total_path+report_pending_path));
		int num=Integer.parseInt(text);
		return num;
	}
	
	public int totalReportToBeSentCount(){
		
		List<WebElement> city = browser.findWebElements(expandCity);
		int count=0;
		int j=0;
		while(j<city.size()){
			String text=browser.getText(By.xpath("//*[@id='DIV_"+(j+1)+"_0']"+report_to_be_sent_path));
			int num=Integer.parseInt(text);
			count=count+num;
			j++;
		}
		return count;
	}
	public int totalReportToBeSentDisplay(){
		String text=browser.getText(By.xpath(total_path+report_to_be_sent_path));
		int num=Integer.parseInt(text);
		return num;
	}
	
	public int getCityIndex(String cityName){
		int size=browser.getListSize(expandCity);
		Map<String,Integer> list=new HashMap<String,Integer>();	
		int key=0;
		int k=1;
		while(k<=size){
		String value=browser.getText(By.xpath("//*[@id='DIV_"+k+"']/td/table/tbody/tr/td[2]"));
		list.put(value, new Integer(k));
		k++;
		}
		if(list.containsKey(cityName)){
		 key= list.get(cityName);
		}
		else{
			key=-1;
		}
		return key;
	}

	public String getCityName(int key){
		int size=browser.getListSize(expandCity);
		if(key>=1 && key<=size){
		String str=null;
		Map<Integer, String> list=new HashMap<Integer, String>();
		int k=1;
		while(k<=size){
			String value=browser.getText(By.xpath("//*[@id='DIV_"+k+"']/td/table/tbody/tr/td[2]"));
			list.put(new Integer(k),value);
			k++;
		}	
		if(list.containsKey(key)){
			str = list.get(key);
		}
		return str;
		}
		else{
			return "key is not present";
		}
	}
}
/*
public int totalDataCount(){
	
	
	int data=0;
	List<WebElement> city = browser.findListOfWebElements(expandCity);
	int k=0;
	while(k<city.size()){
		String value=browser.getTextValue(By.xpath("//*[@id='DIV_"+(k+1)+"_0']"+data_volume_path));
		String regex = "(?<=[\\d])(,)(?=[\\d])";
		Pattern p = Pattern.compile(regex);
		String str = value;
		Matcher m = p.matcher(str);
		str = m.replaceAll("");
		int count=Integer.parseInt(str);
		data=data+count;
		k++;
	}
	return data;
}
*/
/*	
public double totalDataAsRates_CE_NF(String pathToData){
		
		
		double data=0;
		List<WebElement> city = browser.findListOfWebElements(expandCity);
		int k=0;
		while(k<city.size()){
			String value=browser.getTextValue(By.xpath("//*[@id='DIV_"+(k+1)+"_0']"+pathToData));
			String temp = value.replaceAll("%", "");
			double count=Double.parseDouble(temp);
			data=data+count;
			k++;
		}
		data=(double)(data/city.size());
		String format_data=new DecimalFormat("##.##").format(data);
		return Double.parseDouble(format_data);
	}
*/
/*
public String getCityName(String cityName){
	return browser.getTextValue(By.xpath("//div[starts-with(@id, 'City_DIV_"+cityName+"')]"+"//td/table/tbody/tr/td[2]"));
}
*/
/*
public List getCityName(){
	int size=browser.getNumOfRows(expandCity);
	List<String> list=new ArrayList<String>();
	List<WebElement> city= browser.findListOfWebElements(By.xpath("//*[starts-with(@id,'DIV_')]/td/table/tbody/tr/"));
	System.out.println("Size of cities " +city.size()   +"Name is" +city.get(2));
	String str=null;
	for(WebElement text:city){
		list.add(text.getText());
	}
	return list;
}
*/
	/*Map<Integer, String> list=new HashMap<Integer, String>();
	list=new HashMap<Integer, String>();
	//int key=0;
*/	/*int k=1;
	while(k<=size){
	String value=browser.getTextValue(By.xpath("//*[@id='DIV_"+k+"']/td/table/tbody/tr/td[2]"));
	list.put(new Integer(k),value);
	k++;
	}	
	if(list.containsKey(key)){
		 str = list.get(key);
		}*/
		



	
/*	
 * System.out.println(browser.getTextValue(By.cssSelector("tbody>tr>td[class^='td-Border_custz']")));	
 */



