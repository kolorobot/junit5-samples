package pl.codeleak.samples.junit5.mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.codeleak.samples.shared.petclinic.model.Pet;
import pl.codeleak.samples.shared.petclinic.model.Visit;
import pl.codeleak.samples.shared.petclinic.repository.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class _6_InjectMocks {

    @InjectMocks
    private VisitNotificationService notificationService;

    @Mock
    private Visits visits;

    @Mock
    private NotificationSender notificationSender;

    @Captor
    private ArgumentCaptor<String> messageCaptor;

    @Captor
    private ArgumentCaptor<String> receiverCaptor;

    @Test
    @DisplayName("Should send notification about the visit for a given pet")
    void sendsNotification() {
        // arrange
        var petName = "Leo";
        var visitDate = "2017-10-26T19:00";

        Pet givenPet = Pets.byName(petName);
        Visit givenVisit = Visits.aVisit(visitDate, "Just a visit", petName, VisitType.DIAGNOSTICS);

        when(visits.findByPet(givenPet))
                .thenReturn(Optional.of(givenVisit));

        // act
        notificationService.notifyAboutVisit(givenPet);

        // assert
        verify(notificationSender, times(1))
                .sendMessage(receiverCaptor.capture(), messageCaptor.capture());

        assertTrue(messageCaptor.getValue().contains(visitDate));
        assertEquals(receiverCaptor.getValue(), givenPet.getOwner().getTelephone());
    }
}