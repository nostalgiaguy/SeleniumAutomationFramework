package com.erevmax.chainConfiguration.tests;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.erevmax.RTAdmin.pages.ClientChainConfigurationPage;
import com.erevmax.baseTest.TestBase;
import com.erevmax.utils.CustomTestListener;
import com.erevmax.utils.Verify;

@Listeners(CustomTestListener.class)
public class ClientChainConfigurationTest extends TestBase {

	ClientChainConfigurationPage ccc=new ClientChainConfigurationPage();
	String chainCode="AS";
	String chainName="All Suites Hotels";
	String currency="RON";
	String status ="Active";

	@BeforeClass
	public void navigationToClientChain(){
		browser.click(ccc.ConfigMenu);
		browser.elementWait(3000);
		browser.click(ccc.ConfigureChainMenu);	
	}

	@Test
	public void chainCodeAjaxTest(){
		System.out.println("Test 1 started");
		browser.elementWait(3000);
		browser.enterInput(ccc.chainCode,chainCode);
		ccc.chooseAjaxOption(ccc.chainCodeAjaxOption, chainCode);
		browser.elementWait(2000);
		Verify.verifyEquals(browser.getAttributeValue(ccc.chainCode,"value"), chainCode);
		Verify.verifyEquals(browser.getAttributeValue(ccc.chainName,"value"), chainName);
		System.out.println("Verified that ajax list is enabled for Chian Code.");
		System.out.println("Test 1 completed.");
	}

	@Test
	public void chainNameAjaxTest(){
		System.out.println("Test 2 started");
		browser.elementWait(3000);
		browser.enterInput(ccc.chainName,chainName);
		ccc.chooseAjaxOption(ccc.chainNameAjaxOption,chainName);
		browser.elementWait(2000);
		Verify.verifyEquals(browser.getAttributeValue(ccc.chainName,"value"), chainName);
		Verify.verifyEquals(browser.getAttributeValue(ccc.chainCode,"value"), chainCode);
		System.out.println("Verified that ajax list is enabled for Chain Name.");
		System.out.println("Test 2 completed.");
	}

	@Test
	public void resetButtonTest(){
		System.out.println("Test 3 started");
		browser.elementWait(3000);
		browser.enterInput(ccc.chainCode,chainCode);
		ccc.chooseAjaxOption(ccc.chainCodeAjaxOption, chainCode);
		browser.click(ccc.ResetButton);
		Verify.verifyEquals(browser.getAttributeValue(ccc.chainName,"value"),"");
		Verify.verifyEquals(browser.getAttributeValue(ccc.chainCode,"value"),"");
		System.out.println("Verified that Reset Button is enabled for Chain Configuration.");
		System.out.println("Test 3 completed.");
	}

	@Test(dataProvider="newChainNameData")
	public void AddChain_chainNameErrMsgTest(String newChainName, String validation){

		System.out.println("Test 4 Start.");
		String parentWindowHandle=null;
		browser.elementWait(3000);
		browser.click(ccc.Add_Chain);
		browser.elementWait(2000);
		parentWindowHandle=browser.switchToNewlyOpenedWindow();
		browser.enterInput(ccc.newChainName,newChainName);
		browser.click(ccc.newChainSave);
		Verify.verifyEquals(browser.getText(ccc.validationMsg).trim(),validation);
		browser.click(ccc.newAddChainClose);
		browser.switchToOriginalWindow(parentWindowHandle);	
		System.out.println("Test 4 completed.");
	}
	@DataProvider(name="newChainName") 
	public static Object[][] newChainNameData() {
		return new Object[][] {{"A","Length of Chain Name must be between 2 and 30."}
		,{"22","Chain Name may consist of a-z A-Z, 0-9, underscores, spaces and must begin with a letter."}};
	}

