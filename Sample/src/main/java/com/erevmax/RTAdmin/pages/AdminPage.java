package com.erevmax.RTAdmin.pages;

import org.openqa.selenium.By;

import com.erevmax.mysample.Sample.WebPage;

public class AdminPage extends WebPage{
	
	public By adminMenu;
	public By addNewUser;
	public By role;
	
	public AdminPage(){
		this.adminMenu=By.id("ad");
		this.addNewUser=By.xpath("//*[@id='DIV_357286']/td/table/tbody/tr/td[3]/a/span");
		this.role=By.id("userRoleSelected");
		//userRoleSelected
	}

}
