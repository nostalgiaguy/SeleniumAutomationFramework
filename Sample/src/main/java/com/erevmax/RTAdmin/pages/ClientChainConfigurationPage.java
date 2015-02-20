package com.erevmax.RTAdmin.pages;

import static com.erevmax.baseTest.TestBase.browser;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.erevmax.mysample.Sample.WebPage;

public class ClientChainConfigurationPage extends WebPage{
	
	public By ConfigMenu;
	public By ConfigureChainMenu;
	public By chainCode;
	public By chainCodeAjaxOption;
	public By chainName;
	public By chainNameAjaxOption;
	public By ResetButton;
	public By Add_Chain;
	public By newChainName;
	public By newChainCode;
	public By newChainCurrency;
	public By newChainStatus;
	public By newChainSave;
	public By newChainReset;
	public By validationMsg;
	public By newAddChainClose;
	public By continueButton;
	public By panel_title;
	
	public ClientChainConfigurationPage(){
		
		this.ConfigMenu=By.id("cg");
		this.ConfigureChainMenu=By.id("cg4");
		this.chainCode=By.id("chainCode");
		this.chainCodeAjaxOption=By.xpath("//*[starts-with(@id,'ui-id-')]/a");
		this.chainName=By.id("chainName");
		this.chainNameAjaxOption=By.xpath("//*[starts-with(@id,'ui-id-')]/a");
		this.ResetButton=By.linkText("Reset");
		this.Add_Chain=By.id("add-chain");
		this.newChainName=By.id("newChainName");
		this.newChainCode=By.id("newChainCode");
		this.newChainCurrency=By.id("currency");
		this.newChainStatus=By.id("status");
		this.validationMsg=By.className("validateTips");
		this.newChainSave=By.id("chainSave");
		this.newChainReset=By.id("chainReset");
		this.newAddChainClose=By.className("ui-button-text");
		this.continueButton=By.linkText("Continue");
		this.panel_title=By.className("panel-title");

	}

	public void chooseAjaxOption(By lctr,String data){
		List<WebElement> elements = browser.findWebElements(lctr);
		for (WebElement val : elements) {
			String value = val.getText();
			if (value.equals(data)) {
				val.click();
				break;		
			}
		}
	}
}
