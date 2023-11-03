package pl.codeleak.samples.junit5.assertj;

import org.assertj.core.api.Condition;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.codeleak.samples.shared.petclinic.model.Pet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.Callable;

import static org.assertj.core.api.Assertions.*;

class _1_AssertJBasicAssertions {

    @Test
    void basics() {

        // arrange
        boolean bool = true;
        int integer = 2;
        String string = "AssertJ";
        Object object = new Object();

        // assert
        assertThat(bool).isTrue();

        assertThat(integer)
                .isLessThan(3)
                .isGreaterThan(1);

        assertThat(string)
                .hasSize(7)
                .matches("^AssertJ$")
                .contains("t")
                .doesNotContain("x");
    }

    @Test
    void dates() {
        // arrange
        LocalDate localDate = LocalDate.of(2011, Month.JANUARY, 1);

        // assert
        assertThat(localDate)
                .isBefore(LocalDate.now())
                .isAfter(LocalDate.of(2010, Month.JANUARY, 1));
    }

    @Test
    void lists() {
        // arrange
        List<String> list = Arrays.asList("one", "two", "three", "four");

        // assert
        assertThat(list)
                .isNotEmpty()
                .containsAnyOf("two", "four")
                .containsSubsequence("two", "three")
                .containsOnlyOnce("one");
    }

    @Test
    void maps() {
        // arrange
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");

        // assert
        assertThat(map)
                .doesNotContainKeys(6, 7)
                .containsKeys(1, 3)
                .containsValues("one", "five");
    }

    @Test
    void arrays() {
        // arrange
        String[] array = new String[]{"one", "two", "three", "four"};

        // assert
        assertThat(array)
                .isNotEmpty()
                .containsAnyOf("two", "four")
                .containsSubsequence("two", "three")
                .containsOnlyOnce("one");
    }

    @Test
    void files() throws IOException {
        // arrange
        File file1 = File.createTempFile("abc", "xyz");
        File file2 = File.createTempFile("abc", "xyz");

        // assert
        assertThat(file1)
                .isFile()
                .hasSameTextualContentAs(file2);
    }

    @Test
    @Disabled
    void paths() throws IOException {
        // arrange
        Path path1 = Path.of("abc", "xyz");

        // assert
        assertThat(path1)
                .doesNotExist()
                .endsWith(Path.of("xyz"));

    }

    @Test
    void objects() {
        // arrange
        Pet p1 = Pet.builder().name("Leo").build();
        Pet p2 = Pet.builder().name("Leo").build();

        // assert
        assertThat(p1)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(p2);

    }

    @Test
    void exceptions() {
        // arrange
        Callable<?> callable = () -> {
            throw new Exception("exception thrown");
        };

        // assert
        assertThatThrownBy(callable::call)
                .hasMessage("exception thrown")
                .hasNoCause();
    }

    @Test
    void softAssertions() {
        // arrange
        boolean bool = true;
        int integer = 2;
        String string = "AssertJ";

        // assert
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(bool).isTrue();
        softAssertions.assertThat(integer).isBetween(0, 3).isPositive();
        softAssertions.assertThat(string).contains("t");
        softAssertions.assertAll();
    }

    @Test
    void conditions() {
        // arrange
        Pet pet = Pet.builder()
                .name("Leo")
                .birthDate(LocalDate.of(2021, Month.JANUARY, 1))
                .build();

        Condition<Pet> leo = new Condition<>(p -> p.getName().matches("Leo"), "Leo");

        Condition<Pet> bornInJanuary2020 = new Condition<>(
                p -> p.getBirthDate().isEqual(LocalDate.of(2021, Month.JANUARY, 1)), "born in january 2020"
        );

        // assert
        assertThat(pet)
                .is(leo)
                .is(bornInJanuary2020);
    }
}
