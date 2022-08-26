package utils.driverManager;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import utils.EnvironmentConsumer;

import java.util.HashMap;
import java.util.Map;

public class BrowserManager {
	protected static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	private static Dotenv settings;

	public static WebDriver getDriverInstance(String settingsFileName) throws Exception {
		WebDriver driver = drivers.get(settingsFileName);
		if(driver == null){
			settings = EnvironmentConsumer.getInstance(settingsFileName);
			boolean gridEnabled = Boolean.parseBoolean(settings.get("GridEnabled"));
			String browserType = settings.get("BrowserType");

			System.out.println(browserType);
			System.out.println(Boolean.toString(gridEnabled));

			if (gridEnabled) {
				driver = createGridDriver(browserType);
			} else {
				driver = createLocalDriver(browserType);
			}

			driver.get("about:blank");
			driver.manage().window().maximize();
			// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		}
		return driver;
	}

	public static void tearDown(WebDriver driver) throws Exception {
		if (driver != null) {
			try {
				driver.manage().deleteAllCookies();
				driver.quit();
				driver = null;
			} catch (Exception e) {
				throw new Exception("ERROR AL CERRAR EL DRIVER");
			}
		}
	}

	private static WebDriver createGridDriver(String browserType) throws Exception {
		WebDriver driver = null;
		String gridUrl = settings.get("GridURL");
		String oS = settings.get("OSName");
		String version = settings.get("Version");
		switch (browserType.toUpperCase()) {
		case "CHROME":
			ChromeDriverCreator chromeDriverCreator = new ChromeDriverCreator();
			driver = chromeDriverCreator.chromeDriverGrid(gridUrl, oS, version);
			break;
		case "EDGE":
			EdgeDriverCreator edgeDriverCreator = new EdgeDriverCreator();
			driver = edgeDriverCreator.edgeDriverGrid(gridUrl, oS, version);
			break;
		case "FIREFOX":
			FirefoxDriverCreator firefoxDriverCreator = new FirefoxDriverCreator();
			driver = firefoxDriverCreator.firefoxDriverGrid(gridUrl, oS, version);
			break;
		case "IE":
			IEDriverCreator ieDriverCreator = new IEDriverCreator();
			driver = ieDriverCreator.ieDriverGrid(gridUrl, oS, version);
			break;
		default:
		}
		return driver;
	}

	private static WebDriver createLocalDriver(String browserType) throws Exception {
		WebDriver driver = null;
		switch (browserType.toUpperCase()) {
		case "CHROME":
			ChromeDriverCreator chromeDriverCreator = new ChromeDriverCreator();
			driver = chromeDriverCreator.chromeDriverLocal();
			break;
		case "EDGE":
			EdgeDriverCreator edgeDriverCreator = new EdgeDriverCreator();
			driver = edgeDriverCreator.edgeDriverLocal();
			break;
		case "FIREFOX":
			FirefoxDriverCreator firefoxDriverCreator = new FirefoxDriverCreator();
			driver = firefoxDriverCreator.firefoxDriverLocal();
			break;
		case "IE":
			IEDriverCreator ieDriverCreator = new IEDriverCreator();
			driver = ieDriverCreator.ieDriverLocal();
			break;
		default:
		}
		return driver;
	}
}
