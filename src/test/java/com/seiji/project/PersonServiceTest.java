package com.seiji.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.seiji.model.Person;
import com.seiji.service.IPersonService;
import com.seiji.service.PersonService;

public class PersonServiceTest {

    Person person;
    IPersonService service;

    @BeforeEach 
    void setup() {
        person = new Person(
            "Steve Jobs",
            56,
            "Apple"
        );

        service = new PersonService();
    }
     
    @DisplayName("When create a person with sucess should return a person object")
    @Test
    void testCreatePerson_WhenSucess_ShoudReturnPersonObject() {
        
        // Arrange
        IPersonService serviceExample = new PersonService();

        // Act
        Person actual = serviceExample.createPerson(person);

        // Assert
        assertNotNull(actual, () -> "The createPerson() shoud not return null");

    }

    @DisplayName("When Create a Person with Sucess should contains FirstName in Returned Person")
    @Test 
    void testCreatePerson_WithSucess_ShouldContainsFirstNameInReturnedPerson() {

        Person actual = service.createPerson(person);

        assertEquals(person.getName(), actual.getName() , () -> "The first name is different");

    }

    @DisplayName("When create a Person with Sucess should return an object with the same parameters informed")
    @Test  
    void testCreatePerson_WhenSucess_ShouldReturnObjectWithSameParametersInformed() {

        Person actual = service.createPerson(person);

        Boolean equals = actual.equals(person);

        assertTrue(equals, () -> "Object create is the same as informed");

    }

    @DisplayName("When create a person with null parameter should return an exception")
    @Test 
    void testCreatePerson_WhenParameterNull_ShouldReturnException() {

        person.setCompany(null);
        
        assertThrows(
            IllegalArgumentException.class, 
            () -> service.createPerson(person),
            () -> "Empty parameter at returned object"
        );

    }

    @DisplayName("When throws an exception of null or blank parameter should create the correct exception message")
    @Test 
    void testWhenThrowsExceptionNullBlankParameter_ShouldReturnCorrectExceptionMessage() {
        
        person.setCompany(null);
        
        String expectedMessage = "The Person email is null or empty";

        try {
            service.createPerson(person);
        } catch (Exception e) {
            assertEquals(e.getMessage(), expectedMessage);
        }

        // Could also be

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> service.createPerson(person),
            () -> "Empty parameter at returned object"
        );

        assertEquals(exception.getMessage(), expectedMessage, () -> "Error message is incorrect");

    }

}
