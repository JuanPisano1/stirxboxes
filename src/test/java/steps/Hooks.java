package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import testRunner.RunnerByTag;
import testRunner.TestRunner;
import utils.CustomScreenRecorder;
import utils.driverManager.BrowserManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static utils.WebBase.saveScreenshotPNG;

public class Hooks {
//    public static WebDriver driver = null;
//    @AfterStep
//    @Attachment(value = "Page screenshot", type = "image/png")
//    public void as(Scenario scenario){
//        if (scenario.isFailed()) {
//           scenario.attach(saveScreenshotPNG(getRunnerDriver()), "image/png", scenario.getName());
//        }
//    }

    private CustomScreenRecorder screenRecorder;
    @Before
    public void startScreenRecord(Scenario scenario) throws IOException, AWTException {
        screenRecorder = new CustomScreenRecorder(new File(System.getProperty("user.dir") + "/target/screen-records"));
        screenRecorder.startRecording(scenario.getName(), true);
    }

    @After
    public void stopScreenRecord() throws IOException {
        screenRecorder.stopRecording(true);
    }

    public static WebDriver getRunnerDriver(){
        WebDriver driver = null;
        if(TestRunner.driver != null){
            driver = TestRunner.driver;
        }else if(RunnerByTag.driver != null){
            driver = RunnerByTag.driver;
        }
        return driver;
    }

}
