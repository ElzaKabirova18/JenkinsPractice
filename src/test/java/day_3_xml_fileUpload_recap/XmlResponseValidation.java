package day_3_xml_fileUpload_recap;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class XmlResponseValidation {
    static String id = "";

    @Test
    public void test_1_createTraveller(){
        String url = "http://restapi.adequateshop.com/api/Traveler";
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String address = faker.address().fullAddress();

        String requestBody ="<?xml version=\"1.0\"?>\n" +
                "<Travelerinformation>\n" +
                "    <name>"+firstName+"</name>\n" +
                "    <email>"+email+"</email>\n" +
                "    <adderes>"+address+"</adderes>\n" +
                "</Travelerinformation>";



        Response response = RestAssured.given()
                .contentType(ContentType.XML)
                .body(requestBody)
                .post(url);

        // response.prettyPrint();
        // x.Travelerinformation.id

        id = response.xmlPath().getString("Travelerinformation.id");

    }

    @Test
    public void test_2_getTraveller(){
        String url = "http://restapi.adequateshop.com/api/Traveler/"+ id;

        /**
         * TASK USE GET REQUEST AND pretty print response
         */
        Response response = RestAssured.get(url);
        response.prettyPrint();

        String actualName = response.xmlPath().getString("Travelerinformation.name");
        String actualEmail = response.xmlPath().getString("Travelerinformation.email");

        Assert.assertNotNull(actualName);
        Assert.assertNotNull(actualEmail);

    }


    @Test
    public void test3GetListOfTravellers() {
        String url = "http://restapi.adequateshop.com/api/Traveler";

        Response response = RestAssured.get(url);

        String actualId = response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].id");
        String actualName = response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name");
        String actualEmail = response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].email");

        Assert.assertNotNull(actualId);
        Assert.assertNotNull(actualName);
        Assert.assertNotNull(actualEmail);

        System.out.println("traveller id " + actualId +
                " Traveller name: " + actualName + " Traveller email " +actualEmail);
    }

    @Test
    public void printAllListOfTravellers() {
        String url = "http://restapi.adequateshop.com/api/Traveler";

        Response response = RestAssured.get(url);

        int sizeOfList = response.xmlPath().getList("TravelerinformationResponse.travelers.Travelerinformation").size();
        for (int i = 0; i < sizeOfList; i++) {
            String actualId = response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[" + i + "].id");
            String actualName = response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[" + i + "].name");
            String actualEmail = response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[" + i + "].email");
            System.out.println(actualId);
            System.out.println(actualName);
            System.out.println(actualEmail);
            System.out.println("======================");
        }

    }

}
