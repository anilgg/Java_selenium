
package sprint_overall;

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

public class Sanity_24Boost {
	private static WebDriver driver;

	@Test
	public void Store_Front_Page() throws Exception {

		/*
		 * String PROXY = "127.0.209.148:57000";
		 * 
		 * Proxy proxy = new Proxy(); proxy.setSocksProxy(PROXY);
		 * DesiredCapabilities cap = new DesiredCapabilities();
		 * cap.setCapability(CapabilityType.PROXY, proxy);
		 */
		Util.setupDriver(Util.getPropertyValue("browserType"));
		driver = Util.getDriver();
		driver.get(Util.getPropertyValue("shopUrl"));
		driver.manage().window().maximize();
		Thread.sleep(5000);
		WebElement shopelement = driver.findElement(By.xpath("/html/body/header/div/div[2]/ul[2]/li[1]/a"));// shop
																											// clicked
																											// hover
		Actions action = new Actions(driver);
		action.moveToElement(shopelement).perform();
		Thread.sleep(2000);
		WebElement allphones = driver.findElement(By.xpath("/html/body/header/div/div[2]/ul[2]/li[1]/ul/li[1]/a"));// all
																													// phones
																													// clicked
		action.moveToElement(allphones);
		action.click();
		action.perform();

		WebDriver augmentedDriver = new Augmenter().augment(driver);

		FileInputStream readingbillinfo = new FileInputStream("prototype.xls");
		Workbook w = Workbook.getWorkbook(readingbillinfo);
		Sheet sh = w.getSheet(0);
		Workbook workbook1 = Workbook.getWorkbook(new File("prototype.xls"));
		WritableWorkbook copy = Workbook.createWorkbook(new File("prototype99.xls"), workbook1);
		WritableSheet sheet2 = copy.getSheet(0);
		ArrayList<String> list = new ArrayList<>();
		ArrayList<String> results = new ArrayList<>();

		WritableCellFormat cellFormat = new WritableCellFormat();
		WritableCellFormat cellFormat2 = new WritableCellFormat();
		WritableCellFormat cellFormat32 = new WritableCellFormat();

		String ccc = "Sanity 24 =Shop";

		Cell rrrt = sh.findCell(ccc);
		int ss1 = rrrt.getRow();
		int f3starts = ss1 + 1;
		String q = null;
		String p = null;
		// Phone 1

		int i = f3starts;
		i++;
		list.add("");
		results.add("NA");

		try {
			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[2]/div[2]/h2/a")).getText();
			System.out.println(p);
			System.out.println(q);
			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[2]/div[2]/h2/a"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[2]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();

			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[2]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the Price");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 1
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[2]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the Phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone2

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[3]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[3]/div[2]/h2/a"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone name");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[3]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[3]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 2
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[3]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone3

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[4]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[4]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[4]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[4]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 3
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[4]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// phone4

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();
			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[5]/div[2]/h2/a")).getText();
			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[5]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[5]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[5]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone price");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 4
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[4]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// phone5

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();
			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[6]/div[2]/h2/a")).getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[6]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[6]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[6]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 5
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[6]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// phone6

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();
			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[7]/div[2]/h2/a")).getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[7]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[7]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[7]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone price");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// phone Src 6
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[8]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// phone7
		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[8]/div[2]/h2/a]")).getText();

			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[8]/div[2]/h2/a"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[8]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();

			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[8]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone price");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 7
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[9]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// phone8

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[9]/div[2]/h2/a")).getText();
			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[9]/div[2]/h2/a"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[9]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[9]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 8
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[9]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// phone9

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[10]/div[2]/h2/a")).getText();
			System.out.println(p);
			System.out.println(q);
			assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[10]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[10]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[10]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 9
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[10]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// phone10

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[11]/div[2]/h2/a")).getText();
			System.out.println(p);
			System.out.println(q);
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[11]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[11]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[11]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 10
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[10]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone11

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[12]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[12]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[12]/div[2]/div[3]/div[3]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[12]/div[2]/div[3]/div[3]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 11
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[12]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone12

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[13]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[13]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[13]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[13]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 12
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[13]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone13

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[14]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[14]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[14]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[14]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 13
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[14]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone14

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[15]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[15]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[15]/div[2]/h2/a")).getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[15]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 14
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[15]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone15

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[16]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[16]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[16]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[16]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 15
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[16]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone16

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[17]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[17]/div[2]/h2/aa"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[17]/div[2]/h2/a")).getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[17]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 16
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[17]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone17

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[18]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[18]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[18]/div[2]/div[3]/div[3]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[18]/div[2]/div[3]/div[3]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 17
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[18]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone18

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[19]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[19]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[19]/div[2]/div[3]/div[3]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[19]/div[2]/div[3]/div[3]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 18
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[19]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone19

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[20]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[20]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[20]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[20]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 19
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[19]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone 20

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[21]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[21]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[21]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[21]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 20
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[21]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone 21

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[22]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[22]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[22]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[22]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 21
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[22]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone 22

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[23]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[23]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[23]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[23]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 22
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[23]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// phone 23

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[24]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[24]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[24]/div[2]/h2/a")).getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[24]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 23
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[24]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone 24

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[25]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[25]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[25]/div[2]/div[3]/div[2]/div[2]/span")).getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[25]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 24
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[25]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone 25

		i++;
		i++;
		list.add("");
		results.add("NA");
		try {

			q = sh.getCell(0, i).getContents();

			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[26]/div[2]/h2/a")).getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[26]/div[2]/h2/a"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone name");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[26]/div[2]/div[3]/div[2]/div[2]/span")).getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[26]/div[2]/div[3]/div[2]/div[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add("OOPS! Can't get the phone price");
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone Src 25
		try {
			q = sh.getCell(0, i).getContents();

			list.add(driver
					.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[26]/div[2]/div[2]/a[2]/img"))
					.getAttribute("src"));
			results.add("passed");

		} catch (AssertionError | Exception e) {

			list.add("OOPS! Can't get the phone src");

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// phone 26

				i++;
				i++;
				list.add("");
				results.add("NA");
				try {

					q = sh.getCell(0, i).getContents();

					p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[27]/div[2]/h2/a")).getText();

					Assert.assertEquals(q.trim(), p.trim());
					list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[27]/div[2]/h2/a"))
							.getText());
					results.add("Passed");
					cellFormat.setBackground(Colour.GREEN);
				} catch (AssertionError | Exception e) {
					list.add("OOPS! Can't get the phone name");
					results.add("Failed");
					cellFormat2.setBackground(Colour.RED);
				}
				// price
				i++;
				try {

					q = sh.getCell(0, i).getContents();
					p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[27]/div[2]/div[3]/div[2]/div[2]/span")).getText();
					Assert.assertEquals(q.trim(), p.trim());
					list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[27]/div[2]/div[3]/div[2]/div[2]/span"))
							.getText());
					results.add("Passed");
					cellFormat.setBackground(Colour.GREEN);
				} catch (AssertionError | Exception e) {
					list.add("OOPS! Can't get the phone price");
					results.add("Failed");
					cellFormat2.setBackground(Colour.RED);
				}
				// phone Src 26
				try {
					q = sh.getCell(0, i).getContents();

					list.add(driver
							.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[27]/div[2]/div[2]/a[2]/img"))
							.getAttribute("src"));
					results.add("passed");

				} catch (AssertionError | Exception e) {

					list.add("OOPS! Can't get the phone src");

					results.add("Failed");
					cellFormat2.setBackground(Colour.RED);
				}
				// phone 27

				i++;
				i++;
				list.add("");
				results.add("NA");
				try {

					q = sh.getCell(0, i).getContents();

					p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[28]/div[2]/h2/a")).getText();

					Assert.assertEquals(q.trim(), p.trim());
					list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[28]/div[2]/h2/a"))
							.getText());
					results.add("Passed");
					cellFormat.setBackground(Colour.GREEN);
				} catch (AssertionError | Exception e) {
					list.add("OOPS! Can't get the phone name");
					results.add("Failed");
					cellFormat2.setBackground(Colour.RED);
				}
				// price
				i++;
				try {

					q = sh.getCell(0, i).getContents();
					p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[28]/div[2]/div[3]/div[2]/div[2]/span")).getText();
					Assert.assertEquals(q.trim(), p.trim());
					list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[28]/div[2]/div[3]/div[2]/div[2]/span"))
							.getText());
					results.add("Passed");
					cellFormat.setBackground(Colour.GREEN);
				} catch (AssertionError | Exception e) {
					list.add("OOPS! Can't get the phone price");
					results.add("Failed");
					cellFormat2.setBackground(Colour.RED);
				}
				// phone Src 27
				try {
					q = sh.getCell(0, i).getContents();

					list.add(driver
							.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[28]/div[2]/div[2]/a[2]/img"))
							.getAttribute("src"));
					results.add("passed");

				} catch (AssertionError | Exception e) {

					list.add("OOPS! Can't get the phone src");

					results.add("Failed");
					cellFormat2.setBackground(Colour.RED);
				}
		
				// phone 28

				i++;
				i++;
				list.add("");
				results.add("NA");
				try {

					q = sh.getCell(0, i).getContents();

					p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[29]/div[2]/h2/a")).getText();

					Assert.assertEquals(q.trim(), p.trim());
					list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[29]/div[2]/h2/a"))
							.getText());
					results.add("Passed");
					cellFormat.setBackground(Colour.GREEN);
				} catch (AssertionError | Exception e) {
					list.add("OOPS! Can't get the phone name");
					results.add("Failed");
					cellFormat2.setBackground(Colour.RED);
				}
				// price
				i++;
				try {

					q = sh.getCell(0, i).getContents();
					p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[29]/div[2]/div[3]/div[2]/div[2]/span")).getText();
					Assert.assertEquals(q.trim(), p.trim());
					list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[29]/div[2]/div[3]/div[2]/div[2]/span"))
							.getText());
					results.add("Passed");
					cellFormat.setBackground(Colour.GREEN);
				} catch (AssertionError | Exception e) {
					list.add("OOPS! Can't get the phone price");
					results.add("Failed");
					cellFormat2.setBackground(Colour.RED);
				}
				// phone Src 28
				try {
					q = sh.getCell(0, i).getContents();

					list.add(driver
							.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[29]/div[2]/div[2]/a[2]/img"))
							.getAttribute("src"));
					results.add("passed");

				} catch (AssertionError | Exception e) {

					list.add("OOPS! Can't get the phone src");

					results.add("Failed");
					cellFormat2.setBackground(Colour.RED);
				}
		
		
				// phone 29

				i++;
				i++;
				list.add("");
				results.add("NA");
				try {

					q = sh.getCell(0, i).getContents();

					p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[30]/div[2]/h2/a")).getText();

					Assert.assertEquals(q.trim(), p.trim());
					list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[30]/div[2]/h2/a"))
							.getText());
					results.add("Passed");
					cellFormat.setBackground(Colour.GREEN);
				} catch (AssertionError | Exception e) {
					list.add("OOPS! Can't get the phone name");
					results.add("Failed");
					cellFormat2.setBackground(Colour.RED);
				}
				// price
				i++;
				try {

					q = sh.getCell(0, i).getContents();
					p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[30]/div[2]/div[3]/div[3]/div[2]/span")).getText();
					Assert.assertEquals(q.trim(), p.trim());
					list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[30]/div[2]/div[3]/div[3]/div[2]/span"))
							.getText());
					results.add("Passed");
					cellFormat.setBackground(Colour.GREEN);
				} catch (AssertionError | Exception e) {
					list.add("OOPS! Can't get the phone price");
					results.add("Failed");
					cellFormat2.setBackground(Colour.RED);
				}
				// phone Src 29
				try {
					q = sh.getCell(0, i).getContents();

					list.add(driver
							.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[30]/div[2]/div[2]/a[2]/img"))
							.getAttribute("src"));
					results.add("passed");

				} catch (AssertionError | Exception e) {

					list.add("OOPS! Can't get the phone src");

					results.add("Failed");
					cellFormat2.setBackground(Colour.RED);
				}
				// phone 30

				i++;
				i++;
				list.add("");
				results.add("NA");
				try {

					q = sh.getCell(0, i).getContents();

					p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[31]/div[2]/h2/a")).getText();

					Assert.assertEquals(q.trim(), p.trim());
					list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[31]/div[2]/h2/a"))
							.getText());
					results.add("Passed");
					cellFormat.setBackground(Colour.GREEN);
				} catch (AssertionError | Exception e) {
					list.add("OOPS! Can't get the phone name");
					results.add("Failed");
					cellFormat2.setBackground(Colour.RED);
				}
				// price
				i++;
				try {

					q = sh.getCell(0, i).getContents();
					p = driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[31]/div[2]/div[3]/div[2]/div[2]/span")).getText();
					Assert.assertEquals(q.trim(), p.trim());
					list.add(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[31]/div[2]/div[3]/div[2]/div[2]/span"))
							.getText());
					results.add("Passed");
					cellFormat.setBackground(Colour.GREEN);
				} catch (AssertionError | Exception e) {
					list.add("OOPS! Can't get the phone price");
					results.add("Failed");
					cellFormat2.setBackground(Colour.RED);
				}
				// phone Src 30
				try {
					q = sh.getCell(0, i).getContents();

					list.add(driver
							.findElement(By.xpath("html/body/div[1]/div/div[5]/div[2]/form/ul/li[31]/div[2]/div[2]/a[2]/img"))
							.getAttribute("src"));
					results.add("passed");

				} catch (AssertionError | Exception e) {

					list.add("OOPS! Can't get the phone src");

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
				if (r == "Failed") {
					Label labelRe = new Label(2, j, r, cellFormat2);
					sheet2.addCell(labelRe);

				} else {
					Label labelRRe = new Label(2, j, r, cellFormat32);
					sheet2.addCell(labelRRe);
				}
			}
			j++;
			// listString += s + "\n";

		}

		// copy.write();
		// copy.close();

	}
}
