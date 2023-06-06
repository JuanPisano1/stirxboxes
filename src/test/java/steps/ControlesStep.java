package steps;

import database.asserts.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LandingPage;
import utils.GlobalData;

import static utils.GlobalData.*;


@Listeners({ listeners.TestListener.class })

public class ControlesStep extends BaseStep {
    @And("se verifica en Calipso que el equipo {string} haya quedado asociado al vehiculo")
    public void seVerificaEnCalipsoEquipoYVehiculo(String tipoDeDispositivo) throws InterruptedException {
        switch (tipoDeDispositivo) {
            case "GPS":
                Assert.assertTrue(CalipsoAssert.verificarEquipoVehiculo(nroDeDocumento, "ID" + nroSerieGPS, vehiculoDominio));
                break;
            case "VLU":
                Assert.assertTrue(CalipsoAssert.verificarEquipoVehiculo(nroDeDocumento, nroSerieVLU, vehiculoDominio));
                break;
            default:
                throw new IllegalStateException("Dispositivo no encontrado: " + tipoDeDispositivo + "\nDispositivos disponibles: 'GPS', 'VLU'");
        }
    }

    @And("se verifica en Calipso el plan {string} y el servicio {string}. Company ID {string}")
    public void seVerificaenCalipsoPlanesYServicios(String plan, String servicio, String companyID) {
        Assert.assertTrue(CalipsoAssert.verificarPlanesAsociadosAlVehiculo(nroDeDocumento, vehiculoDominio, plan, servicio, companyID));
    }

    @And("se verifican en Calipso los datos del vehiculo. Company ID {string}")
    public void seVerificaEnCalipsoElAltaDelClienteElVehiculoElEquipoPorLaEmpresaConLosPlanes(String companyID) {
        Assert.assertTrue(CalipsoAssert.verificarDatosDelVehiculo(nroDeDocumento, vehiculoDominio, companyID, chasis, motor));
    }

    @And("se verifica en GIS la entidad del vehículo y la entidad del equipo")
    public void seVerificaEnGISLaEntidadDelVehículoYLaEntidadDelEquipo() {
        Assert.assertTrue(GisAssert.existeVehiculo(vehiculoDominio));
        Assert.assertTrue(GisAssert.existeEquipo(nroSerieGPS));
    }

    @And("se verifica en Vehiculos el alta del cliente: Company ID {string}, Nombre y apellido del cliente {string}, {string}")
    public void seVerificaEnLaBaseDeVehiculosAltaDelClienteyVehiculo(String companyID, String nombreCliente, String apellidoCliente) {
        Assert.assertTrue(VehiculosAssert.ciaSegurosExisteCliente(vehiculoDominio, nroDeDocumento, companyID, apellidoCliente, nombreCliente));
    }

    @And("se verifica en Vehiculos la relación del vehiculo y el equipo {string}")
    public void seVerificaEnLaBaseDeVehiculosAltaDelClienteyVehiculo(String tipoDispositivo) {
        Assert.assertTrue(VehiculosAssert.ciaSegurosExisteRelacionEquipoVehiculo(vehiculoDominio, tipoDispositivo));
    }

    @And("se verifica en Plataforma el consumo de la partida instalada. Dispositivo {string}")
    public void seVerificaEnPlataformaElConsumoDeLaPartidaInstalada(String tipoDispositivo) {
        switch (tipoDispositivo) {
            case "GPS":
                Assert.assertTrue(PlataformaAssert.seDescontoStock(tipoDispositivo, nroSerieGPS));
                break;
            case "VLU":
                Assert.assertTrue(PlataformaAssert.seDescontoStock(tipoDispositivo, nroSerieVLU));
                break;
            default:

        }
    }

    @And("se verifica en ATyC la generacion de la SDS y el envio por mail")
    public void seVerificaEnATyCLaGeneracionDeLaSDSYElEnvioPorMail() {
        Assert.assertTrue(AtycAssert.verificarATyC(nroSolicitud));
    }

    @And("se verifica en Turnos el cierre de la solicitud")
    public void seVerificaEnTurnosElCierreDeLaSolicitud() {
        Assert.assertTrue(TurnosAssert.estadoDeLaUltimaTarea(nroSolicitud));
    }


    @And("se verifica en Boxes el estado {string} de la tarea {string}")
    public void seVerificaEnBoxesElEstadoDeLaTarea(String statusTarea, String nombreTarea) {
        Assert.assertTrue(BoxesAssert.estadoUltimaTarea(nroSolicitud, nombreTarea, statusTarea));
    }

    @Then("se prueban asserts con Dominio: {string} Solicitud ID: {string} DNI: {string} GPS: {string} VLU: {string}")
    public void seVerificanAssertsConLosDatosDominioSolicitudIDDNI(String dominio, String nroSolicitud, String dni, String gps, String vlu) {
        GlobalData.vehiculoDominio = dominio;
        GlobalData.nroSolicitud = nroSolicitud;
        GlobalData.nroDeDocumento = dni;
        GlobalData.chasis = "CHA_" + nroSolicitud;
        GlobalData.motor = "MOT_" + nroSolicitud;
        GlobalData.nroSerieGPS = gps;
        GlobalData.nroSerieVLU = vlu;
    }

    @Then("se imprimen los datos de la prueba y se espera {int} segundos")
    public void seEsperanSegundos(int segundos) throws InterruptedException {
        LandingPage landingPage = new LandingPage(Hooks.getRunnerDriver());
        landingPage.visitWaitPage("Ejecutando asserts en base de datos", 30);
        System.out.println("Nro. Documento: "+nroDeDocumento);
        System.out.println("Nro. Serie GPS: "+nroSerieGPS);
        System.out.println("Nro. Serie VLU: "+nroSerieVLU);
        System.out.println("Dominio: "+vehiculoDominio);
        System.out.println("Nro. Solicitud: "+nroSolicitud);
        System.out.println("Nro. Chasis: "+chasis);
        System.out.println("Nro. Motor: "+motor);


        Thread.sleep(segundos*1000);
    }
}