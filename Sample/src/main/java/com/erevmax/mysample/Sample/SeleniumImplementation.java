package com.erevmax.mysample.Sample;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;




///
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;





////
import static java.lang.Thread.currentThread;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.Point;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumImplementation {
	public enum BrowserType {
		FIREFOX;
	}
	//public WebDriver driver;
	public String bangalore_URL="http://176.74.173.43:5159/RTAdmin/";
	public String kolkata_URL="http://176.74.173.39:9797/RTAdmin-2.2.4/";
	protected String pageTitle;
	protected String URL;
	private WebElement element;
	private List<WebElement> elements;
	private final String USER_AGENT = "Mozilla/5.0";
	protected List<WebDriverEventListener> listeners = new ArrayList<WebDriverEventListener>();
	protected Collection<Thread> ALL_WEB_DRIVERS_THREADS = new ConcurrentLinkedQueue<Thread>();
	protected Map<Long, WebDriver> THREAD_WEB_DRIVER = new ConcurrentHashMap<Long, WebDriver>(4);
	protected final AtomicBoolean cleanupThreadStarted = new AtomicBoolean(false);
	private JavascriptExecutor js;
	private RemoteWebDriver driver;


	public SeleniumImplementation(){
	}
	public  Logger getLog(){
		Logger Log = Logger.getLogger(Logger.class.getName());
		DOMConfigurator.configure("log4j.xml");
		return Log;
	}
	public SeleniumImplementation(RemoteWebDriver drv) {
		this.driver = drv;
		setJs(driver);
	}
	public void setJs(JavascriptExecutor js) {
		this.js = js;
	}

	public RemoteWebDriver getDriver() {
		return driver;
	}

	public void closeBrowser() {
		driver.quit();
	}
	public void openUrl(String url) {
		//driver.navigate().to(url);
		getDriver().navigate().to(url);
		maximizeBrowser();

	}

	private void maximizeBrowser() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().setPosition(new Point(0, 0));
		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		Dimension dim = new Dimension((int) screenSize.getWidth(),
				(int) screenSize.getHeight());
		driver.manage().window().setSize(dim);
	}

	public void waitForNumberOfWindowsToEqual(final int numberOfWindows) {
	    new WebDriverWait(driver, 50) {
	    }.until(new ExpectedCondition<Boolean>() {
	        @Override
	        public Boolean apply(WebDriver driver) {                        
	            return (driver.getWindowHandles().size() == numberOfWindows);
	        }
	    });
	}


	/*public void openBrowser(enum browser) {
		//String type= browserType.equalsIgnoreCase(browserType);
		switch (BrowserType.FIREFOX) {
		case FIREFOX:
			PropertyConfigurator.configure(System.getProperty("user.dir")+"\\resources\\log4j.properties");
			driver = new FirefoxDriver();
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")	+ "\\resources\\browserdrivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		case "CHROME":
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\resources\\browserdrivers\\chromedriver.exe");
			ChromeDriverService service = ChromeDriverService.createDefaultService();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(service, options);
			break;
		default:
			throw new RuntimeException(
					"no driver loaded as none was specified in the configuration ...");
		}

	}
*/
	public WebDriver openBrowserType(BrowserType browserType) {
		switch (browserType) {
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		/*case "IE":
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")	+ "\\resources\\browserdrivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\resources\\browserdrivers\\chromedriver.exe");
			ChromeDriverService service = ChromeDriverService.createDefaultService();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(service, options);
			break;*/
		default:
			throw new RuntimeException(
					"no driver loaded as none was specified in the configuration ...");
		}
		return driver;

	}




	public boolean isPageLoad() {
		return (driver.getTitle().contains(pageTitle));
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void close() {
		driver.quit();
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
	}


	public List<WebElement> getElements(By locator) {
		SearchContext searchContext = driver;
		return searchContext.findElements(locator);
	}


	public WebElement findWebElement(By locator) {
		//WebElement element = (new WebDriverWait(driver, 60*3)).until( ExpectedConditions.presenceOfElementLocated(locator));
		SearchContext searchContext = driver;
		waitUntilelementDisplayed(locator);
		//element = driver.findElement(locator);
		return searchContext.findElement(locator);
	}

	public List<WebElement> findWebElements(By locator) {
		SearchContext searchContext = driver;
		waitUntilelementDisplayed(locator);
		List<WebElement> elements= searchContext.findElements(locator);
		return (List<WebElement>) elements;
	}
	/*
	public WebElement findWebElement(By lctr) {
		WebElement element = driver.findElement(lctr);
		return element;
	}

	public List<WebElement> findWebElements(By lctr) {
		List<WebElement> element = driver.findElements(lctr);
		return (List<WebElement>) element;
	}
*/
	/*public void waitUntilelementDisplayed(final By locator){
		(new WebDriverWait(driver, 180)).until(new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver driver) {
			return driver.findElement(locator).isDisplayed();
			}
		});

	}*/
	public void waitUntilelementDisplayed(final By locator){
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

	public void click(By locator) {
		WebElement element = findWebElement(locator);
		element.click();
	}

	public int k=0;
	public void enterInput(By locator, String sendKeys) {
	
		WebElement element = findWebElement(locator);
		element.click();
		element.clear();
		//element.sendKeys(sendKeys.trim());
		int strLength = sendKeys.trim().length();
		for(int i=0; i<strLength; i++){
			element.sendKeys(sendKeys.substring(i, i+1));
			
			if(i==2){
				elementWait(400);
			}
			else if(i==strLength-1){
				elementWait(200);
			}
			else{
				elementWait(200);
			}
		}
		elementWait(1000);
		
		if(!getAttributeValue(locator, "value").equals(sendKeys)){
			k++;
			if(k>5)
				return ;
			else
			enterInput( locator,sendKeys);
			
		}
		
	}
	
	
	

	public void enterInput(String sendKeys) {
		element.sendKeys(sendKeys.trim());

	}
	public void doubleClickOn(By lctr){
		Actions action = new Actions(driver); 
		action.doubleClick(driver.findElement(lctr)); 
		action.perform();
	}

	public boolean isTextPresent(String text) {
		HtmlUnitDriver d= new HtmlUnitDriver();
		WebDriverWait wait = new WebDriverWait(d, 15);
		//wait.until(ExpectedConditions.);
		if (d.getPageSource().trim().contains(text))
			return true;
		else
			return false;

	}

	@Deprecated
	public boolean isElementPresentAndDisplay(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}	}
 /*
	public void selectDropDownList(By locator, String data){
		WebElement element= findWebElement(locator);
		Select sele= new Select(element);
		for(WebElement value: sele.getOptions()){
			if (value.equals(data)){
				sele.selectByVisibleText(data);
			System.out.println(value);
				break;
			}
		}
	}
*/
	public void selectFromDropDownList(By lctr,String data){
		WebElement dropDownListBox = findWebElement(lctr);
		Select clickThis = new Select(dropDownListBox);
		clickThis.selectByVisibleText(data);
		//clickThis.selectByValue(data);
	}

	public List<String> getDropDownList(By lctr1, By lctr2, String input) {
		List<String> list=new ArrayList<String>();
		enterInput(lctr1,input);
		elementWait(4000);
		List<WebElement> elements = driver.findElements(lctr2);
		for (WebElement val : elements) {
			String value = val.getText();
			list.add(value);
		}
		return list;
	}

	public void elementWait(long milli){
	/*	try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		for(int i=1; i<=(milli/1000);i++)
		  {
		   Calendar c = Calendar.getInstance();
		   try {
		    Thread.sleep(1000);
		    System.out.println(c.getTime());
		   } catch (InterruptedException e) {
		    e.printStackTrace();
		   }
		  }
	}

	public void waitForElementVisible(By lctr) throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);  //nullify implicit wait
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(lctr));
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);  //nullify implicit wait
	}
	public void autoSelectFromAjaxList(By lctr1,By lctr2,String sendKeys){
	/*	int strLength = sendKeys.length();
		for(int i=0; i<strLength; i++){
			browser.findWebElement(lctr1).sendKeys(sendKeys.substring(i, i+1));
			
			if(i==2){
				elementWait(400);
			}
			else if(i==strLength-1){
				elementWait(200);
			}
			else{
				elementWait(200);
			}
		}
		*/	//browser.findWebElement(lctr1).sendKeys(sendKeys);
		enterInput(lctr1,sendKeys);
		WebDriverWait wait2 = new WebDriverWait(getDriver(), 50);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(lctr2));   	
		findWebElement(lctr2).click();    	
		elementWait(2000);
	}
	/*
	public void autoSelect(By lctr1, By lctr2, String input) {
	  element= findWebElement(lctr1);
	    element.click();
	    element.clear();
		elementWait(2000);
	    element.sendKeys(input);
	    if(input.equals(null)|| input.equals(""))
			return;
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

/*
	public  void autoSelect(By lctr1,By lctr2, String input){
		WebElement element=findWebElement(lctr1);
		element.click();
		element.clear();
		browser.elementWait(3000);
		element.sendKeys(input.trim());
		if(input.equals(null)|| input.equals(""))
		return;
		browser.elementWait(3000);
		WebElement select = findWebElement(lctr2); 
		List<WebElement> options = select.findElements(By.tagName("option")); 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//System.out.println("Size of list is" +options.size());
		Iterator<WebElement> i = options.iterator();
		while(i.hasNext()) {   
			WebElement text = i.next(); 
			if(text.getText().equals(input)){
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				//text.sendKeys(Keys.TAB);
				text.sendKeys(Keys.DOWN);
				text.click();

				break;
			}  } 
		
	}
	*/
	//Method for Selecting any particular person from assigned to text-box
		public void autoSelect(By lctr1, By lctr2, String input) {
			enterInput(lctr1,input);
			elementWait(4000);
			List<WebElement> elements = driver.findElements(lctr2);
			for (WebElement val : elements) {
				String value = val.getText();	
				if (value.equals(input)) {
					val.sendKeys(Keys.DOWN);
					val.click();
					//	browser.sleep(2000);
					break;
				}
			}
		}


	public Set<String> getListOfWindows() {
		Set<String> handles =  driver.getWindowHandles();
		return  handles;    	  
	}
	/*  
    public  void switchToWindow(String windowId){
    	driver.switchTo().window(windowId);
    }

    	 public  void swithTo(String parentWindow){
    			Set<String> handles =  getListOfWindows();
    			for(String windowHandle  : handles){
    				if(!windowHandle.equals(parentWindow)){
    					switchToWindow(windowHandle);
    				}
    			}
    		}


    	 public String getCurrentWindowID() {
    	    	String currentWindowId;
    	    	currentWindowId= driver.getWindowHandle();
    	    	return  currentWindowId;    	  
    	        }
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


    	 
    
    public  boolean isSelected(By ele) {
    	WebElement element= findWebElement(ele);   
    	return element.isSelected();
    }
    
    public void waitForElementClickable(By lctr) throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);  //nullify implicit wait
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.elementToBeClickable(lctr));
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  //reset implicit wait
	}
   

    
    public  void pageRefresh(){
    	driver.navigate().refresh();
    }
    
    public  boolean isEnabled(By ele) {
    	WebElement element=findWebElement(ele);
    	//ATUReports.add("Check the application elements are enabled or not", " ","Verify the application elements should be enabled"," ",true);
    	return element == null ? null : element.isEnabled();
    }


    public String getText(By locator) {
    	WebElement text = findWebElement(locator);
    	return text.getText();
    	//return text == null ? null : text.getText();
    }
    
    public List<String> getListText(By locators) {
    	List<WebElement> text = findWebElements(locators);
    	List<String> ls= new ArrayList<String>();
    	for(WebElement x:text){
    		ls.add(x.getText().trim());
    	}
    	
    	return ls;
    	//return text == null ? null : text.getText();
    }


    //Method for retrieving attribute value from text-box
    public String getAttributeValue(By lctr,String attr) {
    	String data = driver.findElement(lctr).getAttribute(attr);
    	return data;
    }

    public boolean isDisplayed(By locator) {
    	waitUntilelementDisplayed(locator);
    	WebElement text = findWebElement(locator);
    	return text.isDisplayed();
    }



    public  boolean isAttributeDisplayed(By lctr,String attr){
    	WebElement element= findWebElement(lctr);
    	String value= element.getAttribute(attr);
    	if(value==null){
    		return false;
    	}
    	return true;

    }

    public int getListSize(By lctr){
    	List<WebElement> elements=findWebElements(lctr);
    	return elements.size();
    }

    /* public List<WebElement> getAttributeList(String val){
    	List<WebElement> ckBx = 
		return elements;

    }*/
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


    public void selectMultipleCheckBoxes(By lctr,int num){
    	int input=0;
    	int check=0;
    	int uncheck=0;
    	List<WebElement> ckBx = findWebElements(lctr);
    	List<WebElement> checkedList=new ArrayList<WebElement>();
    	List<WebElement> uncheckedList=new ArrayList<WebElement>();
    	//System.out.println("total no. of check boxes:"+ckBx.size());
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
    	//System.out.println("previously checked checkboxes:"+check);
    	//System.out.println("previously unchecked checkboxes:"+uncheck);
    	if(c1>input){
    		//System.out.println("we have to unclick:"+(c1-input));
    		for(int z=0;z<c1-input;z++){
    			((WebElement) checkedList.get(z)).click();
    		}
    	}
    	if(c1<input){
    		//System.out.println("we have to click:"+(input-c1));
    		for(int z=0;z<input-c1;z++){
    			((WebElement) uncheckedList.get(z)).click();
    		}
    	}

    }

    public void checkCheckBoxField(By lctr,int chekBoxNum){

    	List<WebElement> ckBx = findWebElements(lctr);
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
    	List<WebElement> ckBx = findWebElements(lctr);
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

    public String  checkTextAlertMessage(){
    	Alert at= driver.switchTo().alert();
    	elementWait(3000);
    	String message= at.getText();		
    	at.accept(); //clicking ok
    	return message;
    	/*at.getText(); //getting text 
		at.dismiss(); //closing alert box
    	 */		
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

    public void waitForAlert() throws InterruptedException
	{
	   int i=0;
	  	   while(i++<10)
	   {
	        try
	        {
	            Alert alert = driver.switchTo().alert();
	            break;	            
	        }
	        catch(NoAlertPresentException e)
	        {
	          Thread.sleep(1000);
	          continue;
	        }	
	   }
	}
	
	public boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	    catch (NoSuchWindowException Ex1) 
	    { 
	        return false; 
	    }
	}  
	
	public String getAlertText(){
		Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        return alertText;
	}
	
	public void acceptAlert(){
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void dismissAlert(){
		Alert alert = driver.switchTo().alert();
		alert.dismiss();;
	}
	
	public void waitForAlertandAccept() throws InterruptedException
	{
	   int i=0;
	   while(i++<5)
	   {
	        try
	        {
	            Alert alert = driver.switchTo().alert();
	            alert.accept();
	            break;
	        }
	        catch(NoAlertPresentException e)
	        {
	          Thread.sleep(1000);
	          continue;
	        }
	   }
	}
	
	public boolean compareUIandDBdata(List<String> ui,List<String> db){
		if(ui.size()==db.size()){
			for(int count=0;count<ui.size();count++){
				if(ui.get(count).equals(db.get(count))){	
				}
				else{
					System.out.println("UI and DB data is not matching");
					return false;
				}
			}
			return true;
		}
		else{
			System.out.println("UI view status count="+ui.size());
			System.out.println("DB view status count="+db.size());
			return false;
		}
	}

}


    

	
	
	 

