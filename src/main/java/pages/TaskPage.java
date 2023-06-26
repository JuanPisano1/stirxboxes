package pages;

import io.qameta.allure.Step;
import locators.TaskPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utils.GlobalData;
import utils.LocatorGenerator;

public class TaskPage extends BasePage implements TaskPageLocators {

    private LoginPage loginPage;

    public TaskPage(WebDriver driver) {
        super(driver);
        loginPage = new LoginPage(driver);
    }

    @Step("Valida que se ecuentra en correctamente logeado")
    public boolean isTitleVisible(){
        waitForElementToAppear(HOME_TITLE_LABEL,10);
        return isDisplayed(HOME_TITLE_LABEL);
    }

    @Step("Filtra por tipo de tarea == Recepcion")
    public void filtroRecepcion(){
        waitForElementToAppear(TASK_TABLE);

        findElements(TASK_DROPDOWN_FILTER_LIST).get(1).click();
        click(TASK_OPTION_TYPE_RECEPTION);
        sleepSeconds(3);
        findElements(CLOSE_X_BTN_LIST).get(1).click();
    }
    @Step("Filtra por tipo de tarea == Instalacion")
    public void filtroInstall(){
        sleepSeconds(3);
        waitForElementToAppear(TASK_TABLE);
        sleepSeconds(7);
        findElements(TASK_DROPDOWN_FILTER_LIST).get(1).click();
        sleepSeconds(3);
        click(TASK_OPTION_TYPE_INSTALL);
        sleepSeconds(3);
        findElements(CLOSE_X_BTN_LIST).get(1).click();
    }

    @Step("Filtra por tipo de tarea ")
    public void filtroTareas(String filterTask){
        waitClick(COMBO_FILTRO_TAREA);
        By opcionComboFiltroTarea = LocatorGenerator.addMissignValue(OPCION_COMBO_FILTRO_TAREA,filterTask);
        waitClick(opcionComboFiltroTarea);
        waitLoading();
        waitClick(COMBO_FILTRO_TAREA);
    }
    @Step("Selecciona vehiculo")
    public void searchAVehicle(String dominio){
        if(dominio.isEmpty()) dominio = GlobalData.vehiculoDominio;
        waitClearType(TASK_SEARCH_FIELD,dominio);
    }
    @Step("Selecciona vehiculo")
    public void searchAVehicle(){
        waitClearType(TASK_SEARCH_FIELD,GlobalData.vehiculoDominio);
    }
    @Step("Selecciona primer elemento de la lista")
    public void pickFirstElement(String domain){
        if(domain.isEmpty()) domain = "SAM";
        waitClick(TASK_FIRST_ELEMENT);
    }
    @Step("Recepciona Vehiculo")
    public void receptACar(){
        waitForElementToDisappear(TASK_FIRST_ELEMENT);
        waitFrameAndSwitch(IFRAME);
        waitClick(TASK_RECIBE_BTN);
        switchBackToParentFrame();
    }

    @Step("Selecciona la tarea")
    public void pickTask(String task){
        waitClick(LocatorGenerator.addMissignValue(TASKS_DOMAIN_SELECTED,task));
    }

    @Step("Asigna la tarea")
    public void assignJob(){
        try {
            waitFrameAndSwitch(IFRAME);
            waitClick(BUTTON_TOMAR_TAREA);
        }catch (Exception e) {
            System.out.println("No es necesario volver a tomar la tarea");
        }

        switchBackToDefaultContent();
        try{
            loginPage.revalidaLogin();
        } catch (Exception e) {
            System.out.println("No es necesario ingresar las credenciales");
        }

    }

    public void agregarServicio(String servicio){
        waitClick(By.xpath("//label[@id='solucionVendida:j_idt85:serviciosAdicionales_label']"));
        waitClick(LocatorGenerator.addMissignValue(By.xpath("//li[contains(text(), '%s')]"), servicio));
        waitClick(By.xpath("//button[@id='solucionVendida:j_idt85:j_idt131']"));
        waitLoading();
    }




    @Step("Elegir contrato")
    public void registrarEmpresaYProducto(String contrato, String producto){
        waitClick(COMBO_CONTRATO);
        waitClick(LocatorGenerator.addMissignValue(OPCION_COMBO, contrato));
        waitLoading();
        waitClick(COMBO_PRODUCTO);
        waitClick(LocatorGenerator.addMissignValue(OPCION_COMBO, producto));
        waitLoading();
        waitClick(BOTON_ACEPTAR_INSTALACION);
    }


