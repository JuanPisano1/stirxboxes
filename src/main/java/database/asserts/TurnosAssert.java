package database.asserts;

import database.business.TurnosDB;
import org.json.simple.JSONArray;
import utils.Database;

public class TurnosAssert {
    public static Boolean estadoDeLaUltimaTarea(String nroSolicitud){
        JSONArray result = TurnosDB.obtenerUltimaTarea(nroSolicitud);
        String resultresultadoID = Database.getValue(result,0,"Resultado_ID");
        String resultusuarioResultadoID = Database.getValue(result,0,"UsuarioResultado_ID");

        if(!resultresultadoID.equalsIgnoreCase("6")){
            return false;
        }

        if(!resultusuarioResultadoID.equalsIgnoreCase("-7")){
            return false;
        }

        return true;
    }

    public static Boolean dispositivoAsociadoAlTurno(String vehiculoDominio, String nroSerie){
        JSONArray result = TurnosDB.obtenerNroSerieGPS(vehiculoDominio);

        String resultNroSerie = Database.getValue(result,0,"NroSerie");
        String resultNoGestiona = Database.getValue(result,0,"NoGestiona");
        String resultFechaInstVLU = Database.getValue(result,0,"FechaInstVLU");

        if(!nroSerie.equalsIgnoreCase(resultNroSerie)){
            return false;
        }

        if(!resultNoGestiona.equalsIgnoreCase("1")){
            return false;
        }

        if(resultFechaInstVLU.equalsIgnoreCase("NULL") || resultFechaInstVLU.equalsIgnoreCase("")){
            return false;
        }

        return true;
    }
}
