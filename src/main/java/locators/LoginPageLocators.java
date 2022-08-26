package locators;

import org.openqa.selenium.By;

public interface LoginPageLocators {
    By LOGIN_USER_TXT=By.id("ingreso:nombre");
    By LOGIN_PASS_TXT=By.id("ingreso:credenciales");
    By LOGIN_BTN=By.xpath("//span[@class='ui-button-text ui-c' and contains(text(),'Ingresar')]");
    By LOGIN_BTN_GENERIC=By.xpath("//span[@class='ui-button-text ui-c' and contains(text(),'%s')]");
    By BOTON_REVALIDA=By.xpath("//button/span[text()='Continuar' or contains(text(),'Tomar')]");
}
