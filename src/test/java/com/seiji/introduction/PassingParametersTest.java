package com.seiji.introduction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PassingParametersTest {
    
    /*
        The mainly idea here is ways to pass a list of values to be tested in 
        our methods
            - Directly: @ValueSource(strings = {"Bill", "Steve", "Rockfeller"})
            - @MethodSource("testDivisionInputparameters"): a method that return a list of Stream<Arguments> 
            - @CsvSource: passing a CSV string
            - CsvFileSource: passing a CSV file
    */

    SimpleMath math;

    @BeforeEach 
    void beforeEachMethod() {
        math = new SimpleMath();
    }

    @ParameterizedTest 
    @ValueSource(strings = {"Bill", "Steve", "Rockfeller"})
    void testValueSource(String firstName) {
        assertNotNull(firstName);
    }

    @DisplayName("Test firstNumber / secondNumber = expected")
    @ParameterizedTest 
    @MethodSource("testDivisionInputparameters") 
    void testDivision(double firstNumber, double secondNumber, double expected) {

        double actual = math.division(firstNumber, secondNumber);

        assertEquals(expected, actual, 2D,
            () ->  firstNumber + " / " + secondNumber + "did not produce" + expected + "!");
    }

    /*
        Arguments is an abstraction class that provides access to an array 
        of objects to be used for invoking a @ParameterizedTest method.
    */

    public static Stream<Arguments> testDivisionInputparameters() {
        return Stream.of(
            Arguments.of(6.2D, 2D, 3.1D),
            Arguments.of(71D, 14D, 5.07D),
            Arguments.of(18.0D, 3.1D, 5.9D)
        );
    }

    @DisplayName("Test firstNumber / secondNumber = expected with CSV Format")
    @ParameterizedTest 
    @CsvSource({
        "6.2, 2, 3.1",
        "71, 14, 5.07",
        "18.0, 3.1, 5.9"
    })
    void testDivisionCSVString(double firstNumber, double secondNumber, double expected) {

        double actual = math.division(firstNumber, secondNumber);

        assertEquals(expected, actual, 2D,
            () ->  firstNumber + " / " + secondNumber + "did not produce" + expected + "!");
    }

    @DisplayName("Test firstNumber / secondNumber = expected with CSV File")
    @ParameterizedTest 
    @CsvFileSource(resources = "/testDivision.csv")
    void testDivisionCSVFile(double firstNumber, double secondNumber, double expected) {

        double actual = math.division(firstNumber, secondNumber);

        assertEquals(expected, actual, 2D,
            () ->  firstNumber + " / " + secondNumber + "did not produce" + expected + "!");
    }

}
