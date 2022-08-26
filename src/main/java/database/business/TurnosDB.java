package database.business;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import utils.Database;

import java.io.IOException;
import java.sql.SQLException;

public class TurnosDB {

    public static JSONArray obtenerNroSerieGPS(String vehiculoDominio){
        String query =
                "SELECT TOP 1 s.solicitud_id, s.NroSerie, s.NoGestiona, s.FechaInstVLU"
                        +" FROM Prospect p"
                        +" INNER JOIN Solicitudes s ON"
                        +" s.Solicitud_ID = p.Solicitud_ID"
                        +" WHERE VehiculoDominio = '<VEHICULO_DOMINIO>'";

        query = query.replace("<VEHICULO_DOMINIO>",vehiculoDominio);

        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("Ventas", query);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static JSONArray obtenerUltimaTarea(String solicitudID){
        String query =
                "SELECT TOP 1 Resultado_ID, UsuarioResultado_ID"
                        + " FROM tareasporsolicitud"
                        + " WHERE solicitud_id = <SOLICITUD_ID>"
                        + " ORDER BY orden DESC";

        query = query.replace("<SOLICITUD_ID>", solicitudID);

        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("Ventas", query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

}
