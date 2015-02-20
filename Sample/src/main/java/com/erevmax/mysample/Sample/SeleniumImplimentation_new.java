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
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.erevmax.utils.CustomException;
import com.thoughtworks.selenium.SeleniumException;

public class SeleniumImplimentation_new implements SeleniumInterface,SearchContext{
	public  static ThreadLocal<WebDriver> threaddriver = new ThreadLocal<WebDriver>();
	private WebElement element;
	private List<WebElement> elements;
	public SearchContext  context;
	private final String USER_AGENT = "Mozilla/5.0";
	
	public SeleniumImplimentation_new(){
		 
	}
	
	@Override
	public WebDriver getDriver(WebDriver driver) {
			return threaddriver.get();
	} 
    private  void setWebDriver(WebDriver driver) {
    	threaddriver.set(driver);
    }

	@Override
	public WebDriver createBrowserInstance(String browserName) {
		if (browserName.toLowerCase().contains("firefox")) {
					setWebDriver(new FirefoxDriver());					
            return driver;
        }
        if (browserName.toLowerCase().contains("ie")) {
            setWebDriver(new InternetExplorerDriver());
            return driver;
        }
        if (browserName.toLowerCase().contains("chrome")) {
             setWebDriver(new ChromeDriver());
             return driver;
        }
        return driver;
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
		
	public void openUrl(String url){
		threaddriver.get().navigate().to(url);
		//maximizeBrowser();
	}
	
	@Override
	public void clear() {
		element.clear();
		
	}

	@Override
	public void click() {
		element.click();
		
	}
	@Override
	public void click(By locator) {
		element= findWebElement(locator);
		click();
		
	}

	@Override
	public WebElement findElement(By lctr) {
		context=threaddriver.get();
		return element= context.findElement(lctr);
	}

	@Override
	public List<WebElement> findElements(By locator) {
		context=threaddriver.get();
		return elements= context.findElements(locator);
	}

	@Override
	public String getAttribute(String arg0) {
		String text= element.getAttribute(arg0);
		return text.trim();
	}
	 public String getAttributeValue(By lctr, String attributeName) {
	     element= findWebElement(lctr);
	     String value= getAttribute(attributeName);
	     return value;
	    }


	@Override
	public String getCssValue(String cssName) {
		String cssValue= element.getCssValue(cssName);
		return cssValue;
	}

	@Override
	public Point getLocation() {
		
		return null;
	}

	@Override
	public Dimension getSize() {
		
		return null;
	}

	@Override
	public String getTagName() {
		
		return null;
	}

	@Override
	public String getText() {
		return element.getText().trim();
	}
	
	public String getText(By locator) {
		element = findWebElement(locator);
		 return getText();
		}

	@Override
	public boolean isDisplayed() {
		return element.isDisplayed();
	}
	public boolean isDisplayed(By locator) {
		waitUntilelementDisplayed(locator);
		element = findWebElement(locator);
		return isDisplayed();
	}

	@Override
	public boolean isEnabled() {
		return element.isEnabled();
	}
	

	@Override
	public boolean isSelected() {
		return element.isSelected();
	}

	@Override
	public void sendKeys(CharSequence... arg0) {
		element.sendKeys(arg0);
		}
	
	
	

	@Override
	public void submit() {
		element.submit();
		
	}

	@Override
	public RemoteWebDriver getRcDriver(RemoteWebDriver rcDriver) {
		return null;
	}

	@Override
	public void enterInput(By locator,String value) {
		element=findWebElement(locator);
		click();
		clear();
		sendKeys(value);
		
	}

	@Override
	public WebElement findWebElement(By locator) {
		element= waitUntilelementDisplayed(locator);
		return element=findElement(locator);
		
	}
	
	public WebElement waitUntilelementDisplayed(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(driver), 180);
		element= wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return element;
	}
	
