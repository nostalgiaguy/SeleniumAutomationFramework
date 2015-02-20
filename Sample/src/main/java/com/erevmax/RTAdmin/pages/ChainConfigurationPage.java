package com.erevmax.RTAdmin.pages;

import java.util.List;

import static com.erevmax.baseTest.TestBase.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.erevmax.mysample.Sample.WebPage;

public class ChainConfigurationPage extends WebPage{
	
	public By ConfigMenu;
	public By ConfigureChainMenu;
	public By chainCode;
	public By chainCodeAjaxOption;
	public By chainName;
	public By continueButn;
	public By ExpandButn;
	public By addRegionButn;
	public By editRegionButn;
	public By addRegionResetButn;
	public By addRegionName;
	public By addRegionType;
	public By addRegionStatus;
	public By editRegionSaveButn;
	public By addRegionSaveButn;
	public By errorMsg; 
	public By Regions;
	
	public ChainConfigurationPage(){
		this.ConfigMenu=By.id("cg");
		this.ConfigureChainMenu=By.id("cg4");
		this.chainCode=By.id("chainCode");
		this.chainCodeAjaxOption=By.xpath("//*[starts-with(@id,'ui-id-')]/a");
		this.chainName=By.id("chainName");
		this.continueButn=By.linkText("Continue");
		this.ExpandButn=By.xpath("//img[starts-with(@id,'EC_')]"); 
		this.addRegionButn=By.xpath("//*[@id='xsnazzy']/div/table[4]/tbody/tr/th[3]/a");
		this.editRegionButn=By.xpath("//*[@id='xsnazzy']/div/table[4]/tbody/tr/th[4]/a");
		this.addRegionName=By.id("regionName");
		this.addRegionType=By.id("regionType");
		this.addRegionStatus=By.id("status");
		this.addRegionResetButn=By.linkText("Reset");
		this.editRegionSaveButn=By.linkText("Save");
		this.addRegionSaveButn=By.linkText("Save");
		this.errorMsg=By.id("errorMsg");
		this.Regions=By.xpath("//*[@id='DIV_357289_0']//tbody/tr");
	}

	
	public void expand(){
		List<WebElement> list = browser.findWebElements(ExpandButn);
		int j=0;
		while(j<list.size()){
			if(list.get(j).getAttribute("src").equals(browser.kolkata_URL+"images/Icon-Minus.png")){
			}else {
				((WebElement) list.get(j)).click();
			}
		j++;	
		}
	
	}
	
	public By getRegionNameOfIndex(int index){
		return By.xpath("//*[@id='DIV_357289_0']/div[2]/table/tbody/tr["+index+"]/td[2]");
	}
	public By getRegionStatusOfIndex(int index){
		return By.xpath("//*[@id='DIV_357289_0']/div[2]/table/tbody/tr["+index+"]/td[4]");
	}
	public By getRegionRadioButtonOfIndex(int index){
		return By.xpath("//*[@id='DIV_357289_0']/div[2]/table/tbody/tr["+index+"]/td[1]/span/input");
	}
	public By getRegionDefinitionOfIndex(int index){
		return By.xpath("//*[@id='DIV_357289_0']/div[2]/table/tbody/tr["+index+"]/td[3]/textarea");
	}
	
	public void chooseChainCodeAjaxOption(String chainCode){
		List<WebElement> elements = browser.findWebElements(chainCodeAjaxOption);
		for (WebElement val : elements) {
			String value = val.getText();
			if (value.equals(chainCode)) {
				val.click();
				break;		
			}
		}
	}
}
