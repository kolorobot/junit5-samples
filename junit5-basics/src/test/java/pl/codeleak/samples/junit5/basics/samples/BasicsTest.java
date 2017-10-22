package pl.codeleak.samples.junit5.basics.samples;

import org.junit.jupiter.api.*;
import pl.codeleak.samples.shared.petclinic.model.Owner;
import pl.codeleak.samples.shared.petclinic.repository.Owners;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicsTest {

    private final Owners testObj = new Owners();

    // Junit4 style

    @Test
    public void shouldFindOwnerByName() {
        // arrange
        String givenName = "Jean";
        String expectedCity = "Monona";
        String expectedAddress = "105 N. Lake St.";

        // act
        Optional<Owner> result = testObj.findByName(givenName);

        // assert
        assertTrue(result.isPresent());
        assertEquals(result.get().getFirstName(), givenName);
        assertEquals(result.get().getCity(), expectedCity);
        assertEquals(result.get().getAddress(), expectedAddress);
    }

    // Junit 5 style, displayName, assertAll, custom message, package private,
    // testReporter + testInfo -> wzmianka o mechaniźmie wstrzykiwania
    @Test
    @DisplayName("Should find owner by its name")
    void findsOwnerByName(TestInfo testInfo, TestReporter testReporter) {
        // arrange
        testReporter.publishEntry("Running test", testInfo.getDisplayName());

        String givenName = "Jean";
        String expectedCity = "Monona";
        String expectedAddress = "105 N. Lake St.";

        testReporter.publishEntry("name", givenName);

        // act
        Optional<Owner> result = testObj.findByName(givenName);

        // assert
        testReporter.publishEntry("Result", result.toString());

        assertTrue(result.isPresent(), "Unable to find entity by its name");
        assertAll(
                () -> assertEquals(result.get().getFirstName(), givenName),
                () -> assertEquals(result.get().getCity(), expectedCity),
                () -> assertEquals(result.get().getAddress(), expectedAddress)
        );
    }
}