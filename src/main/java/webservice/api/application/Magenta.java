package webservice.api.application;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.EnvironmentConsumer;
import webservice.pojo.ClienteMagenta;
import webservice.pojo.LoginMagenta;

import static io.restassured.RestAssured.given;
import static webservice.api.SpecBuilder.getResponseSpec;

import static webservice.api.Route.*;
public class Magenta {
    static Dotenv env;

    public Magenta() {
        env = EnvironmentConsumer.getInstance("properties");
    }
    public LoginMagenta login(){
        String magentaUrl = env.get("magenta_url");

        RequestSpecification loginRequest = new RequestSpecBuilder()
                .setBaseUri(magentaUrl)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .addHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8")
                .addHeader("Accept", "application/json, text/plain, */*")
                .setBody("grant_type=client_credentials&scope=all")
                .build();

        ResponseSpecification loginResponse = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();

        return given(loginRequest)
                .auth().basic(env.get("magenta_user"), env.get("magenta_password"))
                .when().post(AUTH)
                .then().spec(getResponseSpec())
                .extract().as(LoginMagenta.class);
    }

    public ClienteMagenta[] validateAccount(String token, String dni){
        RequestSpecification validateAccountRequest = new RequestSpecBuilder()
                .setBaseUri(env.get("magenta_url"))
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .addHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8")
                .addHeader("Accept", "application/json, text/plain, */*")
                .build();



        return given(validateAccountRequest).
                header("Authorization", "Bearer "+token).
                when().get(ACCOUNT+"?identification_type=DNI&identification_number="+dni).
                then().spec(getResponseSpec()).
                extract().as(ClienteMagenta[].class);
    }
}
