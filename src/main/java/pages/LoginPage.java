package pages;


import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Step;
import locators.LoginPageLocators;
import org.openqa.selenium.WebDriver;
import utils.EnvironmentConsumer;

import utils.WebBase;

public class LoginPage extends BasePage implements LoginPageLocators {
	Dotenv env;
	public LoginPage(WebDriver driver) {
		super(driver);
		env = EnvironmentConsumer.getInstance("properties");
	}

	@Step("Usuario decide ingresar a boxes con usuario y contraseña")
	public void fullLogin(String userName){
		waitClearType(LOGIN_USER_TXT, env.get("boxesUser"));
		waitClearType(LOGIN_PASS_TXT, env.get("boxesPassword"));
		waitClick(LOGIN_BTN);

	}

	@Step("Revalidacion login")
	public void revalidaLogin(){
		try{
			try{
				if(waitElementEnabled(LOGIN_USER_TXT, 5)){
					waitClearType(LOGIN_USER_TXT, env.get("boxesUser"));
				}
			}catch(Exception e){
//
			}
			waitClearType(LOGIN_PASS_TXT, env.get("boxesPassword"), 5);
			waitClick(BOTON_REVALIDA, 10);
		}catch(Exception e){
			System.out.println("No se pudo revalidar login");
		}
	}



	private void clickOnLogin(){


	}
}
