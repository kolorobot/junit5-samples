package pl.codeleak.samples.junit5.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class _3_MockAndVerify {

    @Mock
    Set<Integer> mock;

    @Mock
    Set<Integer> anotherMock;

    @Test
    void mockAndVerify() {
        // arrange
        Mockito.when(mock.add(1))
                .thenReturn(true);
        // act
        mock.add(1);

        // assert
        Mockito.verify(mock)
                .add(1);
    }

    @Test
    void mockAndVerifyMultipleCalls() {
        // arrange
        Mockito.when(mock.add(1))
                .thenReturn(true)
                .thenReturn(false)
                .thenReturn(false);
        // act
        mock.add(1);
        mock.add(1);
        mock.add(1);

        // assert
        Mockito.verify(mock, Mockito.times(3))
                .add(1);
    }


    @Test
    void verifyInOrder() {
        Mockito.when(mock.add(1)).thenReturn(true);
        Mockito.when(anotherMock.add(1)).thenReturn(true);

        anotherMock.add(1);
        mock.add(1);

        InOrder inOrder = Mockito.inOrder(anotherMock, mock);
        inOrder.verify(anotherMock).add(1);
        inOrder.verify(mock).add(1);
    }

    @Test
    void verifyNoInteractions() {
        Mockito.verifyNoInteractions(mock);
    }

    @Test
    void verifyNoMoreInteractions() {
        // arrange
        Mockito.when(mock.add(1))
                .thenReturn(true);
        // act
        mock.add(1);

        // assert
        Mockito.verify(mock)
                .add(1);
        Mockito.verifyNoMoreInteractions(mock);
    }

    @Test
    void mockWithThenThrow() {
        // arrange
        Mockito.when(mock.add(1)).thenReturn(true);
        Mockito.when(mock.add(2)).thenThrow(new IllegalArgumentException("2"));

        // act
        mock.add(1);
        assertThrows(IllegalArgumentException.class, () -> mock.add(2));

        Mockito.verify(mock)
                .add(1);
        Mockito.verify(mock)
                .add(2);
        Mockito.verifyNoMoreInteractions(mock);
    }
}
