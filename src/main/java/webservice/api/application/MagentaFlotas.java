package webservice.api.application;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.EnvironmentConsumer;
import webservice.pojo.*;

import static io.restassured.RestAssured.given;
import static webservice.api.SpecBuilder.getResponseSpec;

import static webservice.api.Route.*;
public class MagentaFlotas {
    static Dotenv env;

    public MagentaFlotas() {
        env = EnvironmentConsumer.getInstance("properties");
    }

    public static LoginMagenta login(){
        String magentaUrl = env.get("magenta_flotas_url");

        RequestSpecification loginRequest = new RequestSpecBuilder()
                .setBaseUri(magentaUrl)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .addHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8")
                .addHeader("Accept", "application/json, text/plain, */*")
                .setBody(String.format("username=%s&password=%s&grant_type=client_credentials&scope=all",env.get("magenta_flotas_user"), env.get("magenta_flotas_password")))
                .build();

        return given(loginRequest)
                .auth().basic(env.get("magenta_flotas_user"), env.get("magenta_flotas_password"))
                .when().post(AUTH)
                .then().spec(getResponseSpec())
                .extract().as(LoginMagenta.class);
    }

    public static VehiculoMagentaFlotas[] buscarVehiculo(String token, String dominio){
        RequestSpecification buscarVehiculosRequest = new RequestSpecBuilder()
                .setBaseUri(env.get("magenta_flotas_url"))
                .log(LogDetail.ALL)
                .addHeader("Accept", "application/json; charset=utf-8")
                .build();

        return given(buscarVehiculosRequest).
              header("Authorization", "Bearer "+token).
                when().get(THINGS+"?info.domain="+dominio).
                then().spec(getResponseSpec()).
                extract().as(VehiculoMagentaFlotas[].class);
    }
}
