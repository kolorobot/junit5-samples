package pl.codeleak.samples.junit5.mockito;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class _9_StaticMethods {

    static class Serializer {
        void serialize(Path path, String data) {
            try {
                Files.writeString(path, data);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }

    MockedStatic<Files> mockStatic = Mockito.mockStatic(Files.class);

    @AfterEach
    void closeStaticMock() {
        mockStatic.close();
    }


    @Test
    void mockStaticMethod() {

        // arrange
        mockStatic.when(() -> Files.writeString(Mockito.any(Path.class), Mockito.anyString()))
                .thenReturn(null);

        // act
        Serializer s = new Serializer();
        Path destPath = Path.of("temp.txt");
        s.serialize(destPath, "demo");

        // assert
        mockStatic.verify(() -> Files.writeString(destPath, "demo"));
    }

    @Test
    void mockStaticMethodWithAnswer() {

        // arrange
        mockStatic.when(() -> Files.writeString(Mockito.any(Path.class), Mockito.anyString()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // act
        Serializer s = new Serializer();
        Path destPath = Path.of("temp.txt");
        s.serialize(destPath, "demo");

        // assert
        mockStatic.verify(() -> Files.writeString(destPath, "demo"));
    }
}
