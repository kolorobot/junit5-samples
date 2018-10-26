package pl.codeleak.samples.junit5.junit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

public class JUnit4Test {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void junit4Test() {
        System.out.println("JUnit 4 test ...");
        thrown.expect(IllegalArgumentException.class);
        throw new IllegalArgumentException();
    }
}
