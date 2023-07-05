package utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentConsumer {
    private static final Map<String, Dotenv> instances = new HashMap<String, Dotenv>();

    public static Dotenv getInstance(String envFIleName){
        Dotenv instance = instances.get(envFIleName);
        if (instance == null) {
            try {
                instance = Dotenv.configure().directory("properties").filename(envFIleName + ".env").load();
                instances.put(envFIleName, instance);
            } catch (Exception e) {
                System.out.println("ERROR AL OBTENER EL ENVIRONMENT " + envFIleName);
            }
        }
        return instance;
    }
}
