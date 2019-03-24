package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.*;

import java.util.Comparator;

@TestMethodOrder(MethodLengthOrderer.class)
class _10c_CustomTestExecutionOrder {

    @Test
    void aTest() {

    }

    @Test
    void abTest() {

    }

    @Test
    void abcTest() {

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