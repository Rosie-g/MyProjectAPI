package utils;

import com.github.javafaker.Faker;
import pojo.Spartan;

import java.util.LinkedHashMap;
import java.util.Map;


public class SpartanUtils {

    private static Faker faker = new Faker();

    public static Map<String,Object> getRandomSpartanMap(){

        Map<String,Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", faker.name().firstName());
        bodyMap.put("gender",faker.demographic().sex());
        bodyMap.put("phone", faker.number().numberBetween(5_000_000_000L, 10_000_000_000L));

        return bodyMap;


    }

    public static Spartan getRandomSpartanPOJO(){

        Spartan spartan = new Spartan();

        spartan.setName(faker.name().firstName());
        spartan.setGender(faker.demographic().sex());
        spartan.setPhone(faker.number().numberBetween(5_000_000_000L, 10_000_000_000L));

        return spartan;

    }


}
