package pl.codeleak.samples.junit5.mockito;

import pl.codeleak.samples.shared.petclinic.model.Pet;
import pl.codeleak.samples.shared.petclinic.model.Visit;
import pl.codeleak.samples.shared.petclinic.repository.Visits;
import pl.codeleak.samples.shared.petclinic.service.NotificationService;

class VisitNotificationService implements NotificationService {

    private static final String ERROR_MESSAGE = "There is no visit associated with pet %s";
    private static final String VISIT_MESSAGE = "Please come for a visit at %s";

    private final Visits visits;
    private final NotificationSender notificationSender;

    VisitNotificationService(Visits visits, NotificationSender notificationSender) {
        this.visits = visits;
        this.notificationSender = notificationSender;
    }

    void notifyAboutVisit(Pet pet) {
        Visit visit = visits.findByPet(pet)
                .orElseThrow(() -> new IllegalStateException(String.format(ERROR_MESSAGE, pet)));

        notificationSender.sendMessage(visit.getPet().getOwner().getTelephone(), String.format(VISIT_MESSAGE, visit.getDate()));
    }
}