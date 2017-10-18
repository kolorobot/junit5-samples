package pl.codeleak.samples.junit5.gradle;

import org.junit.jupiter.api.Test;
import pl.codeleak.samples.junit5.gradle.support.Smoke;

@Smoke
class SmokeTest {

    @Test
    void smokeTest() {
        System.out.println("Smoke test ... with system property 'smoked' = " + System.getProperty("smoked"));
    }
}
