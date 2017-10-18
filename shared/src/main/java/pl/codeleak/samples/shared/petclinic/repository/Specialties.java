package pl.codeleak.samples.shared.petclinic.repository;

import pl.codeleak.samples.shared.petclinic.model.Specialty;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Specialties {

    private static final List<Specialty> specialties = Arrays.asList(
        aSpecialty("surgery"),
        aSpecialty("neosurgery"),
        aSpecialty("dentistry"),
        aSpecialty("radiology")

    );

    public static Specialty aSpecialty(String specialty) {
        return Specialty.builder().name(specialty).build();
    }


    public static List<Specialty> byName(String... names) {
        return Arrays.stream(names)
                     .map(Specialties::byName)
                     .filter(Optional::isPresent)
                     .map(Optional::get)
                     .collect(Collectors.toList());
    }

    public static Optional<Specialty> byName(String name) {
        return specialties.stream().filter(s -> Objects.equals(s.getName(), name)).findFirst();
    }
}
