package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class _10a_AlphanumericTestExecutionOrder {

    @Test
    void aTest() {

    }

    @Test
    void bTest() {

    }

    @Test
    void cTest() {

    }

}
