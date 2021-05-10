package pl.codeleak.samples.junit5.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class _2_MockitoWithExtensionTest {

    @Mock
    Collection<String> col;

    @Test
    void mockAndVerify() {
        // arrange
        when(col.add("str"))
                .thenReturn(true);

        // act
        col.add("str");

        // assert
        verify(col, times(1))
                .add("str");
    }
}
