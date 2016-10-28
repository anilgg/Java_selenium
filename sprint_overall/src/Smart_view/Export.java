package Smart_view;
import static org.testng.AssertJUnit.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import bsh.ParseException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Export extends ReadFromWorkbook {
	private static WebDriver driver;
	private WebElement element;

	@Test
	public void export() throws Exception {
	try{           
		Util.setupDriver(Util.getPropertyValue("browserType"));
	    driver = Util.getDriver();
	    driver.get(Util.getPropertyValue("baseURL"));
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//div/div[@class='dsh-tlbr-grp-lbl']")).click();
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//div[@id='dsh-X']/div[@class='fnd_ias_img26 dsh-tlbr-icn']")).click();
	    if(driver.findElement(By.xpath("//div/div[@id='fnd_ias_cnfgPnl_1']")).isDisplayed()){
	    	String type =driver.findElement(By.xpath("//span/span[@class='k-input']")).getText();
	    	if(type.equals("Custom...")){
	    		Assert.assertTrue(driver.findElement(By.xpath("//span/input[@class='k-formatted-value notFixedWidth k-input']")).isEnabled(),"width is not enabled");
	    		Assert.assertTrue(driver.findElement(By.xpath("//span[2]/input[@class='k-formatted-value notFixedWidth k-input']")).isEnabled(),"Height is not enabled");
	    	}else{
	    		Assert.assertFalse(driver.findElement(By.xpath("//span/input[@class='k-formatted-value notFixedWidth k-input']")).isEnabled(),"width is enabled");
	    		Assert.assertTrue(driver.findElement(By.xpath("//tr/td[2]/span[2]/input[@class='k-formatted-value notFixedWidth k-input']")).isEnabled(),"Height is not enabled");
	    	}
	    	
	    	//select custom and verify the height and width ares enabled
	    	driver.findElement(By.xpath("//span/span[@class='k-input']")).sendKeys("Custom...");
	    	String type1 =driver.findElement(By.xpath("//span/span[@class='k-input']")).getText();
	    	if(type1.equals("Custom...")){
	    		Assert.assertTrue(driver.findElement(By.xpath("//span/input[@class='k-formatted-value notFixedWidth k-input']")).isEnabled(),"width is not enabled");
	    		Assert.assertTrue(driver.findElement(By.xpath("//span[2]/input[@class='k-formatted-value notFixedWidth k-input']")).isEnabled(),"Height is not enabled");
	    	}else{
	    		Assert.assertFalse(driver.findElement(By.xpath("//span/input[@class='k-formatted-value notFixedWidth k-input']")).isEnabled(),"width is enabled");
	    		Assert.assertTrue(driver.findElement(By.xpath("//span[2]/input[@class='k-formatted-value notFixedWidth k-input']")).isEnabled(),"Height is not enabled");
	    	}   														
	    
	    }
		//WebDriver augmentedDriver = new Augmenter().augment(driver);
		}
	catch (AssertionError | Exception e) {
		e.getMessage();
		}
	
		ArrayList<String> list = new ArrayList<>();
		ArrayList<String> results = new ArrayList<>();

		WritableCellFormat cellFormat = new WritableCellFormat();
		WritableCellFormat cellFormat2 = new WritableCellFormat();
		WritableCellFormat cellFormat32 = new WritableCellFormat();

		// Successfully land on Home page

		String ccc = "Title";

		Cell rrrt = oldexcel.findCell(ccc);//finds which excel sheet in workbook
		int ss1 = rrrt.getRow();
		int f3starts = ss1 + 1;
	
		int i = f3starts;
		String actual = null;
		String expected = null;
		int count = 0;
			
		/*try {			
			expected = oldexcel.getCell(0, i).getContents();
			actual = driver.findElement(By.id("div-title-account-overdue-curing")).getText();
			assertEquals(expected.trim(), actual.trim());
			list.add(driver.findElement(By.id("div-title-account-overdue-curing")).getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} 
		catch (AssertionError | Exception e) {
			list.add(e.getMessage());
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		//  services interrupted message
		i++;
		*/						
		 int ii = f3starts;
			for (String s : list) {
				Label labelA = new Label(1, ii, s);
				newexcel.addCell(labelA);
				ii++;
			}
			int j= f3starts;
			for (String r : results)
			{
			if(r=="Passed"){
			Label labelR = new Label(2, j, r,cellFormat);
			newexcel.addCell(labelR);}
			else{
			if(r=="Failed"){
			Label labelRe = new Label(2, j, r,cellFormat2);
			newexcel.addCell(labelRe);
			count++;								
			}
			else{
			Label labelRRe = new Label(2, j, r,cellFormat32);
			newexcel.addCell(labelRRe);
			}
			}
			  j++;
			}
	driver.close();

	}}