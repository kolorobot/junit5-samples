package pl.codeleak.samples.junit5.gradle.feature2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

class Feature2Tests {

    @Tag("feature2")
    @Tag("fast")
    @Test
    void feature2FastTest1() {
        Assertions.assertTrue(true);
    }

    @Tag("feature2")
    @Tag("slow")
    @Test
    void feature2SlowTest2() {
        Assertions.assertTrue(true);
    }
}