    public void registrarDatosClienteporCia(String tipoCliente, String condImpositiva){

        waitClick(EDITAR_DATOS_GENERALES);

        waitFrameAndSwitch(By.tagName("iframe"));

        waitClick(TIPO_DE_CLIENTE);

        waitClick(LocatorGenerator.addMissignValue(CLIENTE_POR_CIA_DE_SEGURO, tipoCliente));

        waitClick(CONDICION_IMPOSITIVA);

        waitClick(By.xpath(String.format("//ul[@id='formDetalleCliente:clienteCondicionImpositiva_items']/li[contains(@data-label, '%s')]", condImpositiva)));

        waitClick(GENERO);

        waitClick(OPCION_M);

        waitClick(GUARDAR_DATOS_GENERALES);

        switchBackToDefaultContent();
        waitLoading();

        // 1165142077

        String nroTelefono = getAttribute("value", TELEFONO_SIN_NORMALIZAR);

        waitClick(AGREGAR_TELEFONO);

        waitFrameAndSwitch(By.xpath("//div[@id='tabs:j_idt129:j_idt175:formTelefonos:telefonos:j_idt195_dlg']//iframe"));
        waitClick(TIPO_DE_CONTACTO);
        waitClick(CELULAR_TIPO_DE_CONTACTO);

        sleepSeconds(1); // NECESARIO
        waitClick(BOTON_TELEFONO);

        switchBackToDefaultContent();

        waitFrameAndSwitch(By.xpath("//div[@id='telefono:j_idt46_dlg']//iframe"));
        waitType(NUMERO_,nroTelefono);
        waitClick(BOTON_NORMALIZAR);
        waitClick(TILDE_VALIDAR);
        switchBackToDefaultContent();
        waitFrameAndSwitch(By.tagName("iframe"));
        waitClick(GUARDAR_TELEFONOS);
    }





    @Step("Tomar tarea Control SDS")
    public void registrarDatosCliente(String tipoCliente, String condImpositiva) {

        waitClick(EDITAR_DATOS_GENERALES);
        waitLoading();
        waitFrameAndSwitch(By.tagName("iframe"));
        waitLoading();
        waitClick(TIPO_DE_CLIENTE);
        waitClick(By.xpath(String.format("//ul[@id='formDetalleCliente:clienteTipoCliente_items']/li[contains(@data-label, '%s')]", tipoCliente)));
        waitClick(CONDICION_IMPOSITIVA);
        waitClick(By.xpath(String.format("//ul[@id='formDetalleCliente:clienteCondicionImpositiva_items']/li[contains(@data-label, '%s')]", condImpositiva)));
        waitClick(GENERO);
        waitClick(OPCION_M);
        waitClick(GUARDAR_DATOS_GENERALES);
        switchBackToDefaultContent();
        waitLoading();
        String nroTelefono = getAttribute("value", TELEFONO_SIN_NORMALIZAR);
        waitClick(AGREGAR_TELEFONO);
        waitLoading();
        waitFrameAndSwitch(By.xpath("//div[@id='tabs:j_idt129:j_idt175:formTelefonos:telefonos:j_idt195_dlg']//iframe"));
        waitLoading();
        waitClick(TIPO_DE_CONTACTO);
        waitLoading();
        waitClick(CELULAR_TIPO_DE_CONTACTO);
        sleepSeconds(2); // NECESARIO
        waitClick(BOTON_TELEFONO);
        waitLoading();
        switchBackToDefaultContent();
        //*[@id="telefono:j_idt46_dlg"]/div[2]/iframe
        waitFrameAndSwitch(By.xpath("//div[@id='telefono:j_idt46_dlg']//iframe"));
        waitType(NUMERO_, nroTelefono);
        waitClick(BOTON_NORMALIZAR);
        waitLoading();
        waitClick(TILDE_VALIDAR);
        switchBackToDefaultContent();
        try {
            waitFrameAndSwitch(By.tagName("iframe"));
        } catch (Exception e) {
        }
        waitClick(GUARDAR_TELEFONOS);
    }


