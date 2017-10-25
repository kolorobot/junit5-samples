package pl.codeleak.samples.junit5.basics.samples;

import pl.codeleak.samples.shared.petclinic.model.Pet;
import pl.codeleak.samples.shared.petclinic.model.Visit;
import pl.codeleak.samples.shared.petclinic.repository.Pets;
import pl.codeleak.samples.shared.petclinic.service.VisitScheduleService;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

class BadlyWrittenScheduleService implements VisitScheduleService {

    private final AtomicInteger visitsMade = new AtomicInteger();

    @Override
    public Visit scheduleVisit(Pet pet) {
        String petName = pet.getName();
        Pet foundPet = Pets.byName(petName).get();
        LocalDateTime visitDate = LocalDateTime.now().plusDays(foundPet.getName().length());

        visitsMade.incrementAndGet();

        simulateLongOperation(foundPet);

        return Visit.builder()
                    .pet(foundPet)
                    .date(visitDate)
                    .build();
    }

    int getMadeVisits() {
        return visitsMade.get();
    }

    private void simulateLongOperation(Pet foundPet) {
        try {
            Thread.sleep(foundPet.getName().length() * 150 + new Random().nextInt(350));
        } catch (InterruptedException ignored) {
        }
    }
}
