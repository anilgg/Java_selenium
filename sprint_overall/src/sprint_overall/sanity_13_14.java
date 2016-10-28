package sprint_overall;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class sanity_13_14 {
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
		 driver.findElement(By.id("nav-login")).click();
		 WebDriverWait wait = new WebDriverWait(driver, 60); 
		    wait.until(ExpectedConditions.elementToBeClickable(By.id("desktop-nav-payments")));
		
		 driver.findElement(By.id("desktop-nav-payments")).click();
		
		   wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-manage-card")));
			
		 driver.findElement(By.id("nav-manage-card")).click();
		 Thread.sleep(8000);
		 WebDriver augmentedDriver = new Augmenter().augment(driver);
			
			FileInputStream readingbillinfo = new FileInputStream("prototype.xls");
			 Workbook w = Workbook.getWorkbook(readingbillinfo);
			 Sheet sh = w.getSheet(0);
			 Workbook workbook1 = Workbook.getWorkbook(new File("prototype.xls"));
			    WritableWorkbook copy = Workbook.createWorkbook(new File("prototypeNEW.xls"), workbook1);
			    WritableSheet sheet2 = copy.getSheet(0); 
			   
			     ArrayList <String> list = new ArrayList<>();
			    ArrayList <String> results = new ArrayList<>();
			    
			               WritableCellFormat cellFormat = new WritableCellFormat();
						    WritableCellFormat cellFormat2 = new WritableCellFormat();
						    WritableCellFormat cellFormat32 = new WritableCellFormat();
						    String ccc = "Sanity 13  =  View RPV saved Information";

							 Cell rrrt = sh.findCell(ccc);
							 int ss1 = rrrt.getRow();
							 int f3starts = ss1+2;
							 //landing on saved payment
						    try{
								 
								 list.add("Successfully land on: "+ driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[1]/div[1]/div/div[1]/div")).getText());
							 	results.add("Passed");
							 	cellFormat.setBackground(Colour.GREEN);
							 }


							 catch(Exception e){
								 list.add("OOPS! Can't get to the info page");
							 	results.add("Failed");
							 	cellFormat2.setBackground(Colour.RED);
							 }
						    //name on card
						    try{
								 
								 list.add(driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div[2]")).getText());
							 	results.add("Passed");
							 	cellFormat.setBackground(Colour.GREEN);
							 }


							 catch(Exception e){
								 list.add("OOPS! Can't get to the info page");
							 	results.add("Failed");
							 	cellFormat2.setBackground(Colour.RED);
							 }
						    //card nickname
						    try{
								 
								 list.add(driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[1]/div[1]/div/div[2]/div[3]/div[2]")).getText());
							 	results.add("Passed");
							 	cellFormat.setBackground(Colour.GREEN);
							 }


							 catch(Exception e){
								 list.add("OOPS! Can't get to the info page");
							 	results.add("Failed");
							 	cellFormat2.setBackground(Colour.RED);
							 }
						  //billing address1
						    try{
								 
								 list.add(driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[1]/div[1]/div/div[2]/div[4]/div[2]/div[1]/div")).getText());
							 	results.add("Passed");
							 	cellFormat.setBackground(Colour.GREEN);
							 }


							 catch(Exception e){
								 list.add("OOPS! Can't get to the info page");
							 	results.add("Failed");
							 	cellFormat2.setBackground(Colour.RED);
							 }
						  //billing address2 continue
						    try{
								 
								 list.add(driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[1]/div[1]/div/div[2]/div[4]/div[2]/div[2]/div")).getText());
								 list.add("Avaliable under forlder");
								 File shot = ((TakesScreenshot)augmentedDriver).
							  	         getScreenshotAs(OutputType.FILE);
							  	FileUtils.copyFile(shot, new File("Sprint_Project_Screenshots\\sanity_13_ViewRPV_success.png"));
							 	results.add("Passed");
							 	results.add("Passed");
							 	cellFormat.setBackground(Colour.GREEN);
							 }


							 catch(Exception e){
								 list.add("OOPS! Can't get to the info page");
								 list.add("Avaliable under forlder");
								 File shot = ((TakesScreenshot)augmentedDriver).
							  	         getScreenshotAs(OutputType.FILE);
							  	FileUtils.copyFile(shot, new File("Sprint_Project_Screenshots\\sanity_13_ViewRPV_success.png"));
							 	results.add("Failed");
							 	results.add("Failed");
							 	cellFormat2.setBackground(Colour.RED);
							 }
						    //"Sanity 14  = Edit RPV (With and Without Saved Address)
						    FileInputStream wer = new FileInputStream("Bookforchekout.xls");
							 Workbook wf = Workbook.getWorkbook(wer);
							 Sheet shh = wf.getSheet(0);
							 
						    driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[1]/div[1]/div/div[2]/div[5]/div[2]/a")).click();
						    Thread.sleep(5000);
						    list.add("");
						    list.add("");
						    results.add("NA");
							 results.add("NA");
						   
							
							
					//card number
						    String CAR_NUMBER= shh.getCell(0, 16).getContents();
							 driver.findElement(By.id("reg_card")).clear();	
							 driver.findElement(By.id("reg_card")).click();
							driver.findElement(By.id("reg_card")).sendKeys(CAR_NUMBER);
							

	                        try{
								 
								 list.add(CAR_NUMBER);
								 
								results.add("Passed");
							 	cellFormat.setBackground(Colour.GREEN);
							 }


							 catch(Exception e){
				
								 list.add("OOPS! Can'table to fetch the information");
								 
							 	results.add("Failed");
							 	cellFormat2.setBackground(Colour.RED);
							 }
	                        //month
	                        String month= shh.getCell(1, 16).getContents();
							 driver.findElement(By.id("expiryMonth")).clear();
							 driver.findElement(By.id("expiryMonth")).click();
							driver.findElement(By.id("expiryMonth")).sendKeys(month);
							try{
								 
								 list.add(month);
								results.add("Passed");
							 	cellFormat.setBackground(Colour.GREEN);
							 }


							 catch(Exception e){
								 list.add("OOPS! Can'table to fetch the information");
							 	results.add("Failed");
							 	cellFormat2.setBackground(Colour.RED);
							 }
							//yesr
							String year= shh.getCell(2, 16).getContents();
							 driver.findElement(By.id("expiryYear")).clear();
							 driver.findElement(By.id("expiryYear")).click();
							driver.findElement(By.id("expiryYear")).sendKeys(year);
							try{
								 
								 list.add(year);
								results.add("Passed");
							 	cellFormat.setBackground(Colour.GREEN);
							 }


							 catch(Exception e){
								 list.add("OOPS! Can'table to fetch the information");
							 	results.add("Failed");
							 	cellFormat2.setBackground(Colour.RED);
							 }
						    //cvv
							String cvv= shh.getCell(3, 16).getContents();
							 driver.findElement(By.id("cvv_card")).clear();
							driver.findElement(By.id("cvv_card")).sendKeys(cvv);
							try{
								 
								 list.add(cvv);
								results.add("Passed");
							 	cellFormat.setBackground(Colour.GREEN);
							 }


							 catch(Exception e){
								 list.add("OOPS! Can'table to fetch the information");
							 	results.add("Failed");
							 	cellFormat2.setBackground(Colour.RED);
							 }
							//name
							String name= shh.getCell(4, 16).getContents();
							 driver.findElement(By.id("cardHolderName")).clear();
							driver.findElement(By.id("cardHolderName")).sendKeys(name);
							try{
								 
								 list.add(name);
								results.add("Passed");
							 	cellFormat.setBackground(Colour.GREEN);
							 }


							 catch(Exception e){
								 list.add("OOPS! Can'table to fetch the information");
							 	results.add("Failed");
							 	cellFormat2.setBackground(Colour.RED);
							 }
							//address1
							String address1= shh.getCell(5, 16).getContents();
							 driver.findElement(By.id("addr1Line1")).clear();
							driver.findElement(By.id("addr1Line1")).sendKeys(address1);
							try{
								 
								 list.add(address1);
								results.add("Passed");
							 	cellFormat.setBackground(Colour.GREEN);
							 }


							 catch(Exception e){
								 list.add("OOPS! Can'table to fetch the information");
							 	results.add("Failed");
							 	cellFormat2.setBackground(Colour.RED);
							 }
							//addres2
							String address2= shh.getCell(6, 16).getContents();
							 driver.findElement(By.id("addr2")).clear();
							driver.findElement(By.id("addr2")).sendKeys(address2);
							try{
								 
								 list.add(address2);
								results.add("Passed");
							 	cellFormat.setBackground(Colour.GREEN);
							 }


							 catch(Exception e){
								 list.add("OOPS! Can'table to fetch the information");
							 	results.add("Failed");
							 	cellFormat2.setBackground(Colour.RED);
							 }
							
							//city
							String city= shh.getCell(7, 16).getContents();
							 driver.findElement(By.id("addr1City")).clear();
							driver.findElement(By.id("addr1City")).sendKeys(city);
							try{
								 
								 list.add(city);
								results.add("Passed");
							 	cellFormat.setBackground(Colour.GREEN);
							 }


							 catch(Exception e){
								 list.add("OOPS! Can'table to fetch the information");
							 	results.add("Failed");
							 	cellFormat2.setBackground(Colour.RED);
							 }
							//state
							String state= shh.getCell(8, 16).getContents();
							 driver.findElement(By.id("addr1State")).clear();
							driver.findElement(By.id("addr1State")).sendKeys(state);
							try{
								 
								 list.add(state);
								results.add("Passed");
							 	cellFormat.setBackground(Colour.GREEN);
							 }


							 catch(Exception e){
								 list.add("OOPS! Can'table to fetch the information");
							 	results.add("Failed");
							 	cellFormat2.setBackground(Colour.RED);
							 }
							//zip
							String zip= shh.getCell(9, 16).getContents();
							 driver.findElement(By.id("addr1Zip")).clear();
							driver.findElement(By.id("addr1Zip")).sendKeys(zip);
							try{
								 
								 list.add(zip);
								results.add("Passed");
							 	cellFormat.setBackground(Colour.GREEN);
							 }


							 catch(Exception e){
								 list.add("OOPS! Can'table to fetch the information");
							 	results.add("Failed");
							 	cellFormat2.setBackground(Colour.RED);
							 }
							
							//succss after click save card
							
							driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/form/div/div[2]/div/div/div[2]/div[2]/button")).click();
							Thread.sleep(8000);
							try{
								 
								 list.add("successfully edit card"+ driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div/div/div[2]/div[1]")).getText());
								 list.add("Avaliable under forlder");
								 File shot = ((TakesScreenshot)augmentedDriver).
							  	         getScreenshotAs(OutputType.FILE);
							  	FileUtils.copyFile(shot, new File("Sprint_Project_Screenshots\\sanity_14_edit_RPV_Success.png"));
							 	results.add("Passed");
							 	cellFormat.setBackground(Colour.GREEN);
							 }


							 catch(Exception e){
								 list.add("------------");
								 list.add("Avaliable under forlder");
								 File shot = ((TakesScreenshot)augmentedDriver).
							  	         getScreenshotAs(OutputType.FILE);
							  	FileUtils.copyFile(shot, new File("Sprint_Project_Screenshots\\sanity_14_edit_RPV_failed.png"));
							 	results.add("Failed");
							 	cellFormat2.setBackground(Colour.RED);
							 }
							
						    
						    int ii= f3starts;  
							for (String s : list)
							{
								Label labelA = new Label(1,ii,s);
								sheet2.addCell(labelA);
							     ii++;
							  //  listString += s + "\n";
							   
							    
							}

							 int j= f3starts;
								for (String r : results)
								{
									if(r=="Passed"){
									Label labelR = new Label(2, j, r,cellFormat);
									sheet2.addCell(labelR);}
									else{
										if(r=="NA"){
											Label labelRRe = new Label(2, j, r,cellFormat32);
											sheet2.addCell(labelRRe);
										}
										Label labelRe = new Label(2, j, r,cellFormat2);
										sheet2.addCell(labelRe);
									}
							         j++;
								  //  listString += s + "\n";
								   
								    
								}
								

								
							copy.write();
							copy.close();
}}
