package pl.codeleak.samples.junit5.assertj;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.codeleak.samples.shared.petclinic.model.Owner;
import pl.codeleak.samples.shared.petclinic.repository.Owners;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("")
class _2_AssertAllAndSoftAssertions {

    private final Owners testObj = new Owners();

    @Test
    void softAssertions() {
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
    void assertAllWithAssertThat() {
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
    void assertThatWithAssertAll() {
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