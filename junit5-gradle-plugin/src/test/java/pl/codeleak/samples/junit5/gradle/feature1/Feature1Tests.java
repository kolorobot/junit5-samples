package pl.codeleak.samples.junit5.gradle.feature1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("feature1")
class Feature1Tests {

    @Test
    void feature1Test1() {
        Assertions.assertTrue(true);
    }

    @Test
    void feature1Test2() {
        Assertions.assertTrue(true);
    }
}
