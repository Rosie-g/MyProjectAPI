package SpartansRest;

import lombok.val;
import org.checkerframework.checker.units.qual.C;
import pojo.Spartan;
import utils.SpartanNoAuth;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PostRequests extends SpartanNoAuth {

    @DisplayName("POST /spartans/ with String")
    @Test
    public void testPostSpartanWithStringObject(){


        String spartan = "{\n" +

                "            \"name\": \"Shah\",\n" +
                "            \"gender\": \"Male\",\n" +
                "            \"phone\": 8726150217\n" +
                "        }";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan).
        when()
                .post("/spartans").
        then()
                .log().all().
                statusCode(201)
                .contentType(ContentType.JSON)
                ;

    }
    @DisplayName("POST /spartans with external file")
    @Test
    public void testPostDataWithJsonFileAsBody(){

        File file = new File("oneSpartan.json");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(file).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                ;
        
    }

    @DisplayName("POST /spartans with Map Object")
    @Test
    public void testPostDataWithMapObjectAsBody() {

      /*  {
            "name": "Dorota",
            "gender": "Female",
            "phone": 9877899876
            
          }
        
       */

        Map<String,Object> map = new LinkedHashMap<>();
        map.put("name","Dorota");
        map.put("gender", "Female");
        map.put("phone", 9877899876l);

        System.out.println("map = " + map);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(map).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201);

    }

    @DisplayName("POST /spartans with POJO")
    @Test
    public void testPostDataWithPOJOAsBody(){

        Spartan spartan = new Spartan("Roza","Female",2344322345l);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201);

    }




}
