package database.business;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import utils.Database;

import java.io.IOException;
import java.sql.SQLException;

public class BoxesDB {

    public static int generarSolicitud(Servicio servicio, String dni, String dominio){
        switch (servicio){
            case RECUPERO:
                return generarSolicitudRecupero(dni, dominio);
            case STRIX_AUTO:
                return generarSolicitudStrixAuto(dni, dominio);
            case STRIX_MOTO:
                return generarSolicitudStrixMoto(dni, dominio);
            case STRIX_FLOTAS:
                return generarSolicitudStrixFlotas(dni, dominio);
            case ESTANDAR:
                return generarSolicitudEstandar(dni, dominio);
            case MASIVO:
                return generarSolicitudMasivo(dni, dominio);
            case FULL:
                return generarSolicitudFull(dni, dominio);
            default:
                throw new IllegalStateException("No se puede generar solicitud. Servicio no encontrado: " + servicio+ "\nServicios disponibles: 'RECUPERO', 'STRIX_AUTO', 'STRIX_MOTO', 'STRIX_FLOTAS'");
        }
    }

    public static String obtenerNroSerieGPS(Servicio servicio){
        switch (servicio){
            case RECUPERO:
                return obtenerNroSerieGPSRecupero();
            case ESTANDAR:
                return obtenerNroSerieGPSEstandar();
            case STRIX_AUTO:
                return obtenerNroSerieGPSStrixAuto();
            case STRIX_MOTO:
                return obtenerNroSerieGPSStrixMoto();
            case STRIX_FLOTAS:
                return obtenerNroSerieGPSStrixFlotas();
            case MASIVO:
                return obtenerNroSerieGPSMasivo();
            case FULL:
                return obtenerNroSerieGPSFull();
            case FULL_ANTIJAMMING_ADR:
                return obtenerNroSerieGPSFullJammingAdr();
            default:
                throw new IllegalStateException("No se puede obtener Nro. de serie del GPS. Servicio no encontrado: " + servicio+ "\nServicios disponibles: 'RECUPERO', 'STRIX_AUTO', 'STRIX_MOTO', 'STRIX_FLOTAS', 'ESTANDAR_VLU'");
        }
    }

    public static String obtenerNroSerieVLU(Servicio servicio){
        switch (servicio){
            case ESTANDAR:
                return obtenerNroSerieVLUEstandar();
            case RECUPERO:
                return obtenerNroSerieVLURecupero();
            default:
                throw new IllegalStateException("No se puede obtener Nro. de serie del VLU. Servicio no encontrado: " + servicio+ "\nServicios disponibles: 'ESTANDAR_VLU'");
        }
    }

