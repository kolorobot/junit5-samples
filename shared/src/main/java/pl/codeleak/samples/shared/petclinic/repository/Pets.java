package pl.codeleak.samples.shared.petclinic.repository;

import pl.codeleak.samples.shared.petclinic.model.Pet;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Pets {

    private static List<Pet> pets = Arrays.asList(
        aPet("Leo", "2013-09-07"),
        aPet("Basil", "2014-08-06"),
        aPet("Rosy", "2015-04-17"),
        aPet("Jewel", "2014-03-07"),
        aPet("Iggy", "2014-11-30"),
        aPet("George", "2014-01-20"),
        aPet("Samantha", "2016-09-04"),
        aPet("Max", "2015-09-04"),
        aPet("Lucky", "2014-08-06"),
        aPet("Mulligan", "2009-02-24"),
        aPet("Freddy", "2010-03-09"),
        aPet("Lucky", "2010-06-24"),
        aPet("Sly", "2012-06-08")
    );

    public static Pet aPet(String name, String birthDate) {
        return Pet.builder().name(name).birthDate(LocalDate.parse(birthDate)).build();
    }

    public static Optional<Pet> byName(String name) {
        return pets.stream().filter(p -> Objects.equals(p.getName(), name)).findFirst();
    }
}
