package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class _2b_AssertException {

    @Test
    void assertException() {
        // arrange
        Executable throwingExecutable = () -> {
            throw new RuntimeException("Unexpected error!");
        };
        // act and assert
        RuntimeException thrown = assertThrows(
                RuntimeException.class, throwingExecutable, "???"
        );

        assertAll(
                () -> assertEquals("Unexpected error!", thrown.getMessage()),
                () -> assertNotNull(thrown.getCause())
        );
    }
}
