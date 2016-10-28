package common;

import org.openqa.selenium.By;

public class webBar extends DriverManaget {
	
	public static void clickToolBar(String id){
		if(driver.findElement(By.xpath(id)).isDisplayed()){
		driver.findElement(By.xpath(id));
		}
	}
	
	public static boolean isNotePanelDisplayed(String id){
		boolean flag;
		if(driver.findElement(By.xpath(id)).isDisplayed()){
			flag=true;
		}else
			flag= false;
		return flag;
	}
	
	public static boolean isNoteTextAreaEnabled(String id){
		boolean flag;
		if(driver.findElement(By.xpath(id)).isEnabled()){
			flag=true;
		}else
			flag= false;
		return flag;
	}
	
	
}
