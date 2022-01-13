package pl.codeleak.samples.junit5.assertj;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.codeleak.samples.shared.petclinic.model.Pet;

import java.time.LocalDate;

import static pl.codeleak.samples.junit5.assertj.PetAssertion.assertThat;

class _5_CustomAssertions {
    @Test
    void customAssertions() {
        // act
        Pet pet = Pet.builder()
                .birthDate(LocalDate.of(2010, 12, 31))
                .owner(null)
                .build();

        // assert
        assertThat(pet)
                .isHomeless()
                .birthDateBefore(LocalDate.now());
    }
}


class PetAssertion extends AbstractAssert<PetAssertion, Pet> {

    public static PetAssertion assertThat(Pet actual) {
        return new PetAssertion(actual);
    }

    protected PetAssertion(Pet pet) {
        super(pet, PetAssertion.class);
    }

    public PetAssertion hasOwner() {
        Assertions.assertThat(actual.getOwner()).isNotNull();
        return this;
    }

    public PetAssertion isHomeless() {
        Assertions.assertThat(actual.getOwner()).isNull();
        return this;
    }

    public PetAssertion birthDateBefore(LocalDate localDate) {
        Assertions.assertThat(actual.getBirthDate()).isBefore(localDate);
        return this;
    }
}
