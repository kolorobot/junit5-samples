package pl.codeleak.samples.junit5.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class _8_Spying {

    @Spy
    Set<String> spy = new HashSet<>();

    @Test
    void spy() {
        // doReturn, doThrow, doAnswer must be used with spies
        // why? because real methods is called (we initialized spy to a HashSet)
        Mockito.doReturn(true).when(spy).add("str1");

        Assertions.assertTrue(spy.add("str1")); // stubbed call
        Assertions.assertEquals(0, spy.size()); // real call

        Mockito.verify(spy).add("str1");
        Mockito.verify(spy).size();
    }
}
