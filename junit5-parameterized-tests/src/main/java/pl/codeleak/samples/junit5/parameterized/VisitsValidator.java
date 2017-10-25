package pl.codeleak.samples.junit5.parameterized;

import pl.codeleak.samples.shared.petclinic.model.Pet;
import pl.codeleak.samples.shared.petclinic.model.Visit;
import pl.codeleak.samples.shared.petclinic.repository.VisitType;
import pl.codeleak.samples.shared.petclinic.repository.Visits;

import java.time.LocalDateTime;

public class VisitsValidator {

    private final Visits visits = new Visits();

    public boolean hasVisitWithDescription(String description) {
        return visits.findAll().stream()
                .anyMatch(v -> v.getDescription().contains(description));
    }

    public boolean canScheduleVisitForPet(Pet pet, LocalDateTime dateTime) {
        return !visits.findByPet(pet)
                .filter(v -> v.getDate().equals(dateTime))
                .isPresent();
    }

    public boolean canScheduleVisit(VisitType visitType) {
        long totalVisits = visits.findAll().stream()
                .map(Visit::getVisitType)
                .filter(visitType::equals)
                .count();

        return totalVisits < visitType.getMaxVisits();
    }
}