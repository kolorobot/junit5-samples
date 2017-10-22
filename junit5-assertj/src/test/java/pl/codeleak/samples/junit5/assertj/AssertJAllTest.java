package pl.codeleak.samples.junit5.assertj;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.codeleak.samples.shared.petclinic.model.Owner;
import pl.codeleak.samples.shared.petclinic.repository.Owners;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertAll;

class AssertJAllTest {

    private final Owners testObj = new Owners();

    // assert all assertJ style
    @Test
    @DisplayName("Should find owner by its name")
    @Disabled
    void findsOwnerByName() {
        // arrange
        String givenName = "Jean";
        String expectedCity = "Monona6";
        String expectedAddress = "1056 N. Lake St.";

        // act
        Optional<Owner> result = testObj.findByName(givenName);

        // assert
        assertThat(result).isPresent();

        assertSoftly(softly -> {
            softly.assertThat(result.get().getFirstName()).isEqualTo(givenName);
            softly.assertThat(result.get().getCity()).isEqualTo(expectedCity);
            softly.assertThat(result.get().getAddress()).isEqualTo(expectedAddress);
        });
    }

    // combaining assertAll with assertJ
    @Test
    @DisplayName("Should find owner by its name")
    @Disabled
    void findsOwnerByName2() {
        // arrange
        String givenName = "Jean";
        String expectedCity = "Monona6";
        String expectedAddress = "1056 N. Lake St.";

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

    // even more assertAll with assertJ combining
    @Test
    @DisplayName("Should find owner by its name")
    @Disabled
    void findsOwnerByName3() {
        // arrange
        String givenName = "Jean";
        String expectedCity = "Monona6";
        String expectedAddress = "1056 N. Lake St.";

        // act
        Optional<Owner> result = testObj.findByName(givenName);

        // assert
        assertThat(result).hasValueSatisfying(r -> {
            assertAll(
                    () -> assertThat(result.get().getFirstName()).isEqualTo(givenName),
                    () -> assertThat(result.get().getCity()).isEqualTo(expectedCity),
                    () -> assertThat(result.get().getAddress()).isEqualTo(expectedAddress)
            );
        });
    }
}