package steps;

import database.business.Servicio;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.TaskPage;
import utils.GlobalData;

import java.util.Locale;

@Listeners({ listeners.TestListener.class })


public class TasksStep extends BaseStep {
	TaskPage taskPage;
	public TasksStep(){
		taskPage = new TaskPage(Hooks.getRunnerDriver());
	}

	// STRIX_MOTO|STRIX_AUTO|STRIX_FLOTAS|RECUPERO|ESTANDAR_VLU)
	@Given("un turno de Instalacion para la solucion {string}")
	public void comienzaElTest(String test) {
		System.out.println("Comienza el test " + test);
	}

	@Test(description = "Task Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Busqueda de un vehiuculo")
	@Story("Story: Recupero GPS")
	@Given("Selecciona un vehiculo cuya tarea se encuentra en estado {string} y con dominio {string}")
	public void gotBoxes(String state, String domain) {
		taskPage.filtroRecepcion();
		taskPage.searchAVehicle(domain);
		taskPage.pickFirstElement(domain);
	}

	@When("Decide recepcionarlo")
	public void picReception(){
		taskPage.receptACar();
	}

	@And("Asigna el tecnico que tomara la tarea y confirma la tarea")
	public void takeTask() {
		taskPage.assignJob();
	}

	@And("Filtra por estado {string} para el vehiculo recepcionado y la accion de {string}")
	public void PickInstall(String taskFilter,String accion){
		taskPage.filtroInstall();
		taskPage.searchAVehicle();
		taskPage.pickTask(accion);
	}

	@And("se filtran tareas por vehiculo")
	public void seFiltranTareasPorVehiculo() {
		taskPage.searchAVehicle();
	}

	@And("se accede a la tarea de: {string}")
	public void seAccedeALaTareaDe(String opcion) {
		taskPage.filtroTareas(opcion);
		taskPage.pickTask(opcion);
		taskPage.assignJob();
	}

	@And("se agrega el servicio {string}")
	public void agregarServicioAdicional(String servicio) {
		taskPage.agregarServicio(servicio);
	}

	@And("se registran los datos de la gestion operativa con contrato: {string} y producto: {string}")
	public void registrarGestionOperativa(String contrato, String producto) {
		taskPage.registrarEmpresaYProducto(contrato,producto);
	}

	@And("se registran los datos de Cliente, tipo de cliente: {string}, condición impositiva: {string}")
	public void registrarDatosDelCliente(String tipoCliente, String condicionImpositiva) {
		taskPage.registrarDatosCliente(tipoCliente, condicionImpositiva);
	}

	@And("se registran los datos del Identificable con uso: {string}, color: {string}")
	public void seRegistranLosDatosDelIdentificable(String uso, String color) {
		taskPage.registrarDatosIdentificable(uso, color);
	}

	@And("se registran los datos de Facturacion")
	public void seRegistranLosDatosDeFacturacion() {
		taskPage.registrarDatosFacturacion();
	}

	@And("se registran los datos de GIS")
	public void seRegistranLosDatosDeGIS() {
		taskPage.registrarDatosGIS();
	}
	@And("se registran los datos de Operaciones")
	public void seRegistranLosDatosDeOperaciones() {
		taskPage.registrarDatosOperaciones();
	}

	@And("imprimir SDS")
	public void imprimirSDS() {
		try{
			taskPage.imprimirSDS();
		}catch (Exception e){
			System.out.println("No solicita imprimir SDS");
		}
	}

	@And("se completan campos de gestion de calidad")
	public void seCompletanCamposGestionCalidad() {

		taskPage.completarRandom("No");
		taskPage.completarClasificacion1("Bueno");
		taskPage.completarClasificacion2("Bueno");
	}

	@And("se finaliza la tarea de Control de Calidad")
	public void seFinalizaLaTareaDeControDeCalidad() {

		taskPage.finalizarCCalidad();
	}
	@And("se finaliza la tarea de Control SDS")
	public void seFinalizaLaTareaDeControlSDS() {

		taskPage.finalizarSDS();
	}

	@And("el cliente tiene SDS firmada electronicamente")
	public void elClienteTieneSDSFirmadaElectronicamente() {
		try{
			taskPage.firmaElectronicaSDS();
		}catch (Exception e){
			System.out.println("No solicita confirmar firma electrónica");
		}
	}

	@And("Realizar Cierre")
	public void realizarCierre() {
		taskPage.finalizarCierre();
	}

	@When("Selecciona un vehiculo con dominio {string}")
	public void seleccionaUnVehiculoConDominio(String dominio) {
		if(GlobalData.vehiculoDominio.isEmpty()){
			GlobalData.vehiculoDominio = dominio;
		}
		taskPage.searchAVehicle(dominio);
	}



}