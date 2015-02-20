package com.erevmax.RTAdmin.pages;

import static com.erevmax.baseTest.TestBase.browser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;

import com.erevmax.mysample.Sample.WebPage;

public class AssignmentPage extends WebPage {


	public By othersMenu;
	public By assignmentsView;
	public By assignedTo;
	public By selectAssign;
	public By searchButton;
	public By startDate;
	public By assignmentDate;
	public By endDate;
	public By tableElement;
	public By reset;

	public By days;
	public By results;
	public By option;
	public By firstRowSerialNo;
	public By next;
	public By first;

	public By ckBx;
	public By save;
	public By message;
	public By page_x_of_x;
	public By allCkBx;
	private int serialNo;

	public AssignmentPage(){

		this.othersMenu=By.id("ot");
		this.assignmentsView= By.id("ot7");
		this.assignedTo=By.id("assignTo_search");
		this.selectAssign=By.id("selectAssignTo_search");
		this.option=By.tagName("option");
		this.searchButton=By.linkText("Search");
		this.startDate=By.id("startDate");
		this.endDate=By.id("endDate");
		this.assignmentDate=By.id("assignmentTitle");
		this.reset=By.linkText("Reset");
		this.tableElement=By.xpath("//*[@id='assignment-container']/table/tbody/tr");
		this.results=By.id("results");
		this.ckBx=By.name("savecheckbox");
		this.save=By.linkText("Save");
		this.allCkBx=By.name("saveallcheckbox" );
		this.message=By.id("message");
		this.next=By.linkText("Next");
		this.page_x_of_x=By.className("simple-pagination-page-x-of-x");
	}

	/*Method for editing and saving any assigned note detail
	 * using serial number,serial number start from zero
	 */
	public String editNoteField(String data,By lctr,int checkBoxNum){

		String previousData=browser.getAttributeValue(By.id("selectednote_"+checkBoxNum),"value");
		System.out.println("Previous Note Field:"+previousData);		
		browser.enterInput(By.id("selectednote_"+checkBoxNum), data);
		browser.click(lctr);
		browser.elementWait(3000);
		String actual_update=browser.getText(message);
		String changeDataField=browser.getText(By.id("note2_"+checkBoxNum));
		System.out.println("Modified Note Field:"+changeDataField);
		return actual_update;
	}

	public boolean setNullAssignField(String name){
		String msg=browser.getText(message);
		if(msg.equalsIgnoreCase("No records found.")){
			System.out.println("No Record Is Present.");
			return false;
		}
		serialNo=0;
		int num=browser.getListSize(tableElement);
		List<Integer> list = new ArrayList<Integer>();
		int k=1;
		while(serialNo<num){
			String value=browser.getAttributeValue(By.id("assignTo_"+serialNo),"value");
			if(value.isEmpty()){
				list.add(serialNo);
			}
			serialNo=serialNo+1;
			if(k%50==0){	
				browser.click(By.linkText("Next"));
				browser.elementWait(3000);
			}
			k=k+1;
		}
		if(list.size()==0){
			System.out.println("No Null Assigned Field is Present");
			return false;
		}
		//System.out.println(list);
		else{
		int pageCount=1+((list.get(0)+1)/50);
		browser.click(By.linkText(""+(pageCount)+""));
		browser.elementWait(3000);
		int i=pageCount+1;
		for(Integer serialNo:list){
			if(serialNo>(50*i)){
				browser.click(By.linkText("Next"));
				browser.elementWait(5000);
				i=i+1;
			}
			browser.autoSelect(By.id("assignTo_"+serialNo), By.id("selectAssignTo_"+serialNo), name);
		} 
		browser.click(save);
		browser.elementWait(3000);
		return true;
		}
	}

	public void resetTop3AssignToField(){
		System.out.println("here");
		String msg=browser.getText(message);
		if(msg.equalsIgnoreCase("No records found.")){
			System.out.println("No Record Is Present.");
			return;
		}
		int rowCount=browser.getListSize(tableElement);	
		int serialNo=0;
		int page=0;
		int page_count=(rowCount/50)+1;
		while(page<page_count){
			int count=0;
			for(count=0;count<3;count++){
				serialNo=serialNo+count;
				String name=browser.findWebElement(By.id("assignTo_"+serialNo)).getAttribute("value");
				if(name!=null || name!=""){
					browser.findWebElement(By.id("assignTo_"+serialNo)).clear();
				}
				serialNo=page*50;
			}
			page=page+1;
			serialNo=page*50;
			if(page==page_count){
				break;
			}
			browser.click(next);
		}
		browser.elementWait(3000);	
		browser.click(save);
		browser.elementWait(4000);
	}

	public void resetTop3AndAssignNameToField(String name){
		String msg=browser.getText(message);
		if(msg.equalsIgnoreCase("No records found.")){
			System.out.println("No Record Is Present.");
			return;
		}
		int rowCount=browser.getListSize(tableElement);	
		int serialNo=0;
		int page=0;
		int page_count=(rowCount/50)+1;
		while(page<page_count){
			int count=0;
			for(count=0;count<3;count++){
				serialNo=serialNo+count;
				browser.autoSelect(By.id("assignTo_"+serialNo),By.id("selectAssignTo_"+serialNo), name);
				serialNo=page*50;
			}
			page=page+1;
			serialNo=page*50;
			if(page==page_count){
				break;
			}
			browser.click(next);
		}
		browser.elementWait(3000);	
		browser.click(save);
		browser.elementWait(4000);
	}

