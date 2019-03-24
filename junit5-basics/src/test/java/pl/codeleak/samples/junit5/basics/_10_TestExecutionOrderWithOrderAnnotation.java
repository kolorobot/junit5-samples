package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class _10_TestExecutionOrderWithOrderAnnotation {

    @Order(1)
    @Test
    void aTest() {

    }

    @Order(2)
    @Test
    void bTest() {

    }

    @Order(3)
    @Test
    void cTest() {

    }

}
