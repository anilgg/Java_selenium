package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverManaget {
	public static WebDriver driver;
	public static String url = "https://foundationia.alpha.live.qa.oceanwidebridge.com/Foundation.IAS.WebClient/Default.aspx";
	public static void browserInstantiate(String browser){
		if(browser.equals("firefox")){
			driver = new FirefoxDriver();
		}else driver = new InternetExplorerDriver();
		 driver.manage().window().maximize();
	}
	
	public static void getURL(String url){
		driver.get(url);
	}
	
	

}
