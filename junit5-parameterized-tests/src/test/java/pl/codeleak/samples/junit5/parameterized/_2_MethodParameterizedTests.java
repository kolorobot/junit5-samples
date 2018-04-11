package pl.codeleak.samples.junit5.parameterized;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import pl.codeleak.samples.shared.petclinic.model.Pet;
import pl.codeleak.samples.shared.petclinic.repository.Pets;
import pl.codeleak.samples.shared.petclinic.repository.VisitType;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class _2_MethodParameterizedTests {

    private final VisitsValidator validator = new VisitsValidator();

    @DisplayName("Can schedule visit for pet")
    @ParameterizedTest
    @MethodSource("pets")
    void methodSourceParameterized(Pet givenPet, LocalDateTime givenDate, boolean expectedResult) {
        // act
        boolean result = validator.canScheduleVisitForPet(givenPet, givenDate);

        // assert
        assertEquals(result, expectedResult);
    }

    private static Stream<Arguments> pets() {
        return Stream.of(
            Arguments.of(Pets.byName("Freddy").get(), LocalDateTime.parse("2017-01-06T10:00:00"), false),
            Arguments.of(Pets.byName("Freddy").get(), LocalDateTime.parse("2017-11-06T10:00:00"), true),
            Arguments.of(Pets.byName("Mulligan").get(), LocalDateTime.parse("2017-01-06T10:00:00"), true),
            Arguments.of(Pets.byName("Leo").get(), LocalDateTime.parse("2017-01-03T10:00:00"), false)
        );
    }

}