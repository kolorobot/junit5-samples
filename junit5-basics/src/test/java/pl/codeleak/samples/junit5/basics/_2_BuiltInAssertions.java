package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

class _2_BuiltInAssertions {

    @Test
    void basicAssertions() {
        // act
        List<String> owners = Arrays.asList("Betty Davis", "Eduardo Rodriquez");

        // assert
        Assertions.assertNotNull(owners);
        Assertions.assertSame(owners, owners);
        Assertions.assertTrue(!owners.isEmpty());
        Assertions.assertFalse(owners::isEmpty);
        Assertions.assertEquals(2, owners.size(), "Found owner names size is incorrect");
        Assertions.assertLinesMatch(Arrays.asList("Betty Davis", "Eduardo Rodriquez"), owners);
        Assertions.assertArrayEquals(new String[]{"Betty Davis", "Eduardo Rodriquez"}, owners.toArray(new String[0]));
    }

    @DisplayName("assertAll")
    @Test
    void assertAll() {
        List<String> owners = Arrays.asList("Betty Davis", "Eduardo Rodriquez");

        // assert
        Assertions.assertAll(
            () -> Assertions.assertTrue(owners.contains("Betty Doe"), "Contains Betty Doe"),
            () -> Assertions.assertTrue(owners.contains("John Doe"), "Continas John Doe"),
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
                Assertions.assertTrue(owners.contains("Betty Doe"), "Contains Betty Doe");

                Assertions.assertAll(
                    () -> Assertions.assertNotNull(owners),
                    () -> Assertions.assertTrue(owners.size() > 1)
                );
            }
        );
    }

    @Test
    void assertTimeout() {
        // arrange
        Executable task = () -> Thread.sleep(1000);

        // waits for the task to finish before failing the test
        Assertions.assertTimeout(Duration.ofMillis(100), task::execute);
    }

    @Test
    void assertTimeoutWithThrowingSupplier() {
        // arrange
        ThrowingSupplier<String> task = () -> "result";

        // waits for the task to finish before failing the test
        Assertions.assertTimeout(Duration.ofMillis(100), task::get);
    }

    @Test
    void assertTimeoutPreemptively() {
        // arrange
        Executable task = () -> Thread.sleep(1000);

        // abort execution when timeout exceeded
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(100), task::execute);
    }

    @Test
    void assertTimeoutPreemptivelyWithThrowingSupplier() {
        // arrange
        ThrowingSupplier<String> task = () -> "result";

        // abort execution when timeout exceeded, return the result
        String result = Assertions.assertTimeoutPreemptively(Duration.ofMillis(100), task::get);

        Assertions.assertEquals("result", result);
    }

    @Test
    void assertException() {
        // arrange
        Executable throwingExecutable = () -> {
            throw new RuntimeException("Unexpected error!");
        };

        // act and assert
        RuntimeException thrown = Assertions.assertThrows(
            RuntimeException.class, throwingExecutable::execute, "???"
        );

        Assertions.assertAll(
            () -> Assertions.assertEquals("Unexpected error!", thrown.getMessage()),
            () -> Assertions.assertNotNull(thrown.getCause())
        );
    }
}
