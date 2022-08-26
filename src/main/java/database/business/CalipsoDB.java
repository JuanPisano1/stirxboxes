package database.business;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import utils.Database;

import java.io.IOException;
import java.sql.SQLException;

public class CalipsoDB {

    public static String obtenerCustomerID(String nroDocumento){
        String query =
                "SELECT TOP 1 customer_id"
                + " FROM dbo.b_customer c"
                + " where c.customer_tax_id_number = '<NRO_DOCUMENTO>'";

        query = query.replace("<NRO_DOCUMENTO>", nroDocumento);

        String result = null;
        try {
            JSONArray rs = Database.executeQueryJSON("calipso_intdev01", query);
            result = Database.getValue(rs,0,"customer_id");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static JSONArray obtenerEquipoAsociadoAlVehiculo(String customerId, String inventorySerial, String vehiculoDominio){
        String query =
                " SELECT DISTINCT"
                        +" v.place_id, "
                        +" v.inventory_id, "
                        +" v.inventory_serial,"
                        +" v.inventory_status"
                        +" FROM Integration.vwInstalablesPorCliente v"
                        +" WHERE v.customer_id = '<CUSTOMER_ID>'"
                        +" AND v.inventory_serial = '<INVENTORY_SERIAL>'"
                        +" AND exists (select *"
                        +" from dbo.i_lj_place pl"
                        +" where pl.place_id = v.place_id"
                        +" and pl.place_vehicle_plate = '<VEHICULO_DOMINIO>')";

        query = query.replace("<CUSTOMER_ID>", customerId);
        query = query.replace("<INVENTORY_SERIAL>", inventorySerial);
        query = query.replace("<VEHICULO_DOMINIO>", vehiculoDominio);

        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("calipso_intdev01", query);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static JSONArray obtenerPlanesAsociadosAlVehiculo(String customerId, String vehiculoDominio, String plan, String servicio, String companyID){
        String query =
                "SELECT distinct"
                        +" v.company_id,"
                        +" v.account_status,"
                        +" v.service_id, "
                        +" v.account_service_status,"
                        +" i.inventory_serial, "
                        +" v.plan_id plan_id, "
                        +" v.account_plan_status "
                        +" FROM Integration.vwServiciosPorCliente v"
                        +" JOIN dbo.i_lj_place pl on pl.place_id = v.place_id"
                        +" LEFT JOIN dbo.b_inventory i on i.inventory_id = v.inventory_id"
                        +" WHERE v.customer_id = '<CUSTOMER_ID>'"
                        +" and v.plan_id IN ('<PLAN_ID>')"
                        +" and v.service_id IN ('<SERVICIO_ID>')"
                        +" and pl.place_vehicle_plate = '<VEHICULO_DOMINIO>'"
                        +" and v.company_id = '<COMPANY_ID>'";

        query = query.replace("<CUSTOMER_ID>", customerId);
        query = query.replace("<PLAN_ID>", plan);
        query = query.replace("<SERVICIO_ID>", servicio);
        query = query.replace("<VEHICULO_DOMINIO>", vehiculoDominio);
        query = query.replace("<COMPANY_ID>", companyID);


        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("calipso_intdev01", query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static JSONArray obtenerVehiculo(String customerId, String vehiculoDominio, String companyID){
        String query =
                "SELECT i.place_id,"
                        +" i.place_vehicle_chassis,"
                        +" i.place_vehicle_engine,"
                        +" i.place_vehicle_year,"
                        +" i.place_vehicle_brand,"
                        +" i.place_vehicle_model_id,"
                        +" i.place_value,"
                        +" i.place_vehicle_type_id,"
                        +" i.place_vehicle_fuel_type_id,"
                        +" i.place_company_value"
                        +" FROM Integration.vwIdentificablesPorCliente i"
                        +" WHERE i.customer_id = '<CUSTOMER_ID>'"
                        +" AND i.company_id = '<COMPANY_ID>'"
                        +" AND i.place_vehicle_plate = '<VEHICULO_DOMINIO>'";

        query = query.replace("<CUSTOMER_ID>", customerId);
        query = query.replace("<VEHICULO_DOMINIO>", vehiculoDominio);
        query = query.replace("<COMPANY_ID>", companyID);

        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("calipso_intdev01", query);

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
