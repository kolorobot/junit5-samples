package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.*;

@DisplayName("Repeated tests")
class _1b_RepeatedTest {

    @DisplayName("Repeated test (1)")
    @RepeatedTest(value = 2)
    void repeatedTest() {

    }

    @DisplayName("Repeated test (2)")
    @RepeatedTest(value = 2, name = "(Custom) #{currentRepetition} of {totalRepetitions}")
    void repeatedTestWithRepetitionInfo() {

    }
}
