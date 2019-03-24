package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.*;

import java.util.Comparator;

@TestMethodOrder(MethodLengthOrderer.class)
class _10c_CustomTestExecutionOrder {

    @Test
    void firstTest() {

    }


    @Test
    void secondTest() {

    }

    @Test
    void thirdTest() {

    }

}

class MethodLengthOrderer implements MethodOrderer {

    private Comparator<MethodDescriptor> comparator =
            Comparator.comparingInt(methodDescriptor -> methodDescriptor.getMethod().getName().length());

    @Override
    public void orderMethods(MethodOrdererContext context) {
        context.getMethodDescriptors().sort(comparator);
    }
}