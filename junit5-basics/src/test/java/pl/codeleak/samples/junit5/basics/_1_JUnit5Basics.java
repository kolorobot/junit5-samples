package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.*;

@DisplayName("JUnit5 - Test basics")
class _1_JUnit5Basics {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all tests (once)");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Runs before each test");
    }

    @Test
    void standardTest() {
        System.out.println("Test is running");
    }

    @Test
    public void testCanBePublicTestButWhy() {
        System.out.println("Test is running");
    }

    @DisplayName("My #2 JUnit5 test")
    @Test
    void testWithCustomDisplayName() {
        System.out.println("Test is running");
    }

    @DisplayName("Tagged JUnit5 test")
    @Tag("cool")
    @Test
    void tagged() {
        System.out.println("Test is running");
    }

    @Disabled("Failing due to unknown reason")
    @DisplayName("Disabled test")
    @Test
    void disabledTest() {
        System.out.println("Disabled, will not show up");
    }

    @DisplayName("Repeated test")
    @RepeatedTest(value = 2, name = "#{currentRepetition} of {totalRepetitions}")
    void repeatedTestWithRepetitionInfo() {
        System.out.println("Repeated test");
    }

    @AfterEach
    void afterEach() {
        System.out.println("Runs after each test");
    }
}
