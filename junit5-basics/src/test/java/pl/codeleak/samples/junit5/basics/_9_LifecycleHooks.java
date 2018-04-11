package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

@ExtendWith(BeforeAndAfterAllCallbackTest1.class)
class BeforeAndAfterAllCallbackTest1 implements BeforeAllCallback, AfterAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) {

    }

    @Override
    public void afterAll(ExtensionContext context) {

    }

    @BeforeAll
    static void beforeAll() {

    }

    @AfterAll
    static void afterAll() {

    }

    @Test
    void test() {
        Assertions.assertTrue(true);
    }
}

@ExtendWith(BeforeAndAfterAllCallbackTest2.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BeforeAndAfterAllCallbackTest2 implements BeforeAllCallback, AfterAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) {
    }

    @Override
    public void afterAll(ExtensionContext context) {

    }

    @BeforeAll
    void beforeAll() {

    }

    @AfterAll
    void afterAll() {

    }

    @Test
    void test() {
        Assertions.assertTrue(true);
    }
}

@ExtendWith(OtherCallbacksTest.class)
class OtherCallbacksTest implements
    BeforeEachCallback, BeforeTestExecutionCallback,
    AfterEachCallback, AfterTestExecutionCallback {

    @Override
    public void beforeEach(ExtensionContext context) {

    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {

    }

    @Override
    public void afterEach(ExtensionContext context) {

    }

    @Override
    public void afterTestExecution(ExtensionContext context) {

    }

    @BeforeEach
    void beforeEach() {

    }

    @AfterEach
    void afterEach() {

    }

    @Test
    void test1() {
        Assertions.assertTrue(true);
    }

    @Test
    void test2() {
        Assertions.assertTrue(true);
    }
}