	@Override
	public void elementWait(long milli) {
		try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<WebElement> findWebElements(By locator) {
		 waitUntilElementsDisplayed(locator);
		return elements=findElements(locator);
		
	}
	
	public void waitUntilElementsDisplayed(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(driver), 180);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		//return elements;
	}
	
	public void autoSelect(By lctr1, By lctr2, String input) {
		element= findWebElement(lctr1);
	  	element.click();
	  	element.clear();
	  	element.sendKeys(input);
	  	elementWait(4000);
	  	if(input.equals("")|| input.equals(null))
	  		return;
	  	element.sendKeys(Keys.DOWN);
	  	elementWait(4000);
		List<WebElement> options = findWebElements(lctr2);
		System.out.println("Size of weblement is--->:" + options.size());
		Iterator<WebElement> i = options.iterator();
		while (i.hasNext()) {
			WebElement text = i.next();
			if (text.getText().trim().equals(input)) {
				text.click();
				System.out.println("Inside loop");
				elementWait(2000);
				break;
			}	}	}
	
	public Set<String> getListOfWindows() {
		Set<String> handles = getDriver(driver).getWindowHandles();
		return handles;
	}
	public void switchToWindow(String windowId) {
		driver.switchTo().window(windowId);
	}
	public boolean isPageLoad(String pageTitle) {
		return (driver.getTitle().contains(pageTitle));
	}
	public String getPageTitle() {
		return getDriver(driver).getTitle();
	}
	public String getCurrentUrl() {
		return getDriver(driver).getCurrentUrl();
	}
	public void close() {
		getDriver(driver).quit();
	}
	
	public void selectFromDropDownList(By lctr, String data) {
		WebElement dropDownListBox = findWebElement(lctr);
		Select clickThis = new Select(dropDownListBox);
		clickThis.selectByVisibleText(data);
	}
	
	public String switchToNewlyOpenedWindow() {
		// get the handle of latest window
		String oldWinHandle = getDriver(driver).getWindowHandle();
		String latestWinHandle = null;
		for (String winHandle : getDriver(driver).getWindowHandles())
			latestWinHandle = winHandle;
		getDriver(driver).switchTo().window(latestWinHandle);
		return oldWinHandle;
	}

	public void switchToOriginalWindow(String current) {
		getDriver(driver).switchTo().window(current);
	}
	
	public int getListSize(By lctr) {
		List<WebElement> elements = findWebElements(lctr);
		return elements.size();
	}
	public String  checkTextAlertMessage(){
	     Alert at= getDriver(driver).switchTo().alert();
	     elementWait(3000);
	     String message= at.getText().trim();  
	     at.accept(); 
	     return message;
	     
	    }
	
	public void selectMultipleCheckBoxes(By lctr, int num) {
		int input = 0;
		int check = 0;
		int uncheck = 0;
		List<WebElement> ckBx = findWebElements(lctr);
		List<WebElement> checkedList = new ArrayList<WebElement>();
		List<WebElement> uncheckedList = new ArrayList<WebElement>();
		System.out.println("total no. of check boxes:" + ckBx.size());
		int checkBoxListSize = ckBx.size();
		if (checkBoxListSize >= num) {
			input = num;
		} else {
			input = checkBoxListSize;
		}
		int w = 0;
		while (w < ckBx.size()) {
			boolean bValue;
			bValue = ((WebElement) ckBx.get(w)).isSelected();

			if (bValue) {
				check++;
				checkedList.add(ckBx.get(w));
			} else {
				uncheck++;
				uncheckedList.add(ckBx.get(w));
			}
			w++;
		}
		int c1 = checkedList.size();
		int c2 = uncheckedList.size();
		System.out.println("previously checked checkboxes:" + check);
		System.out.println("previously unchecked checkboxes:" + uncheck);
		if (c1 > input) {
			System.out.println("we have to unclick:" + (c1 - input));
			for (int z = 0; z < c1 - input; z++) {
				((WebElement) checkedList.get(z)).click();
			}
		}
		if (c1 < input) {
			System.out.println("we have to click:" + (input - c1));
			for (int z = 0; z < input - c1; z++) {
				((WebElement) uncheckedList.get(z)).click();
			}		}	}
	
