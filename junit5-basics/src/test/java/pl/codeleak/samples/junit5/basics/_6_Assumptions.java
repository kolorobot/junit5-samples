package pl.codeleak.samples.junit5.basics;


import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AssumptionInBeforeAll {

    @BeforeAll
    static void beforeAll() {
        String osName = System.getProperty("os.name");
        Assumptions
            .assumeTrue(osName.matches("Linux"), "Linux expected. Got " + osName);
    }

    @Test
    void test() {
        fail("Simply fails, but only on Linux");
    }
}

class AssertAssumingThat {
    @Test
    void test() {
        String osName = System.getProperty("os.name");
        assumingThat(osName.matches("Windows"), () -> {
            // perform these assertions only on Windows
            assertTrue(true);
        });
    }
}
