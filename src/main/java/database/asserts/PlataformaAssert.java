package database.asserts;

import database.business.PlataformaDB;
import org.json.simple.JSONArray;
import utils.Database;

public class PlataformaAssert {

    public static Boolean seDescontoStock(String tipoDeDispositivo, String nroSerie ){

        JSONArray result = PlataformaDB.obtenerDispositivo(tipoDeDispositivo, nroSerie);

        String stock = Database.getValue(result,0,"sdpp_stock_act");
        String partClasif = Database.getValue(result,0,"part_clasif_1");


        if(!stock.startsWith("0")){
            return false;
        }

        if(!partClasif.equalsIgnoreCase("INS")){
            return false;
        }

        return true;
    }


}
