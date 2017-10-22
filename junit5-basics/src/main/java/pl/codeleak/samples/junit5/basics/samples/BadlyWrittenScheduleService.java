package pl.codeleak.samples.junit5.basics.samples;

import pl.codeleak.samples.shared.petclinic.model.Pet;
import pl.codeleak.samples.shared.petclinic.model.Visit;
import pl.codeleak.samples.shared.petclinic.repository.Pets;
import pl.codeleak.samples.shared.petclinic.service.VisitScheduleService;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

// zle napisany serwis im dluzsza nazwa peta tym dluzej sie wykonuje
public class BadlyWrittenScheduleService implements VisitScheduleService {

    private final AtomicInteger visitsMade = new AtomicInteger();

    @Override
    public Visit scheduleVisit(Pet pet) {
        String petName = pet.getName();
        Pet foundPet = Pets.byName(petName).get();
        LocalDateTime visitDate = LocalDateTime.now().plusDays(foundPet.getName().length());
        visitsMade.incrementAndGet();

        badlyWrittenVisitSql(foundPet);

        return Visit.builder()
                .pet(foundPet)
                .date(visitDate)
                .build();
    }

    public int getMadeVisits() {
        return visitsMade.get();
    }

    private void badlyWrittenVisitSql(Pet foundPet) {
        try {
            Thread.sleep(foundPet.getName().length() * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
