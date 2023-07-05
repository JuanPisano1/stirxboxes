package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.TaskPage;


@Listeners({ listeners.TestListener.class })
@Epic("Lojack Recupero GPS")
@Feature("recupero GPS")

public class LoginStep extends BaseStep {
	LoginPage loginPage;
	TaskPage taskPage;
	public LoginStep(){
		this.loginPage = new LoginPage(Hooks.getRunnerDriver());
		this.taskPage = new TaskPage(Hooks.getRunnerDriver());
	}

	@Test(description = "recupero GPS")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Usuario realiza un ingreso con usuario y pass")
	@Story("Story: Recupero GPS")
	@Given("Ingresa credenciales para {string} y selecciona ingresar")
	public void logOn(String userName) {
		loginPage.fullLogin(userName);
	}


	@Test(description = "recupero GPS")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Usuario realiza un ingreso con usuario y pass")
	@Story("Story: Recupero GPS")
	@Given("Valida que se ecuentra en correctamente logueado")
	public void isUserLoggedOn() {
		Assert.assertTrue(taskPage.isTitleVisible());
	}


	@And("Revalida usuario y password")
	public void revalidaLogin() {
		loginPage.revalidaLogin();
	}



}