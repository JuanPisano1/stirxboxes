package locators;

import org.openqa.selenium.By;

import java.nio.file.Paths;

public interface LandingPageLocators {
     String URL_BOXES = "http://192.168.166.84:8081/boxes/";

     String URL_WAIT_PAGE= Paths.get("src/test/resources/wait.html").toUri().toString();
     By LANDING_BANNER = By.xpath("//div[@class='MarAuto Card ShadowEffect']");
     By HEAD_LOGIN_ICON = By.xpath("//span[@class='ui-menuitem-text']");
}
