package pl.codeleak.samples.junit5.gradle;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Tag("fast")
class ReadJvmProperty {
    @Test
    void readsJvmProperty() {
        String prop1 = System.getProperty("prop1");

        assumeTrue(
                prop1 != null && !prop1.isEmpty(),
                "No system property found! Re-run and pass 'prop1' system property (-D)."
        );

        System.out.println("Fast test ... with system property 'prop1' = " + prop1);
    }

    @Test
    @EnabledIfSystemProperty(named = "prop1", matches = "true")
    void enabledIfSystemPropertyProp1IsTrue() {

    }
}
