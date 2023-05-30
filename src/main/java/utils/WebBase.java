package utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WebBase {
	// Description: Object declaration
	protected WebDriver driver;
	protected WebElement driverElement;
	private WebDriverWait wait;
	private FluentWait fluentWait;
	private Dotenv settings;

	// Description: Constructor de la clase

	public WebBase(WebDriver driver) {
		this.driver = driver;
		try{
			settings = EnvironmentConsumer.getInstance("settings");
			String timeoutStr = settings.get("Timeout");
			Duration timeout;
			if(timeoutStr != null){
				timeout = Duration.ofSeconds(Long.parseLong(timeoutStr));
			} else throw new Exception();
			wait = new WebDriverWait(driver, timeout);

		}catch(Exception e){
			System.out.println("ERROR: variable 'Timeout' no configurada en el archivo 'settings'");
			e.printStackTrace();
		}
	}

	public WebBase(WebDriver driver, String settingsFileName) {
		this.driver = driver;
		try{
			settings = EnvironmentConsumer.getInstance(settingsFileName);
			String timeoutStr = settings.get("Timeout");
			Duration timeout;
			if(timeoutStr != null){
				timeout = Duration.ofSeconds(Long.parseLong(timeoutStr));
			} else throw new Exception();
			wait = new WebDriverWait(driver, timeout);

		}catch(Exception e){
			System.out.println("ERROR: variable 'Timeout' no configurada en el archivo '" + settingsFileName + "'");
			e.printStackTrace();
		}
	}

	public WebBase(WebDriver driver, String settingsFileName, String timeoutName) {
		this.driver = driver;
		try{
			settings = EnvironmentConsumer.getInstance(settingsFileName);
			String timeoutStr = settings.get(timeoutName);
			Duration timeout;
			if(timeoutStr != null){
				timeout = Duration.ofSeconds(Long.parseLong(timeoutStr));
			} else throw new Exception();
			wait = new WebDriverWait(driver, timeout);
		}catch(Exception e){
			System.out.println("ERROR: variable 'Timeout' no configurada en el archivo '" + settingsFileName + "'");
			e.printStackTrace();
		}
	}

	/*********************************************************/
	/**************** Selenium POM Methods **************/
	/*********************************************************/

	// Description: Return webdriver
	public WebDriver getDriver() {
		return driver;
	}

	// Description: FindElement Method
	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}

	// Description: List WebElements Method
	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}

	// Description: Gettext Method Overload receives locator
	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}

	// Description: Gettext Method Overload receives locator by index
	public String getText(By locator, int index) {
		return driver.findElements(locator).get(index).getText();
	}

	// Description: GetAttribute value method InputText
	public String getAttribute(String value, By locator) {
		return driver.findElement(locator).getAttribute(value);
	}

	// Description: Select DropDownList By Index Locator Method
	public void selectUsingIndex(By locator, int index) {
		Select dDLCombo = new Select(findElement(locator));
		dDLCombo.selectByIndex(index);
	}

	// Description: Select DropDownList By Value Locator Method
	public void selectUsingValue(By locator, String value) {
		Select dDLCombo = new Select(findElement(locator));
		dDLCombo.selectByValue(value);
	}

	// Description: Select DropDownList By Text Locator Method
	public void selectUsingText(By locator, String text) {
		Select dDLCombo = new Select(findElement(locator));
		dDLCombo.selectByVisibleText(text);
	}

	// Description: Click Method catch excepcion
	public void click(By locator) {
		driver.findElement(locator).click();
	}

	public void click(WebElement element) {
		element.click();
	}

	// Description: Sendkeys Method
	public void typeKeys(String inputText, By locator) {
		driver.findElement(locator).sendKeys(inputText);
	}

	// Description: Sendkeys(tab,space,etc) Method
	public void typeKeys(Keys keys, By locator) {
		driver.findElement(locator).sendKeys(keys);
	}

	// Description: Sendkeys Method
	public void waitType(By Locator, String s) {
		wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
		driver.findElement(Locator).sendKeys(s);
	}

	public void waitClearType(By Locator, String s, long seconds) {
		waitClick(Locator, seconds);
		clear(Locator);
		typeKeys(s,Locator);
	}

	public void waitClearType(By Locator, String s) {
		waitClick(Locator);
		clear(Locator);
		typeKeys(s,Locator);
	}


	// Description: Clear Method
	public void clear(By locator) {
		driver.findElement(locator).clear();
	}

	// Description: Explicit wait Element to be clickeable
	public void waitClick(By Locator) {
		wait.until(ExpectedConditions.elementToBeClickable(Locator));
		driver.findElement(Locator).click();
	}

	// Description: Explicit wait Element to be clickeable
	public void waitClick(By Locator, long seconds) {
		WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		customWait.until(ExpectedConditions.elementToBeClickable(Locator));
		driver.findElement(Locator).click();
	}

	// Description: Explicit wait Element to be clickeable
	public void waitClick(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	// Description: Explicit wait Element to be clickeable
	public void waitClick(WebElement element, long seconds) {
		WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		customWait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void waitRefreshedClick(WebElement element) {
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
		element.click();
	}

	public void waitRefreshedClick(By element) {
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
		click(element);
	}

	public void waitRefreshedClick(WebElement element, long seconds) {
		WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		customWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
		click(element);
	}

	public void waitRefreshedClick(By element, long seconds) {
		WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		customWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
		click(element);
	}

	// Description: Explicit wait Element to be clickeable
	public void waitNoClick(By Locator) {
		wait.until(ExpectedConditions.elementToBeClickable(Locator));
	}

	// Description: Explicit wait Element to be clickeable
	public void waitNoClick(By Locator, long seconds) {
		WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		customWait.until(ExpectedConditions.elementToBeClickable(Locator));
	}

	// Description: Explicit wait Element to be clickeable
	public void waitNoClick(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// Description: Explicit wait Element to be clickeable
	public void waitNoClick(WebElement element, long seconds) {
		WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		customWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// Description: Explicit wait Element to be enabled
	public boolean waitElementEnabled(By Locator) {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(Locator)).isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	// Description: Explicit wait Element to be enabled
	public boolean waitElementEnabled(By Locator, long seconds) {
		try {
			WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
			return customWait.until(ExpectedConditions.visibilityOfElementLocated(Locator)).isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	// Description: Semd text amd clear Element
	protected void waitForElementToAppear(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Description: Semd text amd clear Element
	protected void waitForElementToAppear(By locator, long seconds) {
		WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Description: Explicit wait Element to be invisible and press Click
	protected void waitForElementToDisappear(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	// Description: Explicit wait Element to be invisible and press Click
	protected void waitForElementToDisappear(By locator, long seconds) {
		WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		customWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	// TODO: Create fluentWait
	public void fluentWait(long pollingTime, long timeOut, Class ignoringClass) {
		FluentWait wait = new FluentWait(driver).pollingEvery(Duration.ofMillis(pollingTime)).ignoring(ignoringClass)
				.withTimeout(Duration.ofSeconds(timeOut));

	}

	// Descripcion: PopUp Alert send Enter
	public void alertSendKeyEnter() {
		driver.switchTo().activeElement().sendKeys(Keys.ENTER);
	}

	public void sendKeyEnter(By Locator) {
		driver.findElement(Locator).sendKeys(Keys.ENTER);
	}

	// Description: Method to know if an element of the page loaded
	public boolean isDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	// Description: Implicit wait global
	public void implicitlyWait(long seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}

	// Description: Window maximize
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	// Description: Method to go the Url
	public void visit(String url) {
		driver.get(url);
	}

	// Description: Method to obtain the Url
	public String checkUrl() {
		return driver.getCurrentUrl();
	}

	// Description: Method to check if driver url is correct
	public boolean checkUrl(String url) throws Exception {
		try {
			return driver.getCurrentUrl().equals(url);
		} catch (Exception e) {
			throw new Exception("No se pudo obtener la Url");
		}
	}

	// Description: Get the current Page Title
	public String getTitlePage() throws Exception {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			throw new Exception("No se pudo obtener el titulo de la pestaña");
		}
	}

	// Description: Check if the page title is correct
	public boolean checkTitlePage(String title) throws Exception {
		try {
			return driver.getTitle().equals(title);
		} catch (Exception e) {
			throw new Exception("No se pudo obtener el titulo de la pestaña");
		}
	}

	// Description: Move back a single "item" in the browser's history
	public void back() {
		driver.navigate().back();
	}

	// Description: Move forward a single "item" in the browser's history
	public void forward() {
		driver.navigate().forward();
	}

	// Description: Accept Alert
	public void switchToAlertAccept() {
		driver.switchTo().alert().accept();
	}

	// Description: Capture alerts messages
	public String switchToAlertText() {
		return driver.switchTo().alert().getText();
	}

	// Description: Get the current window handle
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	// Description: Get multiple window handles
	public List<String> getWindowHandles() {
		Set<String> winHandles = driver.getWindowHandles();
		List<String> handles = new ArrayList<String>(winHandles);
		return handles;
	}

	// Description: To Perform a WebAction of MouseHover
	public void mouseHover(By Locator) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(findElement(Locator)).build().perform();
	}

	public void mouseHover(WebElement Locator) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(Locator).build().perform();
	}

	// Descripcion: Click en un elemento utilizando Action
	public void actionClick(WebElement Locator) {
		Actions actions = new Actions(getDriver());
		actions.click(Locator);
	}

	// Descripcion: Click and hold on a webElement without releasing
	public void actionClickHold(WebElement Locator) {
		Actions actions = new Actions(getDriver());
		actions.clickAndHold(Locator);
	}

	// Descripcion: Right click on a webElement
	public void actionContextClick(WebElement Locator) {
		Actions actions = new Actions(getDriver());
		actions.contextClick(Locator);
	}

	// Descripcion: Double click on a webElement
	public void actionDoubleClick(WebElement Locator) {
		Actions actions = new Actions(getDriver());
		actions.doubleClick(Locator);
	}

	// Descripcion: Release the hold on a webElement
	public void actionRelease(WebElement Locator) {
		Actions actions = new Actions(getDriver());
		actions.release(Locator);
	}

	// Description: Refresh Webpage
	public void refresh() {
		driver.navigate().refresh();
	}

	// Description: Close WebDriver
	public void closeDriver() {
		if (driver != null) {
			driver.close();
		}
	}

	// Description: Stop WebDriver
	public void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	// Description: iF the text is present on the website
	public boolean isTextPresent(String text) {
		return driver.getPageSource().contains(text);
	}

	// Description: ThreadSleep method
	public void sleepSeconds(long seconds) {
		try{
			Thread.sleep(seconds * 1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	// Description: Open new tab
	public WebDriver newTab(String url) {
		String a = "window.open('about:blank','_blank');";
		((JavascriptExecutor) driver).executeScript(a);
		this.goToLastTab();
		driver.get(url);
		return getDriver();
	}

	// Description: HighLightElements method
	public void highLightElement(WebElement element, String backgroundColor, String borderColor) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: " + backgroundColor + "; border: 2px solid "
				+ borderColor + ";');", element);
	}

	// Description: Go to las tab
	public void goToLastTab() {
		for (String subWindow : getDriver().getWindowHandles()) {
			getDriver().switchTo().window(subWindow);
		}
	}

	// Description: Go to tab by Title
	public void goToTabTitle(String title) throws Exception {
		try {
			Set<String> winHandles = getDriver().getWindowHandles();
			int tabsCount = winHandles.size();
			int currentTabIndex = 1;
			for (String subWindow : winHandles) {
				if (getTitlePage().equals(title)) {
					getDriver().switchTo().window(subWindow);
				} else if (currentTabIndex == tabsCount)
					throw new Exception();
				currentTabIndex++;
			}
		} catch (Exception e) {
			System.out.println("No se encontró la pestaña buscada");
			throw new Exception("No se encontró la pestaña buscada");
		}
	}

	// Description: Go to tab by url
	public void goToTabUrl(String url) throws Exception {
		try {
			Set<String> winHandles = getDriver().getWindowHandles();
			int tabsCount = winHandles.size();
			int currentTabIndex = 1;
			for (String subWindow : winHandles) {
				if (driver.getCurrentUrl().equals(url)) {
					getDriver().switchTo().window(subWindow);
				} else if (currentTabIndex == tabsCount)
					throw new Exception();
				currentTabIndex++;
			}
		} catch (Exception e) {
			System.out.println("No se encontró la pestaña buscada");
			throw new Exception("No se encontró la pestaña buscada");
		}
	}

	public void switchBackToDefaultContent(){
		driver.switchTo().defaultContent();
	}

	public void waitFrameAndSwitch(By frame){
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
	}

	public void waitFrameAndSwitch(String frame){
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
	}

	public void waitFrameAndSwitch(WebElement frame){
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
	}

	public void waitFrameAndSwitch(int frameNumber){
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNumber));
	}

	public void switchBackToParentFrame(){
		driver.switchTo().parentFrame();
	}

	public static byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
}
