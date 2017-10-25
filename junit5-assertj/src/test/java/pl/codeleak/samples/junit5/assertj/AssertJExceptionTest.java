package pl.codeleak.samples.junit5.assertj;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.codeleak.samples.shared.petclinic.model.Owner;
import pl.codeleak.samples.shared.petclinic.repository.Owners;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AssertJExceptionTest {

    private final Owners testObj = new Owners();

    @Test
    @DisplayName("Should throw an exception when adding to read-only repository (AssertJ Style)")
    void throwsExceptionWhenWriting1() {
        // arrange
        Owner givenOwner = Owners.anOwner("George", "Franklin", "110 W. Liberty St.", "Madison", "6085551023");

        // assert & act
        assertThatThrownBy(() -> testObj.add(givenOwner))
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessage(Owners.OPERATION_NOT_SUPPORTED_MESSAGE);
    }

    @Test
    @DisplayName("Should throw an exception when adding to read-only repository (JUnit5 Style)")
    void throwsExceptionWhenWriting2() {
        // arrange
        Owner givenOwner = Owners.anOwner("George", "Franklin", "110 W. Liberty St.", "Madison", "6085551023");

        // act
        UnsupportedOperationException resultException =
            assertThrows(UnsupportedOperationException.class, () -> testObj.add(givenOwner));

        // assert
        assertEquals(resultException.getMessage(), Owners.OPERATION_NOT_SUPPORTED_MESSAGE);
    }
}
