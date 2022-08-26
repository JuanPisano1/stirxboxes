package testRunner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import steps.Hooks;
import utils.driverManager.BrowserManager;

import java.io.File;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps"},
        tags = "@Smoke",
        plugin ={ "pretty","html:target/cucumber-reports/CucumberTestReport.html" })
public class TestRunner extends AbstractTestNGCucumberTests {
    public static WebDriver driver;
    @BeforeClass
    @Parameters({"SettingsFileName"})
    public void setupScenario(String settingsFileName) throws Exception {
        driver = BrowserManager.getDriverInstance(settingsFileName);
    }
    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        BrowserManager.tearDown(driver);
    }
}
