package steps;

import database.business.BoxesDB;
import database.business.Servicio;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LandingPage;
import utils.GlobalData;

import java.util.Locale;
import java.util.Random;

import static utils.GlobalData.*;
import static utils.GlobalData.motor;


public class LandingStep extends  BaseStep{
	LandingPage landingPage;
	public LandingStep() throws Exception {
		landingPage = new LandingPage(Hooks.getRunnerDriver());

	}

	@Test(description = "Boxes Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Ingreso a la pagina de Boxesº")
	@Story("Story: Recupero GPS")
	@Given("Ingresa a la pagina de Boxes")
	public void visitBoxes() {
		landingPage.visitBoxes();
		Assert.assertTrue(landingPage.isLandingPage());
	}

	@Test(description = "Boxes Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Selecciona opcion Login")
	@Story("Story: Recupero GPS")
	@Given("Selecciona opcion login")
	public void gotoLogin() {
		landingPage.goToLogin();
	}

	@Then("Genera una solicitud para el servicio {string}")
	public void generaUnaSolicitud(String servicio) {
		landingPage.visitWaitPage("Generando solicitud en Turnos", 10);
		landingPage.generarSolicitud(Servicio.valueOf(servicio.toUpperCase(Locale.ROOT)));
	}

	@Then("Genera una solicitud para el servicio {string} y espera {int} segundos")
	public void generaUnaSolicitudYEspera(String servicio, int segundos) throws InterruptedException {
		landingPage.visitWaitPage("Generando solicitud en Turnos");
		landingPage.generarSolicitud(Servicio.valueOf(servicio.toUpperCase(Locale.ROOT)));
		landingPage.visitWaitPage("Generando solicitud en Turnos", segundos);
		Thread.sleep(segundos*1000);
	}

}