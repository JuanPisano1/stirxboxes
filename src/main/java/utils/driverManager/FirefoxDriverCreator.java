package utils.driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class FirefoxDriverCreator {
    private String version = null;

	// Description: Creo la conexion chrome driver

	public WebDriver firefoxDriverLocal() throws Exception {
		FirefoxOptions options = new FirefoxOptions();
		// options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		// options.addArguments("--aggressive-cache-discard", "--allow-insecure-localhost",
		// 		"--allow-running-insecure-content", "--disable-application-cache", "--disable-browser-side-navigation",
		// 		"--disable-cache", "--disable-client-side-phishing-detection", "--disable-default-apps",
		// 		"--disable-extensions", "disable-infobars", "--disable-notifications",
		// 		"--disable-offline-load-stale-cache", "--disable-popup-blocking", "--disable-web-security",
		// 		"--ignore-certificate-errors", "--no-sandbox", "start-maximized", "test-type=browser");
		// Description: solve timeout exception issue
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		// Description: Disabled save pass prefs
		// Map<String, Object> prefs = new HashMap<String, Object>();
		// prefs.put("credentials_enable_service", false);
		// prefs.put("profile.password_manager_enabled", false);
		// options.setExperimentalOption("prefs", prefs);

		return WebDriverManager.firefoxdriver().capabilities(options).create();
	}

	public WebDriver firefoxDriverGrid(String urlSelenium, String os, String version) throws Exception {
		try {
			this.version = version;
			FirefoxOptions options = new FirefoxOptions();
			// options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			// options.addArguments("--aggressive-cache-discard", "--allow-insecure-localhost",
			// 		"--allow-running-insecure-content", "--disable-application-cache",
			// 		"--disable-browser-side-navigation", "--disable-cache", "--disable-client-side-phishing-detection",
			// 		"--disable-default-apps", "--disable-extensions", "disable-infobars", "--disable-notifications",
			// 		"--disable-offline-load-stale-cache", "--disable-popup-blocking", "--disable-web-security",
			// 		"--ignore-certificate-errors", "--no-sandbox", "start-maximized", "test-type=browser");
			// Description: solve timeout exception issue
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			// Description: Disabled save pass prefs
			// Map<String, Object> prefs = new HashMap<String, Object>();
			// prefs.put("credentials_enable_service", false);
			// prefs.put("profile.password_manager_enabled", false);
			// options.setExperimentalOption("prefs", prefs);

			// options.setCapability("version", this.version);
			options.setBrowserVersion(this.version);
			// options.setCapability("browserName", "MicrosoftEdge");
			// options.setCapability("platform", os.toUpperCase());
			options.setPlatformName(os.toUpperCase());
			return new RemoteWebDriver(new URL(urlSelenium), options);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
