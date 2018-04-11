package pl.codeleak.samples.junit5.parameterresolver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

class Dependency {
    void doSomething() {
        System.out.println("Doing something... " + this.toString());
    }
}

@ExtendWith(InjectResolver.class)
class CustomParameterResolverTest {

    @Test
    void test1(@Inject Dependency injected) {
        injected.doSomething();
    }

    @Test
    void test2(@Inject Dependency injected) {
        injected.doSomething();
    }
}