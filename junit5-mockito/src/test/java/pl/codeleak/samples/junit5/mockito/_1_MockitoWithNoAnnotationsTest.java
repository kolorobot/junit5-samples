package pl.codeleak.samples.junit5.mockito;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.*;

class _1_MockitoWithNoAnnotationsTest {

    @Test
    void mockAndVerify() {
        // arrange
        Collection<String> col = mock(List.class);

        when(col.add("str"))
                .thenReturn(true);

        // act
        col.add("str");

        // assert
        verify(col, times(1))
                .add("str");
    }
}
