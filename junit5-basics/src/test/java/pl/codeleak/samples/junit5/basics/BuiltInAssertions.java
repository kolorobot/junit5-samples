package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

class BuiltInAssertions {

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
        // act
        List<String> owners = Arrays.asList("Betty Davis", "Eduardo Rodriquez");

        // assert

        // 2 errors will be reported
        // Assertions.assertAll("owners",
        //     () -> Assertions.assertNotNull(null),
        //     () -> Assertions.assertTrue(false)
        // );

        Assertions.assertAll(
            () -> Assertions.assertNotNull(null, "May not be null"),
            () -> Assertions.assertTrue(false, "Must be true")
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
