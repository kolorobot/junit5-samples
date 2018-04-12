package pl.codeleak.samples.junit5.mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.codeleak.samples.shared.petclinic.model.Pet;
import pl.codeleak.samples.shared.petclinic.model.Visit;
import pl.codeleak.samples.shared.petclinic.repository.Pets;
import pl.codeleak.samples.shared.petclinic.repository.VisitType;
import pl.codeleak.samples.shared.petclinic.repository.Visits;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OfficialMockitoExtensionTest {

    @InjectMocks
    private VisitNotificationService testObj;

    @Mock
    private Visits visits;

    @Mock
    private NotificationSender notificationSender;

    @Test
    @DisplayName("Should send notification about the visit for a given pet")
    void sendsNotification() {
        // arrange
        String petName = "Leo";
        String visitDate = "2017-10-26T19:00";

        Pet givenPet = Pets.byName(petName).get();
        Visit givenVisit = Visits.aVisit(visitDate, "Just a visit", petName, VisitType.DIAGNOSTICS);

        when(visits.findByPet(givenPet)).thenReturn(Optional.of(givenVisit));

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

        // act
        testObj.notifyAboutVisit(givenPet);

        // assert
        verify(notificationSender).sendMessage(eq(petName), messageCaptor.capture());
        assertTrue(messageCaptor.getValue().contains(visitDate));
    }
}