	@Test(dataProvider="newChainCodeData")
	public void AddChain_chainCodeErrMsgTest(String newChainCode, String validation){

		System.out.println("Test 5 Start.");
		String parentWindowHandle=null;
		browser.elementWait(3000);
		browser.click(ccc.Add_Chain);
		browser.elementWait(2000);
		parentWindowHandle=browser.switchToNewlyOpenedWindow();
		browser.enterInput(ccc.newChainName,chainName);
		browser.enterInput(ccc.newChainCode,newChainCode);
		browser.click(ccc.newChainSave);
		Verify.verifyEquals(browser.getText(ccc.validationMsg).trim(),validation);
		browser.click(ccc.newAddChainClose);
		browser.switchToOriginalWindow(parentWindowHandle);	
		System.out.println("Test 5 completed.");
	}
	@DataProvider(name="newChainCode") 
	public static Object[][] newChainCodeData() {
		return new Object[][] {{"","Length of Chain Code must be between 1 and 2."}
		,{"222","Length of Chain Code must be between 1 and 2."}};
	}


	@Test
	public void AddChain_currencyErrMsgTest(){

		System.out.println("Test 6 Start.");
		String parentWindowHandle=null;
		browser.elementWait(3000);
		browser.click(ccc.Add_Chain);
		browser.elementWait(2000);
		parentWindowHandle=browser.switchToNewlyOpenedWindow();
		browser.enterInput(ccc.newChainName,chainName);
		browser.enterInput(ccc.newChainCode,chainCode);
		browser.click(ccc.newChainSave);
		Verify.verifyEquals(browser.getText(ccc.validationMsg).trim(),"Please Select Currency");
		browser.click(ccc.newAddChainClose);
		browser.switchToOriginalWindow(parentWindowHandle);	
		System.out.println("Test 6 completed.");
	}


	@Test
	public void AddChain_statusErrMsgTest(){

		System.out.println("Test 7 Start.");
		String parentWindowHandle=null;
		browser.elementWait(3000);
		browser.click(ccc.Add_Chain);
		browser.elementWait(2000);
		parentWindowHandle=browser.switchToNewlyOpenedWindow();
		browser.enterInput(ccc.newChainName,chainName);
		browser.enterInput(ccc.newChainCode,chainCode);
		browser.selectFromDropDownList(ccc.newChainCurrency,"RON");
		browser.click(ccc.newChainSave);
		Verify.verifyEquals(browser.getText(ccc.validationMsg).trim(),"Please Select Status");
		browser.click(ccc.newAddChainClose);
		browser.switchToOriginalWindow(parentWindowHandle);	
		System.out.println("Test 7 completed.");
	}


	@Test
	public void AddChain_resetButtonTest(){

		System.out.println("Test 8 Start.");
		String parentWindowHandle=null;
		browser.elementWait(3000);
		browser.click(ccc.Add_Chain);
		browser.elementWait(2000);
		parentWindowHandle=browser.switchToNewlyOpenedWindow();
		browser.enterInput(ccc.newChainName,chainName);
		browser.enterInput(ccc.newChainCode,chainCode);
		browser.selectFromDropDownList(ccc.newChainCurrency,currency);
		browser.selectFromDropDownList(ccc.newChainStatus,status);
		browser.elementWait(1000);
		browser.click(ccc.newChainReset);
		browser.elementWait(1000);
		Verify.verifyEquals(browser.getAttributeValue(ccc.newChainName, "value").trim(),"");
		Verify.verifyEquals(browser.getAttributeValue(ccc.newChainCode, "value").trim(),"");
		Verify.verifyEquals(browser.getAttributeValue(ccc.newChainCurrency, "value").trim(),"-1");
		Verify.verifyEquals(browser.getAttributeValue(ccc.newChainStatus, "value").trim(),"-1");
		browser.click(ccc.newAddChainClose);
		browser.switchToOriginalWindow(parentWindowHandle);	
		System.out.println("Test 8 completed.");
	}

