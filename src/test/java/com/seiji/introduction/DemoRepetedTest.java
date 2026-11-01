package com.seiji.introduction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

public class DemoRepetedTest {

    SimpleMath math;

    @BeforeEach 
    void beforeEachMethod() {
        IO.println("Running @BeforeEach method!");
        math = new  SimpleMath();
    }
    
    // @RepeatedTest(3)
    @RepeatedTest(value = 3, name = "{displayName}. Repetition " + 
        "{currentRepetition} of {totalRepetitions}!" // You can also format the output with Junit variables
    )
    @DisplayName("Divide by OD")
    void testDivide_When_ItsZeroDouble(
        RepetitionInfo repetitionInfo // We can also retrieve repetition informations of Junit
    ) {

         IO.println("Repetition " + repetitionInfo.getCurrentRepetition()
                + "º of " + repetitionInfo.getTotalRepetitions() + " repetitions."
            );

        double firstNumber = 6.2D;
        double secondNumber = 0D;
        String expectedMessage = "Impossible to divide by zero";

        ArithmeticException actual = assertThrows(ArithmeticException.class, 
            () -> math.division(firstNumber, secondNumber));
        
        assertEquals(expectedMessage, actual.getMessage(), () -> "Unexpected exception message");

    }
}
