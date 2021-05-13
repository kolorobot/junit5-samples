package pl.codeleak.samples.junit5.assertj;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.codeleak.samples.shared.petclinic.model.Owner;
import pl.codeleak.samples.shared.petclinic.repository.Owners;

import java.util.Optional;

@ExtendWith(SoftAssertionsExtension.class)
public class _4_SoftAssertionsWithExtension {

    private final Owners testObj = new Owners();

    @InjectSoftAssertions
    SoftAssertions softly;

    @Test
    void softAssertions() {
        // arrange
        String givenName = "Jean";

        // act
        Optional<Owner> result = testObj.findByName(givenName);

        softly.assertThat(result.get().getFirstName()).isEqualTo("Jean");
        softly.assertThat(result.get().getCity()).isEqualTo("Mononas");
        softly.assertThat(result.get().getAddress()).isEqualTo("105 N. Lake St.");

    }
}
