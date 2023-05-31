package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import utils.driverManager.BrowserManager;


@CucumberOptions(
        publish = true,
        features = "src/test/resources/features",
        glue = {"steps","hooks"},
        tags = "@Recupero",
        plugin ={ "pretty","json:target/cucumber-reports/CucumberTestReport.json", })
public class RunnerByTag extends AbstractTestNGCucumberTests {
    public static WebDriver driver;
    @BeforeTest
    @Parameters({"SettingsFileName"})
    public void setupScenario(String settingsFileName) throws Exception {
        driver = BrowserManager.getDriverInstance(settingsFileName);

    }
}
