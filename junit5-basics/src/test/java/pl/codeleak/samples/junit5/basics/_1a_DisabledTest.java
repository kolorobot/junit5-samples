package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.*;

@DisplayName("Disabling tests")
class _1a_DisabledTest {

    @Disabled
    @DisplayName("Disabled test (1)")
    @Test
    void disabledTest1() {
        System.out.println("Disabled, will not show up");
    }

    @Disabled("Failing due to unknown reason")
    @DisplayName("Disabled test (2)")
    @Test
    void disabledTest2() {
        System.out.println("Disabled, will not show up");
    }
}
