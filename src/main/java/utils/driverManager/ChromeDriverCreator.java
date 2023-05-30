package utils.driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.EnvironmentConsumer;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChromeDriverCreator extends BrowserManager {

	private String version = null;

	// Description: Creo la conexion chrome driver
	public WebDriver chromeDriverLocal() throws Exception {
		ChromeOptions options = new ChromeOptions();

		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.addArguments("--aggressive-cache-discard", "--allow-insecure-localhost",
				"--allow-running-insecure-content", "--disable-application-cache",
				"--disable-browser-side-navigation", "--disable-cache", "--disable-client-side-phishing-detection",
				"--disable-default-apps", "--disable-extensions", "--disable-infobars", "--disable-notifications",
				"--disable-offline-load-stale-cache", "--disable-popup-blocking", "--disable-web-security",
				"--ignore-certificate-errors", "--no-sandbox", "--start-maximized", "--test-type=browser","force-device-scale-factor=0.75", "high-dpi-support=0.75"
				,"--remote-allow-origins=*"
		);
		try{
			Dotenv settings = EnvironmentConsumer.getInstance("settings");
			String lang  = Objects.requireNonNull(settings.get("Language"));
			options.addArguments("--lang=" + lang);
		}catch(NullPointerException e) {}

		// Description: solve timeout exception issue
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		// Description: Disabled save pass prefs
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		return WebDriverManager.chromedriver().capabilities(options).create();
	}

	public WebDriver chromeDriverGrid(String urlSelenium, String os, String version) throws Exception {
		try {
			this.version = version;
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			options.addArguments("--aggressive-cache-discard", "--allow-insecure-localhost",
					"--allow-running-insecure-content", "--disable-application-cache",
					"--disable-browser-side-navigation", "--disable-cache", "--disable-client-side-phishing-detection",
					"--disable-default-apps", "--disable-extensions", "--disable-infobars", "--disable-notifications",
					"--disable-offline-load-stale-cache", "--disable-popup-blocking", "--disable-web-security",
					"--ignore-certificate-errors", "--no-sandbox", "--start-maximized", "--test-type=browser");
			try{
				Dotenv settings = EnvironmentConsumer.getInstance("settings");
				String lang  = Objects.requireNonNull(settings.get("Language"));
				options.addArguments("--lang=" + lang);
			}catch(NullPointerException e) {}
			// Description: solve timeout exception issue
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			// Description: Disabled save pass prefs
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);

			// options.setCapability("version", this.version);
			options.setBrowserVersion(this.version);
			// options.setCapability("browserName", "chrome");
			options.setPlatformName(os.toUpperCase());
			// options.setCapability("platform", os.toUpperCase());
			return new RemoteWebDriver(new URL(urlSelenium), options);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}