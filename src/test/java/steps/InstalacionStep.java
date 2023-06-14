package steps;

import database.business.Servicio;
import io.cucumber.java.en.And;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.InstalacionPage;
import utils.GlobalData;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Listeners({ listeners.TestListener.class })
@Epic("Lojack Recupero GPS")
@Feature("recupero GPS")

public class InstalacionStep extends BaseStep {
	Map<String, String> nrosSerie = new HashMap<>();

	String nroSerieGPS = "";

	InstalacionPage instalacionPage;
	public InstalacionStep(){
		instalacionPage = new InstalacionPage(Hooks.getRunnerDriver());
	}

	@Test(description = "recupero GPS")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Selecciona CTA")
	@Story("Story: Recupero GPS")
	@And("Selecciona la opcion {string}")
	public void selectCTA(String option) {
		instalacionPage.clickBTNGen(option);
	}

	@And("selecciona empresa {string}")
	public void seleccionaEmpresayVerificaNroSerieDispositivo(String option) throws Exception { //TODO REFACTOR
		instalacionPage.seleccionarEmpresa(option);
	}

	@And("ingresa numero de serie de {string} valido")
	public void ingresaNroSerie(String option) throws Exception {
		String nroSerie = instalacionPage.ingresarNroSerie(option);
		nrosSerie.put(option, nroSerie);
	}

	@And("completar Ubicacion {string} y Posicion {string}")
	public void completarUbicacionAsientoYPosicionDebajoAsientoConductor(String ubicacion, String posicion) {
		instalacionPage.completarUbicacionyPosicion(ubicacion,posicion);
		instalacionPage.validarServicioActivado();
		instalacionPage.finalizar();
	}

	@And("valida que {string} tenga tilde verde")
	public void validaQueExistaElTildeVerde(String servicio) throws Exception {
		instalacionPage.validarTildeVerde(servicio);
	}

	@And("edita el kilometraje")
	public void editaElKilometraje() {
		instalacionPage.editarKilometraje();
	}

	@And("valida el numero de serie del GPS en el resumen")
	public void validaElNumeroDeSerieDelGPSEnElResumen() {
		instalacionPage.validarNroSerie(GlobalData.nroSerieGPS);
	}

	@And("verifica el equipo")
	public void verificaElEquipo() {
		instalacionPage.verificarEquipo();
	}

	@And("finaliza la instalacion")
	public void finalizaLaInstalacion() {
		instalacionPage.finalizarInstalacion();
	}

	@And("finaliza la instalacion sin confirmar")
	public void finalizaLaInstalacionStrix_flotas() {
		instalacionPage.finalizarInstalacionFlotas();
	}

	@And("obtener nro de serie del GPS para {string}")
	public void obtenerNroDeSerieDelGPS(String servicio) {
		instalacionPage.obtenerNroSerieGPS(Servicio.valueOf(servicio.toUpperCase(Locale.ROOT)));
	}

	@And("obtener nro de serie del VLU para {string}")
	public void obtenerNroDeSerieDelVLU(String servicio) {
		instalacionPage.obtenerNroSerieVLU(Servicio.valueOf(servicio.toUpperCase(Locale.ROOT)));
	}

	@And("agregar servicio {string}")
	public void agregarServicio(String arg0) {
		instalacionPage.tildarServicioVLU(arg0);
	}

	@And("Selecciona la opcion Editar solucion para realizar cambio de solucion a {string}")
	public void editarSolucion(String solucion) {
		instalacionPage.cambioSolucion(solucion);
	}

	@And("se accede a {string}")
	public void accederComercial(String solucion){

		instalacionPage.accederComercial(solucion);}

}