package pages;

import database.business.BoxesDB;
import database.business.Servicio;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Step;
import locators.LandingPageLocators;
import org.openqa.selenium.WebDriver;
import utils.EnvironmentConsumer;
import utils.GlobalData;
import utils.WebBase;

import java.util.Random;

import static utils.GlobalData.*;
import static utils.GlobalData.motor;

public class LandingPage extends BasePage implements LandingPageLocators {
    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ingresa a la url de Boxes")
    public void visitBoxes(){
        visit(URL_BOXES);
    }

    @Step("Ingresa a la pagina de espera")
    public void visitWaitPage(String message){
        String url = URL_WAIT_PAGE+"?message="+message;
        visit(url);
    }

    @Step("Ingresa a la pagina de espera")
    public void visitWaitPage(String message, int countdown){
        String url = URL_WAIT_PAGE+"?message="+message+"&countdown="+String.valueOf(countdown);
        visit(url);
    }

    public boolean isLandingPage(){
        return isDisplayed(LANDING_BANNER);
    }
    @Step("Usuario selecciona ingresar")
    public void goToLogin(){
        click(HEAD_LOGIN_ICON);
    }

    @Step("Generar solicitud")
    public void generarSolicitud(Servicio servicio) {
        Random aleatorio = new Random(System.currentTimeMillis());
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String dominioPrefijo="ZZ";
        String dominioSufijo= String.valueOf(alphabet.charAt(aleatorio.nextInt(alphabet.length())))+String.valueOf(alphabet.charAt(aleatorio.nextInt(alphabet.length())));
        String dominioNumeros = String.valueOf(aleatorio.nextInt(899)+100);

        String dni = String.valueOf(aleatorio.nextInt(43000000)+7000000);
        String dominio = dominioPrefijo+dominioNumeros+dominioSufijo;
        int nroSolicitud = BoxesDB.generarSolicitud(servicio, dni, dominio);

        GlobalData.vehiculoDominio = dominio;
        GlobalData.nroSolicitud = String.valueOf(nroSolicitud);
        GlobalData.nroDeDocumento = dni;
        GlobalData.chasis = "CHA_"+nroSolicitud;
        GlobalData.motor = "MOT_"+nroSolicitud;


        System.out.println("Nro. Documento: "+nroDeDocumento);
        System.out.println("Dominio: "+vehiculoDominio);
        System.out.println("Nro. Solicitud: "+GlobalData.nroSolicitud);
        System.out.println("Nro. Chasis: "+chasis);
        System.out.println("Nro. Motor: "+motor);
    }
}
