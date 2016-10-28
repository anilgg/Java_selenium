package selenium.core;

import static selenium.core.Config.getProperty;
import static selenium.core.Selenium.webdriver;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Stack;


import org.apache.commons.io.FileUtils;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.monte.media.math.Rational;
//import org.monte.media.Format;
//import org.monte.screenrecorder.ScreenRecorder;
//import static org.monte.media.AudioFormatKeys.*;
//import static org.monte.media.VideoFormatKeys.*;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;


public class CustomListener extends RunListener {
	private static CustomListener listener = null;

	private static boolean isFailure = false;
	private static boolean isTestCaseFailed = false;
	public final static String skippingMessage = "Test case was skipped";
	public final static String MEDIA_CONVERTER_FFMEG = "ffmpeg.exe";
	public final static String EXT_MP4 = ".mp4";
	public final static String EXT_MOV = ".mov";
	public final static String EXT_OGV = ".ogv";
	public final static String EXT_WEBM = ".webm";
	public final static String EXT_VTT = ".vtt";

	//This field is required as workaround to multiple calls of testFailure()
	private static String lastFailedNameTmp = "";
	private static String currentMethodName;
	private static String currentTestClass = "";
	private static String videoHtmlFileName = "";
	
private static File currentDir;
  private static String videosFolder;   
  private static File monteFile;   
  private static File renamedFile;  
  private static File renamedVttFile;
  private static File hackFolder;
	
	//	to allow screenshots in cleanup
	private static boolean isFinishRunOnFailure = false;
	

	//private static ScreenRecorder screenRecorder;
	
	private static Stack<Boolean> batchMode = new Stack<Boolean>();
	private static Stack<Boolean> dependentMode = new Stack<Boolean>();
	private static Stack<Boolean> suitePreconditionsPasses = new Stack<Boolean>();
	
	private static Hashtable<String, Boolean> testStatus = new Hashtable<String, Boolean>();
	
	public static void createAndAddToNotifier(RunNotifier notifier){
		if(listener == null){
			listener = new CustomListener();
			notifier.addListener(listener);
		}
	}
	
	public static boolean isFailure() {
		return CustomListener.isFailure;
	}
	
	public static boolean isSuitePreconditionsPasses() {
		return CustomListener.suitePreconditionsPasses.empty() ? true : CustomListener.suitePreconditionsPasses.peek();
	}
		
    public void testFailure(Failure failure) throws Exception {
       CustomListener.isFailure = true;
       isTestCaseFailed = true;
       String failingClass = failure.getDescription().getTestClass().getSimpleName();
       CustomListener.testStatus.put(failingClass, false);
       if (failingClass.contains("Preconditions") || CustomListener.getDependentMode() == true) {
    	   CustomListener.suitePreconditionsPasses.pop();
    	   CustomListener.suitePreconditionsPasses.push(false);
       }
       if (!CustomListener.lastFailedNameTmp.equals(failure.getDescription().toString()) &&
    		   !skippingMessage.equals(failure.getMessage())) {
    	   CustomListener.lastFailedNameTmp = failure.getDescription().toString();
    	   CustomListener.captureScreenshot(failure);
    	   if (recordVideo()) {
    		   captureVideo(failure);
    	   }
    	   System.err.println("<BR>");
       }
       
       if(CustomListener.isFinishRunOnFailure){
    	   BaseTest.finishRun();
       }
    }
    
	private static String getHackingPath(){
		int parentCount = CustomListener.currentTestClass.split("\\.").length;
		String result = "";
		for (int i = 0; i < parentCount; i++) {
			result = result + ".." + File.separator;
		}
		return result;
	}
	
	protected static String getShortClassName(){
		String[] fullClassName = CustomListener.currentTestClass.split("\\.");
		return fullClassName[fullClassName.length-1];
	}
	

	
	private static File getVideoDir(){
		File currentDir = new File(Config.getReportsFolderPath() + File.separator + "video");
		return currentDir;
	}
	
	private static void captureVideo(Failure failure) {
		String hackingPath = (getHackingPath() + "video/").replace(File.separator, "/");
		if (videoHtmlFileName != null) {
			System.err.println(String.format("<A href='%1s' >%2s: Desktop video</A><BR>",
					hackingPath + videoHtmlFileName, getShortClassName() + "."+CustomListener.currentMethodName));
		} else {
			System.err.println(String.format("<A href='%1s' >%2s: Desktop video not available</A><BR>",
					hackingPath, getShortClassName() + "."+CustomListener.currentMethodName));
		}
		
	}

