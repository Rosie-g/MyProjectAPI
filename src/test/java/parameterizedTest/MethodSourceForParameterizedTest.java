package parameterizedTest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MethodSourceForParameterizedTest {

    @ParameterizedTest
    @MethodSource("getManyNames")
    public void printNames(String allNames){

        System.out.println("allNames = " + allNames);
        assertTrue(allNames.length()>5);

    }




    public static List<String> getManyNames(){

        List<String > allNames = Arrays.asList("Roza","Shahriyar","Lika","Alex","Hafiz","Khatir");
        return allNames;

    }






}
