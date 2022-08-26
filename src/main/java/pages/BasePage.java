package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WebBase;
import utils.driverManager.BrowserManager;

public class BasePage extends WebBase {
    public BasePage(WebDriver driver) {
        super(driver);
    }

    private By CARGANDO = By.xpath("//img[contains(@src,'cargando.gif')]");
    public void waitLoading(){
        try{
            waitForElementToAppear(CARGANDO, 10);
            waitForElementToDisappear(CARGANDO);
        }catch (Exception e){
            System.out.println("No es necesario capturar el spinner de carga");
        }

    }
}
