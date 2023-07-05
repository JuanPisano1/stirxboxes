package steps;

import io.cucumber.java.en.And;
import org.testng.Assert;
import webservice.api.application.Magenta;
import webservice.pojo.ClienteMagenta;
import webservice.pojo.VehiclePremium;
import webservice.pojo.Services;
import webservice.pojo.LoginMagenta;

import static utils.GlobalData.nroDeDocumento;


public class MagentaStep {

    @And("se verifica en Magenta el servicio. Nombre {string} y servicio activo")
    public void validateAccount(String nombre){
        Magenta magentaWS = new Magenta();
        LoginMagenta loginMagenta = magentaWS.login();
        ClienteMagenta[] clienteMagenta = magentaWS.validateAccount(loginMagenta.getAccess_token(), nroDeDocumento);
        Services services = clienteMagenta[0].getServices();
        VehiclePremium vehiclePremium = services.getVehiclePremium();

        Assert.assertEquals(clienteMagenta[0].getName(),nombre);
        Assert.assertEquals(vehiclePremium.getEnabled(),true);
    }
}