    @Step("Registrar datos del Identificable")
    public void registrarDatosIdentificable(String uso, String color){
        switchBackToDefaultContent();
        waitLoading();
        waitClick(IDENTIFICABLE);
        waitLoading();
        waitClick(EDITAR);
        waitFrameAndSwitch(By.tagName("iframe"));

        waitClick(USO);
        waitClick(By.xpath(String.format("//ul[@id='formDatosSecundarios:uso_items']/li[contains(@data-label, '%s')]",uso)));

        waitClick(COLOR_SELECT);
        waitClick(By.xpath(String.format("//ul[@id='formDatosSecundarios:color_items']/li[contains(@data-label, '%s')]",color)));
        waitClick(GUARDAR_DATO_SECUNDARIO);
        waitLoading();

        switchBackToDefaultContent();
        waitLoading();
    }
    @Step("Registrar datos de GIS")
    public void registrarDatosGIS(){
            waitClick(GIS);
            waitClick(EDITAR_USUARIO_GIS);
            waitLoading();
            waitFrameAndSwitch(By.tagName("iframe"));

            waitClearType(INPUT_LOGIN_USUARIO_GIS,GlobalData.vehiculoDominio+"_"+GlobalData.nroDeDocumento);

            waitClick(GUARDAR_DATOS_USUARIO_GIS);
            waitLoading();
            switchBackToDefaultContent();
            waitLoading();
    }

    @Step("Registrar datos de Facturacion")
    public void registrarDatosFacturacion(){

        waitClick(FACTURACION);
        waitLoading();
        waitClick(EDITAR_FORMA_DE_PAGO);
        waitFrameAndSwitch(By.tagName("iframe"));
        waitClick(CREAR_NUEVO_MEDIO_DE_PAGO);
        try{
            Thread.sleep(2000);
        }catch(Exception e){

        }
        waitLoading();   //*[@id="medioDePago:mediosDePagoEncontrados:j_idt45_dlg"]/div[2]/iframe
//            WebElement frameFormaDePago = buscarFrameDeElemento(TIPO_FORMA_DE_PAGO);
        switchBackToDefaultContent();
        waitFrameAndSwitch(By.xpath("//div[@id='medioDePago:mediosDePagoEncontrados:j_idt45_dlg']//iframe"));  //43

        waitClick(TIPO_FORMA_DE_PAGO);
        try{
            Thread.sleep(2000);
        }catch(Exception e){

        }

        try {
            waitClick(SIN_MEDIO_DE_PAGO);
            waitLoading();
            waitClick(GUARDAR_FORMA_DE_PAGO);
        }catch (Exception e){}

        try {
            waitClick(TIPO_DE_PAGO_DEBITO_DIRECTO);
            waitClearType(INPUT_CBU_PAGO_DIRECTO,"7765859311100051318636");
            waitClick(COMBOBOX_TIPO_CUENTA);
            waitClick(COMBOBOX_TIPO_DE_PAGO);
            waitLoading();
           // waitFrameAndSwitch(By.xpath("//*[@id='medioDePago:mediosDePagoEncontrados:j_idt45_dlg']/div[2]/iframe"));
            waitClick(EDITAR_TIPO_DE_PAGO);
        }catch (Exception e){}

        waitLoading();

        switchBackToDefaultContent();
    }

    @Step("Registrar datos de Operaciones")
    public void registrarDatosOperaciones(){
        waitClick(OPERACIONAL);

        waitClick(AGREGAR_PERSONA_AUTORIZADA);

        waitFrameAndSwitch(By.tagName("iframe"));

        waitType(NOMBRE,"JUAN");
        waitType(APELLIDO,"PEREZ");

        waitClick(TIPO_DE_IDENTIFICABLE);
        waitClick(DNI);

        waitLoading();

        waitClearType(NUMERO_DE_IDENTIFICABLE,"22587411");

        waitClick(RELACION_PERSONAL);

        waitClick(CONDUCTOR);

        waitClick(GUARDAR_PERSONA_AUTORIZADA);

        switchBackToDefaultContent();

        waitClick(TELEFONO_PERSONA_AUTORIZADA);

        waitFrameAndSwitch(By.tagName("iframe"));


        waitClick(AGREGAR_TELEFONO_PERSONA_AUTORIZADA);

        switchBackToDefaultContent();
        waitFrameAndSwitch(1);

        waitClick(TIPO_DE_CONTACTO);
        waitClick(CELULAR_TIPO_DE_CONTACTO);

        sleepSeconds(3); // NECESARIO
        waitClick(BOTON_TELEFONO);
        waitLoading();
        switchBackToDefaultContent();
        sleepSeconds(3);
        waitFrameAndSwitch(By.xpath("//div[@id='telefono:j_idt46_dlg']//iframe"));

        String nroTelefonoPersonaAutorizada = "1560101049";
        waitType(NUMERO_,nroTelefonoPersonaAutorizada);
        sleepSeconds(3);
        waitClick(BOTON_NORMALIZAR);
        sleepSeconds(3);
        waitClick(TILDE_VALIDAR);

        switchBackToDefaultContent();
        waitFrameAndSwitch(1);
        sleepSeconds(3);
        waitClick(GUARDAR_TELEFONOS);

        waitLoading();
        switchBackToDefaultContent();
        waitFrameAndSwitch(By.tagName("iframe"));
        sleepSeconds(3);
        waitClick(VOLVER);


        switchBackToDefaultContent();
    }

