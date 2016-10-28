
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

public class sanity_24_shop extends filesystem_handles{
	private static WebDriver driver;

	@Test
	public void Store_Front_Page() throws Exception {

	try{
		
	
		Util.setupDriver(Util.getPropertyValue("browserType"));
	    driver = Util.getDriver();
    driver.get(Util.getPropertyValue("shopUrl"));
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
		
	}
	catch (AssertionError | Exception e) {

		e.getMessage();
		}
		
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
		int count = 0;
		// Phone 1

		int i = f3starts;
		i++;
		list.add("");
		results.add("NA");

		try {
			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[1]/h3/a/span[2]"))
					.getText();
			System.out.println(p);
			System.out.println(q);
			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[1]/h3/a/span[2]"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add(e.getMessage());
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[3]/div[3]/h2"))
					.getText();

			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[3]/div[3]/h2"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add(e.getMessage());

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

			p = driver
					.findElement(
							By.xpath("/html/body/div[3]/div[3]/section/section[3]/div[3]/div[3]/div[1]/h3/a/span[2]"))
					.getText();

			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver
					.findElement(
							By.xpath("/html/body/div[3]/div[3]/section/section[3]/div[3]/div[3]/div[1]/h3/a/span[2]"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add(e.getMessage());

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[3]/div[3]/div[3]/h2"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[3]/div[3]/div[3]/h2"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add(e.getMessage());
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

			p = driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[4]/div[1]/h3/a/span[2]"))
					.getText();

			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[4]/div[1]/h3/a/span[2]"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add(e.getMessage());
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[4]/div[3]/div[3]/h2"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[4]/div[3]/div[3]/h2"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add(e.getMessage());
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
			p = driver
					.findElement(
							By.xpath("/html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[1]/h3/a/span[2]"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver
					.findElement(
							By.xpath("/html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[1]/h3/a/span[2]"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add(e.getMessage());
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[3]/div[3]/h2"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[2]/div[3]/div[3]/h2"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add(e.getMessage());

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
			p = driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[6]/div[1]/h3/a/span[2]"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[6]/div[1]/h3/a/span[2]"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add(e.getMessage());
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// Retail price
		i++;
		try {

			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(By
							.xpath("/html/body/div[3]/div[3]/section/section[3]/div[3]/div[6]/div[3]/div[3]/p[1]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("/html/body/div[3]/div[3]/section/section[3]/div[3]/div[6]/div[3]/div[3]/p[1]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add(e.getMessage());
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// Promotional discount
		i++;
		try {

			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(By
							.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[6]/div[3]/div[3]/p[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By
							.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[6]/div[3]/div[3]/p[2]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add(e.getMessage());

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[6]/div[3]/div[3]/h2"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[6]/div[3]/div[3]/h2"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add(e.getMessage());
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
			p = driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[3]/div[1]/h3/a/span[2]"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[3]/div[1]/h3/a/span[2]"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add(e.getMessage());
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}

		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[3]/div[3]/div[3]/h2"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[3]/div[3]/div[3]/h2"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add(e.getMessage());

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

			p = driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[8]/div[1]/h3/a/span[2]"))
					.getText();

			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[8]/div[1]/h3/a/span[2]"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add(e.getMessage());;
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[8]/div[3]/div[3]/h2"))
					.getText();

			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[8]/div[3]/div[3]/h2"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add(e.getMessage());

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

			p = driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[9]/div[1]/h3/a/span[2]"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[9]/div[1]/h3/a/span[2]"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add(e.getMessage());
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[9]/div[3]/div[3]/h2"))
					.getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[9]/div[3]/div[3]/h2"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add(e.getMessage());
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

			p = driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[4]/div[1]/h3/a/span[2]"))
					.getText();
			System.out.println(p);
			System.out.println(q);
			assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[4]/div[1]/h3/a/span[2]"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add(e.getMessage());
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// Retail price
		i++;
		try {

			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(By
							.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[4]/div[3]/div[3]/p[1]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver
					.findElement(By
							.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[4]/div[3]/div[3]/p[1]/span"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add(e.getMessage());

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// Promotional discount
		i++;
		try {

			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(By
							.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[4]/div[3]/div[3]/p[2]/span"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());

			list.add(driver
					.findElement(By
							.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[4]/div[3]/div[3]/p[2]/span"))
					.getText());

			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {

			list.add(e.getMessage());

			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[4]/div[3]/div[3]/h2"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[4]/div[3]/div[3]/h2"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add(e.getMessage());
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

			p = driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[11]/div[1]/h3/a/span[2]"))
					.getText();
			System.out.println(p);
			System.out.println(q);
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[11]/div[1]/h3/a/span[2]"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add(e.getMessage());
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();

			p = driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[11]/div[3]/div[3]/h2"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[11]/div[3]/div[3]/h2"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add(e.getMessage());
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

			p = driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[12]/div[1]/h3/a/span[2]"))
					.getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[12]/div[1]/h3/a/span[2]"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add(e.getMessage());
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[12]/div[3]/div[3]/h2"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[12]/div[3]/div[3]/h2"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add(e.getMessage());
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

			p = driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[13]/div[1]/h3/a/span[2]"))
					.getText();

			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(
							By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[13]/div[1]/h3/a/span[2]"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add(e.getMessage());
			results.add("Failed");
			cellFormat2.setBackground(Colour.RED);
		}
		// price
		i++;
		try {

			q = sh.getCell(0, i).getContents();
			p = driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[12]/div[3]/div[3]/h2"))
					.getText();
			Assert.assertEquals(q.trim(), p.trim());
			list.add(driver
					.findElement(By.xpath("html/body/div[3]/div[3]/section/section[3]/div[3]/div[12]/div[3]/div[3]/h2"))
					.getText());
			results.add("Passed");
			cellFormat.setBackground(Colour.GREEN);
		} catch (AssertionError | Exception e) {
			list.add(e.getMessage());
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
			count++;
				}
				else{
					Label labelRRe = new Label(2, j, r,cellFormat32);
					sheet2.addCell(labelRRe);
				}
			}
			j++;
			// listString += s + "\n";
            if(count>= 1){
                
       driver.findElement(By.id("errrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr")).click();
       
       //System.out.flush();
       System.out.println("executing 8-9");

       }
		}
		
		copy.write();
	    copy.close();
		
	}
}
