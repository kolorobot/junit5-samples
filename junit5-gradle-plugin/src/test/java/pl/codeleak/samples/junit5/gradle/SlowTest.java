package pl.codeleak.samples.junit5.gradle;

import org.junit.jupiter.api.Test;
import pl.codeleak.samples.junit5.gradle.Slow;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Slow
class SlowTest {

    @Test
    void slowTest() throws InterruptedException {
        System.out.println("Slow test ... 1");
        Thread.sleep(1000);
    }

    @Test
    void slowTest2() throws InterruptedException {
        System.out.println("Slow test ... 2");
        Thread.sleep(1000);
    }

    @Test
    void slowTest3() throws InterruptedException {
        System.out.println("Slow test ... 3");
        Thread.sleep(1000);
    }
}
