package smartview.testscripts.notes.note;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import selenium.core.*;
import common.DriverManaget;

public class Notes extends BaseTest
{ 
	static boolean flag = false;
	
	@Override
	public void preconditions() throws Exception{
		System.out.println("***********************"+TestData.getTestData("TestData").get("Note"));
		
	} 
	@Test
	public void main() throws Exception{
		DriverManaget.browserInstantiate("firefox");
		DriverManaget.getURL("https://foundationia.alpha.live.qa.oceanwidebridge.com/Foundation.IAS.WebClient/Default.aspx");
        Thread.sleep(1000);
        DriverManaget.driver.findElement(By.xpath(".//*[@id='menuButton_2']")).click();
        //driver.findElement(By.xpath(""))
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverManaget.driver.findElement(By.xpath(".//*[@id='tb-N_2']/div")).click();
       if(DriverManaget.driver.findElement(By.id("fnd_ias_cnfgPnl_1")).isDisplayed())
       {
    	   System.out.println("Notes panel is displayed");
       }else{
    	   System.out.println("Notes panel is not displayed");
       }
       if(DriverManaget.driver.findElement(By.name("noteInput")).isEnabled()){
    	   System.out.println("Notes text area is enabled");
       }else{
    	   System.out.println("Notes text area is not enabled");
       }
       DriverManaget.driver.findElement(By.name("noteInput")).sendKeys(TestData.getTestData().get("Note"));
       DriverManaget.driver.findElement(By.name("Create")).click();
       List<WebElement> ws = DriverManaget.driver.findElements(By.xpath("//div[@name='Content']/div[@class='NoteMessage IsAuthor']"));
       //div[@name='Content']/div/div[@class='NoteTitle']
       for(WebElement e : ws){
    	  // System.out.println(";;;;;"+e.findElement(By.className("NoteTitle")).getText());
    	   //System.out.println(";;;;;"+e.getText());
    	  if(e.getText().contains(TestData.getTestData().get("Note")))
    	  {
    		  flag=true;
    		  System.out.println(TestData.getTestData().get("Note")+" is exist");
    	  }else{
   		  flag =false;
    		  System.out.println(TestData.getTestData().get("Note") +" is does not exist");
    	  }
		
       }
	}
	
	@Override
	public void cleanup(){
		
	}
	
	//public static void main(String[] args) throws Exception {
		//String a=Config.getCurrentClassName();
		//Config.setCurrentClassName("H:\\Workspace\\smartview\\src\\com\\note\\Notes");
		
		// TODO Auto-generated method stub
	//DriverManaget.browserInstantiate("firefox");
	//DriverManaget.getURL("https://foundationia.alpha.live.qa.oceanwidebridge.com/Foundation.IAS.WebClient/Default.aspx");
	//Thread.sleep(1000);
      //  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.findElement(By.xpath(".//*[@id='menuButton_2']")).click();
//        //driver.findElement(By.xpath(""))
//       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.findElement(By.xpath(".//*[@id='tb-N_2']/div")).click();
//       if(driver.findElement(By.id("fnd_ias_cnfgPnl_1")).isDisplayed())
//       {
//    	   System.out.println("Notes panel is displayed");
//       }else{
//    	   System.out.println("Notes panel is not displayed");
//       }
//       if(driver.findElement(By.name("noteInput")).isEnabled()){
//    	   System.out.println("Notes text area is enabled");
//       }else{
//    	   System.out.println("Notes text area is not enabled");
//       }
//       driver.findElement(By.name("noteInput")).sendKeys(TestData.getTestData().get("Note"));
//       driver.findElement(By.name("Create")).click();
//       List<WebElement> ws = driver.findElements(By.xpath("//div[@name='Content']/div[@class='NoteMessage IsAuthor']"));
//       //div[@name='Content']/div/div[@class='NoteTitle']
//       for(WebElement e : ws){
//    	  // System.out.println(";;;;;"+e.findElement(By.className("NoteTitle")).getText());
//    	   //System.out.println(";;;;;"+e.getText());
//    	  if(e.getText().contains(TestData.getTestData().get("Note")))
//    	  {
//    		  flag=true;
//    		  System.out.println(TestData.getTestData().get("Note")+" is exist");
//    	  }else{
//    		  flag =false;
//    		  System.out.println(TestData.getTestData().get("Note") +" is does not exist");
//    	  }
//       }
  
	}


