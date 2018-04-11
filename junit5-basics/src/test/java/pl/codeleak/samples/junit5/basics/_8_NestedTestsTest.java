package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.*;
import pl.codeleak.samples.shared.petclinic.model.Pet;
import pl.codeleak.samples.shared.petclinic.repository.Pets;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pets repository")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class _8_NestedTestsTest {

    private Pets testObj;

    @BeforeAll
    void beforeAll() {
        testObj = new Pets();
    }

    @Nested
    @DisplayName("when new instance created")
    class WhenNew {

        @Test
        @DisplayName("has 13 associated pets")
        void hasThirteenPets() {
            // act
            List<Pet> result = testObj.findAll();

            // assert
            assertEquals(result.size(), 13);
        }

        @Test
        @DisplayName("existing pet can be found by its name")
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
        @DisplayName("non existing pet will not be found")
        void doesNotFindExistingPet() {
            // arrange
            String givenName = "Anonymous";

            // act
            Optional<Pet> result = testObj.findByName(givenName);

            // assert
            assertFalse(result.isPresent());
        }

        @Nested
        @DisplayName("and when new pet is added")
        class AfterAdd {

            private Pet givenPet = Pets.aPet("New Pet", "1992-08-25", "David Schroeder");

            @BeforeEach
            void setUp() {
                testObj.add(givenPet);
            }

            @Test
            @DisplayName("14 pets exist")
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
            @DisplayName("it can be found by its name")
            void findsNewPet() {
                // act
                Optional<Pet> result = testObj.findByName(givenPet.getName());

                // assert
                assertTrue(result.isPresent());
                assertEquals(result.get(), givenPet);
            }
        }

        @Nested
        @DisplayName("and when a pet is removed")
        class AfterRemove {

            private String givenName = "Freddy";

            @BeforeEach
            void setUp() {
                Optional<Pet> optionalPet = testObj.findByName(givenName);

                Assumptions.assumeTrue(optionalPet.isPresent(), "No pet with given name exists!");

                testObj.remove(optionalPet.get());
            }

            @Test
            @DisplayName("it can't be found anymore")
            void doesNotFindRemovedPet() {
                // act
                Optional<Pet> result = testObj.findByName(givenName);

                // assert
                assertFalse(result.isPresent());
            }
        }
    }
}
