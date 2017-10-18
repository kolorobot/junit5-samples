package pl.codeleak.samples.junit5.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AssertJIntegrationTest {
    // TODO
    @Test
    void test() {
        assertAll(
            () -> assertThat(true).isTrue(),
            () -> assertThat(false).isFalse()
        );
    }
}