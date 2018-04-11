package pl.codeleak.samples.junit5.parameterized;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pl.codeleak.samples.shared.petclinic.repository.VisitType;

import static org.junit.jupiter.api.Assertions.assertTrue;

class _3_EnumParameterizedTests {

    private final VisitsValidator validator = new VisitsValidator();

    @Disabled
    @DisplayName("Can schedule visit of type")
    @ParameterizedTest
    @EnumSource(VisitType.class)
    void enumParameterized(VisitType givenType) {
        // act
        boolean result = validator.canScheduleVisit(givenType);

        // assert
        assertTrue(result);
    }

    @DisplayName("Can schedule visit of type")
    @ParameterizedTest
    @EnumSource(value = VisitType.class, names = {"CONSULTATION", "DIAGNOSTICS"})
    void enumIncludeParameterized(VisitType givenType) {
        // act
        boolean result = validator.canScheduleVisit(givenType);

        // assert
        assertTrue(result);
    }


    @DisplayName("Can schedule visit of type")
    @ParameterizedTest
    @EnumSource(value = VisitType.class, names = "SURGERY", mode = EnumSource.Mode.EXCLUDE)
    void enumExcludeParameterized(VisitType givenType) {
        // act
        boolean result = validator.canScheduleVisit(givenType);

        // assert
        assertTrue(result);
    }
}