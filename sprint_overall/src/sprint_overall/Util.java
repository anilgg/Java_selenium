package sprint_overall;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.WebDriverException;
import java.util.GregorianCalendar;


public class Util {	
	
	//class members
	private static String propFilePath = null;	
	private static WebDriver driver = null;	
	private static boolean acceptNextAlert = true;
	
	//Get property value
	public static String getPropertyValue(String propName) 
	{
		System.out.println("propName: " + propName);
		// Find the relative path for the config.properties file
		Path path = FileSystems.getDefault().getPath("config", "config.properties");
		propFilePath = path.toAbsolutePath().toString();
		System.out.println(path.toAbsolutePath().toString());
		
		String propValue = null;		
		Properties properties = new java.util.Properties();
		
		try{
			
			properties.load(new FileInputStream(propFilePath));
			propValue = properties.getProperty(propName);
		
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
										
		} catch (IOException ex) {
			ex.printStackTrace();
		
		} finally {
			properties = null;
			}
			return propValue;
		
	}		
	//Setup driver for specific browser type 
	
	public static void setupDriver(String browserType) {
		
		try{			
			//if (browserType.equals("Chrome"))	{		
			if (browserType.equals("FireFox")){
				
				//System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
				
				String PROXY = Util.getPropertyValue("proxyURL");		

				Proxy proxy = new Proxy();
				proxy.setSocksProxy(PROXY);
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(CapabilityType.PROXY, proxy);
				
				ProfilesIni profile = new ProfilesIni();
				FirefoxProfile ffprofile = profile.getProfile("sprintProfile");
				
				if ((getPropertyValue("platform")).equals("desktop")) {
					
					//driver = new FirefoxDriver(ffprofile);
					driver = new FirefoxDriver(cap);
				} else if ((getPropertyValue("platform")).equals("mobileAndroid")){
					ffprofile.setPreference("general.useragent.override", "Mozilla/5.0 (Linux; Android 4.3; SPH-L710 Build/JSS15J) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.99 Mobile Safari/537.36");
					driver = new FirefoxDriver(ffprofile);
				} else if ((getPropertyValue("platform")).equals("mobileiOS")){
					ffprofile.setPreference("general.useragent.override", "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_2_1 like Mac OS X; da-dk) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148 Safari/6533.18.5");
					driver = new FirefoxDriver(ffprofile);
				}
				
				
				/*String PROXY = Util.getPropertyValue("proxyURL");		

				Proxy proxy = new Proxy();
				proxy.setSocksProxy(PROXY);
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(CapabilityType.PROXY, proxy);  

				
				driver = new FirefoxDriver(); */


			
			} else if (browserType.equals("IE")){		
				
				// Find the relative path for the config.properties file
				Path path = FileSystems.getDefault().getPath("libs", "IEDriverServer.exe");				
				
				propFilePath = path.toAbsolutePath().toString();
				System.out.println(path.toAbsolutePath().toString());
				
				File file = new File(propFilePath);
				System.out.println(file.getAbsolutePath());
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				
				driver = new InternetExplorerDriver();
			
			}else if (browserType.equals("Chrome")){
			//else if (browserType.equals("FireFox")){
				// Find the relative path for the config.properties file
				Path path = FileSystems.getDefault().getPath("libs", "chromedriver.exe");				
				
				propFilePath = path.toAbsolutePath().toString();
				System.out.println(path.toAbsolutePath().toString());
				
				File file = new File(propFilePath);
				System.out.println(file.getAbsolutePath());
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				driver = new ChromeDriver(DesiredCapabilities.chrome());
			}
		} catch (WebDriverException wde){
			wde.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}		
				
	}	
	
	public static WebDriver getDriver(){
		return driver;
		
	}
	
	// get the text between html tag
	public static String getText(WebDriver d, WebElement e) {
    	return (String)((JavascriptExecutor) d).executeScript("return arguments[0].innerHTML;", e);
    }
    
	//wait until the object found by id
    public static void waitById(WebDriver d, String id) {
    	System.out.println("Waiting for " + id + "  to appear");
    	waitBy(d,By.id(id));
    }    
      
       
    //wait until the object found by id
    public static void waitElementToBeClickable(WebDriver d, String id) {
    	System.out.println("Loading " + id + ".");
    	WebDriverWait wait = new WebDriverWait(driver,30);
		
		
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
		
	
		    	
    } 
    
    //wait until the object found by id
    public static void waitPresenceOfElementLocated(WebDriver d, String id) {
    	System.out.println("Loading " + id + ".");
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
			    	
    } 

    private static void waitBy(WebDriver d, final By by) {
    	(new WebDriverWait(d, 15)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
       		  return d.findElements(by) != null;
           }
        });
    }  
    
      
    //Put a delay
    public static void wait(int sec) {
    	try {
    		Thread.sleep(sec*1000L);
    	}catch (Exception e) {
    		System.out.println(e.toString());
    	}
    }   
     
    // handling dialog box        
	public static boolean isAlertPresent() {
	    /*try {
	      driver.switchTo().activeElement();
	      System.out.println("true");
	      Alert alert = driver.switchTo().alert();
	      alert.dismiss();
	      return true;
	    } catch (NoAlertPresentException e) {
	    	System.out.println("false");
	    	return false;
	    }*/
		
		try{
            driver.switchTo().alert().dismiss();
            return true;
     }catch (org.openqa.selenium.NoAlertPresentException alpe){
            System.out.println("No alert is present (WARNING: The server did not provide any stacktrace information");
            alpe.printStackTrace();
            return false;
     }

	}
	//checking if the object exist
	static boolean isElementPresent(By by) {
	    try {
	      driver.findElements(by);
	      
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	}
	
	public static String closeAlertAndGetItsText() {
		    try {
		      Alert alert = driver.switchTo().alert();
		      String alertText = alert.getText();
		      System.out.println("alertText: " + alertText);
		      if (acceptNextAlert) {
		        alert.accept();
		      } else {
		        alert.dismiss();
		      }
		      return alertText;
		    } finally {
		      acceptNextAlert = true;
		    }
		  }
	
	//Get Greetings based of the time of day
	public static String getGreeting() {
		GregorianCalendar time = new GregorianCalendar();  
		int hour = time.get(Calendar.HOUR_OF_DAY);  
		int min = time.get(Calendar.MINUTE);  
		int day = time.get(Calendar.DAY_OF_MONTH);  
		int month = time.get(Calendar.MONTH) + 1;  
		int year = time.get(Calendar.YEAR);  
		String greeting = null;
		  
		System.out.println("The current time is \t" + hour + ":" + min);  
		System.out.println("Today's date is \t" + month + "/" + day + "/"  
		    + year);  
		  
		if (hour < 12) { 
		   System.out.println("Good Morning");  
		   greeting = "Good Morning,";
		} else if (hour < 17 && !(hour == 12))  {
		   System.out.println("Good Afternoon,");  
		   greeting = "Good Afternoon,";
		} else if (hour == 12)  {
		   System.out.println("Good Afternoon,");  
			greeting = "Good Afternoon,";
		}
	    else {
		   System.out.println("Good Evening,");
		   greeting = "Good Evening,";
	    }
		return greeting.toString();  
		
	}

	public static void writePropertyValue(WebDriver driver2, String string) {
		// TODO Auto-generated method stub
		
	}
}
