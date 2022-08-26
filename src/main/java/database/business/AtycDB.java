package database.business;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import utils.Database;

import java.io.IOException;
import java.sql.SQLException;

public class AtycDB {
    public static JSONArray obtenerDocumentoAsociado(String idTramite){
        String query =
                "SELECT documento_nombre\n" +
                        "FROM Documentos_Generados\n" +
                        "WHERE tramite_id = <NRO_TRAMITE>";

        query = query.replace("<NRO_TRAMITE>", idTramite);

        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("ATyC", query);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static JSONArray obtenerAceptacionServicioAsociada(String idTramite){
        String query =
                "SELECT count(1) as count \n" +
                        " FROM aceptaciones \n" +
                        " WHERE tramite_id = <NRO_TRAMITE>";

        query = query.replace("<NRO_TRAMITE>", idTramite);

        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("ATyC", query);

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
