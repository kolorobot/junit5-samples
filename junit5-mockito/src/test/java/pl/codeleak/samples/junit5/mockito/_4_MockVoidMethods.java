package pl.codeleak.samples.junit5.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class _4_MockVoidMethods {

    interface SomeInterface {
        void someMethod();
        void someMethod(String arg1, Integer arg2);
    }

    @Mock
    SomeInterface mock;

    @Test
    void mockVoidMethod() {
        // Mock with: doNothing(), doThrow(), and doAnswer() or not at all (default behavior)
        doNothing().when(mock).someMethod();

        // act
        mock.someMethod();

        // assert
        verify(mock).someMethod();
    }

    @Test
    void mockVoidMethodWithAnswer() throws InterruptedException {
        // arrange
        doAnswer(invocation -> {
            Object arg1 = invocation.getArgument(0);
            Object arg2 = invocation.getArgument(1);

            assertEquals(arg1, "Str");

            return null;
        }).when(mock).someMethod("Str", 42);

        // act
        mock.someMethod("Str", 42);
        mock.someMethod("Str", 42);

        // assert
        verify(mock, Mockito.times(2)).someMethod("Str", 42);
    }

    @Test
    void mockVoidWithArgumentCaptor() {

        // arrange
        ArgumentCaptor<String> arg1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> arg2 = ArgumentCaptor.forClass(Integer.class);

        doNothing().when(mock).someMethod(arg1.capture(), arg2.capture());

        // act
        mock.someMethod("Str", 42);

        assertEquals("Str", arg1.getValue());
        assertEquals(42, arg2.getValue());
    }
}
