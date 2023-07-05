package database.asserts;

import database.business.CalipsoDB;
import database.business.VehiculosDB;
import org.json.simple.JSONArray;
import utils.Database;

public class VehiculosAssert {

    public static Boolean ciaSegurosExisteCliente(String vehiculoDominio, String nroDocumento, String companyID, String apellidoCliente, String nombreCliente){
        String customerId = CalipsoDB.obtenerCustomerID(nroDocumento);

        JSONArray result3 = CalipsoDB.obtenerVehiculo(customerId, vehiculoDominio, companyID);

        String result3PlaceID = Database.getValue(result3,0,"place_id");
        String result3Chasis = Database.getValue(result3,0,"place_vehicle_chassis");
        String result3Motor = Database.getValue(result3,0,"place_vehicle_engine");

        JSONArray result = VehiculosDB.obtenerRelacionVehiculoCliente(vehiculoDominio, nroDocumento);

        String resultChasis = Database.getValue(result,0,"chasis");
        String resultMotor = Database.getValue(result,0,"motor");
        String resultApellido = Database.getValue(result,0,"apellido");
        String resultNombre = Database.getValue(result,0,"nombre");
        String resultIdTipoDocumento = Database.getValue(result,0,"idtipodocumento");

        if(resultChasis.equalsIgnoreCase("NULL") || resultChasis.equalsIgnoreCase("")){
            return false;
        }

        if(!resultChasis.equalsIgnoreCase(result3Chasis)){
            return false;
        }
        if(!resultMotor.equalsIgnoreCase(result3Motor)){
            return false;
        }
        if(!resultApellido.equalsIgnoreCase(apellidoCliente)){
            return false;
        }

        if(!resultNombre.equalsIgnoreCase(nombreCliente)){
            return false;
        }

        if(!resultIdTipoDocumento.equalsIgnoreCase("1")){
            return false;
        }

        return true;
    }
    public static Boolean ciaSegurosExisteRelacionEquipoVehiculo(String vehiculoDominio, String tipoDispositivo){
        JSONArray result = VehiculosDB.obtenerRelacionVehiculoEquipo(vehiculoDominio, tipoDispositivo);

        String count = Database.getValue(result,0,"count");
        if(count.equalsIgnoreCase("0")){
            return false;
        }

        return true;
    }
    public static Boolean ciaSegurosExisteRelacionVehiculoCliente(String vehiculoDominio, String nroDocumento, String companyID, String nroIdentificacion, String apellidoCliente, String nombreCliente){
        String customerId = CalipsoDB.obtenerCustomerID(nroDocumento);

        JSONArray result3 = CalipsoDB.obtenerVehiculo(customerId, vehiculoDominio, companyID);

        String result3PlaceID = Database.getValue(result3,0,"place_id");
        String result3Chasis = Database.getValue(result3,0,"place_vehicle_chassis");
        String result3Motor = Database.getValue(result3,0,"place_vehicle_engine");

        JSONArray result = VehiculosDB.obtenerRelacionVehiculoCliente(vehiculoDominio, nroDocumento);

        String resultChasis = Database.getValue(result,0,"chasis");
        String resultMotor = Database.getValue(result,0,"motor");
        String resultApellido = Database.getValue(result,0,"apellido");
        String resultNombre = Database.getValue(result,0,"nombre");
        String resultIdTipoDocumento = Database.getValue(result,0,"idTipoDocumento");

        if(resultChasis.equalsIgnoreCase("NULL") || resultChasis.equalsIgnoreCase("")){
            return false;
        }

        if(!resultChasis.equalsIgnoreCase(result3Chasis)){
            return false;
        }
        if(!resultMotor.equalsIgnoreCase(result3Motor)){
            return false;
        }
        if(!resultApellido.equalsIgnoreCase(apellidoCliente)){
            return false;
        }

        if(!resultNombre.equalsIgnoreCase(nombreCliente)){
            return false;
        }

        if(!resultIdTipoDocumento.equalsIgnoreCase("1")){
            return false;
        }

        return true;
    }


    public static Boolean ciaSegurosExisteRelacionVehiculoPersonaAutorizada(String vehiculoDominio, String nroDocumento){
        JSONArray result = VehiculosDB.obtenerRelacionVehiculoPersonaAutorizada(vehiculoDominio, nroDocumento);
        String resultChasis = Database.getValue(result,0,"chasis");

        if(resultChasis.equalsIgnoreCase("NULL") || resultChasis.equalsIgnoreCase("")){
            return false;
        }

        return true;
    }


}
