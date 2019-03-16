package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

class _2c_AssertTimeout {

    @Test
    void assertTimeout() {
        // arrange
        Executable task = () -> Thread.sleep(100);

        // waits for the task to finish before failing the test
        Assertions.assertTimeout(Duration.ofMillis(500), task::execute);
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
        Executable task = () -> Thread.sleep(100);

        // aborts execution when timeout exceeded
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(1000), task::execute);
    }

    @Test
    void assertTimeoutPreemptivelyWithThrowingSupplier() {
        // arrange
        ThrowingSupplier<String> task = () -> {
            Thread.sleep(100);
            return "result";
        };

        // aborts execution when timeout exceeded, returns the result
        String result = Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), task::get);

        Assertions.assertEquals("result", result);
    }

    @Test
    void assertTimeoutPreemptivelyWithAssertDoesNotThrow() {
        // arrange
        ThrowingSupplier<String> task = () -> {
            Thread.sleep(100);
//            throw new IllegalArgumentException("Well done!");
            return "result";
        };

        // aborts execution when timeout exceeded, asserts not exceptions was thrown, returns the result
        String result = Assertions.assertTimeoutPreemptively(Duration.ofMillis(200),
                () -> Assertions.assertDoesNotThrow(task));

        Assertions.assertEquals("result", result);
    }
}
