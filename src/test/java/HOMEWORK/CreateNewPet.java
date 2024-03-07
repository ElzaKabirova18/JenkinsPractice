package HOMEWORK;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateNewPet {
    String id ="";

    @Test
    public void test1CreateNewDog() {

        String url = "https://petstore.swagger.io/v2/pet";
        Faker faker = new Faker();

        String DogName = faker.dog().name();
        String requestBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Pet>\n" +
                "    <id>0</id>\n" +
                "    <Category>\n" +
                "        <id>0</id>\n" +
                "        <name>Dog</name>\n" +
                "    </Category>\n" +
                "    <name>"+DogName+"</name>\n" +
                "    <photoUrls>\n" +
                "        <photoUrl>string</photoUrl>\n" +
                "    </photoUrls>\n" +
                "    <tags>\n" +
                "        <Tag>\n" +
                "            <id>0</id>\n" +
                "            <name>string</name>\n" +
                "        </Tag>\n" +
                "    </tags>\n" +
                "    <status>available</status>\n" +
                "</Pet>";

        Response response = RestAssured.given().contentType(ContentType.XML).body(requestBody).post(url);
        response.prettyPrint();

        id = response.jsonPath().getString("id");
        System.out.println(id);
        response.prettyPrint();
    }

    @Test
    public void test2GetDogById() {
        String url = "https://petstore.swagger.io/v2/pet/" + id;
        Response response = RestAssured.given().get(url);
        response.prettyPrint();

//        String actualName = response.xmlPath().getString("name");
//        String actualStatus = response.xmlPath().getString("status");
//
//        Assert.assertNotNull(actualName);
//        Assert.assertNotNull(actualStatus);
    }

    @Test
    public void  test3DeleteDogById() {
        String url = "https://petstore.swagger.io/v2/pet/" + id;
        Response response = RestAssured.delete(url);
        response.prettyPrint();

    }
}
