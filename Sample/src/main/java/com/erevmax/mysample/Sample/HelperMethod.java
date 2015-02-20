package com.erevmax.mysample.Sample;

import static com.erevmax.baseTest.TestBase.browser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.webbitserver.WebbitException;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.thoughtworks.selenium.webdriven.commands.AlertOverride;


public class HelperMethod {

	private final String USER_AGENT = "Mozilla/5.0";
	public static WebDriver driver;
	protected WebElement element;
	public HelperMethod() {
	}

	public HelperMethod(WebDriver driver) {
		HelperMethod.driver = driver;
		HelperMethod.driver.manage().window().maximize();
	}

	public void openBrowser(String type) {
		switch (type) {
		case "FIREFOX":
			FirefoxProfile fp = new FirefoxProfile();
			fp.setPreference("webdriver.load.strategy", "unstable"); // As of 2.19. from 2.9 - 2.18 use 'fast'
			driver = new FirefoxDriver(fp);
			HelperMethod.driver.manage().window().maximize();
			break;
		case "chrome" :
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\resources\\browserdrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			HelperMethod.driver.manage().window().maximize();
			break;
		case "ie" :
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\resources\\browserdrivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			HelperMethod.driver.manage().window().maximize();
			break;
		}
	}

	//Method for clicking any checkbox button

	public void selectMultipleCheckBoxes(By lctr,int num){
		int input=0;
		int check=0;
		int uncheck=0;
		List<WebElement> ckBx = browser.findWebElements(lctr);
		List<WebElement> checkedList=new ArrayList<WebElement>();
		List<WebElement> uncheckedList=new ArrayList<WebElement>();
		System.out.println("total no. of check boxes:"+ckBx.size());
		int checkBoxListSize=ckBx.size();
		if(checkBoxListSize>=num){
			input=num;
		}
		else{
			input=checkBoxListSize;
		}
		int w=0;
		while(w<ckBx.size()){
			boolean bValue ;
			bValue = ((WebElement) ckBx.get(w)).isSelected();	
			if(bValue){
				check++;
				checkedList.add(ckBx.get(w));
			}
			else{
				uncheck++;
				uncheckedList.add(ckBx.get(w));
			}
			w++;
		}
		int c1=checkedList.size();
		int c2=uncheckedList.size();
		System.out.println("previously checked checkboxes:"+check);
		System.out.println("previously unchecked checkboxes:"+uncheck);
		if(c1>input){
			System.out.println("we have to unclick:"+(c1-input));
			for(int z=0;z<c1-input;z++){
				((WebElement) checkedList.get(z)).click();
			}
		}
		if(c1<input){
			System.out.println("we have to click:"+(input-c1));
			for(int z=0;z<input-c1;z++){
				((WebElement) uncheckedList.get(z)).click();
			}
		}

	}

	public void checkCheckBoxField(By lctr,int chekBoxNum){
		List<WebElement> ckBx = driver.findElements(lctr);
		int iSize=ckBx.size();
		if(chekBoxNum<=iSize-1){
			boolean bValue = false;
			bValue = ((WebElement) ckBx.get(chekBoxNum)).isSelected();
			if(!bValue){
				((WebElement) ckBx.get(chekBoxNum)).click();
			}
		}
		else{
			System.out.println("chekbox is invalid");
		}

	}

	public void deSelectCheckbox(By lctr,int chekBoxNum){
		List<WebElement> ckBx = driver.findElements(lctr);
		int iSize=ckBx.size();
		if(chekBoxNum<=iSize-1){
			boolean bValue = false;
			bValue = ((WebElement) ckBx.get(chekBoxNum)).isSelected();
			if(bValue){
				((WebElement) ckBx.get(chekBoxNum)).click();
			}
		}
		else{
			System.out.println("chekbox is invalid");
		}

	}

