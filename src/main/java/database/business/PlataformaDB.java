package database.business;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import utils.Database;

import java.io.IOException;
import java.sql.SQLException;

public class PlataformaDB {

    public static JSONArray obtenerDispositivo(String tipoDeDispositivo, String nroSerie){
        switch (tipoDeDispositivo){
            case "GPS":
                return obtenerGPS(nroSerie);
            case "VLU":
                return obtenerVLU(nroSerie);
            default:
                throw new IllegalStateException("No se puede obtener el stock. Dispositivo no encontrado: " + tipoDeDispositivo+ "\nDispositivos disponibles: 'GPS', 'VLU'");
        }
    }

    private static JSONArray obtenerGPS(String nroSerieGPS){
        String query =
//            "SELECT count(1) as count"
                "SELECT p.part_clasif_1 part_clasif_1, isnull(sdpp.sdpp_stock_act,0) sdpp_stock_act"
                        +" FROM dbOleiros.dbo.stoc_part p"
                        +" LEFT join dbOleiros.dbo.stoc_sdpp sdpp on sdpp.sdpp_partida=p.part_partida"
                        +" WHERE p.part_partida_emp='ID<NRO_SERIE_GPS>'";


        query = query.replace("<NRO_SERIE_GPS>", nroSerieGPS);

        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("dbOleiros", query);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static JSONArray obtenerVLU(String nroSerieVLU){
        String query =
                "SELECT p.part_clasif_1 part_clasif_1, isnull(sdpp.sdpp_stock_act,0) sdpp_stock_act"
               // "SELECT count(1) as count"
                        + " from dbo.stoc_part p"
                        + "   left join dbo.stoc_sdpp sdpp on sdpp.sdpp_partida = p.part_partida"
                        + "  where p.part_partida_emp = '<NRO_SERIE_VLU>' and sdpp.sdpp_stock_act not like 0";


        query = query.replace("<NRO_SERIE_VLU>", nroSerieVLU);

        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("dbWorld_Car_Security", query);

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
