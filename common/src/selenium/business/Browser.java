package selenium.business;

import static selenium.core.Config.getProperty;
import static selenium.core.Selenium.webdriver;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import selenium.controls.webui.core.FrameManager;
import selenium.core.Config;
import selenium.core.Logger;
import selenium.core.Selenium;
import com.google.common.base.Function;

public final class Browser {

	private Browser() {
		}
	private static String mainWindow = null;
	public static final String WINDOW_ID = "windowID";
	private static final long DEFAULT_TIMEOUT = Long.parseLong(getProperty("defaultTimeout"));
	public enum CustomStoreName{
		MOVIESTORE ("moviestore"),
		VIDEOGAMESTORE ("videogamestore"),
		HOMEUS ("homeus"),
		STOREUS ("storeus"),
		STOREDE ("storede"),
		DEFAULT ("");
		private final String value;
		private CustomStoreName(String value){
			this.value = value;
		}
		public String getName(){
			return value;
		}
	}

	public static boolean isBrowseMode = false;

	public enum CaptureNetworkTrafficFormat  {
		XML("xml"), JSON ("json"),
		PLAIN("plain");
		private String value;
		CaptureNetworkTrafficFormat(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	public static boolean isChrome() {
		return getProperty("AppBrowser").equalsIgnoreCase("chrome");
	}
	
	private static boolean isMaximize() {
		// true by default
		return Boolean.parseBoolean(getProperty("isMaximize", "true"));
	}

	private static class TimeoutThread extends Thread {
	    private final int timeout;
	    private boolean timeoutExpired = false;
	    private boolean shoudStop = false;
	    public TimeoutThread(int seconds) {
	        timeout = seconds;
	    }
		@Override
		public void run() {
			long finishTime = timeout * 1000L + System.currentTimeMillis();
			while (System.currentTimeMillis() < finishTime) {
				if (shoudStop) {
					break;
				}
				if (timeoutExpired) {
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					Logger.debug("InterruptedException in TimeoutThread occured");
				}
			}
			Logger.debug("TimeoutThread finished");
		}
	    public void stopThread(){
	    	Logger.debug("Stopping TimeoutThread");
	    	shoudStop = true;
	    }

	public static void maximize() {
		//	we start Chrome maximized; besides this method does not seem to be supported in Chrome anyway
		if(!Browser.isChrome() && Browser.isMaximize() ){
			FrameManager.resetFrame();	//	workaround for WebDriver bug when a frame is selected
			webdriver.manage().window().maximize();
		}
	}

	public static void minimize() {
		if(!Browser.isChrome() && Browser.isMaximize() ){
			FrameManager.resetFrame();	//	workaround for WebDriver bug when a frame is selected
			webdriver.manage().window().setPosition(new Point(0, 0));
			webdriver.manage().window().setSize(new Dimension(10, 10));
		}
	}	
	private static boolean isMaximize() {
		// true by default
		return Boolean.parseBoolean(getProperty("isMaximize", "true"));
	}
	public static String getLocation() {
		return webdriver.getCurrentUrl();
	}
	public static String getPageTitle() {
		return webdriver.getTitle();
	}
	public static void waitDocumentReady() {
		waitDocumentReady(DEFAULT_TIMEOUT);
	}
	public static void waitDocumentReady(String timeout) {
		waitDocumentReady(Long.parseLong(timeout));
	}
	public static void waitDocumentReady(long timeout) {
		String state;
		Wait<JavascriptExecutor> wait = new FluentWait<JavascriptExecutor>((JavascriptExecutor)webdriver)
		  .withTimeout(timeout, TimeUnit.MILLISECONDS)
		  .ignoring(WebDriverException.class)
		  .pollingEvery(100, TimeUnit.MILLISECONDS);
		try{
			wait.until(new Function<JavascriptExecutor, Boolean>() {
				@Override
				public Boolean apply(JavascriptExecutor js) {
					return ((String)js.executeScript("return document.readyState;")).equals("complete");
				}
			});
		}
		catch(TimeoutException e){
			state = (String)((JavascriptExecutor)webdriver).executeScript("return document.readyState;");
			if(!state.equals("interactive")){
				throw new TimeoutException("Document state is " + state + ". Original message is: " + e.getMessage());
			}
		}
	}
	public static void waitForPageLoadingStarts(long timeout) {
		Wait<JavascriptExecutor> waitLoading = new FluentWait<JavascriptExecutor>((JavascriptExecutor) webdriver)
			.withTimeout(timeout, TimeUnit.MILLISECONDS)
			.pollingEvery(50, TimeUnit.MILLISECONDS);
		try {
			waitLoading.until(new Function<JavascriptExecutor, Boolean>() {
				@Override
				public Boolean apply(JavascriptExecutor js) {
					return ((String) js.executeScript("return document.readyState;")).equals("loading");
				}
			});
		} catch (TimeoutException e1) {
			Logger.warning("Page did not start loading. Error message is: " + e1.getMessage());
		}
	}
	public static void setMainWindow(String nameOrHandle){
		mainWindow = nameOrHandle;
	}
	@Deprecated
	public static void closeWindow() throws Exception {
		throw new Exception("Deprecated. Use bccLogin with 3 params to reopen the window.");
	}
	public static void closeWindow(String windowID){
		selectWindow(windowID);
		webdriver.close();
		selectMainWindow();
	}
	public static String getWindowHandle() {
		return webdriver.getWindowHandle();
	}
	public static Dimension getWindowInnerSize() {
		int width = Integer.parseInt(((JavascriptExecutor)webdriver).executeScript("return document.documentElement.clientWidth;").toString());
		int height = Integer.parseInt(((JavascriptExecutor)webdriver).executeScript("return document.documentElement.clientHeight;").toString());
		return new Dimension(width, height);
	}

	public static void open(String url, int timeout) {
		FrameManager.crsFrame = null;
		TimeoutThread timeoutThread = new TimeoutThread(timeout);
		timeoutThread.start();
		Selenium.get(url);
		timeoutThread.stopThread();
		timeoutThread.interrupt();
		if(timeoutThread.timeoutExpired){
			throw new TimeoutException("URL " + url + " was not loaded in " + timeout + " seconds.");
		}
	}
	public static void openWindow(String url, String windowID) {
		((JavascriptExecutor) webdriver).executeScript(String.format("window.open('%1$s','%2$s')", url, windowID));
		}
	public static void selectWindow(String windowID){
		if (windowID == null || windowID == "") {
			webdriver.switchTo().window(getWindowHandles()[0]);
		} else {
			webdriver.switchTo().window(windowID);
		}
	}
	public static String[] getWindowHandles() {
		Set<String> handles = webdriver.getWindowHandles();
		return handles.toArray(new String[handles.size()]);
	}
	public static void selectMainWindow(){
		mainWindow = getWindowHandles()[0];
		webdriver.switchTo().window(mainWindow);
	}
	public static void selectChildWindow(){
		Assert.assertTrue("There is only one window. No any child window.", webdriver.getWindowHandles().size()>1);
		String secondHwnd = getWindowHandles()[1];
		webdriver.switchTo().window(secondHwnd);
	}
	public static void selectWindow(int index){
		Assert.assertTrue("There is no window with index: "+index, webdriver.getWindowHandles().size()>index);
		String secondHwnd = getWindowHandles()[index];
		webdriver.switchTo().window(secondHwnd);
	}
	public static String getPageSource() {
		return webdriver.getPageSource();
	}
	public static String getCookieString() {
		StringBuilder cookieString = new StringBuilder();
		Set<Cookie> cookies = webdriver.manage().getCookies();
		for (Cookie cookie : cookies) {
			cookieString.append(cookie.getName());
			cookieString.append("=");
			cookieString.append(cookie.getValue());
			cookieString.append("; ");
		}
		return cookieString.toString();
	}
}
}