    @Step("Imprimir SDS")
    public void imprimirSDS(){
        try{
            waitForElementToAppear(IMPRIMIR_SDS);
            if(waitElementEnabled(IMPRIMIR_SDS, 10)){
                sleepSeconds(2);
                waitClick(IMPRIMIR_SDS);
            }



        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }

    @Step("Cliente tiene firmada electronicamente la SDS")
    public void firmaElectronicaSDS(){
        try{
            waitClick(By.id("//*[@id='accionesEditarFormaDePago:j_idt64']/span"));
        }catch(Exception e){}
        waitClick(BOTON_SI_ACEPTAR_FIRMA);

    }
    @Step("completar campo Random")
    public void completarRandom( String opcion){
        waitClick(COMBO_RANDOM);
        waitClick(LocatorGenerator.addMissignValue(OPCION_COMBO, opcion));
    }

    @Step("completar campo Clasificacion 1")
    public void completarClasificacion1( String opcion){
        waitClick(COMBO_CLAS1);
        waitClick(OPCION_CLAS1);
    }

    @Step("completar campo Clasificacion 2")
    public void completarClasificacion2( String opcion){
        waitClick(COMBO_CLAS2);
        waitClick(LocatorGenerator.addMissignValue(OPCION_CLAS2, opcion));
    }

    @Step("Finalizar control de calidad")
    public void finalizarCCalidad(){
        waitClick(FINALIZAR_C_CALIDAD);
    }

    @Step("Finalizar control SDS")
    public void finalizarSDS(){
        waitLoading();
        waitClick(FINALIZAR);
    }

    @Step("Finalizar tarea de cierre")
    public void finalizarCierre(){
        switchBackToDefaultContent();
        waitClick(FINALIZAR_CIERRE);
    }

    public void upgradeSolucion(String solucion) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        waitLoading();
        waitClick(EDITAR_UPGRADE_SOLUCION);
        waitLoading();
        waitFrameAndSwitch(POPUP_IFRAME);
        waitLoading();
        waitClick(COMBO_UPGRADE_SOLUCION);
       // waitClick(By.id("formVenta:solucion_7"));
        waitClick(LocatorGenerator.addMissignValue(OPCION_COMBO, solucion));
        waitLoading();
        /*
        try {
            Robot robot = new Robot();
            for (int i = 0; i < 6; i++) {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_SUBTRACT);
                robot.keyRelease(KeyEvent.VK_SUBTRACT);
                robot.keyRelease(KeyEvent.VK_CONTROL);
            }
        }
        catch (AWTException e)
        {
            e.printStackTrace();
        }
        */

        //jsExecutor.executeScript("document.body.style.zoom='75%'");
        //waitFrameAndSwitch(POPUP_IFRAME);
        waitClick(BOTON_LISTA_PRECIOS);
        sleepSeconds(2);
        waitClick(BOTON_LISTA_PRECIOS1);
        sleepSeconds(2);
       jsExecutor.executeScript("window.scrollBy(0,1000)");
        waitClick(BOTON_ACUERDO_SPONSOREO);
        sleepSeconds(2);
        waitClick(BOTON_ACUERDO_SPONSOREO121);
        waitClick(GUARDAR_UPGRADE_SOLUCION);

        waitLoading();
        waitClick(VOLVER_TAREAS,2);

    }




}
