
package sprint_overall;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Storefrontpage {
	private static WebDriver driver;
	private String p = null;

	@Test
	public void initial() throws Exception {
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\chromedriver.exe");
		/*String PROXY = "127.0.209.148:57000";

		Proxy proxy = new Proxy();
		proxy.setSocksProxy(PROXY);
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.PROXY, proxy);

		driver = new FirefoxDriver(cap);
		driver.get("http://144.226.178.65/primary/");*/
		Util.setupDriver(Util.getPropertyValue("browserType"));       
        
        driver = Util.getDriver();
       //Maximize the browser
        driver.manage().window().maximize();
        // Get application URL
        driver.get(Util.getPropertyValue("baseURL"));

      
		driver.findElement(By.xpath("//*[@id='xml_8d4acf57-1891-4406-a3f9-ff283ebeea2b']/a")).click();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		
		Actions action = new Actions(driver);
		 WebElement shopelement = driver.findElement(By.xpath(".//*[@id='service_c45a0926-74ed-41ed-bbd5-0e19523723a7']/div/header/div/div/ul/li[1]/a"));      
	        action.moveToElement(shopelement).build().perform();      

	        WebDriverWait wait = new WebDriverWait(driver, 5);
	        WebElement allphones = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='service_c45a0926-74ed-41ed-bbd5-0e19523723a7']/div/header/div/div/ul/li[1]/ul/li[1]/a")));
	        allphones.click();

		WebDriver augmentedDriver = new Augmenter().augment(driver);

		FileInputStream readingbillinfo = new FileInputStream("prototype.xls");
		Workbook w = Workbook.getWorkbook(readingbillinfo);
		Sheet sh = w.getSheet(0);
		Workbook workbook1 = Workbook.getWorkbook(new File("prototype.xls"));
		WritableWorkbook copy = Workbook.createWorkbook(new File("prototype3.xls"), workbook1);
		WritableSheet sheet2 = copy.getSheet(0);
		ArrayList<String> list = new ArrayList<>();
		ArrayList<String> results = new ArrayList<>();

		WritableCellFormat cellFormat = new WritableCellFormat();
		WritableCellFormat cellFormat2 = new WritableCellFormat();
		WritableCellFormat cellFormat32 = new WritableCellFormat();

		

		String ccc = "Sanity 7 =Store Front Page";

		Cell rrrt = sh.findCell(ccc);
		int ss1 = rrrt.getRow();
		int f3starts = ss1 + 1;
		System.out.println("chance");
		//Phone 1
		
		int i = f3starts + 1;
		
		
		String q = sh.getCell(0, i).getContents();
		
		System.out.println(q);

		p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[1]/h3/a/span[2]")).getText();
try {
	list.add("");

Assert.assertEquals(q, p);

list.add( driver
		.findElement(By
				.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[1]/h3/a/span[2]")).getText());

		
		
			
			
					
			results.add("NA");
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {
			list.add("");
			list.add("OOPS! Can't get the phone name");
			results.add("NA");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
//Retail price
i++;


 q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("/html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[3]/div[3]/p[1]/span")).getText();
try {


Assert.assertEquals(q, p);

list.add( driver
.findElement(By
		.xpath("/html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[3]/div[3]/p[1]/span")).getText());



	
	
			
	
	results.add("Passed");
	cellFormat.setBackground(Colour.GREEN);
} catch (Exception e) {
	;
	list.add("OOPS! Can't get theRetail price");
	
	results.add("Failed");
	cellFormat2.setBackground(Colour.RED);
}
//Promotional discount
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("/html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[3]/div[3]/p[2]/span")).getText();
try {


Assert.assertEquals(q, p);

list.add( driver
.findElement(By
		.xpath("/html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[3]/div[3]/p[2]/span")).getText());



	
	
			
	
	results.add("Passed");
	cellFormat.setBackground(Colour.GREEN);
} catch (Exception e) {
	;
	list.add("OOPS! Can't get the Promotional discount");
	
	results.add("Failed");
	cellFormat2.setBackground(Colour.RED);
}
		// price
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[3]/div[3]/h2")).getText();
try {


Assert.assertEquals(q, p);
		
			list.add(driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[3]/div[3]/h2"))
					.getText());
			
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {
			
			list.add("OOPS! Can't get the Price");
			
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// phone2

i++;

i++;
 q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[3]/div[1]/h3/a/span[2]")).getText();
try {
list.add("");

Assert.assertEquals(q, p);

list.add( driver
.findElement(By
		.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[3]/div[1]/h3/a/span[2]")).getText());



	
	
			
	results.add("NA");
	results.add("Passed");
	cellFormat.setBackground(Colour.GREEN);
} catch (Exception e) {
	list.add("");
	list.add("OOPS! Can't get the phone name");
	results.add("NA");
	results.add("Failed");
	cellFormat2.setBackground(Colour.RED);
}
		
		// price
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[3]/div[3]/div[3]/h2")).getText();
try {


Assert.assertEquals(q, p);
		
		

			
			list.add(driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[3]/div[3]/div[3]/h2")).getText());
			
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {
			
			list.add("OOPS! Can't get the phone price");
			results.add("NA");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone3
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[4]/div[1]/h3/a/span[2]")).getText();
try {
list.add("");

Assert.assertEquals(q, p);

list.add( driver
.findElement(By
		.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[4]/div[1]/h3/a/span[2]")).getText());



	
	
			
	results.add("NA");
	results.add("Passed");
	cellFormat.setBackground(Colour.GREEN);
} catch (Exception e) {
	list.add("");
	list.add("OOPS! Can't get the phone name");
	results.add("NA");
	results.add("Failed");
	cellFormat2.setBackground(Colour.RED);
}
		
		// price
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[4]/div[3]/div[3]/h2")).getText();
try {


Assert.assertEquals(q, p);
		
		

			
			list.add(driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[4]/div[3]/div[3]/h2")).getText());
			
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {
			
			list.add("OOPS! Can't get the phone price");
			
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		
		// phone4
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[5]/div[1]/h3/a/span[2]")).getText();
try {
list.add("");

Assert.assertEquals(q, p);

list.add( driver
.findElement(By
		.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[5]/div[1]/h3/a/span[2]")).getText());



	
	
			
	results.add("NA");
	results.add("Passed");
	cellFormat.setBackground(Colour.GREEN);
} catch (Exception e) {
	list.add("");
	list.add("OOPS! Can't get the phone name");
	results.add("NA");
	results.add("Failed");
	cellFormat2.setBackground(Colour.RED);
}
	
	
		// price
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[5]/div[3]/div[3]/h2")).getText();
try {


Assert.assertEquals(q, p);
		
		

			
			list.add(driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[5]/div[3]/div[3]/h2")).getText());
			
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {
			
			list.add("OOPS! Can't get the phone price");
			
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		
		
		// phone5
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[6]/div[1]/h3/a/span[2]")).getText();
try {
list.add("");

Assert.assertEquals(q, p);

list.add( driver
.findElement(By
		.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[6]/div[1]/h3/a/span[2]")).getText());



	
	
			
	results.add("NA");
	results.add("Passed");
	cellFormat.setBackground(Colour.GREEN);
} catch (Exception e) {
	list.add("");
	list.add("OOPS! Can't get the phone name");
	results.add("NA");
	results.add("Failed");
	cellFormat2.setBackground(Colour.RED);
}
//Retail price
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[6]/div[3]/div[3]/p[1]")).getText();
try {


Assert.assertEquals(q, p);

list.add( driver
.findElement(By
		.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[6]/div[3]/div[3]/p[1]")).getText());



	
	
			
	
	results.add("Passed");
	cellFormat.setBackground(Colour.GREEN);
} catch (Exception e) {
	;
	list.add("OOPS! Can't get theRetail price");
	
	results.add("Failed");
	cellFormat2.setBackground(Colour.RED);
}
//Promotional discount
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[6]/div[3]/div[3]/p[2]")).getText();
try {


Assert.assertEquals(q, p);

list.add( driver
.findElement(By
		.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[6]/div[3]/div[3]/p[2]")).getText());



	
	
			
	
	results.add("Passed");
	cellFormat.setBackground(Colour.GREEN);
} catch (Exception e) {
	;
	list.add("OOPS! Can't get the Promotional discount");
	
	results.add("Failed");
	cellFormat2.setBackground(Colour.RED);
}
		
		// price
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[6]/div[3]/div[3]/h2")).getText();
try {


Assert.assertEquals(q, p);
		
		

			
			list.add(driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[6]/div[3]/div[3]/h2")).getText());
			
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {
			
			list.add("OOPS! Can't get the phone price");
			
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		
		
		// phone6
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[7]/div[1]/h3/a/span[2]")).getText();
try {
list.add("");

Assert.assertEquals(q, p);

list.add( driver
.findElement(By
		.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[7]/div[1]/h3/a/span[2]")).getText());



	
	
			
	results.add("NA");
	results.add("Passed");
	cellFormat.setBackground(Colour.GREEN);
} catch (Exception e) {
	list.add("");
	list.add("OOPS! Can't get the phone name");
	results.add("NA");
	results.add("Failed");
	cellFormat2.setBackground(Colour.RED);
}
		
		// price
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[3]/div[3]/p[2]")).getText();
try {


Assert.assertEquals(q, p);
		
		

			
			list.add(driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[3]/div[3]/p[2]")).getText());
			
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {
			
			list.add("OOPS! Can't get the phone price");
			
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		
		
		// phone7
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[8]/div[1]/h3/a/span[2]")).getText();
try {
list.add("");

Assert.assertEquals(q, p);

list.add( driver
.findElement(By
		.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[8]/div[1]/h3/a/span[2]")).getText());



	
	
			
	results.add("NA");
	results.add("Passed");
	cellFormat.setBackground(Colour.GREEN);
} catch (Exception e) {
	list.add("");
	list.add("OOPS! Can't get the phone name");
	results.add("NA");
	results.add("Failed");
	cellFormat2.setBackground(Colour.RED);
}
		// price
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[8]/div[3]/div[3]/h2")).getText();
try {


Assert.assertEquals(q, p);
		
		

			
			list.add(driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[8]/div[3]/div[3]/h2")).getText());
			
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {
			
			list.add("OOPS! Can't get the phone price");
			
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		
		// phone8
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[9]/div[1]/h3/a/span[2]")).getText();
try {
list.add("");

Assert.assertEquals(q, p);

list.add( driver
.findElement(By
		.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[9]/div[1]/h3/a/span[2]")).getText());



	
	
			
	results.add("NA");
	results.add("Passed");
	cellFormat.setBackground(Colour.GREEN);
} catch (Exception e) {
	list.add("");
	list.add("OOPS! Can't get the phone name");
	results.add("NA");
	results.add("Failed");
	cellFormat2.setBackground(Colour.RED);
}
		// price
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[9]/div[3]/div[3]/h2")).getText();
try {


Assert.assertEquals(q, p);
		
		

			
			list.add(driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[9]/div[3]/div[3]/h2")).getText());
			
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {
			
			list.add("OOPS! Can't get the phone price");
			
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		
		
			// phone9

i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[10]/div[1]/h3/a/span[2]")).getText();
try {
list.add("");

Assert.assertEquals(q, p);

list.add( driver
.findElement(By
		.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[10]/div[1]/h3/a/span[2]")).getText());



	
	
			
	results.add("NA");
	results.add("Passed");
	cellFormat.setBackground(Colour.GREEN);
} catch (Exception e) {
	list.add("");
	list.add("OOPS! Can't get the phone name");
	results.add("NA");
	results.add("Failed");
	cellFormat2.setBackground(Colour.RED);
}
		// price
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[10]/div[3]/div[3]/h2")).getText();
try {


Assert.assertEquals(q, p);
		
		

			
			list.add(driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[10]/div[3]/div[3]/h2")).getText());
			
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {
			
			list.add("OOPS! Can't get the phone price");
			
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		

	
		// phone10

i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[11]/div[1]/h3/a/span[2]")).getText();
try {
list.add("");

Assert.assertEquals(q, p);

list.add( driver
.findElement(By
		.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[11]/div[1]/h3/a/span[2]")).getText());



	
	
			
	results.add("NA");
	results.add("Passed");
	cellFormat.setBackground(Colour.GREEN);
} catch (Exception e) {
	list.add("");
	list.add("OOPS! Can't get the phone name");
	results.add("NA");
	results.add("Failed");
	cellFormat2.setBackground(Colour.RED);
}
		// price
i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[11]/div[3]/div[3]/h2")).getText();
try {


Assert.assertEquals(q, p);
		
		

			
			list.add(driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[11]/div[3]/div[3]/h2")).getText());
			
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {
			
			list.add("OOPS! Can't get the phone price");
			
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		

	

	// phone11

i++;


q = sh.getCell(0, i).getContents();

System.out.println(q);

p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[12]/div[1]/h3/a/span[2]")).getText();
try {
list.add("");

Assert.assertEquals(q, p);

list.add( driver
.findElement(By
		.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[12]/div[1]/h3/a/span[2]")).getText());



	
	
			
	results.add("NA");
	results.add("Passed");
	cellFormat.setBackground(Colour.GREEN);
} catch (Exception e) {
	list.add("");
	list.add("OOPS! Can't get the phone name");
	results.add("NA");
	results.add("Failed");
	cellFormat2.setBackground(Colour.RED);
}
		// price
i++;


q = sh.getCell(0, i).getContents();



p = driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[12]/div[3]/div[3]/h2")).getText();
try {


Assert.assertEquals(q, p);
		
		

			
			list.add(driver.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[11]/div[3]/div[3]/h2")).getText());
			
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {
			
			list.add("OOPS! Can't get the phone price");
			
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

	
	
	
int ii = f3starts;
for (String s : list) {
	Label labelA = new Label(1, ii, s);
	sheet2.addCell(labelA);
	ii++;
	// listString += s + "\n";

}

int j = f3starts;
for (String r : results) {
	if (r == "Passed") {
		Label labelR = new Label(2, j, r, cellFormat);
		sheet2.addCell(labelR);
	} else {
		if (r == "NA") {
			Label labelRRe = new Label(2, j, r, cellFormat32);
			sheet2.addCell(labelRRe);
		}
		Label labelRe = new Label(2, j, r, cellFormat2);
		sheet2.addCell(labelRe);
	}
	j++;
	// listString += s + "\n";

}

copy.write();
copy.close();
}}