	@Test
	public void duplicate_chainNameMsgTest(){
		System.out.println("Test 9 Start.");
		String parentWindowHandle=null;
		browser.elementWait(3000);
		browser.click(ccc.Add_Chain);
		browser.elementWait(2000);
		parentWindowHandle=browser.switchToNewlyOpenedWindow();
		browser.enterInput(ccc.newChainName,chainName);
		browser.enterInput(ccc.newChainCode,"81");
		browser.selectFromDropDownList(ccc.newChainCurrency,currency);
		browser.selectFromDropDownList(ccc.newChainStatus,status);
		browser.elementWait(1000);
		browser.click(ccc.newChainSave);
		browser.elementWait(1000);
		Verify.verifyEquals(browser.getText(ccc.validationMsg),"Chain Name already exists.");
		browser.click(ccc.newAddChainClose);
		browser.switchToOriginalWindow(parentWindowHandle);	
		System.out.println("Test 9 completed.");
	}

	@Test
	public void duplicate_chainCodeMsgTest(){
		System.out.println("Test 10 Start.");
		String parentWindowHandle=null;
		browser.elementWait(3000);
		browser.click(ccc.Add_Chain);
		browser.elementWait(2000);
		parentWindowHandle=browser.switchToNewlyOpenedWindow();
		browser.enterInput(ccc.newChainName,"XYZABC");
		browser.enterInput(ccc.newChainCode,chainCode);
		browser.selectFromDropDownList(ccc.newChainCurrency,currency);
		browser.selectFromDropDownList(ccc.newChainStatus,status);
		browser.elementWait(1000);
		browser.click(ccc.newChainSave);
		browser.elementWait(1000);
		Verify.verifyEquals(browser.getText(ccc.validationMsg),"Chain Code already exists.");
		browser.click(ccc.newAddChainClose);
		browser.switchToOriginalWindow(parentWindowHandle);	
		System.out.println("Test 10 completed.");
	}

	@Test
	public void duplicate_chainNameAndCodeMsgTest(){
		System.out.println("Test 11 Start.");
		String parentWindowHandle=null;
		browser.elementWait(3000);
		browser.click(ccc.Add_Chain);
		browser.elementWait(2000);
		parentWindowHandle=browser.switchToNewlyOpenedWindow();
		browser.enterInput(ccc.newChainName,chainName);
		browser.enterInput(ccc.newChainCode,chainCode);
		browser.selectFromDropDownList(ccc.newChainCurrency,currency);
		browser.selectFromDropDownList(ccc.newChainStatus,status);
		browser.elementWait(1000);
		browser.click(ccc.newChainSave);
		browser.elementWait(1000);
		Verify.verifyEquals(browser.getText(ccc.validationMsg),"Chain Name and Code already exists.");
		browser.click(ccc.newAddChainClose);
		browser.switchToOriginalWindow(parentWindowHandle);	
		System.out.println("Test 11 completed.");
	}

	//@Test(dataProvider="addNewChainData")
	public void addNewChainTest(String newChainName, String newChainCode){
		System.out.println("Test 11 Start.");
		String parentWindowHandle=null;
		browser.elementWait(3000);
		browser.click(ccc.Add_Chain);
		browser.elementWait(2000);
		parentWindowHandle=browser.switchToNewlyOpenedWindow();
		browser.enterInput(ccc.newChainName,newChainName);
		browser.enterInput(ccc.newChainCode,newChainCode);
		browser.selectFromDropDownList(ccc.newChainCurrency,currency);
		browser.selectFromDropDownList(ccc.newChainStatus,status);
		browser.elementWait(1000);
		browser.click(ccc.newChainSave);
		browser.elementWait(3000);
		browser.switchToOriginalWindow(parentWindowHandle);	
		Verify.verifyEquals(browser.getAttributeValue(ccc.chainCode,"value"), newChainCode);
		Verify.verifyEquals(browser.getAttributeValue(ccc.chainName,"value"),newChainName);
		browser.click(ccc.continueButton);
		browser.elementWait(3000);
		Verify.verifyEquals(browser.getText(ccc.panel_title),newChainName+" | "+newChainCode);
		browser.click(ccc.ConfigureChainMenu);
		System.out.println("Test 11 completed.");
	}
	@DataProvider(name="addNewChain") 
	public static Object[][] addNewChainData() {
		return new Object[][] {{"BCD","83"}};
	}

}