    private static int generarSolicitudRecupero(String dni, String dominio){

        String storeProcedureTemplate =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                        "<Solicitud Observaciones=\"RECUPERO CLIENTE POR CIA\" TipoEntidad=\"1\">\n" +
                        "    <Cliente Apellido=\"PEREZ\" Documento=\"<NRO_DOCUMENTO_REEMPLAZAR>\" Email=\"<NRO_DOMINIO_REEMPLAZAR>_<NRO_DOCUMENTO_REEMPLAZAR>@lacueva.com\" Nombre=\"RATON\" Telefono=\"1165142077\">\n" +
                        "        <Direccion Altura=\"2944\" Calle=\"CARRANZA ADOLFO PEDRO\" CodigoPostal=\"C1417HFN\" EntreCalle1=\"AVENIDA NAZCA\" EntreCalle2=\"HELGUERA\" IDLocalidad=\"59652\" IDPartido=\"59652\" IDProvincia=\"59650\" Latitud=\"-34.6079085714286\" Localidad=\"CIUDAD AUTONOMA BUENOS AIRES\" Longitud=\"-58.48497\" Provincia=\"CIUDAD AUT. DE BUENOS AIRES\"/>\n" +
                        "    </Cliente>\n" +
                        "    <Vehiculo Anio=\"2009\" CiaSSN=\"501\" Dominio=\"<NRO_DOMINIO_REEMPLAZAR>\" IDMarca=\"12\" Marca=\"CHEVROLET\" IDModelo=\"90005\" Modelo=\"MERIVA 1.8 GL PLUS AB\" IDMarcaModelo=\"450336\" IDTipoVehiculo=\"1\" Valor=\"895000\" Color=\"8\"/>\n" +
                        "    <Turno IDProducto=\"2110795\" IDTarea=\"21\" InstalacionLugar=\"1\" IDUnidadOperativa=\"47055924\" Producto=\"GPS\" Solucion=\"PL_RECUPERO\" Vendedor=\"76\" AcuerdoTipoCanal=\"1\" AcuerdoSponsoreo=\"425\" CanalExterno=\"9\" Campania=\"12\" ListaDePrecio=\"LP_PART_POR_CIAS\">\n" +
                        "        <DireccionInstalacion TipoDomicilio=\"1\" Altura=\"2944\" Calle=\"CARRANZA ADOLFO PEDRO\" CodigoPostal=\"C1417HFN\" Departamento=\"\" EntreCalle1=\"AVENIDA NAZCA\" EntreCalle2=\"HELGUERA\" IDLocalidad=\"59652\" IDPartido=\"59652\" IDProvincia=\"59650\" Latitud=\"-34.6079085714286\" Localidad=\"CIUDAD AUTONOMA BUENOS AIRES\" Longitud=\"-58.48497\" Partido=\"CAPITAL FEDERAL\" Provincia=\"CAPITAL FEDERAL\" Telefono=\"1165142077\"/>\n" +
                        "    </Turno>\n" +
                        "</Solicitud>\n";

        String query = "SELECT TOP 1 Turno_ID, Titulo\n" +
                "  FROM Turnos\n" +
                "  where titulo = '<NRO_DOMINIO_REEMPLAZAR>'";

        query = query.replace("<NRO_DOMINIO_REEMPLAZAR>",dominio);

        storeProcedureTemplate = storeProcedureTemplate.replace("<NRO_DOCUMENTO_REEMPLAZAR>",dni);
        storeProcedureTemplate = storeProcedureTemplate.replace("<NRO_DOMINIO_REEMPLAZAR>",dominio);

        try {
            int nroSolicitud = Database.executeStoredProcedureGenerarSolicitud("Ventas",  storeProcedureTemplate);

            JSONArray dbresponse = Database.executeQueryJSON("Ventas",query);
            String titulo  = Database.getValue(dbresponse,0,"Titulo");

            if(!titulo.isEmpty()) return nroSolicitud;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return 0;
    }

    private static int generarSolicitudStrixAuto(String dni, String dominio){

        String storeProcedureTemplate =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                        "<Solicitud Observaciones=\"STRIX EN TU AUTO PARTICULAR\" TipoEntidad=\"10\">\n" +
                        "    <Cliente Apellido=\"PEREZ\" Documento=\"<NRO_DOCUMENTO_REEMPLAZAR>\" Email=\"<NRO_DOMINIO_REEMPLAZAR>_<NRO_DOCUMENTO_REEMPLAZAR>@lacueva.com\" Nombre=\"RATON\" Telefono=\"1165142077\">\n" +
                        "        <Direccion Altura=\"2944\" Calle=\"CARRANZA ADOLFO PEDRO\" CodigoPostal=\"C1417HFN\" EntreCalle1=\"AVENIDA NAZCA\" EntreCalle2=\"HELGUERA\" IDLocalidad=\"59652\" IDPartido=\"59652\" IDProvincia=\"59650\" Latitud=\"-34.6079085714286\" Localidad=\"CIUDAD AUTONOMA BUENOS AIRES\" Longitud=\"-58.48497\" Provincia=\"CIUDAD AUT. DE BUENOS AIRES\"/>\n" +
                        "    </Cliente>\n" +
                        "    <Vehiculo Anio=\"2009\" CiaSSN=\"-1\" Dominio=\"<NRO_DOMINIO_REEMPLAZAR>\" IDMarca=\"12\" Marca=\"CHEVROLET\" IDModelo=\"90005\" Modelo=\"MERIVA 1.8 GL PLUS AB\" IDMarcaModelo=\"450336\" IDTipoVehiculo=\"1\" Valor=\"895000\" Color=\"8\"/>\n" +
                        "    <Turno IDProducto=\"2110795\" IDTarea=\"21\" InstalacionLugar=\"1\" IDUnidadOperativa=\"47055924\" Producto=\"GPS\" Solucion=\"PL_STRIXAUTO\" Vendedor=\"654\" AcuerdoTipoCanal=\"9\" CanalExterno=\"145\" Campania=\"24\" ListaDePrecio=\"LP_SXA_STRIX_BLACK\">\n" +
                        "        <DireccionInstalacion TipoDomicilio=\"1\" Altura=\"2944\" Calle=\"CARRANZA ADOLFO PEDRO\" CodigoPostal=\"C1417HFN\" Departamento=\"\" EntreCalle1=\"AVENIDA NAZCA\" EntreCalle2=\"HELGUERA\" IDLocalidad=\"59652\" IDPartido=\"59652\" IDProvincia=\"59650\" Latitud=\"-34.6079085714286\" Localidad=\"CIUDAD AUTONOMA BUENOS AIRES\" Longitud=\"-58.48497\" Partido=\"CAPITAL FEDERAL\" Provincia=\"CAPITAL FEDERAL\" Telefono=\"1165142077\"/>\n" +
                        "    </Turno>\n" +
                        "</Solicitud>";

        String query = "SELECT TOP 1 Turno_ID, Titulo\n" +
                "  FROM Turnos\n" +
                "  where titulo = '<NRO_DOMINIO_REEMPLAZAR>'";

        query = query.replace("<NRO_DOMINIO_REEMPLAZAR>",dominio);

        storeProcedureTemplate = storeProcedureTemplate.replace("<NRO_DOCUMENTO_REEMPLAZAR>",dni);
        storeProcedureTemplate = storeProcedureTemplate.replace("<NRO_DOMINIO_REEMPLAZAR>",dominio);

        try {
            int nroSolicitud = Database.executeStoredProcedureGenerarSolicitud("Ventas",  storeProcedureTemplate);

            JSONArray dbresponse = Database.executeQueryJSON("Ventas",query);
            String titulo  = Database.getValue(dbresponse,0,"Titulo");

            if(!titulo.isEmpty()) return nroSolicitud;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return 0;
    }

    private static int generarSolicitudStrixMoto(String dni, String dominio){

        String storeProcedureTemplate =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                        "<Solicitud Observaciones=\"STRIX EN TU MOTO PARTICULAR\" TipoEntidad=\"10\">\n" +
                        "    <Cliente Apellido=\"PEREZ\" Documento=\"<NRO_DOCUMENTO_REEMPLAZAR>\" Email=\"<NRO_DOMINIO_REEMPLAZAR>_<NRO_DOCUMENTO_REEMPLAZAR>@lacueva.com\" Nombre=\"RATON\" Telefono=\"1165142077\">\n" +
                        "        <Direccion Altura=\"2944\" Calle=\"CARRANZA ADOLFO PEDRO\" CodigoPostal=\"C1417HFN\" EntreCalle1=\"AVENIDA NAZCA\" EntreCalle2=\"HELGUERA\" IDLocalidad=\"59652\" IDPartido=\"59652\" IDProvincia=\"59650\" Latitud=\"-34.6079085714286\" Localidad=\"CIUDAD AUTONOMA BUENOS AIRES\" Longitud=\"-58.48497\" Provincia=\"CIUDAD AUT. DE BUENOS AIRES\"/>\n" +
                        "    </Cliente>\n" +
                        "    <Vehiculo Anio=\"2019\" CiaSSN=\"-1\" Dominio=\"<NRO_DOMINIO_REEMPLAZAR>\" IDMarca=\"849\" Marca=\"KTM\" IDModelo=\"8490047\" Modelo=\"DUKE 890 R\" IDMarcaModelo=\"99834\" IDTipoVehiculo=\"6\" Valor=\"5500000\" Color=\"8\"/>\n" +
                        "    <Turno IDProducto=\"2110795\" IDTarea=\"21\" InstalacionLugar=\"1\" IDUnidadOperativa=\"47055924\" Producto=\"GPS\" Solucion=\"PL_STRIXMOTO\" Vendedor=\"654\" AcuerdoTipoCanal=\"9\" CanalExterno=\"145\" Campania=\"24\" ListaDePrecio=\"LP_SXM_STRIX_BLACK\">\n" +
                        "        <DireccionInstalacion TipoDomicilio=\"1\" Altura=\"2944\" Calle=\"CARRANZA ADOLFO PEDRO\" CodigoPostal=\"C1417HFN\" Departamento=\"\" EntreCalle1=\"AVENIDA NAZCA\" EntreCalle2=\"HELGUERA\" IDLocalidad=\"59652\" IDPartido=\"59652\" IDProvincia=\"59650\" Latitud=\"-34.6079085714286\" Localidad=\"CIUDAD AUTONOMA BUENOS AIRES\" Longitud=\"-58.48497\" Partido=\"CAPITAL FEDERAL\" Provincia=\"CAPITAL FEDERAL\" Telefono=\"1165142077\"/>\n" +
                        "    </Turno>\n" +
                        "</Solicitud>";

        String query = "SELECT TOP 1 Turno_ID, Titulo\n" +
                "  FROM Turnos\n" +
                "  where titulo = '<NRO_DOMINIO_REEMPLAZAR>'";

        query = query.replace("<NRO_DOMINIO_REEMPLAZAR>",dominio);

        storeProcedureTemplate = storeProcedureTemplate.replace("<NRO_DOCUMENTO_REEMPLAZAR>",dni);
        storeProcedureTemplate = storeProcedureTemplate.replace("<NRO_DOMINIO_REEMPLAZAR>",dominio);

        try {
            int nroSolicitud = Database.executeStoredProcedureGenerarSolicitud("Ventas",  storeProcedureTemplate);

            JSONArray dbresponse = Database.executeQueryJSON("Ventas",query);
            String titulo  = Database.getValue(dbresponse,0,"Titulo");

            if(!titulo.isEmpty()) return nroSolicitud;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return 0;
    }

    private static int generarSolicitudStrixFlotas(String dni, String dominio){ // TODO
        String storeProcedureTemplate =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                        "<Solicitud Observaciones=\"STRIX FLOTAS LOGISTICA PARTICULAR\" TipoEntidad=\"11\">\n" +
                        "    <Cliente Apellido=\"PEREZ\" Documento=\"<NRO_DOCUMENTO_REEMPLAZAR>\" Email=\"<NRO_DOMINIO_REEMPLAZAR>_<NRO_DOCUMENTO_REEMPLAZAR>@lacueva.com\" Nombre=\"RATON\" Telefono=\"1165142077\">\n" +
                        "        <Direccion Altura=\"2944\" Calle=\"CARRANZA ADOLFO PEDRO\" CodigoPostal=\"C1417HFN\" EntreCalle1=\"AVENIDA NAZCA\" EntreCalle2=\"HELGUERA\" IDLocalidad=\"59652\" IDPartido=\"59652\" IDProvincia=\"59650\" Latitud=\"-34.6079085714286\" Localidad=\"CIUDAD AUTONOMA BUENOS AIRES\" Longitud=\"-58.48497\" Provincia=\"CIUDAD AUT. DE BUENOS AIRES\"/>\n" +
                        "    </Cliente>\n" +
                        "    <Vehiculo Anio=\"2009\" CiaSSN=\"-1\" Dominio=\"<NRO_DOMINIO_REEMPLAZAR>\" IDMarca=\"12\" Marca=\"CHEVROLET\" IDModelo=\"90005\" Modelo=\"MERIVA 1.8 GL PLUS AB\" IDMarcaModelo=\"450336\" IDTipoVehiculo=\"1\" Valor=\"895000\" Color=\"8\"/>\n" +
                        "    <Turno IDProducto=\"2110795\" IDTarea=\"21\" InstalacionLugar=\"1\" IDUnidadOperativa=\"47055924\" Producto=\"GPS\" Solucion=\"PL_STRIXFLOTALOG\" Vendedor=\"654\" AcuerdoTipoCanal=\"10\" CanalExterno=\"154\" Campania=\"14\" ListaDePrecio=\"LP_TYC_0452\">\n" +
                        "        <DireccionInstalacion TipoDomicilio=\"1\" Altura=\"2944\" Calle=\"CARRANZA ADOLFO PEDRO\" CodigoPostal=\"C1417HFN\" Departamento=\"\" EntreCalle1=\"AVENIDA NAZCA\" EntreCalle2=\"HELGUERA\" IDLocalidad=\"59652\" IDPartido=\"59652\" IDProvincia=\"59650\" Latitud=\"-34.6079085714286\" Localidad=\"CIUDAD AUTONOMA BUENOS AIRES\" Longitud=\"-58.48497\" Partido=\"CAPITAL FEDERAL\" Provincia=\"CAPITAL FEDERAL\" Telefono=\"1165142077\"/>\n" +
                        "    </Turno>\n" +
                        "</Solicitud>";

        String query = "SELECT TOP 1 Turno_ID, Titulo\n" +
                "  FROM Turnos\n" +
                "  where titulo = '<NRO_DOMINIO_REEMPLAZAR>'";

        query = query.replace("<NRO_DOMINIO_REEMPLAZAR>",dominio);

        storeProcedureTemplate = storeProcedureTemplate.replace("<NRO_DOCUMENTO_REEMPLAZAR>",dni);
        storeProcedureTemplate = storeProcedureTemplate.replace("<NRO_DOMINIO_REEMPLAZAR>",dominio);

        try {
            int nroSolicitud = Database.executeStoredProcedureGenerarSolicitud("Ventas",  storeProcedureTemplate);

            JSONArray dbresponse = Database.executeQueryJSON("Ventas",query);
            String titulo  = Database.getValue(dbresponse,0,"Titulo");

            if(!titulo.isEmpty()) return nroSolicitud;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return 0;
    }

    private static int generarSolicitudEstandar(String dni, String dominio){
        String storeProcedureTemplate =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                        "<Solicitud Observaciones=\"INSTALACION STANDAR\" TipoEntidad=\"10\">\n" +
                        "    <Cliente Apellido=\"PEREZ\" Documento=\"<NRO_DOCUMENTO_REEMPLAZAR>\" Email=\"<NRO_DOMINIO_REEMPLAZAR>_<NRO_DOCUMENTO_REEMPLAZAR>@lacueva.com\" Nombre=\"RATON\" Telefono=\"1165142077\">\n" +
                        "        <Direccion Altura=\"2944\" Calle=\"CARRANZA ADOLFO PEDRO\" CodigoPostal=\"C1417HFN\" EntreCalle1=\"AVENIDA NAZCA\" EntreCalle2=\"HELGUERA\" IDLocalidad=\"59652\" IDPartido=\"59652\" IDProvincia=\"59650\" Latitud=\"-34.6079085714286\" Localidad=\"CIUDAD AUTONOMA BUENOS AIRES\" Longitud=\"-58.48497\" Provincia=\"CIUDAD AUT. DE BUENOS AIRES\"/>\n" +
                        "    </Cliente>\n" +
                        "    <Vehiculo Anio=\"2009\" CiaSSN=\"-1\" Dominio=\"<NRO_DOMINIO_REEMPLAZAR>\" IDMarca=\"12\" Marca=\"CHEVROLET\" IDModelo=\"90005\" Modelo=\"MERIVA 1.8 GL PLUS AB\" IDMarcaModelo=\"450336\" IDTipoVehiculo=\"1\" Valor=\"895000\" Color=\"8\"/>\n" +
                        "    <Turno IDProducto=\"2110795\" IDTarea=\"21\" InstalacionLugar=\"1\" IDUnidadOperativa=\"47055924\" Producto=\"GPS\" Solucion=\"PL_ESTANDAR\" Vendedor=\"654\" AcuerdoTipoCanal=\"10\" CanalExterno=\"154\" Campania=\"14\" ListaDePrecio=\"LP_TYC_0431\">\n" +
                        "        <DireccionInstalacion TipoDomicilio=\"1\" Altura=\"2944\" Calle=\"CARRANZA ADOLFO PEDRO\" CodigoPostal=\"C1417HFN\" Departamento=\"\" EntreCalle1=\"AVENIDA NAZCA\" EntreCalle2=\"HELGUERA\" IDLocalidad=\"59652\" IDPartido=\"59652\" IDProvincia=\"59650\" Latitud=\"-34.6079085714286\" Localidad=\"CIUDAD AUTONOMA BUENOS AIRES\" Longitud=\"-58.48497\" Partido=\"CAPITAL FEDERAL\" Provincia=\"CAPITAL FEDERAL\" Telefono=\"1165142077\"/>\n" +
                        "    </Turno>\n" +
                        "</Solicitud>\n";

        String query = "SELECT TOP 1 Turno_ID, Titulo\n" +
                "  FROM Turnos\n" +
                "  where titulo = '<NRO_DOMINIO_REEMPLAZAR>'";

        query = query.replace("<NRO_DOMINIO_REEMPLAZAR>",dominio);

        storeProcedureTemplate = storeProcedureTemplate.replace("<NRO_DOCUMENTO_REEMPLAZAR>",dni);
        storeProcedureTemplate = storeProcedureTemplate.replace("<NRO_DOMINIO_REEMPLAZAR>",dominio);

        try {
            int nroSolicitud = Database.executeStoredProcedureGenerarSolicitud("Ventas",  storeProcedureTemplate);

            JSONArray dbresponse = Database.executeQueryJSON("Ventas",query);
            String titulo  = Database.getValue(dbresponse,0,"Titulo");

            if(!titulo.isEmpty()) return nroSolicitud;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return 0;
    }

    private static int generarSolicitudMasivo(String dni, String dominio){
        String storeProcedureTemplate =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                        "<Solicitud Observaciones=\"INSTALACION MASIVO\" TipoEntidad=\"10\">\n" +
                        "    <Cliente Apellido=\"PEREZ\" Documento=\"<NRO_DOCUMENTO_REEMPLAZAR>\" Email=\"<NRO_DOMINIO_REEMPLAZAR>_<NRO_DOCUMENTO_REEMPLAZAR>@lacueva.com\" Nombre=\"RATON\" Telefono=\"1165142077\">\n" +
                        "        <Direccion Altura=\"2944\" Calle=\"CARRANZA ADOLFO PEDRO\" CodigoPostal=\"C1417HFN\" EntreCalle1=\"AVENIDA NAZCA\" EntreCalle2=\"HELGUERA\" IDLocalidad=\"59652\" IDPartido=\"59652\" IDProvincia=\"59650\" Latitud=\"-34.6079085714286\" Localidad=\"CIUDAD AUTONOMA BUENOS AIRES\" Longitud=\"-58.48497\" Provincia=\"CIUDAD AUT. DE BUENOS AIRES\"/>\n" +
                        "    </Cliente>\n" +
                        "    <Vehiculo Anio=\"2009\" CiaSSN=\"-1\" Dominio=\"<NRO_DOMINIO_REEMPLAZAR>\" IDMarca=\"12\" Marca=\"CHEVROLET\" IDModelo=\"90005\" Modelo=\"MERIVA 1.8 GL PLUS AB\" IDMarcaModelo=\"450336\" IDTipoVehiculo=\"1\" Valor=\"895000\" Color=\"8\"/>\n" +
                        "    <Turno IDProducto=\"2110795\" IDTarea=\"21\" InstalacionLugar=\"1\" IDUnidadOperativa=\"47055924\" Producto=\"GPS\" Solucion=\"PL_MASIVO\" Vendedor=\"654\" AcuerdoTipoCanal=\"10\" CanalExterno=\"154\" Campania=\"14\" ListaDePrecio=\"LP_TYC_0431\">\n" +
                        "        <DireccionInstalacion TipoDomicilio=\"1\" Altura=\"2944\" Calle=\"CARRANZA ADOLFO PEDRO\" CodigoPostal=\"C1417HFN\" Departamento=\"\" EntreCalle1=\"AVENIDA NAZCA\" EntreCalle2=\"HELGUERA\" IDLocalidad=\"59652\" IDPartido=\"59652\" IDProvincia=\"59650\" Latitud=\"-34.6079085714286\" Localidad=\"CIUDAD AUTONOMA BUENOS AIRES\" Longitud=\"-58.48497\" Partido=\"CAPITAL FEDERAL\" Provincia=\"CAPITAL FEDERAL\" Telefono=\"1165142077\"/>\n" +
                        "    </Turno>\n" +
                        "</Solicitud>\n";

        String query = "SELECT TOP 1 Turno_ID, Titulo\n" +
                "  FROM Turnos\n" +
                "  where titulo = '<NRO_DOMINIO_REEMPLAZAR>'";

        query = query.replace("<NRO_DOMINIO_REEMPLAZAR>",dominio);

        storeProcedureTemplate = storeProcedureTemplate.replace("<NRO_DOCUMENTO_REEMPLAZAR>",dni);
        storeProcedureTemplate = storeProcedureTemplate.replace("<NRO_DOMINIO_REEMPLAZAR>",dominio);

        try {
            int nroSolicitud = Database.executeStoredProcedureGenerarSolicitud("Ventas",  storeProcedureTemplate);

            JSONArray dbresponse = Database.executeQueryJSON("Ventas",query);
            String titulo  = Database.getValue(dbresponse,0,"Titulo");

            if(!titulo.isEmpty()) return nroSolicitud;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return 0;
    }

    private static int generarSolicitudFull(String dni, String dominio){
        String storeProcedureTemplate =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                        "<Solicitud Observaciones=\"INSTALACION FULL\" TipoEntidad=\"10\">\n" +
                        "    <Cliente Apellido=\"PEREZ\" Documento=\"<NRO_DOCUMENTO_REEMPLAZAR>\" Email=\"<NRO_DOMINIO_REEMPLAZAR>_<NRO_DOCUMENTO_REEMPLAZAR>@lacueva.com\" Nombre=\"RATON\" Telefono=\"1165142077\">\n" +
                        "        <Direccion Altura=\"2944\" Calle=\"CARRANZA ADOLFO PEDRO\" CodigoPostal=\"C1417HFN\" EntreCalle1=\"AVENIDA NAZCA\" EntreCalle2=\"HELGUERA\" IDLocalidad=\"59652\" IDPartido=\"59652\" IDProvincia=\"59650\" Latitud=\"-34.6079085714286\" Localidad=\"CIUDAD AUTONOMA BUENOS AIRES\" Longitud=\"-58.48497\" Provincia=\"CIUDAD AUT. DE BUENOS AIRES\"/>\n" +
                        "    </Cliente>\n" +
                        "    <Vehiculo Anio=\"2009\" CiaSSN=\"-1\" Dominio=\"<NRO_DOMINIO_REEMPLAZAR>\" IDMarca=\"12\" Marca=\"CHEVROLET\" IDModelo=\"90005\" Modelo=\"MERIVA 1.8 GL PLUS AB\" IDMarcaModelo=\"450336\" IDTipoVehiculo=\"1\" Valor=\"895000\" Color=\"8\"/>\n" +
                        "    <Turno IDProducto=\"2110795\" IDTarea=\"21\" InstalacionLugar=\"1\" IDUnidadOperativa=\"47055924\" Producto=\"GPS\" Solucion=\"PL_FULL\" Vendedor=\"654\" AcuerdoTipoCanal=\"10\" CanalExterno=\"154\" Campania=\"14\" ListaDePrecio=\"LP_TYC_0431\">\n" +
                        "        <DireccionInstalacion TipoDomicilio=\"1\" Altura=\"2944\" Calle=\"CARRANZA ADOLFO PEDRO\" CodigoPostal=\"C1417HFN\" Departamento=\"\" EntreCalle1=\"AVENIDA NAZCA\" EntreCalle2=\"HELGUERA\" IDLocalidad=\"59652\" IDPartido=\"59652\" IDProvincia=\"59650\" Latitud=\"-34.6079085714286\" Localidad=\"CIUDAD AUTONOMA BUENOS AIRES\" Longitud=\"-58.48497\" Partido=\"CAPITAL FEDERAL\" Provincia=\"CAPITAL FEDERAL\" Telefono=\"1165142077\"/>\n" +
                        "    </Turno>\n" +
                        "</Solicitud>\n";

        String query = "SELECT TOP 1 Turno_ID, Titulo\n" +
                "  FROM Turnos\n" +
                "  where titulo = '<NRO_DOMINIO_REEMPLAZAR>'";

        query = query.replace("<NRO_DOMINIO_REEMPLAZAR>",dominio);

        storeProcedureTemplate = storeProcedureTemplate.replace("<NRO_DOCUMENTO_REEMPLAZAR>",dni);
        storeProcedureTemplate = storeProcedureTemplate.replace("<NRO_DOMINIO_REEMPLAZAR>",dominio);

        try {
            int nroSolicitud = Database.executeStoredProcedureGenerarSolicitud("Ventas",  storeProcedureTemplate);

            JSONArray dbresponse = Database.executeQueryJSON("Ventas",query);
            String titulo  = Database.getValue(dbresponse,0,"Titulo");

            if(!titulo.isEmpty()) return nroSolicitud;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return 0;
    }

    public static String obtenerNroDeSolicitud(String dominio){
        String query = "SELECT TOP 1 Turno_ID, Titulo\n" +
                "  FROM Turnos\n" +
                "  where titulo = '<NRO_DOMINIO_REEMPLAZAR>'";

        query = query.replace("<NRO_DOMINIO_REEMPLAZAR>",dominio);

        try {
            JSONArray dbresponse = Database.executeQueryJSON("Ventas",query);
            String titulo  = Database.getValue(dbresponse,0,"Turno_ID");

            if(!titulo.isEmpty()) return titulo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return "";
    }

   /* private static String obtenerNroSerieGPSRecupero(){
        String query =
                "SELECT\n" +
//          "ARTS_ARTICULO_EMP Articulo, ARTS_NOMBRE Descripcion, PART_PARTIDA_EMP Partida_Empresa, SDPP_DEPOSITO Nro_Deposito, DPOS_NOMBRE Deposito, PART_CLASIF_1 Estado_Equipo, STOC_CA05.CA05_CLASIF_5 Id_Tipo_Equipo, STOC_CA05.CA05_NOMBRE Tipo_Equipo\n" +
                        "TOP 1 PART_PARTIDA_EMP Partida_Empresa\n" +
                        "FROM STOC_ARTS\n" +
                        "JOIN STOC_PART ON PART_ARTICULO = ARTS_ARTICULO\n" +
                        "JOIN STOC_SDPP ON SDPP_PARTIDA = PART_PARTIDA\n" +
                        "JOIN STOC_DPOS ON DPOS_DEPOSITO = SDPP_DEPOSITO\n" +
                        "JOIN STOC_STDP ON STDP_ARTICULO = ARTS_ARTICULO AND STDP_DEPOSITO = SDPP_DEPOSITO\n" +
                        "JOIN STOC_CA05 ON ARTS_CLASIF_5 = STOC_CA05.CA05_CLASIF_5\n" +
                        "WHERE PART_CLASIF_1='NNS'\n" +
                        "AND SDPP_STOCK_ACT > 0\n" +
                        "AND STDP_STOCK_ACT > 0\n" +
                        "AND SDPP_DEPOSITO = 179\n" +
                        "AND ARTS_ARTICULO_EMP IN ('GGPS001','GGPS002','GGPS003','GGPS004','GGPS005','GGPS006','GGPS007','VLU059L',\n" +
                        "'GGPS008','GGPS009','GGPS010','GGPS011','GPS0001','GPS0002','GPS0003','GPS0011',\n" +
                        "'GPS0012','GPS0013','GPS0014','GPS0021','GPS0022','GPS0023','GPS0033','GPS0034',\n" +
                        "'GPSAMPS','GPSGM48','GPSS6FW','KCARGMT','KCHGS15','KGMT200','KGPS15M','KGPSS15',\n" +
                        "'KINSD27','KIT0001','KIT0003','KIT0011','KIT0012','KIT0013','KIT0015','KIT0016',\n" +
                        "'KIT0021','KIT0022','KIT0023','KIT0025','KIT0026','KIT0027','KIT0029','KIT0033',\n" +
                        "'KIT0035','KIT0036','KIT0043','KIT0053','KIT0056','KIT0059','KIT0066','KIT0069',\n" +
                        "'KIT00A0','KIT00A7','KIT00A9','KIT03KM','KIT03KS','KIT03SS','KIT0A12','KIT1000',\n" +
                        "'KIT10DS','KIT15DS','KIT16DS','KIT2630','KIT2631','KIT3000','KIT30SS','KIT3500',\n" +
                        "'KIT3555','KIT35SS','KIT3M55','KITA7SS','KITBR27','KITCAR2','KITCH27','KITCH59',\n" +
                        "'KITCH69','KITDS27','KITGE10','KITGE11','KITGS16','KITGS24','KITIN23','KITIN25',\n" +
                        "'KITIN26','KITIN27','KITIN56','KITIN59','KITMO16','KITMO56','KITQ200','KITQ300',\n" +
                        "'KITRLA8','KITS6FW','KITS6SS','KITSC25','KITSC27','KITSC29','KITSC59','KITSD25',\n" +
                        "'KITSD27','KITSD29','KITSD35','KITSD59','KITSD69','KITSPA8','KITSS26','KITSS29',\n" +
                        "'KITSS56','KITSS59','KITSS66','KITSS69','KITSSA7','KITSSA8','KITSSA9','KITUR26',\n" +
                        "'KLMU200','KS15MDS','KSSMO56','KTG2630','KTGG200','KTGS15B','KTLMU2')\n" +
                        "AND NOT EXISTS\n" +
                        "(SELECT *\n" +
                        "FROM [LJSQLDEVTEST02,1433].calipso_intdev01.dbo.b_inventory i\n" +
                        "JOIN [LJSQLDEVTEST02,1433].calipso_intdev01.dbo.b_place_inventory pi on pi.place_inve_inventory_id = i.inventory_id\n" +
                        "WHERE pi.place_inve_to_date is null\n" +
                        "AND i.inventory_serial = PART_PARTIDA_EMP COLLATE Latin1_General_CI_AS)\n" +
                        "AND PART_PARTIDA_EMP not like 'ID8988500%'\n" +
                        "ORDER BY NEWID()";

        String result = "";
        try {
            JSONArray rs = Database.executeQueryJSON("dbOleiros", query);
            result  = Database.getValue(rs,0,"Partida_Empresa");
            result = result.replace("ID","");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    */

    // script para buscar gps s15
    private static String obtenerNroSerieGPSRecupero(){
        String query =
                "SELECT  TOP 1 PART_PARTIDA_EMP Partida_Empresa\n" +
                        "FROM STOC_ARTS\n" +
                        "JOIN STOC_PART ON PART_ARTICULO = ARTS_ARTICULO\n" +
                        "JOIN STOC_SDPP ON SDPP_PARTIDA = PART_PARTIDA\n" +
                        "JOIN STOC_DPOS ON DPOS_DEPOSITO = SDPP_DEPOSITO\n" +
                        "JOIN STOC_STDP ON STDP_ARTICULO = ARTS_ARTICULO AND STDP_DEPOSITO = SDPP_DEPOSITO\n" +
                        "JOIN STOC_CA05 ON ARTS_CLASIF_5 = STOC_CA05.CA05_CLASIF_5\n" +
                        "WHERE PART_CLASIF_1='NNS'\n" +
                        "AND SDPP_STOCK_ACT > 0\n" +
                        "AND STDP_STOCK_ACT > 0\n" +
                        "AND SDPP_DEPOSITO = 179\n" +
                        "and STOC_CA05.CA05_NOMBRE = 'S15'\n" +
                        "and STOC_PART.PART_PARTIDA_EMP not in ('ID868850021064570','ID868850021356406','ID898880000000004','ID898880000000005')\n" +
                        "AND ARTS_ARTICULO_EMP IN ('KCARGMT','KCHGS15','KGMT200','KGPS15M','KGPSS15','KIT10DS',\n" +
                        "'KIT15DS','KIT16DS','KIT2630','KITCAR2','KITGE10','KITGE11','KITGS16','KITQ200',\n" +
                        "'KITS6FW','KITS6SS','KLMU200','KS15MDS','KTG2630','KTGG200','KTGS15B','KTLMU2','KITGS23',\n" +
                        "'KCHGS15', 'KIT10DS', 'KIT15DS','KIT16DS', 'KIT17DS', 'KITGE10', 'KITGS10', 'KITGS15', 'KITGS16', 'KITGS17', 'KITGS23', 'KITS6FW','KTGS15B',\n" +
                        "'KITQ200', 'KITQ300','KIT2631','KIT2630','KCARGMT','KGMT200','KITQ200','KITQ300','KITCAR2','KLMU200','KIT2631','KIT2630', 'KCAR920','KCARGMT',\n" +
                        "'KCARGV5', 'KCHGS15','KGMT200', 'KGPS15M', 'KGSS15M','KIT10DS','KIT15DS','KIT16DS','KIT17DS','KIT2630','KIT2631','KITB920','KITCAR2','KITQ200',\n" +
                        "'KITQ300','KITS6FW','KLMU200','KS15MDS','KTGS15B')\n" +
                        "AND NOT EXISTS\n" +
                        "(SELECT *\n" +
                        "FROM [LJSQLDEVTEST02,1433].calipso_intdev01.dbo.b_inventory i\n" +
                        "JOIN [LJSQLDEVTEST02,1433].calipso_intdev01.dbo.b_place_inventory pi on pi.place_inve_inventory_id = i.inventory_id\n" +
                        "WHERE pi.place_inve_to_date is null\n" +
                        "AND i.inventory_serial = PART_PARTIDA_EMP COLLATE Latin1_General_CI_AS)\n" +
                        "order by newid()";

        String result = "";
        try {
            JSONArray rs = Database.executeQueryJSON("dbOleiros", query);
            result  = Database.getValue(rs,0,"Partida_Empresa");
            result = result.replace("ID","");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }





    private static String obtenerNroSerieGPSEstandar(){
        String query =
                "SELECT\n" +
//          "ARTS_ARTICULO_EMP Articulo, ARTS_NOMBRE Descripcion, PART_PARTIDA_EMP Partida_Empresa, SDPP_DEPOSITO Nro_Deposito, DPOS_NOMBRE Deposito, PART_CLASIF_1 Estado_Equipo, STOC_CA05.CA05_CLASIF_5 Id_Tipo_Equipo, STOC_CA05.CA05_NOMBRE Tipo_Equipo\n" +
                        "TOP 1 PART_PARTIDA_EMP Partida_Empresa\n" +
                        "FROM STOC_ARTS\n" +
                        "JOIN STOC_PART ON PART_ARTICULO = ARTS_ARTICULO\n" +
                        "JOIN STOC_SDPP ON SDPP_PARTIDA = PART_PARTIDA\n" +
                        "JOIN STOC_DPOS ON DPOS_DEPOSITO = SDPP_DEPOSITO\n" +
                        "JOIN STOC_STDP ON STDP_ARTICULO = ARTS_ARTICULO AND STDP_DEPOSITO = SDPP_DEPOSITO\n" +
                        "JOIN STOC_CA05 ON ARTS_CLASIF_5 = STOC_CA05.CA05_CLASIF_5\n" +
                        "WHERE PART_CLASIF_1='NNS'\n" +
                        "AND SDPP_STOCK_ACT > 0\n" +
                        "AND STDP_STOCK_ACT > 0\n" +
                        "AND SDPP_DEPOSITO = 179\n" +
                        "AND ARTS_ARTICULO_EMP IN ('GGPS001','GGPS002','GGPS003','GGPS004','GGPS005','GGPS006','GGPS007','VLU059L',\n" +
                        "'GGPS008','GGPS009','GGPS010','GGPS011','GPS0001','GPS0002','GPS0003','GPS0011',\n" +
                        "'GPS0012','GPS0013','GPS0014','GPS0021','GPS0022','GPS0023','GPS0033','GPS0034',\n" +
                        "'GPSAMPS','GPSGM48','GPSS6FW','KCARGMT','KCHGS15','KGMT200','KGPS15M','KGPSS15',\n" +
                        "'KINSD27','KIT0001','KIT0003','KIT0011','KIT0012','KIT0013','KIT0015','KIT0016',\n" +
                        "'KIT0021','KIT0022','KIT0023','KIT0025','KIT0026','KIT0027','KIT0029','KIT0033',\n" +
                        "'KIT0035','KIT0036','KIT0043','KIT0053','KIT0056','KIT0059','KIT0066','KIT0069',\n" +
                        "'KIT00A0','KIT00A7','KIT00A9','KIT03KM','KIT03KS','KIT03SS','KIT0A12','KIT1000',\n" +
                        "'KIT10DS','KIT15DS','KIT16DS','KIT2630','KIT2631','KIT3000','KIT30SS','KIT3500',\n" +
                        "'KIT3555','KIT35SS','KIT3M55','KITA7SS','KITBR27','KITCAR2','KITCH27','KITCH59',\n" +
                        "'KITCH69','KITDS27','KITGE10','KITGE11','KITGS16','KITGS24','KITIN23','KITIN25',\n" +
                        "'KITIN26','KITIN27','KITIN56','KITIN59','KITMO16','KITMO56','KITQ200','KITQ300',\n" +
                        "'KITRLA8','KITS6FW','KITS6SS','KITSC25','KITSC27','KITSC29','KITSC59','KITSD25',\n" +
                        "'KITSD27','KITSD29','KITSD35','KITSD59','KITSD69','KITSPA8','KITSS26','KITSS29',\n" +
                        "'KITSS56','KITSS59','KITSS66','KITSS69','KITSSA7','KITSSA8','KITSSA9','KITUR26',\n" +
                        "'KLMU200','KS15MDS','KSSMO56','KTG2630','KTGG200','KTGS15B','KTLMU2')\n" +
                        "AND NOT EXISTS\n" +
                        "(SELECT *\n" +
                        "FROM [LJSQLDEVTEST02,1433].calipso_intdev01.dbo.b_inventory i\n" +
                        "JOIN [LJSQLDEVTEST02,1433].calipso_intdev01.dbo.b_place_inventory pi on pi.place_inve_inventory_id = i.inventory_id\n" +
                        "WHERE pi.place_inve_to_date is null\n" +
                        "AND i.inventory_serial = PART_PARTIDA_EMP COLLATE Latin1_General_CI_AS)\n" +
                        "AND PART_PARTIDA_EMP not like 'ID8988500%'\n" +
                        "ORDER BY NEWID()";

        String result = "";
        try {
            JSONArray rs = Database.executeQueryJSON("dbOleiros", query);
            result  = Database.getValue(rs,0,"Partida_Empresa");
            result = result.replace("ID","");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }



    private static String obtenerNroSerieGPSStrixAuto(){
        String query =
                "SELECT TOP 1 PART_PARTIDA_EMP Partida_Empresa\n" +
                    "FROM STOC_ARTS\n" +
                    "JOIN STOC_PART ON PART_ARTICULO = ARTS_ARTICULO\n" +
                    "JOIN STOC_SDPP ON SDPP_PARTIDA = PART_PARTIDA\n" +
                    "JOIN STOC_DPOS ON DPOS_DEPOSITO = SDPP_DEPOSITO\n" +
                    "JOIN STOC_STDP ON STDP_ARTICULO = ARTS_ARTICULO AND STDP_DEPOSITO = SDPP_DEPOSITO\n" +
                    "JOIN STOC_CA05 ON ARTS_CLASIF_5 = STOC_CA05.CA05_CLASIF_5\n" +
                    "WHERE PART_CLASIF_1='NNS'\n" +
                    "AND SDPP_STOCK_ACT > 0\n" +
                    "AND STDP_STOCK_ACT > 0\n" +
                    "AND SDPP_DEPOSITO = 179\n" +
                    "AND ARTS_ARTICULO_EMP IN ('KCARGMT','KCHGS15','KGMT200','KGPS15M','KGPSS15','KIT10DS',\n" +
                    "'KIT15DS','KIT16DS','KIT2630','KITCAR2','KITGE10','KITGE11','KITGS16','KITQ200',\n" +
                    "'KITS6FW','KITS6SS','KLMU200','KS15MDS','KTG2630','KTGG200','KTGS15B','KTLMU2')\n" +
                    "AND NOT EXISTS\n" +
                    "(SELECT *\n" +
                    "FROM [LJSQLDEVTEST02,1433].calipso_intdev01.dbo.b_inventory i\n" +
                    "JOIN [LJSQLDEVTEST02,1433].calipso_intdev01.dbo.b_place_inventory pi on pi.place_inve_inventory_id = i.inventory_id\n" +
                    "WHERE pi.place_inve_to_date is null\n" +
                    "AND PART_PARTIDA_EMP like 'ID900%'\n"+
                    "AND i.inventory_serial = PART_PARTIDA_EMP COLLATE Latin1_General_CI_AS)\n" +
                    "ORDER BY NEWID()";
//        GPS SERIAL NUMBER: 900000000000197


        String result = "";
        try {
            JSONArray rs = Database.executeQueryJSON("dbOleiros", query);
            result  = Database.getValue(rs,0,"Partida_Empresa");
            result = result.replace("ID","");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }
    private static String obtenerNroSerieGPSStrixFlotas(){
        return obtenerNroSerieGPSStrixAuto();
    }

    private static String obtenerNroSerieGPSStrixMoto(){
        String query =
                        "SELECT TOP 1 PART_PARTIDA_EMP Partida_Empresa\n" +
                                "FROM STOC_ARTS\n" +
                                "JOIN STOC_PART ON PART_ARTICULO = ARTS_ARTICULO\n" +
                                "JOIN STOC_SDPP ON SDPP_PARTIDA = PART_PARTIDA\n" +
                                "JOIN STOC_DPOS ON DPOS_DEPOSITO = SDPP_DEPOSITO\n" +
                                "JOIN STOC_STDP ON STDP_ARTICULO = ARTS_ARTICULO AND STDP_DEPOSITO = SDPP_DEPOSITO\n" +
                                "JOIN STOC_CA05 ON ARTS_CLASIF_5 = STOC_CA05.CA05_CLASIF_5\n" +
                                "WHERE PART_CLASIF_1='NNS'\n" +
                                "AND SDPP_STOCK_ACT > 0\n" +
                                "AND STDP_STOCK_ACT > 0\n" +
                                "AND SDPP_DEPOSITO = 179\n" +
                                "AND ARTS_ARTICULO_EMP IN ('KCARGMT','KGMT200','KTGG200')\n" +
                                "AND NOT EXISTS\n" +
                                "(SELECT *\n" +
                                "FROM [LJSQLDEVTEST02,1433].calipso_intdev01.dbo.b_inventory i\n" +
                                "JOIN [LJSQLDEVTEST02,1433].calipso_intdev01.dbo.b_place_inventory pi on pi.place_inve_inventory_id = i.inventory_id\n" +
                                "WHERE pi.place_inve_to_date is null\n" +
                                "AND i.inventory_serial = PART_PARTIDA_EMP COLLATE Latin1_General_CI_AS)\n" +
                        "ORDER BY NEWID()";

        String result = "";
        try {
            JSONArray rs = Database.executeQueryJSON("dbOleiros", query);
            result  = Database.getValue(rs,0,"Partida_Empresa");
            result = result.replace("ID","");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String obtenerNroSerieGPSFullAntijammingAdr(){
        String query =
                "SELECT TOP 1 PART_PARTIDA_EMP Partida_Empresa\n" +
                        "FROM STOC_ARTS\n" +
                        "JOIN STOC_PART ON PART_ARTICULO = ARTS_ARTICULO\n" +
                        "JOIN STOC_SDPP ON SDPP_PARTIDA = PART_PARTIDA\n" +
                        "JOIN STOC_DPOS ON DPOS_DEPOSITO = SDPP_DEPOSITO\n" +
                        "JOIN STOC_STDP ON STDP_ARTICULO = ARTS_ARTICULO AND STDP_DEPOSITO = SDPP_DEPOSITO\n" +
                        "JOIN STOC_CA05 ON ARTS_CLASIF_5 = STOC_CA05.CA05_CLASIF_5\n" +
                        "WHERE PART_CLASIF_1='NNS'\n" +
                        "AND SDPP_STOCK_ACT > 0\n" +
                        "AND STDP_STOCK_ACT > 0\n" +
                        "AND SDPP_DEPOSITO = 179\n" +
                        "AND PART_ARTICULO =  1866\n" +
                        "AND ARTS_ARTICULO_EMP IN ('KCARGMT','KCHGS15','KGMT200','KGPS15M','KGPSS15','KIT10DS',\n" +
                        "'KIT15DS','KIT16DS','KIT2630','KITCAR2','KITGE10','KITGE11','KITGS16','KITQ200',\n" +
                        "'KITS6FW','KITS6SS','KLMU200','KS15MDS','KTG2630','KTGG200','KTGS15B','KTLMU2')\n" +
                        "AND NOT EXISTS\n" +
                        "(SELECT *\n" +
                        "FROM [LJSQLDEVTEST02,1433].calipso_intdev01.dbo.b_inventory i\n" +
                        "JOIN [LJSQLDEVTEST02,1433].calipso_intdev01.dbo.b_place_inventory pi on pi.place_inve_inventory_id = i.inventory_id\n" +
                        "WHERE pi.place_inve_to_date is null\n" +
                        "AND PART_PARTIDA_EMP like 'ID900%'\n"+
                        "AND i.inventory_serial = PART_PARTIDA_EMP COLLATE Latin1_General_CI_AS)\n" +
                        "ORDER BY NEWID()";
//        GPS SERIAL NUMBER: 900000000000197


        String result = "";
        try {
            JSONArray rs = Database.executeQueryJSON("dbOleiros", query);
            result  = Database.getValue(rs,0,"Partida_Empresa");
            result = result.replace("ID","");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }



    private static String obtenerNroSerieVLUEstandar(){
        String query =
                "SELECT PART_PARTIDA_EMP Partida_Empresa\n" +
                        "FROM STOC_ARTS\n" +
                        "JOIN STOC_PART ON PART_ARTICULO = ARTS_ARTICULO\n" +
                        "JOIN STOC_SDPP ON SDPP_PARTIDA = PART_PARTIDA\n" +
                        "JOIN STOC_DPOS ON DPOS_DEPOSITO = SDPP_DEPOSITO\n" +
                        "JOIN STOC_STDP ON STDP_ARTICULO = ARTS_ARTICULO AND STDP_DEPOSITO = SDPP_DEPOSITO\n" +
                        "JOIN STOC_CA05 ON ARTS_CLASIF_5 = STOC_CA05.CA05_CLASIF_5\n" +
                        "WHERE PART_CLASIF_1 IN ('ains','nuev')\n" +
                        "AND SDPP_STOCK_ACT > 0\n" +
                        "AND STDP_STOCK_ACT > 0\n" +
                        "AND SDPP_DEPOSITO = 179\n" +
                        "ORDER BY NEWID()";

        String result = "";
        try {
            JSONArray rs = Database.executeQueryJSON("dbWorld_Car_Security", query);
            result  = Database.getValue(rs,0,"Partida_Empresa");
            result = result.replace("ID","");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String obtenerNroSerieVLURecupero(){
        String query =
                "SELECT PART_PARTIDA_EMP Partida_Empresa\n" +
                        "FROM STOC_ARTS\n" +
                        "JOIN STOC_PART ON PART_ARTICULO = ARTS_ARTICULO\n" +
                        "JOIN STOC_SDPP ON SDPP_PARTIDA = PART_PARTIDA\n" +
                        "JOIN STOC_DPOS ON DPOS_DEPOSITO = SDPP_DEPOSITO\n" +
                        "JOIN STOC_STDP ON STDP_ARTICULO = ARTS_ARTICULO AND STDP_DEPOSITO = SDPP_DEPOSITO\n" +
                        "JOIN STOC_CA05 ON ARTS_CLASIF_5 = STOC_CA05.CA05_CLASIF_5\n" +
                        "WHERE PART_CLASIF_1 IN ('ains','nuev')\n" +
                        "AND SDPP_STOCK_ACT > 0\n" +
                        "AND STDP_STOCK_ACT > 0\n" +
                        "AND SDPP_DEPOSITO = 179\n" +
                        "AND PART_ARTICULO = 700\n" +
                        "ORDER BY NEWID()";

        String result = "";
        try {
            JSONArray rs = Database.executeQueryJSON("dbWorld_Car_Security", query);
            result  = Database.getValue(rs,0,"Partida_Empresa");
            result = result.replace("ID","");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String obtenerTramiteID(String nroSolicitud){
        String query = "SELECT caso_id FROM SolicitudDeServicios sds WHERE sds.idExterno = '<NRO_SOLICITUD>'";

        query = query.replace("<NRO_SOLICITUD>", nroSolicitud);

        String result = null;
        try {
            JSONArray rs = Database.executeQueryJSON("Boxes", query);
            result = Database.getValue(rs,0,"caso_id");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String obtenerInstanciaProceso(String tramiteID){
        String query = "SELECT idInstanciaDeProceso as idinstanciadeproceso FROM Caso WHERE id = '<NRO_TRAMITE>'";

        query = query.replace("<NRO_TRAMITE>", tramiteID);

        String result = null;
        try {
            JSONArray rs = Database.executeQueryJSON("Boxes", query);
            result = Database.getValue(rs,0,"idinstanciadeproceso");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static JSONArray obtenerEstadoUltimaTarea(String instanciaProceso, String nombreTarea, String status){
        String query = "select count(1) as count from LJ_JBPM_Boxes.dbo.AuditTaskImpl\n "
                +" WHERE processInstanceId = <NRO_INSTANCIA> \n"
                +" AND name='<NOMBRE_TAREA>' \n"
                +" AND status='<ESTADO_TAREA>'";

        query = query.replace("<NRO_INSTANCIA>", instanciaProceso);
        query = query.replace("<NOMBRE_TAREA>", nombreTarea);
        query = query.replace("<ESTADO_TAREA>", status);

        JSONArray result = null;
        try {
            result = Database.executeQueryJSON("BPM_Boxes", query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String obtenerNroSerieGPSMasivo(){
        return obtenerNroSerieGPSStrixAuto();
    }

    private static String obtenerNroSerieGPSFull(){
        return obtenerNroSerieGPSStrixAuto();
    }

    private static String obtenerNroSerieGPSFullJammingAdr(){
        return obtenerNroSerieGPSFullAntijammingAdr();
    }
}
