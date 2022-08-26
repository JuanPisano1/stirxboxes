package database.asserts;

import database.business.AtycDB;
import database.business.BoxesDB;
import org.json.simple.JSONArray;
import utils.Database;

public class AtycAssert {

    public static Boolean verificarATyC(String nroSolicitud){
        String tramiteID = BoxesDB.obtenerTramiteID(nroSolicitud);

        JSONArray resultDocumentoAsociado = AtycDB.obtenerDocumentoAsociado(tramiteID);
        String documentoNombre = Database.getValue(resultDocumentoAsociado,0,"documento_nombre");
        if(documentoNombre.equalsIgnoreCase("NULL") || documentoNombre.equalsIgnoreCase("")){
            return false;
        }

        JSONArray resultAceptacionServicio = AtycDB.obtenerAceptacionServicioAsociada(tramiteID);
        String count = Database.getValue(resultAceptacionServicio,0,"count");
        if(count.equalsIgnoreCase("0")){
            return false;
        }

        return true;
    }

}
