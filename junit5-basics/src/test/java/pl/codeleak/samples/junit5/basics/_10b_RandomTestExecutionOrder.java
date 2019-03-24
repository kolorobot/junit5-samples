package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
class _10b_RandomTestExecutionOrder {

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
