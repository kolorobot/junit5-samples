package pl.codeleak.samples.junit5.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class _5a_ArgumentCaptorGetAllValues {

    interface SomeInterface {
        void someMethod(String arg1);
    }

    @Mock
    SomeInterface mock;

    @Captor
    ArgumentCaptor<String> argCaptor;

    @Test
    void argumentCaptorGetAllValues() {

        // arrange
        doNothing().when(mock)
                .someMethod(argCaptor.capture());

        // act
        mock.someMethod("Str 1");
        mock.someMethod("Str 2");
        mock.someMethod("Str 3");


        // assert
        assertAll(
                () -> assertEquals(argCaptor.getAllValues().get(0), "Str 1"),
                () -> assertEquals(argCaptor.getAllValues().get(1), "Str 2"),
                () -> assertEquals(argCaptor.getAllValues().get(2), "Str 3")
        );

        verify(mock, times(3)).someMethod(startsWith("Str "));
    }
}
