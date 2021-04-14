package parameterizedTest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;


public class CsvSourceParameterizedTest {

    @ParameterizedTest
    @CsvSource({"NY, New York",
            "CO, Denver",
            "VA, Fairfax",
            "VA, Arlington",
            "MA, Boston",
            "NY, New York",
            "MD, Annapolis"})
    public void testStateCityToZipEndpoint(String stateArg , String cityArg){

        int countPlace =

        given()
                .baseUri("https://api.zippopotam.us")
                .pathParam("state", stateArg)
                .pathParam("city",cityArg)
                .log().uri().
                when()
                .get("/us/{state}/{city}")
                .then()
                .statusCode(200).
                extract()
                .jsonPath()
                .getList("places")
                .size();


    }

    @ParameterizedTest
    @CsvFileSource(resources = "/state_city_zipCount.csv" , numLinesToSkip = 1 )
    public void testStateCityToZipEndpointWithCSVFile(String stateArg, String cityArg , int zipArg  ) {

    given()
            .baseUri("https://api.zippopotam.us")
            .log().all()
            .pathParam("state", stateArg)
            .pathParam("city", cityArg).
            when()
            .get("/us/{state}/{city}").
            then()
            .statusCode(200);


    }

}
