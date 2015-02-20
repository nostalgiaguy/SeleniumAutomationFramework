package com.erevmax.chainConfiguration.tests;

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.erevmax.RTAdmin.pages.ChainConfigurationPage;
import com.erevmax.baseTest.TestBase;
import com.erevmax.utils.Verify;

public class ChainConfigurationTest extends TestBase {

	ChainConfigurationPage ccp=new ChainConfigurationPage();
	
	String chainCode="Y2";
	String chainName="TEST2";

	@BeforeClass
	public void navigationToClientChain(){
		browser.click(ccp.ConfigMenu);
		browser.elementWait(3000);
		browser.click(ccp.ConfigureChainMenu);
		browser.elementWait(4000);
		browser.enterInput(ccp.chainCode, chainCode);
		ccp.chooseChainCodeAjaxOption(chainCode);
		browser.elementWait(2000);
		browser.click(ccp.continueButn);
		browser.elementWait(3000);
	}

	@Test(dataProvider="regionData",priority=10)
	public void regionNameLengthTest(String regionName, String regionType, String statusType){
		String parentWindowHandle=null;
		try{
			System.out.println("Test 1 Start.");

			browser.click(ccp.addRegionButn);
			browser.elementWait(3000);
			parentWindowHandle=browser.switchToNewlyOpenedWindow();
			browser.enterInput(ccp.addRegionName,regionName.substring(0,1));
			browser.selectFromDropDownList(ccp.addRegionType,regionType);
			browser.selectFromDropDownList(ccp.addRegionStatus,statusType);
			browser.elementWait(4000);
			browser.click(ccp.addRegionSaveButn);
			browser.elementWait(2000);
			String text=browser.checkTextAlertMessage().trim();
			Verify.assertEquals(text, "Length of Region Name must be between 2 and 20.");
			System.out.println("Test 1 completed.");
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentWindowHandle);	
			}
		}
	}
	@Test(dataProvider="regionData",priority=20)
	public void addRegion_regionTypeErrorMsgTest(String regionName, String regionType, String statusType){
		String parentWindowHandle=null;
		try{
			System.out.println("Test 2 Start.");
			browser.click(ccp.addRegionButn);
			browser.elementWait(3000);
			parentWindowHandle=browser.switchToNewlyOpenedWindow();
			browser.enterInput(ccp.addRegionName,regionName);
			browser.elementWait(4000);
			browser.click(ccp.addRegionSaveButn);
			String text=browser.checkTextAlertMessage().trim();
			Verify.assertEquals(text, "Please Select Region Type");
			System.out.println("Test 2 completed.");
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentWindowHandle);	
			}
		}
	}
	@Test(dataProvider="regionData",priority=30)
	public void addRegion_statusErrorMsgTest(String regionName, String regionType, String statusType){
		String parentWindowHandle=null;
		try{
			System.out.println("Test 3 Start.");

			browser.click(ccp.addRegionButn);
			browser.elementWait(3000);
			parentWindowHandle=browser.switchToNewlyOpenedWindow();
			browser.enterInput(ccp.addRegionName,regionName);
			browser.selectFromDropDownList(ccp.addRegionType,regionType);
			browser.elementWait(2000);
			browser.click(ccp.addRegionSaveButn);
			String text=browser.checkTextAlertMessage().trim();
			Verify.assertEquals(text, "Please Select Status");
			System.out.println("Test 3 completed.");
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentWindowHandle);	
			}
		}
	}
	@Test(dataProvider="regionData",priority=40)
	public void addRegionResetButnTest(String regionName, String regionType, String statusType){
		String parentWindowHandle=null;
		try{
			System.out.println("Test 4 Start.");
			browser.click(ccp.addRegionButn);
			browser.elementWait(3000);
			parentWindowHandle=browser.switchToNewlyOpenedWindow();
			browser.elementWait(4000);
			browser.enterInput(ccp.addRegionName,regionName);
			browser.selectFromDropDownList(ccp.addRegionType, regionType);
			browser.selectFromDropDownList(ccp.addRegionStatus, statusType);
			browser.elementWait(4000);
			browser.click(ccp.addRegionResetButn);
			browser.elementWait(2000);
			Verify.assertEquals(browser.getText(ccp.addRegionName).trim(), "");
			Verify.assertEquals(browser.getAttributeValue(ccp.addRegionType,"value"),"-1");
			Verify.assertEquals(browser.getAttributeValue(ccp.addRegionStatus,"value"),"-1");
			System.out.println("Test 4 completed.");
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentWindowHandle);	
			}	
		}
	}
	
	@Test(dataProvider="regionData",priority=50)
	public void addRegionTest(String regionName, String regionType, String statusType){
		String parentWindowHandle=null;
		List<String> RegionName_list=new ArrayList<String>();
		List<String> RegionStatus_list=new ArrayList<String>();
		try{
			System.out.println("Test 5 Start.");
			browser.click(ccp.addRegionButn);
			browser.elementWait(3000);
			parentWindowHandle=browser.switchToNewlyOpenedWindow();
			browser.enterInput(ccp.addRegionName,regionName);
			browser.selectFromDropDownList(ccp.addRegionType, regionType);
			browser.selectFromDropDownList(ccp.addRegionStatus, statusType);
			browser.elementWait(4000);
			browser.click(ccp.addRegionSaveButn);
			browser.elementWait(2000);
			browser.switchToOriginalWindow(parentWindowHandle);	
			browser.elementWait(4000);
			ccp.expand();
			int size=browser.getListSize(ccp.Regions);
			for(int i=1;i<=size;i++){
				RegionName_list.add(browser.getText(ccp.getRegionNameOfIndex(i)).trim());
				RegionStatus_list.add(browser.getText(ccp.getRegionStatusOfIndex(i)).trim());
			}
			Verify.assertEquals(RegionName_list.contains(regionName),true);
			Verify.assertEquals(RegionStatus_list.contains(statusType),true);
			System.out.println("Test 5 completed.");
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentWindowHandle);	
			}	
		}
	}
	@Test(dataProvider="regionData",priority=60)
	public void duplicateRegionNameTest(String regionName, String regionType, String statusType){
		String parentWindowHandle=null;
		try{
			System.out.println("Test 6 Start.");
			browser.click(ccp.addRegionButn);
			browser.elementWait(3000);
			parentWindowHandle=browser.switchToNewlyOpenedWindow();
			browser.enterInput(ccp.addRegionName,regionName);
			browser.selectFromDropDownList(ccp.addRegionType,regionType);
			browser.selectFromDropDownList(ccp.addRegionStatus,statusType);
			browser.elementWait(4000);
			browser.click(ccp.addRegionSaveButn);
			browser.elementWait(2000);
			Verify.assertEquals(browser.getText(ccp.errorMsg).trim(), "Region Name already exists.");
			System.out.println("Test 6 completed.");
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentWindowHandle);	
			}	
		}
	}
	@Test(priority=70)
	public void editRegionErrorMsgTest(){
		String parentWindowHandle=null;
		try{
			System.out.println("Test 7 Start.");
			ccp.expand();
			browser.click(ccp.editRegionButn);
			String errorMsg=browser.checkTextAlertMessage().trim();
			Verify.assertEquals(errorMsg, "Please select a region before edit.");
			System.out.println("Test 7 completed.");
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentWindowHandle);	
			}	
		}
	}
	@Test(dataProvider="editRegionData",priority=80)
	public void editRegionTest(String regionName, String regionType, String statusType){
		String parentWindowHandle=null;
		try{
			System.out.println("Test 8 Start.");
			ccp.expand();
			browser.click(ccp.getRegionRadioButtonOfIndex(1));
			browser.click(ccp.editRegionButn);
			parentWindowHandle=browser.switchToNewlyOpenedWindow();
			browser.elementWait(4000);
			browser.enterInput(ccp.addRegionName,regionName);
			browser.selectFromDropDownList(ccp.addRegionStatus,statusType);
			/*
		WebElement From =browser.findWebElement(By.xpath("//*[@id='dojoUnique1']"));	 
		WebElement To = browser.findWebElement(By.xpath("//*[@id='blank_selected_Brand_hotels']"));
		Actions builder = new Actions(browser.getDriver());	 
		Action dragAndDrop = builder.clickAndHold(From)	 
		.moveToElement(To)	 
		.release(To)	 
		.build();	 
		dragAndDrop.perform();
			 */
			browser.elementWait(4000);
			browser.click(ccp.editRegionSaveButn);
			browser.switchToOriginalWindow(parentWindowHandle);	
			browser.elementWait(4000);
			ccp.expand();
			Verify.assertEquals(browser.getText(ccp.getRegionNameOfIndex(1)).trim(),regionName);
			Verify.assertEquals(browser.getText(ccp.getRegionStatusOfIndex(1)).trim(),statusType);
			//System.out.println(browser.getText(ccp.getRegionDefinitionOfIndex(1)).trim());
			System.out.println("Test 8 completed.");
		}
		finally{
			if(browser.getDriver().getWindowHandles().size() == 2){
				browser.getDriver().close();
				browser.switchToOriginalWindow(parentWindowHandle);	
			}	
		}
	}
	@DataProvider(name="addregionData") 
	public static Object[][] regionData() {
		return new Object[][] {{ "Kolkata4", "City","Active"}};
	}
	@DataProvider(name="editRegionData") 
	public static Object[][] editRegionData() {
		return new Object[][] {{ "Mumbai13", "City","Active"}};
	}
}
