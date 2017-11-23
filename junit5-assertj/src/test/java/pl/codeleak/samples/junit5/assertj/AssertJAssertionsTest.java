package pl.codeleak.samples.junit5.assertj;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.codeleak.samples.shared.petclinic.model.Owner;
import pl.codeleak.samples.shared.petclinic.repository.Owners;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertAll;

class AssertJAssertionsTest {

    private final Owners testObj = new Owners();

    @Test
    @DisplayName("Should find owner by its name (AssertJ Soft Assertions)")
    void findsOwnerByName1() {
        // arrange
        String givenName = "Jean";
        String expectedCity = "Monona";
        String expectedAddress = "105 N. Lake St.";

        // act
        Optional<Owner> result = testObj.findByName(givenName);

        // assert
        assertThat(result).isPresent();

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(result.get().getFirstName()).isEqualTo(givenName);
            softAssertions.assertThat(result.get().getCity()).isEqualTo(expectedCity);
            softAssertions.assertThat(result.get().getAddress()).isEqualTo(expectedAddress);
        });
    }

    @Test
    @DisplayName("Should find owner by its name (JUnit 5 + AssertJ)")
    void findsOwnerByName2() {
        // arrange
        String givenName = "Jean";
        String expectedCity = "Monona";
        String expectedAddress = "105 N. Lake St.";

        // act
        Optional<Owner> result = testObj.findByName(givenName);

        // assert
        assertThat(result).isPresent();

        assertAll(
            () -> assertThat(result.get().getFirstName()).isEqualTo(givenName),
            () -> assertThat(result.get().getCity()).isEqualTo(expectedCity),
            () -> assertThat(result.get().getAddress()).isEqualTo(expectedAddress)
        );
    }

    @Test
    @DisplayName("Should find owner by its name (AssertJ + JUnit 5)")
    void findsOwnerByName3() {
        // arrange
        String givenName = "Jean";
        String expectedCity = "Monona";
        String expectedAddress = "105 N. Lake St.";

        // act
        Optional<Owner> result = testObj.findByName(givenName);

        // assert
        assertThat(result).hasValueSatisfying(owner -> assertAll(
            () -> assertThat(owner.getFirstName()).isEqualTo(givenName),
            () -> assertThat(owner.getCity()).isEqualTo(expectedCity),
            () -> assertThat(owner.getAddress()).isEqualTo(expectedAddress)
        ));
    }
}