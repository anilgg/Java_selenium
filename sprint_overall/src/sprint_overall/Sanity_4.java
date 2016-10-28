

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

public class Sanity_4 {
	private static WebDriver driver;

	@Test
	public void initial() throws Exception {
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\chromedriver.exe");
		/*
		 * String PROXY = "127.0.209.148:57000";
		 * 
		 * Proxy proxy = new Proxy(); proxy.setSocksProxy(PROXY);
		 * DesiredCapabilities cap = new DesiredCapabilities();
		 * cap.setCapability(CapabilityType.PROXY, proxy);
		 */
		 driver = new FirefoxDriver();
		  driver.get("https://msdpsprintprepaid:11001/primary");
		 

		/*Util.setupDriver(Util.getPropertyValue("browserType"));

		driver = Util.getDriver();
		// Maximize the browser
		driver.manage().window().maximize();
		// Get application URL
		driver.get(Util.getPropertyValue("baseURL"));*/

		driver.findElement(By.xpath("//*[@id='xml_8d4acf57-1891-4406-a3f9-ff283ebeea2b']/a")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("msisdn")).sendKeys("2198093188");
		driver.findElement(By.id("pin")).sendKeys("123456");
		driver.findElement(By.id("nav-login")).click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//driver.findElement(By.id("desktop-nav-service-usage")).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement device = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("desktop-nav-service-usage")));
		device.click();
		Thread.sleep(4000);
		WebDriver augmentedDriver = new Augmenter().augment(driver);

		FileInputStream readingbillinfo = new FileInputStream("prototype.xls");
		Workbook w = Workbook.getWorkbook(readingbillinfo);
		Sheet sh = w.getSheet(0);
		Workbook workbook1 = Workbook.getWorkbook(new File("prototype.xls"));
		WritableWorkbook copy = Workbook.createWorkbook(new File("prototype_Usage.xls"), workbook1);
		WritableSheet sheet2 = copy.getSheet(0);
		ArrayList<String> list = new ArrayList<>();
		ArrayList<String> results = new ArrayList<>();

		WritableCellFormat cellFormat = new WritableCellFormat();
		WritableCellFormat cellFormat2 = new WritableCellFormat();
		WritableCellFormat cellFormat32 = new WritableCellFormat();

		// Successfully land on registered a Service and usage

		String ccc = "sanity 4 =Service And Usage";

		Cell rrrt = sh.findCell(ccc);
		int ss1 = rrrt.getRow();
		int f3starts = ss1 + 1;
		String p = null;
		String q = null;

		int i = f3starts;

		// Usage
		q = sh.getCell(0, i).getContents();

		p = driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[1]/div/div/div/div/div[1]/div[1]/p"))
				.getText();
				try {
			Assert.assertEquals(q, p);

			list.add(driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[1]/div/div/div/div/div[1]/div[1]/p"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} 
				catch (Exception e) {

			list.add("OOPS! Can't get to the talk page");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		System.out.println("hi");
		// Talk

		i++;
		i++;

		q = sh.getCell(0, i).getContents();

		p = driver
				.findElement(
						By.xpath("/html/body/div/div[4]/div[1]/div[1]/div/div/div/div/div[3]/div[2]/div/ul/li[1]/a"))
				.getText();
		list.add("");
		results.add("NA");

		try {
			Assert.assertEquals(q, p);

			list.add(driver
					.findElement(By
							.xpath("/html/body/div/div[4]/div[1]/div[1]/div/div/div/div/div[3]/div[2]/div/ul/li[1]/a"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {

			list.add("OOPS! Can't get to the talk page");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		System.out.println("chance");

		// Text

		i++;
		i++;

		q = sh.getCell(0, i).getContents();

		p = driver
				.findElement(
						By.xpath("/html/body/div/div[4]/div[1]/div[1]/div/div/div/div/div[3]/div[2]/div/ul/li[2]/a"))
				.getText();
		list.add("");
		results.add("NA");

		try {
			Assert.assertEquals(q, p);

			list.add(driver
					.findElement(By
							.xpath("/html/body/div/div[4]/div[1]/div[1]/div/div/div/div/div[3]/div[2]/div/ul/li[2]/a"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {

			list.add("OOPS! Can't get to the text page");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// Data

		i++;

		q = sh.getCell(0, i).getContents();

		p = driver
				.findElement(
						By.xpath("/html/body/div/div[4]/div[1]/div[1]/div/div/div/div/div[3]/div[2]/div/ul/li[3]/a"))
				.getText();

		try {
			Assert.assertEquals(q, p);
			list.add(driver
					.findElement(By
							.xpath("/html/body/div/div[4]/div[1]/div[1]/div/div/div/div/div[3]/div[2]/div/ul/li[3]/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {
			list.add("OOPS! Can't get to the data page");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// Others

		i++;
		q = sh.getCell(0, i).getContents();

		p = driver
				.findElement(
						By.xpath("/html/body/div/div[4]/div[1]/div[1]/div/div/div/div/div[3]/div[2]/div/ul/li[4]/a"))
				.getText();

		try {
			Assert.assertEquals(q, p);
			list.add(driver
					.findElement(By
							.xpath("/html/body/div/div[4]/div[1]/div[1]/div/div/div/div/div[3]/div[2]/div/ul/li[4]/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {
			list.add("OOPS! Can't get to the data page");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// Change my plan or add-ons
		i++;
		i++;
		q = sh.getCell(0, i).getContents();
		p = driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[2]/div[1]/div/div[2]/a[1]")).getText();
		list.add("");
		results.add("NA");
		try {
			Assert.assertEquals(q, p);
			list.add(driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[2]/div[1]/div/div[2]/a[1]")).getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		}
		catch (Exception e) {
			list.add("OOPS! Can't get to the change my plan or add-ons page");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// Talk history
		i++;
		q = sh.getCell(0, i).getContents();
		p = driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[2]/div/div/div[2]/a[2]")).getText();
		try {
			Assert.assertEquals(q, p);
			list.add(driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[2]/div/div/div[2]/a[2]")).getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		}
		catch (Exception e) {
			list.add("OOPS! Can't get to the talk history page");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// Text history
		i++;
		q = sh.getCell(0, i).getContents();
		p = driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[2]/div/div/div[2]/a[3]")).getText();
		try {
			Assert.assertEquals(q, p);
			list.add(driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[2]/div/div/div[2]/a[3]")).getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		}

		catch (Exception e) {

			list.add("OOPS! Can't get to the text history page");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// Reset my plan

		i++;

		q = sh.getCell(0, i).getContents();

		p = driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[2]/div/div/div[2]/a[4]")).getText();
		try {
			Assert.assertEquals(q, p);
			list.add(driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[2]/div/div/div[2]/a[4]")).getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		}

		catch (Exception e) {

			list.add("OOPS! Can't get to the reset my plan page");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// See more Talk Offers

		i++;

		q = sh.getCell(0, i).getContents();

		p = driver
				.findElement(By
						.xpath("/html/body/div/div[4]/div[1]/div[1]/div/div/div/div/div[3]/div[1]/div[1]/div/div[2]/div/div"))
				.getText();
		try {
			Assert.assertEquals(q, p);
			list.add(driver
					.findElement(By
							.xpath("/html/body/div/div[4]/div[1]/div[1]/div/div/div/div/div[3]/div[1]/div[1]/div/div[2]/div/div"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (Exception e) {

			list.add("OOPS! Can't get to the see more talk offers page");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// See more

		i++;

		q = sh.getCell(0, i).getContents();

		p = driver.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[1]/div/div/div/div/div[1]/div[2]/a/u"))
				.getText();
		try {
			Assert.assertEquals(q, p);
			list.add(driver
					.findElement(By.xpath("/html/body/div/div[4]/div[1]/div[1]/div/div/div/div/div[1]/div[2]/a/u"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		}

		catch (Exception e) {

			list.add("OOPS! Can't get to the other page");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// Plan Talk
		i++;

		q = sh.getCell(0, i).getContents();

		System.out.println(q);

		p = driver
				.findElement(By
						.xpath("/html/body/div/div[4]/div[1]/div[1]/div/div/div/div/div[3]/div[1]/div[1]/div/div[1]/div/div[1]"))
				.getText();

		try {
			Assert.assertEquals(q, p);

			list.add(driver
					.findElement(By
							.xpath("/html/body/div/div[4]/div[1]/div[1]/div/div/div/div/div[3]/div[1]/div[1]/div/div[1]/div/div[1]"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		}

		catch (Exception e) {

			list.add("OOPS! Can't get to the other page");

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

		/*int j = f3starts;
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
			j++;*/
			// listString += s + "\n";
		 int j= f3starts;
		 for (String r : results)
		 {
		 if(r=="Passed"){
		 Label labelR = new Label(2, j, r,cellFormat);
		 sheet2.addCell(labelR);}
		 else{
		 if(r=="Failed"){
		 Label labelRe = new Label(2, j, r,cellFormat2);
		 sheet2.addCell(labelRe);
		 									
		 }
		 else{
		 Label labelRRe = new Label(2, j, r,cellFormat32);
		 sheet2.addCell(labelRRe);
		 }
		 }
		   j++;

		}

		copy.write();
		copy.close();

	}
}