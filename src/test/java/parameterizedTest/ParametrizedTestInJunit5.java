package parameterizedTest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class ParametrizedTestInJunit5 {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testNums(int num) {

        System.out.println("num = " + num);
        assertTrue(num > 0);


    }

    @ParameterizedTest
    @ValueSource(strings = {"Roza","Shahriyar","Lika","Alex","Hafiz","Khatir"})
    public void testNames(String names){

        System.out.println("names = " + names);
        assertTrue(names.length()>=4);
    }

    @ParameterizedTest
    @ValueSource(shorts = {22030,22031, 22032, 22033 , 22034, 22035, 22036})
    public void testShorts(short zip){

        given()
                .baseUri("https://api.zippopotam.us")
                .pathParam("zipcode",zip)
                .log().all().
                when()
                .get("/us/{zipcode}").
                then()
                .statusCode(200);



    }


}
