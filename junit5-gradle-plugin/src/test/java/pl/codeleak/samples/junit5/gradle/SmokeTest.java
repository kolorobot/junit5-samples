package pl.codeleak.samples.junit5.gradle;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import pl.codeleak.samples.junit5.gradle.support.Smoke;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Smoke
class SmokeTest {

    @Test
    void smokeTest() {
        String smoked = System.getProperty("smoked");

        assumeTrue(smoked != null && !smoked.isEmpty(), "No system property found! Re-run and pass 'smoked' system property.");

        System.out.println("Smoke test ... with system property 'smoked' = " + smoked);
    }
}
