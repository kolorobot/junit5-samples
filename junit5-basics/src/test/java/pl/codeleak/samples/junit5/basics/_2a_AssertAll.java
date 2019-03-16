package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

class _2a_AssertAll {

    @DisplayName("assertAll")
    @Test
    void assertAll() {
        List<String> owners = Arrays.asList("Betty Davis", "Eduardo Rodriquez");

        // assert
        Assertions.assertAll(
                () -> Assertions.assertTrue(owners.contains("Betty Davis"), "Contains Betty Davis"),
                () -> Assertions.assertFalse(owners.contains("John Doe"), "Does not contain John Doe"),
                () -> Assertions.assertTrue(owners.contains("Eduardo Rodriquez"), "Eduardo Rodriquez")
        );
    }

    @DisplayName("assertAll (dependent)")
    @Test
    void assertAllDependent() {
        List<String> owners = Arrays.asList("Betty Davis", "Eduardo Rodriquez");

        // assert
        Assertions.assertAll(
                () -> {
                    Assertions.assertTrue(owners.contains("Betty Davis"), "Eduardo Rodriquez");

                    Assertions.assertAll(
                            () -> Assertions.assertNotNull(owners),
                            () -> Assertions.assertTrue(owners.size() > 1)
                    );
                }
        );
    }
}
