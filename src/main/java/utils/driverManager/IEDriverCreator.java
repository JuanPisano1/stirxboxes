package utils.driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class IEDriverCreator {
    private String version = null;

    // Description: Creo la conexion chrome driver
    public WebDriver ieDriverLocal() throws Exception {
        InternetExplorerOptions options = new InternetExplorerOptions();
        // options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        // options.addArguments("--aggressive-cache-discard", "--allow-insecure-localhost",
        //         "--allow-running-insecure-content", "--disable-application-cache", "--disable-browser-side-navigation",
        //         "--disable-cache", "--disable-client-side-phishing-detection", "--disable-default-apps",
        //         "--disable-extensions", "disable-infobars", "--disable-notifications",
        //         "--disable-offline-load-stale-cache", "--disable-popup-blocking", "--disable-web-security",
        //         "--ignore-certificate-errors", "--no-sandbox", "start-maximized", "test-type=browser");
        // Description: solve timeout exception issue
        //options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        // Description: Disabled save pass prefs
        // Map<String, Object> prefs = new HashMap<String, Object>();
        // prefs.put("credentials_enable_service", false);
        // prefs.put("profile.password_manager_enabled", false);
        // options.setExperimentalOption("prefs", prefs);

        return WebDriverManager.iedriver().capabilities(options).create();
    }

    public WebDriver ieDriverGrid(String urlSelenium, String os, String version) throws Exception {
        try {
            this.version = version;
            InternetExplorerOptions options = new InternetExplorerOptions();
            //options.addCommandSwitches("-private");
            // options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
            // options.addArguments("--aggressive-cache-discard", "--allow-insecure-localhost",
            //         "--allow-running-insecure-content", "--disable-application-cache",
            //         "--disable-browser-side-navigation", "--disable-cache", "--disable-client-side-phishing-detection",
            //         "--disable-default-apps", "--disable-extensions", "disable-infobars", "--disable-notifications",
            //         "--disable-offline-load-stale-cache", "--disable-popup-blocking", "--disable-web-security",
            //         "--ignore-certificate-errors", "--no-sandbox", "start-maximized", "test-type=browser");
            // Description: solve timeout exception issue
            //options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            // Description: Disabled save pass prefs
            // Map<String, Object> prefs = new HashMap<String, Object>();
            // prefs.put("credentials_enable_service", false);
            // prefs.put("profile.password_manager_enabled", false);
            // options.setExperimentalOption("prefs", prefs);

            // options.setCapability("version", this.version);
            //options.setBrowserVersion(this.version);
            // options.setCapability("browserName", "chrome");
            //options.setPlatformName(os.toUpperCase());
            // options.setCapability("platform", os.toUpperCase());
            return new RemoteWebDriver(new URL(urlSelenium), options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}