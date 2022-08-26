package database.business;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import utils.Database;

import java.io.IOException;
import java.sql.SQLException;

public class VehiculosDB {

    public static JSONArray obtenerRelacionVehiculoCliente(String vehiculoDominio, String nroIdentificacion){
        String query =
            "SELECT chasis, motor, apellido, nombre, idtipodocumento"
            +" FROM vehiculosPorCliente vpc"
            +" JOIN vehiculos v ON vpc.idVehiculo = v.idVehiculo"
            +" JOIN clientes c ON vpc.idCliente = c.idCliente"
            +" WHERE v.dominio = '<VEHICULO_DOMINIO>'"
            +" AND c.nroIdentificacion = '<NUMERO_IDENTIFICACION>'";

        query = query.replace("<VEHICULO_DOMINIO>", vehiculoDominio);
        query = query.replace("<NUMERO_IDENTIFICACION>", nroIdentificacion);

        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("Lojack_vehiculos", query);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static JSONArray obtenerDatosVehiculoCompaniaSeguros(String vehiculoDominio, String nroIdentificacion, String companyID){
        String query =
                "SELECT  chasis, motor, apellido, nombre, idtipodocumento"
                        +" FROM vehiculosPorCliente vpc"
                        +" JOIN vehiculos v ON vpc.idVehiculo = v.idVehiculo"
                        +" JOIN clientes c ON vpc.idCliente = c.idCliente"
                        +" JOIN detallePolizas dp ON vpc.idDetallePoliza = dp.idDetallePoliza"
                        +" JOIN ciasDeSeguros cds ON dp.idCiaSeguro = cds.idCiaSeguro"
                        +" WHERE v.dominio = '<VEHICULO_DOMINIO>'"
                        +" AND c.nroIdentificacion = '<NUMERO_IDENTIFICACION>'"
                        +" AND cds.nombre = '<COMPANY_ID>'";

        query = query.replace("<VEHICULO_DOMINIO>", vehiculoDominio);
        query = query.replace("<NUMERO_IDENTIFICACION>", nroIdentificacion);
        query = query.replace("<COMPANY_ID>", companyID);

        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("Lojack_vehiculos", query);

//            String chasis  = Database.getValue(result,0,"chasis");
//            String motor  = Database.getValue(result,0,"motor");
//            String apellido  = Database.getValue(result,0,"apellido");
//            String nombre  = Database.getValue(result,0,"nombre");
//            String idTipoDocumento  = Database.getValue(result,0,"idTipoDocumento");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static JSONArray obtenerRelacionVehiculoEquipo(String vehiculoDominio, String tipoDispositivo){
        String query =
//        "SELECT serie, codigo, modelo, me.empresa"
                "SELECT count(1) as count"
                        +" FROM equipos e"
                        +" JOIN equiposPorVehiculo epv ON e.idEquipo = epv.idEquipo"
                        +" JOIN vehiculos v ON epv.idVehiculo = v.idVehiculo"
                        +" JOIN modelosEquipos me ON e.idModeloEquipo = me.idModeloEquipo"
                        +" JOIN tipoDispositivo td ON me.idTipoDispositivo = td.idTipoDispositivo"
                        +" WHERE v.dominio = '<VEHICULO_DOMINIO>'"
                        +" AND td.tipoDispositivo = '<TIPO_DISPOSITIVO>'";

        query = query.replace("<VEHICULO_DOMINIO>", vehiculoDominio);
        query = query.replace("<TIPO_DISPOSITIVO>", tipoDispositivo);

        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("Lojack_vehiculos", query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static JSONArray obtenerRelacionVehiculoPersonaAutorizada(String vehiculoDominio, String nroDocumento){
        String query =
            "SELECT *"
            +" FROM vehiculos v"
            +" JOIN personasAutorizadasPorVehiculo papv ON v.idVehiculo = papv.idVehiculo"
            +" JOIN personasAutorizadas pa ON papv.idPersonaAutorizada = pa.idPersonaAutorizada"
            +" WHERE v.dominio = '<VEHICULO_DOMINIO>'"
            +" AND pa.nroDocumento = 'NRO_DOCUMENTO'";

        query = query.replace("<VEHICULO_DOMINIO>", vehiculoDominio);
        query = query.replace("<NRO_DOCUMENTO>", nroDocumento);

        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("Lojack_vehiculos", query);

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
