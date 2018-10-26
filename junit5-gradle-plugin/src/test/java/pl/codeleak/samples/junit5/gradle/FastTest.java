package pl.codeleak.samples.junit5.gradle;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("fast")
class FastTest {
    @Test
    void someFastTest() {
        System.out.println("Fast test ...");
    }
}
