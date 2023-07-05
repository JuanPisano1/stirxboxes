package database.asserts;

import database.business.CalipsoDB;
import org.json.simple.JSONArray;
import utils.Database;

import java.util.Arrays;
import java.util.List;

public class CalipsoAssert {

    public static Boolean verificarEquipoVehiculo(String nroDocumento, String nroSerie, String vehiculoDominio){
        // Controlar cliente - Obtener customer ID
        String customerId = CalipsoDB.obtenerCustomerID(nroDocumento);

        // Controlar que el equipo quedó asociado al vehiculo
        JSONArray resultEquipoVehiculo = CalipsoDB.obtenerEquipoAsociadoAlVehiculo(customerId, nroSerie, vehiculoDominio);

        String resultPlaceID = Database.getValue(resultEquipoVehiculo,0,"place_id");
        String resultInventoryID = Database.getValue(resultEquipoVehiculo,0,"inventory_id");
        String resultInventorySerial = Database.getValue(resultEquipoVehiculo,0,"inventory_serial");
        String resultInventoryStatus = Database.getValue(resultEquipoVehiculo,0,"inventory_status");


        if(resultPlaceID.isEmpty()){
            return false;
        }
        if(resultInventoryID.isEmpty()){
            return false;
        }
        if(!resultInventoryStatus.equalsIgnoreCase("Installed")){
            return false;
        }
        if(!resultInventorySerial.contains(nroSerie)){
            return false;
        }

        return true;
    }

    public static Boolean verificarPlanesAsociadosAlVehiculo(String nroDocumento, String vehiculoDominio, String plan, String servicio, String companyID){
        // Controlar cliente - Obtener customer ID
        String customerId = CalipsoDB.obtenerCustomerID(nroDocumento);

        // Controlar los planes asociados al vehículo
        JSONArray resultPlanesVehiculo= CalipsoDB.obtenerPlanesAsociadosAlVehiculo(customerId,vehiculoDominio, plan, servicio, companyID);

        String planObtenido = Database.getValue(resultPlanesVehiculo,0,"plan_id");
        String servicioObtenido = Database.getValue(resultPlanesVehiculo,0,"service_id");

        if(!planObtenido.equals(plan)){
            return false;
        }

        if(!servicioObtenido.equals(servicio)){
            return false;
        }

        return true;
    }

    public static Boolean verificarDatosDelVehiculo(String nroDocumento, String vehiculoDominio, String companyID, String chasis, String motor){
        // Controlar cliente - Obtener customer ID
        String customerId = CalipsoDB.obtenerCustomerID(nroDocumento);

        // Controlar los datos del vehículo
        JSONArray resultVehiculo = CalipsoDB.obtenerVehiculo(customerId, vehiculoDominio, companyID);

        String resultChasis = Database.getValue(resultVehiculo,0,"place_vehicle_chassis");
        String resultMotor = Database.getValue(resultVehiculo,0,"place_vehicle_engine");

        if(!chasis.equalsIgnoreCase(resultChasis) || !motor.equalsIgnoreCase(resultMotor)){
            return false;
        }

        return true;
    }


    public static Boolean verificarTipoDeCliente(String nroDocumento, String vehiculoDominio, String companyID, String chasis, String motor){
        // Controlar cliente - Obtener customer ID
        String customerId = CalipsoDB.obtenerCustomerID(nroDocumento);

        // Controlar los datos del vehículo
        JSONArray resultVehiculo = CalipsoDB.obtenerVehiculo(customerId, vehiculoDominio, companyID);

        String resultChasis = Database.getValue(resultVehiculo,0,"place_vehicle_chassis");
        String resultMotor = Database.getValue(resultVehiculo,0,"place_vehicle_engine");

        if(!chasis.equalsIgnoreCase(resultChasis) || !motor.equalsIgnoreCase(resultMotor)){
            return false;
        }

        return true;
    }

}
