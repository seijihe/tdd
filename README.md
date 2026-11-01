# TDD

### Personal Annotations

- JUnit:
    - Mainly annotations and methods `(SimpleMathTest.java)`
        ```java
        @BeforeAll
        @AfterAll
        @BeforeEach
        @Test
        @DisplayName("Name")
        ```
    - Pass a bunch of values as parameters to be tested in methods (can be a list or a file) `(PassingParametersTest.java)`
    - Repeat a method x times `(DemoRepetedTest.java)`
    - Set a timeout for method execution and compare array values `(ArraysCompareTest.java)`
    - Force the execution order of methods
        - `@TestMethodOrder()`: order of method execution
            - Randomly: to verify that there is no dependency between methods. This is also strongly recommended for unit tests, because unit tests should not depend on shared state between instances.
            - By index: at the method level (mainly) or class level (rarely). This is important in integration tests when the order of a test may depend on the result of another.
        - `@TestInstance()`: JUnit test lifecycle. Variable state shared across methods
            - Per method: an instance is created for each method, so you lose variable state across calls (recommended for unit tests)
            - Per class: an instance is created per class. This means you can share variable state across methods (recommended for integration tests)

- TDD
    - Cycle of TDD:
        - 1. Write a test and watch it fail (red)
        - 2. Write just enough code to make the test pass (green)
        - 3. Improve the code without changing its behavior (refactor) -> again...
    - TDD is not just a way to write tests; it is a methodology for writing code.
    - The advantage is that you know what the code should do before writing it.

Steps of TDD implementation:

- What should the code do? `@DisplayName("When creating a person successfully, it should return an object")`
- First, think about calling a service through an interface type `-> (RED)`
    ```java
    // Arrange
    IPersonService service = new PersonService();
    ```
- The compiler will complain because it does not exist yet. So we create the class and the interface `-> (GREEN while running unit tests)`
    ```java
    public interface IPersonService {}
    public class PersonService implements IPersonService {}
    ```
- Create a Person `-> (RED)`
    ```java
    Person person = new Person();
    ```
- The compiler will complain because the `Person` model does not exist. So we create it `-> (GREEN while running unit tests)`
    ```java
    public class Person {...}
    ```
- Call the `createPerson()` method, passing the created object `-> (RED)`
    ```java
    // Arrange
    IPersonService service = new PersonService();
    Person person = new Person();

    // Act
    service.createPerson(person);
    ```
- The compiler will complain because the method does not exist. So we add it to the interface and class `-> (GREEN while running unit tests)`
    ```java
    public interface IPersonService {
        void createPerson(Person person);
    }
    public class PersonService implements IPersonService {
        void createPerson(Person person) {

        }
    }
    ```
- Now, it should return a `Person` object
    ```java
    // Arrange
    IPersonService service = new PersonService();
    Person person = new Person();

    // Act
    Person actual = service.createPerson(person);

    // Assert
    assertNotNull(actual);
    ```

- The compiler will complain because the method signature does not match. So we adjust the interface and class to return a `Person` object `-> (GREEN while running unit tests)`
    ```java
    public interface IPersonService {
        Person createPerson(Person person);
    }
    public class PersonService implements IPersonService {
        Person createPerson(Person person) {
            return new Person();
        }
    }
    ```

- Now another test: `@DisplayName("When creating a person successfully, the returned person should contain the first name")`
    ```java
    assertEquals(person.getName(), actual.getName(), () -> "The first name is different");
    ```
- The unit test execution fails `-> (RED)`. So we adjust the method to pass `-> (GREEN while running unit tests)`
    ```java
    public class PersonService implements IPersonService {
        Person createPerson(Person person) {
            return person;
        }
    }
    ```

- Now, we have two tests for person creation. We can use `@BeforeEach` to reduce repetition.










