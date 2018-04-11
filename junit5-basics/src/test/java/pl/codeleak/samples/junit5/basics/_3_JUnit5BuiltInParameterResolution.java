package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.*;

class _3_JUnit5BuiltInParameterResolution {

    @BeforeAll
    static void beforeAll(TestInfo testInfo) {
        System.out.println("Before all can take parameters. Started: " + testInfo.getDisplayName());
    }

    @BeforeAll
    static void beforeAll(TestReporter testReporter) {
        testReporter.publishEntry("myEntry", "myValue");
    }

    @BeforeAll
    static void beforeAll(TestInfo testInfo, TestReporter testReporter) {
        testReporter.publishEntry("myOtherEntry", testInfo.getDisplayName());
    }


    @BeforeEach
    void beforeEach(TestInfo testInfo) {

    }

    @Test
    void standardTest(TestInfo testInfo) {

    }

    @DisplayName("Repeated test")
    @RepeatedTest(value = 2, name = "#{currentRepetition} of {totalRepetitions}")
    void repeatedTest(RepetitionInfo repetitionInfo) {
        System.out.println("Repeated test - " + repetitionInfo.toString());
    }

    @AfterAll
    static void afterAll() {

    }

    @AfterAll
    static void afterAll(TestInfo testInfo) {

    }

    @AfterEach
    void afterEach() {

    }
}
