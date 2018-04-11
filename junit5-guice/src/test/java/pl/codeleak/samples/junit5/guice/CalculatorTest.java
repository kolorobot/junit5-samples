package pl.codeleak.samples.junit5.guice;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

@ExtendWith(value = GuiceExtension.class)
@Guice(modules = {BasicCalculatorModule.class})
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculatorTest {

    @Inject
    private CalculatorService field;

    @Test
    @Inject
    void one(CalculatorService arg1, CalculatorService arg2) {
        field.calculate();
        arg1.calculate();
        arg2.calculate();
    }

    @Test
    void two(TestInfo testInfo) {
        field.calculate();
        System.out.println(testInfo);
    }
}