	private static void captureScreenshot(Failure failure) {
		try {
			String hackingPath = getHackingPath().replace(File.separator, "/") + "screenshots" + "/";
			File currentDir = new File(Config.getReportsFolderPath() + File.separator + "screenshots");
			currentDir.mkdirs();

			String screenshotsFolder = currentDir.getCanonicalPath() + File.separator;
			String filename = "";
			String filenameDesktop = "";
			String plainString, htmlString, browserScreenshotURL;

			filename = System.currentTimeMillis() + "_ScreenshotBrowser.png";
			filenameDesktop = System.currentTimeMillis() + "_ScreenshotDesktop.png";

			if(Selenium.isRunning()){
				File scrFile = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(screenshotsFolder + filename));
				browserScreenshotURL = hackingPath + filename;
			}
			else{
				browserScreenshotURL = "#";
			}
		    System.err.println(String.format("<A href='%1$s' >%2$s: Browser screenshot</A><BR>",
		    		browserScreenshotURL, getShortClassName() + "."+CustomListener.currentMethodName));
		    Selenium.captureDesktopScreenShot(screenshotsFolder + filenameDesktop);
		    System.err.println(String.format("<A href='%1$s' >%2$s: Desktop screenshot</A><BR>",
					hackingPath + filenameDesktop, getShortClassName() + "."+CustomListener.currentMethodName));
			
		    plainString = String.format("Screenshot - %1$s", screenshotsFolder + filename); 
		    htmlString = String.format("<BR>Screenshots: <A href='%1$s'>Browser</A>, <A href='%2$s'>Desktop</A><BR>",
		    		browserScreenshotURL, hackingPath + filenameDesktop);
			Logger.stepFailed(failure, plainString, htmlString);
		} catch (Exception eInternal) {
			Logger.error("Error in screenshots: " + eInternal.getMessage());
			Logger.stepFailed(failure, "", "");
		}
	}
	
	/**
	 * Get reason for test skipping
	 * @return 
	 */
	private static String getSkipReason(String testClassName){
		if(!CustomListener.isSuitePreconditionsPasses()
				&& !testClassName.contains("Cleanup")){
			if(CustomListener.getDependentMode()){
				return "Skipped due to a failure/error in a previous test of dependent test suite.";
			}
			else{
				return "Skipped due to a failure/error in test suite preconditions.";
			}
		}
		else if(CustomTestRunner.isSkipCurrentTest()){
			return "Test is explicitly marked to be skipped.";
		}
		else return null;
	}
	
	public static boolean testSkipped(String testClassName){
		return getSkipReason(testClassName) != null;
	}
	
		
	private boolean recordVideo(){
		return getProperty("recordVideo","false").equals("true");
	}
	

	public static boolean getStatus(String testName){
		if(!CustomListener.testStatus.containsKey(testName)){
			return false;
		}
		else{
			return CustomListener.testStatus.get(testName);
		}
	}
	
	/**
	 * Trigger BaseTest.finishRun() upon the next testFailure() call (to take screenshots in cleanup)
	 */
	public static void setFinishRunOnFailure(){
		CustomListener.isFinishRunOnFailure = true;
	}
	
	public static void pushSuiteModeSettings(boolean batchMode, boolean dependencyMode){
		Boolean currentBatchMode = CustomListener.getBatchMode();
		CustomListener.batchMode.push(batchMode);
		CustomListener.dependentMode.push(dependencyMode);
		if(!currentBatchMode || CustomListener.suitePreconditionsPasses.empty()){
			CustomListener.suitePreconditionsPasses.push(true);
		}
		else{
			CustomListener.suitePreconditionsPasses.push(CustomListener.isSuitePreconditionsPasses());
		}
	}
	
	public static void popSuiteModeSettings(){
		CustomListener.batchMode.pop();
		CustomListener.dependentMode.pop();
		CustomListener.suitePreconditionsPasses.pop();
	}
	
	public static boolean getBatchMode(){
		return CustomListener.batchMode.empty() ? false : CustomListener.batchMode.peek();
	}
	
	public static boolean getDependentMode(){
		return CustomListener.dependentMode.empty() ? false : CustomListener.dependentMode.peek();
	}
	
	/**
	 * Capture screenshot and save it with given file name
	 * @param filename
	 */
	public static void captureScreenshot(String filename) {
		try {
			File currentDir = new File(Config.getReportsFolderPath() + File.separator + "screenshots" + File.separator + Config.getCurrentRequirementName()); 

			String screenshotsFolder = currentDir.getCanonicalPath() + File.separator;

			File scrFile = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(scrFile, new File(screenshotsFolder + filename));
		
		} catch (Exception eInternal) {
			Logger.error("Error in screenshots: " + eInternal.getMessage());
			
		}
	}
	
}
	