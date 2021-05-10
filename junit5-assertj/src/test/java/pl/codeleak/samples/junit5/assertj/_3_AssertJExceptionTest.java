package pl.codeleak.samples.junit5.assertj;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.codeleak.samples.shared.petclinic.model.Owner;
import pl.codeleak.samples.shared.petclinic.model.Pet;
import pl.codeleak.samples.shared.petclinic.repository.Owners;
import pl.codeleak.samples.shared.petclinic.repository.Pets;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class _3_AssertJExceptionTest {

    private final ThrowingExceptionScheduleService testObj = new ThrowingExceptionScheduleService();

    @Test
    @DisplayName("Should throw an exception when scheduling a visit (AssertJ Style)")
    void exceptionTesting1() {
        // arrange
        Pet givenPet = Pets.byName("Leo");

        // assert & act
        assertThatThrownBy(() -> testObj.scheduleVisit(givenPet))
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessage(ThrowingExceptionScheduleService.OPERATION_NOT_SUPPORTED_MESSAGE);
    }

    @Test
    @DisplayName("Should throw an exception when scheduling a visit (JUnit5 Style)")
    void exceptionTesting2() {
        // arrange
        Pet givenPet = Pets.byName("Leo");

        // act
        UnsupportedOperationException resultException =
            assertThrows(UnsupportedOperationException.class, () -> testObj.scheduleVisit(givenPet));

        // assert
        assertEquals(resultException.getMessage(), ThrowingExceptionScheduleService.OPERATION_NOT_SUPPORTED_MESSAGE);
    }
}
