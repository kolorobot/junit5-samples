package pl.codeleak.samples.shared.petclinic.repository;

import pl.codeleak.samples.shared.petclinic.model.PetType;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PetTypes {

    private static final List<PetType> petTypes =
        Arrays.asList(
            aPetType("cat"),
            aPetType("dog"),
            aPetType("lizard"),
            aPetType("snake"),
            aPetType("bird"),
            aPetType("hamster")
        );

    public static PetType aPetType(String petType) {
        return PetType.builder().name(petType).build();
    }

    public static Optional<PetType> byName(String petType) {
        return petTypes.stream().filter(pt -> Objects.equals(pt.getName(), petType)).findFirst();
    }
}
