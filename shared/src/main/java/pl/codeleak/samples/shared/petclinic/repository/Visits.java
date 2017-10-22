package pl.codeleak.samples.shared.petclinic.repository;

import pl.codeleak.samples.shared.petclinic.model.Pet;
import pl.codeleak.samples.shared.petclinic.model.Visit;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Visits {

    private static List<Visit> visits = Arrays.asList(
        aVisit("2017-01-03T10:00:00", "rabies shot", Pets.byName("Leo").get()),
        aVisit("2017-01-04T10:00:00", "neutered", Pets.byName("Samantha").get()),
        aVisit("2017-01-05T10:00:00", "neutered", Pets.byName("Lucky").get()),
        aVisit("2017-01-06T10:00:00", "spayed", Pets.byName("Freddy").get())
    );


    public static Visit aVisit(String dateTime, String description, Pet pet) {
        return Visit.builder()
                    .date(LocalDateTime.parse(dateTime))
                    .description(description)
                    .pet(pet).build();
    }

    public Optional<Visit> findByPet(Pet pet) {
        return visits.stream()
                .filter(v -> v.getPet().equals(pet))
                .findFirst();
    }
}
