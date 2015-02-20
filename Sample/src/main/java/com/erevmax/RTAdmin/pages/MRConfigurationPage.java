package com.erevmax.RTAdmin.pages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.erevmax.mysample.Sample.WebPage;

import static com.erevmax.baseTest.TestBase.browser;
import static com.erevmax.utils.DataBase.closeConnection;
import static com.erevmax.utils.DataBase.executeQuery;
import static com.erevmax.utils.DataBase.getRateTigerDataBaseConnection;

public class MRConfigurationPage extends WebPage{
	
	public String hotel_Code;
	
	public By config;
	public By hotelCode;
	public By selectHotelCode;
	public By proceed;
	public By module_ManageReport;
	public By MRSettingEditButton;
	public By dailyReportSubscribed;
	public By activateDaily;
	public By activateWeekly;
	public By activateMonthly;
	public By save;
	public By maxDailyReport;
	
	public By noOfDailyReport;
	public By weeklyReportSubscribed;
	public By monthlyReportSubscribed;
	public By manageReportStatus;
	public By hotelCodeAlert;
	public By expandWeekly;
	public By expandMonthly;
	public By noOfWeeklyReport;
	public By noOfMonthlyReport;
	public By weekDayReport;
	public By selectWeekDayWeekly;
	public By selectWeekDayMonthly;
	public By selectMonthDayOption;
	public By enterMonthDay;
	public By posOfWeekMonthly;
	public By hotelCkBx;
	public By channelCkBx;
	public By expandChannelSelection;
	public By competitorHotelsList;
	public By channelsList;
	public By dailyReportDays;
	
	//public String query="select * from rt_mr_settings where hotel_code='00343'";
	

	
	public MRConfigurationPage(String hotel_Code){
		
		
		this.hotel_Code=hotel_Code;
		this.dailyReportDays=By.xpath("//input[starts-with(@id, 'reportDays_')]");
		this.competitorHotelsList=By.xpath("//input[starts-with(@id, 'compstatusMap_')]");
		this.channelsList=By.xpath("//input[starts-with(@id, 'channelstatusMap_')]");
		this.hotelCkBx=By.name("hotel_selection");
		this.channelCkBx=By.name("channel_selection");
		this.posOfWeekMonthly=By.name("posOfweekMonthly");
		this.selectMonthDayOption=By.name("rate");
		this.enterMonthDay=By.name("dayOfMonthMonthly");
		this.selectWeekDayWeekly=By.id("weekDayWeekly");
		this.selectWeekDayMonthly=By.id("weekDayMonthly");
		this.weekDayReport=By.name("weekDayWeekly");
		this.expandWeekly=By.xpath("//*[@id='EC_357279']");
		this.expandMonthly=By.xpath("//*[@id='EC_357280']");
		this.expandChannelSelection=By.xpath("//*[@id='EC_357287']");	
		this.hotelCodeAlert=By.xpath("//*[@id='hotelSearchForm']/table/tbody/tr[8]/td/table/tbody/tr[2]");
		this.config=By.id("cg");
		this.hotelCode=By.id("hotelCode");
		this.selectHotelCode=By.id("selectHotelCode");
		this.proceed=By.linkText("Continue");
		//this.title=By.className("title-header");
		this.module_ManageReport= By.xpath("//*[@id='browser']/li[1]/ul/li[7]/ul/li[2]/span");
		this.MRSettingEditButton=By.xpath("//*[@id='client-preference']/table//td[contains(text(),'Managed Report Settings:')]/following-sibling::td/a[span]");
	    this.dailyReportSubscribed=By.name("isDailySubscribed");
	    this.weeklyReportSubscribed=By.name("isWeeklySubscribed");
	    this.monthlyReportSubscribed=By.name("isMonthlySubscribed");
	    this.activateDaily=By.id("isDailyActivated");
	    this.activateWeekly=By.id("isWeeklyActivated");
	    this.activateMonthly=By.id("isMonthlyActivated");
	    this.save=By.linkText("Save");
	    this.maxDailyReport=By.id("maxNoDailyReport");
	    this.noOfDailyReport=By.id("noOfDailyReportDays");
	    this.noOfWeeklyReport=By.name("noOfDaysWeekly");
	    this.noOfMonthlyReport=By.name("noOfDaysMonthly");
	   
	    this.manageReportStatus=By.xpath("//*[@id='client-preference']/table//td[contains(text(),'Managed Rate Shopper:')]/following-sibling::td[4]");
	}
	
	public String getQuery(){
		 String query="select * from rt_mr_settings where hotel_code='"+hotel_Code+"'";
		 return query;
	}

