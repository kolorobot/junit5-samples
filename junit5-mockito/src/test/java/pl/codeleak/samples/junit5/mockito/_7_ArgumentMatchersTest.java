package pl.codeleak.samples.junit5.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

// See org.mockito.ArgumentMatchers
@ExtendWith(MockitoExtension.class)
class _7_ArgumentMatchersTest {

    @Mock
    Set<Object> mock;

    @Test
    void any() {
        // arrange
        Mockito.when(mock.add(Mockito.any()))
                .thenReturn(true);

        Mockito.when(mock.add(Mockito.any(String.class)))
                .thenReturn(true);

        Mockito.when(mock.add(Mockito.anyString()))
                .thenReturn(true);


        // act
        mock.add("str1");
        mock.add("str2");
        mock.add("str3");

        // assert
        Mockito.verify(mock, Mockito.times(3))
                .add(Mockito.any());
    }

    @Test
    void argThat() {
        // arrange
        Mockito.when(mock.add(Mockito.argThat(arg -> {
            if (arg instanceof String) {
                return ((String) arg).matches("str1");
            }
            return false;
        }))).thenReturn(true);


        // act
        mock.add("str1");

        // assert
        Mockito.verify(mock, Mockito.times(1))
                .add("str1");
    }

    @Test
    void eq() {
        // arrange
        Mockito.when(mock.add(Mockito.eq("str1")))
                .thenReturn(true);
        // act
        mock.add("str1");

        // assert
        Mockito.verify(mock)
                .add(Mockito.eq("str1"));
    }
}
