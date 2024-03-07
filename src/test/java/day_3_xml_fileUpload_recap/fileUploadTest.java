package day_3_xml_fileUpload_recap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CashwiseAuthorization;

import java.io.File;

public class fileUploadTest {

    @Test
    public void fileUpload() {
        String url ="https://backend.cashwise.us/api/myaccount/file/v2";
        String token = CashwiseAuthorization.getToken();

        String filePath = "pom.xml";

        File file = new File(filePath);
        Response response = RestAssured.given()
                .auth().oauth2(token)
                .multiPart("file" , file, "multiple/data")
                .post(url);

        System.out.println("My status code: " + response.statusCode());

        String fileName = response.jsonPath().getString("fileName");
        System.out.println("my file name: " + fileName);

        response.prettyPrint();

    }
}
