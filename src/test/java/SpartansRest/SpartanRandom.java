package SpartansRest;

import io.restassured.path.json.JsonPath;
import utils.SpartanNoAuth;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import utils.SpartanUtils;

import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SpartanRandom extends SpartanNoAuth {

    @DisplayName("POST /spartans with random Data")
    @Test
    public void addOneRandomSpartanTest(){

        Map<String ,Object> bodyMap = SpartanUtils.getRandomSpartanMap();

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("data.name",is(bodyMap.get("name")))
                .body("data.gender", is(bodyMap.get("gender")))
                .body("data.phone",is(bodyMap.get("phone")));

    }

    @DisplayName("POST /spartans with random POJO")
    @Test
    public void addOneRandomPOJOTest(){

        Spartan spartan = SpartanUtils.getRandomSpartanPOJO();

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .body("data.name",is(spartan.getName()))
                .body("data.gender",is(spartan.getGender()))
                .body("data.phone",is(spartan.getPhone()));


    }

    @DisplayName("POST /spartans and send GET /spartans/{id} to verify")
    @Test
    public void testAddOneDataThenGetOneDataToVerify(){

        Spartan spartan = SpartanUtils.getRandomSpartanPOJO();

        Response response =

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan).
                when()
                .post("/spartans")
                .prettyPeek();

        int newId = response.path("data.id");
        assertThat(response.statusCode(),is(201));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id",newId).
                when()
                .get("/spartans/{id}").
                then()
                .log().body()
                .statusCode(200)
                .body("id",is(newId))
                .body("name",is(spartan.getName()))
                .body("gender",is(spartan.getGender()))
                .body("phone",is(spartan.getPhone()));



    }

    @DisplayName("POST /spartans and send GET /spartans/{id} to verify 2")
    @Test
    public void testAddOneDataThenGetOneDataToVerifyInTheChain(){

        Spartan spartan = SpartanUtils.getRandomSpartanPOJO();

        int newID =

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201).
        extract()
                .path("data.id"); // we can use both ways here to extract data
        //.jsonPath().getInt("data.id");

        System.out.println("newID = " + newID);

    }

    @DisplayName("POST /spartans and send GET /spartans/{id} to verify 3")
    @Test
    public void testAddOneDataThenExtractHeader(){

        Spartan spartan = SpartanUtils.getRandomSpartanPOJO();

        String location =

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan).
                when()
                .post("/spartans").
                then()
                .statusCode(201)
                .extract()
                .header("Location");

        System.out.println("location = " + location);




    }








    }
