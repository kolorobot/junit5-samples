package pl.codeleak.samples.junit5.assertj;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.codeleak.samples.shared.petclinic.model.Owner;
import pl.codeleak.samples.shared.petclinic.repository.Owners;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AssertJExceptionTest {

    private final Owners testObj = new Owners();

    // assertJ exceptions assert style
    @Test
    @DisplayName("Should throw exception when trying to save to read only repository (AssertJ)")
    void throwsExceptionWhenWriting() {
        // arrange
        Owner givenOwner = Owners.anOwner("Maciek", "Koziara", "address", "city", "telephone");

        // act
        assertThatThrownBy(() -> testObj.add(givenOwner))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage(Owners.EXCEPTION_MESSAGE);
    }

    // junit5 exceptions assert style
    @Test
    @DisplayName("Should throw exception when trying to save to read only repository (Junit5)")
    void throwsExceptionWhenWriting2() {
        // arrange
        Owner givenOwner = Owners.anOwner("Maciek", "Koziara", "address", "city", "telephone");

        // act
        UnsupportedOperationException resultException = assertThrows(UnsupportedOperationException.class, () -> testObj.add(givenOwner));
        assertEquals(resultException.getMessage(), Owners.EXCEPTION_MESSAGE);
    }

    // podsumowanie co i jak my używamy


}
