package pl.codeleak.samples.junit5.basics;


import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AssumptionsBasics {

    @BeforeAll
    static void beforeAll() {
        String osName = System.getProperty("os.name");
        Assumptions
            .assumeTrue(osName.matches("Linux"), "Linux expected. Got " + osName);
    }

    @Test
    void test() {
        assertTrue(false);
    }
}
