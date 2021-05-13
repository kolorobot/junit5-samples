package pl.codeleak.samples.junit5.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class _3_MockAndVerify {

    @Mock
    Set<Integer> mock;

    @Mock
    Set<Integer> anotherMock;

    @Test
    void mockAndVerify() {
        // arrange
        when(mock.add(1))
                .thenReturn(true);
        // act
        mock.add(1);

        // assert
        verify(mock).add(1);
    }

    @Test
    void mockAndVerifyMultipleCalls() {
        // arrange
        when(mock.add(1))
                .thenReturn(true)
                .thenReturn(false)
                .thenReturn(false);
        // act
        mock.add(1);
        mock.add(1);
        mock.add(1);

        // assert
        verify(mock, times(3)).add(1);
    }


    @Test
    void verifyInOrder() {
        when(mock.add(1)).thenReturn(true);
        when(anotherMock.add(1)).thenReturn(true);

        anotherMock.add(1);
        mock.add(1);

        InOrder inOrder = inOrder(anotherMock, mock);
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
        when(mock.add(1)).thenReturn(true);
        // act
        mock.add(1);

        // assert
        verify(mock).add(1);
        Mockito.verifyNoMoreInteractions(mock);
    }

    @Test
    void mockWithThenThrow() {
        // arrange
        when(mock.add(1)).thenReturn(true);
        when(mock.add(2)).thenThrow(new IllegalArgumentException("2"));

        // act
        mock.add(1);
        assertThrows(IllegalArgumentException.class, () -> mock.add(2));

        verify(mock).add(1);
        verify(mock).add(2);

        Mockito.verifyNoMoreInteractions(mock);
    }
}
