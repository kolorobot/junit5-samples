package pl.codeleak.samples.junit5.parameterized;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.codeleak.samples.shared.petclinic.model.Pet;
import pl.codeleak.samples.shared.petclinic.repository.Pets;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class _3_MethodParameterizedWithCustomArgumentConverterTests {

    private final VisitsValidator validator = new VisitsValidator();

    @DisplayName("Can schedule visit for pet")
    @ParameterizedTest
    @MethodSource("pets")
    void methodSourceParameterized(
            @ConvertWith(StringToPet.class) Pet givenPet, LocalDateTime givenDate, boolean expectedResult) {
        // act
        boolean result = validator.canScheduleVisitForPet(givenPet, givenDate);

        // assert
        assertEquals(result, expectedResult);
    }

    private static Stream<Arguments> pets() {
        return Stream.of(
                Arguments.of("Freddy", "2017-01-06T10:00:00", false),
                Arguments.of("Freddy", "2017-11-06T10:00:00", true),
                Arguments.of("Mulligan", "2017-01-06T10:00:00", true),
                Arguments.of("Leo", "2017-01-03T10:00:00", false)
        );
    }

    private static class StringToPet implements ArgumentConverter {
        @Override
        public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
            return Pets.byName(source.toString()).get();
        }
    }
}