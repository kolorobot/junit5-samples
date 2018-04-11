package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("JUnit5 - Test lifecycle adjustments")
class _4_JUnit5PerClassLifecycle {

    private Object first = new Object();
    private Object second;

    @BeforeAll
    void beforeAll(TestInfo testInfo) {
        this.second = this.first;
        System.out.println("Non static before all.");
    }

    @BeforeEach
    void beforeEach() {
        Assertions.assertEquals(first, second);
    }

    @Test
    void first() {
        Assertions.assertEquals(first, second);
    }

    @Test
    void second() {
        Assertions.assertEquals(first, second);
    }

    @AfterAll
    void afterAll() {
        System.out.println("Non static after all.");
    }

    @AfterEach
    void afterEach() {
        Assertions.assertEquals(first, second);
    }
}