	public void openUrl(String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); 
	}

	//Method for retrieving attribute value from text-box
	public String getAttributeValue(By lctr) {
		String data = driver.findElement(lctr).getAttribute("value");
		return data;
	}

	//Method for getting text data
	public String getText(By lctr) {
		String data = driver.findElement(lctr).getText();
		return data;
	}


	//Method for splitting any text data
	public void splitData(By lctr,String delimiter){
		String data=getText(lctr);
		for (String retval: data.split(delimiter)){
			Integer convertedNumber = Integer.valueOf(retval);
			System.out.println(convertedNumber);
		}
	}

	public WebElement findWebElement(By lctr) {
		WebElement element = driver.findElement(lctr);
		return element;
	}

	public List<WebElement> findWebElements(By lctr) {
		List<WebElement> element = driver.findElements(lctr);
		return (List<WebElement>) element;
	}

	//Method for sending data to particular text-box
	public void enterInput(By lctr, String input) {
		WebElement element = findWebElement(lctr);
		element.click();
		element.clear();	
		element.sendKeys(input);
	}

	public void click(By lctr) {
		WebElement element = findWebElement(lctr);
		element.click();
	}

	//Method for Selecting any particular person from assigned to text-box
	public void autoSelect(By lctr1, By lctr2, String input) {
		WebElement element = findWebElement(lctr1);
		element.click();
		element.clear();
		browser.elementWait(3000);
		element.sendKeys(input);
		browser.elementWait(3000);
		List<WebElement> elements = driver.findElements(lctr2);
		for (WebElement val : elements) {
			String value = val.getText();	
			if (value.equals(input)) {
				val.sendKeys(Keys.DOWN);
				val.click();
				//browser.elementWait(2000);
				break;
			}
		}
	}
	/*
	public void autoSelect(By lctr1, By lctr2, String input) {
	  element= findWebElement(lctr1);
	    element.click();
	    element.sendKeys(input);
	    elementWait(4000);
	    element.sendKeys(Keys.DOWN);
	    elementWait(4000);
	  List<WebElement> options = findWebElements(lctr2);
	  System.out.println("Size of weblement is--->:" + options.size());
	  Iterator<WebElement> i = options.iterator();
	  while (i.hasNext()) {
	   WebElement text = i.next();
	   if (text.getText().trim().equals(input)) {
	    //driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	    text.click();
	    System.out.println("Inside loop");
	    elementWait(2000);
	    break;
	   }
	  }
	 }

	*/

	public List<String> getDropDownList(By lctr1, By lctr2, String input) {
		List<String> list=new ArrayList<String>();
		enterInput(lctr1,input);
		browser.elementWait(4000);
		List<WebElement> elements = driver.findElements(lctr2);
		for (WebElement val : elements) {
			String value = val.getText();
			list.add(value);
		}
		return list;
	}


	public void closeBrowser() {
		driver.quit();
	}

	public int getListSize(By lctr2){
		WebElement Webtable=driver.findElement(lctr2); 
		List<WebElement> TotalRowCount=Webtable.findElements(lctr2);
		int rows=TotalRowCount.size();
		return rows;
	}


	public String getCurrentUrl(){
		return driver.getCurrentUrl();	
	}

	// Method for printing any Table Record
	public void printTable(By lctr2) {

		WebElement Webtable=driver.findElement(By.className("tsc_table_s13")); // Replace TableID with Actual Table ID or Xpath
		List<WebElement> TotalRowCount=Webtable.findElements(By.xpath("//tbody/tr"));
		int rows=TotalRowCount.size();

		System.out.println("No. of Rows in the WebTable: "+TotalRowCount.size());
		// Now we will Iterate the Table and print the Values   
		int RowIndex=1;
		for(WebElement rowElement:TotalRowCount)
		{
			List<WebElement> TotalColumnCount=rowElement.findElements(By.xpath("td"));
			int ColumnIndex=1;
			for(WebElement colElement:TotalColumnCount)
			{
				System.out.print("Row "+RowIndex+" Column "+ColumnIndex+" Data "+colElement.getText());
				ColumnIndex=ColumnIndex+1;
			}
			RowIndex=RowIndex+1;
			System.out.println();
		}	
	}

	public String  checkTextAlertMessage(){
		Alert at= driver.switchTo().alert();
		browser.elementWait(3000);
		String message= at.getText();		
		at.accept(); //clicking ok
		return message;
		/*at.getText(); //getting text 
		at.dismiss(); //closing alert box
		 */		
	}


	/**
	 * switches to latest opened window.
	 *
	 * further methods will execute on the latest opened window.
	 *
	 * @return - Window handle for the current window.
	 *
	 *         call driver.switchTo.window(@return) to switch to original window
	 */
	public String switchToNewlyOpenedWindow() {
		// get the handle of latest window
		String oldWinHandle = driver.getWindowHandle();
		String latestWinHandle = null;
		for (String winHandle : driver.getWindowHandles())
			latestWinHandle = winHandle;
		driver.switchTo().window(latestWinHandle);
		return oldWinHandle;
	}

	public void switchToOriginalWindow(String current){
		driver.switchTo().window(current);
	}


	public boolean isDisplayed(By lctr){
		WebElement element=findWebElement(lctr);
		return element==null? null:element.isDisplayed();
	}


	/*public void getValue(String text){
		    	 HtmlUnitDriver element= new  HtmlUnitDriver();  
		    	 //driver.getPageSource()text;
		    	 //element.getPageSource().
		    	//System.out.println(element);
		    	if(element.equals(text)){
		    		System.out.println("Text is present");
		    	}
	 */
	
	public void selectFromDropDownList(By lctr,String data){
		WebElement dropDownListBox = driver.findElement(lctr);
		Select clickThis = new Select(dropDownListBox);
		clickThis.selectByVisibleText(data);
	}


	public void elementWait(long milli){
		try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  int sendGetRequest(String resourceUrl) {

		HttpClient client= new  DefaultHttpClient();
		HttpGet get= new HttpGet(resourceUrl);
		HttpResponse response = null;
		try {
			response = client.execute(get);
		} catch (ClientProtocolException e) {		
			e.printStackTrace();
		} catch (IOException e) {				
			e.printStackTrace();
		}
		return response.getStatusLine().getStatusCode();			    	    
	}

	public  boolean isResourceLoaded(String resourceUrl) {
		int getStatusCode= sendGetRequest(resourceUrl);
		if(getStatusCode == HttpStatus.SC_OK)
			return true;
		else
			return false;
	}
	
	 public void deSelectAllMultipleCheckBoxes(By lctr){
	    	int input=0;
	    	int check=0;
	    	int uncheck=0;
	    	List<WebElement> ckBx = findWebElements(lctr);
	    	List<WebElement> checkedList=new ArrayList<WebElement>();
	    	List<WebElement> uncheckedList=new ArrayList<WebElement>();
	    	//System.out.println("total no. of check boxes:"+ckBx.size());
	    	int checkBoxListSize=ckBx.size();
	    	/*
	    	if(checkBoxListSize>=num){
	    		input=num;
	    	}
	    	else{
	    		input=checkBoxListSize;
	    	}
	    	*/
	    	int w=0;
	    	while(w<ckBx.size()){
	    		boolean bValue ;
	    		bValue = ((WebElement) ckBx.get(w)).isSelected();

	    		if(bValue){
	    			check++;
	    			checkedList.add(ckBx.get(w));
	    		}
	    		else{
	    			uncheck++;
	    			uncheckedList.add(ckBx.get(w));
	    		}
	    		w++;
	    	}
	    	int c1=checkedList.size();
	    	int c2=uncheckedList.size();
	    	//System.out.println("previously checked checkboxes:"+check);
	    	//System.out.println("previously unchecked checkboxes:"+uncheck);
	    	if(c1>0){
	    		//System.out.println("we have to unclick:"+(c1-input));
	    		for(int z=0;z<c1;z++){
	    			((WebElement) checkedList.get(z)).click();
	    		}
	    	}
	    }
	 
	 

	    public int sendHttpGetRequest(String url)  {
	    	URL obj=null;
	    	try {
	    		obj = new URL(url);
	    	} catch (MalformedURLException e) {
	    		e.printStackTrace();
	    	}
	    	HttpURLConnection con=null;
	    	try {
	    		con = (HttpURLConnection) obj.openConnection();
	    	} catch (IOException e) {
	    		e.printStackTrace();
			}
	 		// optional default is GET
			try {
				con.setRequestMethod("GET");
			} catch (ProtocolException e) {
				e.printStackTrace();
			} 
			//add request header
			//con.setRequestProperty("User-Agent", USER_AGENT);
	 		int responseCode=-1;
			try {
				responseCode = con.getResponseCode();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			/*BufferedReader in = null;
			try {
				in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			String inputLine=null;
			StringBuffer response = new StringBuffer();
	 		try {
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			System.out.println("Page loaded response code is :"+responseCode);
			return responseCode;

	    }


	    public void sendHttpPostRequest(String url) {
	    	URL obj = null;
	    	try {
	    		obj = new URL(url);
	    	} catch (MalformedURLException e1) {
	    		// TODO Auto-generated catch block
	    		e1.printStackTrace();
	    	}
	    	HttpURLConnection con = null;
	    	try {
	    		con = (HttpURLConnection) obj.openConnection();
	    	} catch (IOException e2) {
	    		// TODO Auto-generated catch block
	    		e2.printStackTrace();
	    	}
	    	//add reuqest header
	    	try {
	    		con.setRequestMethod("POST");
	    	} catch (ProtocolException e1) {
	    		// TODO Auto-generated catch block
	    		e1.printStackTrace();
	    	}
	    	con.setRequestProperty("User-Agent", USER_AGENT);
	    	con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	    	String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345"; 
	    	// Send post request
	    	con.setDoOutput(true);
	    	/*DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();*/

	    	int responseCode = 0;
	    	try {
	    		responseCode = con.getResponseCode();
	    	} catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    	System.out.println("\nSending 'POST' request to URL : " + url);
	    	System.out.println("Post parameters : " + urlParameters);
	    	System.out.println("Response Code : " + responseCode);

	    	BufferedReader in = null;
	    	try {
	    		in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    	} catch (IOException e1) {
	    		// TODO Auto-generated catch block
	    		e1.printStackTrace();
	    	}
	    	String inputLine;
	    	StringBuffer response = new StringBuffer();
	    	try {
	    		while ((inputLine = in.readLine()) != null) {
	    			response.append(inputLine);
	    		}
	    	} catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    	try {
	    		in.close();
	    	} catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}

	    	//print result
	    	System.out.println(response.toString());

	    }


}




		
	


