package com.note;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import common.DriverManaget;

public class Export extends DriverManaget{

	public static void main(String[] args) throws InterruptedException {
		
		DriverManaget.browserInstantiate("firefox");
		DriverManaget.getURL("https://foundationia.alpha.live.qa.oceanwidebridge.com/Foundation.IAS.WebClient/Default.aspx");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("div.dsh-tlbr-grp-lbl")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(".//*[@id='dsh-X']")).click();
        driver.findElement(By.xpath(".//*[@id='fnd_ias_cnfgPnl_1']/div[2]/div[1]/p/input")).clear();
        driver.findElement(By.xpath(".//*[@id='fnd_ias_cnfgPnl_1']/div[2]/div[1]/p/input")).sendKeys("New document input");
       
        List<WebElement> switchElement = driver.findElements(By.cssSelector("div.fnd_ias_cnfgOnOffIcon.fnd_ias_img26"));
        if (switchElement.size() != 0) {
            driver.findElement(By.xpath(".//*[@id='oneDataViewPerPage']/div[2]")).click();
        } else
            System.out.println("Switch  is already off");
          
        
        List<WebElement>radioButton = driver.findElements(By.xpath("(//input[@name='Orientation'])[2]"));
        System.out.println(radioButton.size());
        //If u want to select the radio button
        //driver.findElement(By.id("example")).click();
        Thread.sleep(3000);
        //If u want the Text in U R console
        for(int i=0;i<radioButton.size();i++){
        System.out.println(radioButton.get(i).getText());
        } 
        
        //driver.findElement(By.cssSelector("span.k-input")).click();
        //driver.findElement(By.cssSelector("li.k-item.k-state-hover")).click();
       Select sel = new Select(driver.findElement(By.cssSelector("span.k-input")));
       sel.selectByValue("Tabloid (11\"x17\")");
        
        List<WebElement> element = driver.findElements(By.xpath(".//*[@id='fnd_ias_cnfgPnl_1']/div[2]/div[4]/span/span/span[1]"));
        		
        for (int i = 0; i < element.size(); i++) {
           String temp = element.get(i).getText();
            if (temp.equals("Tabloid (11\"x17\")")) {
                element.get(i).click();             
                break;
            }
       }
        
        WebElement wb = driver.findElement(By.xpath(".//*[@id='fnd_ias_cnfgPnl_1']/div[2]/div[4]/span/span/span[1]"));
        wb.click();
        Actions mouse = new Actions(driver);
        mouse.moveToElement(wb).perform();
        Thread.sleep(4000);
        WebElement dri= driver.findElement(By.xpath(".//*[@id='fnd_ias_cnfgPnl_1']/div[2]/div[4]/span"));
        WebElement opt=dri.findElement(By.xpath("//select"));
        
        List<WebElement> opt1 =opt.findElements(By.tagName("//option"));
        for(WebElement o :opt1){
        	System.out.println("Text:"+o.getText());
        	if(o.getText()=="Tabloid (11\"x17\")"){
        		mouse.moveToElement(o);
        		mouse.click(o);
        	}
        }
        mouse.build();
        mouse.perform();
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("document.getElementsByTagName('option')[3].click()");
        js.executeScript(stringBuilder.toString());
        
//        driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
//        driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("2");
//        driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
//        driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("3");
//        driver.findElement(By.name("Export")).click();
        
	}

}