	public void manageReportSubscriptionSetting(By lctr, int i){
		List<WebElement> element=browser.findWebElements(lctr);
		if(i==0 || i==1){
			element.get(i).click();
		}
	}

	public void manageReportPreferenceSetting(By lctr,String flag){
		if(flag.equals("yes")){
			browser.checkCheckBoxField(lctr, 0);
		}
		else{
			browser.deSelectCheckbox(lctr, 0);
		}
	}

	public void channelSelectionSetting(By lctr,String flag){
		if(flag.equals("yes")){
			browser.checkCheckBoxField(lctr, 0);
		}
		else{
			browser.deSelectCheckbox(lctr, 0);
		}
	}
	
	public void selectNumberOfHotels(int num){
		if(num<=0){
			System.out.println("select at least one hotel");
		}
		else{
		System.out.println("we have to select "+num+" of hotels.");
		browser.selectMultipleCheckBoxes(competitorHotelsList, num);
		}
	}
	
	public void selectNumberOfChannels(int num){
		boolean flag=false;
		if(num<=0){
			System.out.println("select at least one channel");
		}
		else{
		int i=browser.getListSize(channelsList); 
		 flag=browser.findWebElement(By.id("allHotelSiteSelection")).isSelected();
		System.out.println("we have to select "+num+" of channels.");
		if(flag){
		browser.selectMultipleCheckBoxes(channelsList, num-1);
		}
		if(!flag && (i>=num)){
			browser.findWebElement(By.id("allHotelSiteSelection")).click();
			browser.selectMultipleCheckBoxes(channelsList, num-1);
		}
		if(!flag && (num>i)){
			browser.findWebElement(By.id("allHotelSiteSelection")).click();
			browser.selectMultipleCheckBoxes(channelsList, num);
		}
		}
	}
	
	public void selectMonthlySubscription(String flag){
		List<WebElement> element= browser.findWebElements(selectMonthDayOption);
		if(flag=="first"){
			element.get(0).click();
		}
		if(flag=="second"){
			element.get(1).click();
		}
	}
	
	public int checkDaysSelected(int days){
		browser.deSelectAllMultipleCheckBoxes(dailyReportDays);
		browser.selectMultipleCheckBoxes(dailyReportDays, days);
		/*int count=0;
		int z=6;
		for(int i=1;i<=days;i++){
			count=(int) (count+Math.pow(2, z));
			z--;
		}
		
		return count;*/
	
		 switch (days) {
         case 1:  //monday=2^6=64
         return 64;
         case 2:  //tuesday=64+2^5=96
         return 96;
         case 3: // wednesday=96+2^4=112
         return 112;
         case 4:  //thursday=112+2^3=120
         return 120;
         case 5: //friday=120+2^2=124
         return 124;
         case 6: //saturday=124+2^1=126
         return 126;
         case 7:  //sunday=126+2^0=127
         return 127;
         default: System.out.println("Invalid Days.");
         return 0;
		 }
		
	}
	
	public void selectWeekDayWeeklyName(String day){
		browser.selectFromDropDownList(selectWeekDayWeekly,day);
	}
	
	public void selectWeekDayMonthlyName(String day){
		browser.selectFromDropDownList(selectWeekDayMonthly,day);
	}
	
