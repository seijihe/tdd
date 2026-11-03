package com.seiji.workspace.junit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.seiji.wokspace.junit.SimpleMath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class SimpleMathTest {

    SimpleMath math;

    @BeforeAll
    static void setup() {
        IO.println("Running @BeforeAll method!");
    }

    @AfterAll 
    static void cleanup() {
        IO.print("Running @AfterAll method!");
    }

    @BeforeEach 
    void beforeEachMethod() {
        IO.println("Running @BeforeEach method!");
        math = new  SimpleMath();
    }

    // @Disabled("TOD O: We still need work on it") // Until Comment @Test to disable the test
    @Test // test[System_Under_Test]_[Condition_or_State_Chance]_[Expected_Result]
    @DisplayName("Test 6.2 + 2 = 8.2") // Until label method by a huge name
    void testSum_When_SixDotTwoIsAddedByTwo_ShouldReturnEightDotTwo() { 

        // Behavior-Driven-Developmend and AAA (Triple A)
        // Given | Arrange
        double firstNumber = 6.2D;
        double secondNumber = 2D;
        double expected = 8.2D;

        // When | Act
        Double actual = math.sum(6.2D, 2D);
        

        // Then | Assert
        // Lazy message with lambda to create string only when the equals fails
        assertEquals(expected, actual,
            () -> firstNumber + " + " + secondNumber + "did not produce" + expected + "!");
    }

    @Test 
    @DisplayName("Divide by OD")
    void testDivide_When_ItsZeroDouble() {
        double firstNumber = 6.2D;
        double secondNumber = 0D;
        String expectedMessage = "Impossible to divide by zero";

        ArithmeticException actual = assertThrows(ArithmeticException.class, 
            () -> math.division(firstNumber, secondNumber));
        
        assertEquals(expectedMessage, actual.getMessage(), () -> "Unexpected exception message");

    }

    @Test 
    @DisplayName("Test 8 - 9 = -1")
    void testSubtraction_When_EightSubtractNine_ShoudResultMinusOne() {

        double firstNumber = 8D;
        double secondNumber = 9D;

        double actual = math.subtraction(firstNumber, secondNumber);
        double expected = -1D;

        assertEquals(expected, actual,
            () -> firstNumber + " - " + secondNumber + "did not produce" + expected + "!");
    }

    @Test
    @DisplayName("Test 4 x 5 = 20")
    void testMultiply_When_FourMultipliedForFive_ShoudReturnTwenty() {

        double firstNumber = 4D;
        double secondNumber = 5D;

        double actual = math.multiplication(firstNumber, secondNumber);

        double expected = 20D;
        assertEquals(expected, actual,
            () ->  firstNumber + " * " + secondNumber + "did not produce" + expected + "!");
    }

    @Test
    @DisplayName("Test 4 / 5 = 0.8")
    void testDivision_When_FourDividedByFive_ShoudReturnZeroDotEight() {

        double firstNumber = 4D;
        double secondNumber = 5D;

        double actual = math.division(firstNumber, secondNumber);

        double expected = 0.8D;
        assertEquals(expected, actual,
            () ->  firstNumber + " / " + secondNumber + "did not produce" + expected + "!");
    }

    @Test
    @DisplayName("Test (4 + 5) / 2 = 4.5")
    void testMean_When_FourAndFivenMean_ShoudResultFourDotFive() {

        double firstNumber = 4D;
        double secondNumber = 5D;

        double actual = math.mean(firstNumber, secondNumber);

        double expected = 4.5D;
        assertEquals(expected, actual,
            () ->  firstNumber + " and " + secondNumber + "did not produce an mean of" + expected + "!");
    }

    @Test
    @DisplayName("Test Square(4) = 2")
    void testSquareRoot_When_SquareOfFour_ShoudReturnTwo() {

        double firstNumber = 4D;

        double actual = math.squareRoot(firstNumber);

        double expected = 2D;
        assertEquals(expected, actual,
            () ->  firstNumber + " square will not produce" + expected + "!");
    }


}