	public boolean sortingOrderOfList(String name){
		List<String> actual_list=new ArrayList<String>();
		List<String> expected_list=new ArrayList<String>();
		actual_list=browser.getDropDownList(assignedTo, option,name);
		expected_list.addAll(actual_list);
		System.out.println(actual_list);
		System.out.println(expected_list);
		Collections.sort(expected_list);
		System.out.println(expected_list);
		boolean flag=false;
		for(int count=0;count<actual_list.size();count++){
			if(actual_list.get(count).equals(expected_list.get(count))){
				flag=true;
			}
			else{
				flag=false;
				break;
			}
		}
		return flag;
	}

	public boolean duplicateElementInList(String name){
		List<String> actual_list=new ArrayList<String>();
		actual_list=browser.getDropDownList(assignedTo,option,name);
		Set<String> inputSet = new HashSet<String>(actual_list);
		//System.out.println(actual_list.size());
		//System.out.println(inputSet.size());
		if(inputSet.size()< actual_list.size()){
			return true;
		}
		return false;
	}

	public int getPageNum(int rowNum){
		int rowCount=browser.getListSize(tableElement);
		int page;
		if(rowNum<=rowCount){
			int div=rowNum/50;
			int rem=rowNum%50;
			if(rem>0){
				page=div+1;
			}
			else{
				page=div;
			}
			for(int i=1;i<page;i++){
				browser.click(next);
				browser.elementWait(3000);
			}	
			System.out.println( "record "+rowNum+" is found at page no.="+page);
			return page;
		}
		else{	
			return 0;
		}	
	}

	public void editTop3NoteField(String data){
		String msg=browser.getText(message);
		if(msg.equalsIgnoreCase("No records found.")){
			System.out.println("No Record Is Present.");
			return;
		}
		int rowCount=browser.getListSize(tableElement);	
		int serialNo=0;
		int page=0;
		int page_count=(rowCount/50)+1;
		while(page<page_count){
			int count=0;
			for(count=0;count<3;count++){
				serialNo=serialNo+count;
				browser.checkCheckBoxField(ckBx, serialNo);
				browser.enterInput(By.id("selectednote_"+serialNo), data);
				browser.elementWait(1000);
				serialNo=page*50;
			}
			page=page+1;
			serialNo=page*50;
			if(page==page_count){
				break;
			}
			browser.click(next);
		}
		browser.elementWait(3000);
		browser.click(save);
		browser.elementWait(4000);
	}

}

/*   	browser.enterInput(By.id("selectednote_"+serialNo), data);
 * ***************************************************************************************************************
 */
/*
 * 
 * 
// method for editing all note field
public void editAllNoteField(String data,int chekBoxNum){	
	this.enterInput(By.id("selectednote_"+chekBoxNum+""), data);

	try {
		Thread.elementWait(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

}

/*Method for printing and returning any note field
 * using serial number,serial number start from zero

public String readNoteField(By currentData,int num){
	int chekBoxNum=num;
	String fieldData=driver.findElement(By.id("note2_"+chekBoxNum+"")).getText();//getText(currentData);
	//System.out.println("Note Field Data:"+fieldData);
	return fieldData;
}

/*Method for printing and returning any assigned person name 
 * using serial number,serial number start from zero

public String readAssignToField(By currentData,int num){
	int serialNo=num;
	String Assigned_Person_Name=driver.findElement(By.id("assignTo_"+serialNo+"")).getAttribute("value");//getText(currentData);
	//System.out.println("Assigned Person Name:"+Assigned_Person_Name);
	return Assigned_Person_Name;
}

public void resetAllAssignToField(){
		int rowCount=browser.getListSize(tableElement);
		int count=0;
		int page=1;
		while(count<rowCount){
			serialNo=count;
			if(count/50==page && count!=0){
				browser.click(next);
				page++;
			}
			String name=browser.findWebElement(By.id("assignTo_"+serialNo)).getAttribute("value");
			if(name!=null || name!=""){
				browser.findWebElement(By.id("assignTo_"+serialNo)).clear();
			}
			count=count+1;
		}
		browser.elementWait(3000);	
		browser.click(save);
		browser.elementWait(3000);
	}
	
	public void resetAndAssignNameToField(List<String> name){
		int rowCount=browser.getListSize(tableElement);
		int count=0;
		int page=1;
		while(count<rowCount){
			serialNo=count;
			if(count/50==page && count!=0){
				browser.click(next);
				page++;
			}
			browser.autoSelect(By.id("assignTo_"+serialNo),By.id("selectAssignTo_"+serialNo), name.get(page-1));
			count=count+1;
			browser.elementWait(1000);	
	
		}
		browser.elementWait(3000);	
		browser.click(save);
		browser.elementWait(3000);
	}
	
	public void editAllNoteField(String data){
		int rowCount=browser.getListSize(tableElement);
		int count=0;
		int page=1;
		while(count<rowCount){
			serialNo=count;
			if(count/50==page && count!=0){
				browser.click(next);
				page++;
			}		
			browser.enterInput(By.id("selectednote_"+serialNo), data);
			count=count+1;
			browser.elementWait(1000);
		}
		browser.elementWait(3000);
		browser.click(save);
		browser.elementWait(3000);
	}


 **********************************************************************************************************
 */