	public void selectPosOfWeekMonthly(String pos){
		browser.selectFromDropDownList(posOfWeekMonthly,pos);
	}
	
	
	public int checkDataBaseForInteger(String query,String field){
		Connection con= getRateTigerDataBaseConnection();
		ResultSet rs=executeQuery(query,con);
		int actual=0;
		try {
			while(rs.next()){
				 actual=rs.getInt(field);				
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("sql exception occured.");
		}			
		closeConnection(con,null,rs,null);	
		return actual;
}
	public  String checkDataBaseForString(String query,String field){
		Connection con= getRateTigerDataBaseConnection();
		
		ResultSet rs=executeQuery(query,con);
		String actual=null;	
		
		try {
			while(rs.next()){
				 actual=rs.getString(field);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception occured.");
		}
		closeConnection(con,null,rs,null);
		System.out.println(actual);
		return actual;
	}

}

/* 
public void checkDailyRadioButton(String flag){
	List<WebElement> element= browser.selectRadioButton(dailyReport);
	if(flag=="yes"){
		element.get(0).click();
	}
	else{
		element.get(1).click();
	}
}

public void checkWeeklyRadioButton(String flag){
	List<WebElement> element= browser.selectRadioButton(weeklyReport);
	if(flag=="yes"){
		element.get(0).click();
	}
	else{
		element.get(1).click();
	}
}

public void checkMonthlyRadioButton(String flag){
	List<WebElement> element= browser.selectRadioButton(monthlyReport);
	if(flag=="yes"){
		element.get(0).click();
	}
	else{
		element.get(1).click();
	}
}
*/
/*
public void checkDailyActivatedCkBx(String flag){
	if(flag=="yes"){
	browser.checkBoxField(activateDaily, 0);
	}
	else{
		browser.checkBoxField(activateDaily, 1);
	}
}
public void checkWeeklyActivatedCkBx(String flag){
	if(flag=="yes"){
	browser.checkBoxField(activateWeekly, 0);
	}
	else{
		browser.checkBoxField(activateWeekly, 1);
	}
}
public void checkMonthlyActivatedCkBx(String flag){
	if(flag=="yes"){
	browser.checkBoxField(activateMonthly, 0);
	}
	else{
		browser.checkBoxField(activateMonthly, 1);
	}
}
public void checkHotelCkBx(String flag){
	if(flag=="yes"){
	browser.checkBoxField(hotelCkBx, 0);
	}
	else{
		browser.checkBoxField(hotelCkBx, 1);
	}
}
public void checkChannelCkBx(String flag){
	if(flag=="yes"){
	browser.checkBoxField(channelCkBx, 0);
	}
	else{
		browser.checkBoxField(channelCkBx, 1);
	}
}
*/
/*int check=0;
int uncheck=0;
List<WebElement> ckBx1 = browser.findListOfWebElements(competitorHotelsList);
List<WebElement> checkedList=new ArrayList<WebElement>();
List<WebElement> uncheckedList=new ArrayList<WebElement>();
System.out.println(ckBx1.size());
int w=0;
while(w<ckBx1.size()){
	String a=ckBx1.get(w).getAttribute("value");
	if(a.equalsIgnoreCase("true")){
		check++;
		checkedList.add(ckBx1.get(w));
	}
	else{
		uncheck++;
		uncheckedList.add(ckBx1.get(w));
	}
	w++;
}
int c1=checkedList.size();
int c2=uncheckedList.size();
int input=num;
System.out.println("check="+check+"   uncheck="+uncheck);
System.out.println(c1+"  "+c2);
if(c1>input){
	int k=c1-input;
	System.out.println(k);
	for(int z=0;z<k;z++){

		((WebElement) checkedList.get(z)).click();
	}
}
if(c1<input){

	for(int z=0;z<input-c1;z++){

		((WebElement) uncheckedList.get(z)).click();
	}
}*/

/*int i =2;
while(i<ckBx.size()){
	//List<String> name=new ArrayList<String>();
	String hotelNmae=browser.getTextValue(By.xpath("//*[@id='DIV_357287_1']/td/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr["+i+"]")).trim();
      //name1.add(hotelNmae);
	System.out.println(i+" "+browser.getTextValue(By.xpath("//*[@id='DIV_357287_1']/td/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr["+i+"]")));
	String z11 = browser.findWebElement(By.name("My Hotel Paris")).getAttribute("value");
	System.out.println(z11);
	String z1 = browser.findWebElement(By.name(hotelNmae)).getAttribute("value");
	if(z1=="true"){
		check++;
	}
	else{
		uncheck++;
	}
	i++;
	System.out.println(check+" "+uncheck);
	
}
*/

/*
public int checkDaysSelected(Set<String> days){
	int count=0;
	if(days.contains("mon")){
		browser.checkCheckBoxField(monday, 0);
		count=count+64;
	}
	if(days.contains("tue")){
		browser.checkCheckBoxField(tuesday, 0);
		count=count+32;
	}
	if(days.contains("thu")){
		browser.checkCheckBoxField(thursday, 0);
		count=count+8;
	}
	if(days.contains("wed")){
		browser.checkCheckBoxField(wednesday, 0);
		count=count+16;
	}
	if(days.contains("fri")){
		browser.checkCheckBoxField(friday, 0);
		count=count+4;
	}
	if(days.contains("sat")){
		browser.checkCheckBoxField(saturday, 0);
		count=count+2;
	}
	if(days.contains("sun")){
		browser.checkCheckBoxField(sunday, 0);
		count=count+1;
	}
	return count;
}

public By sunday;
	public By monday;
	public By tuesday;
	public By thursday;
	public By wednesday;
	public By friday;
	public By saturday;
	
	 this.sunday=By.name("Sun");
	    this.monday=By.name("Mon");
	    this.tuesday=By.name("Tue");
	    this.wednesday=By.name("Wed");
	    this.thursday=By.name("Thu");
	    this.friday=By.name("Fri");
	    this.saturday=By.name("Sat");
*/