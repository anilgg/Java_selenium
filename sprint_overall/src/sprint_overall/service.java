package sprint_overall;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class service {
	private static WebDriver driver;
	@Test
	public void view_rpv() throws Exception{
		//System.setProperty("webdriver.chrome.driver",  "C:\\chromedriver.exe");
		String PROXY = "127.0.209.148:57000";

		  Proxy proxy = new Proxy();
		  proxy.setSocksProxy(PROXY);
		  DesiredCapabilities cap = new DesiredCapabilities();
		  cap.setCapability(CapabilityType.PROXY, proxy);
		 
	    driver = new FirefoxDriver(cap);
	    driver.get("http://144.226.178.65/primary/");
	    driver.findElement(By.xpath("//*[@id='xml_8d4acf57-1891-4406-a3f9-ff283ebeea2b']/a")).click();
	    Thread.sleep(3000);
		 driver.findElement(By.id("msisdn")).sendKeys("6022149884");
		 driver.findElement(By.id("pin")).sendKeys("909090");
		// Thread.sleep(8000);
		 driver.findElement(By.id("nav-login")).click();
		 Thread.sleep(8000);
		 WebDriverWait wait = new WebDriverWait(driver, 60); 
		    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='desktop-nav-service-usage']")));
		
		 driver.findElement(By.xpath(".//*[@id='desktop-nav-service-usage']")).click();
		
		   
		 Thread.sleep(8000);
		 WebDriver augmentedDriver = new Augmenter().augment(driver);
			
			FileInputStream readingbillinfo = new FileInputStream("prototype.xls");
			 Workbook w = Workbook.getWorkbook(readingbillinfo);
			 Sheet sh = w.getSheet(0);
			 Workbook workbook1 = Workbook.getWorkbook(new File("prototype.xls"));
			    WritableWorkbook copy = Workbook.createWorkbook(new File("prototype.xls"), workbook1);
			    WritableSheet sheet2 = copy.getSheet(0); 
			   
			     ArrayList <String> list = new ArrayList<>();
			    ArrayList <String> results = new ArrayList<>();
			    
			               WritableCellFormat cellFormat = new WritableCellFormat();
						    WritableCellFormat cellFormat2 = new WritableCellFormat();
						    WritableCellFormat cellFormat32 = new WritableCellFormat();
						    String ccc = "Sanity 13  =  View RPV saved Information";

							 Cell rrrt = sh.findCell(ccc);
							 int ss1 = rrrt.getRow();
							 int f3starts = ss1+1;
							 
		 
		 
		//talk 
		 try{
			 list.add("");
			 
			 list.add("Successfully land on: "+ driver.findElement(By.xpath(".//*[@id='service_68a290af-6efa-4912-8705-77bb39c9b9cd']/div/div/div/div[3]/div[2]/div/ul/li[1]/a")).getText());
		 	results.add("Passed");
		 	cellFormat.setBackground(Colour.GREEN);
		 }


		 catch(Exception e){
			 list.add("OOPS! Can't get to the info page");
			 results.add("");
		 	results.add("Failed");
		 	cellFormat2.setBackground(Colour.RED);
		 }
		 	//see more talk offers
		 	 
			 try{
				 list.add("");
				 list.add("Successfully land on: "+ driver.findElement(By.xpath(".//*[@id='talk']/div/div[2]/div/div/h3/a")).getText());
				 results.add("NA");
			 	results.add("Passed");
			 	cellFormat.setBackground(Colour.GREEN);
			 }


			 catch(Exception e){
				 
			 list.add("");
				 list.add("OOPS! Can't get to the info page");
				 results.add("NA");
			 	results.add("Failed");
			 	cellFormat2.setBackground(Colour.RED);
			 }
			 //text
try{
				 
				 list.add("Successfully land on: "+ driver.findElement(By.xpath(".//*[@id='service_68a290af-6efa-4912-8705-77bb39c9b9cd']/div/div/div/div[3]/div[2]/div/ul/li[2]/a")).getText());
			 	results.add("Passed");
			 	cellFormat.setBackground(Colour.GREEN);
			 }


			 catch(Exception e){
				 list.add("OOPS! Can't get to the info page");
			 	results.add("Failed");
			 	cellFormat2.setBackground(Colour.RED);
			 }
			 	//data
			 	try{
			 	list.add("Successfully land on: "+ driver.findElement(By.xpath(".//*[@id='service_68a290af-6efa-4912-8705-77bb39c9b9cd']/div/div/div/div[3]/div[2]/div/ul/li[3]/a")).getText());
			 	results.add("Passed");
			 	cellFormat.setBackground(Colour.GREEN);
			 }


			 catch(Exception e){
				 list.add("OOPS! Can't get to the info page");
			 	results.add("Failed");
			 	cellFormat2.setBackground(Colour.RED);}
			 	//purchase
			 	try{
					 list.add("");
					 list.add("Successfully land on: "+ driver.findElement(By.xpath(".//*[@id='data']/div/div[3]/div/div[3]/a")).getText());
					
					 
				 	cellFormat.setBackground(Colour.GREEN);
				 }


				 catch(Exception e){
					 list.add("OOPS! Can't get to the info page");
					 results.add("");
				 	results.add("Failed");
				 	cellFormat2.setBackground(Colour.RED);}
				 	//purchase
				 	try{
						 
						 list.add("Successfully land on: "+ driver.findElement(By.xpath(".//*[@id='data']/div/div[4]/div/div[3]/a")).getText());
						
						 
					 	cellFormat.setBackground(Colour.GREEN);
					 }


					 catch(Exception e){
						 list.add("OOPS! Can't get to the info page");
					 	results.add("Failed");
					 	cellFormat2.setBackground(Colour.RED);}
				 	//other
				 	try{
						 
						 list.add("Successfully land on: "+ driver.findElement(By.xpath(".//*[@id='service_68a290af-6efa-4912-8705-77bb39c9b9cd']/div/div/div/div[3]/div[2]/div/ul/li[4]/a")).getText());
						
						 
					 	cellFormat.setBackground(Colour.GREEN);
					 }


					 catch(Exception e){
						 list.add("OOPS! Can't get to the info page");
					 	results.add("Failed");
					 	cellFormat2.setBackground(Colour.RED);}
				 	//see more
				 	try{
						 
						 list.add("Successfully land on: "+ driver.findElement(By.xpath(".//*[@id='service_68a290af-6efa-4912-8705-77bb39c9b9cd']/div/div/div/div[1]/div[2]/a/u")).getText());
					 
					 	cellFormat.setBackground(Colour.GREEN);
					 }


					 catch(Exception e){
						 list.add("OOPS! Can't get to the info page");
					 	results.add("Failed");
					 	cellFormat2.setBackground(Colour.RED);}
				 	try{
						 list.add("");
						 list.add("Successfully land on: "+ driver.findElement(By.xpath(".//*[@id='service_e0aff4a9-ff3b-4998-b37f-bc97fd8d3ecd']/div/div[2]/a[1]")).getText());
					 
					 	cellFormat.setBackground(Colour.GREEN);
					 }


					 catch(Exception e){
						 list.add("OOPS! Can't get to the info page");
						 results.add("");
					 	results.add("Failed");
					 	cellFormat2.setBackground(Colour.RED);}
				 	try{
						 
						 list.add("Successfully land on: "+ driver.findElement(By.xpath(".//*[@id='service_e0aff4a9-ff3b-4998-b37f-bc97fd8d3ecd']/div/div[2]/a[2]")).getText());
					 
					 	cellFormat.setBackground(Colour.GREEN);
					 }


					 catch(Exception e){
						 list.add("OOPS! Can't get to the info page");
						 
					 	results.add("Failed");
					 	cellFormat2.setBackground(Colour.RED);}
				 	try{
						 
						 list.add("Successfully land on: "+ driver.findElement(By.xpath(".//*[@id='service_e0aff4a9-ff3b-4998-b37f-bc97fd8d3ecd']/div/div[2]/a[3]")).getText());
					 
					 	cellFormat.setBackground(Colour.GREEN);
					 }


					 catch(Exception e){
						 list.add("OOPS! Can't get to the info page");
						 
					 	results.add("Failed");
					 	cellFormat2.setBackground(Colour.RED);}
				 	try{
						 
						 list.add("Successfully land on: "+ driver.findElement(By.xpath(".//*[@id='service_e0aff4a9-ff3b-4998-b37f-bc97fd8d3ecd']/div/div[2]/a[4]")).getText());
					 
					 	cellFormat.setBackground(Colour.GREEN);
					 }


					 catch(Exception e){
						 list.add("OOPS! Can't get to the info page");
						 
					 	results.add("Failed");
					 	cellFormat2.setBackground(Colour.RED);}
				 	
				 	
				 	
	}}
