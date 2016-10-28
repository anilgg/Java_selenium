package smartview;
import static org.testng.AssertJUnit.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class export1 {
	private static WebDriver driver;
	private WebElement element;
	public static WritableSheet newexcel;
    public static WritableWorkbook copyfromexcel;
    

	@Test
	public void export() throws BiffException, IOException, RowsExceededException, WriteException {
		 Sheet s;
		FileInputStream fi = new FileInputStream("C:\\Users\\anigan\\Desktop\\sprint_overall\\export.xls");
	    Workbook W = Workbook.getWorkbook(fi);
	    s = W.getSheet(0);
	    try{           
		Util.setupDriver(Util.getPropertyValue("browserType"));
	    driver = Util.getDriver();
	    driver.get(Util.getPropertyValue("baseURL"));
	    Thread.sleep(1000);
	   driver.manage().window().maximize();
	    //WebDriver driver = new FirefoxDriver();
	    //driver.get("https://foundationia.alpha.live.qa.oceanwidebridge.com/Foundation.IAS.WebClient/Default.aspx");
	    
	    for(int row = 1;row <= s.getRows();row++)
	    {
	    	driver.findElement(By.xpath("//div/div[@class='dsh-tlbr-grp-lbl']")).click();
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//div[@id='dsh-X']/div[@class='fnd_ias_img26 dsh-tlbr-icn']")).click();
		    Thread.sleep(4000);
		    driver.findElement(By.xpath(".//*[@id='fnd_ias_cnfgPnl_1']/div[2]/div[1]/p/input")).clear();
		    String m1=s.getCell(0,row).getContents();
		    driver.findElement(By.xpath(".//*[@id='fnd_ias_cnfgPnl_1']/div[2]/div[1]/p/input")).sendKeys(m1);
	    	//for toggle
		    if(s.getCell(1, row).getContents()=="Yes")
	    	{
	    		driver.findElement(By.xpath(".//*[@id='dsh-tlbr']")).click();
	    	}
		    else
		    {
		    	driver.findElement(By.xpath(".//*[@id='dsh-tlbr']")).click();
		    }
		    //for orientation
		    if(s.getCell(2, row).getContents()=="Portrait")
		    {
		    	driver.findElement(By.xpath(".//*[@id='fnd_ias_cnfgPnl_1']/div[2]/div[3]/div[1]/label/input")).click();
		    }
		    else
		    {
		    	driver.findElement(By.xpath(".//*[@id='fnd_ias_cnfgPnl_1']/div[2]/div[3]/div[2]/label/input")).click();
		    }
		    //for paper size
		    if(s.getCell(3, row).getContents()!="Custom…")
		    {
		    	driver.findElement(By.xpath("//span/span[@class='k-input")).sendKeys(s.getCell(3,row).getContents());
		    }
		    else
		    {
		    	driver.findElement(By.xpath("//span/span[@class='k-input")).sendKeys(s.getCell(3,row).getContents());
		    	driver.findElement(By.xpath("//span/input[@class='k-formatted-value notFixedWidth k-input']")).sendKeys(s.getCell(4,row).getContents());
		    	driver.findElement(By.xpath("//span[2]/input[@class='k-formatted-value notFixedWidth k-input']")).sendKeys(s.getCell(5,row).getContents());
		    }
		    //for margin size
		    if(s.getCell(6, row).getContents()!="Custom…")
		    {
		    	driver.findElement(By.xpath("//span/span[@class='k-input")).sendKeys(s.getCell(6,row).getContents());
		    }
		    else
		    {
		    	driver.findElement(By.xpath("//span/span[@class='k-input")).sendKeys(s.getCell(6,row).getContents());
		    	driver.findElement(By.xpath(".//*[@id='fnd_ias_cnfgPnl_1']/div[2]/div[7]/table/tbody/tr[2]/td[1]/span/span/input[1]")).sendKeys(s.getCell(7,row).getContents());
		    	driver.findElement(By.xpath(".//*[@id='fnd_ias_cnfgPnl_1']/div[2]/div[7]/table/tbody/tr[2]/td[3]/span/span/input[1]")).sendKeys(s.getCell(8,row).getContents());
		    	driver.findElement(By.xpath(".//*[@id='fnd_ias_cnfgPnl_1']/div[2]/div[7]/table/tbody/tr[4]/td[1]/span/span/input[1]")).sendKeys(s.getCell(9,row).getContents());
		    	driver.findElement(By.xpath(".//*[@id='fnd_ias_cnfgPnl_1']/div[2]/div[7]/table/tbody/tr[4]/td[3]/span/span/input[1]")).sendKeys(s.getCell(10,row).getContents());
		    }
		    driver.findElement(By.xpath(".//*[@id='fnd_ias_cnfgPnl_1']/div[2]/div[8]/span[1]/span/input[1]")).sendKeys(s.getCell(11,row).getContents());
		    driver.findElement(By.name("Export")).click();
		      
	    }
	    Workbook workbook2 = Workbook.getWorkbook(new File("testdata.xls"));
	    copyfromexcel = Workbook.createWorkbook(new File("testdata1.xls"), workbook2);// creates a new excel sheet with the result
        newexcel = copyfromexcel.getSheet(0);	        
	}
	catch (AssertionError | Exception e) 
		{
		e.getMessage();
		}
	
		//ArrayList<String> list = new ArrayList<>();
		ArrayList<String> results = new ArrayList<>();

		WritableCellFormat cellFormat = new WritableCellFormat();
		WritableCellFormat cellFormat2 = new WritableCellFormat();
		WritableCellFormat cellFormat32 = new WritableCellFormat();
		//to check if the file is downloaded
	    try
	    {
	    File f = new File("C:\\Users\\anigan\\Downloads");
	    if(f.exists() && !f.isDirectory()) 
	    { 
	    	results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		}
			
	    }
	    catch (AssertionError | Exception e) 
	    {
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// Successfully land on Home page

		String ccc = "Title";

		Cell rrrt = s.findCell(ccc);//finds which excel sheet in workbook
		int ss1 = rrrt.getRow();
		int f3starts = ss1 + 1;
	
		int i = f3starts;
		String actual = null;
		String expected = null;
		int count = 0;			
		// int ii =f3starts ;
			//for (String s : list) {
				//Label labelA = new Label(1, ii, s);
				//newexcel.addCell(labelA);
				//ii++;
			//}
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
