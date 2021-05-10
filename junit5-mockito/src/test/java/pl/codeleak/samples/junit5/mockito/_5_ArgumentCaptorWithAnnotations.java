package pl.codeleak.samples.junit5.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class _5_ArgumentCaptorWithAnnotations {

    interface Doer {
        void doSomething();
        void doSomething(String arg1, Integer arg2);
    }

    @Mock
    Doer mock;

    @Captor
    ArgumentCaptor<String> arg1;

    @Captor
    ArgumentCaptor<Integer> arg2;

    @Test
    void argumentCaptors() {

        // arrange
        Mockito.doNothing().when(mock).doSomething(arg1.capture(), arg2.capture());

        // act
        mock.doSomething("Str", 42);

        assertEquals("Str", arg1.getValue());
        assertEquals(42, arg2.getValue());
    }

}
