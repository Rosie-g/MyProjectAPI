package utils;



import static io.restassured.RestAssured.*;
import io.restassured.RestAssured.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class SpartanNoAuth {

    @BeforeAll
    public static void init(){

        baseURI = "http://18.213.0.89:8000";
        basePath = "/api";

    }

    @AfterAll
    public static void close(){

        reset();
    }

}
