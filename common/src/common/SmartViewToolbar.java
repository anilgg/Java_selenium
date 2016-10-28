package common;

import org.junit.*;

import org.openqa.selenium.By;

import common.uimap.*;

public class SmartViewToolbar extends webBar {
	
	public static void clickToolBar(String id){
		if(driver.findElement(By.xpath(id)).isDisplayed()){
		driver.findElement(By.xpath(id));
		}
	}
	
	public static void toolbarNote(){
		webBar.clickToolBar(UIMap.toolbar);
		Assert.assertTrue(webBar.isNotePanelDisplayed(UIMap.notePanel));
		Assert.assertTrue(webBar.isNoteTextAreaEnabled(UIMap.noteTextArea));
		
		
	}

}
