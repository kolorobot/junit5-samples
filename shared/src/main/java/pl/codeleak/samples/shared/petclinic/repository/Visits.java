package pl.codeleak.samples.shared.petclinic.repository;

import pl.codeleak.samples.shared.petclinic.model.Pet;
import pl.codeleak.samples.shared.petclinic.model.Visit;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Visits implements Repository<Visit> {

    private static List<Visit> visits = Arrays.asList(
        aVisit("2017-01-03T10:00:00", "rabies shot", "Leo", VisitType.SURGERY),
        aVisit("2017-01-04T10:00:00", "neutered", "Samantha", VisitType.CONSULTATION),
        aVisit("2017-01-05T10:00:00", "neutered", "Lucky", VisitType.DIAGNOSTICS),
        aVisit("2017-01-06T10:00:00", "spayed", "Freddy", VisitType.CONSULTATION)
    );

    public static Visit aVisit(String dateTime, String description, String petName, VisitType visitType) {
        return Visit.builder()
                    .date(LocalDateTime.parse(dateTime))
                    .description(description)
                    .pet(Pets.byName(petName))
                    .visitType(visitType)
                    .build();
    }

    public Optional<Visit> findByPet(Pet pet) {
        return findAll().stream()
                     .filter(visit -> visit.getPet().equals(pet))
                     .findFirst();
    }

    @Override
    public List<Visit> findAll() {
        return visits;
    }
}
