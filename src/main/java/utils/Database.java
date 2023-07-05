package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Connection connectToDatabase(String database_id) throws SQLException, IOException, org.json.simple.parser.ParseException{
        Connection conn = null;

        try{
            java.sql.DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            String connString = getConnectionString(database_id);
            conn = java.sql.DriverManager.getConnection(connString);
            if (conn != null) {
//                System.out.println("Connected");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static ResultSet executeQuery(String database_id, String query) throws SQLException, IOException, org.json.simple.parser.ParseException{
        Connection conn = connectToDatabase(database_id);
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery(query);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(query);
            conn.close();
        }

        return result;
    }

    public static JSONArray executeQueryJSON(String database_id, String query) throws SQLException, IOException, org.json.simple.parser.ParseException{
        Connection conn = connectToDatabase(database_id);
        JSONArray result = null;
        ResultSet rs = null;
        System.out.println(query);

        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            result = convertToJSON(rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(query);
            conn.close();
        }

        return result;
    }
    public static int executeStoredProcedureGenerarSolicitud(String database_id, String store) throws SQLException, IOException, ParseException {
        Connection con = connectToDatabase(database_id);

        try(CallableStatement cstmt = con.prepareCall("{call ci.CrearSolicitud(?, ?)}");) {
            cstmt.setString(1, store);
            cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
            cstmt.execute();
            int solicitud = cstmt.getInt(2);
            System.out.println("SOLICITUD ID: " + Integer.toString(solicitud));
            return solicitud;
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(store);
            con.close();
        }

        return 0;
    }

    private static String getConnectionString(String database_id) throws IOException, org.json.simple.parser.ParseException, SQLException{
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("properties/databases.json");
        Object obj = jsonParser.parse(reader);
        JSONObject json = (JSONObject) obj;
        JSONObject database =  (JSONObject) json.get(database_id);

        String DBServer = (database.get("server")).toString();
        String DBName = (database.get("database")).toString();
        String DBUsername = (database.get("user")).toString();
        String DBPassword = (database.get("password")).toString();

        // jdbc:sqlserver://192.168.166.13:1533;database=Ventas;username=boxestestapp;password=V2qHTqkkhD;sslProtocol=TLSv1.1;
        String connectionString = String.format("jdbc:sqlserver://%s;database=%s;trustServerCertificate=true;username=%s;password=%s",DBServer, DBName, DBUsername, DBPassword);
        return connectionString;
    }

    private static JSONArray convertToJSON(ResultSet rs){
        JSONArray json = new JSONArray();
        try{
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()) {
                int numColumns = rsmd.getColumnCount();
                JSONObject obj = new JSONObject();
                for (int i=1; i<=numColumns; i++) {
                    String column_name = rsmd.getColumnName(i);
                    obj.put(column_name, rs.getObject(column_name));
                }

                json.add(obj);
            }
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    public static String getValue(JSONArray results, int row, String columnName){
        if(results == null || results.size() == 0) return "";
        return ((JSONObject) results.get(row)).get(columnName).toString();
    }

    public static List<String> getValues(JSONArray results, String columnName){
        List<String> newArrayOfValues = new ArrayList<String>();

        for(int i = 0; i < results.size(); i++){
            newArrayOfValues.add(((JSONObject) results.get(i)).get(columnName).toString());
        }

        return newArrayOfValues;
    }



}