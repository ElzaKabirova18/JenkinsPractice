package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.CustomResponse;
import pojo.RequestBody;

import java.util.Map;

import static utilities.CashwiseAuthorization.getToken;

public class APIRunner {
    private static CustomResponse customResponse;
    private static CustomResponse[] customResponseArray;

    /** Day_5 APIRunner (Description about this class)
     * APIRunner class contains custom methods for CRUD commands
     * With help of this class we can focus on test logic, instead of automation
     * script
     */

    /**
     * Executes an HTTP GET request using RestAssured and retrieves the response as a CustomResponse object.
     *
     * @param path The endpoint path to be appended to the base URL.
     * @return A CustomResponse object representing the response of the GET request.
     *
     */
    public static CustomResponse runGET( String path  ){
        // step - 1
        String  url =Config.getProperty("baseUrl") + path;
        // step - 2
        Response response = RestAssured.given()
                .auth().oauth2(   getToken()    )
                .get( url );
        // step - 3
        ObjectMapper mapper = new ObjectMapper();
        // step -4
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class ) ;
        } catch (JsonProcessingException e) {
            // It's nested try-catch; Because we have to handle Array of ==> customResponseArray
            System.out.println( " This is a list response ");
            try {
                customResponseArray = mapper.readValue( response.asString(), CustomResponse[].class );
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

        }
        return customResponse;
    }


    public static CustomResponse runPOST(String path , RequestBody requestBody){
        // step - 1
        String  url =Config.getProperty("baseUrl") + path;
        // step - 2
        Response response = RestAssured.given()
                .auth().oauth2(   getToken()    )
                .contentType(ContentType.JSON)
                .body( requestBody )
                .post( url );

        response.prettyPrint();

        // step - 3
        ObjectMapper mapper = new ObjectMapper();
        // step -4
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class ) ;
        } catch (JsonProcessingException e) {
            // It's nested try-catch; Because we have to handle Array of ==> customResponseArray
            System.out.println( " This is a list response ");
            try {
                customResponseArray = mapper.readValue( response.asString(), CustomResponse[].class );
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

        }
        System.out.println(  "My status code: "+ response.statusCode() );
        return customResponse;
    }


    public static CustomResponse runDELETE(String path ){
        // step - 1
        String  url =Config.getProperty("baseUrl") + path;
        // step - 2
        Response response = RestAssured.given()
                .auth().oauth2(   getToken()    )
                .delete( url );

        // step - 3
        ObjectMapper mapper = new ObjectMapper();
        // step -4
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class ) ;
        } catch (JsonProcessingException e) {
            // It's nested try-catch; Because we have to handle Array of ==> customResponseArray
            System.out.println( "This is a list response ");
            try {
                customResponseArray = mapper.readValue( response.asString(), CustomResponse[].class );
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

        }
        System.out.println(  "My status code: "+ response.statusCode() );
        return customResponse;
    }

    public static CustomResponse runGET(String path, Map<String, Object> param){
        // step - 1
        String  url =Config.getProperty("baseUrl") + path;
        // step - 2
        Response response = RestAssured.given()
                .auth().oauth2(   getToken()    )
                .contentType(ContentType.JSON)
                .params(param)
                .get( url );
        // step - 3
        ObjectMapper mapper = new ObjectMapper();
        // step -4
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class ) ;
        } catch (JsonProcessingException e) {
            // It's nested try-catch; Because we have to handle Array of ==> customResponseArray
            System.out.println( " This is a list response ");
            try {
                customResponseArray = mapper.readValue( response.asString(), CustomResponse[].class );
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

        }
        return customResponse;
    }

    // I can read value of my private variable with help of getter method
    public static CustomResponse getCustomResponse(){
        return customResponse;
    }

    public static CustomResponse[] getCustomResponseArray(){
        return customResponseArray;
    }



}