	public void checkCheckBoxField(By lctr, int chekBoxNum) {
		List<WebElement> ckBx = findWebElements(lctr);
		int iSize = ckBx.size();
		if (chekBoxNum <= iSize - 1) {
			boolean bValue = false;
			bValue = ((WebElement) ckBx.get(chekBoxNum)).isSelected();
			if (!bValue) {
				((WebElement) ckBx.get(chekBoxNum)).click();
			}
		} else {
			System.out.println("chekbox is invalid");
		}	}
	
	public void deSelectCheckbox(By lctr, int chekBoxNum) {
		List<WebElement> ckBx = findWebElements(lctr);
		int iSize = ckBx.size();
		if (chekBoxNum <= iSize - 1) {
			boolean bValue = false;
			bValue = ((WebElement) ckBx.get(chekBoxNum)).isSelected();
			if (bValue) {
				((WebElement) ckBx.get(chekBoxNum)).click();
			}
		} else {
			System.out.println("chekbox is invalid");
		}	}
	
	public void deSelectAllMultipleCheckBoxes(By lctr) {
		int input = 0;
		int check = 0;
		int uncheck = 0;
		List<WebElement> ckBx = findWebElements(lctr);
		List<WebElement> checkedList = new ArrayList<WebElement>();
		List<WebElement> uncheckedList = new ArrayList<WebElement>();
		//int checkBoxListSize = ckBx.size();
		int w = 0;
		while (w < ckBx.size()) {
			boolean bValue;			
			bValue = ((WebElement) ckBx.get(w)).isSelected();
			if (bValue) {
				check++;
				checkedList.add(ckBx.get(w));
			} else {
				uncheck++;
				uncheckedList.add(ckBx.get(w));
			}
			w++;
		}
		int c1 = checkedList.size();
		//int c2 = uncheckedList.size();
		if (c1 > 0) {

			for (int z = 0; z < c1; z++) {
				((WebElement) checkedList.get(z)).click();
			}		}	}
	
	
	public int sendHttpGetRequest(String url) {
		URL obj = null;
		try {
			obj = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection con = null;
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
		// add request header
		// con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = -1;
		try {
			responseCode = con.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * BufferedReader in = null; try { in = new BufferedReader( new
		 * InputStreamReader(con.getInputStream())); } catch (IOException e) {
		 * e.printStackTrace(); } String inputLine=null; StringBuffer response =
		 * new StringBuffer(); try { while ((inputLine = in.readLine()) != null)
		 * { response.append(inputLine); } } catch (IOException e) {
		 * e.printStackTrace(); } try { in.close(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
		System.out.println("Page loaded response code is :" + responseCode);
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
		// add reuqest header
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
		/*
		 * DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		 * wr.writeBytes(urlParameters); wr.flush(); wr.close();
		 */

		int responseCode = 0;
		try {
			responseCode = con.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String inputLine;
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
		}
		// print result
		System.out.println(response.toString());

	}

	public  boolean isURLLoaded(String resourceUrl) {
	     int getStatusCode= sendGetRequest(resourceUrl);
	     if(getStatusCode == HttpStatus.SC_OK)
	      return true;
	     else
	      return false;
	    }
	
	 private  int sendGetRequest(String resourceUrl) {
	     
	     HttpGet get= new HttpGet(resourceUrl);
	     HttpResponse response = null;
	     try {
	      HttpClient client= new  DefaultHttpClient();
	      response = client.execute(get);
	      } catch (ClientProtocolException e) {  
	      e.printStackTrace();
	     } catch (IOException e) {    
	      e.printStackTrace();
	     }
	     return response.getStatusLine().getStatusCode();            
	    }




	
		
	

	

}
