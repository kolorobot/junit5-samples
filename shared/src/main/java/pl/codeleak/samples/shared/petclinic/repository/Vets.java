package pl.codeleak.samples.shared.petclinic.repository;

import pl.codeleak.samples.shared.petclinic.model.Vet;

import java.util.Arrays;
import java.util.List;

public class Vets {

    private static List<Vet> vets = Arrays.asList(
        aVet("James", "Carter", "radiology"),
        aVet("Helen", "Leary", "surgery", "neosurgery"),
        aVet("Linda", "Douglas", "dentistry"),
        aVet("Rafael", "Ortega", "dentistry"),
        aVet("Henry", "Stevens", "surgery"));

    public static Vet aVet(String firstname, String lastname, String... specialties) {
        return Vet.builder()
                  .firstName(firstname)
                  .lastName(lastname)
                  .specialties(Specialties.byName(specialties))
                  .build();
    }
}
