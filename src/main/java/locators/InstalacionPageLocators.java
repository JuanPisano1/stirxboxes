package locators;

import org.openqa.selenium.By;

public interface InstalacionPageLocators {
    By INSTALACION_BTN_GENERICO=By.xpath("//span[@class='ui-button-text ui-c' and contains(text(),'%s')]");
    By INSTALATION_DOMAIN_LABAL=By.id("j_idt70:j_idt78");
    By POPUP_IFRAME=By.tagName("iframe");
    By COMBO_EMPRESA=By.className("ui-selectonemenu-trigger");
    By EMPRESA_GPS = By.xpath("//li[@data-label='%s']");
    By CAMPO_SERIAL_GPS=By.id("formNumeroSerie:j_idt30");
    By BOTON_VALIDAR_GPS=By.id("formUbicacion:botonValidar");
    By BOTON_VALIDAR_VLU=By.xpath("//button/span[contains(text(),'Validar VLU')]");

    By CHECKBOX_SERVICIO_VLU=By.xpath("//li[contains(text(),'Recupero VLU')]/div[contains(@class,'ui-chkbox')]");
    By BOTON_CONFIRMA_SIN_VALIDAR_EQUIPO=By.id("j_idt29:j_idt34");
    By BOTON_FINALIZAR_INSTALACION=By.id("botones:j_idt372");

    By BOTON_EDITAR_KILOMETRAJE= By.xpath("//button/span[text()='Editar']");
    By INPUT_KILOMETRAJE=By.id("edicionKilometraje:kilometraje");
    By GUARDAR_KILOMETRAJE=By.id("accionesEditarKilometraje:j_idt37");
}
