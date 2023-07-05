package database.asserts;

import database.business.BoxesDB;
import org.json.simple.JSONArray;
import utils.Database;

public class BoxesAssert {

    public static Boolean estadoUltimaTarea(String nroSolicitud, String nombreTarea, String statusTarea){
        String tramiteID = BoxesDB.obtenerTramiteID(nroSolicitud);
        String instanciaID = BoxesDB.obtenerInstanciaProceso(tramiteID);

        JSONArray result = BoxesDB.obtenerEstadoUltimaTarea(instanciaID, nombreTarea, statusTarea);

        String count = Database.getValue(result,0,"count");
        if(count.startsWith("0")){
            return false;
        }

        return true;
    }

}
