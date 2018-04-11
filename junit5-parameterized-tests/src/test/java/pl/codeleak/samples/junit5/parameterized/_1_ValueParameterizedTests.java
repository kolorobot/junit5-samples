package pl.codeleak.samples.junit5.parameterized;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class _1_ValueParameterizedTests {

    private final VisitsValidator validator = new VisitsValidator();

    @DisplayName("Has visit with description containing given phrase")
    @ParameterizedTest(name = "Given phrase is {arguments}")
    @ValueSource(strings = {"neu", "spa", "shot"})
    void valueSourceParametrized(String givenDescription) {
        // act
        boolean result = validator.hasVisitWithDescription(givenDescription);

        // assert
        assertTrue(result);
    }
}