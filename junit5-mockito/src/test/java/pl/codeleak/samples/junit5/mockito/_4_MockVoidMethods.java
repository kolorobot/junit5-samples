package pl.codeleak.samples.junit5.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class _4_MockVoidMethods {

    interface Doer {
        void doSomething();
        void doSomething(String arg1, Integer arg2);
    }

    @Mock
    Doer mock;

    @Test
    void mockVoidMethod() {
        // Mock with: doNothing(), doThrow(), and doAnswer() or not at all (default behavior)
        Mockito.doNothing().when(mock).doSomething();

        // act
        mock.doSomething();

        // assert
        Mockito.verify(mock).doSomething();
    }

    @Test
    void mockVoidMethodWithAnswer() throws InterruptedException {
        // arrange
        Mockito.doAnswer(invocation -> {
            Object arg1 = invocation.getArgument(0);
            Object arg2 = invocation.getArgument(1);

            assertEquals(arg1, "Str");

            return null;
        }).when(mock).doSomething("Str", 42);

        // act
        mock.doSomething("Str", 42);
        mock.doSomething("Str", 42);

        // assert
        Mockito.verify(mock, Mockito.times(2)).doSomething("Str", 42);
    }

    @Test
    void mockVoidWithArgumentCaptor() {

        // arrange
        ArgumentCaptor<String> arg1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> arg2 = ArgumentCaptor.forClass(Integer.class);

        Mockito.doNothing().when(mock).doSomething(arg1.capture(), arg2.capture());

        // act
        mock.doSomething("Str", 42);

        assertEquals("Str", arg1.getValue());
        assertEquals(42, arg2.getValue());
    }
}
