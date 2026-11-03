# TDD

- [JUnit Principal Annotations](#junit-principal-annotations)
- [TDD](#tdd)
- [Mockito](#mockito)

### JUnit Principal Annotations

> Here is represented by /junit workspace

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

- Test method name variations
    - testSomething_WhenSomething_ResultSomething()
    - testSomething_When_Something() 
    - testSomething_When_SomethingHappened_ShouldReturniAnotherthing() 

- BDD (Behavior-Driven Development): you'll define system behavior through business scenarios, using the Given–When–Then template
- AAA: define how the test is implemented
</br>
    |BDD|AAA|Purpose|
    |---|---|---|
    |Given|Arrange|Define initial conditions and test data.|
    |When|Act|Execute the action being tested.|
    |Then|Assert|Verify the expected outcome.|

---

### TDD

> This use /tdd workspace

- TDD (Test Driven Development)
    - You'll guide the system development, defining first tests before implementation
    - Cycle: Red (failing test) → Green (passing test) → Refactor (improve code).
    - TDD is not just a way to write tests; it is a methodology for writing code.
    - The advantage is that you know what the code should do before writing it.

- 1. BDD: What is the behavior of the system?
- 2. AAA: How will our test be organized?
- 3. TDD: In what order do we write the test and the code? (Red → Green → Refactor)

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

---

### Mockito

> This use /mock workspace

- We can create a Stub and test the interface logic contract makes sense, until have an implementation.
- Stub: A test double (dublê) that provides predefined responses to simulate a dependency's behavior during testing (it checks the **result**).
- Mock: A test double that verifies interactions with a dependency, such as whether a method was called with specific arguments (it checks the **behavior**).
- System (Method) Under Test (Sistema em teste)
    ```java
    IService stub = new ServiceStub();
    Business business = new Business(stub);
    ```
    Here, Business is the SUT, while stub is a dependency that may be mocked or stubbed.

- The problem with stub is, everytime you update the interface that stub double (IService), you automatically have to implement these new signature at stub class since it's a contract, eventhougt you not use that at this momemnt (e.g at `CourseService`). With mock you implement just the specific method. When you increase the number of methods, it's dificult to handle with that manually via stub. 

- Some Mockito mainly methods personal annotations
    ```java
    // Setting values to be returned by mock
    // ClassA mock = mock(ClassA.class);
    ClassA mock = mock() // New way
    when(mock.method()).thenReturn(something).thenReturn(anotherthing);
    when(mock.method()).thenReturn(something).thenReturn(anotherthing);
    asssertEquals(something, mock.method());
    asssertEquals(anothersomething, mock.method());

    // List Mock
    var list = mock(List.class);
    when(list.get(anyInt())).thenReturn("Something");
    asssertEquals(something, list.get(999))
    asssertEquals(something, list.get(anyInt()))

    // Mock and exception
    when(list.get(9)).thenThrow(new RuntimeException("Foo Bar"));

    /*
        For void methods where there is no return to be validated at assert()
        you can also use mockito verify(). 
        
        That verifies whether a method was called 
        on a mock object, including how many times and with which arguments. 

        For example, above, after execution of "list.get(0)" you can verify if 
        the mocked method was invoked.
    */
    verify(list).get(0);
    verify(mock).method("Something");
    verify(mock, times(1)).method("Something");
    verify(mock, atLeast(1)).method("Something");
    verify(mock, atLeastOnce()).method("Something");
    verify(mock, never()).method("Something"); // When you never called that at your SUT

    /*
        You can also use then().should() and never(). It's the same of verify(),
        just more BDD-Style. Thats came from library BDDMockito. 
    */
    then(list).shoud().get(0);
    then(mock).should().deleteCourse("Python for Beginners");
    then(mock).should(never()).deleteCourse("Spring MVC");

    // You can also capt the parameter passed as input to the mock method called
    ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
    list.get(0);
    then(list).should().get(argumentCaptor.capture());
    assertThat(argumentCaptor.getValue()).isEqualTo(0);

    // A method call return 1 value. However here is a loop invoking the method, then that will return X times the result
    then(mock).should(time(9)).method(argumentCaptor.capture());
    assertThat(argumentCaptor.getValueAllValues()).hasSize(9);
    ```

- Mockito also have BDD support implementation (take a look at `CourseBusinessMockWithBDDTest.java`)
    - You can use AssertJ/Hamcrest to improve test readability through fluent, expressive, and chainable assertions ("Dev-Friendly"). 
    - JUnit executes tests; AssertJ works alongside verifing their results.
    - But, mainly, you not necessary have to use until default Junit assertions methods. 


















