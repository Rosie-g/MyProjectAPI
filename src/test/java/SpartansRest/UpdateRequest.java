package SpartansRest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import utils.SpartanNoAuth;
import io.restassured.http.ContentType;
import java.util.LinkedHashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateRequest extends SpartanNoAuth {

    @DisplayName("PUT /spartans/{id} body as a Map")
    @Test
    public void testUpdateDataWithMap() {

        Map<String, Object> mapBody = new LinkedHashMap<>();
        mapBody.put("name", "Varu");
        mapBody.put("gender", "Male");
        mapBody.put("phone", 3456789874l);

        given()
                .log().all()
                .body(mapBody)
                .pathParam("id", 101)
                .contentType(ContentType.JSON).
                when()
                .put("/spartans/{id}").
                then()
                .statusCode(204);

    }

    @DisplayName("PUT /spartans/{id} body as POJO")
    @Test
    public void testUpdateWithPOJO() {

        Spartan spartan = new Spartan("Alex", "Male", 5433645673l);

        given()
                .log().all()
                .body(spartan)
                .pathParam("id", 101)
                .contentType(ContentType.JSON).
                when()
                .put("/spartans/{id}").
                then()
                .statusCode(204);


    }

    @DisplayName("PATCH /spartans/{id} body as String")
    @Test
    public void testPartialUpdateDataWithString() {


        String patchBody = "{\"phone\": 9876543218}";

        given()
                .log().all()
                .body(patchBody)
                .pathParam("id", 101)
                .contentType(ContentType.JSON).
                when()
                .patch("/spartans/{id}").
                then()
                .statusCode(204);


    }

    @DisplayName("DELETE /spartans/{id}")
    @Test
    public void testDeleteOne() {

        given()
                .pathParam("id", 101).
                when()
                .delete("/spartans/{id}").
                then()
                .statusCode(equalTo(204));


    }
}