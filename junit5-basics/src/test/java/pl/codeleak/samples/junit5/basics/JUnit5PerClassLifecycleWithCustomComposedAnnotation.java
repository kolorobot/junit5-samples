package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.*;

import java.lang.annotation.*;

/**
 * Custom composed annotation that inherits the semantics of @TestInstance
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Documented
@interface DemoTest {

}

@DemoTest
@DisplayName("Junit5 - Custom composed annotation")
class JUnit5PerClassLifecycleWithCustomComposedAnnotation {

    private Object first = new Object();
    private Object second;

    @BeforeAll
    void beforeAll() {
        this.second = this.first;
    }

    @Test
    void first() {
        Assertions.assertEquals(second, first);
    }

    @Test
    void second() {
        Assertions.assertEquals(second, first);
    }
}
