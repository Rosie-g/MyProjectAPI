package SpartansRest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.SpartanNoAuth;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GetRequests extends SpartanNoAuth {

    @DisplayName("GET /spartans")
    @Test
    public void getAllSpartans(){

        when()
              .get("/spartans").
        then()
                .log().all()
                .statusCode(200);

    }

    @DisplayName("GET /spartans/{id}")
    @Test
    public void getOneSpartan(){

        given()
                .pathParam("id",106).
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode(200)
                .body("name", is("Lika"))
                .contentType(ContentType.JSON);

    }

    @DisplayName("Test GET /spartans/search Endpoint")
    @Test
    public void testSearch(){

        Response response =

        given()
                .queryParam("nameContains","a")
                .queryParam("gender","Female").
        when()
                .get("/spartans/search").prettyPeek()
                ;

        System.out.println("Total elements = " + response.path("totalElement"));

        assertAll(
            () -> assertThat(response.statusCode(), equalTo(200)),
            () -> assertThat(response.contentType(),is("application/json"))

        ) ;

    }

    @DisplayName("Spartan Test with path variable anq query param")
    @Test
    public void getOneSpartanVar(){

        given()
                .pathParam("spartan_id", 106).
        when()
                .get("/spartans/{spartan_id}").

         then()
                .log().all()
                .statusCode(200);

    }




}
