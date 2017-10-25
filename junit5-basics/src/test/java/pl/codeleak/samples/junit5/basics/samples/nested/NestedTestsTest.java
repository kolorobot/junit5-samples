package pl.codeleak.samples.junit5.basics.samples.nested;

import org.junit.jupiter.api.*;
import pl.codeleak.samples.shared.petclinic.model.Pet;
import pl.codeleak.samples.shared.petclinic.repository.Pets;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// nested tests, badający repozytoria
@DisplayName("Pets repository")
class NestedTestsTest {

    private Pets testObj;

    @Nested
    @DisplayName("when new")
    class WhenNew {

        @BeforeEach
        void setUp() {
            testObj = new Pets();
        }

        @Test
        @DisplayName("has thirteen associated pets")
        void hasThirteenPets() {
            // act
            List<Pet> result = testObj.findAll();

            // assert
            assertEquals(result.size(), 13);
        }

        @Test
        @DisplayName("returns associated pet by its name")
        void findsExistingPet() {
            // arrange
            String givenName = "Rosy";
            LocalDate expectedBirthDate = LocalDate.of(2015, Month.APRIL, 17);

            // act
            Optional<Pet> result = testObj.findByName(givenName);

            // assert
            assertTrue(result.isPresent());
            assertAll(
                    () -> assertEquals(result.get().getName(), givenName),
                    () -> assertEquals(result.get().getBirthDate(), expectedBirthDate)
            );
        }

        @Test
        @DisplayName("returns empty optional when unable to find pet")
        void doesNotFindExistingPet() {
            // arrange
            String givenName = "Maciek";

            // act
            Optional<Pet> result = testObj.findByName(givenName);

            // assert
            assertFalse(result.isPresent());
        }

        @Nested
        @DisplayName("after add")
        class AfterAdd {

            private Pet givenPet = Pets.aPet("Maciek", "1992-08-25");

            @BeforeEach
            void setUp() {
                testObj.add(givenPet);
            }

            @Test
            @DisplayName("has fourteen associated pets")
            void hasFourteenPets() {
                // act
                List<Pet> result = testObj.findAll();

                // assert
                assertAll(
                        () -> assertEquals(14, result.size()),
                        () -> assertTrue(result.contains(givenPet))
                );
            }

            @Test
            @DisplayName("returns new pet by its name")
            void findsNewPet() {
                // arrange
                String givenName = "Maciek";

                // act
                Optional<Pet> result = testObj.findByName(givenName);

                // assert
                assertTrue(result.isPresent());
                assertEquals(result.get(), givenPet);
            }
        }

        @Nested
        @DisplayName("after remove")
        class AfterRemove {

            private String givenName = "Freddy";

            @BeforeEach
            void setUp() {
                Optional<Pet> foundPet = testObj.findByName(givenName);
                Assumptions.assumeTrue(foundPet.isPresent(), "Unable to run tests: pet to remove not found");
                testObj.remove(foundPet.get());
            }

            @Test
            @DisplayName("has twelve associated pets")
            void hasTwelvePets() {
                // act
                List<Pet> result = testObj.findAll();

                // assert
                assertEquals(result.size(), 12);
            }

            @Test
            @DisplayName("returns empty optional")
            void doesNotFindRemovedPet() {
                // act
                Optional<Pet> result = testObj.findByName(givenName);

                // assert
                assertFalse(result.isPresent());
            }
        }
    }
}
