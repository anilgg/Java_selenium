package selenium.controls.webui.core;

import static selenium.core.Config.getProperty;
import static selenium.core.Selenium.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import selenium.business.Browser;
import selenium.core.Logger;

import com.google.common.base.Function;


public class FrameManager {
	private static String currentFrame = null;
	public static String crsFrame = null;
		
	
	static void switchFrame(String frame){	//	package-private method
		boolean needReset;
		String frameToSelect;
		
		if(currentFrame == null){
			needReset = false;
			frameToSelect = frame;
		}
		else{
			if(frame == null){
				needReset = true;
				frameToSelect = null;
			}
			else{
				if(frame.equals(currentFrame)){
					needReset = false;
					frameToSelect = null;
				}
				else if(frame.startsWith(currentFrame + ".")){
					needReset = false;
					frameToSelect = frame.replaceFirst(currentFrame + ".", "");
				}
				else{
					needReset = true;
					frameToSelect = frame;
				}
			}
		}
		
		if(needReset){
			webdriver.switchTo().defaultContent();
//			if(Browser.isIE()){	//	IEDriver may lose session after switching to top window
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {}
//			}
			//waits for page loading after switching frame to default content
			//Browser.waitDocumentReady();
			Logger.debug("Switching to frame: TOP");
			currentFrame = null;
		}
		if(frameToSelect != null){
			String [] frames = frameToSelect.split("\\.");
			for(int i = 0; i < frames.length; i++){
				Wait<String> wait = new FluentWait<String>(frames[i])
				.withTimeout(Integer.parseInt(getProperty("defaultTimeout")), java.util.concurrent.TimeUnit.MILLISECONDS);
				
				//	wait for the frame to appear
				wait.until(new Function<String, Boolean>() {
					@Override
					public Boolean apply(String frameName) {
						try{
							webdriver.findElement(By.name(frameName));
							return true;
						}
						catch(NoSuchElementException e1){
							try{	//	try again by id
								webdriver.findElement(By.id(frameName));
								return true;
							}
							catch(NoSuchElementException e2){
								Logger.debug("Exception upon switching frame to: " + frameName + "; current frame: " + currentFrame);
								return false;
							}
						}
					}
				});

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				webdriver.switchTo().frame(frames[i]);
				//Waits for page loading after switching frame
				//Browser.waitDocumentReady();
				Logger.debug("Switching to frame: " + frames[i]);
			}
			Logger.debug("Finished frame path selection. Frame path to select: " + frame + "; actually selected: " + frameToSelect);
			currentFrame = frame;
		}
	}
	
	public static void resetFrame(){
		switchFrame(null);
	}
	
	public static void waitForFramePresent() {
		if (currentFrame != null) {
			String frame = currentFrame;
			String[] frames = frame.split("\\.");
			
			// reset content to top
			//Browser.waitDocumentReady();
			webdriver.switchTo().defaultContent();
			//Browser.waitDocumentReady();
			Logger.debug("Switching to frame: TOP");
			currentFrame = null;
			
			for (int i = 0; i < frames.length; i++) {
				Wait<String> wait = new FluentWait<String>(frames[i]).withTimeout(Integer.parseInt(getProperty("defaultTimeout")), java.util.concurrent.TimeUnit.MILLISECONDS);

				// wait for the frame to appear
				wait.until(new Function<String, Boolean>() {
					@Override
					public Boolean apply(String frameName) {
						try {
							webdriver.findElement(By.name(frameName));
							return true;
						} catch (NoSuchElementException e1) {
							try { // try again by id
								webdriver.findElement(By.id(frameName));
								return true;
							} catch (NoSuchElementException e2) {
								Logger.debug("Exception upon switching frame to: "+ frameName);
								return false;
							}
						}
					}
				});

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// switch to frame
				webdriver.switchTo().frame(frames[i]);
				// Waits for page loading after switching frame
				//Browser.waitDocumentReady();
				Logger.debug("Switching to frame: " + frames[i]);
			}
			currentFrame = frame;
		}
	}
}
