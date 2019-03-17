package pl.codeleak.samples.junit5.extensions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class _1_TempDir {

    private FileWriter fileWriter = new FileWriter();

    @TempDir
    Path tempDir;

    @BeforeEach
    void beforeEach(@TempDir Path tempDir) {
        assertTrue(Files.isDirectory(tempDir));
        assertTrue(Files.isDirectory(this.tempDir));
    }

    @RepeatedTest(3)
    void throwsErrorWhenTargetFileExists() throws IOException {
        // arrange
        Path output = Files.createFile(
                tempDir.resolve("output.txt")
        );

        // act & assert
        IOException expectedException = assertThrows(IOException.class, () -> fileWriter.writeTo(output.toString(), "test"));
        assertEquals("file already exists", expectedException.getMessage());
    }

    @Test
    void writesContentToFile(@TempDir Path tempDir) throws IOException {
        // arrange
        Path output = tempDir
                .resolve("output.txt");

        // act
        fileWriter.writeTo(output.toString(), "test");

        // assert
        assertAll(
                () -> assertTrue(Files.exists(output)),
                () -> assertLinesMatch(List.of("test"), Files.readAllLines(output))
        );
    }
}