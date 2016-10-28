package selenium.core;

import static selenium.core.Config.getProperty;
import static selenium.core.Config.removeProperty;
import static selenium.core.Config.setProperty;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import selenium.business.Browser;
import selenium.controls.webui.core.FrameManager;

public class Selenium {
	public static WebDriver webdriver;
	private static Robot robot = null;
	private static boolean isRunning = false;

	private static WebDriver primaryWebDriver;
	private static WebDriver secondWebDriver;

	private static final String APP_BROWSER = "AppBrowser";
	private static final String APP_BROWSER_1 = "AppBrowser1";
	private static final String APP_BROWSER_2 = "AppBrowser2";

	private static final String REMOTE_WD_URL = "RemoteWebDriverURL";
	private static final String REMOTE_WD_URL_1 = "RemoteWebDriverURL1";
	private static final String REMOTE_WD_URL_2 = "RemoteWebDriverURL2";

	private static final String BROWSER_PROFILE = "BrowserProfile";
	private static final String BROWSER_PROFILE_1 = "BrowserProfile1";
	private static final String BROWSER_PROFILE_2 = "BrowserProfile2";

	private static void intialize() {
		// Handle AppBrowser
		setProperty(APP_BROWSER_1, getProperty(APP_BROWSER), false);

		// Handle RemoteWebDriverURL
		if(getProperty(REMOTE_WD_URL) != null)
			setProperty(REMOTE_WD_URL_1, getProperty(REMOTE_WD_URL), false);

		// Handle BrowserProfile
		if(getProperty(BROWSER_PROFILE) != null)
			setProperty(BROWSER_PROFILE_1, getProperty(BROWSER_PROFILE), false);
	}
	
	public static void start() throws Exception {
		webdriver = DriverManager.createDriver();
		primaryWebDriver = webdriver;

	}

	public static void switchToPrimaryInstance() throws Exception {
		FrameManager.resetFrame();

		// Handle AppBrowser
		setProperty(APP_BROWSER, getProperty(APP_BROWSER_1), false);

		// Handle RemoteWebDriverURL
		if(getProperty(REMOTE_WD_URL_1) != null)
			setProperty(REMOTE_WD_URL, getProperty(REMOTE_WD_URL_1), true);
		else
			removeProperty(REMOTE_WD_URL);

		// Handle BrowserProfile
		if(getProperty(BROWSER_PROFILE_1) != null)
			setProperty(BROWSER_PROFILE, getProperty(BROWSER_PROFILE_1), false);
		else
			removeProperty(BROWSER_PROFILE);

		if (primaryWebDriver == null) {
			webdriver = DriverManager.createDriver();
			primaryWebDriver = webdriver;
		} else {
			webdriver = primaryWebDriver;
		}
	}

	public static void switchToSecondaryInstance() throws Exception {
		intialize();
		FrameManager.resetFrame();

		// Handle AppBrowser
		setProperty(APP_BROWSER, getProperty(APP_BROWSER_2), false);

		// Handle RemoteWebDriverURL
		if(getProperty(REMOTE_WD_URL_2) != null)
			setProperty(REMOTE_WD_URL, getProperty(REMOTE_WD_URL_2), false);
		else
			removeProperty(REMOTE_WD_URL);

		// Handle BrowserProfile
		if(getProperty(BROWSER_PROFILE_2) != null)
			setProperty(BROWSER_PROFILE, getProperty(BROWSER_PROFILE_2), false);
		else
			removeProperty(BROWSER_PROFILE);

		if (secondWebDriver == null) {
			webdriver = DriverManager.createDriver();
			secondWebDriver = webdriver;
		} else {
			webdriver = secondWebDriver;
		}
	}

	public static void stop() throws IOException, InterruptedException {
		if (isRunning) {
			try {
				webdriver.quit();
				if (primaryWebDriver != null) {
					primaryWebDriver.quit();
					primaryWebDriver = null;
				}
				if (secondWebDriver != null) {
					secondWebDriver.quit();
					secondWebDriver = null;
				}
			} catch (WebDriverException e) {
				Logger.warning(e);
			}
			if (System.getProperty("os.name").startsWith("Windows") && getProperty(REMOTE_WD_URL_1) == null) {
				Process proc = Runtime.getRuntime().exec(
						String.format("taskkill /F /IM %1s.exe", getProperty(APP_BROWSER_1)));
				proc.waitFor();
			}
			if (System.getProperty("os.name").startsWith("Windows") && getProperty(REMOTE_WD_URL_2) == null) {
				Process proc = Runtime.getRuntime().exec(
						String.format("taskkill /F /IM %1s.exe", getProperty(APP_BROWSER_2)));
				proc.waitFor();
			}

		}
	}

	private static Robot getRobot() throws AWTException {
		if (robot == null) {
			robot = new Robot();
		}
		return robot;
	}

	public static void captureDesktopScreenShot(String filePath) {
		final BufferedImage bufferedImage;
		final Rectangle captureSize;
		final Robot robot;
		final File img;
		Logger.error(filePath);
		try {
			robot = getRobot();
			captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			bufferedImage = robot.createScreenCapture(captureSize);
			img = new File(filePath);
			ImageIO.write(bufferedImage, "png", img);
		} catch (AWTException e) {
			Logger.error(e);
		} catch (IOException e) {
			Logger.error(e);
		}
	}

	public static void get(String url) {
	webdriver.get(url);
	}
//		try {
//			webdriver.get(url);
//		} catch (TimeoutException e) {
//			if (Browser.isFF()) {
//				try {
//					stop();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//			throw e;
//		}
//	}

	public static boolean isRunning() {
		return isRunning;
	}
}
