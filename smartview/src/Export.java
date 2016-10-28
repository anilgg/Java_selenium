import org.junit.Test;
import org.openqa.selenium.By;
import org.testng.Assert;

import common.DriverManaget;
import selenium.core.BaseTest;
import selenium.core.TestData;


public class Export extends BaseTest{
	@Override
	public void preconditions() throws Exception{
		//System.out.println("***********************"+TestData.getTestData("TestData").get("Note"));
		
	} 
	@Test
	public void main() throws Exception{
		DriverManaget.browserInstantiate("firefox");
		DriverManaget.getURL("https://foundationia.alpha.live.qa.oceanwidebridge.com/Foundation.IAS.WebClient/Default.aspx");
	    Thread.sleep(1000);
	    //DriverManaget.driver.findElement(By.xpath(".//*[@id='menuButton_2']")).click();
	    
	    DriverManaget.driver.findElement(By.xpath("//div/div[@class='dsh-tlbr-grp-lbl']")).click();
	    Thread.sleep(5000);
	    DriverManaget.driver.findElement(By.xpath("//div[@id='dsh-X']/div[@class='fnd_ias_img26 dsh-tlbr-icn']")).click();
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    /*if(DriverManaget.driver.findElement(By.xpath("//div/div[@id='fnd_ias_cnfgPnl_1']")).isDisplayed()){
	    	String type =DriverManaget.driver.findElement(By.xpath("//span/span[@class='k-input']")).getText();
	    	if(type.equals("Custom...")){
	    		Assert.assertTrue(DriverManaget.driver.findElement(By.xpath("//span/input[@class='k-formatted-value notFixedWidth k-input']")).isEnabled(),"width is not enabled");
	    		Assert.assertTrue(DriverManaget.driver.findElement(By.xpath("//span[2]/input[@class='k-formatted-value notFixedWidth k-input']")).isEnabled(),"Height is not enabled");
	    	}else{
	    		Assert.assertFalse(DriverManaget.driver.findElement(By.xpath("//span/input[@class='k-formatted-value notFixedWidth k-input']")).isEnabled(),"width is enabled");
	    		Assert.assertTrue(DriverManaget.driver.findElement(By.xpath("//tr/td[2]/span[2]/input[@class='k-formatted-value notFixedWidth k-input']")).isEnabled(),"Height is not enabled");
	    	}
	    	
	    	//select custom and verify the height and width ares enabled
	    	DriverManaget.driver.findElement(By.xpath("//span/span[@class='k-input']")).sendKeys("Custom...");
	    	String type1 =DriverManaget.driver.findElement(By.xpath("//span/span[@class='k-input']")).getText();
	    	if(type1.equals("Custom...")){
	    		Assert.assertTrue(DriverManaget.driver.findElement(By.xpath("//span/input[@class='k-formatted-value notFixedWidth k-input']")).isEnabled(),"width is not enabled");
	    		Assert.assertTrue(DriverManaget.driver.findElement(By.xpath("//span[2]/input[@class='k-formatted-value notFixedWidth k-input']")).isEnabled(),"Height is not enabled");
	    	}else{
	    		Assert.assertFalse(DriverManaget.driver.findElement(By.xpath("//span/input[@class='k-formatted-value notFixedWidth k-input']")).isEnabled(),"width is enabled");
	    		Assert.assertTrue(DriverManaget.driver.findElement(By.xpath("//span[2]/input[@class='k-formatted-value notFixedWidth k-input']")).isEnabled(),"Height is not enabled");
	    	}
	    																	
	    
	    }*/
	    
	    
	}
	

}
