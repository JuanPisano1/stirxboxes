package database.business;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import utils.Database;

import java.io.IOException;
import java.sql.SQLException;

public class GisDB {

    public static JSONArray obtenerEntidadVehiculo(String vehiculoDominio){
        String query =
                "SELECT TOP 1 count(1) as count"
                        + " FROM Entidad e"
                        + " WHERE e.nombre = '<VEHICULO_DOMINIO>'";

        query = query.replace("<VEHICULO_DOMINIO>", vehiculoDominio);

        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("Lojack", query);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static JSONArray obtenerEntidadEquipo(String nroSerie){

        String query =
                "SELECT TOP 1 count(1) as count"
                        + " FROM Equipo e"
                        + " WHERE e.nroSerie = 'ID<NRO_SERIE>'";

        query = query.replace("<NRO_SERIE>", nroSerie);

        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("Lojack", query);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static JSONArray obtenerPosicion(String nroSerie){

        String query =
                "SELECT TOP 1 count(1) as count"
                        + " FROM Posicion p"
                        + " WHERE p.idEquipo IN (SELECT e.idEquipo"
                        + " FROM Equipo e"
                        + " WHERE e.nroSerie = 'ID<NRO_SERIE>')";

        query = query.replace("<NRO_SERIE>", nroSerie);

        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("Lojack", query);

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
