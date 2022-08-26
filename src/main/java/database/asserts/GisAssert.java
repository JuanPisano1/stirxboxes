package database.asserts;

import database.business.GisDB;
import org.json.simple.JSONArray;
import utils.Database;

public class GisAssert {

    public static Boolean existeVehiculo(String vehiculoDominio){
        JSONArray result = GisDB.obtenerEntidadVehiculo(vehiculoDominio);

        String count = Database.getValue(result,0,"count");
        if(count.equalsIgnoreCase("0")){
            return false;
        }

        return true;
    }

    public static Boolean existeEquipo(String nroSerie){
        JSONArray result = GisDB.obtenerEntidadEquipo(nroSerie);

        String count = Database.getValue(result,0,"count");
        if(!count.equalsIgnoreCase("0")){
            return false;
        }

        return true;
    }

    public static Boolean existePosicionParaEquipo(String nroSerie){
        JSONArray result = GisDB.obtenerPosicion(nroSerie);

        String count = Database.getValue(result,0,"count");
        if(!count.equalsIgnoreCase("0")){
            return false;
        }

        return true;
    }

}
