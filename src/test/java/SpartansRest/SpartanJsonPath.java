package SpartansRest;

import utils.SpartanNoAuth;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class SpartanJsonPath extends SpartanNoAuth {

    @Test
    public void testOne(){

        Response response =

        given()
                .pathParam("id", 106)
                .log().all().
        when()
                .get("/spartans/{id}").prettyPeek();

        int id = response.path("id");
        System.out.println("id = " + id);

        String name = response.path("name");
        System.out.println("name = " + name);

        long phone = response.path("phone");
        System.out.println("phone = " + phone);

        JsonPath jsonPath = response.jsonPath();

        System.out.println("------------------------------------------");

        // Below just 2 ways how we can get Map result from response using JsonPath method

        System.out.println("Get Map " + jsonPath.getMap(""));

        Map<String,Object> map = jsonPath.getMap("");
        System.out.println("Another Map " + map);

    }

    @DisplayName("Extract data from GET /spartans")
    @Test
    public void testGetAllSpartan(){

        JsonPath jp = get("/spartans").jsonPath();

        System.out.println("jp.getInt(\"id[0]\") = " + jp.getInt("id[0]"));

        System.out.println("jp.getString(\"name[0]\") = " + jp.getString("name[0]"));

        System.out.println("jp.getString(\"[0]\") = " + jp.getString("[0]"));

        System.out.println("jp.getMap(\"[0]\") = " + jp.getMap("[0]"));

        System.out.println("jp.getInt(\"[0].id\") = " + jp.getInt("[0].id"));
    }

    @DisplayName("Extract data from GET /spartans/search ")
    @Test
    public void testGetSearchSpartan(){

        JsonPath jp =

        given()
                .log().all()
                .queryParam("nameContains","Lika")
                .queryParam("gender","Female").
                when()
                .get("/spartans/search")
                .jsonPath()
                .prettyPeek();

        // find out our first lady id, second lady name

        System.out.println("jp.getInt(\"content.id[0]\") = " + jp.getInt("content.id[0]"));
        System.out.println("jp.getString(\"content.name[1]\") = " + jp.getString("content.name[1]"));

        // store first object in the map
        Map<String,Object> firstObj = jp.getMap("content[0]");
        System.out.println("firstObj = " + firstObj);


    }

    @DisplayName("Saving json array fields into List")
    @Test
    public void testSavingJsonArrayFieldsIntoList(){

        JsonPath jp =

       given()
               .queryParam("nameContains","k")
               .queryParam("gender","Female")
               .log().all().
               when()
               .get("/spartans/search")
               .jsonPath()
               .prettyPeek();

        System.out.println("jp.getList(\"content.id\") = " + jp.getList("content.id"));
        System.out.println("jp.getList(\"content.name\") = " + jp.getList("content.name"));
        System.out.println("jp.getList(\"content.phone\") = " + jp.getList("content.phone"));
        System.out.println("====================================================================");
        List<Integer> allId = jp.getList("content.id");
        System.out.println("allId = " + allId);

        List<Integer> allId2 = jp.getList("content.id",Integer.class);
        System.out.println("allId2 = " + allId2);


    }

    @DisplayName("Get List Practice for GET /spartans")
    @Test
    public void testGetListOutOfAllSpartans(){

        JsonPath jp = get("/spartans").jsonPath();


        List<Integer> allId = jp.getList("id");
        assertThat(allId,hasSize(113));

    }





    }
