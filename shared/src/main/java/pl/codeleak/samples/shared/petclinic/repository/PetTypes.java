package pl.codeleak.samples.shared.petclinic.repository;

import pl.codeleak.samples.shared.petclinic.model.PetType;

import java.util.*;

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

    public static PetType byName(String petType) {
        return petTypes.stream()
                .filter(pt -> Objects.equals(pt.getName(), petType))
                .findFirst()
                .orElseThrow();
    }
}
