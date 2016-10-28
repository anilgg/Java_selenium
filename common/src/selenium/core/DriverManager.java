package selenium.core;

import static selenium.core.Config.getProperty;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import selenium.business.Browser;

final class DriverManager {

	/**
	 * Creates a new webdriver instance.
	 *
	 * @return the webdriver instance
	 * @throws Exception the exception
	 */
	static WebDriver createDriver() throws Exception {
		WebDriver webdriver = null;

		int xstart = Integer.parseInt(getProperty("xstart", "100"));
		int ystart = Integer.parseInt(getProperty("ystart", "100"));
		int xres = Integer.parseInt(getProperty("xres", "1024"));
		int yres = Integer.parseInt(getProperty("xres", "768"));

		DesiredCapabilities capabilities;

		String wdUrl = getProperty("RemoteWebDriverURL");
		String appBrowser = getProperty("AppBrowser");

		if (appBrowser.equalsIgnoreCase("firefox")) {
			FirefoxProfile profile = createFFProfile();
			loadFFExtensions(profile);
			setPreferences(profile);
			if (wdUrl == null) {
				webdriver = new FirefoxDriver(profile);
			} else {
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability(FirefoxDriver.PROFILE, profile);
				webdriver = new RemoteWebDriver(new URL(wdUrl), capabilities);
			}
		} else if (appBrowser.equalsIgnoreCase("iexplore")) {
			if (wdUrl == null) {
				wdUrl = "http://localhost:" + getProperty("WebDriverServerPort");
			}
			capabilities = DesiredCapabilities.internetExplorer();
			// workaround for
			// http://code.google.com/p/selenium/issues/detail?id=4403
			capabilities.setCapability("nativeEvents", false);
			// end of workaround
			webdriver = new RemoteWebDriver(new URL(wdUrl), capabilities);
		} else if (appBrowser.equalsIgnoreCase("android")) {
			if (wdUrl == null) {
			} else {
				capabilities = DesiredCapabilities.android();
				webdriver = new RemoteWebDriver(new URL(wdUrl), capabilities);
			}
		} else if (appBrowser.equalsIgnoreCase("iphone")) {
			capabilities = new DesiredCapabilities();
			capabilities.setCapability("device", getProperty("Device"));
			capabilities.setCapability(CapabilityType.VERSION, getProperty("OSVersion"));
			capabilities.setCapability("app", "Safari");

			capabilities.setCapability("newCommandTimeout", "3600");
			capabilities.setCapability("launchTimeout", "120000");
			capabilities.setCapability("nonSyntheticWebClick", false);
			capabilities.setCapability("safariOpenLinksInBackground", true);
			if (wdUrl == null)
				wdUrl = "http://localhost:4723/wd/hub";
			webdriver = new RemoteWebDriver(new URL(wdUrl), capabilities);
		} else if (appBrowser.equalsIgnoreCase("nativeapp")) {
			capabilities = new DesiredCapabilities();
			capabilities.setCapability("device", getProperty("Device"));
			capabilities.setCapability(CapabilityType.VERSION, getProperty("OSVersion"));
			capabilities.setCapability("app", getProperty("NativeAppPath"));

			capabilities.setCapability("newCommandTimeout", "3600");
			capabilities.setCapability("launchTimeout", "120000");

			if (wdUrl == null)
				wdUrl = "http://localhost:4723/wd/hub";
			webdriver = new RemoteWebDriver(new URL(wdUrl), capabilities);
		} else if (appBrowser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");

			if (Config.getProperty("BrowserProfile") != null) {
				options.addArguments("user-data-dir=" + Config.getProperty("BrowserProfile"));
			}

			capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			if (wdUrl == null) {
				wdUrl = "http://localhost:" + getProperty("WebDriverServerPort");
			}
			webdriver = new RemoteWebDriver(new URL(wdUrl), capabilities);
		} else {
			throw new Exception("Unsupported browser: " + appBrowser);
		}

		// if wdUrl != null, it is a RemoteWebDriver which needs to be augmented
		if (wdUrl != null) {
			webdriver = new Augmenter().augment(webdriver);
		}

		return webdriver;
	}

	/**
	 * Creates the FF profile.
	 *
	 * @return the firefox profile
	 */
	private static FirefoxProfile createFFProfile() {
		FirefoxProfile profile;
		String profPath = Config.getProperty("BrowserProfile");
		if (profPath != null && !profPath.isEmpty()) {
			profile = new FirefoxProfile(new File(profPath));
		} else {
			profile = new FirefoxProfile();
		}
		profile.setPreference("capability.policy.default.HTMLIFrameElement.name", "allAccess");
		profile.setPreference("capability.policy.default.Window.frameElement", "allAccess");
		profile.setPreference("capability.policy.default.HTMLDocument.compatMode", "allAccess");
		profile.setPreference("capability.policy.default.Window.pageXOffset", "allAccess");
		profile.setPreference("capability.policy.default.Window.pageYOffset", "allAccess");
		// to disable any startup pages loaded by default
		profile.setPreference("browser.startup.page", 0);
		profile.setPreference("browser.startup.homepage", "about:blank");
		int timeout = Integer.parseInt(getProperty("defaultTimeout")) / 1000;
		timeout = timeout < 30 ? 30 : timeout;
		profile.setPreference("dom.max_script_run_time", timeout);
		profile.setPreference("dom.max_chrome_script_run_time", timeout);
		return profile;
	}

	private static void setPreferences(FirefoxProfile profile){
		String[] preferences, prefContainer;
		String preferencesContainer = Config.getProperty("FirefoxPreferences");
		if (preferencesContainer != null && !preferencesContainer.isEmpty()){
			preferences = preferencesContainer.split("\\|");
			for(String preference: preferences){
				prefContainer = preference.split("\\=");
				profile.setPreference(prefContainer[0], Boolean.valueOf(prefContainer[1]));
			}			
		}
	}
	
	/**
	 * Load FF extensions.
	 *
	 * @param profile the FF profile
	 */
	private static void loadFFExtensions(FirefoxProfile profile) {
		String[] extensionPaths;
		String exprop = Config.getProperty("FirefoxExtensions");
		if (exprop != null && !exprop.isEmpty()) {
			extensionPaths = exprop.split("\\|");
			for (int i = 0; i < extensionPaths.length; i++) {
				try {
					profile.addExtension(new File(extensionPaths[i]));
				} catch (IOException e) {
					Logger.warning("Error upon opening extension by path " + extensionPaths[i]
							+ ". Original exception: " + e.getMessage());
				}
				// disable firebug firstrun popup
				if (extensionPaths[i].toLowerCase().contains("firebug")) {
					profile.setPreference("extensions.firebug.showFirstRunPage", false);
				}
			}
		}
	}
}
