# TDD

- Some annotations:
    - Mainly annotations and methods `(SimpleMathTest.java)`
        ```java
        @BeforeAll
        @AfterAll
        @BeforeEach
        @Test
        @DisplayName("Name")
        ```
    - Pass a bunch of values as parameters to be tested in methods (can be a list or file) `(PassingParametersTest.java)`
    - Repete a method x times `(DemoRepetedTest.java)`
    - Timeout a method execution, Compare Array values `(ArraysCompareTest.java)`
    - Force methods order execution
        - @TestMethodOrder(). Order of methods executions
            - Randonly: to verify that there is no dependency between methods. In addition strongly recommended for Unit Test, because should not have dependencies between unit tests instances. 
            - By indice: at method (mainly) or class level (rarely). Important at Integration tests when the order of a test maybe depends of the result of other.
        - @TestInstance() - Junit Tests Lifecycle. Variable state shared across methods
            - Per Method: instances is created per method, so you lose variable states across it (recommend for Unit Test)
            - Per Class: instances is created per class. That means you can share variables states across methods (recommend for Integration Tests)












