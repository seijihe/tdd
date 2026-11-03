package com.seiji.workspace.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

// @Order(1) // Used at class level to control the order of class tests execution. This one you also have to enable at resource > junit-platform.properties file. Rarely used
// @TestMethodOrder(MethodOrderer.MethodName.class) // Method ordered by name
// @TestMethodOrder(MethodOrderer.Random.class) // Randonly, to verify that there is no dependency between methods, mainly for UnitTests
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Used at Integration tests when the order of a test maybe depends of the result of other
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // PER_METHOD or PER_CLASS
public class MethodOrderedRandonlyTest {

    StringBuilder actualValue = new StringBuilder("");

    /* 
        The result bellow depends of Lifecycle Instance configuration
        PER_METHOD: The actual value of string is: 0 (Instance value is not shared across methods)
        PER_CLASS: The actual value of string is: 0123 (Instance value is shared across methods, since
        class represent the instance)
    */

    @AfterEach 
    void afterEach() {
        IO.println("The actual value of string is: " + actualValue); // The result here vary with PER_METHOD or PER_CLASS options
    }
    
    @Test 
    @Order(0)
    void testA() {
        IO.println("Running Test A");
        actualValue.append("0");
    }

    @Test 
    @Order(1)
    void testB() {
        IO.println("Running Test B");
        actualValue.append("1");
    }

    @Test 
    @Order(3)
    void testC() {
        IO.println("Running Test C");
        actualValue.append("3");
    }

    @Test 
    @Order(2)
    void testD() {
        IO.println("Running Test D");
        actualValue.append("2");
    }
}
