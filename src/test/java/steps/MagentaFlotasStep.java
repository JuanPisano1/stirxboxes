package steps;

import io.cucumber.java.en.And;
import org.testng.Assert;
import webservice.api.application.MagentaFlotas;
import webservice.pojo.*;

import static utils.GlobalData.*;


public class MagentaFlotasStep {

    @And("se verifica en Magenta Flotas el vehículo")
    public void validarVehiculo(){
        MagentaFlotas magentaFlotas = new MagentaFlotas();

        LoginMagenta loginMagenta = magentaFlotas.login();
        VehiculoMagentaFlotas[] vehiculoMagenta = magentaFlotas.buscarVehiculo(loginMagenta.getAccess_token(), vehiculoDominio);

        VehiculoInfoMagentaFlotas info = vehiculoMagenta[0].getInfo();
        Assert.assertEquals(info.getChasis(),chasis);
        Assert.assertEquals(info.getEngine(),motor);
        Assert.assertTrue(vehiculoMagenta[0].getThings()[0].contains("gps"));
    }
}
