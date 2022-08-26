package pages;

import database.business.BoxesDB;
import database.business.Servicio;
import locators.InstalacionPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.GlobalData;
import utils.LocatorGenerator;

import java.util.List;
import java.util.Map;

public class InstalacionPage extends BasePage implements InstalacionPageLocators {
	public InstalacionPage(WebDriver driver) {
		super(driver);
	}

	public void clickBTNGen(String btnName){
		waitClick(LocatorGenerator.addMissignValue(INSTALACION_BTN_GENERICO,btnName));
	}

	public String obtenerNroSerieGPS(Servicio servicio){
		GlobalData.nroSerieGPS = BoxesDB.obtenerNroSerieGPS(servicio);
		return GlobalData.nroSerieGPS;

	}

	public String obtenerNroSerieVLU(Servicio servicio){
		GlobalData.nroSerieVLU = BoxesDB.obtenerNroSerieVLU(servicio);
		return GlobalData.nroSerieVLU;
	}


	public String validarGPS(String empresa) throws Exception {
		waitFrameAndSwitch(POPUP_IFRAME);
		waitClick(COMBO_EMPRESA);
		waitClick(LocatorGenerator.addMissignValue(EMPRESA_GPS, empresa));

		System.out.println("SERIAL GPS NUMBER: " + GlobalData.nroSerieGPS);

		waitClearType(CAMPO_SERIAL_GPS,GlobalData.nroSerieGPS);
		waitClick(BOTON_VALIDAR_GPS);
		waitLoading();
//		if(!isDisplayed(By.id("formNumeroSerie:ubicacionPorCriterio"))){
//			serialGPSNumber = tryWithNewGPS(false);
//		}else{
//			switchBackToDefaultContent();
//		}
		switchBackToDefaultContent();
		return GlobalData.nroSerieGPS;
	}

	public void seleccionarEmpresa(String empresa){
		waitFrameAndSwitch(POPUP_IFRAME);
		waitClick(COMBO_EMPRESA);
		waitClick(LocatorGenerator.addMissignValue(EMPRESA_GPS, empresa));
	}

	public String ingresarNroSerie(String option) {
		String serialNumber = "";
		if(option.equals("GPS")){
			serialNumber = GlobalData.nroSerieGPS;
			waitClearType(CAMPO_SERIAL_GPS,serialNumber);
			waitClick(BOTON_VALIDAR_GPS);
			waitLoading();
		}else{

			serialNumber = GlobalData.nroSerieVLU;
			waitFrameAndSwitch(POPUP_IFRAME);

			waitClearType(CAMPO_SERIAL_GPS,serialNumber);
			waitClick(BOTON_VALIDAR_VLU);
			waitLoading();
		}


		System.out.println(option +  " SERIAL NUMBER: " + serialNumber);
		switchBackToDefaultContent();

		return serialNumber;
	}

	public void completarAlertaDeConfirmacion(String opcion){
		try{
			By elemento = By.xpath("//button/span[text()='%s']");
			By elementoConVariable = LocatorGenerator.addMissignValue(elemento, opcion);
			waitForElementToAppear(elementoConVariable,10);
			waitClick(elementoConVariable);

		} catch (Exception e) {

		}

	}
	public void completarUbicacionyPosicion(String ubicacion, String posicion) {
		waitFrameAndSwitch(POPUP_IFRAME);
		waitClick(By.id("formNumeroSerie:ubicacionPorCriterio"));
		waitClick(By.xpath("//li[@data-label='" + ubicacion + "']"));
		waitLoading();
		waitClick(By.id("formNumeroSerie:posicion_label"));
		waitClick(By.xpath("//li[@data-label='" + posicion + "']"));
		waitLoading();
	}

	public void tildarServicioVLU(){
		waitFrameAndSwitch(POPUP_IFRAME);
		waitClick(CHECKBOX_SERVICIO_VLU);
		switchBackToDefaultContent();
	}
	public void validarServicioActivado(){
//		sleepSeconds(20);
//		!isEnabled(BOTON_SERVICIO) click(BOTON_SERVICIO);
	}

	public void finalizar(){
		waitClick(By.xpath("//button/span[contains(text(),'Agregar')]"));
		completarAlertaDeConfirmacion("Si");
	}

	public void validarTildeVerde(String servicio) throws Exception {
//		if(isDisplayed(By.id("formNumeroSerie:ubicacionPorCriterio"))) {
//			GlobalData.nroSerieGPS = tryWithNewGPS(true);
//			return;
//		}
		switchBackToDefaultContent();
		By check = By.xpath(String.format("//label[contains(text(), '%s')]//ancestor::td//span[contains(@class, 'icon-chekeado')]", servicio));
		waitForElementToAppear(check);
		List<WebElement> checksFound = driver.findElements(check);
		Assert.assertEquals(checksFound.size(), 1);
	}

//	private int timeout = 3;
//	private int count = 0;
//	private String tryWithNewGPS(boolean validation) throws Exception {
//		if(count < timeout) {
//			String serialGPSNumber = DatabaseBusiness.obtenerGPSSerialNumber();
//			System.out.println("SERIAL GPS NUMBER: " + serialGPSNumber);
//			waitClick(By.id("acciones:j_idt82")); //cancelar gps
//			if(!validation){
//				switchBackToDefaultContent();
//				waitLoading();
//			}
//
//			clickBTNGen("GPS");
//			validarGPS("Oleiros");
//			completarUbicacionyPosicion("Asiento", "Debajo asiento conductor");
//			validarTildeVerde();
//
//			waitClearType(CAMPO_SERIAL_GPS,serialGPSNumber);
//			waitClick(BOTON_VALIDAR_GPS);
//
//
//
//			switchBackToDefaultContent();
//
//			return serialGPSNumber;
//		}else{
//			throw new Exception("3 GPS_IDS INVALIDOS");
//		}
//
//	}


	public void editarKilometraje(){
		waitClick(BOTON_EDITAR_KILOMETRAJE);
		waitLoading();
		waitFrameAndSwitch(POPUP_IFRAME);
		waitType(INPUT_KILOMETRAJE, "100");
		waitClick(GUARDAR_KILOMETRAJE);
		waitLoading();
		switchBackToDefaultContent();
	}

	public void validarNroSerie(String serialGPSNumber){
		waitForElementToAppear(LocatorGenerator.addMissignValue(By.xpath("//label[text()='%s']"), serialGPSNumber));
	}


	public void verificarEquipo(){
		waitClick(By.id("gpss:0:gpsInstalado:formVerificarGps:j_idt289"));
	}

	public void finalizarInstalacion(){
		waitClick(BOTON_FINALIZAR_INSTALACION);
		waitClick(BOTON_CONFIRMA_SIN_VALIDAR_EQUIPO);
	}

	public void finalizarInstalacionFlotas(){

		waitClick(BOTON_FINALIZAR_INSTALACION);
	}


}


